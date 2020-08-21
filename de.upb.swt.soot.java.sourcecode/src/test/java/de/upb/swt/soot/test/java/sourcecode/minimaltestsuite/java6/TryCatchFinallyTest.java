/** @author: Hasitha Rajapakse */
package de.upb.swt.soot.test.java.sourcecode.minimaltestsuite.java6;

import categories.Java8Test;
import de.upb.swt.soot.core.model.SootMethod;
import de.upb.swt.soot.core.signatures.MethodSignature;
import de.upb.swt.soot.test.java.sourcecode.minimaltestsuite.MinimalSourceTestSuiteBase;
import java.util.Collections;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(Java8Test.class)
public class TryCatchFinallyTest extends MinimalSourceTestSuiteBase {

  @Test
  public void tryCatch() {
    SootMethod sootMethod = loadMethod(getMethodSignature("tryCatch"));
    assertJimpleStmts(
        sootMethod,
        expectedBodyStmts(
            "r0 := @this: TryCatchFinally",
            "label1:",
            "$r1 = \"\"",
            "$r1 = \"try\"",
            "$r2 = <java.lang.System: java.io.PrintStream out>",
            "virtualinvoke $r2.<java.io.PrintStream: void println(java.lang.String)>($r1)",
            "label2:",
            "goto label4",
            "label3:",
            "$r3 := @caughtexception",
            "$r4 = $r3",
            "$r1 = \"catch\"",
            "$r5 = <java.lang.System: java.io.PrintStream out>",
            "virtualinvoke $r5.<java.io.PrintStream: void println(java.lang.String)>($r1)",
            "label4:",
            "return",
            "catch java.lang.Exception from label1 to label2 with label3"));
  }

  @Test
  public void tryCatchFinally() {
    SootMethod sootMethod = loadMethod(getMethodSignature("tryCatchFinally"));
    assertJimpleStmts(
        sootMethod,
        expectedBodyStmts(
            "r0 := @this: TryCatchFinally",
            "label1:",
            "$r1 = \"\"",
            "$r1 = \"try\"",
            "$r2 = <java.lang.System: java.io.PrintStream out>",
            "virtualinvoke $r2.<java.io.PrintStream: void println(java.lang.String)>($r1)",
            "label2:",
            "goto label6",
            "label3:",
            "$r3 := @caughtexception",
            "$r4 = $r3",
            "$r1 = \"catch\"",
            "$r5 = <java.lang.System: java.io.PrintStream out>",
            "virtualinvoke $r5.<java.io.PrintStream: void println(java.lang.String)>($r1)",
            "label4:",
            "goto label6",
            "label5:",
            "$r6 := @caughtexception",
            "$r1 = \"finally\"",
            "$r7 = <java.lang.System: java.io.PrintStream out>",
            "virtualinvoke $r7.<java.io.PrintStream: void println(java.lang.String)>($r1)",
            "throw $r6",
            "label6:",
            "$r1 = \"finally\"",
            "$r8 = <java.lang.System: java.io.PrintStream out>",
            "virtualinvoke $r8.<java.io.PrintStream: void println(java.lang.String)>($r1)",
            "return",
            "catch java.lang.Exception from label1 to label2 with label3",
            "catch java.lang.Throwable from label1 to label4 with label5"));
  }

  @Test
  public void tryCatchCombined() {
    SootMethod sootMethod = loadMethod(getMethodSignature("tryCatchCombined"));
    assertJimpleStmts(
        sootMethod,
        expectedBodyStmts(
            "r0 := @this: TryCatchFinally",
            "label1:",
            "$r1 = \"\"",
            "$r1 = \"try\"",
            "$r2 = <java.lang.System: java.io.PrintStream out>",
            "virtualinvoke $r2.<java.io.PrintStream: void println(java.lang.String)>($r1)",
            "label2:",
            "goto label4",
            "label3:",
            "$r3 := @caughtexception",
            "$r4 = $r3",
            "$r1 = \"catch\"",
            "$r5 = <java.lang.System: java.io.PrintStream out>",
            "virtualinvoke $r5.<java.io.PrintStream: void println(java.lang.String)>($r1)",
            "label4:",
            "return",
            "catch java.lang.RuntimeException from label1 to label2 with label3",
            "catch java.lang.StackOverflowError from label1 to label2 with label3"));
  }

