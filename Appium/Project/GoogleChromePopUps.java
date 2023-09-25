package liveProject;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class GoogleChromePopUps {
    AppiumDriver<MobileElement> driver = null;
    WebDriverWait wait = null;

    @BeforeClass
    public void beforeClass() throws MalformedURLException {

        // Set the Desired Capabilities
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("deviceName", "Pixel4API28");
        caps.setCapability("platformName", "android");
        caps.setCapability("automationName", "UiAutomator2");
        caps.setCapability("appPackage", "com.android.chrome");
        caps.setCapability("appActivity", "com.google.android.apps.chrome.Main");
        caps.setCapability("noReset", true);

        // Initialize Appium Driver
        URL appServer = new URL("http://0.0.0.0:4723/wd/hub");
        driver = new AndroidDriver<MobileElement>(appServer, caps);

        // Initialize WebDriverWait.
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Open the URL in the Chrome browser.
        driver.get("https://www.training-support.net/selenium");
    }

    @Test
    public <MobileElement> void verifySuccesslogin() {

        String expectedSuccessLoginText = "Welcome Back, admin";

        // Wait for Search text box to display
        wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.xpath("//android.widget.Button")));


        // Scroll down to Login Form.
        MobileElement loginPopupsForm = driver.findElement(MobileBy.AndroidUIAutomator("UiScrollable(UiSelector()).getChildByText(UiSelector().className(\"android.view.View\"), \"Popups\")"));

        // Click on the Popups.
        loginPopupsForm.click();

        // Wait for Login form to display.
        wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.xpath("//android.view.View[@text='Popups']")));

        // Click on Sign In button.
        driver.findElementByXPath("//android.widget.Button[@text='Sign In']").click();

        /*
         * Test for valid credentials.
         */

        // Enter valid Username.
        driver.findElementById("username").sendKeys("admin");

        // Enter valid Password.
        driver.findElementById("password").sendKeys("password");

        // Click on Login button.
        driver.findElementByXPath("//android.widget.Button[@text='Log in']").click();

        // Wait for Welcome Back admin text to display.
        wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.id("action-confirmation")));

        // Get Welcome text.
        String actualSuccessLoginText = driver.findElementById("action-confirmation").getText();

        System.out.println("The actual welcome message is : "+actualSuccessLoginText);

        // Verify the error messages.
        Assert.assertEquals(expectedSuccessLoginText, actualSuccessLoginText);

    }

    @Test
    public void verifyUnSuccessfullogin() {

        String expectedError = "Invalid Credentials";

        // Click on Sign In button.
        driver.findElementByXPath("//android.widget.Button[@text='Sign In']").click();

        /*
         * Test for invalid credentials.
         */

        // Clear the earlier text and Enter invalid Username.
        driver.findElementById("username").clear();
        driver.findElementById("username").sendKeys("pankaj");

        // Clear the earlier text and Enter invalid Password.
        driver.findElementById("password").clear();
        driver.findElementById("password").sendKeys("shinde");

        // Click on Login button.
        driver.findElementByXPath("//android.widget.Button[@text='Log in']").click();

        // Wait for error text to display.
        wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.id("action-confirmation")));

        // Get Error message.
        String actualError = driver.findElementById("action-confirmation").getText();

        System.out.println("The actual error message is : "+actualError);

        // Verify the error messages.
        Assert.assertEquals(expectedError, actualError);

    }


    @AfterClass
    public void afterClass() {
        // Close the browser.
        driver.quit();
    }
}
