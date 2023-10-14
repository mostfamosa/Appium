package introToAppium.settingsExe.logic.pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.By;

import static introToAppium.settingsExe.logic.entites.enums.DisplayOptions.*;

public class DisplayPage extends BasePage{

    // locators
    private By SWITCH_AUTO_ROTATE = By.xpath("//android.widget.Switch[@content-desc='Auto-rotate screen']");
    // elements
    private MobileElement autoRotateSwitch;

    private final String checkedAttribute = "checked";

    public DisplayPage(AndroidDriver<AndroidElement> myDriver) {
        super(myDriver);
    }

    public void toggleSwitchAutoRotate() {
        //scroll to the settings
        scrollAndGetElementByName(AUTO_ROTATE_SCREEN_SWITCH.getValue());
        autoRotateSwitch = waitToVisible(SWITCH_AUTO_ROTATE);
        autoRotateSwitch.click();
    }

    public boolean isAutoRotateChecked(){
        return Boolean.parseBoolean(autoRotateSwitch.getAttribute(checkedAttribute));
    }
}
