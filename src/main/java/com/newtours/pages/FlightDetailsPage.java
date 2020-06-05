package com.newtours.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FlightDetailsPage extends BasePage{

    public FlightDetailsPage(WebDriver driver) {
        super(driver);
    }

    public void selectPassengers(String numOfPassengers){
        selectDropDownByVisibleText(By.name("passCount"),numOfPassengers);
    }

    public void goToFindFlightsPage(){
        clickElement(By.name("findFlights"));
    }


}
