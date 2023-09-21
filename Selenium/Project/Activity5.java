import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.security.Key;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Activity5 {
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
    public void editInfoTest() throws InterruptedException {
        WebElement username = driver.findElement(By.id("txtUsername"));
        WebElement password = driver.findElement(By.id("txtPassword"));

        username.sendKeys("orange");
        password.sendKeys("orangepassword123");

        driver.findElement(By.id("btnLogin")).click();
        WebElement element= driver.findElement(By.cssSelector("#menu_pim_viewMyDetails > b"));
        element.click();
        element.click();

        Thread.sleep(1000);
        driver.findElement(By.id("btnSave")).click();

        WebElement firstName=driver.findElement(By.name("personal[txtEmpFirstName]"));
        WebElement lastName= driver.findElement(By.name("personal[txtEmpLastName]"));
        firstName.clear();
        lastName.clear();
        firstName.sendKeys("Kopal");
        lastName.sendKeys("Nigam");
        WebElement gender=driver.findElement(By.id("personal_optGender_2"));
        boolean G= gender.isSelected();
        if(G==false) {
            gender.click();
        }
        Select nationality= new Select(driver.findElement(By.xpath("//*[@id=\"personal_cmbNation\"]")));
        List<WebElement> list=nationality.getOptions();
        nationality.selectByIndex(82);

        driver.findElement(By.id("btnSave")).click();
    }
    @AfterClass
    public void afterClass() {
        //Close browser
        driver.close();
    }
}
