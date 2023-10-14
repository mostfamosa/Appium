package introToAppium.settingsExe.logic.pages;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.By;

import static introToAppium.settingsExe.logic.entites.enums.NetworkOptions.*;

public class NetworkPage extends BasePage{

    // locators
    private By INTERNET_STATUS = MobileBy.xpath("//android.widget.LinearLayout[@content-desc=\"AndroidWifi,Connected,Wifi signal full.,Open network\"]/android.widget.LinearLayout[1]/android.widget.RelativeLayout/android.widget.TextView[2]");


    public NetworkPage(AndroidDriver<AndroidElement> myDriver) {
        super(myDriver);
    }

    public String getConnectionStatus() {
        MobileElement status = waitToVisible(INTERNET_STATUS);
        return status.getText();
    }

    public void clickOnInternet(){
        scrollAndGetElementByName(INTERNET.getValue()).click();
    }
}
