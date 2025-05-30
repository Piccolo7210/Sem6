package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SignUpPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private By firstNameField = By.id("user_first_name");
    private By lastNameField = By.id("user_last_name");
    private By emailField = By.id("user_email");
    private By passwordField = By.id("user_password");
    private By passwordConfirmationField = By.id("user_password_confirmation");
    private By submitButton = By.cssSelector("button");
    private By nameSpan = By.cssSelector("span:nth-child(3)");
    private By errorMessage = By.cssSelector(".error");

    public SignUpPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void enterFirstName(String firstName) {
        WebElement firstNameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameField));
        firstNameElement.click();
        firstNameElement.sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(lastNameField)).sendKeys(lastName);
    }

    public void enterEmail(String email) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailField)).sendKeys(email);
    }

    public void enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField)).sendKeys(password);
    }

    public void enterPasswordConfirmation(String passwordConfirmation) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordConfirmationField)).sendKeys(passwordConfirmation);
    }

    public void clickSubmitButton() {
        wait.until(ExpectedConditions.elementToBeClickable(submitButton)).click();
    }

    public String getDisplayedName() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(nameSpan)).getText();
    }
    public String getEmailFieldValidationMessage() {
        wait.until(ExpectedConditions.presenceOfElementLocated(emailField)).clear();
        return wait.until(ExpectedConditions.presenceOfElementLocated(emailField)).getAttribute("validationMessage");
    }

    public String getPasswordFieldValidationMessage() {
        wait.until(ExpectedConditions.presenceOfElementLocated(passwordField)).clear();
        return wait.until(ExpectedConditions.presenceOfElementLocated(passwordField)).getAttribute("validationMessage");
    }
    public String getFirstNameValidationMessage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameField)).clear();
        return wait.until(ExpectedConditions.presenceOfElementLocated(firstNameField)).getAttribute("validationMessage");
    }
    public String getLastNameValidationMessage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(lastNameField)).clear();
        return wait.until(ExpectedConditions.presenceOfElementLocated(lastNameField)).getAttribute("validationMessage");
    }
    public String getPasswordConfirmationValidationMessage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordConfirmationField)).clear();
        return wait.until(ExpectedConditions.presenceOfElementLocated(passwordConfirmationField)).getAttribute("validationMessage");
    }
    public String getErrorMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage)).getText();
    }
}