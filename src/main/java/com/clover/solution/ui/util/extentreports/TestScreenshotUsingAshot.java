package com.clover.solution.ui.util.extentreports;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.clover.solution.ui.browser.DriverManager;

import io.github.bonigarcia.wdm.WebDriverManager;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class TestScreenshotUsingAshot {

	public static void main(String[] args) throws IOException {

		ChromeOptions options = new ChromeOptions();
		//options.addArguments("--headless=new");
        options.addArguments("start-maximized"); // open Browser in maximized mode
        options.addArguments("disable-infobars"); // disabling infobars
        options.addArguments("--disable-extensions"); // disabling extensions
        options.addArguments("--disable-gpu"); // applicable to windows os only
        options.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
        options.addArguments("--no-sandbox"); // Bypass OS security model
        options.addArguments("window-size=1920,1080");
        options.addArguments("--log-level=3","--remote-allow-origins=*");
        options.setAcceptInsecureCerts(true);
        System.setProperty("webdriver.chrome.silentOutput", "true");
        
        //options.addArguments(Arrays.asList("--window-position=0,0"));
       // options.addArguments(Arrays.asList("--window-size=1920,1080"));
        
        WebDriverManager.chromedriver().setup();
		
		WebDriver driver = new ChromeDriver(options);
		try {
			MyScreenRecorder.startRecording("test_record");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		driver.get("https://demo.guru99.com/test/guru99home/");
		driver.manage().window().maximize();
		Screenshot shot= new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);

		ImageIO.write(shot.getImage(), "jpg", new File("ElementScreenshot.jpg"));

		try {
			MyScreenRecorder.stopRecording();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}