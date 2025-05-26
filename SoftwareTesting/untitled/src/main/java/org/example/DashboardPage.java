package org.example;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DashboardPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private By inner = By.cssSelector(".inner");
    private By viewContainer = By.cssSelector(".view-container");
    private By boardNameInput = By.id("board_name");
    private By listNameInput = By.id("list_name");
    private By submitButton = By.cssSelector("button");
    private By createdListHeader = By.cssSelector("h4");

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void clickInner() {
        wait.until(ExpectedConditions.elementToBeClickable(inner)).click();
    }

    public void clickViewContainer() {
        wait.until(ExpectedConditions.elementToBeClickable(viewContainer)).click();
    }

    public void enterBoardName(String boardName) {
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(boardNameInput));
        input.clear();
        input.sendKeys(boardName);
    }

    public void submitBoard() {
        wait.until(ExpectedConditions.elementToBeClickable(submitButton)).click();
    }

    public void enterListName(String listName) {
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(listNameInput));
        input.clear();
        input.sendKeys(listName);
    }

    public void submitList() {
        wait.until(ExpectedConditions.elementToBeClickable(submitButton)).click();
    }

    public String getCreatedListHeader() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(createdListHeader)).getText();
    }
}
