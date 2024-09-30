package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {
	
	private WebDriver driver;
	
	private ElementUtil eleUtil;
	
	private By productHeader = By.tagName("h1");
	
	private By productMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]/li");
	
	private By productPriceData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]/li");
	
	private Map<String , String> productMap;
	
	private By productImage = By.cssSelector("ul.thumbnails img");
	

	public ProductInfoPage(WebDriver driver) {
		
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
		
		
	}
	
	
	public String  getProductHeader() {
		
		String productHeadervalue =eleUtil.waitForElementVisibility(productHeader, AppConstants.DEFAULT_LONG_TIMEOUT).getText();
		
		System.out.println(productHeadervalue);
		return productHeadervalue;
		
	}
	
	
//	// to fetch the data in the form of key,value.
//	// Brand: Apple
//	Product Code: Product 18
//	Reward Points: 800
//	Availability: In Stock
//	
	
	private void getProductMetaData() {
		
		List<WebElement> metaLilst = eleUtil.getElements(productMetaData);
		
		
		
		   for(WebElement meta: metaLilst) {
		    String metaText = meta.getText();
			String metaData[] = metaText.split(":");
			String metaKey = metaData[0].trim();
			String metaValue =metaData[1].trim();
			
			productMap.put(metaKey, metaValue);
			
		}
	}
	
//	
//	$2,000.00
//	Ex Tax: $2,000.00
	
	private void getProductPriceData() {
		
		List<WebElement> priceList = eleUtil.getElements(productPriceData);
		
		String Price =priceList.get(0).getText();
		String exTaxPrice=priceList.get(1).getText().split(":")[1].trim();
		
		productMap.put("ProductPrice", Price);
		productMap.put("extraprice", exTaxPrice);
		
	}
	

    public Map<String,String> getProductData() {
    	
    	productMap = new HashMap<String,String>();	    	
    	productMap.put("productHeader",getProductHeader() );
    	getProductMetaData();
    	getProductPriceData();
    	System.out.println(productMap);
    	return productMap;
    	
    }
	
    
    public int getProductImagesCount() {
    	
  
      int imagescount = eleUtil.waitForElementsPresence(productImage, AppConstants.DEFAULT_MEDIUM_TIMEOUT).size();
       System.out.println(imagescount);
       
       return imagescount;
       
      
    }

}
