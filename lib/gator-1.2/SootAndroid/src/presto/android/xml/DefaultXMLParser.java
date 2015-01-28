/*
 * DefaultXMLParser.java - part of the GATOR project
 *
 * Copyright (c) 2014, The Ohio State University
 *
 * This file is distributed under the terms described in LICENSE in the
 * root directory.
 */
package presto.android.xml;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import presto.android.Configs;
import presto.android.xml.XMLParser.AbstractXMLParser;
import soot.Scene;
import soot.SootClass;
import soot.SootField;
import soot.toolkits.scalar.Pair;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/*
 * This is a re-design of the xml parsing component.
 */
class DefaultXMLParser extends AbstractXMLParser {
  @Override
  public Integer getSystemRIdValue(String idName) {
    return sysRIdMap.get(idName);
  }

  @Override
  public Integer getSystemRLayoutValue(String layoutName) {
    return sysRLayoutMap.get(layoutName);
  }

  @Override
  public String getApplicationRLayoutName(Integer value) {
    return invRLayoutMap.get(value);
  }

  @Override
  public String getSystemRLayoutName(Integer value) {
    return invSysRLayoutMap.get(value);
  }

  @Override
  public AndroidView findViewById(Integer id) {
    AndroidView res = id2View.get(id);
    if (res != null) {
      return res;
    }

    res = sysId2View.get(id);
    if (res != null) {
      return res;
    }
    return null;
  }

  @Override
  public Set<Integer> getApplicationLayoutIdValues() {
    return invRLayoutMap.keySet();
  }

  @Override
  public Set<Integer> getSystemLayoutIdValues() {
    return invSysRLayoutMap.keySet();
  }

  @Override
  public Set<Integer> getApplicationMenuIdValues() {
    return invRMenuMap.keySet();
  }

  @Override
  public Set<Integer> getSystemMenuIdValues() {
    return invSysRMenuMap.keySet();
  }

  @Override
  public String getApplicationRMenuName(Integer value) {
    return invRMenuMap.get(value);
  }

  @Override
  public String getSystemRMenuName(Integer value) {
    return invSysRMenuMap.get(value);
  }

  @Override
  public Set<Integer> getApplicationRIdValues() {
    return invRIdMap.keySet();
  }

  @Override
  public Set<Integer> getSystemRIdValues() {
    return invSysRIdMap.keySet();
  }

  @Override
  public String getApplicationRIdName(Integer value) {
    return invRIdMap.get(value);
  }

  @Override
  public String getSystemRIdName(Integer value) {
    return invSysRIdMap.get(value);
  }

  @Override
  public Set<Integer> getStringIdValues() {
    return invRStringMap.keySet();
  }

  @Override
  public String getRStringName(Integer value) {
    return invRStringMap.get(value);
  }

  @Override
  public String getStringValue(Integer idValue) {
    return intAndStringValues.get(idValue);
  }

  //================================================

  private static final boolean debug = false;

  private static DefaultXMLParser theInst;

  private DefaultXMLParser() {
    doIt();
  }

  static DefaultXMLParser v() {
    if (theInst == null) {
      theInst = new DefaultXMLParser();
    }
    return theInst;
  }

  // === implementation details
  private void doIt() {
    readManifest();
    readRFile();

    // Strings must be read first
    readStrings();

    // Then, layout and menu. Later, we may need to read preference as well.
    readLayout();
    readMenu();

  }

  private void readManifest() {
    String fn = Configs.project + "/AndroidManifest.xml";
    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
    try {
      DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
      Document doc = dBuilder.parse(fn);
      Node root = doc.getElementsByTagName("manifest").item(0);
      appPkg = root.getAttributes().getNamedItem("package").getTextContent();

      Node appNode = doc.getElementsByTagName("application").item(0);
      NodeList nodes = appNode.getChildNodes();
      for (int i = 0; i < nodes.getLength(); ++i) {
        Node n = nodes.item(i);
        String eleName = n.getNodeName();
        if ("activity".equals(eleName)) {
          NamedNodeMap m = n.getAttributes();
          String cls = Helper.getClassName(
              m.getNamedItem("android:name").getTextContent(), appPkg);
          if (cls == null) {
            continue;
          }
          activities.add(cls);

          if (isMainActivity(n)) {
            assert mainActivity == null;
            mainActivity = Scene.v().getSootClass(cls);
          }

          ActivityLaunchMode launchMode = ActivityLaunchMode.standard;
          Node launchModeNode = m.getNamedItem("android:launchMode");
          if (launchModeNode != null) {
            launchMode = ActivityLaunchMode.valueOf(
                launchModeNode.getTextContent());
          }
          activityAndLaunchModes.put(cls, launchMode);
        }
      }
    } catch (Exception ex) {
      throw new RuntimeException(ex);
    }
  }

