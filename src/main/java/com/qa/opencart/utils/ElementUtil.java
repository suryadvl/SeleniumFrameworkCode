package com.qa.opencart.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementUtil {

	private WebDriver driver; // given private not to give access to the others without proper way

	private Actions act;

	public ElementUtil(WebDriver driver) {

		this.driver = driver;
		act = new Actions(driver);

	}

	/* Element click method */

	public void doClick(By locator) {

		getElement(locator).click();

	}

	/* GetAttribute method */

	public String doElementGetAttribute(By locator, String value) {

		return getElement(locator).getAttribute(value);

	}

	/* SendKeys Method to pass the text */

	public void doSendKeys(By locator, String value) {

		getElement(locator).sendKeys(value);

	}

	/* SendKeys Method to pass the multiple text */

	public void doSendKeys(By locator, CharSequence... value) {

		getElement(locator).sendKeys(value);

	}

	/* Is Displayed with exception handling */

	public boolean IsElementIsDisplayed(By locator) {

		try {

			return getElement(locator).isDisplayed();
		}

		catch (NoSuchElementException e) {
			System.out.println("Element not displayed :" + locator);
			return false;
		}

	}

	/* getElement Method to get the WebElement */

	public WebElement getElement(By locator) {

		return driver.findElement(locator);

	}

	/* getElement Method to get the WebElements */

	public List<WebElement> getElements(By locator) {

		return driver.findElements(locator);

	}

	/* getElmentcount is use to get size */

	public int getElementCount(By locator) {

		return getElements(locator).size();

	}

	/* IsDisplayed method to handle false case - no such element case */

	public boolean isElementDisplayed(By locator) {
		try {
			return getElement(locator).isDisplayed();
		}

		catch (NoSuchElementException e) {

			System.out.println("Element not displayed :" + locator);
			return false;

		}
	}

	/* IsEnabled method to handle false case - no such element case */

	public boolean isElementEnabled(By locator) {

		try {
			return getElement(locator).isEnabled();
		} catch (NoSuchElementException e) {

			System.out.println("Element is not found" + locator);
			return false;

		}

	}

	public boolean isElementSelected(By locator) {

		try {

			return getElement(locator).isSelected();
		}

		catch (NoSuchElementException e) {

			System.out.println("Element is not found" + locator);
			return false;
		}

	}

	/* getText Method to get the text of the elements */

	public String doGetElementText(By locator) {

		String eleText = getElement(locator).getText();
		if (eleText != null) {

			System.out.println(eleText);
			return eleText;
		}

		else {

			System.out.println("Title cant be empty" + eleText);
			return null;

		}
	}

	/*
	 * this method is used to get Text of the links and store it in strings from
	 * webelements . first we get webelements and add it in arraylist with string
	 * generics
	 */

	public List<String> getElementsTextList(By locator) {

		List<WebElement> eleList = getElements(locator);
		List<String> eleTextList = new ArrayList<String>();

		for (WebElement e : eleList) {

			String eleText = e.getText();
			if (eleText.length() != 0) {

				eleTextList.add(eleText);

			}
		}

		return eleTextList;

	}

	/* to print the links Text */

	public void PrintElementTextList(By locator) {

		List<String> eleTextList = getElementsTextList(locator);

		for (String e : eleTextList) {
			System.out.println(e);

		}
	}

	public boolean isElementNotPresent(By locator) {

		if (getElementCount(locator) == 0) {
			return true;

		}

		return false;

	}

	public boolean isElementPresent(By locator, int exp) {

		if (getElementCount(locator) == exp) {
			return true;

		}

		return false;

	}

	public boolean isElementPresentMultipleTimes(By locator) {

		if (getElementCount(locator) >= 1) {
			return true;

		}

		return false;

	}

	public boolean isElementPresent(By locator) {

		if (getElementCount(locator) == 1) {
			return true;

		}

		return false;

	}

	/********************* DropDown Utility Methods ******************/

	public Select getSelect(By locator) {

		return new Select(getElement(locator));

	}

	public int getDropDownListCount(By locator) {

//		Select select = new Select(getElement(locator));
//		return select.getOptions().size();

		return getSelect(locator).getOptions().size();

	}

	public void SelectDropDownListByVisibleText(By locator, String VisibleText) {

		// Select select = new Select(getElement(locator));

		// select.selectByVisibleText(VisibleText);

		getSelect(locator).selectByVisibleText(VisibleText);

	}

	public void SelectDropDownListByIndex(By locator, int indexValue) {

//		Select select = new Select(getElement(locator));
//
//		select.selectByIndex(indexValue);

		getSelect(locator).selectByIndex(indexValue);

	}

	public void SelectDropDownListByIndex(By locator, String value) {

//		Select select = new Select(getElement(locator));
//
//		select.selectByValue(value);

		getSelect(locator).selectByValue(value);

	}

	public List<String> getDropDownOptionsTextList(By locator) {

		Select select = new Select(getElement(locator));
		List<WebElement> optionsList = select.getOptions();

		System.out.println("Total number of Countries " + optionsList.size());

		List<String> optionsTextList = new ArrayList<String>();

		for (WebElement e : optionsList) {

			String text = e.getText();
			optionsTextList.add(text);

		}

		return optionsTextList;

	}

	/* Select DropDpown value from drop down , using the select class */

	public void selectDropDownValueUsingSelect(By locator, String value) {

		Select select = new Select(getElement(locator));
		List<WebElement> optionsList = select.getOptions();
		selectDropDownList(optionsList, value);

//			System.out.println("Total number of Countries " +optionsList.size());	
//			
//								
//			for(WebElement e : optionsList) {
//				
//				String text = e.getText();
//				 if(text.equals(value)) {
//					  
//					  e.click(); 
//					  break;
//				  }
//				
//				 
//				 
//			}
//			
//			

	}

	/* Select DropDpown value from drop down with out using the select class */

	public void selectDropDownValue(By locator, String value) {

		List<WebElement> optionsList = getElements(locator);
		selectDropDownList(optionsList, value);

//		 System.out.println("Total number of Countries " + optionsList.size());
//		 		
//		  for(WebElement e : optionsList) {
//			
//			String text = e.getText();
//			  System.out.println(text);
//				  
//			  if(text.equals(value)) {
//				  
//				  e.click(); 
//				  break;
//			  }
//			 
//		  }

	}

	/* private given not to give acess to public , encapsulation */

	private void selectDropDownList(List<WebElement> optionsList, String value) {

		System.out.println("Total number of Countries " + optionsList.size());

		for (WebElement e : optionsList) {

			String text = e.getText();
			System.out.println(text);

			if (text.equals(value)) {

				e.click();
				break;
			}

		}

	}

//********Actions Utils******//

	public void doActionsClick(By locator) {

		// Actions act = new Actions(driver);
		act.click(getElement(locator)).perform();

	}

	public void doActionSendKeys(By locator, String value) {

		// Actions act = new Actions(driver);
		act.sendKeys(getElement(locator), value).perform();
		act.click(driver.findElement(By.name("agree"))).perform();

	}

	public void doSendKeysWithPause(By locator, String value, long pausetime) {

		// Actions act = new Actions(driver);

		char ch[] = value.toCharArray();
		for (char c : ch) {
			act.sendKeys(getElement(locator), String.valueOf(c)).pause(pausetime).perform();
		}

	}

	/**
	 * This Method is handling two level of parent and child menu on the basis of By
	 * locator ActionsMOoveToElementclass method
	 * 
	 * @param parentMenu
	 * @param childMenu
	 * @throws InterruptedException
	 */

	public void parentChildMenu(By parentMenu, By childMenu) throws InterruptedException {

		// Actions action = new Actions(driver);we given at class level

		act.moveToElement(getElement(parentMenu)).perform();
		Thread.sleep(5000);

		// getElement(childMenu).click();
		doClick(childMenu);

	}

	public void parentChildMenu(String parentMenu, String childMenu) throws InterruptedException {

		// Actions action = new Actions(driver); we given at class level

		act.moveToElement(getElement(By.xpath("//div[text()='" + parentMenu + "']"))).perform();
		Thread.sleep(5000);

		// getElement(By.xpath("//div[text()='"+childMenu+"']")).click();
		doClick(By.xpath("//div[text()='" + childMenu + "']"));

	}

	/**
	 * This Method is handling four level of parent and child menu on the basis of
	 * By locator BigBasketMultiManu Handling class method
	 * 
	 * @param parentMenu
	 * @param childMenu
	 * @throws InterruptedException
	 */

	public void parentChildMenu(By level1, By level2, By level3, By level4) throws InterruptedException {

		// getElement(level1).click();
		doClick(level1);
		Thread.sleep(2000);
		// Actions act = new Actions(driver);we given at class level
		act.moveToElement(getElement(level2)).build().perform();
		Thread.sleep(2000);
		act.moveToElement(getElement(level3)).build().perform();
		Thread.sleep(2000);
		// getElement(level4).click();
		doClick(level4);

	}

	// *******Wait Util Methods ************

	/**
	 * An expectation for checking that an element is present on the DOM of a page.
	 * This does not necessarily mean that the element is visible on the page.
	 * 
	 **/

	public WebElement waitForElementPresence(By locator, int timeOut) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));

	}

	/**
	 * An expectation for checking that an element is present on the DOM of a page
	 * and visible as well. Visibility means that the element is not only displayed
	 * but also has a height and width that is greater then 0 default interval time
	 * or polling time is 500ms
	 * 
	 **/

	public WebElement waitForElementVisibility(By locator, int timeOut) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

	}

	public WebElement waitForElementVisibility(By locator, int timeOut, int internalTime) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut), Duration.ofSeconds(internalTime));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

	}
	
	
	
	

	public void waitForElementAndClick(By locator, int timeout) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.elementToBeClickable(locator)).click();

	}

	/**
	 * visibility of all elements located in page An expectation for checking that
	 * all elements present on the web page that match the locatorare visible.
	 * Visibility means that the elements are not only displayed but also have a
	 * heightand width that is greater than 0.
	 */

	public List<WebElement> waitForElementsVisible(By locator, int timeOut) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));

		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));

	}

	public List<WebElement> waitForElementsPresence(By locator, int timeOut) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));

		return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));

	}
	
	

	public String getPageTitleIs(String expectedTitle, int timeOut) {

		if (waitForTitleIs(expectedTitle, timeOut)) {

			return driver.getTitle();
		}

		else {

			return "-1";
		}

	}

	public String getPageTitleContains(String partOfTitle, int timeOut) {

		if (waitForTitleContains(partOfTitle, timeOut)) {

			return driver.getTitle();
		}

		else {

			return "-1";
		}

	}

	public boolean waitForTitleIs(String expectedTitle, int timeOut) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		boolean flag = false;
		try {
			flag = wait.until(ExpectedConditions.titleIs(expectedTitle));

			flag = true;
			return flag;
		} catch (TimeoutException e) {

			System.out.println("title is not matched");
			return flag;

		}

	}

	public boolean waitForTitleContains(String partOfTitle, int timeOut) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		boolean flag = false;
		try {
			flag = wait.until(ExpectedConditions.titleContains(partOfTitle));

			flag = true;
			return flag;
		} catch (TimeoutException e) {

			System.out.println("title is not matched");
			return flag;

		}

	}
	
	public String waitForTitleContainsAndReturn(String fractionTitle, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		try {
			wait.until(ExpectedConditions.titleContains(fractionTitle));
			return driver.getTitle();
		} catch (TimeoutException e) {
			System.out.println("title is not matched");
			return "-1";
		}
	}
	
	
	public String getPageURLContains(String fractionURL, int timeOut) {

		if (waitForURLContains(fractionURL, timeOut)) {

			return driver.getCurrentUrl();
		}

		else {

			return "-1";
		}

	}
	
	
	
	public boolean waitForURLContains(String fractionURL ,int timeOut) {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		boolean flag = false;
		try {
			flag = wait.until(ExpectedConditions.urlContains(fractionURL));

			flag = true;
			return flag;
		} catch (TimeoutException e) {

			System.out.println("URL is not matched");
			return flag;

		}
		
	}
	
	public String waitForURLContainsAndReturn(String fractionURL, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		try {
			wait.until(ExpectedConditions.urlContains(fractionURL));// true
			
			return driver.getCurrentUrl();
		} catch (TimeoutException e) {
			System.out.println("URL is not matched");
			return "-1";
		}

	}
	
	
	
	
	
	
	//******************* Wait for Alerts **************************
	
	public  Alert waitForAlertAndSwitch(int timeOut) {
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOut));
		
		 return wait.until(ExpectedConditions.alertIsPresent());  // alert is checked and move to alert.
		
		
		
	}
	
	public  String getAlertText(int timeOut) {
		
		return waitForAlertAndSwitch(timeOut).getText();
		
	}
	
	public  void acceptAlert(int timeOut) {
		
		waitForAlertAndSwitch(timeOut).accept();
		
	}

	
	public  void dismissAlert(int timeOut) {
		
		waitForAlertAndSwitch(timeOut).dismiss();
		
	}

   
	public  void enterValueonAlert(int timeOut,String value) {
		
		waitForAlertAndSwitch(timeOut).sendKeys("value");
		
	}
	
	
  //*****************wait for Frame **************************
	
	public void waitForFrameUsingLocatorAndSwitchToit(int timeOut , By frameLocator) {
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
		
	}
	
	
	public void waitForFrameUsingLocatorAndSwitchToit(int timeOut , int frameIndex) {
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameIndex));
		
	}
	
	
	public void waitForFrameUsingLocatorAndSwitchToit(int timeOut , WebElement frameElement) {
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameElement));
		
	}

	public void waitForFrameUsingLocatorAndSwitchToit(int timeOut , String idOrName) {
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(idOrName));
		
	}
	
	
	//*******************wait for Window ****************
	
//	public boolean waitForNewWindowOrtab(int timeOut , int expectedNumberOfWindows) {
//		
//		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOut));
//		try {
//			
//			if(wait.until(ExpectedConditions.numberOfWindowsToBe(expectedNumberOfWindows)){
//					return true; }
//			
//		
//		
//		catch(TimeOutException e) {
//			System.out.println("number of windows arew not matche");
//			
//		}
//		
//		return false;
//		
//	}}
		
		
		
		
	

}
