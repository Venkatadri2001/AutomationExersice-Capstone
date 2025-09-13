package com.automationexercise.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class TestCase1 {
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

    // Test Case 1: Register User
    @Test
    public void TC1_RegisterUser() {
        web.get("https://automationexercise.com/");

        // Verify that home page is visible successfully
        Assert.assertTrue(web.getTitle().contains("Automation Exercise"), "Home page not loaded!");

        // Click on 'Signup / Login' button
        web.findElement(By.linkText("Signup / Login")).click();

        // Verify 'New User Signup!' is visible
        Assert.assertTrue(wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//h2[text()='New User Signup!']"))).isDisplayed());

        // Enter name and email address
        web.findElement(By.xpath("//input[@data-qa='signup-name']")).sendKeys("AutomationExercise");
        web.findElement(By.xpath("//input[@data-qa='signup-email']")).sendKeys("AutomationTria02@gmail.com");

        // Click 'Signup' button
        web.findElement(By.xpath("//button[@data-qa='signup-button']")).click();

        // Verify 'ENTER ACCOUNT INFORMATION' is visible
        Assert.assertTrue(wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//b[text()='Enter Account Information']"))).isDisplayed());

        // Fill details: Title, Name, Email, Password, Date of birth
        web.findElement(By.id("id_gender1")).click();
        web.findElement(By.id("password")).sendKeys("Test@1234");
        web.findElement(By.id("days")).sendKeys("1");
        web.findElement(By.id("months")).sendKeys("January");
        web.findElement(By.id("years")).sendKeys("2000");

        // Select checkboxes
        web.findElement(By.id("newsletter")).click();
        web.findElement(By.id("optin")).click();

        // Fill address details
        web.findElement(By.id("first_name")).sendKeys("Automation");
        web.findElement(By.id("last_name")).sendKeys("Exercise");
        web.findElement(By.id("company")).sendKeys("Test Company");
        web.findElement(By.id("address1")).sendKeys("123 Test Street");
        web.findElement(By.id("address2")).sendKeys("Near Automation Park");
        web.findElement(By.id("country")).sendKeys("India");
        web.findElement(By.id("state")).sendKeys("Maharashtra");
        web.findElement(By.id("city")).sendKeys("Pune");
        web.findElement(By.id("zipcode")).sendKeys("411033");
        web.findElement(By.id("mobile_number")).sendKeys("7348324932");

        // Click 'Create Account' button
        web.findElement(By.xpath("//button[@data-qa='create-account']")).click();

        // Verify that 'ACCOUNT CREATED!' is visible
        Assert.assertTrue(wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//b[text()='Account Created!']"))).isDisplayed());

        // Click 'Continue' button
        web.findElement(By.xpath("//a[@data-qa='continue-button']")).click();

        // Verify that 'Logged in as username' is visible
        Assert.assertTrue(wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//a[contains(text(),'Logged in as')]"))).isDisplayed());
    }
}