  private boolean isMainActivity(Node node) {
    assert "activity".equals(node.getNodeName());
    NodeList list = node.getChildNodes();
    for (int i = 0; i < list.getLength(); i++) {
      Node n = list.item(i);
      String s = n.getNodeName();
      if (!s.equals("intent-filter")) {
        continue;
      }
      if (isMainIntent(n)) {
        return true;
      }
    }
    return false;
  }

  private boolean isMainIntent(Node node) {
    assert "intent-filter".equals(node.getNodeName());
    boolean isMain = false;
    boolean isLauncher = false;
    NodeList list = node.getChildNodes();
    for (int i = 0; i < list.getLength(); i++) {
      Node n = list.item(i);
      String s = n.getNodeName();
      if ("action".equals(s)) {
        NamedNodeMap m = n.getAttributes();
        String action = m.getNamedItem("android:name").getTextContent();
        if ("android.intent.action.MAIN".equals(action)) {
          isMain = true;
        }
      } else if ("category".equals(s)) {
        NamedNodeMap m = n.getAttributes();
        String category = m.getNamedItem("android:name").getTextContent();
        if ("android.intent.category.LAUNCHER".equals(category)) {
          isLauncher = true;
        }
      }
    }
    return isMain && isLauncher;
  }

  // --- END

  // --- R files

  // <R.id field, its const val>
  private HashMap<String, Integer> rIdMap;
  private HashMap<Integer, String> invRIdMap;
  private HashMap<String, Integer> sysRIdMap;
  private HashMap<Integer, String> invSysRIdMap;

  // <R.layout field, its const val>
  private HashMap<String, Integer> rLayoutMap;
  private HashMap<Integer, String> invRLayoutMap;
  private HashMap<String, Integer> sysRLayoutMap;
  private HashMap<Integer, String> invSysRLayoutMap;

  // <R.menu field, its const val>
  private HashMap<String, Integer> rMenuMap;
  private HashMap<Integer, String> invRMenuMap;
  private HashMap<String, Integer> sysRMenuMap;
  private HashMap<Integer, String> invSysRMenuMap;

  // <R.string field, its const val>
  private HashMap<String, Integer> rStringMap;
  private HashMap<Integer, String> invRStringMap;

  private final HashMap<String, Integer> sysRStringMap = Maps.newHashMap();
  private final HashMap<Integer, String> invSysRStringMap = Maps.newHashMap();

  // <int const val, string val in xml>
  private HashMap<Integer, String> intAndStringValues;
  // <R.string field, its string val>
  private HashMap<String, String> rStringAndStringValues;

  private final HashMap<Integer, String> sysIntAndStringValues = Maps.newHashMap();
  private final HashMap<String, String> sysRStringAndStringValues = Maps.newHashMap();

