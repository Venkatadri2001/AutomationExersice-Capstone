package com.automationexercise.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class TestCase3 {
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

    // Test Case 3: Login with incorrect credentials
    @Test
    public void TC3_LoginUserWithIncorrectCredentials() {
        web.get("https://automationexercise.com/");

        // Verify that home page is visible successfully
        Assert.assertTrue(web.getTitle().contains("Automation Exercise"));

        // Click on 'Signup / Login' button
        web.findElement(By.linkText("Signup / Login")).click();

        // Verify 'Login to your account' is visible
        Assert.assertTrue(wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//h2[text()='Login to your account']"))).isDisplayed());

        // Enter wrong email and password
        web.findElement(By.xpath("//input[@data-qa='login-email']")).sendKeys("wrongemail@test.com");
        web.findElement(By.xpath("//input[@data-qa='login-password']")).sendKeys("wrongpassword");
        web.findElement(By.xpath("//button[@data-qa='login-button']")).click();

        // Verify error message is visible
        Assert.assertTrue(wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//p[text()='Your email or password is incorrect!']"))).isDisplayed());
    }
}