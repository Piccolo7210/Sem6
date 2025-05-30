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
//public class AddBoardmemberTest {
//  private WebDriver driver;
//  private Map<String, Object> vars;
//  JavascriptExecutor js;
//  private WebDriverWait wait;
//  @Before
//  public void setUp() {
//    WebDriverManager.firefoxdriver().setup();
//    driver = new FirefoxDriver();
//    driver.manage().window().maximize();
//    wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//  }
//  @After
//  public void tearDown() {
//    driver.quit();
//  }
//  @Test
//  public void addbdmember() {
//    driver.get("http://localhost:4000/sign_in");
//    driver.manage().window().setSize(new Dimension(1151, 1063));
//    driver.findElement(By.cssSelector("button")).click();
////    driver.findElement(By.id("add_new_board")).click();
//    wait.until(ExpectedConditions.elementToBeClickable(By.id("add_new_board"))).click();
////
//    driver.findElement(By.id("board_name")).sendKeys("trm");
//    driver.findElement(By.cssSelector("button")).click();
//    driver.findElement(By.cssSelector("li > .add-new")).click();
//    {
//      WebElement element = driver.findElement(By.cssSelector("li > .add-new"));
//      Actions builder = new Actions(driver);
//      builder.moveToElement(element).perform();
//    }
//    {
//      WebElement element = driver.findElement(By.tagName("body"));
//      Actions builder = new Actions(driver);
//      builder.moveToElement(element, 0, 0).perform();
//    }
//    driver.findElement(By.id("crawljax_member_email")).click();
//    driver.findElement(By.id("crawljax_member_email")).sendKeys("toha@gmail.com");
//    driver.findElement(By.cssSelector("button")).click();
//    {
//      List<WebElement> elements = driver.findElements(By.cssSelector("li:nth-child(2) > .react-gravatar"));
//      assert(elements.size() > 0);
//    }
//  }
//}



import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.BoardsPage;
import org.example.SignInPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class BoardPageTest {
  private WebDriver driver;
  private SignInPage signInPage;
  private BoardsPage boardsPage;

  @Before
  public void setUp() {
    WebDriverManager.firefoxdriver().setup();
    driver = new FirefoxDriver();
    driver.manage().window().maximize();
    signInPage = new SignInPage(driver);
    boardsPage = new BoardsPage(driver);
  }

  @After
  public void tearDown() {
    driver.quit();
  }

  @Test
  public void addBoardMemberTest() {
    // Navigate to the sign-in page
    signInPage.navigateTo();

    // Resize window
    driver.manage().window().setSize(new Dimension(1151, 1063));

    // Login (uncomment and provide valid credentials if needed)
    // signInPage.enterEmail("valid@email.com");
    // signInPage.enterPassword("validPassword");
    signInPage.clickLoginButton();
    System.out.println("Logged in. Current URL: " + driver.getCurrentUrl());

    // Create a new board
    boardsPage.clickAddNewBoard();
    boardsPage.enterBoardName("trm");
    boardsPage.clickSubmitButton();
    System.out.println("Board created");

    // Add a new member
    boardsPage.clickAddNewMember();
    System.out.println("Clicked add new member");
    boardsPage.enterMemberEmail("toha@gmail.com");
    System.out.println("Entered member email");
    boardsPage.clickSubmitButton();
    System.out.println("Submitted member form");

    // Take screenshot for debugging
    try {
      File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
      Files.copy(screenshot.toPath(), Paths.get("member_screenshot.png"));
      System.out.println("Screenshot saved as member_screenshot.png");
    } catch (Exception e) {
      System.out.println("Failed to save screenshot: " + e.getMessage());
    }

    // Save page source for debugging
    try {
      Files.writeString(Paths.get("member_source.html"), driver.getPageSource());
      System.out.println("Page source saved as member_source.html");
    } catch (Exception e) {
      System.out.println("Failed to save page source: " + e.getMessage());
    }

    // Verify the member avatar is present
    assertTrue("At least one member avatar should be present", boardsPage.isMemberAvatarPresent());
  }

  @Test
  public void addWithoutEmail() {
    signInPage.navigateTo();

    // Resize window
    driver.manage().window().setSize(new Dimension(1151, 1063));

    // Login (uncomment and provide valid credentials if needed)
    // signInPage.enterEmail("valid@email.com");
    // signInPage.enterPassword("validPassword");
    signInPage.clickLoginButton();
    System.out.println("Logged in. Current URL: " + driver.getCurrentUrl());

    // Create a new board
    boardsPage.clickAddNewBoard();
    boardsPage.enterBoardName("trm");
    boardsPage.clickSubmitButton();
    System.out.println("Board created");

    // Add a new member
    boardsPage.clickAddNewMember();
    System.out.println("Clicked add new member");
//    boardsPage.enterMemberEmail("");
    assertThat(boardsPage.getEmailFieldValidationMessage(), is("Please fill out this field."));
  }
  @Test
  public void createBoard() {
    signInPage.navigateTo();
    driver.manage().window().setSize(new Dimension(810, 703));
    signInPage.clickLoginButton();
    boardsPage.clickAddNewBoard();
    boardsPage.enterBoardName("@123!{0");
    boardsPage.clickSubmitButton();
    boardsPage.openBoardsNav();
    boardsPage.clickViewAllBoards();
    boardsPage.clickBoardLink();
    assertThat(boardsPage.getBoardTitle(), is("@123!{0"));
  }
  @Test
  public void invalidMailAdd() {
    signInPage.navigateTo();
    driver.manage().window().setSize(new Dimension(1174, 568));
    signInPage.clickLoginButton();
    boardsPage.selectMemberOption();
    boardsPage.clickAddNewMember();
    boardsPage.enterMemberEmail("abczczxc@gmail.com");
    boardsPage.clickSubmitButton();
    assertThat(boardsPage.getErrorMessage(), is("User does not exist"));
  }
}