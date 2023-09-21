import com.github.dockerjava.api.model.Link;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v107.page.Page;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Activity4 {
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
    public void addEmployeeTest() throws InterruptedException {
        //Find the username and password fields
        WebElement username = driver.findElement(By.id("txtUsername"));
        WebElement password = driver.findElement(By.id("txtPassword"));

        //Enter credentials
        username.sendKeys("orange");
        password.sendKeys("orangepassword123");

        //Click login
        driver.findElement(By.id("btnLogin")).click();
        WebElement element= driver.findElement(By.cssSelector("#menu_pim_viewPimModule > b"));
        element.click();
        WebElement addElement= driver.findElement(By.id("menu_pim_addEmployee"));
        addElement.click();
        WebElement FirstName= driver.findElement(By.id("firstName"));
        WebElement LastName= driver.findElement(By.id("lastName"));
        FirstName.sendKeys("Kopal");
        LastName.sendKeys("Nigam");
        driver.findElement(By.id("btnSave")).click();
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("#menu_pim_viewPimModule > b")).click();
        driver.findElement(By.id("menu_pim_viewEmployeeList")).click();
        WebElement EmployeeName= driver.findElement(By.id("empsearch_employee_name_empName"));
        EmployeeName.sendKeys("Kopal Nigam");
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("body > div.ac_results > ul > li.ac_even.ac_over")).click();
        Thread.sleep(1000);
        WebElement searchBtn= driver.findElement(By.id("searchBtn"));
        searchBtn.click();
        Thread.sleep(1000);

        List<WebElement> Row= driver.findElements(By.xpath("//*[@id=\"resultTable\"]/tbody/tr[2]"));
        for(WebElement Cell: Row) {
            System.out.println("Cell value is: " + Cell.getText());
            WebElement cellValue = driver.findElement(By.linkText("Kopal"));
            String s=cellValue.getText();
            Assert.assertEquals(s,"Kopal");
            System.out.println("Employee is added");
        }

    }
    @AfterClass
    public void afterClass() {
        //Close browser
        driver.close();
    }
}
