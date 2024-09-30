package com.qa.opencart.tests;

import org.junit.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class LoginPageTest extends BaseTest {
	
	@Test
	public void loginPageTitleTest() {

		
		String actTitle = loginpage.getLoginPageTitle();
		Assert.assertEquals(actTitle, AppConstants.LOGIN_PAGE_TITLE);
				
	}
	
	
	@Test
	public void loginPageURLTest() {
		
		String actUrl = loginpage.getLoginPageURL();
		Assert.assertTrue(actUrl.contains(AppConstants.LOGIN_PAGE_FRACTION_URL));
		
	}
	
	@Test
	public void forgotPwdLinkExistTest() {
		
		Assert.assertTrue(loginpage.isForgotPwdLilnkExist());
	}
	
	
	@Test
	public void logoExistsTest() {
		
		Assert.assertTrue(loginpage.isLogoExist());
	}
	
	
	@Test(priority=Integer.MAX_VALUE)
	public void LoginTest() {
		
		accountpage =loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertEquals(accountpage.getAccountsPageTitle(), AppConstants.ACCOUNT_PAGE_TITLE);
	}
	
	

}
