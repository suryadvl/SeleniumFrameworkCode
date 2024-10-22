package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	// 1. Private By locator (page elements)

	// 2. public page constructor

	// 3. public page methods or actions or features.

	private By username = By.id("input-email");
	private By password = By.id("input-password");
	private By loginBtn = By.xpath("//input[@value='Login']");
	private By forgotpasswordLink = By.linkText("Forgotten Password");
	private By logo = By.cssSelector("img.img-responsive");
	private By registerLink = By.linkText("Register");
	

	
	public LoginPage(WebDriver driver) {

		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	@Step("getting loginpage title value")
	public String getLoginPageTitle() {

		String title = eleUtil.waitForTitleContainsAndReturn(AppConstants.LOGIN_PAGE_TITLE,
				AppConstants.DEFAULT_SHORT_TIMEOUT);
		// String title = driver.getTitle();

		System.out.println("Login Page title :" + title);
		return title;

	}

	@Step("getting loginpage URL value")
	public String getLoginPageURL() {

		String url = eleUtil.waitForURLContainsAndReturn(AppConstants.LOGIN_PAGE_FRACTION_URL,
				AppConstants.DEFAULT_SHORT_TIMEOUT);
		// String url = driver.getCurrentUrl();

		System.out.println("Login Page title :" + url);
		return url;

	}

	@Step("getting forgotpwdlinkExist value")
	public boolean isForgotPwdLilnkExist() {

		return eleUtil.isElementDisplayed(forgotpasswordLink);

		// return driver.findElement(forgotpasswordLink).isDisplayed();

	}

	@Step("IsLogo Exists")
	public boolean isLogoExist() {

		return eleUtil.isElementDisplayed(logo);

		// return driver.findElement(logo).isDisplayed();
	}

	@Step("login with username : {0} and password : {1}")
	public AccountsPage doLogin(String userName, String pwd) {

		eleUtil.waitForElementVisibility(username, AppConstants.DEFAULT_MEDIUM_TIMEOUT).sendKeys(userName);

		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginBtn);

		// driver.findElement(username).sendKeys(userName);
		// driver.findElement(password).sendKeys(pwd);
		// driver.findElement(loginBtn).click();

		// String accPageTitle=
		// eleUtil.waitForTitleContainsAndReturn(AppConstants.ACCOUNT_PAGE_TITLE,
		// AppConstants.DEFAULT_SHORT_TIMEOUT);
		// String accPageTitle = driver.getTitle();
		// System.out.println("Acc Page Title is to know "+ accPageTitle);
		// return accPageTitle;

		return new AccountsPage(driver);

	}

	
	@Step("Navigate to register page")
	public RegistrationPage navigateToRegisterPage() {
		
		eleUtil.doClick(registerLink);
		return new RegistrationPage(driver);
		
	}
}
