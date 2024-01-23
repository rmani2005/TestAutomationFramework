package com.clover.solution.ui.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.clover.solution.ui.configuration.ConfigProvider;
import com.clover.solution.ui.objectFindStrategy.FindStrategy;
import com.clover.solution.ui.objectFindStrategy.FindStrategyPool;
import com.clover.solution.ui.objectFindStrategy.IdFindStrategy;
import com.clover.solution.ui.objectFindStrategy.XPathFindStrategy;
import com.clover.solution.ui.util.logs.Log;

public class jetblueHomePage extends BasePage 
{

	private String pageName;
	public jetblueHomePage(WebDriver driver) {
	        super(driver);
	        pageName=this.getClass().getName();
	        Log.info(pageName + "Creating page constractor and its super");
	       	    	
	}

	//Page Objects
	//FindStrategyPool find;
	//JetBlue.com objects
	
	By cookies_IFrame=new XPathFindStrategy("//iframe[contains(@title,'TrustArc Cookie Consent Manager')]").convert();
	
	By cookiesIunderstandCloseButton = new XPathFindStrategy("//a[contains(text(),'Accept All Cookies')]").convert();
	By homePageHeader = new XPathFindStrategy("//span[contains(text(),'Did someone say…cookies?')]").convert();
	
	By PricingButton = new XPathFindStrategy("//nav/a[@title='Pricing']").convert();
	By MenuButton = new XPathFindStrategy("//button[contains(@data-testid,'menu-button')]").convert();	
	By PricingButtonTab = new XPathFindStrategy("//div[@data-testid='nav-drawer']//span[text()='Pricing']").convert();
	By PricingSiteHeader= new XPathFindStrategy("//p[contains(text(),'Find the right solution to power your business')]").convert();
	

	By homePage_headerMenu_bookOption=new XPathFindStrategy("//a//span[contains(text(),'Book')]").convert();
	By homePage_headerMenu_bookOption_FlightSubOption=new XPathFindStrategy("//a//span[contains(text(),'Flights')]").convert();
	
	//textValidation = It’s so fly to see you.
	//text valdiation after book > flights= Feeling fly today?
	
	By Flights_Category = new XPathFindStrategy("//a//span[contains(text(),'Book')]").convert(); 
	By From = new XPathFindStrategy("//input[contains(@id,'jb-autocomplete-5-search')]").convert(); // value - Newark, NJ (EWR)
	By To = new XPathFindStrategy("//input[contains(@id,'jb-autocomplete-6-search')]").convert(); //Tampa, FL (TPA)
	By Depart = new XPathFindStrategy("//input[contains(@id,'jb-date-picker-input-id-4')]").convert(); // Tue Jan 23
	By Return = new XPathFindStrategy("//input[contains(@id,'jb-date-picker-input-id-5')]").convert();
	By SearchFlight = new XPathFindStrategy("//button//span//span[contains(text(),'Search flights')]").convert();
	
	//Loading time text - Stretch out.
	//Select Depart flight screen - text - Select your departing flight
	By sortandfilter_button=new XPathFindStrategy("//a[contains(text(),' Sort & Filter ')]").convert();
	
	//Sort screen
	By sort_default_button=new XPathFindStrategy("//span[contains(text(),' Default ')]").convert();
	By sort_optio_LowtoHighPrice_option=new XPathFindStrategy("//jb-select-option//span[contains(text(),'Price (low to high)')]").convert();
	By sortandfilter_Reset_button=new XPathFindStrategy("//jb-action//button//span[contains(text(),'Reset')]").convert();
	By sortandfilter_Apply_button=new XPathFindStrategy("//jb-action//button//span[contains(text(),'Apply')]").convert();
	
	By Iternary_List_first_best_price_fare_button=new XPathFindStrategy("(//div[contains(@class,'bg-off-white ng-star-inserted')]//jb-flight-detail-item)[1]//button").convert();
	
