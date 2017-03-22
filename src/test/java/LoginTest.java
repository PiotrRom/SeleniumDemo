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

/**
 * Created by student on 12.03.2017.
 */
public class LoginTest {
    WebDriver driver;
    WebDriverWait wait;
    TestData data = new TestData();

    @Before
    public void beforeTest(){
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 20);
    }

    @After
    public void afterTest() {
        driver.quit();
    }

    @Test
    public void shouldLoginCorrectly() {

        driver.get(data.getUrlTestArena());
        driver.findElement(By.id("email")).sendKeys(data.getAdmmail());
        driver.findElement(By.id("password")).sendKeys(data.getAdmpass());
        driver.findElement(By.id("login")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='header_logout']/a")));

        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='header_logout']/a")).isDisplayed());
    }
}
