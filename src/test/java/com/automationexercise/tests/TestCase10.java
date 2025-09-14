package com.automationexercise.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestCase10 {

	   WebDriver driver;
	   
	   @BeforeTest
	   public void openBrowser()
	   {
		   driver=new ChromeDriver();
		   driver.manage().window().maximize();
	   }
		
		@Test
		
		public void homePage()
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
		
        WebElement footer=driver.findElement(By.xpath("//div[@class='footer-widget']"));
        Actions a=new Actions(driver);
        a.moveToElement(footer).perform();
        
        WebElement subscription=driver.findElement(By.xpath("//div[@class='form-row']"));
        if(subscription.isDisplayed())
        {
        	System.out.println("Subscription text found.");
        }
        else
        {
        	System.out.println("Subscription text not found.");
        	return;
        }
        
        driver.findElement(By.id("susbscribe_email")).sendKeys("Automation07@gmail.com");
        driver.findElement(By.xpath("//i[@class='fa fa-arrow-circle-o-right']")).click();
        
        WebElement message=driver.findElement(By.xpath("//*[contains(text(),'You have been successfully subscribed!')]"));
        if(message.isDisplayed())
        {
        	System.out.println("Success message is displayed.");
        }
        else
        {
        	System.out.println("Success message is not displayed.");
        	return;
        }
        
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		}
		
		@AfterTest
		public void tearDown()
		{
			driver.close();
		}
       
		

	}
