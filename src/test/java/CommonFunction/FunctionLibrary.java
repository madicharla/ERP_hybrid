package CommonFunction;

import java.awt.Desktop.Action;
import java.io.FileInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

public class FunctionLibrary 
{
public static WebDriver driver;
public static Properties conpro;
//methods for launch Browser
public static WebDriver StartBrowser () throws Throwable 
{
	conpro = new Properties();
	conpro.load(new FileInputStream("./PropertyFile/Environment.properties"));
	if(conpro.getProperty("Brower").equalsIgnoreCase("CHROME")) 
	{
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}
		
		else if(conpro.getProperty("Browser").equalsIgnoreCase("FireFox"))
		{
			driver = new FirefoxDriver();
		}
		else
			
			{
				Reporter.log("nothing is matching",true);
				
			}
		
	
	return driver;
	
	}
// methods for open url
public static void openUrl(WebDriver driver) 
{
	driver.get(conpro.getProperty("Url"));
}
// methods for waiting
public static void waitforelement (WebDriver driver,String Locator_Type,String Locator_Value,String Test_data) 
{
	WebDriverWait myWait = new WebDriverWait(driver,Duration.ofSeconds(Integer.parseInt(Test_data)));
	if(Locator_Type.equalsIgnoreCase("id")) 
	{
		myWait.until(ExpectedConditions.visibilityOfElementLocated(By.id(Locator_Value)));
		
	}
	if(Locator_Type.equalsIgnoreCase("name")) 
	{
		myWait.until(ExpectedConditions.visibilityOfElementLocated(By.name(Locator_Value)));
	}
	if(Locator_Type.equalsIgnoreCase("Xpath")) 
	{
		myWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Locator_Value)));
	}
	
}
//methods for text boxes
public static void typeaction(WebDriver driver,String Locator_Type,String Locator_Value,String Test_data) 
{
	if(Locator_Type.equalsIgnoreCase("id")) 
	{
		driver.findElement(By.id(Locator_Value)).clear();
		driver.findElement(By.id(Locator_Value)).sendKeys(Test_data);
	}
	if(Locator_Type.equalsIgnoreCase("name")) 
	{
		driver.findElement(By.name(Locator_Value)).clear();
		driver.findElement(By.name(Locator_Value)).sendKeys(Test_data);
	}
	if(Locator_Type.equalsIgnoreCase("xpath")) 
	{
		driver.findElement(By.xpath(Locator_Value)).clear();
		driver.findElement(By.xpath(Locator_Value)).sendKeys(Test_data);
	}
}
//methods for click action
public static void clickaction(WebDriver driver,String Locator_Type,String Locator_value) 
{
	if(Locator_Type.equalsIgnoreCase("id")) 
	{
		driver.findElement(By.id(Locator_value)).click();
	}
	if(Locator_Type.equalsIgnoreCase("name")) 
	{
		driver.findElement(By.name(Locator_value)).click();
	
	}
	if(Locator_Type.equalsIgnoreCase("xpath")) 
	{
		driver.findElement(By.xpath(Locator_value)).click();
	
	}
}
//methods for validate title
public static void validatetitle(WebDriver driver,String Test_data)
{
	String Actual_title = driver.getTitle();
	try {
		Assert.assertEquals(Actual_title, Test_data, "not matching");
	} catch (Throwable t) {
		System.out.println(t.getMessage());
	}
}
	//methods for close browser
	public static void closebrower(WebDriver driver) 
	{
		driver.quit();
	}

//methods to generate date
	public static String generatedate()
	{
		java.util.Date date = new java.util.Date();
		DateFormat df = new SimpleDateFormat("yyyy-mm-dd hh-mm-ss");
		return df.format(date);
		
	}
	//methods for mouse click
	public static void mouseclick(WebDriver driver) throws Throwable 
	{
		Actions ac = new Actions(driver);
		ac.moveToElement(driver.findElement(By.xpath("//a[.='Stock Items ']"))).perform();
		Thread.sleep(2000);
		ac.moveToElement(driver.findElement(By.xpath("(//a[.='Stock Categories'])[2]"))).click().perform();
		
	}
	//methods for validate title
	public static void categorytable(WebDriver driver,String Test_data) throws Throwable 
	{
		if(! driver.findElement(By.xpath(conpro.getProperty("Serach_textbox"))).isDisplayed());
		driver.findElement(By.xpath(conpro.getProperty("Search_panel"))).click();
		driver.findElement(By.xpath(conpro.getProperty("Search_textbox"))).clear();
		driver.findElement(By.xpath(conpro.getProperty("Search_testbox"))).sendKeys(Test_data);
		driver.findElement(By.xpath(conpro.getProperty("Search_textbox"))).click();
		Thread.sleep(2000);
	}



}






