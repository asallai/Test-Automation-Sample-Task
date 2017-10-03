
import static org.junit.Assert.assertTrue;
import org.junit.Test;


public class BestBuyTest extends BaseTest {
	
	@Test
	public void TestPaging() throws Exception {
				
		HomePage homePage = new HomePage(driver);	
		ResultsPage resultsPage = homePage.open().search("iphone");
		
		resultsPage = resultsPage.clickPage(4);
		assertTrue(resultsPage.selectedPage() == 4);
		
		resultsPage = resultsPage.clickPageNext();		
		assertTrue(resultsPage.selectedPage() == 5);
	}
			
}
