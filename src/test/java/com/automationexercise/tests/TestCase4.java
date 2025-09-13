package com.automationexercise.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestCase4 {
WebDriver d;
	@BeforeMethod
	public void set() {
		WebDriverManager.chromedriver().setup();
		d=new ChromeDriver();
		d.manage().window().maximize();
	}
	@AfterMethod
	public void tearDown() {
		d.quit();
	}
	@Test
	public void login() throws InterruptedException {
		//1. Launch browser
		//2. Navigate to url 'http://automationexercise.com'
		d.get("https://automationexercise.com/");
		
		//3. Verify that home page is visible successfully
		System.out.println("Home page displayed successfully...");
		
		//4.Click on 'Signup / Login' button
		d.findElement(By.xpath("//a[@href='/login']")).click();
		
		//5.Verify 'Login to your account' is visible
		d.findElement(By.xpath("//h2[text()='Login to your account']"));
		System.out.println("Login to your account is visbile...");
		
		//6. Enter correct email address and password
		d.findElement(By.xpath("//input[@type='email']")).sendKeys("Automation07@gmail.com");
        d.findElement(By.xpath("//input[@type='password']")).sendKeys("Test@1234");
        
        //7. Click 'login' button
		d.findElement(By.xpath("//button[@type='submit']")).click();	
		
		//8. Verify that 'Logged in as username' is visible
		d.findElement(By.xpath("//a[contains(text(),'Logged in as')]"));
		
		//9. Click 'Logout' button
		d.findElement(By.xpath("//a[@href='/logout']")).click();
		
		//Verify that user is navigated to login page
		d.findElement(By.xpath("//h2[text()='Login to your account']"));
		
	}
}
