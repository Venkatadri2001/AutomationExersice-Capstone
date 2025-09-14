package com.automationexercise.tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestCase18 {
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
  public void viewCategoryProducts() throws InterruptedException {
	  //Verify that categories are visible on left side bar
	  WebElement categories = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("accordian")) );
      if (categories.isDisplayed()) {
          System.out.println("Categories are visible on left sidebar.");
      } else {
          System.out.println("Categories not visible!");
      }
      //Click on 'Women' category
      WebElement womenCategory = wait.until( ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='#Women']")));
      ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", womenCategory);
      womenCategory.click();
      System.out.println("User is clicked on Womens Category");
      //'Women' category, for example: Dress
      WebElement dressSubCategory = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='Women']//a[contains(text(),'Tops ')]")));
      ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", dressSubCategory);
      dressSubCategory.click();
      //WOMEN - TOPS PRODUCTS'
      WebElement womenProductsText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()='Women - Tops Products']")));
      if (womenProductsText.isDisplayed()) {
          System.out.println("Category page displayed: WOMEN - TOPS PRODUCTS");
      } else {
          System.out.println("Category page verification failed!");
      }
      //click on any sub-category link of 'Men' category
      WebElement menCategory = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='#Men']")) );
      ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", menCategory);
      menCategory.click();
      System.out.println("User is clicked on Mens Category");
      //Mens category - TShirts
      WebElement menTshirts = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[normalize-space()='Tshirts']")));
      ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", menTshirts);
      menTshirts.click();
      //Men - TShirts products
      WebElement menProductsText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()='Men - Tshirts Products']")) );
      if (menProductsText.isDisplayed()) {
          System.out.println("User navigated to MEN category page successfully.");
      } else {
          System.out.println("Navigation to MEN category failed!");
      }
  }
}

