package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ListPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private By listNameInput = By.id("list_name");
    private By submitButton = By.cssSelector("button");
    private By listTitle = By.cssSelector("h4");


    public ListPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    public void updateListName(String listName) {
        WebElement inputElement = wait.until(ExpectedConditions.visibilityOfElementLocated(listNameInput));
        inputElement.click();
        inputElement.sendKeys(Keys.CONTROL + "a"); // Select all text
        inputElement.sendKeys(Keys.DELETE); // Clear the field
        inputElement.sendKeys(listName);
    }
    public void enterListName(String listName) {
        WebElement listNameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(listNameInput));
        listNameElement.sendKeys(listName);
    }

    public void clickSubmitButton() {
        WebElement submitElement = wait.until(ExpectedConditions.elementToBeClickable(submitButton));
        submitElement.click();
    }

    public String getListTitle() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(listTitle)).getText();
    }
    public void clickListTitle() {
        WebElement titleElement = wait.until(ExpectedConditions.elementToBeClickable(listTitle));
        titleElement.click();
    }
}