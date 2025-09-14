package com.automationexercise.tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestCase21 {
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
	    public void addReviewOnProduct() {
	        // Step 3: Click on 'Products' button
	        driver.findElement(By.xpath("//a[@href='/products']")).click();

	        // Step 4: Verify ALL PRODUCTS page
	        String allProductsTitle = driver.findElement(By.xpath("//h2[@class='title text-center']")).getText();
	        Assert.assertEquals(allProductsTitle, "ALL PRODUCTS");

	        // Step 5: Click 'View Product'
	        driver.findElement(By.xpath("(//a[contains(text(),'View Product')])[1]")).click();

	        // Step 6: Verify 'Write Your Review'
	        Assert.assertTrue(driver.findElement(By.xpath("//a[contains(text(),'Write Your Review')]")).isDisplayed());

	        // Step 7: Enter review details
	        driver.findElement(By.id("name")).sendKeys("Test User");
	        driver.findElement(By.id("email")).sendKeys("testuser@example.com");
	        driver.findElement(By.id("review")).sendKeys("This is a test review. The product looks great!");

	        // Step 8: Submit
	        driver.findElement(By.id("button-review")).click();

	        // Step 9: Verify success message
	        String successMsg = driver.findElement(By.xpath("//span[contains(text(),'Thank you for your review.')]")).getText();
	        Assert.assertEquals(successMsg, "Thank you for your review.");
	    }

}