  @Test
  public void tryCatchFinallyCombined() {
    SootMethod sootMethod = loadMethod(getMethodSignature("tryCatchFinallyCombined"));
    assertJimpleStmts(
        sootMethod,
        expectedBodyStmts(
            "r0 := @this: TryCatchFinally",
            "label1:",
            "$r1 = \"\"",
            "$r1 = \"try\"",
            "$r2 = <java.lang.System: java.io.PrintStream out>",
            "virtualinvoke $r2.<java.io.PrintStream: void println(java.lang.String)>($r1)",
            "label2:",
            "goto label6",
            "label3:",
            "$r3 := @caughtexception",
            "$r4 = $r3",
            "$r1 = \"catch\"",
            "$r5 = <java.lang.System: java.io.PrintStream out>",
            "virtualinvoke $r5.<java.io.PrintStream: void println(java.lang.String)>($r1)",
            "label4:",
            "goto label6",
            "label5:",
            "$r6 := @caughtexception",
            "$r1 = \"finally\"",
            "$r7 = <java.lang.System: java.io.PrintStream out>",
            "virtualinvoke $r7.<java.io.PrintStream: void println(java.lang.String)>($r1)",
            "throw $r6",
            "label6:",
            "$r1 = \"finally\"",
            "$r8 = <java.lang.System: java.io.PrintStream out>",
            "virtualinvoke $r8.<java.io.PrintStream: void println(java.lang.String)>($r1)",
            "return",
            "catch java.lang.RuntimeException from label1 to label2 with label3",
            "catch java.lang.StackOverflowError from label1 to label2 with label3",
            "catch java.lang.Throwable from label1 to label4 with label5"));
  }

  @Test
  public void tryCatchNested() {
    SootMethod sootMethod = loadMethod(getMethodSignature("tryCatchNested"));
    assertJimpleStmts(
        sootMethod,
        expectedBodyStmts(
            "r0 := @this: TryCatchFinally",
            "$r1 = \"\"",
            "$r1 = \"1try\"",
            "$r2 = <java.lang.System: java.io.PrintStream out>",
            "virtualinvoke $r2.<java.io.PrintStream: void println(java.lang.String)>($r1)",
            "label1:",
            "$r1 = \"2try\"",
            "$r3 = <java.lang.System: java.io.PrintStream out>",
            "virtualinvoke $r3.<java.io.PrintStream: void println(java.lang.String)>($r1)",
            "label2:",
            "goto label4",
            "label3:",
            "$r4 := @caughtexception",
            "$r5 = $r4",
            "$r1 = \"2catch\"",
            "$r6 = <java.lang.System: java.io.PrintStream out>",
            "virtualinvoke $r6.<java.io.PrintStream: void println(java.lang.String)>($r1)",
            "label4:",
            "goto label6",
            "label5:",
            "$r7 := @caughtexception",
            "$r8 = $r7",
            "$r1 = \"1catch\"",
            "$r9 = <java.lang.System: java.io.PrintStream out>",
            "virtualinvoke $r9.<java.io.PrintStream: void println(java.lang.String)>($r1)",
            "label6:",
            "return",
            "catch java.lang.Exception from label1 to label2 with label3",
            "catch java.lang.Exception from label1 to label4 with label5"));
  }

