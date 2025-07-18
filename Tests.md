# Test 1: User log in
Steps:
1.	 User launches market place application.
2.	 User clicks the username textbox.
3.	 User enters username via the keyboard.
4.	 User clicks the password textbox.
5.	 User enters password via the keyboard
6.	 User clicks the "Log in" button.

Expected result: Application verifies the user's username and password and loads the correct home screen automatically. <br />
Test Status: Passed.

# Test 2: Create seller user account
Steps:
1.	 User launches market place application.
2.	 User clicks the Create Account button.
3.	 User enters username for the new account via the keyboard.
4.	 User chooses a password for the new account via password textbox.
5.	 User selects ‘Seller’ from the account type drop down
6.   User enters store name for the new account via the keyboard
7.   User clicks ‘Create Account’ button

Expected result: Application creates the new seller user account. Verify that the new account is created by logging into the market place application and checking that the seller home screen is displayed. <br />
Test Status: Passed.

# Test 3: Create customer user account
Steps:
1.	 User launches market place application.
2.	 User clicks the Create Account button.
3.	 User enters username for the new account via the keyboard.
4.	 User chooses a password for the new account via password textbox.
5.	 User selects ‘Customer’ from the account type drop down
6.	 User clicks ‘Create Account’ button

Expected result: Application creates the new customer user account. Verify that the new account is created by logging into the market place application and checking that the seller home screen is displayed. <br />
Test Status: Passed.

# Test 4: User log in invalid password
Steps:
1.	 User launches market place application.
2.	 User clicks the username textbox.
3.	 User enters username via the keyboard.
4.	 User clicks the password textbox.
5.	 User enters incorrect password via the keyboard
6.	 User clicks the "Log in" button.

Expected result: Application verifies the user's username and password and displays a message that says ‘Invalid login credentials’. <br />
Test Status: Passed.

# Test 5: User log in invalid username
Steps:
1.	 User launches market place application.
2.	 User clicks the username textbox.
3.	 User enters incorrect username via the keyboard.
4.	 User clicks the password textbox.
5.	 User enters password via the keyboard
6.	 User clicks the "Log in" button.

Expected result: Application verifies the user's username and password and displays a message that says ‘Please enter username and password’. <br />
Test Status: Passed.

# Test 6: User log in blank username and password
Steps:
1.	 User launches market place application.
2.	 User clicks the username textbox.
3.	 User leaves username field blank.
4.	 User leaves password field blank.
5.	 User clicks the "Log in" button.

Expected result: Application verifies the user's username and password and displays an error message. <br />
Test Status: Passed.

# Test 7: Multiple Users log in
Steps:
1.	 User launches market place application.
2.	 User clicks the username textbox.
3.	 User enters first username via the keyboard.
4.	 User clicks the password textbox.
5.	 User enters password via the keyboard
6.	 User clicks the "Log in" button.
7.	 User launches a second instance of market place application.
8.	 User clicks the username textbox.
9.	 User enters second username via the keyboard.
10.	 User clicks the password textbox.
11.	 User enters password via the keyboard
12.	 User clicks the "Log in" button.

Expected result: Application verifies the first user's username and password and loads the correct home screen automatically for the first instance of the application. And similarly second instance of application completes successful login and loads the correct home screen for the second user. <br />
Test Status: Passed.

# Test 8: Seller create product
Steps:
1.	 User launches market place application.
2.	 User logs in using seller account.
3.   From the home screen user selects the store they want to create a product for via dropdown menu.
4.   User clicks on continue button to continue to next screen.
5.   From the next screen click Create Product button to add a new product to the store.
6.   On Create product screen enter Flavor name, description, quantity and price.
7.   User clicks ‘Create’ button to list the new product.
8.   User clicks cancel to exit the 'create product' screen and goes back to the previous screen.

Expected result: Application creates a new product in the seller store. <br />
Test Status: Passed.

# Test 9: User can exit program
Steps:
1.	 User launches market place application.
2.	 User clicks exit button.
3.	 User exits the program and the program closes.

Expected result: Application shuts down after user clicks exit button. <br />
Test Status: Passed.

