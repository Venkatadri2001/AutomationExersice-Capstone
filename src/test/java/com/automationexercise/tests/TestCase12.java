package com.automationexercise.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;


public class TestCase12 {
	
	WebDriver web;
    WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        web = new ChromeDriver();
        wait = new WebDriverWait(web, Duration.ofSeconds(10));
        web.manage().window().maximize();
    }

    @AfterMethod
    public void tearDown() throws InterruptedException {
        if (web != null) {
        	Thread.sleep(4000);
            web.quit();
        }
    }
	
    @Test
    public void TC12_AddProductsInCart() throws InterruptedException {
        web.get("https://automationexercise.com/");

        // Verify that home page is visible successfully
        String homeTitle = web.getTitle();
        Assert.assertTrue(homeTitle.contains("Automation Exercise"), "Home page not loaded!");

        // Click 'Products' button
        web.findElement(By.xpath("//a[@href='/products']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()='All Products']")));

        // Scroll down a bit after Products page loads
        ((JavascriptExecutor) web).executeScript("window.scrollBy(0, 500);");
        
        Actions actions = new Actions(web);

        // Hover over first product
        WebElement firstProductWrapper = web.findElement(By.xpath("(//div[@class='product-image-wrapper'])[1]"));
        actions.moveToElement(firstProductWrapper).perform();
        Thread.sleep(500); // small wait for overlay animation

        // Click 'Add to cart' (JS click to avoid interception)
        WebElement firstAddToCart = firstProductWrapper.findElement(By.xpath(".//a[@class='btn btn-default add-to-cart']"));
        ((JavascriptExecutor) web).executeScript("arguments[0].click();", firstAddToCart);

        // Click 'Continue Shopping' button
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Continue Shopping']"))).click();

        // Hover over second product
        WebElement secondProductWrapper = web.findElement(By.xpath("(//div[@class='product-image-wrapper'])[2]"));
        actions.moveToElement(secondProductWrapper).perform();
        Thread.sleep(500);

        // Click 'Add to cart' (JS click again)
        WebElement secondAddToCart = secondProductWrapper.findElement(By.xpath(".//a[@class='btn btn-default add-to-cart']"));
        ((JavascriptExecutor) web).executeScript("arguments[0].click();", secondAddToCart);

        // Click 'View Cart' button
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//u[text()='View Cart']"))).click();

        // Verify both products are added to Cart
        WebElement firstCartProduct = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//tr[1]//td[@class='cart_description']")));
        WebElement secondCartProduct = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//tr[2]//td[@class='cart_description']")));

        Assert.assertTrue(firstCartProduct.isDisplayed(), "First product not added to cart!");
        Assert.assertTrue(secondCartProduct.isDisplayed(), "Second product not added to cart!");

        // Verify their prices, quantity and total price
        Assert.assertTrue(web.findElement(By.xpath("//tr[1]//td[@class='cart_price']")).isDisplayed());
        Assert.assertTrue(web.findElement(By.xpath("//tr[1]//td[@class='cart_quantity']")).isDisplayed());
        Assert.assertTrue(web.findElement(By.xpath("//tr[1]//td[@class='cart_total']")).isDisplayed());

        Assert.assertTrue(web.findElement(By.xpath("//tr[2]//td[@class='cart_price']")).isDisplayed());
        Assert.assertTrue(web.findElement(By.xpath("//tr[2]//td[@class='cart_quantity']")).isDisplayed());
        Assert.assertTrue(web.findElement(By.xpath("//tr[2]//td[@class='cart_total']")).isDisplayed());
    }

}
