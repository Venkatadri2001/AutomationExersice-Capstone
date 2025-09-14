Feature: Automation Exersice features  

#TestCase1
Scenario: SignUp
Given User is on the home page
When User navigates to login page
Then Verify New User Signup is visible
And User fills all signup details and creates account
Then User verifies account is created and clicks Continue
Then User should be logged in successfully
When User deletes the account
Then User verifies account is deleted successfully

#TestCase2 
Scenario: Login user successfully with correct credentials  
Given User is on the home page
When User navigates to login page
And Verify Login to your account is visible
And User enters valid credentials
Then User should be logged in successfully
When User deletes the existing account 
Then User verifies existing account is deleted successfully

#TestCase3    
Scenario: Login user unsuccessfully with incorrect credentials 
Given User is on the home page
When User navigates to login page
And Verify Login to your account is visible
When Enter incorrect email address and password
Then User should see login error

#TestCase4 
Scenario: Logout user successfully
Given User is on the home page
When User navigates to login page
And Verify Login to your account is visible
And User enters valid credentials
Then User should be logged in successfully
When User clicks on Logout
Then Verify that user is navigated to login page

#TestCase5
Scenario: Register with an already registered email address
Given User is on the home page
When User navigates to login page
Then Verify New User Signup is visible
And User signup with already registered email
And User clicks on signup button
Then Verify Email Address already exist is visible

#TestCase6
Scenario: Submit Contact Us form with valid data
Given User is on the home page
And User clicks on contactus button
Then Verify GET IN TOUCH is visible
And User fills the contact deatils
And User uploads file
And User clicks on submit button
And User clicks on OK button
Then Verify success message is visible
Then Verify home page is visible

#TestCase7
Scenario: User navigates to Test Cases page
Given User is on the home page
When User clicks on Test Cases button
Then User should be navigated to test cases page

#TestCase8
Scenario: View product details from All Products page
Given User is on the home page
When User clicks on Products button
Then User should be navigated to ALL PRODUCTS page
And Products list should be visible
When User clicks 'View Product' for first product
Then User should be navigated to product detail page
And Product details should be visible

#TestCase9
Scenario: User searches for a product
Given  User is on the home page
When User clicks on Products button
Then User should be navigated to "All Products" page
When User enters product name "Tshirt" in search box
And Clicks on "Search" button
Then "Searched Products" text should be visible
And All products related to "Tshirt" should be displayed

#TestCase10
Scenario: User subscribes to newsletter
Given  User is on the home page
Then Footer subscription section should be visible
When User enters email "venkytester@gmail.com" in subscription box
And User clicks on subscribe button
Then Success message "You have been successfully subscribed!" should be displayed

#TestCase11
Scenario: User subscribes to newsletter from cart page
Given User is on the home page
And User navigates to the cart page
And User scrolls down to footer
Then Verify text "Subscription" is visible
When User enters email address in subscription input
And User clicks on subscription arrow button
Then Verify success message "You have been successfully subscribed!" is visible

#TestCase12
Scenario: User adds two products to cart and verifies their details
Given User is on the home page
When User clicks on Products button
Then User should be navigated to "All Products" page
And User scrolls down slightly
When User hovers over first product and clicks "Add to cart"
And User clicks on "Continue Shopping" button
When User hovers over second product and clicks "Add to cart"
And User clicks on View Cart button
Then Verify both products are added to cart
And Verify their prices, quantity and total price

#TestCase13
Scenario: User verifies product quantity in cart
Given User is on the home page
When User clicks 'View Product' for first product
Then Product detail page should be displayed
When User updates quantity to "4"
And User clicks on Add to cart button
And User clicks on View Cart button
Then Product should be displayed in cart with quantity "4"

#TestCase14
Scenario: User places an order and register while checkout
Given User is on the home page
When User adds a product to the cart
And User navigates to the cart page
Then User should see the cart page
When User proceeds to checkout
And User clicks on Register or Login button
And User fills all signup details and creates account
Then User verifies account is created and clicks Continue
Then User should be logged in successfully
When User clicks Cart button again
When User proceeds to checkout
Then User verifies Address Details and Review Order sections are visible
When User enters description in comment box and places the order
And User enters payment details and confirms the order
Then Order should be placed successfully
And User deletes the account
Then User verifies account is deleted successfully