# Test 10: Seller edit product
Steps:
1.	 User launches market place application.
2.	 User logs in using seller account.
3.   From the home screen user selects the store they want to edit a product for via dropdown menu.
4.   User clicks on continue button to continue to next screen.
5.   From the next screen click Edit Product button to edit an existing product for the store.
6.   On Edit product screen enter the flavor name of the product they want to edit.
7.   User clicks ‘Edit’ button to edit the product.
8.   On next page user enters the new Flavor description, quantity, and price.
9.   User clicks on the 'Edit' button to save the changes and message is displayed saying 'Product edited successfully'
10.  User clicks the 'Ok' button and is redirected to the Edit product screen.
11.  User can either select another flavor to edit or click cancel to exit the 'edit product' screen and go back to the previous screen.

Expected result: Application edits an existing product in the seller store. <br />
Test Status: Passed.

# Test 11: Seller delete product
Steps:
1.	 User launches market place application.
2.	 User logs in using seller account.
3.   From the home screen user selects the store they want to delete a product from via dropdown menu.
4.   User clicks on continue button to continue to next screen.
5.   From the next screen click Delete Product button to delete an existing product from the store.
6.   On Delete product screen enter the flavor name of the product they want to delete.
7.   User clicks ‘Delete’ button to delete the product and message is displayed saying 'Product deleted successfully'
8.   User clicks the 'Ok' button and is redirected to the Delete product screen.
9.   User can either select another flavor to delete or click cancel to exit the 'delete product' screen and go back to the previous screen.

Expected result: Application deletes a product in the seller store. <br />
Test Status: Passed.

# Test 12: Seller import products
Steps:
1.	 User launches market place application.
2.	 User logs in using seller account.
3.   From the home screen user selects the store they want to import a product for via dropdown menu.
4.   User clicks on 'Continue' button to continue to next screen.
5.   From the next screen click Import products button to import products from a CSV file.
6.   User clicks 'Select File' button to import a file of new products to the store.
7.   User selects CSV-type file from their device and clicks on 'Select' button to chose file.
8.   On Import product screen click on 'Import' button to import selected file and product shows up on screen.
9.   A message is displayed saying "File imported successfully" and user clicks the "Ok" button being redirected to the import product screen.
10.  User can either select another file to import or click 'Back to Seller Menu' button to exit the 'import product' screen and go back to the previous screen.

Expected result: Application imports new products to the seller store. <br />
Test Status: Passed.

# Test 13: Seller export products
Steps:
1.	 User launches market place application.
2.	 User logs in using seller account.
3.   From the home screen user selects the store they want to export products for via dropdown menu.
4.   User clicks on 'Continue' button to continue to next screen.
5.   From the next screen click Export products button to export a CSV file to a folder.
6.   User clicks 'Select Folder' button to chose which folder to export the CSV of the store to.
7.   User either selects a folder from their device and clicks on 'Select' button to chose folder or clicks the New Folder to make a new folder.
8.   On Export product screen click on 'Export' button to export the store's CSV file to the selected folder.
9.   A message is displayed saying "File exported successfully" and user clicks the "Ok" button being redirected to the export product screen.
10.  User can either select another folder to export to or click 'Back to Seller Menu' button to exit the 'export product' screen and go back to the previous screen.

Expected result: Application exports a CSV file of products to folder on local device. <br />
Test Status: Passed.

# Test 14: Seller view sales report for store
Steps:
1.	 User launches market place application.
2.	 User logs in using seller account.
3.   From the home screen user selects the store they want to view a sales report for.
4.   User clicks on 'Continue' button to continue to next screen.
5.   From the next screen click View sales for this store button.
6.   View sales page displays all the sales for the store.
7.   User clicks 'Back to Seller Menu' button to exit the 'View sales' screen and go back to the previous screen.

Expected result: Application displays the sales for the seller store. <br />
Test Status: Passed.

# Test 15: Seller view Customer Report
Steps:
1.	 User launches market place application.
2.	 User logs in using seller account.
3.   From the home screen user selects the store they want to view a customer report for.
4.   User clicks on 'Continue' button to continue to next screen.
5.   From the next screen click Statistics Dashboard button.
6.   From statistics dashboard screen user clicks View customer report button.
7.   User can view which customers bought what products.
8.   User clicks on Sort dashboard to sort the report by increasing number of items.
9.   User clicks 'Back to Seller Menu' button to exit the 'Customer Report' screen and go back to the previous screen.

