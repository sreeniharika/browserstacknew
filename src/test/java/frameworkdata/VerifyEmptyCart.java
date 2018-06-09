/**
 * 
 */
package frameworkdata;


import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.net.URL;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.LogStatus;

import uiActions.util;

/**
 * @author Niharika
 *
 * 
 */

public class VerifyEmptyCart extends testbase.base {
   SoftAssert softAssert = new SoftAssert();
	
	 private static Logger log = LogManager.getLogger(VerifyEmptyCart.class.getName());
	 util u = new util();
	 testbase.Config config = new testbase.Config(prop);

	 public static final String USERNAME = "niharika19";
	 public static final String AUTOMATE_KEY = "hHgdnVvMTDRFrxJZ23ay";
	 public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";


	 @BeforeTest
	public void driverinitialize() throws IOException{
		test = rep.startTest("VerifyEmptyCart");
		test.log(LogStatus.INFO, "starting the test VerifyEmptyCart");
	//	initializeDriver();
	//	log.info("Browser initialized");
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("browser", "Firefox");
		caps.setCapability("browser_version", "61.0 beta");
		caps.setCapability("os", "Windows");
		caps.setCapability("os_version", "8.1");
		caps.setCapability("resolution", "1024x768");
	    WebDriver driver = new RemoteWebDriver(new URL(URL), caps);
	    testbase.Config config = new testbase.Config(prop);
	    driver.get(config.getUrl());
	}
	 
	
	@Test
	public void cartItems() throws IOException, InterruptedException{
		
	//	testbase.Config config = new testbase.Config(prop);
	//	driver.get(config.getUrl());
		test.log(LogStatus.PASS, "opened url");
		log.info("Navigated to URL");
        u.click("0_items_button_xpath");
		test.log(LogStatus.PASS, "Clicked on cart Button");
		log.info("Clicked on cart button");
		
		u.waitToLoad();
		
		util.isElementDisplayed(getElement("cart_empty_text_xpath"));
		
		
		test.log(LogStatus.PASS, "element is displayed");
		
	    u.verifyTextEquals(getElement("cart_empty_text_xpath"), OR.getProperty("cartempty"));
		test.log(LogStatus.PASS, "Text is matched");
		
	    Actions a=new Actions(driver);
		a.moveToElement(getElement("desktop_navbar_xpath")).build().perform();
		test.log(LogStatus.PASS, "mouseover performed");
		
		test.log(LogStatus.INFO, "Test passed");
		softAssert.assertAll();
		for (String s : Reporter.getOutput()) { 
		    rep.setTestRunnerOutput(s); 
		}
	}

	
		
	@AfterTest
	public void closeBrowser(){
		driver.close();
		driver = null; 
		
		rep.endTest(test);
		rep.flush();
	}
	}

