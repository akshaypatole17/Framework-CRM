package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.testbase.TestBase;
import com.crm.qa.testutil.TestUtil;

public class HomePageTest extends TestBase {
	
	LoginPage loginPage;
	HomePage homePage;
	TestUtil testUtil;
	ContactsPage contactsPage;
	
	public HomePageTest() {
		super();
	}
	
	@BeforeMethod
	public void setUp() {
		initialisation();
		testUtil = new TestUtil();
		loginPage = new LoginPage();
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		contactsPage = new ContactsPage();
	}
	
	@Test(priority=1)
	public void verifyHomePageTitleTest() {
		String homePageTitle = homePage.verifyHomePageTitle();	
		Assert.assertEquals(homePageTitle, "CRMPRO", "HomePage Title Not Matched");
	}

	@Test(priority=2)
	public void verifyUsernameTest() {
		testUtil.switchToFrame();
		Assert.assertTrue(homePage.verifyCorrectUsernameLabel());
	}
	
	@Test(priority=3)
	public void verifyContactsLLinkTest() {
		testUtil.switchToFrame();
		contactsPage = homePage.clickOnContactsLink();
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}


}
