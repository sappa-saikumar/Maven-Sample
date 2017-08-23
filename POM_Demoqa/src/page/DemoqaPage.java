package page;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import data.Data;
public class DemoqaPage
{

	
	public  WebDriver driver;
	@FindBy(css="input[name='userName']")
	private WebElement UserName;
	
	
	@FindBy(css="input[name='password']")
	private WebElement Password;
	
	
	@FindBy(css="input[name='login']")
	private WebElement Signin;
	
	@FindBy(css="select[name='passCount']")
	private WebElement Passengers;
	
	@FindBy(css="select[name='fromPort']")
	private WebElement Departing_Form;
	
	
	@FindBy(css="select[name='fromMonth']")
	private WebElement On;
	
	@FindBy(css="select[name='fromDay']")
	private WebElement FromDay;
	
	@FindBy(css="select[name='toPort']")
	private WebElement ArrivingIn;
	
	@FindBy(css="select[name='toMonth']")
	private WebElement Returning;
	
	@FindBy(css="select[name='toDay']")
	private WebElement ToDay;
	
	@FindBy(css="input[name='findFlights']")
	private WebElement ContinueButton;
	
	
	
	public DemoqaPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	
	public void Login() throws IOException, InterruptedException
	{
		
		
		UserName.sendKeys(Data.getData("Username"));
		Password.sendKeys(Data.getData("Password"));
		Signin.click();
		Thread.sleep(2000);
	}
	
	public void FlightFinder() throws IOException, InterruptedException
	{
		Select select = new Select(Passengers);
		select.selectByVisibleText("2");
		
		Thread.sleep(1000);
		
		Select select1 = new Select(Departing_Form);
		select1.selectByVisibleText("London");
		Thread.sleep(1000);
		
		Select select2 = new Select(On);
		select2.selectByVisibleText("April");
		Thread.sleep(1000);
		
		
		Select select3 = new Select(FromDay);
		select3.selectByVisibleText("15");
		Thread.sleep(1000);
		
		
		Select select4 = new Select(ArrivingIn);
		select4.selectByVisibleText("Paris");
		Thread.sleep(1000);
		
		Select select5 = new Select(Returning);
		select5.selectByVisibleText("May");
		Thread.sleep(1000);
		
		Select select6 = new Select(ToDay);
		select6.selectByVisibleText("10");
		Thread.sleep(1000);
		
		driver.findElement(By.cssSelector("input[value='Business']")).click();
		
		Thread.sleep(2000);
		
		ContinueButton.click();
		Thread.sleep(2000);
		
	}
	
	
	
	
	
	
	
}
