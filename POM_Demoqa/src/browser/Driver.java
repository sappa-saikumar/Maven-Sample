package browser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import data.Data;

public class Driver
{
	public static WebDriver driver=null;

	public void loadBrowser(String BrowserName)
	{
		switch(BrowserName){
		case "Chrome":
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"//drivers//chromedriver.exe");
			driver= new ChromeDriver();
			break;

		case "InternetExplorer":
			System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"//drivers//IEDriverServer.exe");
			DesiredCapabilities iecapabilities = DesiredCapabilities.internetExplorer();
			iecapabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
			iecapabilities.setCapability(InternetExplorerDriver.INITIAL_BROWSER_URL, false);
			iecapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);			
			driver=new InternetExplorerDriver(iecapabilities);
			break;

		case "Firefox":
			System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"//drivers//Geckodriver.exe");
			driver= new FirefoxDriver();
			break;

		default:{
			System.out.println("Invalid Browser, Please choose the relevant browser");
			System.exit(0);
		}
		}

	}

	@BeforeClass
	public void loadBrowser() throws FileNotFoundException, IOException
	{
		Data.loadPropertyFile();
		loadBrowser(Data.ReadFromPropertyFile("Browsers"));
	}

	@BeforeMethod
	public void setUp()
	{
		driver.manage().window().maximize();
		driver.get(Data.ReadFromPropertyFile("URL"));
		driver.manage().deleteAllCookies();


	}
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}

	public static void getscreenshot() throws Exception 
	{
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

		FileUtils.copyFile(scrFile, new File("screenshot.png"));

		Date d = new Date();
		Timestamp t = new Timestamp(d.getTime());
		String timeStamp = t.toString();
		timeStamp = timeStamp.replace(' ', '_');
		timeStamp = timeStamp.replace(':', '_');

		FileUtils.copyFile(scrFile, new File("429636"+ "_" + timeStamp + ".jpg"));

	}	
}
