import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.JavascriptExecutor;

import java.util.*;

public class TestTest {
  private WebDriver driver;
  private Map<String, Object> vars;
  JavascriptExecutor js;

  @BeforeMethod
  public void setUp() {
    WebDriverManager.firefoxdriver().setup();
    driver = new FirefoxDriver();
    driver.manage().window().setSize(new Dimension(654, 751));
  }

  @AfterMethod
  public void tearDown() {
    if (driver != null) {
      driver.quit();
    }
  }

  @Test
  public void addlist() {
    driver.get("http://localhost:4000/sign_in");
    driver.manage().window().setSize(new Dimension(654, 751));
    driver.findElement(By.linkText("Create new account")).click();
    driver.findElement(By.id("user_first_name")).click();
    driver.findElement(By.id("user_first_name")).sendKeys("musta");
    driver.findElement(By.id("user_last_name")).sendKeys("kim");
    driver.findElement(By.id("user_email")).sendKeys("musta@gmail.com");
    driver.findElement(By.id("user_password")).sendKeys("123");
    driver.findElement(By.id("user_password_confirmation")).sendKeys("123");
    driver.findElement(By.cssSelector("button")).click();

    WebElement element = driver.findElement(By.cssSelector("button"));
    new Actions(driver).moveToElement(element).perform();

    WebElement body = driver.findElement(By.tagName("body"));
    new Actions(driver).moveToElement(body, 0, 0).perform();
  }
}
