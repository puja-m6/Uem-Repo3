//To make sure that the methods run in a certain order there are 3 ways:
//1.name them alphabetically
//2.assign them priorities in order of execution
//3.use dependsOnMethods.It accepts one or more method names

//invocationCount invokes/executes the method n number of times.You provide the value of n.

//If you don't wish to execute a method use enabled=false
package shopperStack;

import org.testng.annotations.Test;

public class TestNGMethods {
	//@Test(priority = 2)
	@Test
	public void btest1() {
		System.out.println("Test 1");
	}
	//@Test(priority=3)
	@Test(dependsOnMethods = "btest1")
	public void atest2() {
		System.out.println("Test 2");
	}
	//@Test(priority = 1)
	@Test(dependsOnMethods = "atest2",invocationCount = 5,enabled = false)
	public void ctest3() {
		System.out.println("Test 3");
	}
}
