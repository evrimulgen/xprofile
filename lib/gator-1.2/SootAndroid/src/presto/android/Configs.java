/*
 * Configs.java - part of the GATOR project
 *
 * Copyright (c) 2014, The Ohio State University
 *
 * This file is distributed under the terms described in LICENSE in the
 * root directory.
 */
package presto.android;

import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Set;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

public class Configs {
  public static String benchmarkName;

  // root of the project directory
  public static String project;

  // root directory of Android SDK
  public static String sdkDir;

  public static String apiLevel;
  public static int numericApiLevel;

  public static String sysProj;

  public static String bytecodes;

  public static ArrayList<String> depJars;
  public static ArrayList<String> extLibs;

  // full path to android.jar
  public static String android;

  // jre jars
  public static String jre;

  // xml file describing listeners
  public static String listenerSpecFile;

  // --- boolean flags
  public static boolean verbose = false;

  public static boolean guiAnalysis;

  public static Set<String> debugCodes = Sets.newHashSet();

  public static Set<String> clients = Sets.newHashSet();

  public static boolean withCHA = false;

  public static void processing() {
    bytecodes = project + "/bin/classes";

    depJars = Lists.newArrayList();
    File f = new File(project + "/libs");
    if (f.exists()) {
      File[] files = f.listFiles();
      for (File file : files) {
        String fn = file.getName();
        if (fn.endsWith(".jar")) {
          depJars.add(file.getAbsolutePath());
        }
      }
    }

    Properties prop = new Properties();
    try {
      prop.load(new FileReader(project + "/project.properties"));
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

    // check target
    String tgt = prop.getProperty("target");
    if (tgt.startsWith(GOOGLE_API_PREFIX)) {
      tgt = "android-" + tgt.substring(GOOGLE_API_PREFIX.length());
    }
    if (!apiLevel.equals(tgt)) {
      System.err.println("Specified: " + apiLevel + ", project used: " + tgt);
      System.exit(-1);
    }
    numericApiLevel = Integer.parseInt(apiLevel.substring("android-".length()));
    sysProj = Configs.sdkDir + "/platforms/" + Configs.apiLevel + "/data";
    // read libraries
    int i = 1;
    String lib;
    extLibs = Lists.newArrayList();
    while ((lib = prop.getProperty("android.library.reference." + i)) != null) {
      i++;
      extLibs.add(project + "/" + lib);
    }

    validate();
  }

  final static String GOOGLE_API_PREFIX = "Google Inc.:Google APIs:";

  public static void validate() {
    Class<Configs> cls = Configs.class;
    for (Field f : cls.getFields()) {
      if (f.getType().equals(String.class)) {
        try {
          Object res = f.get(null);
          if (res == null) {
            System.err.println("[Configs] You need to set `Configs."
                + f.getName() + "'");
            System.exit(-1);
          }
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }
  }
}
