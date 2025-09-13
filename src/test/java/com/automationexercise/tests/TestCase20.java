package com.automationexercise.tests;


import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestCase20 {
	WebDriver driver;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("http://automationexercise.com");
    }

    @Test
    public void testSearchAddToCartAndLogin() throws InterruptedException {
    	 // Step 3: Click on 'Products'
        driver.findElement(By.xpath("//a[@href='/products']")).click();

        // Step 4: Verify ALL PRODUCTS page
        WebElement allProducts = driver.findElement(By.xpath("//h2[text()='All Products']"));
        Assert.assertTrue(allProducts.isDisplayed(), "All Products page not visible!");
        System.out.println(" All Products page is visible");

        // Step 5: Enter product name in search and click search
        driver.findElement(By.id("search_product")).sendKeys("Dress");
        driver.findElement(By.id("submit_search")).click();

        // Step 6: Verify 'SEARCHED PRODUCTS' is visible
        WebElement searchedProductsHeader = driver.findElement(By.xpath("//h2[text()='Searched Products']"));
        Assert.assertTrue(searchedProductsHeader.isDisplayed(), "Searched Products header not visible!");
        System.out.println(" Searched Products header is visible");
        
     // Step 7: Verify search results are displayed
        List<WebElement> searchedProducts = driver.findElements(By.cssSelector(".product-image-wrapper"));
        Assert.assertTrue(searchedProducts.size() > 0, "No products found for search!");
        System.out.println("✅ " + searchedProducts.size() + " products found");

        // Step 8: Add first product to cart (JS click fix for iframe issue)
        WebElement addToCartBtn = driver.findElement(By.xpath("//div[@class='productinfo text-center']//a[text()='Add to cart']"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", addToCartBtn);
        Thread.sleep(1000); // allow scroll settle
        js.executeScript("arguments[0].click();", addToCartBtn);
        Thread.sleep(2000); // wait for modal
        driver.findElement(By.xpath("//button[text()='Continue Shopping']")).click();
        System.out.println("Clicked Add to Cart and continued shopping");

        // Step 9: Go to Cart
        driver.findElement(By.xpath("//li//a[@href='/view_cart']")).click();
        List<WebElement> cartItems = driver.findElements(By.xpath("//tr[@id]"));
        Assert.assertTrue(cartItems.size() > 0, "No products visible in cart!");
        System.out.println(" Product added to cart and verified");

        // Step 10: Go to Signup/Login page
        driver.findElement(By.xpath("//li//a[@href='/login']")).click();
        System.out.println("In login page");
        
        // Step 10 (cont): Signup flow
        driver.findElement(By.xpath("//input[@data-qa='signup-name']")).sendKeys("TestUser");
        driver.findElement(By.xpath("//input[@data-qa='login-email']")).sendKeys("Automation07@gmail.com");
        driver.findElement(By.xpath("//button[@data-qa='signup-button']")).click();
        System.out.println("Signed up");

        // Step 11: Go to Cart page again
        driver.findElement(By.xpath("//li//a[@href='/view_cart']")).click();
        System.out.println("In cart page");

        // Step 12: Verify products are still in cart
        List<WebElement> cartItemsAfterLogin = driver.findElements(By.xpath("//tr[@id]"));
        Assert.assertTrue(cartItemsAfterLogin.size() > 0, "Products not visible in cart after login!");
        System.out.println("Products still visible in cart after login");


    }
}

