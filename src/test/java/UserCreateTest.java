import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by student on 12.03.2017.
 */
public class UserCreateTest {
    WebDriver driver;
    WebDriverWait wait;
    TestData data = new TestData();


    @Before
    public void beforeTest() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 20);


        driver.get(data.getUrlTestArena());
        driver.findElement(By.id("email")).sendKeys(data.getAdmmail());
        driver.findElement(By.id("password")).sendKeys(data.getAdmpass());
        driver.findElement(By.id("login")).click();

    }

    @After
    public void afterTest() {
        driver.quit();
    }

    @Test
    public void shouldCreateNewUser() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='header_admin']/a")));
        driver.findElement(By.xpath("//div[@class='header_admin']/a")).click();
        driver.findElement(By.xpath("//a[@href='http://demo.testarena.pl/administration/users']")).click();
        driver.findElement(By.xpath("//a[@href='http://demo.testarena.pl/administration/add_user']")).click();
        driver.findElement(By.id("firstname")).sendKeys(data.generateRandomString(7));
        driver.findElement(By.id("lastname")).sendKeys(data.generateRandomString(7));
        driver.findElement(By.id("email")).sendKeys("Bartek"+ data.generateRandomNumber(3) + "@mailinator.com");
        driver.findElement(By.id("save")).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='j_info_box']/p")));
        Assert.assertTrue(driver.findElement(By.xpath("//div[@id='j_info_box']")).isDisplayed());

    }
}