Expected result: Application displays customer report for the seller store. <br />
Test Status: Passed.

# Test 16: Seller view Product Report
Steps:
1.	 User launches market place application.
2.	 User logs in using seller account.
3.   From the home screen user selects the store they want to view a product report for.
4.   User clicks on 'Continue' button to continue to next screen.
5.   From the next screen click Statistics Dashboard button.
6.   From statistics dashboard screen user clicks View product report button.
7.   User can view what products were sold and how many of each product were sold.
8.   User clicks on Sort dashboard to sort the report by increasing number of sales.
9.   User clicks 'Back to Seller Menu' button to exit the 'Product Report' screen and go back to the previous screen.

Expected result: Application displays product report for the seller store. <br />
Test Status: Passed.

# Test 17: Customer view marketplace and purchase products
Steps:
1.	 User launches market place application.
2.	 User logs in using customer account.
3.	 From the home screen click View marketplace button to view the available products.
4.	 User can view all the products available in the marketplace to purchase.
5.	 User enters the product they want to purchase in the "Select a product to purchase" text box via keyboard.
6.   User enters the store name of where the product is from in the "Enter the store name" text box via keyboard.
7.   User clicks on the Purchase button and continues to the purchasing screen.
8.   User can see the description and quantity available of selected product.
9.   User enters the number of that product they want to purchase in the "Enter the quantity to purchase" textbook via keyboard.
10.  User clicks on Purchase button to purchase the product and a message saying "Product purchased successfully".
11.  User clicks on the Ok button and returns to the marketplace screen.

Expected result: Customer view marketplace and purchase products. <br />
Test Status: Passed.

# Test 18: Customer view marketplace sort by price
Steps:
1.	 User launches market place application.
2.	 User logs in using customer account.
3.	 From the home screen click View marketplace button to view the available products.
4.	 User clicks on Sort Marketplace by Price button to display the products in increasing price order.
5.	 User clicks on the Back button and returns to the home screen.

Expected result: Application sorts products by increasing price. <br />
Test Status: Passed.

# Test 19: Customer view marketplace sort by quantity
Steps:
1.	 User launches market place application.
2.	 User logs in using customer account.
3.	 From the home screen click View marketplace button to view the available products.
4.	 User clicks on Sort Marketplace by Quantity button to display the products in increasing quantity order.
5.	 User clicks on the Back button and returns to the home screen.

Expected result: Application sorts products by increasing quantity. <br />
Test Status: Passed.

# Test 20: Customer Search for products
Steps:
1.	 User launches market place application.
2.	 User logs in using customer account.
3.	 From the home screen click Search for Products button to search for available products.
4.	 User enters the product details they want to search for in the "Enter a search term" text box via keyboard.
5.   User clicks the Search button and the results get displayed in a table below.
6.   User clicks on the Back to Customer Statistics menu button and returns to the home screen.

Expected result: Application searches for products. <br />
Test Status: Passed.

# Test 21: Customer view purchase history
Steps:
1.	 User launches market place application.
2.	 User logs in using customer account.
3.	 From the home screen click View Purchase History button to view all purchased items.
4.	 User can see all the products purchased and their details in table.
5.   User clicks on the Back to Customer menu button and returns to the home screen.

Expected result: Application displays customer purchase history. <br />
Test Status: Passed.

# Test 22: Customer export purchase history
Steps:
1.	 User launches market place application.
2.	 User logs in using customer account.
3.   From the home screen click Export Purchase History button to export all purchased items into a CSV file.
4.   User clicks 'Select Folder' button to chose which folder to export the CSV file to.
5.   User selects a folder from their device and clicks on 'Select' button to chose folder or clicks the New Folder to make a new folder.
6.   On Export product screen click on 'Export' button to export the CSV file to the selected folder.
7.   A message is displayed saying "File exported successfully" and user clicks the "Ok" button being redirected to the export purchase history screen.
8.   User can either select another folder to export to or click 'Back to Customer Menu' button to exit the 'export purchase history' screen and go back to the previous screen.

Expected result: Application exports purchase history of user. <br />
Test Status: Passed.

