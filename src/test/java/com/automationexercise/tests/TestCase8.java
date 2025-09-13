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
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestCase8 {
	WebDriver d;
	 WebDriverWait wait;
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
		void  verifyAllProductsAndProductDetailPage() {
			
			// 1. Launch browser
			//	2. Navigate to url 'http://automationexercise.com'
			//3. Verify that home page is visible successfully	
	        Assert.assertTrue(d.getTitle().contains("Automation Exercise"), "Home page is not visible");
	        System.out.println("Home page is visible");
			
	        //4. Click on 'Products' button
			d.findElement(By.partialLinkText(" Products")).click();
			
			//5. Verify user is navigated to ALL PRODUCTS page successfully
			 Assert.assertTrue(d.getTitle().contains("All Products"), " All products page not navigated");
		     System.out.println("All products page navigated");
			
		       // 6. The products list is visible
		       WebElement productList = d.findElement(By.xpath("//div[@class='features_items']"));
		       Assert.assertTrue(productList.isDisplayed(), "Products list is not visible");
		       System.out.println("Products list is visible");
		       
		    // Step 7: Click on 'View Product' of first product
		       WebDriverWait wait = new WebDriverWait(d, Duration.ofSeconds(10));
		       WebElement firstProduct = wait.until(
		           ExpectedConditions.elementToBeClickable(By.xpath("(//a[contains(text(),'View Product')])[1]"))
		       );

		       // Scroll into view (safety)
		       ((JavascriptExecutor) d).executeScript("arguments[0].scrollIntoView(true);", firstProduct);

		       // Click
		       firstProduct.click();
		       System.out.println("Clicked on 'View Product' of first product");


		       // Step 8: Verify user is landed on product detail page
		       WebElement productDetailSection = d.findElement(By.xpath("//div[@class='product-information']"));
		       Assert.assertTrue(productDetailSection.isDisplayed(), " Product detail section is not visible");
		       System.out.println("User landed on product detail page");

		       // Step 9: Verify product details

		       // Product Name
		       Assert.assertTrue(d.findElement(By.xpath("//div[@class='product-information']/h2")).isDisplayed(), "Product Name not visible");
		       System.out.println("Product name displayed");

		       // Category
		       Assert.assertTrue(d.findElement(By.xpath("//div[@class='product-information']//p[contains(text(),'Category')]")).isDisplayed(), "Category not visible");
		       System.out.println("Product category displayed");

		       // Price
		       Assert.assertTrue(d.findElement(By.xpath("//div[@class='product-information']//span/span[contains(text(),'Rs.')]")).isDisplayed(), "Price not visible");
		       System.out.println("Product price displayed");

		       // Availability
		       Assert.assertTrue(d.findElement(By.xpath("//div[@class='product-information']//p/b[text()='Availability:']")).isDisplayed(), "Availability not visible");
		       System.out.println("Product availability displayed");

		       // Condition
		       Assert.assertTrue(d.findElement(By.xpath("//div[@class='product-information']//p/b[text()='Condition:']")).isDisplayed(), "Condition not visible");
		       System.out.println("Product condition displayed");

		       // Brand
		       Assert.assertTrue(d.findElement(By.xpath("//div[@class='product-information']//p/b[text()='Brand:']")).isDisplayed(), "Brand not visible");
		       System.out.println("Product brand displayed");

		}
		}