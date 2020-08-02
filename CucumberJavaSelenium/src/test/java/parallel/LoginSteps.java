package parallel;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginSteps {
	WebDriver driver = null;
	
	@After
	public void CleanUp(Scenario scenario) {
		if(scenario.isFailed()) {
			
		TakesScreenshot scrnShot = (TakesScreenshot)driver;
		byte[] data = scrnShot.getScreenshotAs(OutputType.BYTES);
		scenario.embed(data, "image/png");
		
		}
	}


	@Given("user is on login page")
	public void user_is_on_login_page() {

		String projectPath = System.getProperty("user.dir");
		System.out.println("Project path is: "+projectPath);
		
		System.setProperty("webdriver.chrome.driver", projectPath+"./Drivers/chromedriver.exe");
		
		
		
		//System.setProperty("webdriver.chrome.driver","C:\\Users\\md\\Desktop\\SeleniumScripts\\chromedriver.exe");
		driver = new ChromeDriver();
		String baseURL = "http://admin-demo.nopcommerce.com";
		driver.get(baseURL);
		driver.manage().window().maximize();

		System.out.println("user is on login page");

	}

	@When("user enters username and password")
	public void user_enters_username_and_password() {

		driver.findElement(By.id("Email")).clear();
		driver.findElement(By.id("Email")).sendKeys("admin@yourstore.com");
		driver.findElement(By.id("Password")).clear();
		driver.findElement(By.id("Password")).sendKeys("admin");

		System.out.println("user enters username and password");

	}

	@When("clicks on login button")
	public void clicks_on_login_button() {
		
		driver.findElement(By.xpath("/html/body/div[6]/div/div/div/div/div[2]/div[1]/div/form/div[3]/input")).click();
		System.out.println("clicks on login button");

	}

	@Then("user is nagivated to the home page")
	public void user_is_nagivated_to_the_home_page() throws Throwable {

		// Screenshot
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(source, new File("./Screenshots/yourstore.png"));


		String my_title = driver.getTitle();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		System.out.println("TITLE IS "+my_title);

		String expected_title = "Dashboard / nopCommerce administration";

		Assert.assertTrue(my_title.contains("Dashboard / nopCommerce administration"));

		System.out.println("Test completed- page verifried");

		System.out.println("user is nagivated to the home page");


	}

	@Then("search for gift card under products")
	public void search_for_gift_card_under_products() throws Throwable {

		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/ul/li[3]/a"));

		driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/ul/li[2]")).click();
		
		driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/ul/li[2]/ul/li[1]/a/span")).click();

		driver.findElement(By.id("SearchProductName")).sendKeys("$25 Virtual Gift Card");
		driver.findElement(By.id("search-products")).click();


		//Screenshot
		TakesScreenshot ts1 = (TakesScreenshot)driver;
		File source1 = ts1.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(source1, new File("./Screenshots/gift.png"));


	}

	@Then("user search for customer emails display validation")
	public void user_search_for_customer_emails_display_validation() throws Throwable {

		driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/ul/li[4]/a/span")).click();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/ul/li[4]/ul/li[1]/a/span")).click();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//*[@id=\"SearchEmail\"]")).sendKeys("victoria_victoria@nopCommerce.com");
		driver.findElement(By.xpath("//*[@id=\"SearchFirstName\"]")).sendKeys("Victoria");
		driver.findElement(By.xpath("//*[@id=\"SearchLastName\"]")).sendKeys("Terces");
		driver.findElement(By.xpath("//*[@id=\"search-customers\"]")).click();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		Boolean isDisplayed = driver.findElement(By.xpath("//*[@id=\"customers-grid\"]/tbody/tr/td[2]")).isDisplayed();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		if (isDisplayed) {
			System.out.println("email is displayed");
		} else {
			System.out.println("emai is not displayed");
		}

		//Screenshot
		TakesScreenshot ts2 = (TakesScreenshot)driver;
		File source2 = ts2.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(source2, new File("./Screenshots/emails.png"));


	}

	@Then("click logout from the page")
	public void click_logout_from_the_page() throws Throwable {

		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/ul/li[3]/a")).click();

		Boolean isDisplayed = driver.findElement(By.xpath("/html/body/div[6]/div/div/div/div/div[2]/div[2]/div[1]/h2")).isDisplayed();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		if (isDisplayed) {
			System.out.println("Defaults for admin area is displayed");
		} else {
			System.out.println("Defaults for admin area is not displayed");
		}

		//Screenshot
		TakesScreenshot ts3 = (TakesScreenshot)driver;
		File source3 = ts3.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(source3, new File("./Screenshots/Defaults.png"));


		driver.quit();
	}

	    
	}

	



