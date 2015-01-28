/*
 * Main.java - part of the GATOR project
 *
 * Copyright (c) 2014, The Ohio State University
 *
 * This file is distributed under the terms described in LICENSE in the
 * root directory.
 */
package presto.android;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.List;
import java.util.Map;

import soot.Pack;
import soot.PackManager;
import soot.SceneTransformer;
import soot.Transform;

/**
 * The main class for SootAndroid.
 */
public class Main {

  public static void main(String[] args) {
    parseArgs(args);
    checkAndPrintEnvironmentInformation(args);
    setupAndInvokeSoot();
  }

  /**
   * Parse the command line arguments for flag values. We, intentionally, do not
   * use any flag library so that everything is explicit and clear.
   *
   * @param args the command line arguments passed from main().
   */
  public static void parseArgs(String[] args) {
    for (int i = 0; i < args.length; i++) {
      String s = args[i];
      if ("-project".equals(s)) {
        Configs.project = args[++i];
      } else if ("-benchmarkName".equals(s)) {
        Configs.benchmarkName = args[++i];
      } else if ("-sdkDir".equals(s)) {
        Configs.sdkDir = args[++i];
      } else if ("-apiLevel".equals(s)) {
        Configs.apiLevel = args[++i];
      } else if ("-android".equals(s)) {
        Configs.android = args[++i];
      } else if ("-jre".equals(s)) {
        Configs.jre = args[++i];
      } else if ("-guiAnalysis".equals(s)) {
        Configs.guiAnalysis = true;
      } else if ("-verbose".equals(s)) {
        Configs.verbose = true;
      } else if ("-debugCode".equals(s)) {
        Configs.debugCodes.add(args[++i]);
      } else if ("-client".equals(s)) {
        Configs.clients.add(args[++i]);
      } else if ("-withCHA".equals(s)) {
        Configs.withCHA = true;
      } else if ("-listenerSpecFile".equals(s)) {
        Configs.listenerSpecFile = args[++i];
      } else {
        throw new RuntimeException("Unknown option: " + s);
      }
    }
    Configs.processing();
  }

  /**
   * Print out information about execution environment.
   *
   * @param args
   */
  static void checkAndPrintEnvironmentInformation(String[] args) {
    // Now, we only support *nix systems. SootAndroid has been tested on Ubuntu
    // and Mac OS X.
    String OS = System.getProperty("os.name").toLowerCase();
    if (OS.indexOf("win") >= 0) {
      throw new RuntimeException("Windows detected!");
    }

    // Print out some information about the execution commands.
    if (Configs.verbose) {
      // The complete Java command
      String cmd = System.getProperty("sun.java.command");
      System.out.println("\n[command] " + cmd);

      // The VM arguments
      RuntimeMXBean RuntimemxBean = ManagementFactory.getRuntimeMXBean();
      List<String> arguments = RuntimemxBean.getInputArguments();
      for (String a : arguments) {
        System.out.print("  [VM-Arg] " + a);
      }
      System.out.println("\n");

      // The arguments after main class is specified
      for (String s : args) {
        System.out.println("  [MAIN-ARG] " + s);
      }
      System.out.println("\n");
    }
  }

  /**
   * Computes the classpath to be used by soot.
   */
  static String computeClasspath() {
    // Compute classpath
    StringBuffer classpathBuffer =
        new StringBuffer(Configs.android + ":" + Configs.jre);
    for (String s : Configs.depJars) {
      classpathBuffer.append(":" + s);
    }

    // TODO(tony): add jar files of third-party libraries if necessary
    for (String s : Configs.extLibs) {
      classpathBuffer.append(":" + s + "/bin/classes");
    }

    return classpathBuffer.toString();
  }

  /**
   * Invoke soot.Main.main() with proper arguments.
   */
  static void setupAndInvokeSoot() {
    String classpath = computeClasspath();
    // Setup an artificial phase to call into our analysis entrypoint. We can
    // run it with or without call graph construction (CHA is chosen here).
    if (Configs.withCHA) {
      String packName = "wjtp";
      String phaseName = "wjtp.gui";
      String[] sootArgs = {
          "-w",
          "-p", "cg", "all-reachable:true",
          "-p", "cg.cha", "enabled:true",
          "-p", phaseName, "enabled:true",
          "-f", "n",
          "-keep-line-number",
          "-allow-phantom-refs",
          "-process-dir", Configs.bytecodes,
          "-cp", classpath,
      };
      setupAndInvokeSootHelper(packName, phaseName, sootArgs);
    } else {
      String packName = "cg";
      String phaseName = "cg.gui";
      String[] sootArgs = {
          "-w",
          "-p", phaseName, "enabled:true",
          "-f", "n",
          "-keep-line-number",
          "-allow-phantom-refs",
          "-process-dir", Configs.bytecodes,
          "-cp", classpath,
      };
      setupAndInvokeSootHelper(packName, phaseName, sootArgs);
    }
  }

  /**
   * Prepare a soot plugin that calls into our analysis entrypoint, and then
   * invoke soot with the plugin enabled.
   */
  static void setupAndInvokeSootHelper(String packName, String phaseName,
      String[] sootArgs) {
    // Create the phase and add it to the pack
    Pack pack = PackManager.v().getPack(packName);
    pack.add(new Transform(phaseName, new SceneTransformer() {
      @Override
      protected void internalTransform(String phaseName,
          Map<String, String> options) {
        AnalysisEntrypoint.v().run();
      }
    }));

    // Print out arguments to Soot for debugging
    if (Configs.verbose) {
      for (String s : sootArgs) {
        System.out.println("  [SOOT-ARG] " + s);
      }
    }

    // Finally, invoke Soot
    soot.Main.main(sootArgs);
  }
}
