package com.qa.opencart.tests;

import org.junit.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class RegistrationPageTest extends BaseTest{
	
	
	@BeforeClass
	public void regSetup() {
		
		registerpage = loginpage.navigateToRegisterPage();
	}
	
	
	public String getRandomEmail() {
		return "uiautomation"+System.currentTimeMillis()+"@open.com";
		
		
	}
	
	
//	@DataProvider
//	public Object[][] getRegistrationData() {
//		
//		return new Object[][] {
//		
//			{"name","Surya"},
//			{"last","danda"},
//			{"Mobile","9959715665"},
//			{"Pwd","Test1234"},
//			{"subscriber","Yes"}
//			
//		};
//		
//	}
	
//	@Test(dataProvider ="getRegistrationData")
//	public void userRegisterTest(String name ,String last, String Mobile , String Pwd,String subscriber) {
//		
//		Assert.assertTrue(registerpage.userRegisteration( name, last, getRandomEmail(), Mobile, Pwd, subscriber));
//		
//		
//		
//		
//	}
	
	
	@Test
	public void userRegisterTest() {
		Assert.assertTrue(registerpage.userRegisteration("Surya","danda",getRandomEmail(),"9900000000","Test1234","Yes"));
		
	}

	
}
