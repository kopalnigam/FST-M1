package liveProject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
public class GoogleKeep {
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
    public void googleKeep() throws InterruptedException {
        Thread.sleep(10000);
        // Click on plus icon.
        driver.findElement(By.id("com.google.android.keep:id/new_note_button")).click();
        Thread.sleep(3000);

        String createNote = "Create Note";
        // Enter title.
        driver.findElement(By.id("com.google.android.keep:id/editable_title")).sendKeys(createNote);
        Thread.sleep(3000);

        // Enter description.
        driver.findElement(By.id("com.google.android.keep:id/edit_note_text")).sendKeys("As part of appium project create a note");
        Thread.sleep(3000);

        // Navigate back.
        driver.findElement(By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']")).click();
        Thread.sleep(3000);

        // Verify the Note is created successfully.
        String title = driver.findElement(By.id("com.google.android.keep:id/index_note_title")).getText();
        System.out.println("The title is : "+title);
        Assert.assertEquals(title,createNote);
    }

    @AfterClass
    public void afterClass() {
        // Close the application.
        driver.quit();
    }
}
