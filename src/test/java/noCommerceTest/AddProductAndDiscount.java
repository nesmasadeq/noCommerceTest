package noCommerceTest;



import static org.testng.Assert.assertTrue;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class AddProductAndDiscount {

	public static void main(String[] args) throws InterruptedException {
		WebDriver driver = new ChromeDriver();
		driver.get("https://admin-demo.nopcommerce.com/");
		driver.manage().window().maximize();
		
		String productName = "test product";
		String discountName = "test discount";
		String discountPercentage ="50";
		
		//get email element
		WebElement emailElement = driver.findElement(By.id("Email"));
		emailElement.clear();
		emailElement.sendKeys("admin@yourstore.com");
		
		//assertion for email typed
		Assert.assertEquals(emailElement.getAttribute("value"), "admin@yourstore.com");
		
		//get password element
		WebElement passwordElement = driver.findElement(By.id("Password"));
		passwordElement.clear();
		passwordElement.sendKeys("admin");
		
		//assertion for password typed
		Assert.assertNotEquals(passwordElement.getAttribute("value"), "");
		
		//get submit button
		WebElement loginButton = driver.findElement(By.cssSelector("button[type=\"submit\"]"));
		loginButton.click();
		//assertion for button style
//		String LoginBackgroundColor = loginButton.getCssValue("background-color");
//      Assert.assertEquals(LoginBackgroundColor, "#467e9f");
		
		//assertion for current url
		boolean checkHomeUrl= driver.getCurrentUrl().contains("admin/");
		Assert.assertTrue(checkHomeUrl);
		
		//get catalog item
		WebElement catalogElement = driver.findElement(By.linkText("Catalog"));
		catalogElement.click();
		
		//assertion for expand menu
		WebElement expandClassElement = new WebDriverWait(driver, Duration.ofSeconds(5))
		.until(ExpectedConditions.elementToBeClickable(driver.findElements(By.cssSelector("ul[data-widget=\"treeview\"] li")).get(1)));
		String expectedClassString =  expandClassElement.getAttribute("class");
		boolean expandClass = expectedClassString.contains("menu-open");
//		Assert.assertTrue(expandClass);
		
		//get product item
		WebElement productElement = driver.findElement(By.cssSelector("li a[href*=\"Product/List\"]")); 
		productElement.click();
		
		//assertion for current url
		boolean productListUrl = driver.getCurrentUrl().contains("Admin/Product/List");
		Assert.assertTrue(productListUrl);
		
		//assertion for current heading
		WebElement productHeading = driver.findElement(By.cssSelector("form h1"));
		String productHeadingString = productHeading.getText();
		Assert.assertEquals(productHeadingString,"Products");
		
		//get new product button
		WebElement newProductButton = driver.findElement(By.cssSelector("a[href*=\"Product/Create\"]"));
		newProductButton.click();
		//assertion for button style
//		String backgroundColor = newProductButton.getCssValue("background-color");
//		Assert.assertEquals(backgroundColor, "#467e9f");
		
		//assertion for current url
		boolean createProductUrl = driver.getCurrentUrl().contains("Admin/Product/Create");
		Assert.assertTrue(createProductUrl);
		
		//assertion for new product heading page
		WebElement newProductHeadingElement = driver.findElement(By.cssSelector("form h1"));
		String newProductHeadingString = newProductHeadingElement.getText();
		boolean checkAddNewProductHeading = newProductHeadingString.contains("Add a new product");
		Assert.assertTrue(checkAddNewProductHeading);
		
		//assertion for back list
		boolean checkBackList = newProductHeadingString.contains("back to product list");
		Assert.assertTrue(checkBackList);
		
		//assertion for check basic is checked by default
		WebElement MoodElement = driver.findElement(By.className("onoffswitch"));
		WebElement bodyElement = driver.findElement(By.cssSelector("body"));
		String basicMood = bodyElement.getAttribute("class");
		boolean checkBasicMood = basicMood.contains("basic-settings-mode");
		if(!checkBasicMood) {
			MoodElement.click();
		}
		
		//get Product info card 
		WebElement ProductInfoCard = driver.findElement(By.id("product-info"));
		
		//check if product info is collapsed
		String productInfoCardCollapseString = ProductInfoCard.getAttribute("class");
		if(productInfoCardCollapseString.contains("collapsed-card")) {
			ProductInfoCard.click();
		}
		
		//assertion for product info heading
		WebElement productInfoHeading = ProductInfoCard.findElement(By.cssSelector("div[data-card-name=\"product-info\"] .card-title"));
		String productInfoHeadingString =productInfoHeading.getText();
		boolean checkproductInfoHeading = productInfoHeadingString.contains("Product info");
		Assert.assertTrue(checkproductInfoHeading);
		
		//get product name label
		WebElement productNameLabel = ProductInfoCard.findElement(By.cssSelector("label[for=\"Name\"]"));
		//assertion for name label
		Assert.assertEquals(productNameLabel.getText(), "Product name");
		
		//get tooltip icon for product
		WebElement productNameToolTip = driver.findElements(By.cssSelector("#product-details-area div[data-toggle=\"tooltip\"]")).get(0);
		Actions productActionProvider = new Actions(driver);
		productActionProvider.clickAndHold(productNameToolTip).build().perform();
		
		//assertion for name product tool tip
		String actualProductNameToolTip= productNameToolTip.getAttribute("data-original-title");
		Assert.assertEquals(actualProductNameToolTip, "The name of the product.");
		
		//get product name field
		WebElement productNameElement = ProductInfoCard.findElement(By.id("Name"));
		productNameElement.sendKeys(productName);
		
		//assertion for product name value
		String currentProductName = productNameElement.getAttribute("value");
		Assert.assertEquals(currentProductName, productName);
		
		//get short description label
		WebElement shortDescriptionLabel = ProductInfoCard.findElement(By.cssSelector("label[for=\"ShortDescription\"]"));
		//assertion for short description label
		Assert.assertEquals(shortDescriptionLabel.getText(), "Short description");
				
		//get tooltip icon for short description
		WebElement shortDescriptionToolTip = ProductInfoCard.findElements(By.cssSelector("#product-details-area div[data-toggle=\"tooltip\"]")).get(1);
		Actions descriptionActionProvider = new Actions(driver);
		descriptionActionProvider.moveToElement(shortDescriptionToolTip).perform();
	    
		//assertion for tooltip content 
		String actualShortDescriptionToolTip= shortDescriptionToolTip.getAttribute("data-original-title");
		Assert.assertEquals(actualShortDescriptionToolTip, "Short description is the text that is displayed in product list i.e. сategory / manufacturer pages.");
				
		//get short description field
		WebElement shortDescriptionElement = ProductInfoCard.findElement(By.id("ShortDescription"));
		shortDescriptionElement.sendKeys("test short description");
				
		//assertion for short description value
		String currentShortDescription = shortDescriptionElement.getAttribute("value");
		Assert.assertEquals(currentShortDescription, "test short description");
		
		//get full description label
		WebElement fullDescriptionLabel = ProductInfoCard.findElement(By.cssSelector("label[for=\"FullDescription\"]"));
		//assertion for full description label
		Assert.assertEquals(fullDescriptionLabel.getText(), "Full description");
						
		//get tooltip icon for full description
		WebElement fullDescriptionToolTip = ProductInfoCard.findElements(By.cssSelector("#product-details-area div[data-toggle=\"tooltip\"]")).get(2);
		Actions fullDescriptionActionProvider = new Actions(driver);
		fullDescriptionActionProvider.moveToElement(fullDescriptionToolTip).perform();
			    
		//assertion for full description tooltip content 
		String actualFullDescriptionToolTip= fullDescriptionToolTip.getAttribute("data-original-title");
		Assert.assertEquals(actualFullDescriptionToolTip, "Full description is the text that is displayed in product page.");
						
		//get full description Iframe
		WebElement fullDescriptionIframe = driver.findElement(By.id("FullDescription_ifr"));
		driver.switchTo().frame(fullDescriptionIframe);
		
		//get full description field
		WebElement fullDescriptionTextArea = driver.findElement(By.cssSelector("#tinymce p"));		
		fullDescriptionTextArea.sendKeys("test full description");
						
		//assertion for full description value
		String currentFullDescription = fullDescriptionTextArea.getText();
		Assert.assertEquals(currentFullDescription, "test full description");
		
		//switch to default content
		driver.switchTo().defaultContent();
		
		//get SKU label
		WebElement skuLabel = ProductInfoCard.findElement(By.cssSelector("label[for=\"Sku\"]"));
		
		//assertion for SKU label
		Assert.assertEquals(skuLabel.getText(), "SKU");
		
		//get tooltip icon for SKU
		WebElement skuToolTip = driver.findElements(By.cssSelector("#product-details-area div[data-toggle=\"tooltip\"]")).get(3);
		Actions skuActionProvider = new Actions(driver);
		skuActionProvider.clickAndHold(skuToolTip).build().perform();
		
		//assertion for SKU tool tip
		String actualSkuToolTip= skuToolTip.getAttribute("data-original-title");
		Assert.assertEquals(actualSkuToolTip, "Product stock keeping unit (SKU). Your internal unique identifier that can be used to track this product.");
		
		//get and fill SKU field
		WebElement skuElement = ProductInfoCard.findElement(By.id("Sku"));
		skuElement.sendKeys("test-sku");
		
		//assertion for SKU value
		String currenSku = skuElement.getAttribute("value");
		Assert.assertEquals(currenSku, "test-sku");
		
		//get categories label
		WebElement categoriesLabel = ProductInfoCard.findElement(By.id("SelectedCategoryIds_label"));
		
		//assertion for categories label
		Assert.assertEquals(categoriesLabel.getText(), "Categories");
		
		//get tooltip icon for categories
		WebElement categoriesToolTip = driver.findElement(By.cssSelector("#SelectedCategoryIds_label+div[data-toggle=\"tooltip\"]"));
		Actions categoriesActionProvider = new Actions(driver);
		categoriesActionProvider.moveToElement(categoriesToolTip).perform();
		
		//assertion for categories tool tip
		String actualCategoriesToolTip= categoriesToolTip.getAttribute("data-original-title");
		Assert.assertEquals(actualCategoriesToolTip, "Choose categories. You can manage product categories by selecting Catalog > Categories.");
		
		//get and select category
		WebElement categoryFeild= driver.findElements(By.cssSelector("#product-info div[role=\"listbox\"]")).get(0);
		Actions categoryActions = new Actions(driver);
		categoryActions.moveToElement(categoryFeild);
		categoryFeild.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));
		WebElement selectedCategory = driver.findElement(By.cssSelector("#SelectedCategoryIds_listbox li:nth-child(2)"));
		selectedCategory.click();
		categoriesLabel.click();
		//assertion for selected category item
		String selectedAttributeString = selectedCategory.getAttribute("aria-selected");
