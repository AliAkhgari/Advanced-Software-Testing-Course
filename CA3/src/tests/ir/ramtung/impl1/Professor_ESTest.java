/*
 * This file was automatically generated by EvoSuite
 * Fri Jun 14 17:44:18 GMT 2024
 */

package ir.ramtung.impl1;

import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true) 
public class Professor_ESTest extends Professor_ESTest_scaffolding {

  @Test(timeout = 4000)
  public void test0()  throws Throwable  {
      Professor professor0 = new Professor(";oR");
      int int0 = professor0.allowedToBorrow();
      Assert.assertEquals(5, int0);
  }
}