#TestCase15
Scenario: User places an order and register before checkout
Given User is on the home page
When User navigates to login page
And User fills all signup details and creates account
Then User verifies account is created and clicks Continue
Then User should be logged in successfully
When User adds a product to the cart
And User navigates to the cart page
Then User should see the cart page
When User proceeds to checkout
Then User verifies Address Details and Review Order sections are visible
When User enters description in comment box and places the order
And User enters payment details and confirms the order
Then Order should be placed successfully
And User deletes the account
Then User verifies account is deleted successfully

#TestCase16
Scenario: User places an order after logging in
Given User is on the home page
When User navigates to login page
And User enters valid credentials
Then User should be logged in successfully
When User adds a product to the cart
And User navigates to the cart page
Then User should see the cart page
When User proceeds to checkout
Then User verifies Address Details and Review Order sections are visible
When User enters description in comment box and places the order
And User enters payment details and confirms the order
Then Order should be placed successfully
When User deletes the existing account 
Then User verifies existing account is deleted successfully

#TestCase17
Scenario: Add products to cart and remove them
Given User is on the home page
When User adds a product to the cart
And User navigates to the cart page
Then User should see the cart page
When User removes a product from the cart
Then Product should be removed from the cart successfully

#TestCase18
Scenario: Verify categories and subcategories
Given Categories should be visible on the left sidebar
When User clicks on "Women" category
And User clicks on "Tops" subcategory under Women
Then "Women - Tops Products" page should be displayed
When User clicks on "Men" category
And User clicks on "Tshirts" subcategory under Men
Then "Men - Tshirts Products" page should be displayed

#TestCase19
Scenario: Verify user can view and navigate brand products
When User clicks on Products button
Then Verify that Brands are visible on left sidebar
When User clicks on brand "Polo"
Then User should be navigated to brand page "Polo" and products should be displayed
When User clicks on brand "Madame"
Then User should be navigated to brand page "Madame" and products should be displayed

#TestCase20
Scenario: User searches for a product, adds it to cart and verifies after login
When User clicks on Products button
Then "All Products" page should be visible
When User searches for product "Dress"
Then "Searched Products" header should be visible
And Search results should be displayed
When User adds the first product to the cart
And User clicks Continue Shopping
Then Product should be added to cart
When User goes to the Cart page
Then Cart should display the added product
When User navigates to login page
And User enters valid credentials
When User goes to the Cart page
Then Cart should display the added product after login

#TestCase21
Scenario: User writes a review on a product
When User clicks on Products button
Then User should be navigated to "All Products" page
When User clicks 'View Product' for first product
Then Verify "Write Your Review" is visible
When User enters name "John Doe", email "john@example.com" and review "Great product, highly recommend!"
And User clicks on the "Submit" button
Then Verify success message "Thank you for your review." is visible

#TestCase22
Scenario: Add recommended product to cart
When User scrolls to bottom of page
Then Verify RECOMMENDED ITEMS are visible
When User clicks on Add To Cart on Recommended product
And User clicks on View Cart button   
Then Cart should display the added product

#TestCase23
Scenario: verfiy test address in CHeck Out Page
Given User is on the home page
When User navigates to login page
And User fills all signup details and creates account
Then User verifies account is created and clicks Continue
Then User should be logged in successfully
When User adds a product to the cart
And User navigates to the cart page
Then User should see the cart page
When User proceeds to checkout
Then User verifies Address Details and Review Order sections are visible
And User deletes the account
Then User verifies account is deleted successfully

#TestCase24
Scenario: User purchases a product and downloads the invoice
Given User is on the home page
When User adds a product to the cart
And User navigates to the cart page
Then User should see the cart page
When User proceeds to checkout
And User clicks on Register or Login button
And User fills all signup details and creates account
Then User verifies account is created and clicks Continue
Then User should be logged in successfully
When User clicks Cart button again
When User proceeds to checkout
Then User verifies Address Details and Review Order sections are visible
When User enters description in comment box and places the order
And User enters payment details and confirms the order
Then Order should be placed successfully
When User downloads the invoice
And User clicks Continue button
And User deletes the account
Then User verifies account is deleted successfully

#TestCase25
Scenario: Verify scroll down and up functionality on Automation Exercise
Given User is on the home page
When User scrolls down to bottom
Then Subscription section should be visible
When User clicks on scroll-up arrow
Then Top text "Full-Fledged practice website for Automation Engineers" should be visible 

#TestCase26
Scenario: Verify scroll down and up functionality on Automation Exercise
Given User is on the home page
When User scrolls down to bottom
Then Subscription section should be visible
When User scrolls up to top
Then Top text "Full-Fledged practice website for Automation Engineers" should be visible