//		Assert.assertEquals(selectedAttributeString, "true");

		//		WebElement categoryElement = driver.findElement(By.id("SelectedCategoryIds"));
//		Select selectCategoryElement = new Select(categoryElement);
//		selectCategoryElement.selectByValue("2");
//		
		//assertion for selected value
//		WebElement selectedElement = ProductInfoCard.findElement(By.cssSelector("#SelectedCategoryIds_taglist li"));
//		String selectedString = selectedElement.getText();
//		boolean checkSelectedString = selectedString.contains("Computers >> Desktops");
//		Assert.assertTrue(checkSelectedString);
		
		//get Prices card
		WebElement pricesCard = driver.findElement(By.id("product-price"));
				
		//check if Prices card is collapsed
		String pricesCardCollapseString = pricesCard.getAttribute("class");
		if(pricesCardCollapseString.contains("collapsed-card")) {
			ProductInfoCard.click();
		}
		
		//assertion for prices heading
		WebElement pricesHeading = pricesCard.findElement(By.cssSelector("div[data-card-name=\"product-price\"] .card-title"));
		String pricesHeadingString =pricesHeading.getText();
		boolean checkPricesHeading = pricesHeadingString.contains("Prices");
		Assert.assertTrue(checkPricesHeading);
		
		//get price label
		WebElement priceLabel = pricesCard.findElement(By.cssSelector("label[for=\"Price\"]"));
		String priceLableString = priceLabel.getText();
		//assertion for price label
		Assert.assertEquals(priceLableString, "Price");
		
		//get tooltip icon for price
		WebElement priceToolTip = driver.findElements(By.cssSelector("#product-price div[data-toggle=\"tooltip\"")).get(0);
		Actions priceActionProvider = new Actions(driver);
		priceActionProvider.clickAndHold(priceToolTip).moveToElement(priceToolTip).build().perform();
		
		//assertion for price tool tip
		String actualPriceToolTip= priceToolTip.getAttribute("data-original-title");
		Assert.assertEquals(actualPriceToolTip, "The price of the product. You can manage currency by selecting Configuration > Currencies.");
		
		//get and fill price field
		WebElement priceElement = pricesCard.findElement(By.cssSelector("#product-price-area  input.k-formatted-value.k-input"));
		priceElement.sendKeys("1000");
		priceLabel.click();
		//assertion for price value
		String priceValueString = priceElement.getAttribute("aria-valuenow");
		Assert.assertEquals(priceValueString, "1000");
		
		//get Inventory card
		WebElement inventoryCard = driver.findElement(By.id("product-inventory"));
		
		//move to inventory card
		Actions inventoryActions = new Actions(driver);
		inventoryActions.moveToElement(inventoryCard).perform();
				
		//check if inventory card is collapsed
		String inventoryCardCollapseString = pricesCard.getAttribute("class");
		if(inventoryCardCollapseString.contains("collapsed-card")) {
			inventoryCard.click();
		}
		
		//assertion for inventory heading
		WebElement inventoryHeading = inventoryCard.findElement(By.cssSelector("#product-inventory .card-title"));
		String inventoryHeadingString =inventoryHeading.getText();
		boolean checkInventoryHeading = inventoryHeadingString.contains("Inventory");
		Assert.assertTrue(checkInventoryHeading);
		
		//get inventory method label
		WebElement inventoryMethodLabel = inventoryCard.findElement(By.cssSelector("label[for=\"ManageInventoryMethodId\"]"));
		
		//assertion for inventory method label
		Assert.assertEquals(inventoryMethodLabel.getText(), "Inventory method");
		
		//get tooltip icon for inventory method
		WebElement inventoryMethodToolTip = driver.findElements(By.cssSelector("#product-inventory div[data-toggle=\"tooltip\"]")).get(0);
		Actions inventoryActionProvider = new Actions(driver);
		inventoryActionProvider.moveToElement(inventoryMethodToolTip).perform();
		
		//assertion for inventory method
		String actualInventoryMethodTip= inventoryMethodToolTip.getAttribute("data-original-title");
		Assert.assertEquals(actualInventoryMethodTip, "Select inventory method. There are three methods: Don’t track inventory, Track inventory and Track inventory by attributes. You should use Track inventory by attributes when the product has different combinations of these attributes and then manage inventory for these combinations.");
		
		//get inventory select element
		WebElement inventorySelectElement = inventoryCard.findElement(By.name("ManageInventoryMethodId"));
		Select inventoryMethodSelect = new Select(inventorySelectElement);
		inventoryMethodSelect.selectByValue("1");
		
		//assertion for selected element
		WebElement inventoryMethodOption = inventoryMethodSelect.getFirstSelectedOption();
		String inventorySelectedOption = inventoryMethodOption.getText();
		Assert.assertEquals(inventorySelectedOption, "Track inventory");
		
		//get save button
		WebElement saveButton = driver.findElement(By.name("save"));
		
		//assertion for save button style after clicking
		Actions saveActions = new Actions(driver);
		saveActions.clickAndHold(saveButton).perform();
		String saveBackgroundColor = saveButton.getCssValue("background-color");
