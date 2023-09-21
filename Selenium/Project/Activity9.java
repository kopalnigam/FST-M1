import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class Activity9 {
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
    public void contactsTest() {
        WebElement username = driver.findElement(By.id("txtUsername"));
        WebElement password = driver.findElement(By.id("txtPassword"));

        username.sendKeys("orange");
        password.sendKeys("orangepassword123");

        driver.findElement(By.id("btnLogin")).click();
        driver.findElement(By.cssSelector("#menu_pim_viewMyDetails > b")).click();
        driver.findElement(By.linkText("Emergency Contacts")).click();
        List<WebElement> firstRow =driver.findElements(By.xpath("/html/body/div[1]/div[3]/div/div[3]/div[2]/form/table/tbody/tr[1]"));
        System.out.println("First row values:");
        for(WebElement cell:firstRow) {
            System.out.println(cell.getText());
        }
        List<WebElement> secondRow =driver.findElements(By.xpath("//*[@id=\"emgcontact_list\"]/tbody/tr[2]"));
        System.out.println("Second row values:");
        for(WebElement cell:secondRow){
            System.out.println(cell.getText());

        }
        List<WebElement> thirdRow=driver.findElements(By.xpath("//*[@id=\"emgcontact_list\"]/tbody/tr[3]"));
        System.out.println("Third row values:");
        for(WebElement cell:thirdRow) {
            System.out.println(cell.getText());
        }

    }
    @AfterClass
    public void afterClass() {
        //Close browser
        driver.close();
    }
}
