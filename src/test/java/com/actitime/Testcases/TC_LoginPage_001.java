package com.actitime.Testcases;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.actitime.pageobject.LoginPage;

public class TC_LoginPage_001 extends BaseClass {
	
	@Test
	public void LoginTest()
	{
		LoginPage p = new LoginPage(driver);
		
		p.setUserName(username);
		etest.info("enetered username " +username);
		p.setPassWord(password);
		etest.info("entered password " +password);
		p.clicknLoginbtn();
		etest.info("clicked on login button");
		
		wait.until(ExpectedConditions.urlContains("submit_tt.do"));
		boolean t = driver.getCurrentUrl().contains("submit_tt.do");
		
		Assert.assertEquals(t, true);
		
//		if (driver.getCurrentUrl().contains("submit_tt.do")) {
//			Assert.asser
//			etest.info("Home page displayed");
//		}else
//		{
//			etest.fail("Home page NOT displayed");
//		}
		
	}
	
}
