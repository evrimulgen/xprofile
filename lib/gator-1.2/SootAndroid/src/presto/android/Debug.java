/*
 * Debug.java - part of the GATOR project
 *
 * Copyright (c) 2014, The Ohio State University
 *
 * This file is distributed under the terms described in LICENSE in the
 * root directory.
 */
package presto.android;

import java.io.File;
import java.io.PrintWriter;

public class Debug {
  // codes
  public static final String OP_NODE_DEBUG = "veZ9vagE";
  public static final String VERBOSE_OUTPUT_PRINTS = "wUpud2a3";
  public static final String VERBOSE_WARNING = "praw7EpR";
  public static final String TYPE_FILTER_DEBUG = "vu7eSWu6";
  public static final String WORKLIST_DEBUG = "P4ewEteq";
  public static final String LISTENER_DEBUG = "3RUsastA";
  public static final String CALLGRAPH_DEBUG = "humut2uC";

  public static final String MENU_DEBUG = "menu_debug";

  public static final String LIST_ADAPTER_DEBUG = "list_adapter_debug";

  public static final String EXP_MENU_PTS = "expMenuPts";

  public static final String DEBUG_FILE_ENV_VAR = "SootAndroidDebugFile";

  private String debugFileName;

  private File debugFile;

  private final PrintWriter out;

//  private ExecutorService executor = Executors.newFixedThreadPool(1);

  private static Debug theInstance;

  private Debug() throws Exception {
    debugFileName = System.getenv(DEBUG_FILE_ENV_VAR);
    if (debugFileName == null) {
      debugFile = File.createTempFile(Configs.benchmarkName + "-DEBUG-", ".txt");
      debugFileName = debugFile.getAbsolutePath();
    } else {
      debugFile = new File(debugFileName);
    }
    out = new PrintWriter(debugFile);

    // Shutdown hook
    Runtime.getRuntime().addShutdownHook(new Thread() {
      @Override
      public void run() {
        if (out != null) {
          out.flush();
          out.close();
        }
        System.out.println("\033[1;31m[DEBUG] Debug file at `"
            + debugFileName + "'.\033[0m");
      }
    });
  }

  public static Debug v() {
    if (theInstance == null) {
      try {
        theInstance = new Debug();
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    }
    return theInstance;
  }

  // Put this in another thread if calling it frequently hurts performance.
  public void printf(String format, Object...args) {
    out.printf(format, args);
  }

  public static void setConditionalBreakpoint(boolean condition) {
    if (condition) {
      ; // set an unconditional breakpoint here
    }
  }
}
