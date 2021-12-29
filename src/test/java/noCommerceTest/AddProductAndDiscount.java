package noCommerceTest;



import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
		.until(ExpectedConditions.visibilityOf(driver.findElements(By.cssSelector("ul[data-widget=\"treeview\"] li")).get(1)));
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
		WebElement ProductInfoCard = driver.findElement(By.cssSelector("div[data-card-name=\"product-info\"]"));
		
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
		Assert.assertEquals(selectedAttributeString, "true");

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
		WebElement pricesCard = driver.findElement(By.cssSelector("div[data-card-name=\"product-price\"]"));
				
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
		
		//assertion for price label
		Assert.assertEquals(priceLabel.getText(), "Price");
		
		//get tooltip icon for price
		WebElement priceToolTip = driver.findElements(By.cssSelector("#product-price div[data-toggle=\"tooltip\"")).get(0);
		Actions priceActionProvider = new Actions(driver);
		priceActionProvider.moveToElement(priceToolTip).perform();
		
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
//		Thread.sleep(3);
//		Assert.assertEquals(saveBackgroundColor, "rgba(68, 129, 166, 1)");
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
		System.out.println(productTableString);
		System.out.println(productName);
		boolean checkProductName = productTableString.contains(productName);
		//assertion for product name saved
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
		
	}

}
