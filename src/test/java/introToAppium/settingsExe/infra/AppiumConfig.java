package introToAppium.settingsExe.infra;

import org.openqa.selenium.remote.DesiredCapabilities;

public class AppiumConfig {

    public static DesiredCapabilities configuration(String platformName,String platformVersion,String deviceName,String appPackage,String appActivity){
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", platformName);
        caps.setCapability("platformVersion", platformVersion);
        caps.setCapability("deviceName", deviceName);
        caps.setCapability("appPackage", appPackage);
        caps.setCapability("appActivity", appActivity);
        return caps;
    }
}
