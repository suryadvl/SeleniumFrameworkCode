package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.FileHandler;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.opencart.errors.AppError;
import com.qa.opencart.exceptions.BrowserException;

public class DriverFactory {
	
	WebDriver driver ;
	Properties prop;
	
	
	/**
	 * This driver is used to init the driver on the basis of given browsername.
	 * @param browserName
	 * @return Webdriver
	 */
	
	public WebDriver initDriver(Properties prop) {
		
		String browserName =  prop.getProperty("browser");
		
		System.out.println("browser Name is :"+browserName );
		
		switch(browserName.toLowerCase().trim()) {
		
		case "chrome" :
			driver = new ChromeDriver();
			break;
		
		case "firefox" :
			driver = new FirefoxDriver();
			break;
			
		case "edge": {

			driver = new EdgeDriver();
			break;
		}
		
		case "safari": {

			driver = new SafariDriver();
			break;
		}

		default:
			System.out.println(AppError.INVALID_BROWSER_MSEG+ browserName);
			throw new BrowserException("Invalid Browser :" + browserName);

		}

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get(prop.getProperty("url"));
		
		return driver;
		
		}
	
	/**
	 * This method is used to init the properties from the config file. 
	 * @return
	 */
	
	 public Properties intiProp() {
		 
		  prop = new Properties();
		  try {
		  FileInputStream ip = new FileInputStream("./src/test/resources/config/config.properties");
		  prop.load(ip);
		  }
		  catch (FileNotFoundException e) {
			  e.printStackTrace();
		  }
		  catch(IOException e) {
			  e.printStackTrace();
		  }
		  
		  return prop;
		  
		 
		 
	 }
	 
	 
	 
	 /**
		 * take screenshot 
		 */
		
//		public static String getScreenshot(String methodName) {
//			File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);//temp dir
//			String path = System.getProperty("user.dir") + "/screenshot/" + methodName + "_" + System.currentTimeMillis()+ ".png";
//			File destination = new File(path);
//			try {
//				FileHandler.copy(srcFile, destination);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//
//			return path;
//		}
//		
		
	}


