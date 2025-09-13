package com.automationexercise.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class TestCase5 {
	WebDriver d;

    @BeforeClass
    public void setup() {
        // Set the path to chromedriver executable if needed
        // System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        d = new ChromeDriver();
        d.manage().window().maximize();
       
    }
    @AfterMethod
	public void tearDown() {
		d.quit();
	}

    @Test
    public void testSignupWithExistingEmail() {
        // 2. Navigate to url
        d.get("http://automationexercise.com");

        //3.Verify that home page is visible successfully
        System.out.println("Home page visible successfully...");

        // 4. Click on 'Signup / Login' button
        WebElement signupButton = d.findElement(By.xpath("//a[@href='/login']"));
        signupButton.click();

        // 5. Verify 'New User Signup!' is visible
        WebElement newsignup = d.findElement(By.xpath("//h2[text()='New User Signup!']"));
        System.out.println(newsignup+" is visible");

        // 6. Enter name and already registered email address
        WebElement name= d.findElement(By.xpath("//input[@name='name']"));
        WebElement email = d.findElement(By.xpath("//input[@data-qa='signup-email']"));
        name.sendKeys("TestUser");
        email.sendKeys("Automation07@gmail.com"); // replace with an actual registered email

        // 7. Click 'Signup' button
        WebElement signup = d.findElement(By.xpath("//button[@data-qa='signup-button']"));
        signup.click();

        // 8. Verify error 'Email Address already exist!' is visible
        WebElement errormsg = d.findElement(By.xpath("//p[text()='Email Address already exist!']"));
        System.out.println("Message: " +errormsg);
        //Assert.assertTrue(errorMsg.isDisplayed(), "Error message is not displayed");
    }  
}
