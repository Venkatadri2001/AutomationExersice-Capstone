package com.automationexercise.tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestCase19 {
	WebDriver driver;
	String baseURL;
	WebDriverWait wait;
@BeforeMethod
	 public void setup() {
	   	driver = new ChromeDriver();
	    driver.manage().window().maximize();
	    baseURL = "http://automationexercise.com/";
	    driver.get(baseURL);
	    wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	  }
	  @AfterMethod
	  public void tearDown() {
	      if (driver != null) {
	          driver.quit();
	      }
	  }
		@Test
	    public void viewCartBrandProducts() {

	        // Step 3: Click on 'Products' button
	        driver.findElement(By.xpath("//a[@href='/products']")).click();

	        // Step 4: Verify that Brands are visible on left side bar
	        
	        Assert.assertTrue(driver.findElement(By.xpath("//a[@href=\"/brand_products/Polo\"]")).isDisplayed(),
	                "Verify that Brands are visible on left side bar");

	        // Step 5: Click on Polo brand
	        
	        WebElement poloBrand = driver.findElement(By.xpath("//a[@href='/brand_products/Polo']"));
	        poloBrand.click();

	        // Step 6: Verify Polo products page
	        
	        String poloTitle = driver.findElement(By.xpath("//h2[@class='title text-center']")).getText();
	        Assert.assertEquals(poloTitle, "BRAND - POLO PRODUCTS");

	        // Step 7: Click on another brand (Madame)
	        
	        driver.findElement(By.xpath("//a[@href=\"/brand_products/Madame\"]")).click();

	        // Step 8: Verify Madame products page
	        
	        String madameTitle = driver.findElement(By.xpath("//h2[@class='title text-center']")).getText();
	        Assert.assertEquals(madameTitle, "BRAND - MADAME PRODUCTS");
	    }
}