  private void readRFile() {
    // R.id
    rIdMap = Maps.newHashMap();
    invRIdMap = Maps.newHashMap();
    final String rIdClass = appPkg + ".R$id";
    readIntConstFields(rIdClass, NameValueFunction.mapInvMap(rIdMap, invRIdMap));

    sysRIdMap = Maps.newHashMap();
    invSysRIdMap = Maps.newHashMap();
    final String sysRIdClass = "android.R$id";
    NameValueFunction sysRIdNVF = NameValueFunction.mapInvMap(sysRIdMap,
        invSysRIdMap);
    readIntConstFields(sysRIdClass, sysRIdNVF);

    final String internalSysRIdClass = "com.android.internal.R$id";
    readIntConstFields(internalSysRIdClass, sysRIdNVF);

    // R.layout
    rLayoutMap = Maps.newHashMap();
    invRLayoutMap = Maps.newHashMap();
    final String rLayoutClass = appPkg + ".R$layout";
    readIntConstFields(rLayoutClass,
        NameValueFunction.mapInvMap(rLayoutMap, invRLayoutMap));

    sysRLayoutMap = Maps.newHashMap();
    invSysRLayoutMap = Maps.newHashMap();
    final String sysRLayoutClass = "android.R$layout";
    NameValueFunction sysRLayoutNVF = NameValueFunction.mapInvMap(
        sysRLayoutMap, invSysRLayoutMap);
    readIntConstFields(sysRLayoutClass, sysRLayoutNVF);
    final String internalSysRLayoutClass = "com.android.internal.R$layout";
    readIntConstFields(internalSysRLayoutClass, sysRLayoutNVF);

    // R.menu
    rMenuMap = Maps.newHashMap();
    invRMenuMap = Maps.newHashMap();
    final String rMenuClass = appPkg + ".R$menu";
    // it may not exist
    if (new File(Configs.project + "/res/menu").exists()) {
      readIntConstFields(rMenuClass,
          NameValueFunction.mapInvMap(rMenuMap, invRMenuMap));
    }
    sysRMenuMap = Maps.newHashMap();
    invSysRMenuMap = Maps.newHashMap();
    if (Configs.numericApiLevel > 10) {
      NameValueFunction sysRMenuNVF = NameValueFunction.mapInvMap(sysRMenuMap,
          invSysRMenuMap);
      readIntConstFields("android.R$menu", sysRMenuNVF);
      readIntConstFields("com.android.internal.R$menu", sysRMenuNVF);
    }

    // R.string
    rStringMap = Maps.newHashMap();
    invRStringMap = Maps.newHashMap();
    final String rStringClass = appPkg + ".R$string";
    final String valuesDir = Configs.project + "/res/values";
    if (new File(valuesDir).exists()) {
      readIntConstFields(rStringClass,
          NameValueFunction.mapInvMap(rStringMap, invRStringMap));
    }
    NameValueFunction sysRStringNVF =
        NameValueFunction.mapInvMap(sysRStringMap, invSysRStringMap);
    readIntConstFields("android.R$string", sysRStringNVF);
    readIntConstFields("com.android.internal.R$string", sysRStringNVF);
  }

  private static void readIntConstFields(String clsName, NameValueFunction nvf) {
    SootClass idCls = Scene.v().getSootClass(clsName);
    // This particular R$* class is not used. Should be system R class though.
    if (idCls.isPhantom()) {
      if (Configs.verbose) {
        System.out.println("[DEBUG] " + clsName + " is phantom!");
      }
      return;
    }
    for (SootField f : idCls.getFields()) {
      String tag = f.getTag("IntegerConstantValueTag").toString();
      int val = Integer.parseInt(tag.substring("ConstantValue: ".length()));
      String name = f.getName();
      nvf.feed(name, val);
    }
  }

  // --- END

  // --- read layout files
  private static final String ID_ATTR = "android:id";
  private static final String TEXT_ATTR = "android:text";
  private static final String TITLE_ATTR = "android:title";

  private static int nonRId = -0x7f040000;

  private HashMap<Integer, AndroidView> id2View;
  private HashMap<Integer, AndroidView> sysId2View;

  private void readLayout() {
    id2View = Maps.newHashMap();
    readLayout(Configs.project, invRLayoutMap, id2View);

    sysId2View = Maps.newHashMap();
    readLayout(Configs.sysProj, invSysRLayoutMap, sysId2View);

    resolveIncludes(Configs.project, invRLayoutMap, id2View);
    resolveIncludes(Configs.sysProj, invSysRLayoutMap, sysId2View);
  }

