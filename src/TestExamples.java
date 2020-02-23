/**
 * These are unit tests used to test our code. The tests are located in the 'tests'
 * folder. In each test, we create a new 'BusinessCardParser' object and then 
 * call the 'getContactInfo' method that will parse the text document and return 
 * the name, email, and phone number on the card. 
 */

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestExamples {

	@Test
	void test1() {
		// Test Example 1
		BusinessCardParser bcp = new BusinessCardParser();
		try {
			ContactInfo ci = bcp.getContactInfo("src/tests/Example1"); 

			assertEquals("Mike Smith", ci.getName());
			assertEquals("4105551234", ci.getPhoneNumber());
			assertEquals("msmith@asymmetrik.com", ci.getEmailAddress());

		} catch (Exception e) {
			System.out.print(e);
		}
	}

	@Test
	void test2() {
		// Test Example 2
		BusinessCardParser bcp = new BusinessCardParser();
		try {
			ContactInfo ci = bcp.getContactInfo("src/tests/Example2"); 

			assertEquals("Lisa Haung", ci.getName());
			assertEquals("4105551234", ci.getPhoneNumber());
			assertEquals("lisa.haung@foobartech.com", ci.getEmailAddress());

		} catch (Exception e) {
			System.out.print(e);
		}
	}

	@Test
	void test3() {
		// Test Example 3
		BusinessCardParser bcp = new BusinessCardParser();
		try {
			ContactInfo ci = bcp.getContactInfo("src/tests/Example3"); 

			assertEquals("Arthur Wilson", ci.getName());
			assertEquals("17035551259", ci.getPhoneNumber());
			assertEquals("awilson@abctech.com", ci.getEmailAddress());


		} catch (Exception e) {
			System.out.print(e);
		}
	}

	@Test
	void mytest1() {
		// This test tests if the algorithm parses the phone number correctly 
		// even when there are no spaces between the numbers.
		BusinessCardParser bcp = new BusinessCardParser();
		try {
			ContactInfo ci = bcp.getContactInfo("src/tests/MyTest1"); 

			assertEquals("John Kim", ci.getName());
			assertEquals("8888889999", ci.getPhoneNumber());
			assertEquals("jk156@gmail.com", ci.getEmailAddress());


		} catch (Exception e) {
			System.out.print(e);
		}
	}

	@Test
	void mytest2() {
		// This test tests if the algorithm chooses the correct name even though
		// there is a name in the title of the company. 
		BusinessCardParser bcp = new BusinessCardParser();
		try {
			ContactInfo ci = bcp.getContactInfo("src/tests/MyTest2"); 

			assertEquals("John Kim", ci.getName());
			assertEquals("18888889999", ci.getPhoneNumber());
			assertEquals("jk156@gmail.com", ci.getEmailAddress());


		} catch (Exception e) {
			System.out.print(e);
		}
	}

}
