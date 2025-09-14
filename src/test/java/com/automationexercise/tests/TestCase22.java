package com.automationexercise.tests;

import java.time.Duration;
import java.util.List;

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

public class TestCase22 {
	WebDriver driver;
	String baseURL;
	WebDriverWait wait;
@BeforeMethod
	 public void setup() {
	   	driver = new ChromeDriver();
	    driver.manage().window().maximize();
	    wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	  }
@AfterMethod
	 public void tearDown() {
	     if (driver != null) {
	         driver.quit();
	     }
	  }
@Test
public void verifyRecommendedItemsAddToCart() {

    // Step 1 & 2: Launch browser and navigate to ur
	driver.get("http://automationexercise.com");
    // Step 3: Scroll to bottom of page
    WebElement footer = wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("footer")));
    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", footer);

    // Step 4: Verify 'RECOMMENDED ITEMS' are visible
    WebElement recommendedItems = wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//h2[normalize-space(text())='recommended items' or normalize-space(text())='RECOMMENDED ITEMS']")
            )
    );
    Assert.assertTrue(recommendedItems.isDisplayed(), "'RECOMMENDED ITEMS' section is not visible");

    // Step 5: Click on 'Add To Cart' of the first Recommended product
    WebElement addToCartBtn = wait.until(
            ExpectedConditions.elementToBeClickable(
                    By.xpath("(//div[@id='recommended-item-carousel']//a[contains(text(),'Add to cart')])[1]")
            )
    );
    addToCartBtn.click();

    // Step 6: Click on 'View Cart' button
    WebElement viewCartBtn = wait.until(
            ExpectedConditions.elementToBeClickable(By.xpath("//u[normalize-space(text())='View Cart']"))
    );
    viewCartBtn.click();

    // Step 7: Verify that product is displayed in cart page
    List<WebElement> cartProducts = wait.until(
            ExpectedConditions.visibilityOfAllElementsLocatedBy(
                    By.xpath("//table[@id='cart_info_table']//td[@class='cart_description']/h4/a")
            )
    );

    Assert.assertTrue(cartProducts.size() > 0, "No products found in cart!");
    cartProducts.forEach(p -> System.out.println("Cart contains: " + p.getText()));
}
}
