package com.automationexercise.tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestCase7 {
		WebDriver driver;
		String baseURL;
		WebDriverWait wait;
	  @BeforeClass
	  public void setup() {
	      driver = new ChromeDriver();
	      driver.manage().window().maximize();
	      baseURL = "https://automationexercise.com/";
	      driver.get(baseURL);
	      wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	  }
	  @AfterClass
	  public void tearDown() {
	      if (driver != null) {
	          driver.quit();
	      }
	  }
  @Test
  public void verifyTestCasePage() {
	  
	  Assert.assertEquals(driver.getCurrentUrl(), baseURL, "Home page is not visible!");  
	  driver.findElement(By.xpath("//a[text()=' Test Cases']")).click();  
	  String testURL= driver.getCurrentUrl();
	  Assert.assertEquals(driver.getCurrentUrl(), testURL, "Test Case page is Not Navigated");
  }
}
