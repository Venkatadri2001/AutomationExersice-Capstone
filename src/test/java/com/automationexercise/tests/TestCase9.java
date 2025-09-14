package com.automationexercise.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestCase9 {
	WebDriver d;
	@BeforeTest
	void setUp() {
		d=new ChromeDriver();
		d.manage().window().maximize();
		d.get("https://automationexercise.com/");
		
	}
	@AfterTest
		void close() {
		d.close();
		System.out.println("chrome closed");
	} 
		@BeforeMethod
		void login() {
			d.findElement(By.linkText("Signup / Login")).click();
			d.findElement(By.xpath("//input[@type='email']")).sendKeys("Automation07@gmail.com");
			d.findElement(By.xpath("//input[@type='password']")).sendKeys("Test@1234");
			d.findElement(By.xpath("//button[@type='submit']")).click();
		}
		@Test
		void searchProduct() throws InterruptedException {
				
		//	1. Launch browser
		// 2. Navigate to url 'http://automationexercise.com'
			
			// 3. Verify that home page is visible successfully
			String hpage = d.getTitle();
	        Assert.assertTrue(hpage.contains("Automation Exercise"));
	        System.out.println("Home page is visible");
	        Thread.sleep(3000);
	        
	        //4.Click on 'Products' button
	        d.findElement(By.partialLinkText(" Products")).click();
	        
	        //5. Verify user is navigated to ALL PRODUCTS page successfully
	        if (d.findElement(By.xpath("//*[text()='All Products']")).isDisplayed()) {
	        	 System.out.println("navigated successfully");
	        } else { 
	       	    System.out.println("navigation failed");
	       }
	        
	        //6. Enter product name in search input and click search button
	      
	        d.findElement(By.id("search_product")).sendKeys("Tshirt");
	        d.findElement(By.id("submit_search")).click();
	        Thread.sleep(2000);
	        
	        //7. Verify 'SEARCHED PRODUCTS' is visible
	        Assert.assertTrue(d.findElement(By.xpath("//*[text()='Searched Products']")).isDisplayed(),
	                "'Searched Products' not visible");
	        System.out.println("Searched Products text is visible");
	        
	        //8. Verify all the products related to search are visible
	        int productCount = d.findElements(By.xpath("//div[@class='productinfo text-center']")).size();
	        Assert.assertTrue(productCount > 0, "No products found for the search");
	        System.out.println(productCount + " products displayed for the search");
		}

}
