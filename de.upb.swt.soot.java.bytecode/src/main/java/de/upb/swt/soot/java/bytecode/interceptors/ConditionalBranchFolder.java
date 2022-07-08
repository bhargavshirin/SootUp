package de.upb.swt.soot.java.bytecode.interceptors;
/*-
 * #%L
 * Soot - a J*va Optimization Framework
 * %%
 * Copyright (C) 1997-2020 Raja Vallée-Rai, Marcus Nachtigall, Markus Schmidt and others
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 2.1 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 *
 * You should have received a copy of the GNU General Lesser Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-2.1.html>.
 * #L%
 */

import com.google.common.collect.Lists;
import de.upb.swt.soot.core.graph.MutableStmtGraph;
import de.upb.swt.soot.core.graph.StmtGraph;
import de.upb.swt.soot.core.jimple.basic.Value;
import de.upb.swt.soot.core.jimple.common.constant.IntConstant;
import de.upb.swt.soot.core.jimple.common.stmt.JIfStmt;
import de.upb.swt.soot.core.jimple.common.stmt.Stmt;
import de.upb.swt.soot.core.model.Body;
import de.upb.swt.soot.core.transform.BodyInterceptor;
import java.util.*;
import javax.annotation.Nonnull;

/**
 * Statically evaluates the conditional expression of Jimple if statements. If the condition is
 * identically true or false, the Folder replaces the conditional branch statement with an
 * unconditional goto statement
 *
 * @author Marcus Nachtigall
 * @author Markus Schmidt
 */
public class ConditionalBranchFolder implements BodyInterceptor {

  @Override
  public void interceptBody(@Nonnull Body.BodyBuilder builder) {

    final MutableStmtGraph stmtGraph = builder.getStmtGraph();

    for (Stmt stmt : Lists.newArrayList(stmtGraph.nodes())) {
      if (!(stmt instanceof JIfStmt)) {
        continue;
      }

      JIfStmt ifStmt = (JIfStmt) stmt;
      // check for constant-valued conditions
      Value condition = ifStmt.getCondition();
      if (!Evaluator.isConstantValue(condition)) {
        continue;
      }

      condition = Evaluator.getConstantValueOf(condition);

      // TODO: [ms] what about the always false case?
      if (((IntConstant) condition).getValue() == 1) {
        // the evaluated if condition is always true: redirect all predecessors to the successor
        // of this if-statement and prune the "true"-block stmt tree until another branch flows
        // to a Stmt

        final List<Stmt> ifSuccessors = stmtGraph.successors(ifStmt);
        final Stmt fallsThroughStmt = ifSuccessors.get(0);
        final Stmt branchTarget = ifSuccessors.get(1);

        // link previous stmt with always-reached successor of the if-Stmt
        for (Stmt predecessor : stmtGraph.predecessors(ifStmt)) {
          builder.removeFlow(predecessor, ifStmt);
          builder.addFlow(predecessor, fallsThroughStmt);
        }

        // removeFlow calls should be obsolete as of following removeStmt
        builder.removeFlow(ifStmt, fallsThroughStmt);
        builder.removeFlow(ifStmt, branchTarget);

        builder.removeStmt(ifStmt);

        pruneExclusivelyReachableStmts(stmtGraph, branchTarget);
      }
    }
  }

  private void pruneExclusivelyReachableStmts(
      @Nonnull MutableStmtGraph stmtGraph, @Nonnull Stmt fallsThroughStmt) {
    Set<Stmt> visited =
        new HashSet<>(); // TODO: ms: there can be a more efficient solution! this has to work as a
    // fix for now.
    Deque<Stmt> q = new ArrayDeque<>();

    q.addFirst(fallsThroughStmt);
    // stmts we want to remove
    // remove all now unreachable stmts from "true"-block
    while (!q.isEmpty()) {
      Stmt itStmt = q.pollFirst();
      if (itStmt.branches()) {
        // reachable branching stmts that may or may not branch to another reachable stmt is all we
        // are actually interested in
        visited.add(itStmt);
      }
      if (stmtGraph.containsNode(itStmt)) {
        final List<Stmt> predecessors = stmtGraph.predecessors(itStmt);
        if (predecessors.size() <= 1) {
          q.addAll(stmtGraph.successors(itStmt));
        }
      }
    }
    // now iterate again and remove if possible: ie predecessor.size() < 1
    q.addFirst(fallsThroughStmt);
    while (!q.isEmpty()) {
      Stmt itStmt = q.pollFirst();
      if (stmtGraph.containsNode(itStmt)) {
        // hint: predecessor could also be already removed
        if (unreachablePredecessorCount(stmtGraph, itStmt, visited) <= 1) {
          q.addAll(stmtGraph.successors(itStmt));
          stmtGraph.removeNode(itStmt);
        }
      }
    }
  }

  /** reachedStmts contains all reached Stmts from entrypoint which ALSO do branch! */
  private int unreachablePredecessorCount(
      @Nonnull StmtGraph<?> graph, @Nonnull Stmt stmt, @Nonnull Set<Stmt> reachedStmts) {
    final List<Stmt> predecessors = graph.predecessors(stmt);
    final int size = predecessors.size();
    int amount = size;
    for (int i = 1; i < size; i++) {
      Stmt predecessor = predecessors.get(i);
      if ((predecessor.fallsThrough() && graph.successors(predecessor).get(0) == stmt)
          || reachedStmts.contains(predecessor)) {
        amount--;
      }
    }
    return amount;
  }
}
