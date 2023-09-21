import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Activity6 {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        // Set up the Firefox driver
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();

        //Open browser
        driver.get("http://alchemy.hguy.co/orangehrm");
    }
    @Test
    public void directoryTest() throws InterruptedException {
        WebElement username = driver.findElement(By.id("txtUsername"));
        WebElement password = driver.findElement(By.id("txtPassword"));

        username.sendKeys("orange");
        password.sendKeys("orangepassword123");

        driver.findElement(By.id("btnLogin")).click();
        WebElement dirElement= driver.findElement(By.xpath("//*[@id=\"menu_directory_viewDirectory\"]/b"));
        dirElement.click();
        Thread.sleep(2000);
        String s=driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[1]/div[1]/h1")).getText();
        Assert.assertEquals(s,"Search Directory");

    }
    @AfterClass
    public void afterClass() {
        //Close browser
        driver.close();
    }
}
