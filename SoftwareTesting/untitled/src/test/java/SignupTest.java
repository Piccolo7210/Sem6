//// Generated by Selenium IDE
//import io.github.bonigarcia.wdm.WebDriverManager;
//import org.junit.Test;
//import org.junit.Before;
//import org.junit.After;
//import static org.junit.Assert.*;
//import static org.hamcrest.CoreMatchers.is;
//import static org.hamcrest.core.IsNot.not;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.remote.RemoteWebDriver;
//import org.openqa.selenium.remote.DesiredCapabilities;
//import org.openqa.selenium.Dimension;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.interactions.Actions;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//import org.openqa.selenium.JavascriptExecutor;
//import org.openqa.selenium.Alert;
//import org.openqa.selenium.Keys;
//
//import java.time.Duration;
//import java.util.*;
//import java.net.MalformedURLException;
//import java.net.URL;
//public class SignupTest {
//  private WebDriver driver;
//  private Map<String, Object> vars;
//  JavascriptExecutor js;
//  @Before
//  public void setUp() {
//    WebDriverManager.firefoxdriver().setup();
//    driver = new FirefoxDriver();
//    driver.manage().window().setSize(new Dimension(654, 751));
//  }
//  @After
//  public void tearDown() {
//    driver.quit();
//  }
//  @Test
//  public void signup() {
//    driver.get("http://localhost:4000/sign_in");
//    driver.manage().window().setSize(new Dimension(1070, 622));
//    driver.findElement(By.linkText("Create new account")).click();
//    driver.findElement(By.id("user_first_name")).click();
//    driver.findElement(By.id("user_first_name")).sendKeys("mustaa");
//    driver.findElement(By.id("user_last_name")).sendKeys("kim");
//    driver.findElement(By.id("user_email")).sendKeys("mustaa@gmail.com");
//    driver.findElement(By.id("user_password")).sendKeys("12345");
//    driver.findElement(By.id("user_password_confirmation")).sendKeys("12345");
//    driver.findElement(By.cssSelector("button")).click();
//    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//    WebElement nameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span:nth-child(3)")));
//
//    assertThat(nameElement.getText(), is("mustaa kim"));
//  }
//}

import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.SignInPage;
import org.example.SignUpPage;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.Dimension;

public class SignupTest {
  private WebDriver driver;
  private SignInPage signInPage;
  private SignUpPage signUpPage;

  @Before
  public void setUp() {
    WebDriverManager.firefoxdriver().setup();
    driver = new FirefoxDriver();
    driver.manage().window().setSize(new Dimension(654, 751));
    signInPage = new SignInPage(driver);
    signUpPage = new SignUpPage(driver);
  }

  @After
  public void tearDown() {
    driver.quit();
  }

