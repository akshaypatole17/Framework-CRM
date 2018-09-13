package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.testbase.TestBase;
import com.crm.qa.testutil.TestUtil;

public class ContactsPageTest extends TestBase{
	
	LoginPage loginPage;
	HomePage homePage;
	TestUtil testUtil;
	ContactsPage contactsPage;
	
	String sheetName = "contacts";
	
	public ContactsPageTest() {
		super();
	}
	
	@BeforeMethod
	public void setUp() {
		initialisation();
		testUtil = new TestUtil();
		contactsPage = new ContactsPage();
		loginPage = new LoginPage();
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		testUtil.switchToFrame();
		homePage.clickOnContactsLink();
	}
	
	@Test(priority=1)
	public void verifyContactsPageLabelTest() {
		Assert.assertTrue(contactsPage.verifyContactsLabel(), "Contacts label is missing on the page.");		
	}
	
	@Test(priority=2)
	public void selectSingleContactsTest() {
		contactsPage.selectContactsByName("Vivek Ramteke");
	}
	
	@Test(priority=3)
	public void selectMultipleContactsTest() {
		contactsPage.selectContactsByName("Vivek Ramteke");
		contactsPage.selectContactsByName("Sonu Khobragade");
	}
	
	@DataProvider
	public Object[][] getCRMTestData() {
		Object[][] data = TestUtil.getTestData(sheetName);
		return data;
	}
	
	@Test(priority=4, dataProvider="getCRMTestData")
	public void createNewContact(String title, String firstName, String lastName, String company) {
		homePage.clickOnNewContactsLink();
		//contactsPage.createNewContact("Mr.", "Atul", "Kasbekar", "viacom18");
		contactsPage.createNewContact(title, firstName, lastName, company);
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}


}
