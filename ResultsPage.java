
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class ResultsPage {

	WebDriver browserDriver;
	WebDriverWait explicitWait;
	
	JavascriptExecutor jsExecutor;
		
	String siteTitle = " - Best Buy Canada";
		
	String siteUrl = "http://www.bestbuy.ca/en-CA/category/";	
	
	String nextPageLocator = "document.querySelector(\"li[class='pagi-next'] > a\")";
	By selectedPageLocator = By.xpath("(//li[@class='current'])[1]");
		
		
	public ResultsPage(WebDriver driver) throws Exception {
		browserDriver = driver;
		explicitWait = new WebDriverWait(browserDriver, 30);		

		jsExecutor = (JavascriptExecutor) driver;	
		if (isTitleCorrect() == false || 
			isUrlCorrect() == false )
			throw new Exception("The page is not loaded correctly!");		
	}
		
	
	public ResultsPage(WebDriver driver, int pageNumber) throws Exception {
		this(driver);		//this calls the previous constructor that has 1 parameter only
		
		if (displayedPageInUrl(pageNumber) == false)
			throw new Exception("page number is not in the url");		
	}
		
	
	
	private boolean displayedPageInUrl(int pageNumber) {
		return explicitWait.until(ExpectedConditions.urlContains("page=" + pageNumber));		
	}
	
	
	public ResultsPage clickPage(int pageNumber) throws Exception {			
		clickJS(pageLocator(pageNumber));			
		return new ResultsPage(browserDriver, pageNumber);
	}
		
	
	private String pageLocator(int pageNumber) {
		return "document.querySelector(\"a[data-page='" + pageNumber + "']\")";
	}
		
	
	public ResultsPage clickPageNext() throws Exception {				
		int seledtedPage = selectedPage();
		clickPageJS(nextPageLocator);			
		return new ResultsPage(browserDriver, seledtedPage + 1 );
	}
				
	
	private void clickPageJS(String pageSelector) throws InterruptedException {
		clickJS(pageSelector);
		waitUntilPageLoaded();
	}
	
	
	public boolean displayedPageInUrl(String pageNum) {
		return explicitWait.until(ExpectedConditions.urlContains("page=" + pageNum));
	}
		
				
	public int selectedPage() {		
		WebElement selectedPageElement = findIfVisible(selectedPageLocator);
		WebElement pageElement = selectedPageElement.findElement(By.tagName("a"));
		return Integer.parseInt(pageElement.getText());
	}
	
	
	private void clickJS(String locator) {
		String jsQuery = String.format("%s.click()", locator);		
		jsExecutor.executeScript(jsQuery);	
	}
		
			
	private WebElement findIfVisible(By locator) {
		return explicitWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
		
		
	private boolean isTitleCorrect() {
		return explicitWait.until(ExpectedConditions.titleContains(siteTitle));
	}
		
		
	private boolean isUrlCorrect() {
		return explicitWait.until(ExpectedConditions.urlContains(siteUrl));
	}
				
		
	private void waitUntilPageLoaded() throws InterruptedException {
		Boolean isLoaded = false;		
		while (!isLoaded) {
			isLoaded = isPageLoaded();
			delay(1);
		}
	}


	private Boolean isPageLoaded() {
		String jsQuery = "function pageLoaded() " + 
						 "{var loadingStatus = " + "(document.readyState == 'complete');" + 
				         "return loadingStatus; }; " + 
						 "return pageLoaded()";
		return (Boolean)jsExecutor.executeScript(jsQuery);
	}

			
	private void delay(int seconds) throws InterruptedException {
		Thread.sleep(seconds * 1000);		
	}	
	
}
