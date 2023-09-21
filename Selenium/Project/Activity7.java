import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Activity7 {
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
    public void addQualificationTest() {
        WebElement username = driver.findElement(By.id("txtUsername"));
        WebElement password = driver.findElement(By.id("txtPassword"));

        username.sendKeys("orange");
        password.sendKeys("orangepassword123");

        driver.findElement(By.id("btnLogin")).click();
        WebElement element= driver.findElement(By.cssSelector("#menu_pim_viewMyDetails > b"));
        element.click();
        WebElement qElement= driver.findElement(By.linkText("Qualifications"));
        qElement.click();
        driver.findElement(By.id("addWorkExperience")).click();
        WebElement Company=driver.findElement(By.id("experience_employer"));
        WebElement jobTitle=driver.findElement(By.id("experience_jobtitle"));
        Company.sendKeys("IBM");
        jobTitle.sendKeys("Test Specialist");
        WebElement Save=driver.findElement(By.id("btnWorkExpSave"));
        Save.click();

    }
    @AfterClass
    public void afterClass() {
        //Close browser
        driver.close();
    }
}
