import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.util.Assert;

import java.net.MalformedURLException;
import java.net.URL;

public class CalculatorTest {
    @Test
    public void calcSumTest() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("platformVersion", "13");
        caps.setCapability("deviceName", "RF8R51CN95V");
        caps.setCapability("appPackage",
                "com.sec.android.app.popupcalculator");
        caps.setCapability("appActivity", ".Calculator");

        AndroidDriver<MobileElement> driver =
                new AndroidDriver<>(new URL("http://localhost:4723/wd/hub"), caps);
        MobileElement number8 = driver.findElementById(
                "com.sec.android.app.popupcalculator:id/calc_keypad_btn_08");
        MobileElement number9 = driver.findElementById(
                "com.sec.android.app.popupcalculator:id/calc_keypad_btn_09");
        MobileElement plus = driver.findElementById(
                "com.sec.android.app.popupcalculator:id/calc_keypad_btn_add");
        MobileElement result = driver.findElementById(
                "com.sec.android.app.popupcalculator:id/calc_tv_result");
        number8.click();
        plus.click();
        number9.click();
        Assertions.assertEquals(result.getText(),"17");
        //this should be in 'after' hook
        driver.quit();
    }
}