  @Test
  public void tryCatchFinallyNested() {
    SootMethod sootMethod = loadMethod(getMethodSignature("tryCatchFinallyNested"));
    assertJimpleStmts(
        sootMethod,
        expectedBodyStmts(
            "r0 := @this: TryCatchFinally",
            "$r1 = \"\"",
            "$r1 = \"1try\"",
            "$r2 = <java.lang.System: java.io.PrintStream out>",
            "virtualinvoke $r2.<java.io.PrintStream: void println(java.lang.String)>($r1)",
            "label1:",
            "$r1 = \"2try\"",
            "$r3 = <java.lang.System: java.io.PrintStream out>",
            "virtualinvoke $r3.<java.io.PrintStream: void println(java.lang.String)>($r1)",
            "label2:",
            "goto label4",
            "label3:",
            "$r4 := @caughtexception",
            "$r5 = $r4",
            "$r1 = \"2catch\"",
            "$r6 = <java.lang.System: java.io.PrintStream out>",
            "virtualinvoke $r6.<java.io.PrintStream: void println(java.lang.String)>($r1)",
            "label4:",
            "goto label8",
            "label5:",
            "$r7 := @caughtexception",
            "$r8 = $r7",
            "$r1 = \"1catch\"",
            "$r9 = <java.lang.System: java.io.PrintStream out>",
            "virtualinvoke $r9.<java.io.PrintStream: void println(java.lang.String)>($r1)",
            "label6:",
            "goto label8",
            "label7:",
            "$r10 := @caughtexception",
            "$r1 = \"1finally\"",
            "$r11 = <java.lang.System: java.io.PrintStream out>",
            "virtualinvoke $r11.<java.io.PrintStream: void println(java.lang.String)>($r1)",
            "throw $r10",
            "label8:",
            "$r1 = \"1finally\"",
            "$r12 = <java.lang.System: java.io.PrintStream out>",
            "virtualinvoke $r12.<java.io.PrintStream: void println(java.lang.String)>($r1)",
            "return",
            "catch java.lang.Exception from label1 to label2 with label3",
            "catch java.lang.Exception from label1 to label4 with label5",
            "catch java.lang.Throwable from label1 to label6 with label7"));
  }

  @Test
  public void tryCatchNestedInCatch() {
    SootMethod sootMethod = loadMethod(getMethodSignature("tryCatchNestedInCatch"));
    assertJimpleStmts(
        sootMethod,
        expectedBodyStmts(
            "r0 := @this: TryCatchFinally",
            "label1:",
            "$r1 = \"\"",
            "$r1 = \"1try\"",
            "$r2 = <java.lang.System: java.io.PrintStream out>",
            "virtualinvoke $r2.<java.io.PrintStream: void println(java.lang.String)>($r1)",
            "label2:",
            "goto label7",
            "label3:",
            "$r3 := @caughtexception",
            "$r4 = $r3",
            "$r1 = \"1catch\"",
            "$r5 = <java.lang.System: java.io.PrintStream out>",
            "virtualinvoke $r5.<java.io.PrintStream: void println(java.lang.String)>($r1)",
            "label4:",
            "$r1 = \"2try\"",
            "$r6 = <java.lang.System: java.io.PrintStream out>",
            "virtualinvoke $r6.<java.io.PrintStream: void println(java.lang.String)>($r1)",
            "label5:",
            "goto label7",
            "label6:",
            "$r7 := @caughtexception",
            "$r8 = $r7",
            "$r1 = \"2catch\"",
            "$r9 = <java.lang.System: java.io.PrintStream out>",
            "virtualinvoke $r9.<java.io.PrintStream: void println(java.lang.String)>($r1)",
            "label7:",
            "return",
            "catch java.lang.Exception from label1 to label2 with label3",
            "catch java.lang.Exception from label4 to label5 with label6"));
  }

  @Test
  public void tryCatchFinallyNestedInCatch() {
    SootMethod sootMethod = loadMethod(getMethodSignature("tryCatchFinallyNestedInCatch"));
    assertJimpleStmts(
        sootMethod,
        expectedBodyStmts(
            "r0 := @this: TryCatchFinally",
            "label1:",
            "$r1 = \"\"",
            "$r1 = \"1try\"",
            "$r2 = <java.lang.System: java.io.PrintStream out>",
            "virtualinvoke $r2.<java.io.PrintStream: void println(java.lang.String)>($r1)",
            "label2:",
            "goto label7",
            "label3:",
            "$r3 := @caughtexception",
            "$r4 = $r3",
            "$r1 = \"1catch\"",
            "$r5 = <java.lang.System: java.io.PrintStream out>",
            "virtualinvoke $r5.<java.io.PrintStream: void println(java.lang.String)>($r1)",
            "label4:",
            "goto label8",
            "label5:",
            "$r6 := @caughtexception",
            "$r1 = \"1finally\"",
            "$r7 = <java.lang.System: java.io.PrintStream out>",
            "virtualinvoke $r7.<java.io.PrintStream: void println(java.lang.String)>($r1)",
            "throw $r6",
            "label6:",
            "$r9 := @caughtexception",
            "$r10 = $r9",
            "$r1 = \"2catch\"",
            "$r11 = <java.lang.System: java.io.PrintStream out>",
            "virtualinvoke $r11.<java.io.PrintStream: void println(java.lang.String)>($r1)",
            "label7:",
            "$r1 = \"1finally\"",
            "$r12 = <java.lang.System: java.io.PrintStream out>",
            "virtualinvoke $r12.<java.io.PrintStream: void println(java.lang.String)>($r1)",
            "return",
            "label8:",
            "$r1 = \"2try\"",
            "$r8 = <java.lang.System: java.io.PrintStream out>",
            "virtualinvoke $r8.<java.io.PrintStream: void println(java.lang.String)>($r1)",
            "label9:",
            "goto label7",
            "catch java.lang.Exception from label1 to label2 with label3",
            "catch java.lang.Throwable from label1 to label4 with label5",
            "catch java.lang.Exception from label8 to label9 with label6"));
  }