  // TODO: due to the way we implement resolveIncludes(), now we need
  // to change findViewById.
  private void resolveIncludes(String proj, HashMap<Integer, String> nameMap,
      HashMap<Integer, AndroidView> viewMap) {

    HashMap<String, AndroidView> name2View = Maps.newHashMap();
    for (Map.Entry<Integer, String> entry : nameMap.entrySet()) {
      String name = entry.getValue();
      AndroidView view = viewMap.get(entry.getKey());
      name2View.put(name, view);
    }
    boolean isSys = (viewMap == sysId2View);
    LinkedList<AndroidView> work = Lists.newLinkedList();
    work.addAll(viewMap.values());
    while (!work.isEmpty()) {
      AndroidView view = work.remove();
      for (int i = 0; i < view.getNumberOfChildren(); i++) {
        IAndroidView child = view.getChildInternal(i);
        if (child instanceof AndroidView) {
          work.add((AndroidView) child);
          continue;
        }
        IncludeAndroidView iav = (IncludeAndroidView) child;
        String layoutId = iav.layoutId;
        AndroidView tgt = name2View.get(layoutId);
        if (tgt == null) {
          // not exist, let's get it on-demand
          String file = getLayoutFilePath(proj, layoutId, isSys);
          if (file == null) {
            System.err.println("[WARNING] Unknown layout " + layoutId
                + " included by " + view.getOrigin());
            continue;
          }
          tgt = new AndroidView();
          tgt.setParent(view, i);
          tgt.setOrigin(file);
          readLayout(file, tgt, isSys);
          int newId = nonRId--;
          viewMap.put(newId, tgt);
          nameMap.put(newId, layoutId);
        } else {
          tgt = (AndroidView) tgt.deepCopy();
          tgt.setParent(view, i);
        }
        Integer includeeId = iav.includeeId;
        if (includeeId != null) {
          tgt.setId(includeeId.intValue());
        }
        work.add(tgt);
      }
    }
  }

  private void readLayout(String proj, HashMap<Integer, String> in,
      HashMap<Integer, AndroidView> out) {
    if (debug) {
      System.out.println("*** read layout of " + proj);
    }
    boolean isSys = (invSysRLayoutMap == in);
    assert Configs.project.equals(proj) ^ isSys;

    for (Map.Entry<Integer, String> entry : in.entrySet()) {
      Integer layoutFileId = entry.getKey();
      String layoutFileName = entry.getValue();
      AndroidView root = new AndroidView();
      out.put(layoutFileId, root);

      String file = getLayoutFilePath(proj, layoutFileName, isSys);
      if (file == null) {
        System.err.println("[WARNING] Cannot find " + layoutFileName
            + ".xml in " + proj);
        continue;
      }

      readLayout(file, root, isSys);
    }
  }

