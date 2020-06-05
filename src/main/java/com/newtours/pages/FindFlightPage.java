package com.newtours.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FindFlightPage extends BasePage {

    public FindFlightPage(WebDriver driver) {
        super(driver);
    }

    public void submitFindFlightPage(){
        waitForElementToBeClickable(By.name("reserveFlights"));
        clickElement(By.name("reserveFlights"));
    }

    public void goToConfirmationPage(){
        waitForElementToBeClickable(By.name("buyFlights"));
        clickElement(By.name("buyFlights"));
    }


}