  @Test
  public void tryCatchFinallyNestedInFinally() {
    SootMethod sootMethod = loadMethod(getMethodSignature("tryCatchFinallyNestedInFinally"));
    assertJimpleStmts(
        sootMethod,
        expectedBodyStmts(
            "r0 := @this: TryCatchFinally",
            "label01:",
            "$r1 = \"\"",
            "$r1 = \"1try\"",
            "$r2 = <java.lang.System: java.io.PrintStream out>",
            "virtualinvoke $r2.<java.io.PrintStream: void println(java.lang.String)>($r1)",
            "label02:",
            "goto label12",
            "label03:",
            "$r3 := @caughtexception",
            "$r4 = $r3",
            "$r1 = \"1catch\"",
            "$r5 = <java.lang.System: java.io.PrintStream out>",
            "virtualinvoke $r5.<java.io.PrintStream: void println(java.lang.String)>($r1)",
            "label04:",
            "goto label12",
            "label05:",
            "$r6 := @caughtexception",
            "$r1 = \"1finally\"",
            "$r7 = <java.lang.System: java.io.PrintStream out>",
            "virtualinvoke $r7.<java.io.PrintStream: void println(java.lang.String)>($r1)",
            "label06:",
            "$r1 = \"2try\"",
            "$r8 = <java.lang.System: java.io.PrintStream out>",
            "virtualinvoke $r8.<java.io.PrintStream: void println(java.lang.String)>($r1)",
            "label07:",
            "goto label09",
            "label08:",
            "$r9 := @caughtexception",
            "$r10 = $r9",
            "$r1 = \"2catch\"",
            "$r11 = <java.lang.System: java.io.PrintStream out>",
            "virtualinvoke $r11.<java.io.PrintStream: void println(java.lang.String)>($r1)",
            "label09:",
            "throw $r6",
            "label10:",
            "$r14 := @caughtexception",
            "$r15 = $r14",
            "$r1 = \"2catch\"",
            "$r16 = <java.lang.System: java.io.PrintStream out>",
            "virtualinvoke $r16.<java.io.PrintStream: void println(java.lang.String)>($r1)",
            "label11:",
            "return",
            "label12:",
            "$r1 = \"1finally\"",
            "$r12 = <java.lang.System: java.io.PrintStream out>",
            "virtualinvoke $r12.<java.io.PrintStream: void println(java.lang.String)>($r1)",
            "label13:",
            "$r1 = \"2try\"",
            "$r13 = <java.lang.System: java.io.PrintStream out>",
            "virtualinvoke $r13.<java.io.PrintStream: void println(java.lang.String)>($r1)",
            "label14:",
            "goto label11",
            "catch java.lang.Exception from label01 to label02 with label03",
            "catch java.lang.Throwable from label01 to label04 with label05",
            "catch java.lang.Throwable from label06 to label07 with label08",
            "catch java.lang.Exception from label13 to label14 with label10"));
  }

  public MethodSignature getMethodSignature(String methodName) {
    return identifierFactory.getMethodSignature(
        methodName, getDeclaredClassSignature(), "void", Collections.emptyList());
  }
}
