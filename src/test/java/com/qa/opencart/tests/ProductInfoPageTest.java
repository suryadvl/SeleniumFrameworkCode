package com.qa.opencart.tests;

import java.util.Map;

import org.junit.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class ProductInfoPageTest extends BaseTest{
	
	@BeforeClass
	public void productInfoSetup() {
		
		accountpage=loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	    		
		
	}

	
	@Test
	public void productHeaderTest() {
		resultPage = accountpage.doSearch("macbook");
		
		productInfoPage = resultPage.selectProduct("MacBook Pro");
		
		Assert.assertEquals(productInfoPage.getProductHeader(), "MacBook Pro");
		
		
	}
	
	
	
//	// to fetch the data in the form of key,value.
//	// Brand: Apple
//	Product Code: Product 18
//	Reward Points: 800
//	Availability: In Stock	$2,000.00
//	Ex Tax: $2,000.00

	
	@Test
	public void productInfoTest() {
		
		resultPage = accountpage.doSearch("macbook");
		
		productInfoPage = resultPage.selectProduct("MacBook Pro");
		
		Map<String ,String> actualproductData = productInfoPage.getProductData();
		
		softAssert.assertEquals(actualproductData.get("Brand"), "Apple");
		softAssert.assertEquals(actualproductData.get("Product Code"), "Product 18");
		softAssert.assertEquals(actualproductData.get("Reward Points"), "800");
		softAssert.assertEquals(actualproductData.get("Availability"), "In Stock");
		softAssert.assertEquals(actualproductData.get("ProductPrice"), "$2,000.00");
		softAssert.assertEquals(actualproductData.get("extraprice"), "$2,000.00");
		
		softAssert.assertAll();
		
		
		
	}
	
	@DataProvider
	public Object[][] getProductImagesCountData() {
		
		return new Object[][] {
			
			{"macbook","MacBook Pro" ,4},
			{"imac","iMac" ,3},
			
			{"samsung","Samsung SyncMaster 941BW" ,1},
			
			{"samsung","Samsung Galaxy Tab 10.1" ,7},
			
			{"canon","Canon EOS 5D" ,3},
			
		};
		
		
	}
	
	
	@Test(dataProvider = "getProductImagesCountData")
	public void productImagesCount(String search , String ProductName , int imagesCount) {
		
		resultPage = accountpage.doSearch(search);
		
		productInfoPage = resultPage.selectProduct(ProductName);
		
		Assert.assertEquals(productInfoPage.getProductImagesCount(),imagesCount );
		
		
		
	}
}
