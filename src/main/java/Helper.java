import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Helper {

    private WebDriver driver;

    public Helper(WebDriver driver) {
        this.driver = driver;
    }

    public void start() {

        WebDriverWait wait = new WebDriverWait(driver, 5);

        driver.get(Constants.START_URL + Constants.LOGIN);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterLogin(Constants.rootLogin);
        loginPage.enterPassword(Constants.rootPassword);
        loginPage.pressButton();

        wait.until(ExpectedConditions.urlToBe(Constants.START_URL + Constants.DASHBOARD));

    }

    public UserLoginPage getUserLoginPage() {
        UsersPage usersPage = getUsersPage();

        usersPage.createNewUser();

        try {
            WebDriverWait wait = new WebDriverWait(driver, 1);
            wait.until(ExpectedConditions.urlToBe("never"));
        } catch (TimeoutException t) {}

        return new UserLoginPage(driver);
    }

    public UsersPage getUsersPage() {
        return new UsersPage(driver, Constants.START_URL + Constants.USERS);
    }

}
