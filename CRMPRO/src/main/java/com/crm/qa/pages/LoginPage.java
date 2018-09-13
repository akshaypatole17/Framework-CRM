package com.crm.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.testbase.TestBase;

public class LoginPage extends TestBase {
	
	//Page Factory - Object Repository
	@FindBy(name="username11")
	WebElement username;
	
	@FindBy(name="password12")
	WebElement password;
	
	@FindBy(xpath="//input[@type='submit']")
	WebElement loginBtn;
	
	@FindBy(xpath="//button[contains(text(), 'Sign Up')]")
	WebElement signUpBtn;
	
	@FindBy(xpath="//img[contains(@class, 'img-responsive')]")
	WebElement crmLogo;
	
	//Initializing the Page Objects.
	public LoginPage() {
		PageFactory.initElements(driver, this);
	}
	
	//Actions
	public String validateLoginPageTitle() {
		return driver.getTitle();
	}
	
	public boolean validateCRMImage() {
		return crmLogo.isDisplayed();	
	}
	
	public HomePage login(String un, String pwd) {
		username.sendKeys(un);
		password.sendKeys(pwd);
		loginBtn.submit();
		
		return new HomePage();
	}
	

}


