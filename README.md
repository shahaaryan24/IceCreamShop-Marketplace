# CS-180-Project-5
CS 180 Capstone Project

# Group Members
Aaryan Shah <br />
Arya Shukla <br />
Atiksh Kotikalapudi <br />
Sruti Karumanchi <br />

# Instructions
To start the MarkerPlace application, first run the MarketPlaceServer class and then run the Client class to launch
the MarketPlace GUI. From there make an account for either buyer and seller or login with the correct credentials. User
will either be redirected to customer home screen or seller home screen and then can choose between the several different
types of functionalities. 

## Note:
1. For this program, each user must have a unique username regardless of whether they are a seller 
or customer when making accounts. <br />
2. Also, there is no pre-populated data so the seller must import products or create new 
products when first starting the application. <br />
3. When importing products, only new flavors can be imported and if a flavor  already exists then an error message is displayed with a list of flavors not imported. <br />
4. Additionally, flavor names cannot be changed, however a new flavor can be created and an old flavor can be deleted. <br />
5. Then, for any stores in the application the store name should not contain spaces. 
6. Finally, the files we have tested with have been provided down below, in case one should want to test with them.
7. Statistics was the optional feature implemented by our team.

Test Files: https://drive.google.com/drive/folders/1BsPBJ9fBne8xH5Vbtjgp2p5Q8rwRCn4n?usp=drive_link <br />
Note: You will need to add an empty StoreATransaction.txt file and an empty DairyQueen.txt file

# Submission
This project was submitted by Sruti Karumanchi in Vocareum. <br />
The report was submitted by Aaryan Shah on BrightSpace. <br />
The presentation was submitted by Sruti Karumanchi on BrightSpace. <br />

# Classes
Client <br />
CreateCustomerPanel <br />
CsvFilter <br />
CurrentUser <br />
Customer <br />
CustomerExportPurchaseHistoryPanel <br />
CustomerPurchaseHistoryPanel <br />
CustomerPurchasePrdPanel <br />
CustomerSearchForPrdPanel <br />
CustomerSortStore <br />
CustomerStatistics <br />
CustomerStatisticsPanel <br />
CustomerStatisticsProductReportPanel <br />
CustomerStatisticsStoreReportPanel <br />
CustomerViewMarketPlaceSelectionPanel <br />
CustomerViewShoppingCartPanel <br />
EditAccountSettingsPanel <br />
MarketPlaceServer <br />
Product <br />
Seller <br />
SellerCreateNewStore <br />
SellerCreatePrdPanel <br />
SellerDeletePrdSelPanel <br />
SellerEditPrdSelPanel <br />
SellerEditProductPanel <br />
SellerExportPrdPanel <br />
SellerImportPrdPanel <br />
SellerMainPanel <br />
SellerSortCustomer <br />
SellerSortProduct <br />
SellerStatistics <br />
SellerStatisticsCustomerReportPanel <br />
SellerStatisticsPanel <br />
SellerStatisticsProductReportPanel <br />
SellerViewSalesByStore <br />
SellerViewShoppingCartPanel <br />
ShoppingCart <br />
Transaction <br />
User <br />

### Client
This class implements the entire functionality of the program on the client side. The Client component serves 
as the operational hub, managing transactions, product listings and user interactions within our program using gui.

### CreateCustomerPanel
This class displays the home screen for the customers and allows the user to select between the features provided for 
customers. Features include viewing the Marketplace, searching for products, viewing purchase history, exporting 
purchase history, viewing statistics, viewing shopping cart, editing the account, and logging out.

### CsvFilter
This class finds the CSV file within the local device that the user searches for.

### CurrentUser
This class records the details of the user currently logged into the application.

### Customer
Customers can view the overall marketplace listing products for sale, search for specific products using terms that 
match the name, store, or description, and sort the marketplace on price or quantity available. Customers can also 
purchase items from the product page and review a history of their previously purchased items.

### CustomerExportPurchaseHistoryPanel
This class creates a new JPanel for when a customer exports purchase history with a double buffer and a flow layout.

### CustomerPurchaseHistoryPanel
This class creates a new JPanel for when a customer wants to view their purchase history with a double buffer and a 
flow layout.

### CustomerPurchasePrdPanel
This class displays the screen after the customers chose a product they want to purchase and allows the user to select
between the features. Features include viewing the description and quantity available for the chosen product, entering
the quantity the user wants to purchase, purchasing a product, adding product to shopping cart, and a cancel option.

### CustomerSearchForPrdPanel
This class creates a new JPanel for when a customer wants to search for products with a double buffer and a flow layout.

