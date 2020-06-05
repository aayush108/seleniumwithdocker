package com.newtours.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationConfirmationPage extends BasePage {

    public RegistrationConfirmationPage(WebDriver driver) {
        super(driver);
    }

    public void goToFlightDetailsPage(){
        waitForElement(By.xpath("//font[contains(text(),'Thank you for registering')]"));
        navigateTo("http://newtours.demoaut.com/mercuryreservation.php");
    }

}
