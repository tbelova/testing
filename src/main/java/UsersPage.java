import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class UsersPage {

    private WebDriver driver;

    public UsersPage(WebDriver driver, String url) {
        driver.get(url);

        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.urlToBe(url));

        this.driver = driver;
    }

    public void createNewUser() {
        WebElement createNewUser = driver.findElement(By.id("id_l.U.createNewUser"));
        createNewUser.click();
    }

    public void deleteAllUsers() {
        while (deleteUser()) {
            WebDriverWait wait = new WebDriverWait(driver, 2);
            try {
                wait.until(ExpectedConditions.alertIsPresent());
            } catch (TimeoutException e) {}
        }
    }

    public boolean deleteUser() {
        List<WebElement> rows = driver.findElements(By.cssSelector("table[class='table users-table'] tbody tr"));
        for (WebElement row: rows) {
            String login = row.findElement(By.cssSelector("td:first-child a")).getText();
            if (login.equals("guest") || login.equals("root")) {
                continue;
            }
            WebElement button = row.findElement(By.cssSelector("td:last-child a[cn='l.U.usersList.deleteUser']"));
            button.click();
            WebDriverWait wait = new WebDriverWait(driver, 5);
            wait.until(ExpectedConditions.alertIsPresent());
            driver.switchTo().alert().accept();
            wait.until(ExpectedConditions.not(ExpectedConditions.alertIsPresent()));
            return true;
        }
        return false;
    }

}
