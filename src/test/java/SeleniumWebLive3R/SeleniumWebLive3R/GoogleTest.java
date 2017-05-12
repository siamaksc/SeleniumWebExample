package SeleniumWebLive3R.SeleniumWebLive3R;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleTest {

	private static WebDriver driver;
	private static WebDriverWait wait;
	private static String baseURL = "https://www.google.se";
	private static String expected = "";
	private static String actual = "";
	// Called once before all tests run
	@BeforeClass
	public static void setupOnce(){
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\siama\\workspace\\SeleniumWebExamples\\geckodriver.exe");
		driver = new FirefoxDriver();
		wait = new WebDriverWait(driver, 3);
		System.out.println("@BeforeClass setupOnce()");
	}
	
	@Before
	public void resetData(){
		driver.navigate().to(baseURL);
		System.out.println("@Before resetData()");
		expected = "";
		actual = "";
	}
	
	@Test (timeout = 3000)
	public void testResultStats(){
		//Prepare
		System.out.println("Preparing - testResultStats()");
		expected = "41 700";
		String input = "Testautomatisering";
		String inputId = "lst-ib";
		String searchId = "_fZl";
		String resultId = "resultStats";
		//Act
		System.out.println("Act - testResultStats()");
		driver.findElement(By.id(inputId)).sendKeys(input);
		driver.findElement(By.id(searchId)).click();
		WebElement result = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(resultId)));
		//Assert
		System.out.println("Assert - testResultStats()");
		Assert.assertTrue("ResultStatsTest Fail", result.getText().contains(expected));
	}
	
	@Test
	public void testGetTitle(){
		//Prepare
		System.out.println("Preparing - testGetTitle()");
		expected = "Google";
		//Act
		System.out.println("Act - testGetTitle()");
		actual = driver.getTitle();
		//Assert
		System.out.println("Assert - testGetTitle()");
		Assert.assertEquals("GetTitleTest Fail", expected, actual);
	}
	
	@After
	public void tearDown(){
		System.out.println("@After tearDown()");
	}
	
	@AfterClass
	public static void tearDownOnce(){
		System.out.println("@AfterClass tearDownOnce()");
		driver.close();
		try{
			Thread.sleep(5000);
		}catch(InterruptedException e){
			System.out.println(e.getStackTrace());
		}
		driver.quit();
	}
}