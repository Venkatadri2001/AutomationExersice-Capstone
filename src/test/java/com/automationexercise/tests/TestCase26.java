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
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestCase26 {
	WebDriver driver;
    WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void testScrollDownAndUp() throws InterruptedException {
        // Step 2: Navigate to url
        driver.get("http://automationexercise.com");

        // Step 3: Verify that home page is visible successfully
        String homeTitle = driver.getTitle();
        Assert.assertTrue(homeTitle.contains("Automation Exercise"), 
                "Home page is not visible");
        System.out.println("Home page is visible");

        // Step 4: Scroll down page to bottom
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
        Thread.sleep(2000);
        System.out.println("Scrolled down to bottom");

        // Step 5: Verify 'SUBSCRIPTION' is visible
        WebElement subscriptionText = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h2[text()='Subscription']")));
        Assert.assertTrue(subscriptionText.isDisplayed(), "'SUBSCRIPTION' is not visible");
        System.out.println(" 'SUBSCRIPTION' is visible");

        // Step 6: Scroll up page to top
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");
        Thread.sleep(2000);
        System.out.println("Scrolled up to top");

        // Step 7: Verify 'Full-Fledged practice website for Automation Engineers' text is visible
        WebElement topText = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h2[contains(text(),'Full-Fledged practice website for Automation Engineers')]")));
        Assert.assertTrue(topText.isDisplayed(), "Top text is not visible after scroll up");
        System.out.println("Verified top text is visible after scroll up");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
