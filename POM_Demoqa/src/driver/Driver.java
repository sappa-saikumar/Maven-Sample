package driver;

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
	if(BrowserName.equalsIgnoreCase("firefox"))
	{
		driver= new FirefoxDriver();
	}
	
	else if(BrowserName.equalsIgnoreCase("Chrome"))
	{
		
		System.setProperty("webdriver.chrome.driver",Data.ReadFromPropertyFile("ChromePath") );
		driver= new ChromeDriver();
	}
	
	else if(BrowserName.equals("iexplore"))
	{
		DesiredCapabilities iecapabilities = DesiredCapabilities.internetExplorer();
		iecapabilities.setCapability(CapabilityType.BROWSER_NAME, "IE");
		iecapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);			
		//System.setProperty("webdriver.ie.driver", "C:\\Users\\Dell\\Desktop\\eclipse\\IEDriverServer.exe");
		File IEDriver = new File("IEDriverServer.exe");
		System.setProperty("webdriver.ie.driver", IEDriver.getAbsolutePath()); 
		driver=new InternetExplorerDriver(iecapabilities);
	}
	
	else
	{
		System.out.println("Invalid Browser, Please choose the relevant browser");
		System.exit(0);
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
