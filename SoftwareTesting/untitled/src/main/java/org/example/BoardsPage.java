package org.example;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.interactions.Actions;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//
//import java.time.Duration;
//
//public class BoardsPage {
//    private WebDriver driver;
//    private WebDriverWait wait;
//    private Actions actions;
//
//    // Locators
//    private By memberOption = By.cssSelector("#\\31--123-0 > .inner");
//    private By addNewMember = By.cssSelector("li > .add-new");
//    private By memberEmailField = By.id("crawljax_member_email");
//    private By submitButton = By.cssSelector("button");
//    private By errorMessage = By.cssSelector(".error");
//    private By addNewBoardButton = By.id("add_new_board");
//    private By boardNameInput = By.id("board_name");
//    private By boardsNav = By.cssSelector("#boards_nav span");
//    private By viewAllBoardsLink = By.linkText("View all boards");
//    private By boardLink = By.cssSelector("h4");
//    private By boardTitle = By.cssSelector("h3");
//
//    public BoardsPage(WebDriver driver) {
//        this.driver = driver;
//        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        this.actions = new Actions(driver);
//    }
//
//    public void selectMemberOption() {
//        WebElement memberElement = wait.until(ExpectedConditions.elementToBeClickable(memberOption));
//        memberElement.click();
//    }
//
//    public void clickAddNewMember() {
//        WebElement addNewElement = wait.until(ExpectedConditions.elementToBeClickable(addNewMember));
//        actions.moveToElement(addNewElement).perform();
//        addNewElement.click();
//        // Move to body to reset hover
//        actions.moveToElement(driver.findElement(By.tagName("body")), 0, 0).perform();
//    }
//
//    public void enterMemberEmail(String email) {
//        WebElement emailElement = wait.until(ExpectedConditions.visibilityOfElementLocated(memberEmailField));
//        emailElement.click();
//        emailElement.sendKeys(email);
//    }
//
//    public void clickSubmitButton() {
//        WebElement submitElement = wait.until(ExpectedConditions.elementToBeClickable(submitButton));
//        actions.moveToElement(submitElement).perform();
//        submitElement.click();
//        // Move to body to reset hover
//        actions.moveToElement(driver.findElement(By.tagName("body")), 0, 0).perform();
//    }
//
//    public String getErrorMessage() {
//        return wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage)).getText();
//    }
//
//    public void clickAddNewBoard() {
//        wait.until(ExpectedConditions.elementToBeClickable(addNewBoardButton)).click();
//    }
//
//    public void enterBoardName(String boardName) {
//        wait.until(ExpectedConditions.visibilityOfElementLocated(boardNameInput)).sendKeys(boardName);
//    }
//
//    public void openBoardsNav() {
//        wait.until(ExpectedConditions.elementToBeClickable(boardsNav)).click();
//    }
//
//    public void clickViewAllBoards() {
//        wait.until(ExpectedConditions.elementToBeClickable(viewAllBoardsLink)).click();
//    }
//
//    public void clickBoardLink() {
//        wait.until(ExpectedConditions.elementToBeClickable(boardLink)).click();
//    }
//
//    public String getBoardTitle() {
//        return wait.until(ExpectedConditions.visibilityOfElementLocated(boardTitle)).getText();
//    }
//}
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BoardsPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private Actions actions;

    // Locators
    private By memberOption = By.cssSelector("[class*='board-item'], [data-testid='board-selector'], .inner"); // Fallback to .inner if others fail
    private By addNewMember = By.cssSelector("li > .add-new");
    private By memberEmailField = By.id("crawljax_member_email");
    private By submitButton = By.cssSelector("button");
    private By errorMessage = By.cssSelector(".error");
    private By addNewBoardButton = By.id("add_new_board");
    private By boardNameInput = By.id("board_name");
    private By boardsNav = By.cssSelector("#boards_nav span");
    private By viewAllBoardsLink = By.linkText("View all boards");
    private By boardLink = By.cssSelector("h4");
    private By boardTitle = By.cssSelector("h3");
    private By viewContainer = By.cssSelector(".view-container");
    private By listNameInput = By.id("list_name");
    private By addNewCardLink = By.linkText("Add a new card...");
    private By cardNameInput = By.id("card_name");
    private By cardContent = By.cssSelector(".card-content");
    private By commentTextarea = By.cssSelector("textarea");
    private By commentText = By.cssSelector(".text");

    public BoardsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.actions = new Actions(driver);
    }

    public void selectMemberOption() {
        try {
            WebElement memberElement = wait.until(ExpectedConditions.elementToBeClickable(memberOption));
            memberElement.click();
        } catch (Exception e) {
            System.out.println("Failed to locate member option with locator: " + memberOption + ". Error: " + e.getMessage());
            throw e;
        }
    }

    public void clickViewContainer() {
        wait.until(ExpectedConditions.elementToBeClickable(viewContainer)).click();
    }

    public void clickAddNewMember() {
        WebElement addNewElement = wait.until(ExpectedConditions.elementToBeClickable(addNewMember));
        actions.moveToElement(addNewElement).perform();
        addNewElement.click();
        // Move to body to reset hover
        actions.moveToElement(driver.findElement(By.tagName("body")), 0, 0).perform();
    }

    public void enterMemberEmail(String email) {
        WebElement emailElement = wait.until(ExpectedConditions.visibilityOfElementLocated(memberEmailField));
        emailElement.click();
        emailElement.sendKeys(email);
    }

    public void clickSubmitButton() {
        WebElement submitElement = wait.until(ExpectedConditions.elementToBeClickable(submitButton));
        actions.moveToElement(submitElement).perform();
        submitElement.click();
        // Move to body to reset hover
        actions.moveToElement(driver.findElement(By.tagName("body")), 0, 0).perform();
    }

    public String getErrorMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage)).getText();
    }

    public void clickAddNewBoard() {
        wait.until(ExpectedConditions.elementToBeClickable(addNewBoardButton)).click();
    }

    public void enterBoardName(String boardName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(boardNameInput)).sendKeys(boardName);
    }

    public void openBoardsNav() {
        wait.until(ExpectedConditions.elementToBeClickable(boardsNav)).click();
    }

    public void clickViewAllBoards() {
        wait.until(ExpectedConditions.elementToBeClickable(viewAllBoardsLink)).click();
    }

    public void clickBoardLink() {
        wait.until(ExpectedConditions.elementToBeClickable(boardLink)).click();
    }

    public String getBoardTitle() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(boardTitle)).getText();
    }
    public void enterListName(String listName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(listNameInput)).sendKeys(listName);
    }

    public void clickAddNewCardLink() {
        wait.until(ExpectedConditions.elementToBeClickable(addNewCardLink)).click();
    }

    public void enterCardName(String cardName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(cardNameInput)).sendKeys(cardName);
    }

    public void clickCardContent() {
        wait.until(ExpectedConditions.elementToBeClickable(cardContent)).click();
    }

    public void enterCardComment(String comment) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(commentTextarea)).sendKeys(comment);
    }

    public String getCardCommentText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(commentText)).getText();
    }

}