# Test 23: Customer view Store Report
Steps:
1.	 User launches market place application.
2.	 User logs in using customer account.
3.   From the next screen click Statistics Dashboard button.
4.   From statistics dashboard screen user clicks View store report button.
5.   User can view how many products each store has sold.
6.   User clicks on Sort dashboard to sort the report by increasing number of products sold.
7.   User clicks 'Back to Customer Statistics Menu' button to exit the 'Store Report' screen and go back to the previous screen.
8.   User clicks on the Back button and returns to the home screen.

Expected result: Application displays store report for all the stores. <br />
Test Status: Passed.

# Test 24: Customer view Product Report
Steps:
1.	 User launches market place application.
2.	 User logs in using customer account.
3.   From the next screen click Statistics Dashboard button.
4.   From statistics dashboard screen user clicks View product report button.
5.   User can view which products each store has sold.
6.   User clicks 'Back to Customer Statistics Menu' button to exit the 'Product Report' screen and go back to the previous screen.
7.   User clicks on the Back button and returns to the home screen.

Expected result: Application displays product report for all the stores. <br />
Test Status: Passed.

# Test 25: Seller create multiple stores
Steps:
1.	 User launches market place application.
2.	 User logs in using seller account.
3.	 From the home screen click Create Store button to create a new store.
4.   User enters the new store's name in the Enter name of the store text box via keyboard.
5.   User clicks the Create button and message is displayed saying "Store created!".
6.   User clicks the Ok button and is redirected to the Create a new Store screen.
7.   Click on the Back button to go to the home screen and select the newly created store in the dropdown.
8.   User clicks Continue button to go to the new store landing screen.
9.	 List new products using create product functionality.
10.	 Repeat steps 3 – 9 to create more stores.

Expected result: Application creates new stores for the seller user. <br />
Test Status: Passed.

# Test 26: Seller switch between multiple stores
Steps:
1.	 User launches market place application.
2.	 User logs in using seller account.
3.	 From the home screen select a store from the list of stores in the dropdown.
4.	 User clicks Continue button to go to the store landing screen.
5.	 Check all store related functionality.
6.	 Repeat steps 3 – 5 to switch to different stores and check all features.

Expected result: Application lets switch stores for the seller user and shows store specific data when in a particular store. <br />
Test Status: Passed.

# Test 27: Edit user account
Steps:
1.	 User launches market place application.
2.	 User logs in using seller or customer account.
3.	 Click on Edit account settings button.
4.   User enter the new password in the New Password text box via keyboard.
5.   User clicks on Update Password button and a message saying 'Password updated successfully' shows.
6.   User clicks on Ok button to return to previous screen.

Expected result: Application lets user update the password successfully. <br />
Test Status: Passed.

# Test 28: Delete user account
Steps:
1.	 User launches market place application.
2.	 User logs in using seller or customer account.
3.	 Click on Edit account settings button
4.	 User clicks Delete account to delete the account.
5.   Message saying 'Account deleted successfully' shows and user is redirected to login page.

Expected result: Application lets user delete marketplace account successfully. <br />
Test Status: Passed.

# Test 29: Customer add items to shopping cart
Steps:
1.	 User launches market place application.
2.	 User logs in using customer account.
3.	 User clicks on View Marketplace button to view all available products.
4.   User enters the product they want to purchase in the "Select a product to purchase" text box via keyboard.
5.   User enters the store name of where the product is from in the "Enter the store name" text box via keyboard.
6.   User clicks on the Purchase button and continues to the purchasing screen.
7.   User can see the description and quantity available of selected product.
8.   User enters the number of that product they want to purchase in the "Enter the quantity to purchase" textbook via keyboard.
9.   User clicks ‘Add to shopping cart’ button and adds item to shopping cart.
10.  Message is displayed saying 'Product added to shopping cart'
11.  User clicks on the Ok button and returns to the marketplace screen.
12.  Repeat steps 3 - 11 and add multiple items to shopping cart from different stores.

Expected result: Application lets user add multiple items to shopping cart. <br />
Test Status: Passed.

# Test 30: Customer view shopping cart
Steps:
1.	 User launches market place application.
2.	 User logs in using customer account.
3.	 User clicks to view shopping cart button.
4.   Shopping cart products in displayed in a table below.

Expected result: Application lets user view items in their shopping cart. <br />
Test Status: Passed.

