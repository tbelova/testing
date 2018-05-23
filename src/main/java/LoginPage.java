import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {

    private WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterPassword(String passwordStr) {
        WebElement password = driver.findElement(By.id("id_l.L.password"));
        password.sendKeys(passwordStr);
    }

    public void enterLogin(String loginStr) {
        WebElement login = driver.findElement(By.id("id_l.L.login"));
        login.sendKeys(loginStr);
    }

    public void pressButton() {
        WebElement loginButton = driver.findElement(By.id("id_l.L.loginButton"));
        loginButton.click();
    }

}
