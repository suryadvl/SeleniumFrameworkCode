package com.qa.opencart.tests;

import java.util.List;

import org.junit.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.pages.ResultsPage;

public class AccountsPageTest extends BaseTest{
	
	@BeforeClass
	public void accSetup() {
		
		accountpage=loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
			
		
	}
	
	@Test
	public void accPageTitleTest() {
		
		String accTitle = accountpage.getAccountsPageTitle();
		
		Assert.assertEquals(accTitle, AppConstants.ACCOUNT_PAGE_TITLE);
		
		
	}
	
	@Test
	public void isLogOutLinkExistTest() {
		
		Assert.assertTrue(accountpage.isLogoutLinkExist());
	}
	
	
	@Test
	public void getAcctpageHeadersCountTest() {
		
	  Assert.assertEquals(accountpage.getAcctpageHeadersList(), AppConstants.ACCOUNT_PAGE_HEADERS_COUNT);
	}
	
	
	
	@Test
	public void acctpageHeadersTest() {
		 List<String> acctualheadersList = accountpage.getAcctpageHeaders();
		 Assert.assertEquals(acctualheadersList, AppConstants.EXPECTED_ACE_PAGE_HEADERS_LIST);
		 
		 
		 
	}
	
	@DataProvider
	public Object[][] getSearchkey() {
		
	    return new Object[][] {
			
			{"macbook", 3},
			{"imac", 1},
			{"samsung", 2}};
		
	}
	
	
	@Test(dataProvider ="getSearchkey")
	
	public void searchCountTest(String searchKey , int searchcount) {
		
		resultPage = accountpage.doSearch(searchKey);
		Assert.assertEquals(resultPage.getSearchResultsCount(), searchcount);
		
		
		
	}
	
	
	@DataProvider
	public Object[][] getSearchData() {
		
	    return new Object[][] {
			
			{"macbook", "MacBook Pro"},
			{"macbook", "MacBook Air"},
			{"imac", "iMac" },
			{"Samsung", "Samsung SyncMaster 941BW"},
			{"Samsung", "Samsung Galaxy Tab 10.1"}};
		
	}
	
		
	@Test(dataProvider = "getSearchData")
	public void searchTest(String searchproduct , String productName) {
		
		resultPage = accountpage.doSearch(searchproduct);
		
		productInfoPage = resultPage.selectProduct(productName);
		
		 Assert.assertEquals(productInfoPage.getProductHeader(), productName);
		 
				
		
	}
	
	
	
}