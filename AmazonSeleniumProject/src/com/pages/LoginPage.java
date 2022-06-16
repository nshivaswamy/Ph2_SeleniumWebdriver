package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	WebDriver driver;

	String URL = "https://www.amazon.in/";

	@FindBy(xpath = "//input[@id='twotabsearchtextbox']")
	private WebElement searchBox;

	@FindBy(xpath = "//input[@id='nav-search-submit-button']")
	private WebElement submitButton;

	public LoginPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
		driver.get(URL);
		driver.manage().window().maximize();
	}

	public void searchProduct(String Product) {

		// WebElement searchBox =
		searchBox.sendKeys(Product);

		submitButton.click();

	}

}
