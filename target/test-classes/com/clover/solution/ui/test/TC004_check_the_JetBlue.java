package com.clover.solution.ui.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.clover.solution.ui.browser.DriverManager;
import com.clover.solution.ui.configuration.ConfigProvider;
import com.clover.solution.ui.page.CloverPage;
import com.clover.solution.ui.page.SearchPage;
import com.clover.solution.ui.page.jetblueHomePage;
import com.clover.solution.ui.util.extentreports.ExtentTestManager;
import com.clover.solution.ui.util.logs.Log;

public class TC004_check_the_JetBlue extends BaseTest 
{

    
    @BeforeMethod(alwaysRun=true)
    public static void preCondition() 
    {
    	Log.info("Tests is starting!");
    	Master_driver=new DriverManager();
    	Log.info("Starting the Test & Opening up the WebDriver");
    	//getDriver().manage().deleteAllCookies();
    	jetblue = new jetblueHomePage(getDriver());
    	Log.info("Clearing the cookies");    
    }
    
    @Test(priority=0,
    	description="TC004 check the clover Site using JSON",
    	enabled = true,
    	alwaysRun=true)
        public void TC004_check_the_JetBlue_Test(Method method) {
            List<LinkedHashMap<String,String>> dataMap=new LinkedList<LinkedHashMap<String,String>>();
    		//Reading the data from JSON
            //dataMap=jsonFileReader(method.getName());
    		//ExtentReports Description
          	ExtentTestManager.startTest(method.getName(), "TC004 check the JetBlue Site using JSON");
          	
          	String url="https://www.jetblue.com/";
          	String source_search_key="JFK";
          	String target_search_key="FLL";
          	String validation_String="";

          
          	jetblue.SiteCheck(url, source_search_key, target_search_key);
          	
          	//Browser Launch
          	//Navigate to Targetted site
          	//Site Check
          	//Accept the Cookies
          	//Start the Scenario
          		//Flight > Book > Enter the flight details > Search
          		//Sort the iternary list > Select lowest To fare
          		//Sort the iternary list > Select lowest from fare
          		//Check out the booking
          	
          	
          	//My PotfolioSite
          	//Hero - "Hey! I Am Manikandan Ravi" - //div[contains(@class,'Hero')]//h2[contains(@class,'GlobalComponents__SectionTitle-sc-6qzy6y-1 jKjqRH')]
          	//click on Project - //div[contains(@class,'HeaderStyles')]//a[text()='Projects']
          	//Validate the present- //section[contains(@id,'projects')]
          	//Validate the value as "Projects" -  <h2 class="GlobalComponents__SectionTitle-sc-6qzy6y-1 jKjqRH">Projects</h2>
         }
    

    @AfterMethod(alwaysRun=true)
    public void postCondition() 
    {
    	getDriver().manage().deleteAllCookies();
    	Log.info("Clearing the cookies");
    	Log.info("WebDriver is cooling down");
    	//DriverManager.close();
        
    }
    
	
}

