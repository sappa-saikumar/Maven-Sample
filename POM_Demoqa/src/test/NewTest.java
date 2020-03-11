package test;

import org.testng.annotations.Test;

import browser.Driver;
import page.DemoqaPage;

public class NewTest extends Driver
{

	@Test
	public void TestCase1() throws Exception
	{
		DemoqaPage obj  = new DemoqaPage(driver);
		obj.Login();
		obj.FlightFinder();
		getscreenshot();
	}
}
