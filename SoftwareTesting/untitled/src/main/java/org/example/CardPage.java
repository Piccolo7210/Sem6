package org.example;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class CardPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private By addCardLink = By.linkText("Add a new card...");
    private By cardNameInput = By.id("card_name");
    private By submitButton = By.cssSelector("button");
    private By cardContent = By.cssSelector(".card-content");

    public CardPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void clickAddCardLink() {
        wait.until(ExpectedConditions.elementToBeClickable(addCardLink)).click();
    }

    public void enterCardName(String cardName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(cardNameInput)).sendKeys(cardName);
    }

    public void clickSubmitButton() {
        wait.until(ExpectedConditions.elementToBeClickable(submitButton)).click();
    }

    public String getCardContent() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(cardContent)).getText();
    }
}
