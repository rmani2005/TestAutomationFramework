package com.clover.solution.ui.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.clover.solution.ui.configuration.ConfigProvider;
import com.clover.solution.ui.objectFindStrategy.FindStrategy;
import com.clover.solution.ui.objectFindStrategy.FindStrategyPool;
import com.clover.solution.ui.objectFindStrategy.IdFindStrategy;
import com.clover.solution.ui.objectFindStrategy.XPathFindStrategy;
import com.clover.solution.ui.util.logs.Log;

public class myPortfolio extends BasePage 
{

	private String pageName;
	public myPortfolio(WebDriver driver) {
	        super(driver);
	        pageName=this.getClass().getName();
	        Log.info(pageName + "Creating page constractor and its super");
	       	    	
	}
	
	//Elements
	By ProjectButtonElement = new XPathFindStrategy("//div[contains(@class,'HeaderStyles')]//a[text()='Projects']").convert();
	By hero_text_assert_element = new XPathFindStrategy("//div[contains(@class,'Hero')]//h2[contains(@class,'GlobalComponents__SectionTitle-sc-6qzy6y-1 jKjqRH')]").convert();
	By project_text_assert_element = new XPathFindStrategy("//h2[contains(text(),'Projects')]").convert(); //Projects
	By project_section_text_assert_element = new XPathFindStrategy("//section[contains(@id,'projects')]").convert();
  
    //Test Steps starts here
    public myPortfolio checkMySite(String URLName,String hero_text_assert,String project_text_assert )
    {
    	if(driver!=null) {
    		String URL=ConfigProvider.getAsString("urlSettings."+ URLName);
			navigateToURL(URL);
			//Page title Assertion - cnbc - Google Search
			//Assert the page text some thing like header	
			
			//clickTheElement(this.element(driver, idFindStrategyLogginButton.convert()));
			//Assert the Value
			String actualString = getTheElementText(element(driver,hero_text_assert_element));
			assertPageContentContains(hero_text_assert,actualString);
			
			clickTheElement(element(driver, ProjectButtonElement));
			
			//Assert the Element
			element(driver,project_text_assert_element);
			
			//Assert the Value
			actualString = getTheElementText(element(driver,project_text_assert_element));
			assertPageContentContains(project_text_assert,actualString);
				
    	}else{
    		Log.error("Error in Webdriver while searchAndAssert");
    	}
		return this;	
    }
    
    public myPortfolio gotoURL(String URLVariable)
    {
    	if(driver!=null) {
    		String URL=ConfigProvider.getAsString("urlSettings."+ URLVariable);
			navigateToURL(URL);
    	}else{
    		Log.error("Error in Webdriver while searchAndAssert");
    	}
		return this;
    }
    
    public myPortfolio enterValueInSearchTextBox(String SearchValue)
    {
    	if(driver!=null) {
    		enterTheTextAndTab(element(driver, project_section_text_assert_element), SearchValue);
    	}else{
    		Log.error("Error in Webdriver while searchAndAssert");
    	}
		return this;
    }
    
    
    public myPortfolio clickGoogleSearchButton()
    {
    	if(driver!=null) {
    		clickTheElement(element(driver, project_section_text_assert_element));
    	}else{
    		Log.error("Error in Webdriver while searchAndAssert");
    	}
		return this;
    }
    
    public myPortfolio assertTheFirstValue(String AssertValue)
    {
    	if(driver!=null) {
    		String actualString = getTheElementText(element(driver,project_section_text_assert_element));
			assertEqual(actualString,AssertValue);
    	}else{
    		Log.error("Error in Webdriver while searchAndAssert");
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