//		Assert.assertEquals(saveBackgroundColor, "rgba(0, 98, 204, 1)");
		saveButton.click();
		
		//check current url after save product
		boolean checkSaveProductUrl = driver.getCurrentUrl().contains("Admin/Product/List");
		Assert.assertTrue(checkSaveProductUrl);
		
		//check success message appears
		WebElement successMessage = driver.findElement(By.className("alert-success"));
		String successMessageString = successMessage.getText();
		boolean checkSuccessMessage = successMessageString.contains("The new product has been added successfully.");
		Assert.assertTrue(checkSuccessMessage);
		
		//check the product added appears in the list
		WebElement productNameSearch = driver.findElement(By.id("SearchProductName"));
		productNameSearch.sendKeys(productName);
		WebElement searchButton= driver.findElement(By.id("search-products"));
		searchButton.click();
		
		//check rows content
		WebElement productTableContent = driver.findElement(By.xpath("//table[@id=\"products-grid\"]/tbody/tr"));
		String productTableString = productTableContent.getText();
		
		//assertion for product name saved
		boolean checkProductName = productTableString.contains(productName);
		Assert.assertTrue(checkProductName);
		
		//assertion for product sku saved
		boolean checkSku = productTableString.contains("test-sku");
		Assert.assertTrue(checkSku);
		
		//assertion for product price saved
		boolean checkPrice = productTableString.contains("1000");
		Assert.assertTrue(checkPrice);
		
		//assertion for product stock quantity saved
		boolean checkStockQuantity = productTableString.contains("10000");
		Assert.assertTrue(checkStockQuantity);
		
		//navigate to promotions item link
		WebElement pormotionElement = driver.findElement(By.linkText("Promotions"));
		pormotionElement.click();
		
		//assertion for expand menu
		WebElement expandPormotionClassElement = new WebDriverWait(driver, Duration.ofSeconds(7))
		.until(ExpectedConditions.elementToBeClickable(driver.findElements(By.cssSelector("nav > ul > li.nav-item.has-treeview")).get(3)));
		String expectedPormotionClassString =  expandPormotionClassElement.getAttribute("class");
		boolean expandPormotionClass = expectedPormotionClassString.contains("menu-open");
