package Tests;

import Pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class NegativeTestLoginRemedly {

  private WebDriver driver;
  private LoginPage loginPage;
  private String currentUrl;
  private boolean isCurrentUrl;
  private final static String VALID_LOGIN = "alla_pugacheva@test.com";
  private final static String NONEXISTENT_LOGIN = "olip@gmail.com";
  private final static String VALID_PASSWORD = "d123456";
  private final static String SQL_LOGIN = "UPDATE USERIBD.OP_IPOSET IP = 'IP_VALUE alla_pugacheva@test.com";
  private final static String SQL_PASSWORD = "UPDATE USERIBD.OP_IPOSET IP = 'IP_VALUE d123456";
  private final static String SPECIAL_SYMBOLS_LOGIN = "√ ∫ ∂ ∑ ∏ & alla_pugacheva@test.com";
  private final static String SPECIAL_SYMBOLS_PASSWORD = "UPDATE USERIBD.OP_IPOSET IP = 'IP_VALUE d123456";
  private final static String SPACES_LOGIN = "         ";
  private final static String SPACES_PASSWORD = "          ";
  private final static String LONG_LOGIN = "lgljsdghk jsdhgjsdghdhGH'welsdfjghksfdhgsj;gh;jsfgh;ksdfjghSGHKJXCFGH;SDGH;";
  private final static String START_PAGE = "patient-dashboard";
  private final static String TEXT_RED_ERROR_LOGIN_EXPECTED = "Please provide a valid email address";
  private final static String TEXT_RED_ERROR_PASSWORD_EXPECTED = "Unknown username or password";

  @BeforeClass
  public void initBrowes() {
    System.setProperty("webdriver.gecko.driver", "/usr/local/bin");
    FirefoxOptions options = new FirefoxOptions().setLegacy(true);
    driver = new FirefoxDriver(options);
    driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
  }

  @Test(description = "Open LoginPage. Check the login, the password, the button 'LOG IN'. Check open Start Page.")
  public void checkLoginPage() {
    loginPage = new LoginPage(driver)
        .openRemedlyLoginPage()
        .typePassword(VALID_PASSWORD);
    boolean isLoginButtonLoginPageEnabled = loginPage.isLoginButtonEnabled();
    Assert.assertFalse(isLoginButtonLoginPageEnabled,
        "The login button is enabled with empty login.");


    String textRedErrorLoginLoginPage = loginPage.clickLoginButton()
        .textRedError();
    Assert.assertEquals(textRedErrorLoginLoginPage, TEXT_RED_ERROR_LOGIN_EXPECTED,
        String.format("The text red error does not fit \"%s\".", TEXT_RED_ERROR_LOGIN_EXPECTED));


    verifyStartPageOpens();


    isLoginButtonLoginPageEnabled = loginPage.typeLogin(VALID_LOGIN)
        .clearPassword()
        .isLoginButtonEnabled();
    Assert.assertFalse(isLoginButtonLoginPageEnabled,
        "The login button is enabled with empty password.");


    String textRedErrorPasswordLoginPage = loginPage.clickLoginButton()
        .textRedError();
    Assert.assertEquals(textRedErrorPasswordLoginPage, TEXT_RED_ERROR_PASSWORD_EXPECTED,
        String.format("The text red error does not fit \"%s\".", TEXT_RED_ERROR_PASSWORD_EXPECTED));


    verifyStartPageOpens();


    isLoginButtonLoginPageEnabled = loginPage.clearLogin()
        .typeLogin(NONEXISTENT_LOGIN)
        .typePassword(VALID_PASSWORD)
        .isLoginButtonEnabled();
    Assert.assertTrue(isLoginButtonLoginPageEnabled,
        "The login button is not enabled with nonexistent login.");


    textRedErrorLoginLoginPage = loginPage.clickLoginButton()
        .textRedError();
    Assert.assertEquals(textRedErrorLoginLoginPage, TEXT_RED_ERROR_PASSWORD_EXPECTED,
        String.format("The text red error does not fit \"%s\".", TEXT_RED_ERROR_PASSWORD_EXPECTED));


    verifyStartPageOpens();


    isLoginButtonLoginPageEnabled = loginPage.clearLogin()
        .typeLogin(SQL_LOGIN)
        .clearPassword()
        .typePassword(SQL_PASSWORD)
        .isLoginButtonEnabled();
    Assert.assertTrue(isLoginButtonLoginPageEnabled,
        "The login button is not enabled with SQL login and password.");


    textRedErrorLoginLoginPage = loginPage.clickLoginButton()
        .textRedError();
    Assert.assertEquals(textRedErrorLoginLoginPage, TEXT_RED_ERROR_LOGIN_EXPECTED,
        String.format("The text red error does not fit \"%s\".", TEXT_RED_ERROR_LOGIN_EXPECTED));


    verifyStartPageOpens();


    isLoginButtonLoginPageEnabled = loginPage.clearLogin()
        .typeLogin(SPECIAL_SYMBOLS_LOGIN)
        .clearPassword()
        .typePassword(SPECIAL_SYMBOLS_PASSWORD)
        .isLoginButtonEnabled();
    Assert.assertTrue(isLoginButtonLoginPageEnabled,
        "The login button is not enabled with special symbols in login and password.");


    textRedErrorLoginLoginPage = loginPage.clickLoginButton()
        .textRedError();
    Assert.assertEquals(textRedErrorLoginLoginPage, TEXT_RED_ERROR_LOGIN_EXPECTED,
        String.format("The text red error does not fit \"%s\".", TEXT_RED_ERROR_LOGIN_EXPECTED));


    verifyStartPageOpens();


    isLoginButtonLoginPageEnabled = loginPage.clearLogin()
        .typeLogin(SPACES_LOGIN)
        .clearPassword()
        .typePassword(SPACES_PASSWORD)
        .isLoginButtonEnabled();
    Assert.assertFalse(isLoginButtonLoginPageEnabled,
        "The login button is enabled with spaces in login and password.");


    textRedErrorLoginLoginPage = loginPage.clickLoginButton()
        .textRedError();
    Assert.assertEquals(textRedErrorLoginLoginPage, TEXT_RED_ERROR_LOGIN_EXPECTED,
        String.format("The text red error does not fit \"%s\".", TEXT_RED_ERROR_LOGIN_EXPECTED));


    verifyStartPageOpens();


    isLoginButtonLoginPageEnabled = loginPage.clearLogin()
        .typeLogin(LONG_LOGIN)
        .clearPassword()
        .typePassword(VALID_PASSWORD)
        .isLoginButtonEnabled();
    Assert.assertTrue(isLoginButtonLoginPageEnabled,
        "The login button is not enabled with spaces in login and password.");


    textRedErrorLoginLoginPage = loginPage.clickLoginButton()
        .textRedError();
    Assert.assertEquals(textRedErrorLoginLoginPage, TEXT_RED_ERROR_LOGIN_EXPECTED,
        String.format("The text red error does not fit \"%s\".", TEXT_RED_ERROR_LOGIN_EXPECTED));


    verifyStartPageOpens();
  }

  private void verifyStartPageOpens() {
    currentUrl = driver.getCurrentUrl();
    isCurrentUrl = currentUrl.contains(START_PAGE);
    Assert.assertFalse(isCurrentUrl,
        "Start page opens!");
  }

  @AfterClass(description = "Стоп вебдрайвер", alwaysRun = true)
  public void tearDown() {
    driver.close();
  }
}
