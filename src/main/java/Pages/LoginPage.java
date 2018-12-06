package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends Page {

  private final static String URL = "http://staging.remedly.com/user-login";

  public LoginPage(WebDriver driver) {
    super(driver);
  }

  @FindBy(xpath = "//input[@id='txtLoginEmail']")
  private WebElement login;

  @FindBy(xpath = "//input[@id='txtLoginPassword']")
  private WebElement password;

  @FindBy(xpath = "//button[contains(@class,'btn-success')]")
  private WebElement loginButton;

  @FindBy(xpath = "//div[@class='red-error']")
  private WebElement textRedMessage;

  public LoginPage openRemedlyLoginPage() {
    getDriver().get(URL);
    return this;
  }

  public boolean isLoginDisplayed() {
    boolean result = login.isDisplayed();
    return result;
  }

  public boolean isLoginEnabled() {
    boolean result = login.isEnabled();
    return result;
  }

  public LoginPage typeLogin(String userLogin) {
    login.sendKeys(userLogin);
    return this;
  }

  public boolean isPasswordDisplayed() {
    boolean result = password.isDisplayed();
    return result;
  }

  public boolean isPasswordEnabled() {
    boolean result = password.isEnabled();
    return result;
  }

  public LoginPage typePassword(String userPassword) {
    password.sendKeys(userPassword);
    return this;
  }

  public LoginPage clearPassword() {
    password.clear();
    return this;
  }

  public LoginPage clearLogin() {
    login.clear();
    return this;
  }

  public boolean isLoginButtonDisplayed() {
    boolean result = loginButton.isDisplayed();
    return result;
  }

  public boolean isLoginButtonEnabled() {
    boolean result = !loginButton.getAttribute("class")
        .contains("disabled");
    return result;
  }

  public String textRedError() {
    String result = textRedMessage.getText();
    return result;
  }

  public StartPage clickLoginButtonOpenStartPage() throws InterruptedException {
    loginButton.click();
    Thread.sleep(6000);
    return new StartPage(driver);
  }

  public LoginPage clickLoginButton() {
    loginButton.click();
    return this;
  }
}
