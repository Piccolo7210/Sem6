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



//working


//package org.example;
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
//public class CardPage {
//    private WebDriver driver;
//    private WebDriverWait wait;
//    private Actions actions;
//
//    // Locators
//    private By cardContent = By.cssSelector(".card-content");
//    private By editLink = By.linkText("Edit");
//    private By descriptionTextarea = By.cssSelector("textarea:nth-child(2)");
//    private By submitButton = By.cssSelector("button:nth-child(3)");
//    private By commentTextarea = By.cssSelector("textarea");
//    private By commentText = By.cssSelector(".text"); // Adjust if needed
//    private By boardLink = By.cssSelector("h4");
//        private By addCardLink = By.linkText("Add a new card...");
//    private By cardNameInput = By.id("card_name");
//
//
//    public CardPage(WebDriver driver) {
//        this.driver = driver;
//        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
//        this.actions = new Actions(driver);
//    }
//
//    public void clickCardContent() {
//        wait.until(ExpectedConditions.elementToBeClickable(cardContent)).click();
//    }
//
//    public void clickEditLink() {
//        wait.until(ExpectedConditions.elementToBeClickable(editLink)).click();
//    }
//
//    public void enterDescription(String description) {
//        WebElement descriptionElement = wait.until(ExpectedConditions.visibilityOfElementLocated(descriptionTextarea));
//        descriptionElement.click();
//        descriptionElement.sendKeys(description);
//    }
//
//    public void clickSubmitButton() {
//        WebElement submitElement = wait.until(ExpectedConditions.elementToBeClickable(submitButton));
//        actions.moveToElement(submitElement).perform();
//        submitElement.click();
//        actions.moveToElement(driver.findElement(By.tagName("body")), 0, 0).perform();
//    }
//
//    public void enterComment(String comment) {
//        wait.until(ExpectedConditions.visibilityOfElementLocated(commentTextarea)).sendKeys(comment);
//    }
//
//    public String getCommentText() {
//        return wait.until(ExpectedConditions.visibilityOfElementLocated(commentText)).getText();
//    }
//
//    public void clickBoardLink() {
//        wait.until(ExpectedConditions.elementToBeClickable(boardLink)).click();
//    }
//
//    public String getDescriptionText() {
//        return wait.until(ExpectedConditions.visibilityOfElementLocated(descriptionTextarea)).getAttribute("value");
//    }
//        public void clickAddCardLink() {
//        wait.until(ExpectedConditions.elementToBeClickable(addCardLink)).click();
//    }
//
//    public void enterCardName(String cardName) {
//        wait.until(ExpectedConditions.visibilityOfElementLocated(cardNameInput)).sendKeys(cardName);
//    }
//        public String getCardContent() {
//        return wait.until(ExpectedConditions.visibilityOfElementLocated(cardContent)).getText();
//    }
//}

//latest


package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CardPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private Actions actions;

    // Locators
//    private By cardContent = By.cssSelector(".card-content");
//    private By editLink = By.linkText("Edit");
//    private By descriptionTextarea = By.cssSelector("textarea#card_description"); // Placeholder, update after DOM inspection
//    private By submitButton = By.cssSelector("button:nth-child(3)");
//    private By commentTextarea = By.cssSelector("textarea");
//    private By commentText = By.cssSelector(".text"); // Adjust if needed
//    private By boardLink = By.cssSelector("h4");
//    private By addCardLink = By.linkText("Add a new card...");
//    private By cardNameInput = By.id("card_name");
    private By cardNameInput = By.id("card_name");
    private By cardContent = By.cssSelector(".card-content");
    private By editLink = By.linkText("Edit");
    private By descriptionTextarea = By.cssSelector("textarea.description-textarea"); // Placeholder, update after DOM inspection
    private By submitButton = By.cssSelector("button:nth-child(3)");
    private By commentTextarea = By.cssSelector("textarea");
    private By commentText = By.cssSelector(".text"); // Adjust if needed
    private By boardLink = By.cssSelector("h4");
    private By addCardLink = By.linkText("Add a new card...");
//    private By cardNameInput = By.id("card_name");
    private By addNewMember = By.cssSelector("li > .add-new");