  private void readLayout(String file, AndroidView root, boolean isSys) {
    Document doc;
    try {
      DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
      doc = dBuilder.parse(file);
    } catch (Exception ex) {
      throw new RuntimeException(ex);
    }

    Element rootElement = doc.getDocumentElement();
    // In older versions, Preference could be put in layout folder and we do
    // not support Prefernce yet.
    if (rootElement.getTagName().equals("PreferenceScreen")) {
      return;
    }

    LinkedList<Pair<Node, AndroidView>> work = Lists.newLinkedList();
    work.add(new Pair<Node, AndroidView>(rootElement, root));
    while (!work.isEmpty()) {
      Pair<Node, AndroidView> p = work.removeFirst();
      Node node = p.getO1();
      AndroidView view = p.getO2();
      view.setOrigin(file);

      NamedNodeMap attrMap = node.getAttributes();
      if (attrMap == null) {
        System.out.println(file + "!!!" + node.getClass() + "!!!"
            + node.toString() + "!!!" + node.getTextContent());
      }
      // Retrieve view id (android:id)
      Node idNode = attrMap.getNamedItem(ID_ATTR);
      int guiId = -1;
      String id = null;
      if (idNode != null) {
        String txt = idNode.getTextContent();
        Pair<String, Integer> pair = parseAndroidId(txt, isSys);
        id = pair.getO1();
        Integer guiIdObj = pair.getO2();
        if (guiIdObj == null) {
          if (debug) {
            System.err.println("[WARNING] unresolved android:id " + id + " in "
                + file);
          }
        } else {
          guiId = guiIdObj.intValue();
        }
      }

      // Retrieve view type
      String guiName = node.getNodeName();
      if ("view".equals(guiName)) {
        guiName = attrMap.getNamedItem("class").getTextContent();
      } else if (guiName.equals("MenuItemView")) {
        // FIXME(tony): this is an "approximation".
        guiName = "android.view.MenuItem";
      } else if (guiName.equals("menu")) {
        guiName = "android.view.Menu";
      } else if (guiName.equals("item")) {
        guiName = "android.view.MenuItem";
      } else if (guiName.equals("group")) {
        // TODO(tony): we might want to create a special fake class to
        // represent menu groups. But for now, let's simply pretend it's
        // a ViewGroup. Also, print a warning when we do see <group>
        if (Configs.verbose) {
          System.out.println("[TODO] <group> used in " + file);
        }
        guiName = "android.view.ViewGroup";
      } 

      if (debug) {
        System.out.println(guiName + " (" + guiId + ", " + id + ")");
      }

      // Retrieve text (android:text)
      String text = readAndroidTextOrTitle(attrMap, TEXT_ATTR);

      view.save(guiId, text, guiName);

      NodeList children = node.getChildNodes();
      for (int i = 0; i < children.getLength(); i++) {
        Node newNode = children.item(i);
        String nodeName = newNode.getNodeName();
        if ("#comment".equals(nodeName)) {
          continue;
        }
        if ("#text".equals(nodeName)) {
          // possible for XML files created on a different operating system
          // than the one our analysis is run on
          continue;
        }
        if (nodeName.equals("requestFocus")) {
          continue;
        }
        if (!newNode.hasAttributes() && !"TableRow".equals(nodeName)
            && !"View".equals(nodeName)) {
          System.err.println("[WARNING] no attribute node "
              + newNode.getNodeName());
        }

        if (newNode.getNodeName().equals("include")) {
          attrMap = newNode.getAttributes();
          String layoutTxt = attrMap.getNamedItem("layout").getTextContent();
          String layoutId = null;
          if (layoutTxt.startsWith("@layout/")) {
            layoutId = layoutTxt.substring("@layout/".length());
          } else if (layoutTxt.startsWith("@android:layout/")) {
            layoutId = layoutTxt.substring("@android:layout/".length());
          } else {
            throw new RuntimeException("[WARNING] Unhandled layout id "
                + layoutTxt);
          }
          Integer includeeId = null;
          id = null;
          idNode = attrMap.getNamedItem(ID_ATTR);
          if (idNode != null) {
            String txt = idNode.getTextContent();
            Pair<String, Integer> pair = parseAndroidId(txt, isSys);
            id = pair.getO1();
            Integer guiIdObj = pair.getO2();
            if (guiIdObj == null) {
              if (debug) {
                System.err.println("[WARNING] unresolved android:id " + id
                    + " in " + file);
              }
            } else {
              includeeId = guiIdObj;
            }
          }

          // view.saveInclude(layoutId, includeeId);
          IncludeAndroidView iav = new IncludeAndroidView(layoutId, includeeId);
          iav.setParent(view);
        } else {
          AndroidView newView = new AndroidView();
          newView.setParent(view);
          work.add(new Pair<Node, AndroidView>(newNode, newView));
        }
      }
    }
  }

  private static String getLayoutFilePath(String project, String layoutId,
      boolean isSys) {
    // special cases
    if ("keyguard_eca".equals(layoutId)) {
      // its real name is defined in values*/alias.xml
      // for our purpose, we can simply hack it
      assert isSys;
      // use the value for portrait
      String ret = project + "/res/layout/keyguard_emergency_carrier_area.xml";
      assert new File(ret).exists() : "ret=" + ret;
      return ret;
    }
    if ("status_bar_latest_event_ticker_large_icon".equals(layoutId)
        || "status_bar_latest_event_ticker".equals(layoutId)
        || "keyguard_screen_status_land".equals(layoutId)
        || "keyguard_screen_status_port".equals(layoutId)) {
      assert isSys;
      String ret = project + "/res/layout-sw600dp/" + layoutId + ".xml";
      if (!new File(ret).exists()) {
        ret = project + "/res/layout-sw720dp/" + layoutId + ".xml";
      }
      assert new File(ret).exists() : "ret=" + ret;
      return ret;
    }
    ArrayList<String> projectDirs = Lists.newArrayList();
    projectDirs.add(project);
    if (!isSys) {
      projectDirs.addAll(Configs.extLibs);
    }

    for (String proj : projectDirs) {
      String file = proj + "/res/layout/" + layoutId + ".xml";
      if (!new File(file).exists()) {
        file = proj + "/res/layout-port/" + layoutId + ".xml";
        if (!new File(file).exists()) {
          file = proj + "/res/layout-land/" + layoutId + ".xml";
          if (!new File(file).exists()) {
            file = proj + "/res/layout-sw600dp/" + layoutId + ".xml";
            if (!new File(file).exists()) {
              file = proj + "/res/layout-sw720dp/" + layoutId + ".xml";
              if (!new File(file).exists()) {
                file = proj + "/res/layout-finger/" + layoutId + ".xml";
                if (!new File(file).exists()) {
                  file = null;
                }
              }
            }
          }
        }
      }
      if (file != null) {
        return file;
      }
    }
    return null;
  }

