package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PatientMessagesPage extends Page {

  public PatientMessagesPage(WebDriver driver) {
    super(driver);
  }

  @FindBy(xpath = "//button[@title='Select appointment']")
  private WebElement clickAppointment;

  @FindBy(xpath = "//button[@title='Select appointment']/following-sibling::div[1]/ul/li[@data-original-index='2']")
  private WebElement chooseAppointment;

  @FindBy(xpath = "//button[@title='Your Care Team']")
  private WebElement clickSendTo;

  @FindBy(xpath = "//button[@title='Your Care Team']/following-sibling::div[1]/ul/li[@data-original-index='2']")
  private WebElement chooseCareTeam;

  @FindBy(xpath = "//input[@name='subject']")
  private WebElement subject;

  @FindBy(xpath = "//textarea[@id='compose-message']")
  private WebElement composeMessage;

  @FindBy(xpath = "//a[contains(text(),'Send')]")
  private WebElement sendButton;

  public PatientMessagesPage completeFormMessage(String textSubject, String textMessage) throws InterruptedException {
    clickAppointment.click();
    chooseAppointment.click();
    clickSendTo.click();
    chooseCareTeam.click();
    subject.sendKeys(textSubject);
    composeMessage.sendKeys(textMessage);
    sendButton.click();
    return this;
  }
}
