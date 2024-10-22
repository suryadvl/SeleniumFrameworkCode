package com.qa.opencart.tests;

import org.junit.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.listeners.AnnotationTransformer;
import com.qa.opencart.listeners.ExtentReportListener;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;


//@Listeners({AnnotationTransformer.class, ExtentReportListener.class})


@Epic("Epic 100: design open cart login page")
@Feature("Feature 101: login feature")
@Story("US 120: All the features related to open cart login page")
@Owner("Surya")
@Link(name = "LoginPage", url = "https://naveenautomationlabs.com/opencart/index.php?route=account/login")
public class LoginPageTest extends BaseTest {

	@Severity(SeverityLevel.MINOR)
	@Description("Login page title test")
	@Feature("This is login page")
	@Test
	public void loginPageTitleTest() {

		
		String actTitle = loginpage.getLoginPageTitle();
		Assert.assertEquals(actTitle, AppConstants.LOGIN_PAGE_TITLE);
				
	}
	
	@Severity(SeverityLevel.NORMAL)
	@Description("Login page URL test")
	@Feature("This is login URL")
	@Test
	public void loginPageURLTest() {
		
		String actUrl = loginpage.getLoginPageURL();
		Assert.assertTrue(actUrl.contains(AppConstants.LOGIN_PAGE_FRACTION_URL));
		
	}
	
	@Severity(SeverityLevel.CRITICAL)
	@Description("Forgot pwd link")
	@Feature("This is forgot pwd link ")
	@Test
	public void forgotPwdLinkExistTest() {
		
		Assert.assertTrue(loginpage.isForgotPwdLilnkExist());
	}
	
	
	@Severity(SeverityLevel.MINOR)
	@Description("logo page test")
	@Feature("logo test")
	@Test
	public void logoExistsTest() {
		
		Assert.assertTrue(loginpage.isLogoExist());
	}
	
	
	@Severity(SeverityLevel.MINOR)
	@Description("Login  test")
	@Feature("This is login")
	@Test(priority=Integer.MAX_VALUE)
	public void LoginTest() {
		
		accountpage =loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertEquals(accountpage.getAccountsPageTitle(), AppConstants.ACCOUNT_PAGE_TITLE);
	}
	
	

}
