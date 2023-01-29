package com.actitime.Testcases;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.security.Timestamp;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.TimeZone;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.actitime.utilities.ReadConfig;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	ReadConfig r = new ReadConfig();
	
	public ExtentReports ereport;
	public ExtentTest etest;
	public static final String REPORTPATH = "./target/MyReport.html";
	public static WebDriver driver;
	public static WebDriverWait wait;
	public String URL= r.getURL();
	public String username=r.getUsername();
	public String password=r.getPassword();
	public String browser = r.getBrowser();
	public long ITO = r.getITO();
	public long ETO = r.getETO();
	public String currentDateTime = r.getTimeStamp();
	
	
	@BeforeSuite
	public void initReport()
	{
		ereport = new ExtentReports();
		ExtentSparkReporter reporttype = new ExtentSparkReporter(REPORTPATH);
		ereport.attachReporter(reporttype);
	}
	
	@AfterSuite
	public void flushReport()
	{
		ereport.flush();
	}
	
	@Parameters("browser")
	@BeforeMethod
	public void setUp(String browser,Method testmethod)
	{
		String testname = testmethod.getName();
		etest = ereport.createTest(testname);
		
		if (browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			etest.info("Opened in CHROME BROWSER");
			
		}else if(browser.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			etest.info("Opened in Fire FOX");
			
		}else if(browser.equalsIgnoreCase("edge"))
		{
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			etest.info("Opened in EDGE");
		}
		
		
		driver.get(URL);
		etest.info("opened URL" +URL);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(ITO));
		wait = new WebDriverWait(driver, Duration.ofSeconds(ETO));
	}
	
	
	
	@AfterMethod
	public void tearDown(ITestResult result)
	{
		String testname = result.getName();
		int status = result.getStatus();
			
		
		if (status==1) {
			etest.info(testname+" is PASS");
			Reporter.log(testname+" is PASS");
			
		}else if (status==2) {
			
			TakesScreenshot shot = (TakesScreenshot)driver;
			File srcfile = shot.getScreenshotAs(OutputType.FILE);
			File dstfile = new File("./ScreenShots/"+testname+currentDateTime+".png");
			try {
				FileUtils.copyFile(srcfile, dstfile);
				etest.info("Test: "+testname+" got FAILED and screenshot has been taken " +dstfile);
				etest.addScreenCaptureFromPath("./../ScreenShots/"+testname+currentDateTime+".png");
				String failmessage = result.getThrowable().getMessage();
				etest.fail(testname+" Failed due to "+failmessage);
			} catch (IOException e) {
				
				}
			
		}
		
	driver.quit();
	
}
}




























