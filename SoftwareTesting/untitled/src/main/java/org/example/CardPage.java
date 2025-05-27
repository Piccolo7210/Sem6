//package org.example;
//
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//import java.time.Duration;
//
//public class CardPage {
//    private WebDriver driver;
//    private WebDriverWait wait;
//
//    // Locators
//    private By addCardLink = By.linkText("Add a new card...");
//    private By cardNameInput = By.id("card_name");
//    private By submitButton = By.cssSelector("button");
//    private By cardContent = By.cssSelector(".card-content");
//
//    public CardPage(WebDriver driver) {
//        this.driver = driver;
//        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//    }
//
//    public void clickAddCardLink() {
//        wait.until(ExpectedConditions.elementToBeClickable(addCardLink)).click();
//    }
//
//    public void enterCardName(String cardName) {
//        wait.until(ExpectedConditions.visibilityOfElementLocated(cardNameInput)).sendKeys(cardName);
//    }
//
//    public void clickSubmitButton() {
//        wait.until(ExpectedConditions.elementToBeClickable(submitButton)).click();
//    }
//
//    public String getCardContent() {
//        return wait.until(ExpectedConditions.visibilityOfElementLocated(cardContent)).getText();
//    }
//}

package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CardPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private Actions actions;

    // Locators
    private By cardContent = By.cssSelector(".card-content");
    private By editLink = By.linkText("Edit");
    private By descriptionTextarea = By.cssSelector("textarea:nth-child(2)");
    private By submitButton = By.cssSelector("button:nth-child(3)");
    private By commentTextarea = By.cssSelector("textarea");
    private By commentText = By.cssSelector(".text"); // Adjust if needed
    private By boardLink = By.cssSelector("h4");
        private By addCardLink = By.linkText("Add a new card...");
    private By cardNameInput = By.id("card_name");


    public CardPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        this.actions = new Actions(driver);
    }

    public void clickCardContent() {
        wait.until(ExpectedConditions.elementToBeClickable(cardContent)).click();
    }

    public void clickEditLink() {
        wait.until(ExpectedConditions.elementToBeClickable(editLink)).click();
    }

    public void enterDescription(String description) {
        WebElement descriptionElement = wait.until(ExpectedConditions.visibilityOfElementLocated(descriptionTextarea));
        descriptionElement.click();
        descriptionElement.sendKeys(description);
    }

    public void clickSubmitButton() {
        WebElement submitElement = wait.until(ExpectedConditions.elementToBeClickable(submitButton));
        actions.moveToElement(submitElement).perform();
        submitElement.click();
        actions.moveToElement(driver.findElement(By.tagName("body")), 0, 0).perform();
    }

    public void enterComment(String comment) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(commentTextarea)).sendKeys(comment);
    }

    public String getCommentText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(commentText)).getText();
    }

    public void clickBoardLink() {
        wait.until(ExpectedConditions.elementToBeClickable(boardLink)).click();
    }

    public String getDescriptionText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(descriptionTextarea)).getAttribute("value");
    }
        public void clickAddCardLink() {
        wait.until(ExpectedConditions.elementToBeClickable(addCardLink)).click();
    }

    public void enterCardName(String cardName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(cardNameInput)).sendKeys(cardName);
    }
        public String getCardContent() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(cardContent)).getText();
    }
}