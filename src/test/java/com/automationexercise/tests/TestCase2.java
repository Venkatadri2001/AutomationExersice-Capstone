package com.automationexercise.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class TestCase2 {
    WebDriver web;
    WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        web = new ChromeDriver();
        wait = new WebDriverWait(web, Duration.ofSeconds(10));
        web.manage().window().maximize();
    }

    @AfterMethod
    public void tearDown() {
        if (web != null) {
            web.quit();
        }
    }

    // Test Case 2: Login with correct credentials
    @Test
    public void TC2_LoginUserWithCorrectCredentials() {
        web.get("https://automationexercise.com/");

        // Verify that home page is visible successfully
        Assert.assertTrue(web.getTitle().contains("Automation Exercise"));

        // Click on 'Signup / Login' button
        web.findElement(By.linkText("Signup / Login")).click();

        // Verify 'Login to your account' is visible
        Assert.assertTrue(wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//h2[text()='Login to your account']"))).isDisplayed());

        // Enter correct email and password
        web.findElement(By.xpath("//input[@data-qa='login-email']")).sendKeys("AutomationTrial02@gmail.com");
        web.findElement(By.xpath("//input[@data-qa='login-password']")).sendKeys("Test@1234");

        // Click 'login' button
        web.findElement(By.xpath("//button[@data-qa='login-button']")).click();

        // Verify 'Logged in as username'
        Assert.assertTrue(wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//a[contains(text(),'Logged in as')]"))).isDisplayed());

        // Click 'Delete Account'
        web.findElement(By.linkText("Delete Account")).click();

        // Verify 'ACCOUNT DELETED!' is visible
        Assert.assertTrue(wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//b[text()='Account Deleted!']"))).isDisplayed());
    }
}