  private Pair<String, Integer> parseAndroidId(String txt, boolean isSys) {
    String id = null;
    Integer guiIdObj = null;
    if ("@+android:id/internalEmpty".equals(txt)) {
      id = "internalEmpty";
      guiIdObj = sysRIdMap.get(id);
    } else if (txt.startsWith("@id/android:")) {
      id = txt.substring(12);
      guiIdObj = sysRIdMap.get(id);
    } else if (txt.startsWith("@+id/android:")
        || txt.startsWith("@+android:id/")) { // handle old code
      id = txt.substring(13);
      guiIdObj = sysRIdMap.get(id);
    } else if (txt.startsWith("@+id")) {
      id = txt.substring(5);
      if (isSys) {
        guiIdObj = sysRIdMap.get(id);
      } else {
        guiIdObj = rIdMap.get(id);
      }
    } else if (txt.startsWith("@id/")) {
      id = txt.substring(4);
      if (isSys) {
        guiIdObj = sysRIdMap.get(id);
      } else {
        guiIdObj = rIdMap.get(id);
      }
    } else if (txt.startsWith("@android:id")) {
      id = txt.substring(12);
      guiIdObj = sysRIdMap.get(id);
    } else {
      throw new RuntimeException("[WARNING] Unhandled android:id prefix " + txt);
    }
    return new Pair<String, Integer>(id, guiIdObj);
  }

  // --- END

  // --- read menu*/*.xml
  private void readMenu() {
    readMenu(Configs.project, invRMenuMap, id2View);
    readMenu(Configs.sysProj, invSysRMenuMap, sysId2View);
  }

  private void readMenu(String proj, HashMap<Integer, String> map,
      HashMap<Integer, AndroidView> viewMap) {
    boolean isSys = (map == invSysRMenuMap);
    assert proj.equals(Configs.project) ^ isSys;

    for (Map.Entry<Integer, String> e : map.entrySet()) {
      Integer val = e.getKey();
      String name = e.getValue();
      AndroidView root = new AndroidView();
      viewMap.put(val, root);
      String file = getMenuFilePath(proj, name, isSys);
      if (file == null) {
        System.err.println("Unknown menu " + name + " for " + proj);
        continue;
      }
      root.setOrigin(file);
      if (debug) {
        System.out.println("--- reading " + file);
      }

      readMenu(file, root, isSys);
    }
  }

