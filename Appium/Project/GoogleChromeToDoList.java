package liveProject;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class GoogleChromeToDoList {
    AppiumDriver<MobileElement> driver = null;
    WebDriverWait wait;

    @BeforeClass
    public <MobileElement> void beforeClass() throws MalformedURLException {
        // Set the Desired Capabilities
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("deviceName", "Pixel4API28");
        caps.setCapability("platformName", "Android");
        caps.setCapability("noReset", true);
        caps.setCapability("appPackage", "com.android.chrome");
        caps.setCapability("appActivity", "com.google.android.apps.chrome.Main");

        // Instantiate Appium Driver
        URL appServer = new URL("http://0.0.0.0:4723/wd/hub");
        driver = new AndroidDriver<MobileElement>(appServer, caps);
        wait = new WebDriverWait(driver, 10);

        // Initialize JavascriptExecutor
        //js = (JavascriptExecutor) driver;

        // Open the URL in the Chrome browser.
        driver.get("https://www.training-support.net/selenium");
    }

    @Test
    public void toDoList() throws InterruptedException {

        // Wait for Search text box to display
        wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.xpath("//android.widget.Button")));

        // Click on Get Started! 
        driver.findElement(By.xpath("//android.widget.Button[contains(@text,'Get Started')]")).click();

        Thread.sleep(4000);

        MobileElement ele = driver.findElement("//android.view.View[contains(@text,'To-Do List')]");

        //js.executeScript("arguments[0].scrollIntoView();", ele);

        Thread.sleep(3000);

        //driver.findElement(MobileBy.AndroidUIAutomator("UiScrollable(UiSelector()).scrollIntoView(text(\"To-Do List\"))")).click();

        // Scroll down to To Do list.
        MobileElement toList = driver.findElement(MobileBy.AndroidUIAutomator("UiScrollable(UiSelector()).getChildByText(UiSelector().className(\"android.view.View\"), \"To-Do List\")"));

        // Click on the To-Do List.
        toList.click();

        // Wait for Search text box to display
        wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.xpath("//android.widget.Button[@text='To-Do List']")));

        // Create an array of three tasks
        String[] tasks = {"Add tasks to list","Get number of tasks","Clear the list"};

        for(int i=0;i<=tasks.length;i++)
        {
            // Below code enters three tasks.
            driver.findElementById("taskInput").sendKeys(tasks[i]);

            // Click on Add Task button.
            driver.findElement("//android.widget.Button[@text='Add Task']").click();

            // Wait for three seconds.
            Thread.sleep(3000);
        }

        // Find the count of the tasks added. 
        List<MobileElement> list = driver.findElementsById("tasksList");
        int size = list.size();

        System.out.println("The count of tasks are : " +size);

        // Strike through the tasks.
        for(int j=1;j<=size;j++)
        {
            driver.findElement("//android.view.View[@id='tasksList']//android.view.View["+j+"]").click();
        }

        // Clear the list.
        driver.findElement("//android.view.View[@text='Clear List']").click();

        // Verify that there are no task list.		
        Assert.assertTrue(!(driver.findElement("(//android.view.View[@id='tasksList']//android.view.View)[1]").isDisplayed()));
    }

    @AfterClass
    public void afterClass() {
        // Close the browser.
        driver.quit();
    }

}
