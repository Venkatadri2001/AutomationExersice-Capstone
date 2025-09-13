package com.automationexercise.tests;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class TestCase13 {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void verifyProductQuantityInCart() {

        // Step 1 & 2: Launch and navigate
        driver.get("https://automationexercise.com");

        // Step 3: Verify home page visible
        Assert.assertTrue(driver.getTitle().contains("Automation Exercise"), "Home page not loaded");

        // Step 4: Click 'View Product' for first product
        WebElement viewProduct = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(@href,'/product_details/1')]")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", viewProduct);

        // Step 5: Verify product detail is opened
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector(".product-information"))).isDisplayed(),
                "Product details not visible");

        // Step 6: Increase quantity to 4
        WebElement qtyBox = driver.findElement(By.id("quantity"));
        qtyBox.clear();
        qtyBox.sendKeys("4");

        // Step 7: Click 'Add to cart'
        WebElement addToCart = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(.,'Add to cart')]")));
        addToCart.click();

        // Step 8: Click 'View Cart'
        WebElement viewCart = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//u[text()='View Cart']")));
        viewCart.click();

        // Step 9: Verify product quantity = 4
        String actualQty = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//td[@class='cart_quantity']//button"))).getText();
        Assert.assertEquals(actualQty, "4", "Quantity mismatch! Expected 4 but got " + actualQty);
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
