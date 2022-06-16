package com.pages;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.tests.JDBCTest;

public class ResultPage {
	WebDriver driver;

	@FindBy(xpath = "//span[contains(text(),'RESULTS')]")
	private WebElement resultsPresent;

	@FindBy(xpath = "//span[@class='a-size-base-plus a-color-base']")
	private List<WebElement> itemCompany;

	@FindBy(xpath = "//span[@class='a-size-base-plus a-color-base a-text-normal']")
	private List<WebElement> itemName;

	@FindBy(xpath = "//span[@class='a-price-whole']")
	private List<WebElement> itemPrice;

	public void listPrice(WebDriver driver, String itemToSearch) throws SQLException {
		PageFactory.initElements(driver, this);
		this.driver = driver;

		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(resultsPresent));
		System.out.println("No of items of category " + itemToSearch + " : " + itemName.size());

//              List<WebElement> itemsCompany = driver.findElements(By.xpath("//div[@class='a-row a-size-base a-color-base']"));
//              List<WebElement> itemsName = driver.findElements(By.xpath("//span[@class='a-size-base-plus a-color-base a-text-normal']"));
//              List<WebElement> itemsPrice = driver.findElements(By.xpath("//span[@class='a-price-whole']"));
//              for(WebElement indItem : itemsCompany) {
//                      System.out.println(indItem.getText());
//              }

		Iterator<WebElement> aIterator = itemCompany.iterator();
		Iterator<WebElement> bIterator = itemName.iterator();
		Iterator<WebElement> cIterator = itemPrice.iterator();

		String a, b, c;
		while (aIterator.hasNext() && bIterator.hasNext() && cIterator.hasNext()) {
			a = aIterator.next().getText();
			b = bIterator.next().getText();
			c = cIterator.next().getText();
			if (a.trim() != "" && b.trim() != "" && c.trim() != "") {
				// System.out.println(a + " | " + b + " | " + c + " | ");
				JDBCTest.UpdateItemIntoDB(itemToSearch, a, b, c);
			}
		}
	}
}