//    private By cardNameInput = By.id("card_name");
    private By tagButton = By.cssSelector(".button:nth-child(3) > span"); // Placeholder, update after DOM inspection
    private By greenTag = By.cssSelector(".green"); // Placeholder, update after DOM inspection
    private By tagElement = By.cssSelector(".tag:nth-child(2)");
    private By memberButton = By.cssSelector(".button:nth-child(2) > span"); // Placeholder, update after DOM inspection
    private By closePopup = By.cssSelector(".fa-close"); // Placeholder, update after DOM inspection
    private By memberAvatar = By.cssSelector(".react-gravatar");
    private By selectMember = By.cssSelector("ul:nth-child(2) > li:nth-child(2) span:nth-child(2)"); // Placeholder, update after DOM inspection
    private By memberEmail = By.id("crawljax_member_email");
    private By commentTimestamp = By.cssSelector("small:nth-child(3)");
    private By deleteCardButton = By.cssSelector(".fa-trash-o");
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
    public void clickCommentTab() {
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".comment-tab"))).click(); // Adjust selector
    }


    public void enterDescription(String description) {
        WebElement descriptionElement = wait.until(ExpectedConditions.visibilityOfElementLocated(descriptionTextarea));
        descriptionElement.click();
        descriptionElement.sendKeys(description);
    }

    public void clickSubmitButton() {
        System.out.println("Attempting to locate submit button: " + submitButton);
        List<WebElement> buttons = driver.findElements(submitButton);
        System.out.println("Found " + buttons.size() + " buttons matching locator");
        WebElement submitElement = wait.until(ExpectedConditions.elementToBeClickable(submitButton));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", submitElement);
        js.executeScript("arguments[0].click();", submitElement);
    }

    public void enterComment(String comment) {
        WebElement commentElement = wait.until(ExpectedConditions.visibilityOfElementLocated(commentTextarea));
        commentElement.click();
        commentElement.sendKeys(comment);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1)); // Ensure input is registered
    }

//    public void enterComment(String comment) {
//        wait.until(ExpectedConditions.visibilityOfElementLocated(commentTextarea)).sendKeys(comment);
//    }

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

//    public void enterCardName(String cardName) {
//        wait.until(ExpectedConditions.visibilityOfElementLocated(cardNameInput)).sendKeys(cardName);
//    }

    public String getCardContent() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(cardContent)).getText();
    }

    // Debugging method to check if textarea exists
    public boolean isDescriptionTextareaPresent() {
        try {
            driver.findElement(descriptionTextarea);
            return true;
        } catch (Exception e) {
            System.out.println("Description textarea not found: " + e.getMessage());
            return false;
        }
    }

    // Optional: Click a description tab or modal trigger if needed
    public void clickDescriptionTab() {
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".description-tab"))).click(); // Adjust selector
    }
    public void clickTagButton() {
        WebElement tagButtonElement = wait.until(ExpectedConditions.elementToBeClickable(tagButton));
        actions.moveToElement(tagButtonElement).perform();
        tagButtonElement.click();
        actions.moveToElement(driver.findElement(By.tagName("body")), 0, 0).perform();
    }

    public void selectGreenTag() {
        wait.until(ExpectedConditions.elementToBeClickable(greenTag)).click();
    }

    public boolean isTagPresent() {
        List<WebElement> tags = driver.findElements(tagElement);
        return tags.size() > 0;
    }
    public void clickMemberButton() {
        WebElement memberButtonElement = wait.until(ExpectedConditions.elementToBeClickable(memberButton));
        actions.moveToElement(memberButtonElement).perform();
        memberButtonElement.click();
        actions.moveToElement(driver.findElement(By.tagName("body")), 0, 0).perform();
    }

    public void clickClosePopup() {
        wait.until(ExpectedConditions.elementToBeClickable(closePopup)).click();
    }
    public void enterMemberEmail(String email) {
        WebElement emailElement = wait.until(ExpectedConditions.visibilityOfElementLocated(memberEmail));
        emailElement.click();
        emailElement.sendKeys(email);
    }
    public void clickAddNewMember() {
        WebElement addNewElement = wait.until(ExpectedConditions.elementToBeClickable(addNewMember));
        actions.moveToElement(addNewElement).perform();
        addNewElement.click();
        actions.moveToElement(driver.findElement(By.tagName("body")), 0, 0).perform();
    }


    public void selectMember() {
        wait.until(ExpectedConditions.elementToBeClickable(selectMember)).click();
    }
    public boolean isMemberAvatarPresent() {
        List<WebElement> avatars = driver.findElements(memberAvatar);
        return avatars.size() > 0;
    }
    public String getCommentTimestamp() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(commentTimestamp)).getText();
    }
//    public void enterCardName(String cardName) {
//        System.out.println("Attempting to locate card name input: " + cardNameInput);
//        try {
//            WebElement inputElement = wait.until(ExpectedConditions.visibilityOfElementLocated(cardNameInput));
//            inputElement.click();
//            inputElement.sendKeys(cardName);
//            System.out.println("Entered card name: " + cardName);
//        } catch (Exception e) {
//            System.out.println("Failed to enter card name: " + e.getMessage());
//            throw e;
//        }
//    }
    public void enterCardName(String cardName) {
        System.out.println("Attempting to locate card name input: " + cardNameInput);
        try {
            WebElement inputElement = wait.until(ExpectedConditions.visibilityOfElementLocated(cardNameInput));
            inputElement.click();
            inputElement.sendKeys(cardName);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1)); // Ensure input is registered
            System.out.println("Entered card name: " + cardName);
        } catch (Exception e) {
            System.out.println("Failed to enter card name: " + e.getMessage());
            throw e;
        }
    }
    public void clickDeleteCardButton() {
        System.out.println("Attempting to locate delete button: " + deleteCardButton);
        wait.until(ExpectedConditions.elementToBeClickable(deleteCardButton)).click();
    }
    public boolean isCardPresent(String cardName) {
        try {

            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[contains(text(), '" + cardName + "')]")));
            return false;
        } catch (Exception e) {
            return true;
        }
    }
}
