package com.automationexercise.tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestCase15 {
	WebDriver driver;
	String baseURL;
	String email = "auto" + System.currentTimeMillis() + "@test.com";
	String name = "TestingUName";
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
  public void placeOrderLoginBeforeCheckoyt() throws InterruptedException {
	  //home page visibility
	  String home = driver.getCurrentUrl();
	  Assert.assertEquals(home, baseURL, "Home page URL is not correct!");
	  System.out.println("Home Page is Visible Successfully!");
	  
	  driver.findElement(By.xpath("//a[@href='/login']")).click();
	  
	  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='name']"))).sendKeys(name);
      driver.findElement(By.xpath("//input[@data-qa='signup-email']")).sendKeys(email);
      driver.findElement(By.xpath("//button[@data-qa='signup-button']")).click();
      
      wait.until(ExpectedConditions.elementToBeClickable(By.id("id_gender1"))).click();
      driver.findElement(By.id("password")).sendKeys("Test@1234");
      driver.findElement(By.id("days")).sendKeys("29");
      driver.findElement(By.id("months")).sendKeys("Feb");
      driver.findElement(By.id("years")).sendKeys("2002");
      driver.findElement(By.id("first_name")).sendKeys("Ven");
      driver.findElement(By.id("last_name")).sendKeys("M");
      driver.findElement(By.id("address1")).sendKeys("Hyderabad");
      WebElement country = driver.findElement(By.id("country"));
      Select selCountry = new Select(country);
      selCountry.selectByValue("India");
      driver.findElement(By.id("state")).sendKeys("Telangana");
      driver.findElement(By.id("city")).sendKeys("Hyderabad");
      driver.findElement(By.id("zipcode")).sendKeys("500001");
      driver.findElement(By.id("mobile_number")).sendKeys("9876543210");
      driver.findElement(By.xpath("//button[@data-qa='create-account']")).click();
      System.out.println("User registered successfully!");
      
      WebElement createdMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//b[text()='Account Created!']")));
      Assert.assertTrue(createdMsg.isDisplayed(), "Account not created!");
      Thread.sleep(2000);
      driver.findElement(By.xpath("//a[@data-qa='continue-button']")).click();
      System.out.println("User account created successfully");
      
      //Verify 'Logged in as username' at top
      wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Logged in as')]")));
      System.out.println("User is loginned Successfully!");
      //Add products to cart
      WebElement addToCart = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@data-product-id='1']")));
      ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addToCart);
      ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addToCart);
      Thread.sleep(2000);
      System.out.println("User selected product!");
      
      WebElement continueShopping = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Continue Shopping']")));
      continueShopping.click();
      Thread.sleep(1000);
      System.out.println("Product added to cart!");
      
      driver.findElement(By.xpath("//a[contains(text(),'Cart')]")).click();
      //Verify that cart page is displayed
      wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[contains(text(),'Shopping Cart')]")));
      System.out.println("User on Cart Page!");
      
      driver.findElement(By.xpath("//a[text()='Proceed To Checkout']")).click();
      //Verify Address Details and Review Your Order
      wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()='Address Details']")));
      wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()='Review Your Order']")));
      System.out.println("Users address is verified!");
      
      driver.findElement(By.xpath("//textarea[@name='message']")).sendKeys("Please deliver between 9AM - 6PM");
      driver.findElement(By.xpath("//a[text()='Place Order']")).click();
      //Enter payment details: Name on Card, Card Number, CVC, Expiration date
      driver.findElement(By.xpath("//input[@name='name_on_card']")).sendKeys("AutomationExercise");
      driver.findElement(By.xpath("//input[@name='card_number']")).sendKeys("4111111111111111");
      driver.findElement(By.xpath("//input[@name='cvc']")).sendKeys("123");
      driver.findElement(By.xpath("//input[@name='expiry_month']")).sendKeys("12");
      driver.findElement(By.xpath("//input[@name='expiry_year']")).sendKeys("2027");
      System.out.println("User Entered Payment Details!");
     //Pay and Confirm Order' button
      WebElement payButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("submit")));
      ((JavascriptExecutor) driver).executeScript("arguments[0].click();", payButton);
      System.out.println("User placed order successfully!");
      
      //Verify success message 'Your order has been placed successfully!'
//      wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("success_message")));
      
      // 'Delete Account' button
      driver.findElement(By.xpath("//a[contains(text(),'Delete Account')]")).click();
      //Verify 'ACCOUNT DELETED!' and click 'Continue' button
      WebElement deletedMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//b[text()='Account Deleted!']")));
      Assert.assertTrue(deletedMsg.isDisplayed(), "Account deleted message not displayed!");
      driver.findElement(By.xpath("//a[@data-qa='continue-button']")).click();
      System.out.println("User account is deleted successfully!");

  }

}
