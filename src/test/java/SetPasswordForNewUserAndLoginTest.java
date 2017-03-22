import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;

/**
 * Created by Piotr on 2017-03-13.
 */
public class SetPasswordForNewUserAndLoginTest {

    public TestData data = new TestData();
    public static String MAIL;
    WebDriver driver;
    WebDriverWait wait;

    @Before
    public void beforeTest() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 30);

        driver.get(data.getUrlTestArena());
    }

    @After
    public void afterTest() {
       driver.quit();
    }

    @Test
    public void shouldCorrectlyCreateNewPasswordForUSer() {
        driver.findElement(By.id("email")).sendKeys(data.getAdmmail());
        driver.findElement(By.id("password")).sendKeys(data.getAdmpass());
        driver.findElement(By.id("login")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='header_admin']/a")));
        driver.findElement(By.xpath("//div[@class='header_admin']/a")).click();
        driver.findElement(By.xpath("//a[@href='http://demo.testarena.pl/administration/users']")).click();
        driver.findElement(By.xpath("//a[@href='http://demo.testarena.pl/administration/add_user']")).click();
        driver.findElement(By.id("firstname")).sendKeys(data.generateRandomString(7));
        driver.findElement(By.id("lastname")).sendKeys(data.generateRandomString(7));
        driver.findElement(By.id("email")).sendKeys("Bartek" + data.generateRandomNumber(4) + "@mailinator.com");

        WebElement we = driver.findElement(By.id("email"));
        MAIL = (we.getAttribute("value"));
        driver.findElement(By.id("save")).click();
        driver.get(data.getUrlMailinator());
        driver.findElement(By.id("inboxfield")).sendKeys(MAIL);
        driver.findElement(By.xpath("//span[@class='input-group-btn']/button")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class, 'innermail ng-binding') and normalize-space(.)='Utworzenie konta']"))).isDisplayed();
        driver.findElement(By.xpath("//div[contains(@class, 'innermail ng-binding') and normalize-space(.)='Utworzenie konta']")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("publiccontenttypeselect")));
        driver.switchTo().frame("publicshowmaildivcontent");
        driver.findElement(By.xpath("//a[contains(@rel, 'nofollow') and normalize-space(.)='USTAW HASŁO']")).click();

        ArrayList<String> windowHandles = new ArrayList<String>(driver.getWindowHandles());
        driver.close();
        driver.switchTo().window(windowHandles.get(1));

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//form[@class='front-log']/h1[text()='Utwórz hasło do konta']"))).isDisplayed();
        driver.findElement(By.id("password")).sendKeys(data.getNewUserPass());
        driver.findElement(By.id("confirmPassword")).sendKeys(data.getNewUserPass());
        driver.findElement(By.id("new")).click();

        Assert.assertTrue(driver.findElement(By.xpath("//span[@class='user-info']/small[text()='" + MAIL + "']")).isDisplayed());

        driver.findElement(By.xpath("//div[@class='header_logout']/a[@title='Wyloguj']")).click();
    }

   @Test
    public void shouldCorrectlyLoginNewUser() {
        driver.findElement(By.id("email")).sendKeys(MAIL);
        driver.findElement(By.id("password")).sendKeys(data.getNewUserPass());
        driver.findElement(By.id("login")).click();

        Assert.assertTrue(driver.findElement(By.xpath("//span[@class='user-info']/small[text()='" + MAIL + "']")).isDisplayed());
    }

    @Test
    public void shouldNotCorrectlyLoginWithWrongPass() {
        driver.findElement(By.id("email")).sendKeys(MAIL);
        driver.findElement(By.id("password")).sendKeys("WrongPass");
        driver.findElement(By.id("login")).click();

        Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Adres e-mail i/lub hasło są niepoprawne.']")).isDisplayed());
    }

}
