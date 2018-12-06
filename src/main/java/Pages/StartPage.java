package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class StartPage extends Page {

  public StartPage(WebDriver driver) {
    super(driver);
  }

  @FindBy(xpath = "//a[contains(@href,'inbox#create')]/div/div/div")
  private WebElement sendMessageButton;

  public PatientMessagesPage openPatientMessagesPage() throws InterruptedException {
    sendMessageButton.click();
    Thread.sleep(7000);
    return new PatientMessagesPage(driver);
  }
}
