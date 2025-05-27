import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.BoardsPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CardCommentTest {
  private WebDriver driver;
  private BoardsPage boardsPage;

  @Before
  public void setUp() {
    WebDriverManager.firefoxdriver().setup();
    driver = new FirefoxDriver();
    driver.manage().window().setSize(new Dimension(654, 751));

    // Instantiate the BoardsPage with the driver
    boardsPage = new BoardsPage(driver);
  }

  @After
  public void tearDown() {
    driver.quit();
  }

  @Test
  public void cardComment() {
    driver.get("http://localhost:4000/sign_in");
    driver.manage().window().setSize(new Dimension(1920, 1048));

    // Use BoardsPage methods for interacting with the UI
    boardsPage.clickAddNewBoard();
    boardsPage.enterBoardName("one");
    boardsPage.clickSubmitButton();   // Clicking the button to create board

    boardsPage.clickViewContainer();  // click on .inner (view-container)

    boardsPage.enterListName("twot");
    boardsPage.clickSubmitButton();   // clicking button to add list

    boardsPage.clickAddNewCardLink();
    boardsPage.enterCardName("card");
    boardsPage.clickSubmitButton();   // click button to add card

    boardsPage.clickCardContent();
    boardsPage.enterCardComment("new comment");
    boardsPage.clickSubmitButton();   // submit the comment

    assertThat(boardsPage.getCardCommentText(), is("new comment"));
  }
}
