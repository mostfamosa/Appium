package introToAppium.settingsExe.logic.pages;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

import static introToAppium.settingsExe.logic.entites.enums.SettingsOptions.*;

public class SettingsPage extends BasePage {
    // Locators
    By SEARCH_BAR = MobileBy.id("com.android.settings:id/search_action_bar");
    By SEARCH_INPUT = MobileBy.id("com.google.android.settings.intelligence:id/open_search_view_edit_text");
    By SEARCH_ITEM_NAME = MobileBy.id("android:id/title");
    By SEARCH_ITEM_SWITCH = MobileBy.id("android:id/switch_widget");

    // Elements
    MobileElement searchBar;
    MobileElement searchInput;
    MobileElement firstItemInSearch;
    private DisplayPage displayPage;
    private NetworkPage networkPage;

    public SettingsPage(AndroidDriver driver) {
        super(driver);
        searchBar = waitToVisible(SEARCH_BAR);
    }

    public void search(String searchFor) {
        searchBar.click();
        searchInput = waitToVisible(SEARCH_INPUT);
        searchInput.sendKeys(searchFor);
        firstItemInSearch = waitToVisible(SEARCH_ITEM_NAME);
    }

    public String getFirstItemTitleAfterSearch() {
        return firstItemInSearch.getText();
    }
    public void clickOnSwitch() {
        waitToVisible(SEARCH_ITEM_SWITCH).click();
    }

    public DisplayPage getDisplayPage() {
        return displayPage;
    }

    public NetworkPage getNetworkPage() {
        return networkPage;
    }

    public void clickOnDisplay() {
        scrollAndGetElementByName(DISPLAY.getValue()).click();
        displayPage = new DisplayPage(driver);
    }

    public void clickOnNetworkAndInternet() {
        scrollAndGetElementByName(NETWORK_AND_INTERNET.getValue()).click();
        networkPage = new NetworkPage(driver);
    }
}
