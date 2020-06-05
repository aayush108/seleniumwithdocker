package com.newtours.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;

public class BasePage {
    WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public BasePage() {
    }

    public void navigateTo(String url){
        driver.get(url);
    }

    public void clickElement(By by) {
        waitForElementToBeVisible(by);
        WebElement element = driver.findElement(by);
        element.click();
    }

    public void clickElement(WebElement element) {
        element.click();
    }

    public void fillTextField(By by, String value) {
        waitForElement(by);
        WebElement element = driver.findElement(by);
        if(value.equals("")) {
            element.clear();
        } else {
            element.sendKeys(value);
        }
    }

    public void clearTextField(By by) {
        waitForElement(by);
        WebElement element = driver.findElement(by);
        element.clear();
    }

    public boolean isElementPresent(By by, int secondsToWait) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, secondsToWait);
            wait.until(ExpectedConditions.presenceOfElementLocated(by));
        } catch(NoSuchElementException | TimeoutException e) {
            // If we exception while trying to find the element, it wasn't present.
            return false;
        }
        // If we made it through without an exception, the element was present
        return true;
    }

    public boolean isElementPresent(By by) {
        return isElementPresent(by, 5);
    }


    public void waitForElement(By by) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public void waitForElementToBeVisible(By by) {
        WebDriverWait wait = new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }


    public By formXpathBy(String htmlTag, String text) {
        return formXpathBy(htmlTag, text, true);
    }

    public By formXpathBy(String htmlTag, String text, boolean global) {
        String startLocation = "/";
        if(global) {
            startLocation = "//";
        }
        return By.xpath(startLocation + htmlTag + "[contains(., '" + text + "')]");
    }


    public WebElement getVisibleElement(By by) {
        ArrayList<WebElement> elements = (ArrayList<WebElement>) driver.findElements(by);
//        logger.info("Found " + elements.size() + " matching elements");
        for(WebElement element : elements) {
            if(element.isDisplayed()) {
                return element;
            }
        }
//        logger.error("No element was visible using locator: " + by.toString());
        return null;
    }


    public ArrayList<WebElement> getVisibleElements(By by) {
//        logger.info("Finding elements using: "  + by.toString());
        ArrayList<WebElement> elements = (ArrayList<WebElement>) driver.findElements(by);
        ArrayList<WebElement> visibleElements = new ArrayList<WebElement>(0);
        for(WebElement element : elements) {
            if(element.isDisplayed()) {
                visibleElements.add(element);
            }
        }
//        logger.info("Found " + visibleElements.size() + " matching elements");
        return visibleElements;
    }


    public boolean isElementPresentOverTime(By by, int seconds) {
        // Initial wait to find the element
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
        // Loop X times and make sure it's still there
        int attempts = 0;
        while(attempts++ <= seconds) {
            try {
//                logger.info("Searching for element with text");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                break;
            }
            wait.until(ExpectedConditions.presenceOfElementLocated(by));
        }
        return true;
    }


    public ArrayList<WebElement> getElements(By by) {
        waitForElementToBeVisible(by);
        return (ArrayList<WebElement>) driver.findElements(by);
    }


    public String getTextValue(By by) {
        int attempts = 0;
        int maxAttempts = 30;

        try {
            while(attempts++ < maxAttempts)
                waitForElementToBeVisible(by);
            WebElement element = driver.findElement(by);
            return element.getText();
        } catch(StaleElementReferenceException e) {
//            logger.info("Element was stale, retrying");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        }

        return "Was stale after retries";
    }

    public void selectDropDownByVisibleText(By by,String visibleText){
        waitForElementToBeVisible(by);
        Select dropDown = new Select(driver.findElement(by));
        By xpath = formXpathBy("option",visibleText);
        waitForElementToBeVisible(xpath);
        if (dropDown.getOptions().size()>0) {
            dropDown.selectByVisibleText(visibleText);
        }

    }

    public void waitForElementToBeClickable(By by) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    public void waitForElementToDisappear(By by) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

}
