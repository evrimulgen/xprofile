/*
 * NAllocNode.java - part of the GATOR project
 *
 * Copyright (c) 2014, The Ohio State University
 *
 * This file is distributed under the terms described in LICENSE in the
 * root directory.
 */
package presto.android.gui.graph;

import soot.RefType;
import soot.SootClass;
import soot.Type;
import soot.jimple.Expr;
import soot.jimple.NewExpr;

public class NAllocNode extends NObjectNode {
  public Expr e;

  @Override
  public SootClass getClassType() {
    if (e instanceof NewExpr) {
      Type type = ((NewExpr)e).getType();
      return ((RefType)type).getSootClass();
    }
    return null;
  }

  @Override
  public String toString() {
    return "NEW[" + e + "]" + id;
  }
}
