/**
 * Scaffolding file used to store all the setups needed to run 
 * tests automatically generated by EvoSuite
 * Fri Jun 14 17:42:26 GMT 2024
 */

package ir.ramtung.impl1;

import org.evosuite.runtime.annotation.EvoSuiteClassExclude;
import org.evosuite.runtime.sandbox.Sandbox;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

@EvoSuiteClassExclude
public class Loan_ESTest_scaffolding {

  @org.junit.Rule
  public org.evosuite.runtime.vnet.NonFunctionalRequirementRule nfr = new org.evosuite.runtime.vnet.NonFunctionalRequirementRule();

  private static final java.util.Properties defaultProperties = (java.util.Properties) java.lang.System.getProperties().clone(); 

  private org.evosuite.runtime.thread.ThreadStopper threadStopper =  new org.evosuite.runtime.thread.ThreadStopper (org.evosuite.runtime.thread.KillSwitchHandler.getInstance(), 3000);


  @BeforeClass
  public static void initEvoSuiteFramework() { 
    org.evosuite.runtime.RuntimeSettings.className = "ir.ramtung.impl1.Loan"; 
    org.evosuite.runtime.GuiSupport.initialize(); 
    org.evosuite.runtime.RuntimeSettings.maxNumberOfThreads = 100; 
    org.evosuite.runtime.RuntimeSettings.maxNumberOfIterationsPerLoop = 10000; 
    org.evosuite.runtime.RuntimeSettings.mockSystemIn = true; 
    org.evosuite.runtime.RuntimeSettings.sandboxMode = org.evosuite.runtime.sandbox.Sandbox.SandboxMode.RECOMMENDED; 
    org.evosuite.runtime.sandbox.Sandbox.initializeSecurityManagerForSUT(); 
    org.evosuite.runtime.classhandling.JDKClassResetter.init();
    setSystemProperties();
    initializeClasses();
    org.evosuite.runtime.Runtime.getInstance().resetRuntime(); 
  } 

  @AfterClass
  public static void clearEvoSuiteFramework(){ 
    Sandbox.resetDefaultSecurityManager(); 
    java.lang.System.setProperties((java.util.Properties) defaultProperties.clone()); 
  } 

  @Before
  public void initTestCase(){ 
    threadStopper.storeCurrentThreads();
    threadStopper.startRecordingTime();
    org.evosuite.runtime.jvm.ShutdownHookHandler.getInstance().initHandler(); 
    org.evosuite.runtime.sandbox.Sandbox.goingToExecuteSUTCode(); 
    setSystemProperties(); 
    org.evosuite.runtime.GuiSupport.setHeadless(); 
    org.evosuite.runtime.Runtime.getInstance().resetRuntime(); 
    org.evosuite.runtime.agent.InstrumentingAgent.activate(); 
  } 

  @After
  public void doneWithTestCase(){ 
    threadStopper.killAndJoinClientThreads();
    org.evosuite.runtime.jvm.ShutdownHookHandler.getInstance().safeExecuteAddedHooks(); 
    org.evosuite.runtime.classhandling.JDKClassResetter.reset(); 
    resetClasses(); 
    org.evosuite.runtime.sandbox.Sandbox.doneWithExecutingSUTCode(); 
    org.evosuite.runtime.agent.InstrumentingAgent.deactivate(); 
    org.evosuite.runtime.GuiSupport.restoreHeadlessMode(); 
  } 

  public static void setSystemProperties() {
 
    java.lang.System.setProperties((java.util.Properties) defaultProperties.clone()); 
    java.lang.System.setProperty("user.dir", "/Users/ali_akhgary/Desktop/Term10.tmp/Advanced Test/CA/Advanced-Test-Course/CA3/src"); 
    java.lang.System.setProperty("java.io.tmpdir", "/var/folders/ll/knvtrnwd0v30yyqkhds7_sgc0000gn/T/"); 
  }

  private static void initializeClasses() {
    org.evosuite.runtime.classhandling.ClassStateSupport.initializeClasses(Loan_ESTest_scaffolding.class.getClassLoader() ,
      "ir.ramtung.impl1.CannotExtendEx",
      "ir.ramtung.impl1.Document",
      "ir.ramtung.impl1.ExcessiveLateDaysException",
      "ir.ramtung.impl1.Magazine",
      "ir.ramtung.impl1.Student",
      "ir.ramtung.impl1.Loan",
      "ir.ramtung.impl1.Reference",
      "ir.ramtung.sts01.LibraryException",
      "ir.ramtung.impl1.Professor",
      "ir.ramtung.impl1.Book",
      "ir.ramtung.impl1.InvalidArgumentEx",
      "ir.ramtung.impl1.Member"
    );
  } 

  private static void resetClasses() {
    org.evosuite.runtime.classhandling.ClassResetter.getInstance().setClassLoader(Loan_ESTest_scaffolding.class.getClassLoader()); 

    org.evosuite.runtime.classhandling.ClassStateSupport.resetClasses(
      "ir.ramtung.impl1.Loan",
      "ir.ramtung.impl1.Document",
      "ir.ramtung.impl1.Magazine",
      "ir.ramtung.sts01.LibraryException",
      "ir.ramtung.impl1.InvalidArgumentEx",
      "ir.ramtung.impl1.Member",
      "ir.ramtung.impl1.Student",
      "ir.ramtung.impl1.Professor",
      "ir.ramtung.impl1.Book",
      "ir.ramtung.impl1.Reference",
      "ir.ramtung.impl1.CannotExtendEx",
      "ir.ramtung.impl1.ExcessiveLateDaysException"
    );
  }
}