//		Assert.assertTrue(expandPormotionClass);
		
		//get discount element
		WebElement discountElement = driver.findElement(By.cssSelector("li a[href*=\"Admin/Discount/List\"]"));
		discountElement.click();
		
		//assertion for discount url
		boolean checkDiscountsUrl = driver.getCurrentUrl().contains("Admin/Discount/List");
		Assert.assertTrue(checkDiscountsUrl);
		
		//assertion for discount page heading
		WebElement discountHeading = driver.findElement(By.cssSelector(".content-header h1"));
		String discountHeadingString = discountHeading.getText();
		Assert.assertEquals(discountHeadingString,"Discounts");
		//get add discount button
		WebElement AddNewDisscountButton = driver.findElement(By.cssSelector("a[href*=\"Discount/Create\"]"));
		
		//assertion for add discount button style after clicking
		Actions addDiscountActions = new Actions(driver);
		addDiscountActions.clickAndHold(AddNewDisscountButton).perform();
		String addDiscountBackgroundColor = AddNewDisscountButton.getCssValue("background-color");
//		Assert.assertEquals(addDiscountBackgroundColor, "rgba(0, 98, 204, 1)");
		AddNewDisscountButton.click();
		
		//check create discount url
		boolean checkCreateDiscountsUrl = driver.getCurrentUrl().contains("Discount/Create");
		Assert.assertTrue(checkCreateDiscountsUrl);
		
		//assertion for create discount heading page
		WebElement newDiscountHeadingElement = driver.findElement(By.cssSelector("form h1"));
		String newDiscountHeadingString = newDiscountHeadingElement.getText();
		boolean checkNewDiscountHeading = newDiscountHeadingString.contains("Add a new discount");
		Assert.assertTrue(checkNewDiscountHeading);
				
		//assertion for back list in discount page
		boolean checkDiscountBackList = newDiscountHeadingString.contains("back to discount list");
		Assert.assertTrue(checkDiscountBackList);
				
		//get discount info card 
		WebElement dicountInfoCard = driver.findElement(By.id("discount-info"));
				
		//check if discount info is collapsed
		String dicountInfoCardCollapseString = dicountInfoCard.getAttribute("class");
		if(dicountInfoCardCollapseString.contains("collapsed-card")) {
			dicountInfoCard.click();
		}
				
		//assertion for discount info heading
		WebElement discountCardHeading = dicountInfoCard.findElement(By.cssSelector("#discount-info .card-title"));
		String discountCardHeadingString =discountCardHeading.getText();
		boolean checkDiscountCardHeadingHeading = discountCardHeadingString.contains("Discount info");
		Assert.assertTrue(checkDiscountCardHeadingHeading);
				
		//get discount name label
		WebElement discountNameLabel = dicountInfoCard.findElement(By.cssSelector("label[for=\"Name\"]"));
		//assertion for discount label
		Assert.assertEquals(discountNameLabel.getText(), "Name");
				
		//get tooltip icon for discount name
		WebElement discountNameToolTip = driver.findElements(By.cssSelector("#discount-info div[data-toggle=\"tooltip\"]")).get(0);
		Actions discountActionProvider = new Actions(driver);
		discountActionProvider.moveToElement(discountNameToolTip).perform();
				
		//assertion for discount name tool tip
		String actualDiscountNameToolTip= discountNameToolTip.getAttribute("data-original-title");
		Assert.assertEquals(actualDiscountNameToolTip, "The name of the discount.");
				
		//get discount name field
		WebElement discountNameElement = dicountInfoCard.findElement(By.id("Name"));
		discountNameElement.sendKeys(discountName);
				
		//assertion for discount name value
		String currentDiscountName = discountNameElement.getAttribute("value");
		Assert.assertEquals(currentDiscountName, discountName);
		
		//get discount type label
		WebElement discountTypeLabel = dicountInfoCard.findElement(By.cssSelector("label[for=\"DiscountTypeId\"]"));
		
		//assertion for discount type label
		Assert.assertEquals(discountTypeLabel.getText(), "Discount type");
				
		//get tooltip icon for discount type
		WebElement discountTypeToolTip = driver.findElements(By.cssSelector("#discount-info div[data-toggle=\"tooltip\"]")).get(1);
		Actions discountTypeActionProvider = new Actions(driver);
		discountTypeActionProvider.clickAndHold(discountTypeToolTip).moveToElement(discountTypeToolTip).build().perform();
				
		//assertion for discount type tool tip
		String actualDiscountTypeToolTip= discountTypeToolTip.getAttribute("data-original-title");
		Assert.assertEquals(actualDiscountTypeToolTip, "The type of discount.");
				
		//get discount type field
		WebElement discountSelectElement = dicountInfoCard.findElement(By.id("DiscountTypeId"));
		Select discountTypeSelect = new Select(discountSelectElement);
		discountTypeSelect.selectByValue("2");
				
		//assertion for selected option
		String currentDiscountType = discountSelectElement.getAttribute("value");
		Assert.assertEquals(currentDiscountType, "2");
		
		//get use percentage label
		WebElement usePercentageLabel = dicountInfoCard.findElement(By.cssSelector("label[for=\"UsePercentage\"]"));
		//assertion for discount type label
		Assert.assertEquals(usePercentageLabel.getText(), "Use percentage");
				
		//get tooltip icon for use percentage
		WebElement usePercentageToolTip = driver.findElements(By.cssSelector("#discount-info div[data-toggle=\"tooltip\"]")).get(3);
		Actions usePercentageActionProvider = new Actions(driver);
		usePercentageActionProvider.clickAndHold(usePercentageToolTip).moveToElement(usePercentageToolTip).build().perform();
				
		//assertion for discount type tool tip
		String actualUsePercentageToolTip= usePercentageToolTip.getAttribute("data-original-title");
		Assert.assertEquals(actualUsePercentageToolTip, "Determines whether to apply a percentage discount to the order/SKUs. If not enabled, a set value is discounted.");
				
		//get use percentage checkbox
		WebElement usePercentagElement = dicountInfoCard.findElement(By.id("UsePercentage"));
		usePercentagElement.click();
		
		//assertion for checking use percentage
		Assert.assertTrue(usePercentagElement.isSelected());
		
		//get discount percentage label
		WebElement discountPercentageLabel = dicountInfoCard.findElement(By.cssSelector("label[for=\"DiscountPercentage\"]"));
		
		//assertion for discount percentage label
		Assert.assertEquals(discountPercentageLabel.getText(), "Discount percentage");
				
		//get tooltip icon for discount percentage
		WebElement discountPercentageToolTip = driver.findElements(By.cssSelector("#discount-info div[data-toggle=\"tooltip\"]")).get(4);
		Actions discountPercentageActionProvider = new Actions(driver);
		discountPercentageActionProvider.clickAndHold(discountPercentageToolTip)
		.moveToElement(discountPercentageToolTip).build().perform();
				
		//assertion for discount percentage tool tip
		String actualDiscountPercentageToolTip= discountPercentageToolTip.getAttribute("data-original-title");
		Assert.assertEquals(actualDiscountPercentageToolTip, "The percentage discount to apply to order/SKUs.");
				
		//get discount percentage field
		WebElement discountPercentageElement = dicountInfoCard.findElement(By.cssSelector("#pnlDiscountPercentage input.k-formatted-value.k-input"));
		discountPercentageElement.sendKeys(discountPercentage);
		discountPercentageLabel.click();		
		//assertion for percentage value
		String currentPercentageDiscount = discountPercentageElement.getAttribute("aria-valuenow");
		Assert.assertEquals(currentPercentageDiscount, discountPercentage);
		
		//get discount amount label
		WebElement discountAmountLabel = dicountInfoCard.findElement(By.cssSelector("label[for=\"MaximumDiscountAmount\"]"));
				
		//assertion for discount amount label
		Assert.assertEquals(discountAmountLabel.getText(), "Maximum discount amount");
						
		//get tooltip icon for discount amount
		WebElement discountAmountToolTip = driver.findElements(By.cssSelector("#discount-info div[data-toggle=\"tooltip\"]")).get(5);
		Actions discountAmountActionProvider = new Actions(driver);
		discountAmountActionProvider.clickAndHold(discountAmountToolTip)
		.moveToElement(discountAmountToolTip).build().perform();
						
		//assertion for discount amount tool tip
		String actualDiscountAmountToolTip= discountAmountToolTip.getAttribute("data-original-title");
		Assert.assertEquals(actualDiscountAmountToolTip, "Maximum allowed discount amount. Leave empty to allow any discount amount. If you're using \"Assigned to products\" discount type, then it's applied to each product separately.");
				
		//get discount percentage field
		WebElement discountAmountElement = dicountInfoCard.findElement(By.cssSelector("#pnlMaximumDiscountAmount input.k-formatted-value"));
		discountAmountElement.sendKeys("20");
		discountPercentageLabel.click();
		
		//assertion for percentage value
		String currentAmountDiscount = discountAmountElement.getAttribute("aria-valuenow");
		Assert.assertEquals(currentAmountDiscount, "20");
				
		//get discount start date label
		WebElement discountStartDateLabel = dicountInfoCard.findElement(By.cssSelector("label[for=\"StartDateUtc\"]"));
				
		//assertion for start date label
		Assert.assertEquals(discountStartDateLabel.getText(), "Start date");
				
		//get tooltip icon for start date
		WebElement startDateToolTip = driver.findElements(By.cssSelector("#discount-info div[data-toggle=\"tooltip\"]")).get(10);
		Actions startDateActionProvider = new Actions(driver);
		startDateActionProvider.clickAndHold(startDateToolTip)
		.moveToElement(startDateToolTip).build().perform();
				
		//assertion for discount start date tool tip
		String actualStartDateToolTip= startDateToolTip.getAttribute("data-original-title");
		Assert.assertEquals(actualStartDateToolTip, "The start of the discount period in Coordinated Universal Time (UTC).");
				
		//get start date field
		WebElement startDateInput = dicountInfoCard.findElement(By.id("StartDateUtc"));
		startDateInput.sendKeys("12/31/2021 12:00:00 AM");
		
		//assertion for start date input value	
		String currentStartDate= startDateInput.getAttribute("value");
		Assert.assertEquals(currentStartDate, "12/31/2021 12:00:00 AM");

		//assertion the selected start date focused
