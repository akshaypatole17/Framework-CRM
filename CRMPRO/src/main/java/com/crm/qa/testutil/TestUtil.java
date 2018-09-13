package com.crm.qa.testutil;

import java.awt.print.Book;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.collections4.map.StaticBucketMap;
import org.apache.commons.exec.OS;
import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.omg.PortableInterceptor.USER_EXCEPTION;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;

import com.crm.qa.testbase.TestBase;

public class TestUtil extends TestBase {
	
	public static long PAGE_LOAD_TIMEOUT=20;
	public static long IMPLICIT_WAIT=30;

	public static String TESTDATA_SHEET_PATH = "C:\\Users\\Akshay\\CRM\\CRMPRO\\src\\main\\java\\com\\crm\\qa\\testdata\\FreeCRM_TestData.xlsx";
	
	static Workbook book;
	static org.apache.poi.ss.usermodel.Sheet sheet;
	
	public void switchToFrame() {
		driver.switchTo().frame("mainpanel");
	}
	
	public static Object[][] getTestData(String sheetName) {
		FileInputStream file = null;
		try {
			file = new FileInputStream(TESTDATA_SHEET_PATH);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			book = WorkbookFactory.create(file);
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		sheet = book.getSheet(sheetName);
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		// System.out.println(sheet.getLastRowNum() + "--------" +
		// sheet.getRow(0).getLastCellNum());
		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
				data[i][k] = sheet.getRow(i + 1).getCell(k).toString();
				// System.out.println(data[i][k]);
			}
		}
		return data;
	}
	
	public static void takeScreenshotAtEndOfTest() throws IOException {
		//1. Take Screenshot and store it as a file format.
		File scrfile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String currentDir = System.getProperty("user.dir");
		//2. Now copy the file/screenshot in desired location using copyfile method.
		FileUtils.copyFile(scrfile, new File(currentDir +"/screenshots/"+ System.currentTimeMillis()+".png"));
		
}
}
