/*
 * NDialogNode.java - part of the GATOR project
 *
 * Copyright (c) 2014, The Ohio State University
 *
 * This file is distributed under the terms described in LICENSE in the
 * root directory.
 */
package presto.android.gui.graph;

import soot.SootClass;
import soot.SootMethod;
import soot.jimple.Stmt;

public class NDialogNode extends NWindowNode {

  public Stmt allocStmt;
  public SootMethod allocMethod;

  public NDialogNode(SootClass dialogClass, Stmt allocStmt, SootMethod allocMethod) {
    c = dialogClass;
    this.allocStmt = allocStmt;
    this.allocMethod = allocMethod;
  }

  @Override
  public String toString() {
    return "DIALOG[" + c + "]" + id;
  }
}
