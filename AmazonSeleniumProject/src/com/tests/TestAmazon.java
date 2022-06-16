package com.tests;

import java.sql.SQLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.pages.LoginPage;
import com.pages.ResultPage;

public class TestAmazon {
	public static WebDriver driver;

	private static void startBrowser() {
		String os = System.getProperty("os.name");
		if (os.contains("Windows")) {
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "//Drivers//chromedriver.exe");
		}
		System.out.println("Executing in " + os + ". Starting Driver");
		driver = new ChromeDriver();
	}

	protected static void SearchItems(String itemToSearch) throws SQLException {
		LoginPage loginPage = new LoginPage(driver);
		ResultPage resPage = new ResultPage();

		loginPage.searchProduct(itemToSearch);
		resPage.listPrice(driver, itemToSearch);
	}

	public static void main(String[] args) throws InterruptedException, SQLException {
		startBrowser();
		JDBCTest.ConnectDatabase();
		JDBCTest.CreateDatabase();
		JDBCTest.CreateTable();
		JDBCTest.deleteAllRecords();

		SearchItems("Footwear for men");
		SearchItems("Footwear for kids");
		SearchItems("Footwear for women");
		SearchItems("Digital Watches");
		SearchItems("Analog Watches");

		JDBCTest.printAllDBRecords();
		JDBCTest.closeDBConnection();
		closeBrowser();
	}

	private static void closeBrowser() {
		System.out.println("Done");
		driver.quit();
	}

}
