package noCommerceTest;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class AddProductAndDiscount {

	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();
		driver.get("https://admin-demo.nopcommerce.com/");
		driver.manage().window().maximize();
		
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
		//assertion for current url
		boolean checkHomeUrl= driver.getCurrentUrl().contains("admin/");
		Assert.assertTrue(checkHomeUrl);
		
		//get catalog item
		WebElement catalogElement = driver.findElement(By.linkText("Catalog"));
		catalogElement.click();
		//assertion for expand menu
		boolean expandClass =driver.findElements(By.cssSelector("ul[data-widget=\"treeview\"] li")).get(1).getAttribute("class").contains("menu-open");
//		Assert.assertTrue(expandClass);
		
		//get product item
		WebElement productElement = driver.findElement(By.cssSelector("li a[href*=\"Product/List\"]")); 
		productElement.click();
		//assertion for current url
		boolean productListUrl = driver.getCurrentUrl().contains("Admin/Product/List");
		Assert.assertTrue(productListUrl);
	}

}
