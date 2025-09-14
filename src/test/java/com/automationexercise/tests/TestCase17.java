package com.automationexercise.tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestCase17 {
	WebDriver driver;
	String baseURL;
	String Email = "Automation07@gmail.com";
	String Pass = "Test@1234";
	WebDriverWait wait;
  @BeforeMethod
  public void setup() {
      driver = new ChromeDriver();
      driver.manage().window().maximize();
      baseURL = "https://automationexercise.com/";
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
  public void removeProductsFromCart() throws InterruptedException {
	  // home page is visible successfully
	  String home = driver.getCurrentUrl();
	  Assert.assertEquals(home, baseURL, "Home page URL is not correct!");
	  System.out.println("Home Page is Visible Successfully!");
	  //Add products to cart
	  WebElement addToCart = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@data-product-id='1']")));
      ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addToCart);
      ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addToCart);
      Thread.sleep(2000);
     
      WebElement continueShopping = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Continue Shopping']")));
      continueShopping.click();
      Thread.sleep(1000);
      System.out.println("Product added to cart!");
      
      driver.findElement(By.xpath("//a[contains(text(),'Cart')]")).click();
      //Verify that cart page is displayed
      wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[contains(text(),'Shopping Cart')]")));
      Thread.sleep(2000);
      System.out.println("User on Cart Page!");
      //Click 'X' button corresponding to particular product
      List<WebElement> removeBtns = driver.findElements(By.xpath("//a[@class='cart_quantity_delete']"));
      if (!removeBtns.isEmpty()) {
          WebElement firstRemove = removeBtns.get(0);
          ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", firstRemove);
          firstRemove.click();
          Thread.sleep(2000);
      }
      //Verify that product is removed from the cart
      removeBtns = driver.findElements(By.xpath("//a[@class='cart_quantity_delete']"));
      if (removeBtns.size() == 1) {
          System.out.println("Product removed successfully from cart.");
      } else {
          System.out.println("Product removal failed.");
      }
  }
}
