package com.newtours.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegistrationPage extends BasePage {

    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    public void goTo(){
        driver.get("http://newtours.demoaut.com/mercuryregister.php");
        waitForElementToBeVisible(By.name("firstName"));
    }

    public void enterUserDetails(String firstName, String lastName){
        fillTextField(By.name("firstName"),firstName);
        fillTextField(By.name("lastName"),lastName);

    }

    public void enterUserCredentials(String username, String password){
        fillTextField(By.id("email"),username);
        fillTextField(By.name("password"),password);
        fillTextField(By.name("confirmPassword"),password);
    }

    public void submit() {
        clickElement(By.name("register"));
    }


}
