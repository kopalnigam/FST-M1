import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class Activity8 {
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

        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"dashboard-quick-launch-panel-menu_holder\"]/table/tbody/tr/td[4]/div/a")).click();
        Thread.sleep(1000);
        WebElement leaveType=driver.findElement(By.xpath("//*[@id=\"applyleave_txtLeaveType\"]"));
        Select s=new Select(leaveType);
        s.selectByVisibleText("DayOff");
        WebElement FrDate = driver.findElement(By.name("applyleave[txtFromDate]"));
        FrDate.click();
        driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/table/tbody/tr[3]/td[3]/a")).click();

        WebElement ToDate = driver.findElement(By.name("applyleave[txtToDate]"));
        ToDate.click();
        driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/table/tbody/tr[3]/td[4]/a")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("applyBtn")).click();
        Thread.sleep(1000);
        driver.findElement(By.linkText("My Leave")).click();
        driver.findElement(By.xpath("//*[@id=\"calFromDate\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/table/tbody/tr[3]/td[3]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"calToDate\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/table/tbody/tr[3]/td[4]/a")).click();
        driver.findElement(By.name("btnSearch")).click();
        List<WebElement> row=driver.findElements(By.xpath("//*[@id=\"resultTable\"]/tbody/tr"));
        for(WebElement cell:row){
            System.out.println(cell.getText());
        }

    }
    @AfterClass
    public void afterClass() {
        //Close browser
        driver.close();
    }
}
