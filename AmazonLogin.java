package kdfDemo;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AmazonLogin {
	public WebDriver driver;
	  @Test
	  public void MyTest() throws Exception 
	  {
		  XSSFWorkbook wb= new XSSFWorkbook("C:\\Mind Q\\Selenium\\Programs\\mnvDemo\\src\\test\\resources\\keywords.xlsx");
		  XSSFSheet ws= wb.getSheet("Sheet1");
		  int rows= ws.getPhysicalNumberOfRows();
		  
		  for(int i=1; i<rows; i++)
		  {
			  String cTestCase= ws.getRow(i).getCell(0).getStringCellValue();
			  String cLocatorType = ws.getRow(i).getCell(1).getStringCellValue();
			  String cLocatorValue = ws.getRow(i).getCell(2).getStringCellValue();
			  String cTestData = ws.getRow(i).getCell(3).getStringCellValue();
			  
			  switch(cTestCase)
			  {
			  case "open_Browser":
				  if(cTestData.equalsIgnoreCase("Chrome"))
				  {
					  WebDriverManager.chromedriver().browserVersion("123").setup();
					  driver = new ChromeDriver();
				  }
				  else if (cTestData.equalsIgnoreCase("Firefox"))
				  {
					  WebDriverManager.firefoxdriver().browserVersion("124").setup();
					  driver = new FirefoxDriver();
				  }
				  driver.manage().window().maximize();
				  break;
				  
			  case "enter_Url":
				  driver.get(cTestData);
				  break;
				  
			  case "click_Button":
				  WebElement btn= driver.findElement(Locator(cLocatorType, cLocatorValue));
				  btn.click();
				  break;
				  
			  case "enter_Text":
				  WebElement txt= driver.findElement(Locator(cLocatorType, cLocatorValue));
				  txt.sendKeys(cTestData);
				  break;
				  
			  }
		  }
	  }
	  @Test
	  public By Locator(String lType, String lValue)
	  {
		  By b= null;
		  
		  switch(lType)
		  {
		  case "id":
			  b= By.id(lValue);
			  break;
			  
		  case "name":
			  b= By.name(lValue);
			  break;
			  
		  case "class":
			  b= By.className(lValue);
			  break;
			  
		  case "tag":
			  b= By.tagName(lValue);
			  break;
			  
		  case "css":
			  b= By.cssSelector(lValue);
			  break;
			  
		  case "xpath":
			  b= By.xpath(lValue);
			  break;
			  
		  case "lt":
			 b= By.linkText(lValue);
				  break;
				  
		  case "plt":
		     b= By.partialLinkText(lValue);
					  break;
		  }
		  return b;
	  }
}
