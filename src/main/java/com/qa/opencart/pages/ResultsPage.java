package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class ResultsPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By searchHeader = By.cssSelector("div#content h1");
	
	private By results = By.cssSelector("div.product-thumb");
	

	
	public ResultsPage(WebDriver driver) {
		
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
		
		
		
	}
	
	public String getSearchHeader() {
		
		String searchHeaderValue =
				eleUtil.waitForElementVisibility(searchHeader, AppConstants.DEFAULT_SHORT_TIMEOUT).getText();
		
		return searchHeaderValue;
		
		
	}
	
	
	public int getSearchResultsCount() {
		
		int resultCount =eleUtil.waitForElementsVisible(results, AppConstants.DEFAULT_SHORT_TIMEOUT).size();
		
		System.out.println("Search result count " + resultCount);
		
		return resultCount;
		
		
	}
	
	
	public ProductInfoPage selectProduct(String productName) {
		
		eleUtil.doClick(By.linkText(productName));
		
		return new ProductInfoPage(driver);
		
		
		
	}

}
