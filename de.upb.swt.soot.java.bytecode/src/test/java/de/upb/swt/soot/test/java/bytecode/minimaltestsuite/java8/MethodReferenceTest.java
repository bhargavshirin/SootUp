package de.upb.swt.soot.test.java.bytecode.minimaltestsuite.java8;

import categories.Java8Test;
import de.upb.swt.soot.core.model.SootMethod;
import de.upb.swt.soot.core.signatures.MethodSignature;
import de.upb.swt.soot.test.java.bytecode.minimaltestsuite.MinimalBytecodeTestSuiteBase;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.Test;
import org.junit.experimental.categories.Category;

/** @author Kaustubh Kelkar */
@Category(Java8Test.class)
public class MethodReferenceTest extends MinimalBytecodeTestSuiteBase {

  @Override
  public MethodSignature getMethodSignature() {
    return identifierFactory.getMethodSignature(
        "methodRefMethod", getDeclaredClassSignature(), "void", Collections.emptyList());
  }

  /** TODO Update the source code when WALA supports lambda expression */

  /**  <pre>
   * public void methodRefMethod(){
   * System.out.println("Instance Method");
   * MethodReference obj1 = new MethodReference();
   * //Uncomment when WALA supports lambda expression MyInterface ref1 = obj1::methodRefMethod;
   * //ref1.display();
   *
   * }
   *
   * <pre>*/
  @Override
  public List<String> expectedBodyStmts() {
    return Stream.of(
            "l0 := @this: MethodReference",
            "$stack1 = <java.lang.System: java.io.PrintStream out>",
            "virtualinvoke $stack1.<java.io.PrintStream: void println(java.lang.String)>(\"Instance Method\")",
            "return")
        .collect(Collectors.toCollection(ArrayList::new));
  }

  @Test
  public void test() {
    SootMethod method = loadMethod(getMethodSignature());
    assertJimpleStmts(method, expectedBodyStmts());
  }
}