//		WebElement foucusedDatElement = dicountInfoCard.findElement(By.cssSelector("table > tbody > tr:nth-child(5) > td:nth-child(6)"));
//		String focusedString = foucusedDatElement.getAttribute("aria-selected");
//		Assert.assertTrue(focusedString.contains("true"));
		
		//get discount end date label
		WebElement discountEndDateLabel = dicountInfoCard.findElement(By.cssSelector("label[for=\"EndDateUtc\"]"));
				
		//assertion for start date label
		Assert.assertEquals(discountEndDateLabel.getText(), "End date");
				
		//get tooltip icon for end date
		WebElement endDateToolTip = driver.findElements(By.cssSelector("#discount-info div[data-toggle=\"tooltip\"]")).get(11);
		Actions endDateActionProvider = new Actions(driver);
		endDateActionProvider.clickAndHold(endDateToolTip)
		.moveToElement(endDateToolTip).build().perform();
				
		//assertion for discount end date tool tip
		String actualEndDateToolTip= endDateToolTip.getAttribute("data-original-title");
		Assert.assertEquals(actualEndDateToolTip, "The end of the discount period in Coordinated Universal Time (UTC).");
				
		//get end date field
		WebElement endDateButton = driver.findElement(By.cssSelector("span[aria-controls=\"EndDateUtc_dateview\"]"));
		endDateButton.click();
		WebElement endDateElement = dicountInfoCard.findElement(By.id("EndDateUtc"));
		endDateElement.sendKeys("02/28/2021 12:00:00 AM");		
		
		//assertion for percentage value
		String currentEndDate= endDateElement.getAttribute("value");
		Assert.assertEquals(currentEndDate, "02/28/2021 12:00:00 AM");
		
		//get save button
		WebElement discountSaveButton = driver.findElement(By.name("save"));
		//assertion for save button style after clicking
		Actions discountSaveActions = new Actions(driver);
		discountSaveActions.clickAndHold(discountSaveButton).perform();
		String discountSaveBackgroundColor = discountSaveButton.getCssValue("background-color");
