package com.automationexercise.tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestCase23 {
	WebDriver driver;
    WebDriverWait wait;
    
    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    
    @Test
    public void testAddressInCheckoutPage() throws InterruptedException {
    	// Navigate to url 'http://automationexercise.com'
    	driver.get("http://automationexercise.com");
    	
    	//Verify that home page is visible successfully
    	String homeTitle = driver.getTitle();
        Assert.assertTrue(homeTitle.contains("Automation Exercise"),
                "❌ Home page is not visible");
        System.out.println("Home page is visible");
        
        // Click on 'Signup / Login' button
        driver.findElement(By.linkText("Signup / Login")).click();
        
        // Enter name and email address
        driver.findElement(By.xpath("//input[@data-qa='signup-name']")).sendKeys("AutomationExercipol000");
        driver.findElement(By.xpath("//input[@data-qa='signup-email']")).sendKeys("MyTestingEmail02@gmail.com");
        Thread.sleep(5000);
        
        // Click 'Signup' button
        driver.findElement(By.xpath("//button[@data-qa='signup-button']")).click();
        Thread.sleep(3000);
        
        
        // Fill all details in Signup and create account
        driver.findElement(By.id("id_gender1")).click();
        driver.findElement(By.id("password")).sendKeys("Test@123400");
        driver.findElement(By.id("days")).sendKeys("1");
        driver.findElement(By.id("months")).sendKeys("January");
        driver.findElement(By.id("years")).sendKeys("2000");
        
     
        driver.findElement(By.id("newsletter")).click();
        driver.findElement(By.id("optin")).click();
        
     
        driver.findElement(By.id("first_name")).sendKeys("Autom");
        driver.findElement(By.id("last_name")).sendKeys("Exercise");
        driver.findElement(By.id("company")).sendKeys("Test Companyx");
        driver.findElement(By.id("address1")).sendKeys("123 Test Street");
        driver.findElement(By.id("address2")).sendKeys("Near Automation Park");
        driver.findElement(By.id("country")).sendKeys("India");
        driver.findElement(By.id("state")).sendKeys("Maharashtra");
        driver.findElement(By.id("city")).sendKeys("Pune");
        driver.findElement(By.id("zipcode")).sendKeys("411030");
        driver.findElement(By.id("mobile_number")).sendKeys("0009022222");
        
        // Click 'Create Account' button
        driver.findElement(By.xpath("//button[@data-qa='create-account']")).click();
        
        Thread.sleep(4000);
        
        
        // Verify 'ACCOUNT CREATED!' and click 'Continue' button
        Assert.assertTrue(wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//b[text()='Account Created!']"))).isDisplayed());

        
        driver.findElement(By.xpath("//a[@data-qa='continue-button']")).click();
        
        // Verify ' Logged in as username' at top
        Assert.assertTrue(wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//a[contains(text(),'Logged in as')]"))).isDisplayed());
        Thread.sleep(3000);
        
        
      //Add products to cart
  	  	WebElement addToCart = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@data-product-id='1']")));
  	  	((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addToCart);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addToCart);
        Thread.sleep(2000);
        
        WebElement continueShopping = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Continue Shopping']")));
        continueShopping.click();
        Thread.sleep(1000);
        System.out.println("Product added to cart!");
        
        //Click 'Cart' button
        driver.findElement(By.xpath("//a[contains(text(),'Cart')]")).click();
        
        //Verify that cart page is displayed
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[contains(text(),'Shopping Cart')]")));
        Thread.sleep(2000);
        System.out.println("User on Cart Page!");
        
        //Click Proceed To Checkout
        WebElement checkOut = driver.findElement(By.xpath("//a[normalize-space()='Proceed To Checkout']"));
        checkOut.click();
        
        // Verify that the delivery address is same address filled at the time registration of account
        WebElement deliveryAddress = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//ul[@id='address_delivery']")));
        String deliveryText = deliveryAddress.getText();
        Assert.assertTrue(deliveryText.contains("Autom"), "❌ Delivery address mismatch!");
        Assert.assertTrue(deliveryText.contains("Exercise"), "❌ Delivery address mismatch!");
        Assert.assertTrue(deliveryText.contains("123 Test Street"), "❌ Delivery address mismatch!");
        Assert.assertTrue(deliveryText.contains("Pune"), "❌ Delivery address mismatch!");
        System.out.println("✅ Delivery address verified successfully");

        // Verify that the billing address is same address filled at the time registration of account
        WebElement billingAddress = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//ul[@id='address_invoice']")));
        String billingText = billingAddress.getText();
        Assert.assertTrue(billingText.contains("Autom"), "❌ Billing address mismatch!");
        Assert.assertTrue(billingText.contains("Exercise"), "❌ Billing address mismatch!");
        Assert.assertTrue(billingText.contains("123 Test Street"), "❌ Billing address mismatch!");
        Assert.assertTrue(billingText.contains("Pune"), "❌ Billing address mismatch!");
        System.out.println("✅ Billing address verified successfully");

        Thread.sleep(2000);
        
        // Click 'Delete Account' button
        driver.findElement(By.linkText("Delete Account")).click();

        // Verify 'ACCOUNT DELETED!' and click 'Continue' button
        Assert.assertTrue(wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//b[text()='Account Deleted!']"))).isDisplayed());
        
        WebElement continueLast = driver.findElement(By.cssSelector(".btn.btn-primary"));
        continueLast.click();

        Thread.sleep(2000);
        
    }
    
    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