  @Test
  public void signup() {
    signInPage.navigateTo();
    driver.manage().window().setSize(new Dimension(1070, 622));
    signInPage.clickCreateNewAccountLink();
    signUpPage.enterFirstName("mustaaa");
    signUpPage.enterLastName("kim");
    signUpPage.enterEmail("mustaaa@gmail.com");
    signUpPage.enterPassword("12345");
    signUpPage.enterPasswordConfirmation("12345");
    signUpPage.clickSubmitButton();
    assertThat(signUpPage.getDisplayedName(), is("mustaaa kim"));
  }
  @Test
  public void signupWithoutFirstName() {
    signInPage.navigateTo();
    driver.manage().window().setSize(new Dimension(1070, 622));
    signInPage.clickCreateNewAccountLink();
    signUpPage.enterLastName("kim");
    signUpPage.enterEmail("mustaaa@gmail.com");
    signUpPage.enterPassword("12345");
    signUpPage.enterPasswordConfirmation("12345");
    signUpPage.clickSubmitButton();
    assertThat(signUpPage.getFirstNameValidationMessage(),is("Please fill out this field."));
  }
  @Test
  public void signupWithoutLastName() {
    signInPage.navigateTo();
    driver.manage().window().setSize(new Dimension(1070, 622));
    signInPage.clickCreateNewAccountLink();
    signUpPage.enterFirstName("mustaaa");
    signUpPage.enterEmail("mustaaa@gmail.com");
    signUpPage.enterPassword("12345");
    signUpPage.enterPasswordConfirmation("12345");
    signUpPage.clickSubmitButton();
    assertThat(signUpPage.getLastNameValidationMessage(),is("Please fill out this field."));
  }
  @Test
  public void signupWithoutEmail() {
    signInPage.navigateTo();
    driver.manage().window().setSize(new Dimension(1070, 622));
    signInPage.clickCreateNewAccountLink();
    signUpPage.enterFirstName("mustaaa");
    signUpPage.enterLastName("kim");
    signUpPage.enterPassword("12345");
    signUpPage.enterPasswordConfirmation("12345");
    signUpPage.clickSubmitButton();
    assertThat(signUpPage.getEmailFieldValidationMessage(),is("Please fill out this field."));
  }
  @Test
  public void signupWithoutPassword() {
    signInPage.navigateTo();
    driver.manage().window().setSize(new Dimension(1070, 622));
    signInPage.clickCreateNewAccountLink();
    signUpPage.enterFirstName("mustaaa");
    signUpPage.enterLastName("kim");
    signUpPage.enterEmail("mustaaa@gmail.com");
//    signUpPage.enterPassword("12345");
    signUpPage.enterPasswordConfirmation("12345");
    signUpPage.clickSubmitButton();
    assertThat(signUpPage.getPasswordFieldValidationMessage(),is("Please fill out this field."));
  }
  @Test
  public void signupWithoutPasswordConfirmation() {
    signInPage.navigateTo();
    driver.manage().window().setSize(new Dimension(1070, 622));
    signInPage.clickCreateNewAccountLink();
    signUpPage.enterFirstName("mustaaa");
    signUpPage.enterLastName("kim");
    signUpPage.enterEmail("mustaaa@gmail.com");
    signUpPage.enterPassword("12345");

    signUpPage.clickSubmitButton();
    assertThat(signUpPage.getPasswordConfirmationValidationMessage(),is("Please fill out this field."));
  }
  @Test
  public void diffPassSignUp() {
    // Navigate to the sign-in page
    signInPage.navigateTo();

    // Click "Create new account" link
    signInPage.clickCreateNewAccountLink();

    // Fill out the sign-up form with mismatched passwords
    signUpPage.enterFirstName("ac");
    signUpPage.enterLastName("asdca");
    signUpPage.enterEmail("abc@gmail.com");
    signUpPage.enterPassword("12345");
    signUpPage.enterPasswordConfirmation("1234");

    // Submit the form
    signUpPage.clickSubmitButton();

    // Verify the error message
    assertThat(signInPage.getErrorMessage(), is("Password does not match"));
  }
  @Test
  public void mailRegx() {
    // Navigate to the sign-in page
    signInPage.navigateTo();

    // Resize window
    driver.manage().window().setSize(new Dimension(603, 809));

    // Click "Create new account" link
    signInPage.clickCreateNewAccountLink();

    // Fill out the sign-up form
    signUpPage.enterFirstName("adsc");
    signUpPage.enterLastName("asdca");
    signUpPage.enterEmail("one@gma,com");
    signUpPage.enterPassword("123451");
    signUpPage.enterPasswordConfirmation("123451");

    // Submit the form
    signUpPage.clickSubmitButton();

    // Verify the "Sign in" link is displayed
    assertThat(driver.findElement(By.linkText("Sign in")).getText(), is("Sign in"));
  }
  @Test
  public void numberNaming() {
    // Navigate to the sign-in page
    signInPage.navigateTo();

    // Resize window
    driver.manage().window().setSize(new Dimension(1920, 1048));

    // Click "Create new account" link
    signInPage.clickCreateNewAccountLink();

    // Fill out the sign-up form
    signUpPage.enterFirstName("123");
    signUpPage.enterLastName("23");
    signUpPage.enterEmail("abc@gmail.com");
    signUpPage.enterPassword("12345");
    signUpPage.enterPasswordConfirmation("12345");

    // Submit the form
    signUpPage.clickSubmitButton();

    // Verify the displayed name is "123 23"
    assertThat(signUpPage.getDisplayedName(), is("123 23"));
  }
  @Test
  public void specialCharacterName() {
    // Navigate to the sign-in page
    signInPage.navigateTo();

    // Click "Create new account" link
    signInPage.clickCreateNewAccountLink();

    // Fill out the sign-up form
    signUpPage.enterFirstName("%$");
    signUpPage.enterLastName("$");
    signUpPage.enterEmail("abc1@gmail.com");
    signUpPage.enterPassword("12345");
    signUpPage.enterPasswordConfirmation("12345");

    // Submit the form
    signUpPage.clickSubmitButton();

    // Verify the displayed name is "%$ $"
    assertThat(signUpPage.getDisplayedName(), is("%$ $"));
  }
  @Test
  public void invalidLengthSignUp() {
    // Navigate to the sign-in page
    signInPage.navigateTo();

    // Click "Create new account" link
    signInPage.clickCreateNewAccountLink();

    // Fill out the sign-up form with mismatched passwords
    signUpPage.enterFirstName("ac");
    signUpPage.enterLastName("asdca");
    signUpPage.enterEmail("abc@gmail.com");
    signUpPage.enterPassword("1234");
    signUpPage.enterPasswordConfirmation("1234");

    // Submit the form
    signUpPage.clickSubmitButton();

    // Verify the error message
    String errorMessage = signUpPage.getErrorMessage();
    assertThat(errorMessage, is("should be at least 5 character(s)"));
  }
}