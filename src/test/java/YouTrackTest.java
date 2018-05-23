import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class YouTrackTest {

    private static WebDriver driver;
    private static Helper helper;

    @BeforeClass
    public static void startUserPage() {
        driver = new ChromeDriver();
        helper = new Helper(driver);
        helper.start();
    }

    @AfterClass
    public static void exit() {
        driver.quit();
    }

    @After
    public void deleteUsers() {
        UsersPage usersPage = helper.getUsersPage();
        usersPage.deleteAllUsers();
    }

    @Test
    public void cancelCreateUserSimpleTest() throws Exception {
        UserLoginPage userLoginPage = helper.getUserLoginPage();
        createStandardUser(userLoginPage);
        userLoginPage.cancel();
    }

    @Test
    public void createOneUserSimpleTest() throws Exception {
        UserLoginPage userLoginPage = helper.getUserLoginPage();
        createStandardUser(userLoginPage);
        userLoginPage.submit();
    }

    @Test
    public void createOneUserWithForceChangeTest() throws Exception {
        UserLoginPage userLoginPage = helper.getUserLoginPage();
        createStandardUser(userLoginPage);
        userLoginPage.clickForcePasswordChange();
        userLoginPage.submit();
    }

    @Test
    public void createOneUserWithExtraInformationTest() throws Exception {
        UserLoginPage userLoginPage = helper.getUserLoginPage();
        createStandardUser(userLoginPage);
        userLoginPage.enterEmail("email");
        userLoginPage.enterFullName("name");
        userLoginPage.enterJabber("jabber");

        userLoginPage.submit();
    }

    @Test
    public void loginWithSpecialSymbolsTest() throws Exception {
        UserLoginPage userLoginPage = helper.getUserLoginPage();
        createStandardUserWithLogin(userLoginPage, "|\\?*+-=_");
        userLoginPage.submit();
    }

    @Test
    public void russianLoginTest() throws Exception {
        UserLoginPage userLoginPage = helper.getUserLoginPage();
        createStandardUserWithLogin(userLoginPage, "логин");
        userLoginPage.submit();
    }

    @Test
    public void russianPasswordTest() throws Exception {
        UserLoginPage userLoginPage = helper.getUserLoginPage();
        userLoginPage.enterLogin("login");
        userLoginPage.enterPassword("пароль");
        userLoginPage.confirmPassword("пароль");
        userLoginPage.submit();
    }

    @Test
    public void allRussianTest() throws Exception {
        UserLoginPage userLoginPage = helper.getUserLoginPage();
        userLoginPage.enterLogin("логин");
        userLoginPage.enterPassword("пароль");
        userLoginPage.confirmPassword("пароль");
        userLoginPage.enterFullName("имя");
        userLoginPage.submit();
    }

    @Test
    public void rewriteLoginTest() throws Exception {
        UserLoginPage userLoginPage = helper.getUserLoginPage();
        createStandardUser(userLoginPage);
        userLoginPage.enterLogin("newlogin");
        userLoginPage.submit();
    }

    @Test
    public void createTwoUsersSimpleTest() throws Exception {
        createNUsers(2);
    }

    @Test
    public void createFiveUsersSimpleTest() throws Exception {
        createNUsers(5);
    }

    @Test
    public void createTenUsersSimpleTest() throws Exception {
        try {
            createNUsers(10);
        } catch (Exception e) {
            return;
        }
        throw new RuntimeException();
    }

    //...........................................................................

    @Test
    public void loginWithSpecialDeprecatedSymbol1Test() throws Exception {
        try {
            UserLoginPage userLoginPage = helper.getUserLoginPage();
            createStandardUserWithLogin(userLoginPage, "/");
            userLoginPage.submit();
        } catch (Exception e) {
            return;
        }
        throw new RuntimeException();
    }

    @Test
    public void loginWithSpecialDeprecatedSymbol2Test() throws Exception {
        try {
            UserLoginPage userLoginPage = helper.getUserLoginPage();
            createStandardUserWithLogin(userLoginPage, "<");
            userLoginPage.submit();
        } catch (Exception e) {
            return;
        }
        throw new RuntimeException();
    }

    @Test
    public void loginWithSpecialDeprecatedSymbol3Test() throws Exception {
        try {
            UserLoginPage userLoginPage = helper.getUserLoginPage();
            createStandardUserWithLogin(userLoginPage, ">");
            userLoginPage.submit();
        } catch (Exception e) {
            return;
        }
        throw new RuntimeException();
    }

    @Test
    public void loginWithSpacesTest() throws Exception {
        try {
            UserLoginPage userLoginPage = helper.getUserLoginPage();
            createStandardUserWithLogin(userLoginPage, "log in");
            userLoginPage.submit();
        } catch (Exception e) {
            return;
        }
        throw new RuntimeException();
    }


    @Test
    public void noLoginTest() throws Exception {
        try {
            UserLoginPage userLoginPage = helper.getUserLoginPage();
            userLoginPage.enterLogin("login");
            userLoginPage.enterPassword("1");
            userLoginPage.confirmPassword("2");
            userLoginPage.enterFullName("name");

            userLoginPage.submit();
        } catch (Exception e) {
            return;
        }
        throw new RuntimeException();
    }

    @Test
    public void noPasswordTest() throws Exception {
        try {
            UserLoginPage userLoginPage = helper.getUserLoginPage();
            userLoginPage.enterLogin("login");

            userLoginPage.submit();
        } catch (Exception e) {
            return;
        }
        throw new RuntimeException();
    }


    @Test
    public void notConfirmPasswordTest() throws Exception {
        try {
            UserLoginPage userLoginPage = helper.getUserLoginPage();
            userLoginPage.enterLogin("login");
            userLoginPage.enterPassword("1");
            userLoginPage.enterFullName("name");

            userLoginPage.submit();
        } catch (Exception e) {
            return;
        }
        throw new RuntimeException();
    }

    @Test
    public void wrongPasswordTest() throws Exception {
        try {
            UserLoginPage userLoginPage = helper.getUserLoginPage();
            userLoginPage.enterLogin("login");
            userLoginPage.enterPassword("1");
            userLoginPage.confirmPassword("2");
            userLoginPage.enterFullName("name");

            userLoginPage.submit();
        } catch (Exception e) {
            return;
        }
        throw new RuntimeException();
    }

    @Test
    public void createTwoSameUsersTest() throws Exception {
        try {
            UserLoginPage userLoginPage = helper.getUserLoginPage();
            createStandardUser(userLoginPage);
            userLoginPage.submit();

            userLoginPage = helper.getUserLoginPage();
            createStandardUser(userLoginPage);
            userLoginPage.submit();
        } catch (Exception e) {
            return;
        }
        throw new RuntimeException();
    }

    @Test
    public void createTwoUsersWithSamLoginsTest() throws Exception {
        try {
            UserLoginPage userLoginPage = helper.getUserLoginPage();
            userLoginPage.enterLogin("login");
            userLoginPage.enterPassword("1");
            userLoginPage.confirmPassword("1");
            userLoginPage.enterFullName("name");

            userLoginPage.submit();

            userLoginPage = helper.getUserLoginPage();
            userLoginPage.enterLogin("login");
            userLoginPage.enterPassword("2");
            userLoginPage.confirmPassword("2");
            userLoginPage.enterEmail("email");

            userLoginPage.submit();
        } catch (Exception e) {
            return;
        }
        throw new RuntimeException();
    }

    //...........................................................................

    private void createStandardUser(UserLoginPage userLoginPage) {
        createStandardUserWithLogin(userLoginPage, "login");
    }

    private void createStandardUserWithLogin(UserLoginPage userLoginPage, String login) {
        userLoginPage.enterLogin(login);
        userLoginPage.enterPassword("password");
        userLoginPage.confirmPassword("password");
    }

    private void createNUsers(int n) throws Exception {
        UserLoginPage userLoginPage;
        for (int i = 0; i < n; ++i) {
            userLoginPage = helper.getUserLoginPage();
            createStandardUserWithLogin(userLoginPage, Integer.toString(i));
            userLoginPage.submit();
        }
    }
}