### CustomerSortStore
This class implements the feature of sorting the stores displayed in marketplace by increasing number of available
products.

### CustomerStatistics
This class creates the product report and store report for customers. It sorts the stores in the store report by 
comparing the number of products in each store. It also loads all the products sold at stores for the product report.

### CustomerStatisticsPanel
This class creates a new JPanel for when a customer wants to see statistics with a double buffer and a flow layout. The 
user can either select between viewing the Product Report or Store Report.

### CustomerStatisticsProductReportPanel
This class creates a new JPanel for when a customer wants to see the product report with a double buffer and a flow 
layout.

### CustomerStatisticsStoreReportPanel
This class creates a new JPanel for when a customer wants to see the store report with a double buffer and a flow 
layout.

### CustomerViewMarketPlaceSelectionPanel
This class displays the marketplace home screen and allows the user to select between the features provided for
customers. Features include viewing the Marketplace, entering a product to purchase, entering the name of the store for 
the product they want to purchase, sorting the marketplace by price, sorting the marketplace by quantity, selecting to 
purchase a product, and a back option to go back to the previous screen.

### CustomerViewShoppingCartPanel
This class displays the shopping cart home screen and allows the user to select between the features provided for
customers. Features include viewing the shopping cart, entering a product to delete from shopping cart, entering the
name of the store for the product they want to delete, selecting to delete a product, a back option to go back to the 
previous screen, and a purchase option to purchase all products from shopping cart at once.

### EditAccountSettingsPanel
This class displays the edit account home screen and allows the user to select between the features provided for all 
users. Features include entering the new password for the account, selecting to update the password, a back option to 
go back to the previous screen, and selecting to delete the user's account.

### MarketPlaceServer
This class implements the entire functionality of the program on the server side. The MarketPlace component serves as 
the operational hub, managing transactions, product listings and user interactions within our program.

### Product
The product class, establishing a fundamental structure that plays a key role in the MarketPlace's design.

### Seller
Sellers can create, edit, or delete products associated with their stores. Sellers can view a list of their sales by 
store, including customer information and revenues from the sale.

### SellerCreateNewStore
This class allows a seller to create a store. Sellers have to enter a store name and it also has a back option to go
back to the previous screen.

### SellerCreatePrdPanel
This class allows a seller to create a product. Sellers have to enter a flavor name, description, quantity number, and
price. The user also has a cancel option.

### SellerDeletePrdSelPanel
This class allows a seller to delete a product. Sellers have to enter the flavor name of the product they want to 
delete. The user also has a back option to go back to the previous screen.

### SellerEditPrdSelPanel
This class allows a seller to choose a product to edit. The user also has a back option to go back to the previous 
screen.

### SellerEditProductPanel
This class allows a seller to edit a product. Sellers have to enter a new flavor name, new description, new quantity
number, and new price. The user also has a cancel option.

### SellerExportPrdPanel
This class provides the implementation for the GUI of the Export Product panel in the seller menu.

### SellerImportPrdPanel
This class provides the implementation for the GUI of the Import Product panel in the seller menu.

### SellerMainPanel
This class displays the home screen for the sellers and allows the user to select between the features provided for
sellers. Features include selecting which store to make changes for, creating a new store, editing the account, and 
logging out.

### SellerSortCustomer
This class sorts the names of customers based on increasing number of products sold for each customer.

### SellerSortProduct
This class sorts the flavor names based on increasing number of products sold for each flavor.

### SellerStatistics
This class creates the product report and customer report for sellers. It sorts the products in the product report by 
comparing the number of products sold in each store. It sorts the customer names in the customer report by comparing the
number of products each customer bought.

### SellerStatisticsCustomerReportPanel
This class creates a new JPanel for when a seller wants to see the customer report with a double buffer and a flow
layout.

### SellerStatisticsPanel
This class creates a new JPanel for when a seller wants to see statistics with a double buffer and a flow layout. The
user can either select between viewing the Product Report or Customer Report.

### SellerStatisticsProductReportPanel
This class creates a new JPanel for when a seller wants to see the product report with a double buffer and a flow
layout.

### SellerViewSalesByStore
This class displays all the sales for the chosen store for a seller and allows the user to return to the seller menu.

### SellerViewShoppingCartPanel
This class displays all the products in customers' shopping carts for sellers and allows the user to return to the 
seller menu.

### ShoppingCart
This class implements the feature for when a customer wants to add a product to the shopping cart. It also allows you to
delete a product from shopping cart and the user can purchase all products from the shopping cart.

### Transaction
This class records all the details of a transaction made by a customer.

### User
This class handles data preservation of the user details and the validation of users.
