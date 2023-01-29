package com.actitime.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(id ="username")
	WebElement username;
	
	
	@FindBy(name = "pwd")
	WebElement password;
	
	@FindBy(id = "loginButton")
	WebElement login;
	
	
	
	public void setUserName(String un) 
	{
		username.sendKeys(un);
	}
	
	public void setPassWord(String pwd )
	{
		password.sendKeys(pwd);
	}
	
	public void clicknLoginbtn()
	{
		login.click();
	}
}