  private void readMenu(String file, AndroidView root, boolean isSys) {
    Document doc;
    try {
      DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
      doc = dBuilder.parse(file);
    } catch (Exception ex) {
      throw new RuntimeException(ex);
    }

    LinkedList<Pair<Node, AndroidView>> worklist = Lists.newLinkedList();
    worklist.add(new Pair<Node, AndroidView>(doc.getDocumentElement(), root));
    root = null;
    while (!worklist.isEmpty()) {
      Pair<Node, AndroidView> pair = worklist.remove();
      Node node = pair.getO1();
      AndroidView view = pair.getO2();
      NamedNodeMap attrMap = node.getAttributes();
      Node idNode = attrMap.getNamedItem(ID_ATTR);
      int guiId = -1;
      String id = null;
      if (idNode != null) {
        String txt = idNode.getTextContent();
        Pair<String, Integer> p = parseAndroidId(txt, isSys);
        id = p.getO1();
        Integer guiIdObj = p.getO2();
        if (guiIdObj == null) {
          if (debug) {
            System.err.println("[WARNING] unresolved android:id " + id + " in "
                + file);
          }
          guiId = nonRId--; // negative value to indicate it is a unique id but
                            // we don't know its value
          if (isSys) {
            sysRIdMap.put(id, guiId);
            invSysRIdMap.put(guiId, id);
          } else {
            rIdMap.put(id, guiId);
            invRIdMap.put(guiId, id);
          }
        } else {
          guiId = guiIdObj.intValue();
        }
      }

      // FIXME(tony): this is an "approximation"
      String guiName = node.getNodeName();
      if (guiName.equals("menu")) {
        guiName = "android.view.Menu";
      } else if (guiName.equals("item")) {
        guiName = "android.view.MenuItem";
      } else if (guiName.equals("group")) {
        // TODO(tony): we might want to create a special fake class to
        // represent menu groups. But for now, let's simply pretend it's
        // a ViewGroup. Also, print a warning when we do see <group>
        if (Configs.verbose) {
          System.out.println("[TODO] <group> used in " + file);
        }
        guiName = "android.view.ViewGroup";
      } else {
        throw new RuntimeException("Unhandled menu tag " + guiName);
      }
      if (debug) {
        System.out.println(guiName + " (" + guiId + ", " + id + ")");
      }
      String text = readAndroidTextOrTitle(attrMap, TITLE_ATTR);

      view.save(guiId, text, guiName);
      NodeList children = node.getChildNodes();
      for (int i = 0; i < children.getLength(); i++) {
        Node newNode = children.item(i);
        String nodeName = newNode.getNodeName();
        if ("#comment".equals(nodeName)) {
          continue;
        }
        if ("#text".equals(nodeName)) {
          // possible for XML files created on a different operating system
          // than the one our analysis is run on
          continue;
        }
        // FIXME: we assume that every node has attributes, may be wrong
        if (!newNode.hasAttributes()) {
          continue;
        }
        AndroidView newView = new AndroidView();
        newView.setParent(view);
        worklist.add(new Pair<Node, AndroidView>(newNode, newView));
      }
    }
  }

  private String getMenuFilePath(String project, String menuId,
      boolean isSys) {
    ArrayList<String> projectDirs = Lists.newArrayList();
    projectDirs.add(project);
    if (!isSys) {
      projectDirs.addAll(Configs.extLibs);
    }

    for (String proj : projectDirs) {
      String file = proj + "/res/menu/" + menuId + ".xml";
      if (!new File(file).exists()) {
        file = null;
      }
      if (file != null) {
        return file;
      }
    }
    return null;
  }
  // --- END

  // --- read values/*.xml
  private void readStrings() {
    intAndStringValues = Maps.newHashMap();
    rStringAndStringValues = Maps.newHashMap();
    for (String file : getStringXMLFilePaths(Configs.project, false)) {
      readStrings(file, intAndStringValues, rStringAndStringValues, rStringMap);
    }

    for (String file : getStringXMLFilePaths(Configs.sysProj, true)) {
      readStrings(file, sysIntAndStringValues, sysRStringAndStringValues, sysRStringMap);
    }
  }

  final static String SYS_ANDROID_STRING_REF = "@android:string/";
  final static int SYS_ANDROID_STRING_REF_LENGTH =
      SYS_ANDROID_STRING_REF.length();

  final static String ANOTHER_SYS_ANDROID_STRING_REF = "@*android:string/";
  final static int ANOTHER_SYS_ANDROID_STRING_REF_LENGTH =
      ANOTHER_SYS_ANDROID_STRING_REF.length();

  final static String MOSTLY_APP_ANDROID_STRING_REF = "@string/";
  final static int MOSTLY_APP_ANDROID_STRING_REF_LENGTH =
      MOSTLY_APP_ANDROID_STRING_REF.length();

