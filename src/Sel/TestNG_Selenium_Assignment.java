package Sel;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver; 
import org.testng.annotations.*;
import org.testng.Assert;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
  
public class TestNG_Selenium_Assignment 
{ 
	public WebDriver driver;
	public WebDriverWait explicitWait;
	public String productName;
	public int productQuantity;
	public String orderNo;
	
	@BeforeTest
	public void InitWebDriver()
	{   		
		String driverPath;		
		driverPath = "D:\\geckodriver.exe"; 
		productName = "Product1169";
		productQuantity=50;		
		driver = new FirefoxDriver();
		explicitWait = new WebDriverWait(driver, 30);
		System.setProperty("geckodriver.exe", driverPath);	
	}
	
	@Test (priority = 1)
	public void LoginToAspireApp()
	{   
		String baseUrl;
		
		baseUrl ="https://aspireapp.odoo.com/web/login/";
		driver.get(baseUrl);	    
	    driver.findElement(By.xpath("//*[@id=\'login\']")).sendKeys("user@aspireapp.com");
		driver.findElement(By.xpath("//*[@id=\'password\']")).sendKeys("@sp1r3app");
		driver.findElement(By.xpath("/html/body/div[1]/main/div/div/div/form/div[3]/button")).click();
		
	}
	
	@Test (priority = 2)
	//Explicit wait as Inventory feature taking some time to appear and then Click on Inventory
	public void NavigateToInventory()
	{		
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div/div[2]/div/a[2]/div[1]")));		
		driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div/a[2]/div[1]")).click();
	}
	
	
    @Test (priority = 3)
    public void CreateNewProduct() 
    {	
    	//Select Product - Products menu
    	explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/header/nav/div[1]/div[2]/button/span")));
    	driver.findElement(By.xpath("/html/body/header/nav/div[1]/div[2]/button/span")).click();
    	driver.findElement(By.xpath("/html/body/header/nav/div[1]/div[2]/div/a[1]")).click();
	
	//Create new Product
    	explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div/div[1]/div[2]/div[1]/div/div/button")));
		driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div[2]/div[1]/div/div/button")).click();
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".o_form_button_save")));
		driver.findElement(By.xpath("//*[@id=\'o_field_input_11\']")).click();
		driver.findElement(By.xpath("//*[@id=\'o_field_input_11\']")).sendKeys(productName);
		//button.btn-primary:nth-child(4)driver.findElement(By.cssSelector(".o_form_button_save")).click();
		
		//Press Save Button
		driver.findElement(By.cssSelector(".o_form_button_save")).click();
  }
    
  @Test (priority = 4)
  public void UpdateQuantity()
  {
	//Update Quantity Tab click	
	driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div/div[1]/div[1]/div/button[2]/span")).click();
	
	//Click on Crate to enable add quantity
	explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button.btn-primary:nth-child(4)")));
	driver.findElement(By.cssSelector("button.btn-primary:nth-child(4)")).click(); 
	WaitMethod(9000);

	//Add quantity
	driver.findElement(By.cssSelector("input.o_field_float")).clear();
	driver.findElement(By.cssSelector("input.o_field_float")).sendKeys(""+ productQuantity+"");	
	
	//SAVE BUTTON CLICK
	explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button.btn-primary:nth-child(1)")));
	driver.findElement(By.cssSelector("button.btn-primary:nth-child(1)")).click();  
  }
  
  @Test(priority = 5)
  public void ClickOnAppIcon()
  {	 
	  explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/header/nav/a[1]")));
	  driver.findElement(By.xpath("/html/body/header/nav/a[1]")).click();
  }
  
  @Test(priority = 6)
  public void CreateManufactingOrder() 
  {		
	  explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div/div[2]/div/a[3]/div[1]")));
	  driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div/a[3]/div[1]")).click();
		
	  //Create button click
	  explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div/div[1]/div[2]/div[1]/div/div/button[3]")));
	  driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div[2]/div[1]/div/div/button[3]")).click();
		
	  WaitMethod(5000);
	
	  //Enter Product Name
	  driver.findElement(By.xpath("//*[@class='o_input ui-autocomplete-input']")).sendKeys(productName);
	  driver.findElement(By.xpath("//*[@class='o_input ui-autocomplete-input']")).sendKeys(Keys.TAB);
	  WaitMethod(5000);
	  driver.findElement(By.xpath("/html/body/div[2]/div[5]/div/div/div/footer/button[1]/span")).click();
		
	  //Click on confirm button on new child window
	  driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div/div[1]/div[1]/div[1]/button[5]/span")).click();
		
		
	  //Wait till Mark as done visible
	  WaitMethod(5000);	
  }
  
  @Test(priority = 7)
  public void UpdateOrderStatus()
  {
	//Mark as done button press
	driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div/div[1]/div[1]/div[1]/button[4]/span")).click();
	WaitMethod(5000);
		
	//Extra confirmation button press
	driver.findElement(By.xpath("/html/body/div[2]/div[5]/div/div/div/footer/button[1]/span")).click();
	WaitMethod(5000);
	driver.findElement(By.xpath("/html/body/div[2]/div[5]/div/div/div/div/footer/div/footer/button[1]/span")).click();
			
  }
  @Test(priority = 8)
  public void ValidateOrder()
  {
	  //Read Product number and quantity set in Test 7
	  //Go to Manufacturing and search with Order number which was get in Test 7
	  //Read Product number and quantity 
	  //Compare product number and quantity - Actual and expected values and declare result
  
  }
  
  public void WaitMethod(long mSecDelay)
  {
	  try 
		{
			Thread.sleep(mSecDelay);
		} 
		catch (InterruptedException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
  }
  
	
  @AfterTest
  public void close()
  {
	  //driver.quit();
  }
  
}  
