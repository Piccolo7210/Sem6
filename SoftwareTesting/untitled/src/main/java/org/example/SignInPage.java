package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SignInPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private By loginButton = By.cssSelector("button");
    private By emailField = By.id("user_email");
    private By passwordField = By.id("user_password");
    private By errorMessage = By.cssSelector(".error");

    public SignInPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void navigateTo() {
        driver.get("http://localhost:4000/sign_in");
    }

    public void clickLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

    public void enterEmail(String email) {
//        wait.until(ExpectedConditions.visibilityOfElementLocated(emailField)).clear();
        WebElement emailElement = wait.until(ExpectedConditions.visibilityOfElementLocated(emailField));
        emailElement.clear();
        emailElement.click();
        emailElement.sendKeys(email);
    }

    public void enterPassword(String password) {
        WebElement passwordElement = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField));
        passwordElement.clear();
        passwordElement.click();
        passwordElement.sendKeys(password);
    }

    public String getErrorMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage)).getText();
    }

    public void clickCreateNewAccountLink() {
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Create new account"))).click();
    }
    public String getEmailFieldValidationMessage() {
//        wait.until(ExpectedConditions.presenceOfElementLocated(emailField)).clear();
        return wait.until(ExpectedConditions.presenceOfElementLocated(emailField)).getAttribute("validationMessage");
    }

    public String getPasswordFieldValidationMessage() {
//        wait.until(ExpectedConditions.presenceOfElementLocated(passwordField)).clear();
        return wait.until(ExpectedConditions.presenceOfElementLocated(passwordField)).getAttribute("validationMessage");
    }
}