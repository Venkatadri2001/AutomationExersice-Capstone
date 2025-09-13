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

public class TestCase11 {

	WebDriver driver;
	
	
	    @BeforeMethod
	    public void openBrowser()
	    {
	    	driver=new ChromeDriver();
	    	driver.manage().window().maximize();
	    	
	    }
	    
	    @AfterMethod
	    public void tearDown()
	    {
	    	driver.quit();
	    }
	    
		
	    @Test
	    public void Testcase12()
	    {
		
		try
		{
		driver.get("http://automationexercise.com");
		
		String pageTitle=driver.getTitle();
		if(pageTitle.equals("Automation Exercise"))
		{
			System.out.println("Home page is loaded sucessfully.");
		}
		else
		{
			System.out.println("Home page is not loaded sucessfully.");
			return;
		}

		driver.findElement(By.linkText("Cart")).click();
		
		 JavascriptExecutor js = (JavascriptExecutor) driver;
	     js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
	     
	     WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
	     WebElement subscriptionText = wait.until(ExpectedConditions.visibilityOfElementLocated(
	     By.xpath("//h2[text()='Subscription']")));
	     Assert.assertTrue(subscriptionText.isDisplayed(), "'SUBSCRIPTION' text is not visible");
	     
	     driver.findElement(By.id("susbscribe_email")).sendKeys("Automation07@gmail.com");
	     driver.findElement(By.xpath("//i[@class='fa fa-arrow-circle-o-right']")).click();
	     
	     WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
	     By.xpath("//*[contains(text(),'You have been successfully subscribed!')]")));
	     Assert.assertTrue(successMessage.isDisplayed(), "Success message not visible");
	     System.out.println("✅ Subscription success message displayed: " + successMessage.getText());

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
		  
}