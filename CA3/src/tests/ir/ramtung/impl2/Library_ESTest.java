/*
 * This file was automatically generated by EvoSuite
 * Fri Jun 14 18:05:04 GMT 2024
 */

package ir.ramtung.impl2;

import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.evosuite.runtime.EvoAssertions.verifyException;
import static org.junit.Assert.fail;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true) 
public class Library_ESTest extends Library_ESTest_scaffolding {

  @Test(timeout = 4000)
  public void test0()  throws Throwable  {
      Library library0 = new Library();
      library0.timePass(0);
  }

  @Test(timeout = 4000)
  public void test1()  throws Throwable  {
      Library library0 = new Library();
      library0.addReference("", 0);
  }

  @Test(timeout = 4000)
  public void test2()  throws Throwable  {
      Library library0 = new Library();
      library0.addProfMember("&*Ib{`.P-9|");
  }

  @Test(timeout = 4000)
  public void test3()  throws Throwable  {
      Library library0 = new Library();
      library0.addStudentMember("&*Ib{`.P-9|", "&*Ib{`.P-9|");
      try { 
        library0.addProfMember("&*Ib{`.P-9|");
        fail("Expecting exception: Exception");
      
      } catch(Exception e) {
         //
         // the member has already added
         //
         verifyException("ir.ramtung.impl2.Library", e);
      }
  }
}
