
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseTest {

	WebDriver driver;
	WebDriverWait wait;		
		
		
	@Before
	public void setUp() {		
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\BrowserDrivers\\chromedriver.exe");	
		driver = new ChromeDriver();				
		wait = new WebDriverWait(driver, 30);
		driver.manage().window().maximize();		
	}
		
	
	@After
	public void tearDown() {		
		driver.quit();
	}
			
}
