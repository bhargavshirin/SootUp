package de.upb.soot.minimaltestsuite.java6;

import static org.junit.Assert.*;

import categories.Java8Test;
import de.upb.soot.core.Body;
import de.upb.soot.core.SootMethod;
import de.upb.soot.frontends.java.Utils;
import de.upb.soot.frontends.java.WalaClassLoaderTestUtils;
import de.upb.soot.jimple.common.stmt.Stmt;
import de.upb.soot.minimaltestsuite.LoadClassesWithWala;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(Java8Test.class)
public class ForLoopTest{
  private String srcDir = "src/test/resources/minimaltestsuite/java6/";
  private String className = "ForLoop";
  private LoadClassesWithWala loadClassesWithWala = new LoadClassesWithWala();


  @Before
  public void loadClasses() {
    loadClassesWithWala.classLoader(srcDir,className);
  }

  @Test
  public void forLoopTest() {
    Optional<SootMethod> m =
        WalaClassLoaderTestUtils.getSootMethod(
                loadClassesWithWala.loader,
                loadClassesWithWala.identifierFactory.getMethodSignature(
                "forLoop", loadClassesWithWala.declareClassSig, "int", Collections.singletonList("int")));
    assertTrue(m.isPresent());
    SootMethod method = m.get();
    Utils.print(method, false);
    Body body = method.getBody();
    assertNotNull(body);

    List<String> actualStmts =
        body.getStmts().stream()
            .map(Stmt::toString)
            .collect(Collectors.toCollection(ArrayList::new));

    List<String> expectedStmts =
        Stream.of(
                "r0 := @this: ForLoop",
                "$i0 := @parameter0: int",
                "$i1 = 0",
                "$i2 = 0",
                "$z0 = $i2 < $i0",
                "if $z0 == 0 goto return $i1",
                "$i3 = $i1",
                "$i4 = $i1 + 1",
                "$i1 = $i4",
                "$i5 = $i2",
                "$i6 = $i2 + 1",
                "$i2 = $i6",
                "goto [?= $z0 = $i2 < $i0]",
                "return $i1")
            .collect(Collectors.toCollection(ArrayList::new));

    assertEquals(expectedStmts, actualStmts);
  }
}