# Test 31: Customer save shopping cart
Steps:
1.	 User launches market place application.
2.	 User logs in using customer account.
3.	 User clicks the Shopping Cart button.
4.	 User adds more items to shopping cart.
5.   User clicks the Back button and clicks View MarketPlace button.
6.   User enters the product they want to purchase in the "Select a product to purchase" text box via keyboard.
7.   User enters the store name of where the product is from in the "Enter the store name" text box via keyboard.
8.   User clicks on the Purchase button and continues to the purchasing screen.
9.   User can see the description and quantity available of selected product.
10.  User enters the number of that product they want to purchase in the "Enter the quantity to purchase" textbook via keyboard.
11.  User clicks ‘Add to shopping cart’ button and adds item to shopping cart.
12.  Message is displayed saying 'Product added to shopping cart'
13.  User clicks on the Ok button and returns to the marketplace screen.
14.  User clicks on logout button and exits marketplace.
15.  User enters username and password into respective text boxes via keyboard and clicks login button.
16.  User clicks Shopping Cart button and can view newly updated shopping cart.

Expected result: Application saves the items in user shopping cart and displays them correctly when user logs back in. <br />
Test Status: Passed.

# Test 32: Customer remove items in shopping cart
Steps:
1.	 User launches market place application.
2.	 User logs in using customer account.
3.	 User clicks the Shopping Cart button.
4.	 User enters the Flavor name of the product they want to delete from shopping cart in 'Select a product to delete' text box via keyboard.
5.   User enters the name of the store of the product they want to delete in the 'Enter the store name' text box via keyboard.
6.   User clicks Delete button and message saying 'Product removed from shopping cart successfully'.
7.   User clicks the Ok button and goes back to Shopping Cart screen.

Expected result: Application allows user to remove items from their shopping cart. <br />
Test Status: Passed.

# Test 33: Customer purchase items in shopping cart
Steps:
1.	 User launches market place application.
2.	 User logs in using customer account.
3.	 User clicks the Shopping Cart button.
4.	 User clicks the Purchase button and message saying 'Products purchased successfully! Shopping cart emptied' is displayed.
5.   User clicks Ok button and is redirected to home screen.
6.   Shopping cart is now empty and items previously in shopping cart are purchased.

Expected result: Application allows user to purchase all items from their shopping cart. <br />
Test Status: Passed.

# Test 34: Customer purchase items in shopping cart – one or more items out of stock (might be removed)
Steps:
1.	 User launches market place application.
2.	 User logs in using customer account.
3.	 User clicks to view shopping cart.
4.	 User places the entire quantity of one flavor into their shopping cart from marketplace.
5.	 Original user logs out and another customer user logins.
6.   Other user purchases one of the flavor that the original customer places in their shopping cart.
7.   Other user logs out and original user logins.
8.   Original user tries to purchase their entire shopping cart and message saying "Items out of stock" is displayed.

Expected result: Application allows user to purchase all items from their shopping cart in stock and displays an error message about the items out of stock. <br />
Test Status: Passed.

# Test 35: Customer Search for products - refresh
Steps:
1.	 User launches market place application.
2.	 User logs in using customer account.
3.	 From the home screen click View Marketplace button to view all the products.
4.	 User launches another instance of market place application as seller account.
5.	 User clicks ‘Create a product’ button to list a new product.
6.	 User goes to first instance logged in as customer and clicks ‘Refresh’ button in marketplace screen.
7.   Newly created product should be displayed in marketplace screen.

Expected result: Application refreshes the search results with new products added to the seller store after customer initially searched for product. <br />
Test Status: Passed.

# Test 36: Seller view Shopping Cart
Steps:
1.	 User launches market place application.
2.	 User logs in using seller account.
3.   From the home screen user selects the store they want to view the shopping cart for.
4.   User clicks on 'Continue' button to continue to next screen.
5.   From the next screen User clicks Shopping Cart button.
6.   User can view what products are in customers carts from that store.
7.   User clicks 'Back to Seller Menu' button to exit the 'Shopping Cart' screen and go back to the previous screen.

Expected result: Application displays shopping cart for the seller store. <br />
Test Status: Passed.

# Test 37: User logout
Steps:
1.	 User launches market place application.
2.	 User logs in using their account.
3.   From the home screen user clicks the logout button and gets redirected to the login screen.

Expected Result: User can successfully log out of marketplace and go back to login screen. <br />
Test Status: Passed.
