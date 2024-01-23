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
import com.clover.solution.ui.page.myPortfolio;
import com.clover.solution.ui.util.extentreports.ExtentTestManager;
import com.clover.solution.ui.util.logs.Log;

public class TC001_check_myPortfoliosite_Sanity extends BaseTest 
{

    
    @BeforeMethod(alwaysRun=true)
    public static void preCondition() 
    {
    	Log.info("Tests is starting!");
    	Master_driver=new DriverManager();
    	Log.info("Starting the Test & Opening up the WebDriver");
    	//getDriver().manage().deleteAllCookies();
    	myportolio=new myPortfolio(getDriver());
    	Log.info("Clearing the cookies");    
    }
    
    @Test(priority=0,
    	description="TC004 check the clover Site using JSON",
    	enabled = true,
    	alwaysRun=true)
        public void TC004_check_myPortfoliosite_SanityTest(Method method) {
    	
    	List<LinkedHashMap<String,String>> dataMap=new LinkedList<LinkedHashMap<String,String>>();
   		//Reading the data from JSON
        dataMap=jsonFileReader(method.getName());
   		//ExtentReports Description
        ExtentTestManager.startTest(method.getName(), "TC004_check_myPortfoliosite_SanityTest");
         	
     	String url=dataMap.get(0).get("engine_url_variable");
     	String hero_text=dataMap.get(0).get("hero_text");
     	String project_text=dataMap.get(0).get("projects_text");
     	
     	//DP - Facade
     	myportolio.checkMySite(url, hero_text, project_text);

     	//DP - Fluent
     	/*
     	myportolio.gotoURL(url)
	 			.assertTheFirstValue(project_text)
	 		//	.clickProjectButton()
	 			.assertTheFirstValue(hero_text);
          	*/
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

