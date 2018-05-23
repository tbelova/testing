import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UserLoginPage {

    private WebElement login;
    private WebElement password;
    private WebElement confirmPassword;
    private WebElement forcePasswordChange;
    private WebElement fullName;
    private WebElement email;
    private WebElement jabber;
    private WebElement ok;
    private WebElement cancel;

    private WebDriver driver;

    public UserLoginPage(WebDriver driver) {
        this.driver = driver;

        login = driver.findElement(By.id("id_l.U.cr.login"));
        password = driver.findElement(By.id("id_l.U.cr.password"));
        confirmPassword = driver.findElement(By.id("id_l.U.cr.confirmPassword"));
        forcePasswordChange = driver.findElement(By.id("id_l.U.cr.forcePasswordChange"));
        fullName = driver.findElement(By.id("id_l.U.cr.fullName"));
        email = driver.findElement(By.id("id_l.U.cr.email"));
        jabber  = driver.findElement(By.id("id_l.U.cr.jabber"));
        ok = driver.findElement(By.id("id_l.U.cr.createUserOk"));
        cancel = driver.findElement(By.id("id_l.U.cr.createUserCancel"));
    }

    public void enterLogin(String loginStr) {
        login.sendKeys(loginStr);
    }

    public void enterPassword(String passwordStr) {
        password.sendKeys(passwordStr);
    }

    public void confirmPassword(String passwordStr) {
        confirmPassword.sendKeys(passwordStr);
    }

    public void clickForcePasswordChange() {
        forcePasswordChange.click();
    }

    public void enterFullName(String fullNameStr) {
        fullName.sendKeys(fullNameStr);
    }

    public void enterEmail(String emailStr) {
        email.sendKeys(emailStr);
    }

    public void enterJabber(String jabberStr) {
        jabber.sendKeys(jabberStr);
    }

    public void submit() {
        ok.click();
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.urlMatches(Constants.START_URL + Constants.USER_CREATED + "/*"));
    }

    public void cancel() {
        cancel.click();
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.urlToBe(Constants.START_URL + Constants.USERS));
    }

}