	By  Iternary_List_first_best_price_Blue_Basic=new XPathFindStrategy("(//div[contains(@class,'bg-off-white ng-star-inserted')]//jb-flight-detail-item)[1]//button//ancestor::jb-itinerary-panel//jb-expandable-container//div//h3[contains(text(),'Blue Basic')]").convert();
	By  Iternary_List_first_best_price_Blue=new XPathFindStrategy("(//div[contains(@class,'bg-off-white ng-star-inserted')]//jb-flight-detail-item)[1]//button//ancestor::jb-itinerary-panel//jb-expandable-container//div//h3[text()=' Blue ']").convert();
	By  Iternary_List_first_best_price_Blue_Extra=new XPathFindStrategy("(//div[contains(@class,'bg-off-white ng-star-inserted')]//jb-flight-detail-item)[1]//button//ancestor::jb-itinerary-panel//jb-expandable-container//div//h3[text()=' Blue Extra ']").convert();
	
	//Check this pop up
	//Unfortunately, your departure and return fares aren't compatible. If you'd like to keep Blue Extra for your departure, your return will also be updated to Blue Extra for an additional $
			
	By switch_to_Accept_button=new XPathFindStrategy("//button//span[contains(text(),'Accept')]").convert();	
	By switch_to_Blue_pop_up_SwitchToBlue_button=new XPathFindStrategy("//button//span[contains(text(),' Switch to Blue ')]").convert();
	
	By trip_summary_Page_header_text=new XPathFindStrategy("//*[contains(text(),' Trip Summary ')]").convert(); //Trip Summary
	By trip_summary_Page_checkout_button=new XPathFindStrategy("//button//span[contains(text(),' Next: Checkout ')]").convert();
	
	By price_summary_Page_text=new XPathFindStrategy("//*[contains(text(),' Price Summary ')]").convert(); // Price Summary 
	//By switch_to_Blue_pop_up_button=new XPathFindStrategy("").convert();
	//By trip_summary_Page_checkout_price_text=new XPathFindStrategy("").convert();
	//By switch_to_Blue_pop_up_button=new XPathFindStrategy("").convert();
	
	//Test Steps starts here
    public jetblueHomePage SiteCheck(String URLName,String AssertValue, String ExpectedPricingString)
    {
    	if(driver!=null) {
    		//String URL=ConfigProvider.getAsString("urlSettings."+ URLName);
			navigateToURL(URLName);
			
			//Moving into IFrame
			navigateToIFrame(element(driver,cookies_IFrame));
			
			//defaulToContext
			//Moving into IFrame
			EscapeFromIFrame();
			
			//Page Assert
			String actualString1 = getTheElementText(element(driver,homePageHeader));
			//assertEqual(actualString1,AssertValue);
			JSClick(element(driver, cookiesIunderstandCloseButton));
			//assertPageTitleContains(title);
			//PageURLContains(urlContians);
			
			//Page Assert
			String actualString = getTheElementText(element(driver,homePageHeader));
			assertEqual(actualString,AssertValue);
			
			if(ConfigProvider.getAsString("webSettings.defaultBrowser").equalsIgnoreCase("CHROMEHEADLESS")) {
				clickTheElement(element(driver, MenuButton));
				clickTheElement(element(driver, PricingButtonTab));
			}else
			{
				clickTheElement(element(driver, PricingButton));
			}
			
			//Page Assert - Pricing //Find the right solution to power your business
			String actualPricingString = getTheElementText(element(driver,PricingSiteHeader));
			assertEqual(actualPricingString,ExpectedPricingString);
    	}else{
    		Log.error("Error in Webdriver while Site Check");
    	}
		return this;	
    }
  
  /* 
    public HomePage goToN11() {
        Log.info("Opening N11 Website.");
        driver.get(baseURL);
        return this;
    }
    
    //Go to LoginPage
    public LoginPage goToLoginPage() {
        Log.info("Going to Login Page..");
        click(signInButtonClass);
        return new LoginPage(driver);
    }
    */
}