package testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.Base;
import pages.HomePage;
import pages.SearchPage;
import utils.Utility;

public class SearchTest extends Base{
	
	public WebDriver driver ;
	HomePage homePage ;
	SearchPage searchPage ;
	
	@BeforeMethod
	public void setup() {
		
		driver = initializeBrowserAndOpenApplication();
		homePage = new HomePage(driver);
		
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	@DataProvider(name = "ValidProduct")
	public Object[][] supplyTestData() {

		return Utility.getDataFromExcel("Search");

	}
	
	@Test(dataProvider="ValidProduct")
	public void seachWithExsistingProductNameFromHomePage(String productName) {
		
		searchPage = homePage.searchProduct(productName);
		Assert.assertTrue(searchPage.visibilityOfHpProduct());
		
	}
	@Test
	public void seachWithNonExsistingProductNameFromHomePage() {
		searchPage = homePage.searchProduct(prop.getProperty("InvalidProduct"));
		Assert.assertTrue(searchPage.retriveProductNotFoundErrorMessage().equals(prop.getProperty("InvalidProductMessage")));
	
	}
	
	

}
