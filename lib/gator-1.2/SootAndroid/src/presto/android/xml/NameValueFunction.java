/*
 * NameValueFunction.java - part of the GATOR project
 *
 * Copyright (c) 2014, The Ohio State University
 *
 * This file is distributed under the terms described in LICENSE in the
 * root directory.
 */
package presto.android.xml;

import java.util.HashMap;

public abstract class NameValueFunction {
  public void feed(String name, int val) {
  }

  public static NameValueFunction mapInvMap(final HashMap<String, Integer> map,
      final HashMap<Integer, String> invMap) {

    return new NameValueFunction() {
      public void feed(String name, int val) {
        map.put(name, val);
        invMap.put(val, name);
      }
    };
  }
}
