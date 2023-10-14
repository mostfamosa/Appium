package introToAppium.settingsExe.logic.pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    protected AndroidDriver<AndroidElement> driver;

    public BasePage(AndroidDriver<AndroidElement> myDriver) {
        this.driver = myDriver;
    }

    //wait the element to be found
    public MobileElement waitToVisible(By locator) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            return (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("NoSuchElementException: " + e.getMessage());
        } catch (TimeoutException e) {
            throw new TimeoutException("TimeoutException: " + e.getMessage());
        }
    }

    protected MobileElement scrollAndGetElementByName(String name) {
        return driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"" + name + "\").instance(0))");
    }
}
