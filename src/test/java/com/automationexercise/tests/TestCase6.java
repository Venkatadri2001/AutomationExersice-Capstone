package com.automationexercise.tests;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestCase6 {
	WebDriver d;
	@BeforeMethod
    public void setup() {
        d = new ChromeDriver();
        d.manage().window().maximize();
        d.get("http://automationexercise.com");
    }
	@AfterMethod
	public void tearDown() {
		d.quit();
	}

    @Test(priority = 1)
    public void verifyHomePage() {
        // Verify home page is visible
        String home = d.getTitle();
        Assert.assertTrue(home.contains("Automation Exercise"), "Home page not visible");
    }

    @Test(priority = 2)
    public void contactUsFormSubmission() throws InterruptedException {
        // Click on 'Contact Us' button
        WebElement contactUsBtn = d.findElement(By.xpath("//a[@href='/contact_us']"));
        contactUsBtn.click();

        // Verify 'GET IN TOUCH' is visible
        WebElement getin= d.findElement(By.xpath("//h2[text()='Get In Touch']"));
        Assert.assertTrue(getin.isDisplayed(), "'GET IN TOUCH' not visible");

        // Enter Name, Email, Subject, Message
        d.findElement(By.xpath("//input[@placeholder='Name']")).sendKeys("Test");
        d.findElement(By.xpath("//input[@placeholder='Email']")).sendKeys("Aoutomation07@gmail.com");
        d.findElement(By.xpath("//input[@placeholder='Subject']")).sendKeys("Test Subject is that we are filling the contact us form");
        d.findElement(By.xpath("//textarea[@placeholder='Your Message Here']")).sendKeys("This is a test message.");

        // Upload file
        d.findElement(By.xpath("//input[@name='upload_file']")).sendKeys("C:/Users/DEVI SRI/Desktop/Automation test message file.txt");

        // Click Submit
        d.findElement(By.xpath("//input[@type='submit']")).click();

        // Handle alert popup if exists
        try {
            Alert alert = d.switchTo().alert();
            alert.accept();
        } catch (Exception e) {
            System.out.println("No alert present");
        }

        // Verify success message
        WebElement msg = d.findElement(By.xpath("//*[text()='Success! Your details have been submitted successfully.']"));
        Assert.assertTrue(msg.isDisplayed(), "Success message not displayed");

        // Click 'Home' button
        d.findElement(By.xpath("//a[text()=' Home']")).click();

        // Verify landed on home page
        String homeafter = d.getTitle();
        Assert.assertTrue(homeafter.contains("Automation Exercise"), "Did not navigate back to home page");
    }

    

}