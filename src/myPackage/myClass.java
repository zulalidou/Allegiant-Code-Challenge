package myPackage;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class myClass {
	
	private static WebDriver driver = null;
	private static Double totalBillSoFar = 0.0;
	
	public static void main(String[] args) throws InterruptedException {
		
		// To access the jar files, we need to access their memory locations, which is what "System.setProperty" does
		System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
		driver = new ChromeDriver();
		
		// The implicit wait is to make sure that all WebElements have loaded.. since most WebElements load at different times
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		// Navigates to the allegiant website
		driver.get("https://www.allegiantair.com");
				
		landingPage();		
		flightPage();
		bundlePage();
		hotelPage();
		carDealsPage();
		travelersPage();
		
		driver.quit();
	}
	
	
	// This method sets all the information for the flight (i.e source/destination cities,
	// round trip or one way, departure/return dates, etc)
	private static void landingPage() throws InterruptedException {
		WebElement closeAdvertisement = driver.findElement(By.cssSelector("body > div.ui-dialog.ui-widget.ui-widget-content.ui-corner-all.ui-front.credit-card-overlay-wrapper.dialog-drupal.ui-draggable.ui-resizable > div.ui-dialog-titlebar.ui-widget-header.ui-corner-all.ui-helper-clearfix.ui-draggable-handle > button > span.ui-button-icon-primary.ui-icon.ui-icon-closethick"));
		closeAdvertisement.click();
		
		
		WebElement departure = driver.findElement(By.name("search_form[departure_city]"));
		departure.sendKeys(Keys.ENTER);	
		Thread.sleep(2000);
		
		WebElement destination = driver.findElement(By.name("search_form[destination_city]"));
		destination.sendKeys(Keys.ARROW_DOWN, Keys.ENTER);	
		Thread.sleep(2000);

		
		WebElement calendar1 = driver.findElement(By.cssSelector("#allegiant_searchform > div:nth-child(3) > div:nth-child(1) > div > div > div > button"));
		calendar1.click();	
		Thread.sleep(2000);
		
		WebElement departureDate = driver.findElement(By.cssSelector("#ui-datepicker-0-5-8 > a"));
		departureDate.click();
		Thread.sleep(2000);

		
		WebElement calendar2 = driver.findElement(By.cssSelector("#allegiant_searchform > div:nth-child(3) > div:nth-child(2) > div > div > div > button"));
		calendar2.click();	
		Thread.sleep(2000);
		
		WebElement returnDate = driver.findElement(By.cssSelector("#ui-datepicker-0-5-27 > a"));
		returnDate.click();
		Thread.sleep(2000);

		
		WebElement submit = driver.findElement(By.cssSelector("#submit-search"));
		submit.click();
	}
	
	
	// This method sums up the bills for the departure and returning flights
	private static void flightPage() throws InterruptedException {
		String departingFlightBill = driver.findElement(By.cssSelector("#flightchooser-departing > div > form > fieldset > div > div > ul > li > label > span.flight-availability-and-price > span > span.medium-up.price")).getText();
		int idx = departingFlightBill.lastIndexOf('$');
		departingFlightBill = departingFlightBill.substring(idx+1); // to remove the old price + the dollar sign for the new price
		
		String returningFlightBill = driver.findElement(By.cssSelector("#flightchooser-returning > div > form > fieldset > div > div > ul > li > label > span.flight-availability-and-price.limited-availability > span > span.medium-up.price")).getText();
		idx = returningFlightBill.lastIndexOf('$');
		returningFlightBill = returningFlightBill.substring(idx+1); // to remove the old price + the dollar sign for the new price
		
		totalBillSoFar = Double.parseDouble(departingFlightBill);
		totalBillSoFar += Double.parseDouble(returningFlightBill);
		
		
		Thread.sleep(2000);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");	// Scrolls to the bottom of the page
		Thread.sleep(2000);		
		
		WebElement goToBundles = driver.findElement(By.cssSelector("#flights > div.button-panel > div.button-wrapper > button"));
		goToBundles.click();
	}
	
	
	// Not much is done here; it skips the bundles and moves on to the next section
	private static void bundlePage() throws InterruptedException {
		Thread.sleep(2000);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(2000);	
		
		WebElement skipBundles = driver.findElement(By.cssSelector("#package > div.no-selection > a"));
		skipBundles.click();
	}
	
	
	// Not much is done here; it skips the hotel packages and moves on to the next section.
	private static void hotelPage() throws InterruptedException {
		Thread.sleep(2000);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(2000);	
		
		WebElement skipHotelPackages = driver.findElement(By.cssSelector("#hotelchooser > div.no-selection > a"));
		skipHotelPackages.click();
	}
	
	
	// Not much is done here; it skips the car deals and moves on to the next section
	private static void carDealsPage() throws InterruptedException {
		Thread.sleep(2000);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(2000);	
		
		WebElement skipCarDeals = driver.findElement(By.cssSelector("#transport > div:nth-child(3) > div.no-selection > a"));
		skipCarDeals.click();
	}
	

	// This method checks to see if the sum of all the options selected is equal to the current bill displayed
	// If yes, the webdriver gets closed; else, an error gets displayed
	private static void travelersPage() throws InterruptedException {
		Thread.sleep(2000);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollTo(0, 400)");
		Thread.sleep(2000);	
		
		String currentBill = driver.findElement(By.cssSelector("#pricing > div > table > tbody.allegiant_price.price.allegiant_models_price_total.total.positive > tr > td")).getText();
		
		currentBill = currentBill.substring(1); // to remove the dollar sign
		Double d_currentBill = Double.parseDouble(currentBill);
		
		
		System.out.println("\ntotalBillSoFar = " + totalBillSoFar);
		System.out.println("currentBill = " + d_currentBill);

		
		assert (totalBillSoFar.equals(d_currentBill)) : "The total does NOT match the prices  of all selected items";
		
		System.out.println("\nThe total matches the prices of all selected items");
	}
}
