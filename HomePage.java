
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class HomePage {

	WebDriver browserDriver;
	WebDriverWait explicitWait;
		
	String siteUrl = "http://www.bestbuy.ca/";
	String siteTitle = "Computers, TVs, Video Games & Appliances - Best Buy Canada";
		
	By searchLocator = By.id("ctl00_MasterHeader_ctl00_uchead_GlobalSearchUC_TxtSearchKeyword");
	By searchBtnLocator  = By.className("icon-search");
		
				
	public HomePage(WebDriver driver) {	
		browserDriver = driver;
		explicitWait = new WebDriverWait(browserDriver, 30);		
	}
		
			
	public HomePage open() throws Exception {		
		browserDriver.get(siteUrl);		
		if (isTitleCorrect() == false || 
			isUrlCorrect() == false )
			throw new Exception ("The home page is not loaded correctly!");			
		return this;
	}

		
	public ResultsPage search(String keyword) throws Exception {
		findIfClickable(searchLocator).click();
		browserDriver.findElement(searchLocator).sendKeys(keyword);
		findIfClickable(searchBtnLocator).click();
		return new ResultsPage(browserDriver);
	}  
		
			
	private WebElement findIfClickable(By locator) {
		return explicitWait.until(ExpectedConditions.elementToBeClickable(locator));
	}
		
		
	private boolean isTitleCorrect() {	
		return explicitWait.until(ExpectedConditions.titleIs(siteTitle));
	}
		
		
	private boolean isUrlCorrect() {			
		return explicitWait.until(ExpectedConditions.urlToBe(siteUrl));		
	}	
					
}