//				Assert.assertEquals(discountSaveBackgroundColor, "rgba(0, 98, 204, 1)");
		discountSaveButton.click();
		//check current  url after adding new discount
		boolean checkAddingDiscountUrl = driver.getCurrentUrl().contains("Discount/List");
		Assert.assertTrue(checkAddingDiscountUrl);
		
		//check success message after add new discount
		WebElement discountSuccessMessage = driver.findElement(By.className("alert-success"));
		String discountSuccessMessageString = discountSuccessMessage.getText();
		Assert.assertTrue(discountSuccessMessageString.contains("The new discount has been added successfully."));
		
		//get discount name search 
		WebElement discountNameSearch = driver.findElement(By.id("SearchDiscountName"));
		discountNameSearch.sendKeys(discountName);
		
		//get discount search button
		WebElement discountSearchButton = driver.findElement(By.id("search-discounts"));
		discountSearchButton.click();
		
		//get table content after search
		WebElement discountTableContent = driver.findElement(By.xpath("//table[@id=\"discounts-grid\"]/tbody/tr"));
		String discountTableContentString = discountTableContent.getText();
		Assert.assertTrue(discountTableContentString.contains(discountName));
		
		//assertion for discount type for saved discount
		Assert.assertTrue(discountTableContentString.contains("Assigned to products"));
		Assert.assertTrue(discountTableContentString.contains(discountPercentage));
		Assert.assertTrue(discountTableContentString.contains("12/31/2021 12:00:00 AM"));
		Assert.assertTrue(discountTableContentString.contains("02/28/2021 12:00:00 AM"));
		
		//get edit button for saved discount
		
		WebElement editDiscountButton = driver.findElement(By.cssSelector("#discounts-grid tr:nth-child(1) td:nth-child(7) a"));
		editDiscountButton.click();
		
		//assertion for edit discount url
		boolean editDiscounttUrl = driver.getCurrentUrl().contains("Discount/Edit/");
		Assert.assertTrue(editDiscounttUrl);
		
		//assertion for edit page heading
		WebElement editDiscountHeading = driver.findElement(By.cssSelector("form h1"));
		String editDiscountHeadingString = editDiscountHeading.getText();
		Assert.assertTrue(editDiscountHeadingString.contains("Edit discount details - "+discountName));
		
		//get add new product button
		WebElement addNewProductElement = driver.findElement(By.id("btnAddNewProduct"));
		
		//assertion for new product button style after clicking
		Actions addNewProductActions = new Actions(driver);
		addNewProductActions.clickAndHold(addNewProductElement).perform();
		Thread.sleep(5);
		String addProductBackgroundColor = addNewProductElement.getCssValue("background-color");