  String convertAndroidTextToString(String androidText) {
    if (androidText.isEmpty()) {
      return null;
    }
    // Is it string ref
    if (androidText.charAt(0) == '@') {
      if (androidText.startsWith(SYS_ANDROID_STRING_REF)) {
        return sysRStringAndStringValues.get(
            androidText.substring(SYS_ANDROID_STRING_REF_LENGTH));
      }
      if (androidText.startsWith(ANOTHER_SYS_ANDROID_STRING_REF)) {
        return sysRStringAndStringValues.get(
            androidText.substring(ANOTHER_SYS_ANDROID_STRING_REF_LENGTH));
      }
      if (androidText.startsWith(MOSTLY_APP_ANDROID_STRING_REF)) {
        String stringName =
            androidText.substring(MOSTLY_APP_ANDROID_STRING_REF_LENGTH);
        String result = rStringAndStringValues.get(stringName);
        if (result == null) {
          result = sysRStringAndStringValues.get(stringName);
        }
        return result;
      }
      // Workaround for a weird case in XBMC
     // if (Configs.benchmarkName.equals("XBMC") && androidText.startsWith("@+id/")) {
     //   return null;
     // }
      System.err.println("[WARNING] Unknown android:text format " + androidText);
      return null;
    } else {
      return androidText;
    }
  }

  String readAndroidTextOrTitle(NamedNodeMap attrMap, String attributeName) {
    Node textNode = attrMap.getNamedItem(attributeName);
    String text = null;
    if (textNode != null) {
      String refOrValue = textNode.getTextContent();
      text = convertAndroidTextToString(refOrValue);
      if (debug) {
        System.out.println("  * `" + refOrValue + "' -> `" + text + "'");
      }
    }
    return text;
  }

  private void readStrings(String file, HashMap<Integer, String> idAndStrings,
      HashMap<String, String> stringFieldAndStrings,
      HashMap<String, Integer> stringFieldAndIds) {
    Document doc;
    try {
      DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
      doc = dBuilder.parse(file);
    } catch (Exception ex) {
      throw new RuntimeException(ex);
    }
    if (Configs.verbose) {
      System.out.println("--- Reading " + file);
    }
    NodeList nodes = doc.getElementsByTagName("string");
    if (nodes == null) {
      return;
    }
    for (int i = 0; i < nodes.getLength(); i++) {
      Node n = nodes.item(i);
      if (!"string".equals(n.getNodeName())) {
        throw new RuntimeException();
      }
      NamedNodeMap attrs = n.getAttributes();
      String stringName = attrs.getNamedItem("name").getTextContent();
      NodeList childNodes = n.getChildNodes();
      String stringValue;
      if (childNodes.getLength() == 0) {
        stringValue = "";
      } else {
        stringValue = eliminateQuotes(childNodes.item(0).getTextContent());
      }
      stringFieldAndStrings.put(stringName, stringValue);

      Integer idValueObj = stringFieldAndIds.get(stringName);
      if (idValueObj == null) {
        if (debug) {
          throw new RuntimeException("Unknown string node " + stringName
              + " defined in " + file);
        }
      } else {
        idAndStrings.put(idValueObj, stringValue);
      }
    }
  }

  private String eliminateQuotes(String s) {
    int len = s.length();
    if (len > 1 && s.charAt(0) == '"' && s.charAt(len - 1) == '"') {
      return s.substring(1, len - 1);
    }
    return s;
  }

  /*
   *  Usually the file name is strings.xml, but it technically can be anything.
   *  For now, let's read strings.xml and strings-*.xml.
   */
  private ArrayList<String> getStringXMLFilePaths(String project, boolean isSys) {
    ArrayList<String> projectDirs = Lists.newArrayList();
    projectDirs.add(project);
    if (!isSys) {
      projectDirs.addAll(Configs.extLibs);
    }
    ArrayList<String> xmlFiles = Lists.newArrayList();
    for (String proj : projectDirs) {
      String valuesDirectoryName = proj + "/res/values/";
      File valuesDirectory = new File(valuesDirectoryName);
      if (!valuesDirectory.exists()) {
        System.out.println(
            "[WARNING] Directory " + valuesDirectory + " does not exist!");
        return null;
      }
      for (String file : valuesDirectory.list()) {
        if (file.equals("strings.xml")
            || (file.startsWith("strings-") && file.endsWith(".xml"))) {
          xmlFiles.add(valuesDirectoryName + file);
        }
      }
    }
    return xmlFiles;
  }
  // --- END

  // === END
}
