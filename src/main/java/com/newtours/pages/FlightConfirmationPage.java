package com.newtours.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FlightConfirmationPage extends BasePage {

    public FlightConfirmationPage(WebDriver driver) {
        super(driver);
    }

    public String getPrice(){
        waitForElementToBeVisible(By.xpath("//font[contains(text(),'Confirmation')]"));
        System.out.println(getTextValue(By.xpath("//font[contains(text(),'Confirmation')]")));
        WebElement priceElement = getElements(By.xpath("//font[contains(text(),'USD')]")).get(1);
        String price = priceElement.getText();
        System.out.println(price);
        clickElement(By.linkText("SIGN-OFF"));
        return price;
    }

}