//		Assert.assertEquals(addProductBackgroundColor, "rgba(0, 98, 204, 1)");
		addNewProductElement.click();
		
		Set<String>windowStrings  = driver.getWindowHandles();
		System.out.println(windowStrings);
		
		//switch to the new window
//		driver.switchTo().window();

		//search for saved product by name
		WebElement searchByProductName = driver.findElement(By.id("SearchProductName"));
		searchByProductName.sendKeys(productName);
		
		//click on search button
		WebElement searchProductButton = driver.findElement(By.id("search-products"));
		searchProductButton.click();
		WebElement productCheckBox = driver.findElements(By.name("input[name=\"SelectedProductIds\"]")).get(0);
		productCheckBox.click();
		
		//assertion if is checked
		Assert.assertTrue(productCheckBox.isSelected());
		
		//save selected product
		WebElement saveProductsButton = driver.findElement(By.name("save"));
		saveProductsButton.click();
		//switch to default window
		
		//assertion for discount product table content
		WebElement discountProductTableContent = driver.findElement(By.cssSelector("#products-grid tbody tr"));
		String discountProductTableContentString  = discountProductTableContent.getText();
		Assert.assertTrue(discountProductTableContentString.contains(productName));
		
		//assertion for discount items number
		WebElement productGridInfo = driver.findElement(By.id("products-grid_info"));
		String productItemString = productGridInfo.getText();
		Assert.assertTrue(productItemString.contains("1"));
		
		WebElement editSaveElement = driver.findElement(By.name("save"));
		editSaveElement.click();
		
		//assertion for save button style after clicking
		Actions editSaveActions = new Actions(driver);
		editSaveActions.clickAndHold(editSaveElement).perform();
		Thread.sleep(5);
		String editSaveBackgroundColor = editSaveElement.getCssValue("background-color");
//				Assert.assertEquals(editSaveBackgroundColor, "rgba(0, 98, 204, 1)")
		
		//assertion for url after save edits
		boolean discountListUrl = driver.getCurrentUrl().contains("Discount/List/");
		Assert.assertTrue(discountListUrl);
	}

}
