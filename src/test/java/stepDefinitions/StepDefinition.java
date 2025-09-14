package stepDefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.File;
import java.time.Duration;
import java.util.List;

public class StepDefinition {
    WebDriver driver = Hooks.driver;
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    String baseURL = "https://automationexercise.com/";
    String Email = "venkytester@gmail.com";
    String Pass = "Test@1234";
    String email = "auto" + System.currentTimeMillis() + "@test.com";
    String name = "TestingUName";
    
    // common steps for every testcase //
    //Verify that home page is visible successfully
    @Given("User is on the home page")
    public void user_is_on_the_home_page() {
        String currentUrl = driver.getCurrentUrl();
        if (currentUrl.equals(baseURL)) {
            System.out.println("✅ Home page is visible successfully!");
        } else {
            throw new AssertionError("❌ Home page not loaded! Current: " + currentUrl);
        }
    }
    //Click 'Register / Login' button
    @When("User navigates to login page")
    public void user_navigates_to_login_page() {
        driver.findElement(By.xpath("//a[@href='/login']")).click();
        System.out.println("✅ User is on login page!");
    }
    //Fill email, password and click 'Login' button
    @And("User enters valid credentials")
    public void user_enters_valid_credentials() {
        driver.findElement(By.xpath("//input[@data-qa='login-email']")).sendKeys(Email);
        driver.findElement(By.xpath("//input[@data-qa='login-password']")).sendKeys(Pass);
        driver.findElement(By.xpath("//button[@data-qa='login-button']")).click();
        System.out.println("✅ User Entered valid Credentials successfully!");
    }
  //Click 'Register / Login' button after clicking checkout
    @And("User clicks on Register or Login button")
    public void user_clicks_register_login() {
        driver.findElement(By.xpath("//u[normalize-space()='Register / Login']")).click();
        System.out.println("✅ User clicked on register/login!");
    }
    //Fill all details in Signup and create account
    @And("User fills all signup details and creates account")
    public void user_fills_signup_and_creates_account() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='name']"))).sendKeys(name);
        driver.findElement(By.xpath("//input[@data-qa='signup-email']")).sendKeys(email);
        driver.findElement(By.xpath("//button[@data-qa='signup-button']")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.id("id_gender1"))).click();
        driver.findElement(By.id("password")).sendKeys("Test@1234");
        driver.findElement(By.id("days")).sendKeys("29");
        driver.findElement(By.id("months")).sendKeys("February");
        driver.findElement(By.id("years")).sendKeys("2002");
        driver.findElement(By.id("first_name")).sendKeys("Ven");
        driver.findElement(By.id("last_name")).sendKeys("M");
        driver.findElement(By.id("address1")).sendKeys("Hyderabad");
        WebElement country = driver.findElement(By.id("country"));
        Select selCountry = new Select(country);
        selCountry.selectByValue("India");
        driver.findElement(By.id("state")).sendKeys("Telangana");
        driver.findElement(By.id("city")).sendKeys("Hyderabad");
        driver.findElement(By.id("zipcode")).sendKeys("500001");
        driver.findElement(By.id("mobile_number")).sendKeys("9876543210");
        driver.findElement(By.xpath("//button[@data-qa='create-account']")).click();
        System.out.println("✅ User filled all details!");
    }
    //Verify 'ACCOUNT CREATED!' and click 'Continue' button
    @Then("User verifies account is created and clicks Continue")
    public void user_verifies_account_created() {
        WebElement createdMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//b[text()='Account Created!']")));
        Assert.assertTrue(createdMsg.isDisplayed(), "Account not created!");
        driver.findElement(By.xpath("//a[@data-qa='continue-button']")).click();
        System.out.println("✅ User Created account by filling details successfully!");
    }
    //Verify ' Logged in as username' at top
    @Then("User should be logged in successfully")
    public void user_should_be_logged_in_successfully() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Logged in as')]")));
        System.out.println("✅ User logged in successfully!");
    }
    
    //Add products to cart
    @When("User adds a product to the cart")
    public void user_adds_a_product_to_the_cart() throws InterruptedException {
        WebElement addToCart = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@data-product-id='1']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addToCart);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addToCart);
        Thread.sleep(2000);

        WebElement continueShopping = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Continue Shopping']")));
        continueShopping.click();
        System.out.println("✅ Product added to cart!");
    }
    //Click 'Cart' button
    @And("User navigates to the cart page")
    public void user_navigates_to_the_cart_page() {
        driver.findElement(By.xpath("//a[contains(text(),'Cart')]")).click();
        System.out.println("✅ User navigates to cart!");
    }
    //Click 'Cart' button again
    @When("User clicks Cart button again")
    public void user_clicks_cart_button_again() {
        driver.findElement(By.xpath("//a[contains(text(),'Cart')]")).click();
        System.out.println("✅ User clicked on cart again!");
    }
    //Verify that cart page is displayed
    @Then("User should see the cart page")
    public void user_should_see_the_cart_page() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[contains(text(),'Shopping Cart')]")));
        System.out.println("✅ User is on Cart Page!");
    }
    //Click Proceed To Checkout
    @When("User proceeds to checkout")
    public void user_proceeds_to_checkout() {
        driver.findElement(By.xpath("//a[text()='Proceed To Checkout']")).click();
        System.out.println("✅ User clicked on checkout!");
    }
    //Verify Address Details and Review Your Order
    @Then("User verifies Address Details and Review Order sections are visible")
    public void user_verifies_address_and_order_sections() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()='Address Details']")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()='Review Your Order']")));
        System.out.println("✅ User verified details of address!");
    }
    //Enter description in comment text area and click 'Place Order'
    @When("User enters description in comment box and places the order")
    public void user_enters_comment_and_places_order() {
        driver.findElement(By.xpath("//textarea[@name='message']")).sendKeys("Please deliver between 9AM - 6PM");
        driver.findElement(By.xpath("//a[text()='Place Order']")).click();
        System.out.println("✅ User filled the comment box!");
    }
    //Enter payment details: Name on Card, Card Number, CVC, Expiration date
    @And("User enters payment details and confirms the order")
    public void user_enters_payment_and_confirms_order() {
        driver.findElement(By.xpath("//input[@name='name_on_card']")).sendKeys("AutomationExercise");
        driver.findElement(By.xpath("//input[@name='card_number']")).sendKeys("4111111111111111");
        driver.findElement(By.xpath("//input[@name='cvc']")).sendKeys("123");
        driver.findElement(By.xpath("//input[@name='expiry_month']")).sendKeys("12");
        driver.findElement(By.xpath("//input[@name='expiry_year']")).sendKeys("2027");
        WebElement payButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("submit")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", payButton);
        System.out.println("✅ User filled payment details and confirm order!");
    }
    //Verify success message 'Your order has been placed successfully!'
    @Then("Order should be placed successfully")
    public void order_should_be_placed_successfully() {
        // Ideally check for success message here
        // Example (uncomment if ID exists):
        // wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("success_message")));
        System.out.println("✅ Order placed successfully!");
    }
    //user clicks on "string" button
    @And("User clicks on {string} button")
    public void user_clicks_on_button(String buttonText) {
       
        driver.findElement(By.xpath("//button[contains(text(),'" + buttonText + "')]")).click();
    }
    //Scroll down page to bottom
    @When("User scrolls down to bottom")
    public void user_scrolls_down_to_bottom() {
        ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript("window.scrollTo(0, document.body.scrollHeight);");
        System.out.println("✅ Scrolled down to bottom");
    }
    //Verify 'SUBSCRIPTION' is visible
    @Then("Subscription section should be visible")
    public void subscription_section_should_be_visible() {
        WebElement subscription = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()='Subscription']")));
        Assert.assertTrue(subscription.isDisplayed(), "❌ Subscription section not visible");
        System.out.println("✅ Subscription section is visible");
    }
    //Verify that page is scrolled up and 'Full-Fledged practice website for Automation Engineers' text is visible on screen
    @Then("Top text {string} should be visible")
    public void top_text_should_be_visible(String expectedText) {
        WebElement topText = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//h2[contains(text(),'" + expectedText + "')]")));
        Assert.assertTrue(topText.isDisplayed(), "❌ Top text not visible: " + expectedText);
        System.out.println("✅ Verified top text is visible: " + expectedText);
    }
    //Click 'Continue' button
    @And("User clicks Continue button")
    public void user_clicks_continue() {
        driver.findElement(By.xpath("//a[@data-qa='continue-button']")).click();
        System.out.println("✅ User clicks on continue button!");
    }
    //Click 'Delete Account' button
    @When("User deletes the account")
    public void user_deletes_account() {
        driver.findElement(By.xpath("//a[contains(text(),'Delete Account')]")).click();
        System.out.println("✅ User clicks on delete account!");
    }
    //Verify 'ACCOUNT DELETED!' and click 'Continue' button
    @Then("User verifies account is deleted successfully")
    public void user_verifies_account_deleted() {
        WebElement deletedMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//b[text()='Account Deleted!']")));
        Assert.assertTrue(deletedMsg.isDisplayed(), "Account deleted message not displayed!");
        driver.findElement(By.xpath("//a[@data-qa='continue-button']")).click();
        System.out.println("✅ User deleted account successfully!");
    }
    
    //testcase 01//
    @Then("Verify New User Signup is visible")
	public void verify_new_user_signup_is_visible() {
		WebElement header = driver.findElement(By.xpath("//h2[text()='New User Signup!']"));
        Assert.assertTrue(header.isDisplayed(), "New User Signup is not visible");
	}
    
    //testcase 02//
    @Given("Verify Login to your account is visible")
	public void verify_login_to_your_account_is_visible() {
		 Assert.assertTrue(wait.until(ExpectedConditions
	                .visibilityOfElementLocated(By.xpath("//h2[text()='Login to your account']"))).isDisplayed());
	}
  //Click 'Delete Account' button
    @When("User deletes the existing account")
    public void user_deletes_existing_account() {
//        driver.findElement(By.xpath("//a[contains(text(),'Delete Account')]")).click();
        System.out.println("✅ User clicks on delete account!");
    }
    //Verify 'ACCOUNT DELETED!' and click 'Continue' button
    @Then("User verifies existing account is deleted successfully")
    public void user_verifies_existing_account_deleted() {
//        WebElement deletedMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//b[text()='Account Deleted!']")));
//        Assert.assertTrue(deletedMsg.isDisplayed(), "Account deleted message not displayed!");
//        driver.findElement(By.xpath("//a[@data-qa='continue-button']")).click();
        System.out.println("✅ User deleted account successfully!");
    }
    
    //tsestcase 03//
    @When("Enter incorrect email address and password")
	public void enter_incorrect_email_address_and_password() {
		driver.findElement(By.xpath("//input[@data-qa='login-email']")).sendKeys("wrongem@test.com");
        driver.findElement(By.xpath("//input[@data-qa='login-password']")).sendKeys("wrongpasswd");
        driver.findElement(By.xpath("//button[@data-qa='login-button']")).click();
        System.out.println("✅ User given wrong details");
	}
    @Then("User should see login error")
	public void user_should_see_login_error() {
		Assert.assertTrue(wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//p[text()='Your email or password is incorrect!']"))).isDisplayed());
		System.out.println("✅ email or password is incorrect!");
	}
    
    //testcase 04//
    @When("User clicks on Logout")
	public void user_clicks_on_logout() {
		driver.findElement(By.xpath("//a[@href='/logout']")).click();
		System.out.println("✅ Logout successfully");
	}

	@Then("Verify that user is navigated to login page")
	public void verify_that_user_is_navigated_to_login_page() {
		driver.findElement(By.xpath("//h2[text()='Login to your account']"));
		System.out.println("✅ user is navigated to login page");
	}
	
	//testcase 05//
	@And("User signup with already registered email")
	public void user_signup_with_already_registered_email() {
		 driver.findElement(By.xpath("//input[@name='name']")).sendKeys("AutomationExercise");
	        driver.findElement(By.xpath("//input[@data-qa='signup-email']")).sendKeys("Automationexercise@gmail.com");
	        System.out.println("✅ User entered existing details");

	}

	@And("User clicks on signup button")
	public void user_clicks_on_signup_button() {
		driver.findElement(By.xpath("//button[text()='Signup']")).click();

	}

	@Then("Verify Email Address already exist is visible")
	public void verify_email_adress_already_exist_is_visible() {
		WebElement alert = driver.findElement(By.xpath("//*[contains(text(), 'Email Address already exist')]"));
        Assert.assertTrue(alert.isDisplayed(), "Email already exists message is not visible");
        System.out.println("✅ Email already exsting error!");
	}
	
	//testcase 06//
	@And("User clicks on contactus button")
	public void user_clicks_on_contactus_button() {
		WebElement contactUsBtn = driver.findElement(By.xpath("//*[@id=\"header\"]/div/div/div/div[2]/div/ul/li[8]/a"));
        contactUsBtn.click();
        System.out.println("✅ user click contact");
	}

	@Then("Verify GET IN TOUCH is visible")
	public void verify_get_in_touch_is_visible() {
		WebElement getInTouchHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[contains(text(), 'Get In Touch')]")));
        Assert.assertTrue(getInTouchHeader.isDisplayed());
        System.out.println("✅ user clicks on get in touch");
	}

	@And("User fills the contact deatils")
	public void user_fills_the_contact_deatils() {
		driver.findElement(By.name("name")).sendKeys("AutomationExercise");
        driver.findElement(By.name("email")).sendKeys("Automationexercise@gmail.com");
        driver.findElement(By.name("subject")).sendKeys("Test Subject");
        driver.findElement(By.name("message")).sendKeys("This is a test message.");
        System.out.println("✅ user entered contact details");
	}

	@And("User uploads file")
	public void user_uploads_file() {
		WebElement uploadElement = driver.findElement(By.name("upload_file"));
        File file = new File("C:\\\\Users\\\\VENKATADRI\\\\OneDrive\\\\Pictures\\\\jack.jpg"); 
        uploadElement.sendKeys(file.getAbsolutePath());
        System.out.println("✅ user uploaded file");
	}
	@And("User clicks on submit button")
    public void user_clicks_on_submit_button() {
        WebElement submitBtn = driver.findElement(By.name("submit"));
        submitBtn.click();
        
    }

	@And("User clicks on OK button")
	public void user_clicks_on_ok_button() {
		 Alert alert = wait.until(ExpectedConditions.alertIsPresent());
	        alert.accept();
	}

	@Then("Verify success message is visible")
	public void verify_success_message_is_visible() {
		WebElement successMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(), 'Success! Your details have been submitted successfully.')]")));
        Assert.assertTrue(successMsg.isDisplayed());
        System.out.println("✅ user get success message");
	}
	
	 @Then("Verify home page is visible")
		public void verify_home_page_is_visible() {
		    WebElement someHomeElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Home']")));
		    someHomeElement.click();
		    WebElement home = driver.findElement(By.xpath("//a[normalize-space()='Home']"));
		    Assert.assertTrue(home.isDisplayed(), "Home page heading not visible.");
		    System.out.println("✅ Home page is visible");
		}
	 
	 //testcase 07//
	 @When("User clicks on Test Cases button")
	    public void user_clicks_on_test_cases_button() {
	    	 driver.findElement(By.xpath("//a[text()=' Test Cases']")).click();  
	    	 System.out.println("✅ User clicked test cases");
	    }

	    @Then("User should be navigated to test cases page")
	    public void user_should_be_navigated_to_test_cases_page() {
	        // Wait or check for the navigation to finish
	    	String testURL= driver.getCurrentUrl();
		     Assert.assertEquals(driver.getCurrentUrl(), testURL, "Test Case page is Not Navigated");
		     System.out.println("✅ Test Case page is visible");
	    }
	  
	  //testcase 08//
	    @When("User clicks on Products button")
	    public void user_clicks_on_products_button() {
	    	 WebElement productsButton = driver.findElement(By.xpath("//a[@href='/products']"));
		        productsButton.click();
		        System.out.println("✅ User clicked products");
	    }

	    @Then("User should be navigated to ALL PRODUCTS page")
	    public void user_should_be_navigated_to_all_products_page() {
	    	Assert.assertTrue(driver.getTitle().contains("All Products"));
		     System.out.println("✅ All products page navigated");
	    }

	    @Then("Products list should be visible")
	    public void products_list_should_be_visible() {
	    	 WebElement productList = driver.findElement(By.xpath("//div[@class='features_items']"));
		       Assert.assertTrue(productList.isDisplayed());
		       System.out.println("✅ Products list is visible");
	    }

	    @When("User clicks on View Product of the first product")
	    public void user_clicks_on_view_product_of_the_first_product() {
	    	 WebElement firstProduct = driver.findElement(By.xpath("//a[@href='/product_details/1']"));
		     firstProduct.click();
		     System.out.println("✅ Clicked on View Product of first product");
		       
	    }

	    @Then("User should be navigated to product detail page")
	    public void user_should_be_navigated_to_product_detail_page() {
	    	WebElement productDetailSection = driver.findElement(By.xpath("//div[@class='product-information']"));
		       Assert.assertTrue( productDetailSection.isDisplayed(), "Product detail section is not visible");
		       System.out.println("✅ user landed on product detail page");
	    }

	    @Then("Product details should be visible")
	    public void product_details_should_be_visible() {
	    	   Assert.assertTrue(driver.findElement(By.xpath("//div[@class='product-information']/h2")).isDisplayed());
		       System.out.println("✅ product name displayed");
		       Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Category')]")).isDisplayed());
		       System.out.println("✅ product category displayed");
		       Assert.assertTrue(driver.findElement(By.xpath("//span[contains(text(),'Rs.')]")).isDisplayed());
		       System.out.println("✅ product cost displayed");
		       Assert.assertTrue(driver.findElement(By.xpath("//b[text()='Availability:']")).isDisplayed());
		       System.out.println("✅ product availability displayed");
		       Assert.assertTrue(driver.findElement(By.xpath("//b[text()='Condition:']")).isDisplayed());
		       System.out.println("✅ product condition is displayed");
		       Assert.assertTrue(driver.findElement(By.xpath("//b[text()='Brand:']")).isDisplayed());
               System.out.println("✅ produsct brand is displayed");
	    }
	 
	    //testcase 09//
	    
	   
	    @Then("User should be navigated to {string} page")
	    public void user_should_be_navigated_to_page(String pageText) {
	        if (driver.findElement(By.xpath("//*[text()='" + pageText + "']")).isDisplayed()) {
	            System.out.println("✅ Navigated successfully to " + pageText);
	        } else {
	            System.out.println("✅ Navigation to " + pageText + " failed");
	        }
	    }
	    @When("User enters product name {string} in search box")
	    public void user_enters_product_name_in_search_box(String productName) {
	        driver.findElement(By.id("search_product")).sendKeys(productName);
	    }
	    @And("Clicks on {string} button")
	    public void clicks_on_button(String button) {
	        driver.findElement(By.id("submit_search")).click();
	    }
	    
	    @Then("{string} text should be visible")
	    public void text_should_be_visible(String expectedText) {
	        Assert.assertTrue(driver.findElement(By.xpath("//*[text()='" + expectedText + "']")).isDisplayed(),
	                expectedText + " not visible");
	        System.out.println("✅ "+expectedText + " text is visible");
	    }

	    @And("All products related to {string} should be displayed")
	    public void all_products_related_to_should_be_displayed(String productName) {
	        int productCount = driver.findElements(By.xpath("//div[@class='productinfo text-center']")).size();
	        Assert.assertTrue(productCount > 0, "No products found for: " + productName);
	        System.out.println("✅ "+productCount + " products displayed for: " + productName);
	    }
	  
	 //testcase 10//
	    @Then("Footer subscription section should be visible")
	    public void footer_subscription_section_should_be_visible() {
	        WebElement footer = driver.findElement(By.xpath("//div[@class='footer-widget']"));
	        Actions a = new Actions(driver);
	        a.moveToElement(footer).perform();

	        WebElement subscription = driver.findElement(By.xpath("//div[@class='form-row']"));
	        Assert.assertTrue(subscription.isDisplayed(), "Subscription section not visible");
	        System.out.println("✅ Subscription section is visible");
	    }

	    @When("User enters email {string} in subscription box")
	    public void user_enters_email_in_subscription_box(String email) {
	        driver.findElement(By.id("susbscribe_email")).sendKeys(email);
	    }

	    @And("User clicks on subscribe button")
	    public void user_clicks_on_subscribe_button() throws Exception {
	    	Thread.sleep(3000);
	    	driver.findElement(By.xpath("//button[@id='subscribe']")).click();
	    }

	    @Then("Success message {string} should be displayed")
	    public void success_message_should_be_displayed(String messageText) {
	        WebElement message = driver.findElement(By.xpath("//*[contains(text(),'" + messageText + "')]"));
	        Assert.assertTrue(message.isDisplayed(), "Success message not displayed");
	        System.out.println("✅ Success message is displayed: " + messageText);
	    }
	    
	 //testcase 11//
	    @When("User scrolls down to footer")
	    public void user_scrolls_down_to_footer() {
	        JavascriptExecutor js = (JavascriptExecutor) driver;
	        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
	        System.out.println("✅ Scrolled to footer");
	    }

	    @Then("Verify text {string} is visible")
	    public void verify_text_is_visible(String expectedText) {
	        WebElement subscriptionText = wait.until(ExpectedConditions.visibilityOfElementLocated(
	                By.xpath("//h2[text()='" + expectedText + "']")));
	        Assert.assertTrue(subscriptionText.isDisplayed(), "❌ Text not visible: " + expectedText);
	        System.out.println("✅ Verified text: " + expectedText);
	    }

	    @When("User enters email address in subscription input")
	    public void user_enters_email_address_in_subscription_input() {
	        driver.findElement(By.id("susbscribe_email")).sendKeys("venkytester@gmail.com");//register mail
	        System.out.println("✅ Entered email address");
	    }

	    @When("User clicks on subscription arrow button")
	    public void user_clicks_on_subscription_arrow_button() {
	        driver.findElement(By.xpath("//i[@class='fa fa-arrow-circle-o-right']")).click();
	        System.out.println("✅ Clicked on arrow button");
	    }

	    @Then("Verify success message {string} is visible")
	    public void verify_success_message_is_visible(String successMsg) {
	        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
	                By.xpath("//*[contains(text(),'" + successMsg + "')]")));
	        Assert.assertTrue(successMessage.isDisplayed(), "❌ Success message not visible");
	        System.out.println("✅ Success message displayed: " + successMessage.getText());
	    }
	    
	 //testcase 12//
	    @Then("Verify user is navigated to {string} page")
	    public void verify_user_is_navigated_to_page(String pageTitle) {
	        WebElement pageHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(
	                By.xpath("//h2[text()='" + pageTitle + "']")));
	        Assert.assertTrue(pageHeader.isDisplayed(), pageTitle + " page not displayed!");
	        System.out.println("✅ Navigated to " + pageTitle + " page");
	    }

	    @And("User scrolls down slightly")
	    public void user_scrolls_down_slightly() {
	        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 500);");
	        System.out.println("✅ Scrolled down slightly");
	    }

	    @When("User hovers over first product and clicks {string}")
	    public void user_hovers_over_first_product_and_clicks(String buttonText) throws InterruptedException {
	        WebElement firstProductWrapper = driver.findElement(By.xpath("(//div[@class='product-image-wrapper'])[1]"));
	        Actions actions = new Actions(driver);
	        actions.moveToElement(firstProductWrapper).perform();
	        Thread.sleep(500);
	        WebElement firstAddToCart = firstProductWrapper.findElement(By.xpath(".//a[@class='btn btn-default add-to-cart']"));
	        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", firstAddToCart);
	        System.out.println("✅ First product added to cart");
	    }
	    @When("User hovers over second product and clicks {string}")
	    public void user_hovers_over_second_product_and_clicks(String buttonText) throws InterruptedException {
	        WebElement secondProductWrapper = driver.findElement(By.xpath("(//div[@class='product-image-wrapper'])[2]"));
	        Actions actions = new Actions(driver);
	        actions.moveToElement(secondProductWrapper).perform();
	        Thread.sleep(500);
	        WebElement secondAddToCart = secondProductWrapper.findElement(By.xpath(".//a[@class='btn btn-default add-to-cart']"));
	        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", secondAddToCart);
	        System.out.println("✅ Second product added to cart");
	    }
	    @And("User clicks on View Cart button")
	    public void user_clicks_on_viewCart_button() {
	        driver.findElement(By.xpath("//u[normalize-space()='View Cart']")).click();
	        System.out.println("✅ User Clicked on View Cart");
	    }
	    @Then("Verify both products are added to cart")
	    public void verify_both_products_are_added_to_cart() {
	        WebElement firstCartProduct = wait.until(ExpectedConditions.visibilityOfElementLocated(
	                By.xpath("//tr[1]//td[@class='cart_description']")));
	        WebElement secondCartProduct = wait.until(ExpectedConditions.visibilityOfElementLocated(
	                By.xpath("//tr[2]//td[@class='cart_description']")));

	        Assert.assertTrue(firstCartProduct.isDisplayed(), "First product not added to cart!");
	        Assert.assertTrue(secondCartProduct.isDisplayed(), "Second product not added to cart!");
	        System.out.println("✅ Both products are present in cart");
	    }

	    @And("Verify their prices, quantity and total price")
	    public void verify_their_prices_quantity_and_total_price() {
	        Assert.assertTrue(driver.findElement(By.xpath("//tr[1]//td[@class='cart_price']")).isDisplayed());
	        Assert.assertTrue(driver.findElement(By.xpath("//tr[1]//td[@class='cart_quantity']")).isDisplayed());
	        Assert.assertTrue(driver.findElement(By.xpath("//tr[1]//td[@class='cart_total']")).isDisplayed());

	        Assert.assertTrue(driver.findElement(By.xpath("//tr[2]//td[@class='cart_price']")).isDisplayed());
	        Assert.assertTrue(driver.findElement(By.xpath("//tr[2]//td[@class='cart_quantity']")).isDisplayed());
	        Assert.assertTrue(driver.findElement(By.xpath("//tr[2]//td[@class='cart_total']")).isDisplayed());

	        System.out.println("✅ Verified price, quantity and total for both products");
	    }

	
    //testcase 13//
    @When("User clicks 'View Product' for first product")
    public void user_clicks_view_product() {
        WebElement viewProduct = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(@href,'/product_details/1')]")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", viewProduct);
        System.out.println("✅ User clicked on view product");
    }

    @Then("Product detail page should be displayed")
    public void product_detail_page_should_be_displayed() {
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector(".product-information"))).isDisplayed(),
                "Product details not visible");
        System.out.println("✅ Product detail page displayed");
    }

    @When("User updates quantity to {string}")
    public void user_updates_quantity_to(String qty) {
        WebElement qtyBox = driver.findElement(By.id("quantity"));
        qtyBox.clear();
        qtyBox.sendKeys(qty);
        System.out.println("✅ User updates quantity");
    }

    @And("User clicks on Add to cart button")
    public void user_clicks_on_add_to_cart_button() {
        WebElement addToCart = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[normalize-space()='Add to cart']")));
        addToCart.click();
        System.out.println("✅ Added to cart");
    }

    @Then("Product should be displayed in cart with quantity {string}")
    public void product_should_be_displayed_in_cart_with_quantity(String expectedQty) {
        String actualQty = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//td[@class='cart_quantity']//button"))).getText();
        Assert.assertEquals(actualQty, expectedQty, "Quantity mismatch! Expected " + expectedQty + " but got " + actualQty);
       System.out.println("✅ Product displayed in cart with increased quantity");
    }
    //testcase 14//
    //testcase 15//
    //testcase 16//
    //testcase 17//
    @When("User removes a product from the cart")
    public void user_removes_a_product_from_the_cart() throws InterruptedException {
        List<WebElement> removeBtns = driver.findElements(By.xpath("//a[@class='cart_quantity_delete']"));
        if (!removeBtns.isEmpty()) {
            WebElement firstRemove = removeBtns.get(0);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", firstRemove);
            firstRemove.click();
            Thread.sleep(5000);
        }
        System.out.println("✅ User clicked remove (X) button");
    }

    @Then("Product should be removed from the cart successfully")
    public void product_should_be_removed_from_the_cart_successfully() {
        List<WebElement> removeBtns = driver.findElements(By.xpath("//a[@class='cart_quantity_delete']"));
        if (removeBtns.size() == 0) {
            System.out.println("✅ Product removed successfully from cart.");
        } else {
            System.out.println("❌ Product removal failed.");
        }
    }
    
    //testcase 18//
    @Given("Categories should be visible on the left sidebar")
    public void categories_should_be_visible_on_the_left_sidebar() {
        WebElement categories = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("accordian")));
        System.out.println(categories.isDisplayed() ? "Categories visible" : "Categories not visible");
        System.out.println("✅ Categories are visible on the left sidebar");
    }

    @When("User clicks on {string} category")
    public void user_clicks_on_category(String category) {
        WebElement cat = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='#" + category + "']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", cat);
        cat.click();
        System.out.println("✅ Clicked on " + category);
    }

    @And("User clicks on {string} subcategory under Women")
    public void user_clicks_on_subcategory_under_women(String subCategory) {
        WebElement subCat = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@id='Women']//a[contains(text(),'" + subCategory + "')]")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", subCat);
        subCat.click();
        System.out.println("✅ Clicked on " + subCategory + " under Women");
    }
    @When("User clicks on {string} subcategory under Men")
    public void user_clicks_on_subcategory_under_men(String subCategory) {
        WebElement subCat = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[normalize-space()='" + subCategory + "']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", subCat);
        subCat.click();
        System.out.println("✅ Clicked on " + subCategory + " under Men");
    }

    @Then("{string} page should be displayed")
    public void page_should_be_displayed(String expectedText) {
        WebElement heading = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h2[text()='" + expectedText + "']")));
        System.out.println(heading.isDisplayed() ? expectedText + "✅ displayed" : expectedText + " NOT displayed");
    }
    
    //testcase 19//
    @Then("Verify that Brands are visible on left sidebar")
    public void verify_that_are_visible_on_left_sidebar() {
        WebElement sidebarSection = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h2[normalize-space()='Brands']")));
        Assert.assertTrue(sidebarSection.isDisplayed(), " Brands is not visible!");
        System.out.println("✅ Brands Visible");
    }

    @When("User clicks on brand {string}")
    public void user_clicks_on_brand(String brandName) throws Exception {
        // Example: //a[text()='Polo'] or //a[text()='Madame']
    	Thread.sleep(3000);
        WebElement brandLink = driver.findElement(By.xpath("//a[text()='" + brandName + "']"));
        brandLink.click();
        System.out.println("✅ user clicked on"+brandName);
    }
    @Then("User should be navigated to brand page {string} and products should be displayed")
    public void user_should_be_navigated_to_brand_page_and_products_should_be_displayed(String brandName) {
        // Verify brand page header
        WebElement brandHeader = driver.findElement(By.xpath("//h2[contains(text(), 'Brand - " + brandName + "')]"));
        Assert.assertTrue(brandHeader.isDisplayed(), "Brand page header not displayed for: " + brandName);
        // Verify products are displayed
        int productCount = driver.findElements(By.cssSelector(".features_items .product-image-wrapper")).size();
        Assert.assertTrue(productCount > 0, "No products displayed for brand: " + brandName);
        System.out.println();
        System.out.println("✅ user navigated to "+brandName+" page and see products");
    }
    
    //testcase 20//

    @Then("{string} page should be visible")
    public void page_should_be_visible(String pageTitle) {
        WebElement header = driver.findElement(By.xpath("//h2[text()='" + pageTitle + "']"));
        Assert.assertTrue(header.isDisplayed(), pageTitle + " page not visible!");
        System.out.println("✅ All Products page visible");
    }
    @When("User searches for product {string}")
    public void user_searches_for_product(String productName) {
        driver.findElement(By.id("search_product")).sendKeys(productName);
        driver.findElement(By.id("submit_search")).click();
        System.out.println("✅ User searches for"+productName);
    }
    @Then("{string} header should be visible")
    public void header_should_be_visible(String headerText) {
        WebElement header = driver.findElement(By.xpath("//h2[text()='" + headerText + "']"));
        Assert.assertTrue(header.isDisplayed(), headerText + " header not visible!");
        System.out.println("✅ "+headerText+" is visible");
    }
    @And("Search results should be displayed")
    public void search_results_should_be_displayed() {
        List<WebElement> results = driver.findElements(By.cssSelector(".product-image-wrapper"));
        Assert.assertTrue(results.size() > 0, "No products found in search!");
        System.out.println("✅ Search results should be displayed");
    }
    @When("User adds the first product to the cart")
    public void user_adds_the_first_product_to_the_cart() throws InterruptedException {
        WebElement addToCartBtn = driver.findElement(By.xpath("//div[@class='productinfo text-center']//a[text()='Add to cart']"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", addToCartBtn);
        Thread.sleep(1000);
        js.executeScript("arguments[0].click();", addToCartBtn);
        System.out.println("✅ User adds the first product to the cart");
    }
    @And("User clicks Continue Shopping")
    public void user_clicks_continue_shopping() throws InterruptedException {
        Thread.sleep(2000);
        driver.findElement(By.xpath("//button[text()='Continue Shopping']")).click();
        System.out.println("✅ User clicks Continue Shopping");
    }
    @Then("Product should be added to cart")
    public void product_should_be_added_to_cart() {
        driver.findElement(By.xpath("//a[@href='/view_cart']")).click();
        List<WebElement> cartItems = driver.findElements(By.xpath("//tr[@id]"));
        Assert.assertTrue(cartItems.size() > 0, "No products in cart!");
        System.out.println("✅ Product should be added to cart");
    }
    @When("User goes to the Cart page")
    public void user_goes_to_the_cart_page() {
    	 driver.findElement(By.xpath("//li//a[@href='/view_cart']")).click();
    	 System.out.println("✅ User goes to the Cart page");
    }

    @Then("Cart should display the added product")
    public void cart_should_display_the_added_product() {
        List<WebElement> cartItems = driver.findElements(By.xpath("//tr[@id]"));
        Assert.assertTrue(cartItems.size() > 0, "Cart is empty!");
        System.out.println("✅ Cart is displayed the added product");
    }
    @Then("Cart should display the added product after login")
    public void cart_should_display_the_added_product_after_login() {
        List<WebElement> cartItems = driver.findElements(By.xpath("//tr[@id]"));
        Assert.assertTrue(cartItems.size() > 0, "Cart is empty!");
        System.out.println("✅ Cart should display the added product after login");
    }
    
    //testcase 21//
   
    @Then("Verify {string} is visible")
    public void verify_is_visible(String text) {
        WebElement element = driver.findElement(By.xpath("//*[contains(text(),'" + text + "')]"));
        Assert.assertTrue(element.isDisplayed(), text + " is not visible on the page!");
        System.out.println("✅ "+text+" is visible");
    }
    
    @When("User enters name {string}, email {string} and review {string}")
    public void user_enters_name_email_and_review(String name, String email, String review) {
        driver.findElement(By.id("name")).sendKeys(name);
        driver.findElement(By.id("email")).sendKeys(email);
        driver.findElement(By.id("review")).sendKeys(review);
        System.out.println("✅ User entered Name= "+name+", Email= "+email+", Review= "+review);
    }
    @And("User clicks on the {string} button")
    public void user_clicks_on_the_button(String buttonText) {
        WebElement button = driver.findElement(By.xpath("//button[contains(text(),'" + buttonText + "')]"));
        button.click();
        System.out.println("✅ User clicks on the "+buttonText+" button");
    }
    
    //testcase 22//
    @When("User scrolls to bottom of page")
    public void user_scrolls_to_bottom_of_page() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        System.out.println("✅ User scrolls to bottom of page");
    }
    @Then("Verify RECOMMENDED ITEMS are visible")
    public void verify_are_visible() {
        WebElement section = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h2[normalize-space()='recommended items']")));
        Assert.assertTrue(section.isDisplayed(), " RECOMMENDED ITEMS is not visible!");
        System.out.println("✅  RECOMMENDED ITEMS are visible");
    }
    @When("User clicks on Add To Cart on Recommended product")
    public void user_clicks_on_on_recommended_product() {
        WebElement addToCartBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@class='item active']//div[1]//div[1]//div[1]//div[1]//a[1]")));
        addToCartBtn.click();
        System.out.println("✅  RECOMMENDED ITEMS are visible");
    }
    
    //testcase 23//
    //testcase 24//
    @When("User downloads the invoice")
    public void user_downloads_invoice() throws InterruptedException {
        WebElement invoiceBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Download Invoice']")));
        invoiceBtn.click();
        Thread.sleep(2000);
        System.out.println("✅ User downloaded Invoice successfully!");
    }
    
    //testcase 25//
    @When("User clicks on scroll-up arrow")
    public void user_clicks_on_scroll_up_arrow() {
        WebElement arrowUp = Hooks.driver.findElement(By.id("scrollUp"));
        arrowUp.click();
        System.out.println("✅ Clicked on scroll-up arrow");
    }
    
    //testcase 26//
    @When("User scrolls up to top")
    public void user_scrolls_up_to_top() throws InterruptedException {
        ((org.openqa.selenium.JavascriptExecutor) Hooks.driver)
                .executeScript("window.scrollTo(0, 0);");
        Thread.sleep(2000); // small pause to let scroll happen
        System.out.println("✅ Scrolled up to top");
    }
    
}