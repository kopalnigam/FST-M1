package liveProject;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class GoogleKeepReminder {
    AppiumDriver<MobileElement> driver = null;
    WebDriverWait wait;

    @BeforeClass
    public void beforeClass() throws MalformedURLException {
        // Set the Desired Capabilities
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("deviceName", "Pixel4API28");
        caps.setCapability("platformName", "Android");
        caps.setCapability("noReset", true);
        caps.setCapability("appPackage", "com.google.android.keep");
        caps.setCapability("appActivity", ".activities.BrowseActivity");

        // Instantiate Appium Driver
        URL appServer = new URL("http://0.0.0.0:4723/wd/hub");
        driver = new AndroidDriver<MobileElement>(appServer, caps);
        wait = new WebDriverWait(driver, 10);
    }
    @Test
    public void googleKeepReminder() throws InterruptedException {

        Thread.sleep(5000);
        // Click on create new note
        driver.findElementById("com.google.android.keep:id/new_note_button").click();

        Thread.sleep(3000);

        String createNote = "Create Reminder Note";

        // Enter the title.
        driver.findElementById("com.google.android.keep:id/editable_title").sendKeys(createNote);
        Thread.sleep(3000);

        // Enter the description.
        driver.findElementById("com.google.android.keep:id/edit_note_text").sendKeys("As part of appium project create a reminder note");
        Thread.sleep(3000);

        // Click on reminder
        driver.findElementById("menu_reminder").click();
        Thread.sleep(3000);

        // Click on second option.
        driver.findElement(By.xpath("(.//*[@class='android.widget.TextView'])[2]")).click();

        // Click on Navigate back
        driver.findElementByXPath("//android.widget.ImageButton[@content-desc='Navigate up']").click();

        boolean found = driver.findElementById("reminder_chip_text").isDisplayed();

        // Check if the reminder is added.
        Assert.assertTrue(found, "Reminder is not added.");

    }

    @AfterClass
    public void afterClass() {
        // Close the application
        driver.quit();
    }
}
