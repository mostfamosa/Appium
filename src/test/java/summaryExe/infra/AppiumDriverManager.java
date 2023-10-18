package summaryExe.infra;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class AppiumDriverManager {
    private static AndroidDriver<AndroidElement> driver;
    private static String url = "http://localhost:4723/wd/hub";
    public static AndroidDriver<AndroidElement> getDriver(DesiredCapabilities capabilities) {
        if (driver == null) {
            initializeDriver(capabilities);
        }
        return driver;
    }

    private static void initializeDriver(DesiredCapabilities capabilities) {
        try {
            // Initialize the Appium driver using the Appium server URL and capabilities
            driver = new AndroidDriver<>(new URL(url), capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            System.out.println("Unable to initialize the Appium driver.");
        }
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
