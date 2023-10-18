package summaryExe.logic.pages;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

import static introToAppium.settingsExe.logic.entites.enums.SettingsOptions.DISPLAY;
import static introToAppium.settingsExe.logic.entites.enums.SettingsOptions.NETWORK_AND_INTERNET;

public class CalendarPage extends BasePage {
    // Locators
    By ADD_EVENT_BTN = By.id("com.claudivan.taskagenda:id/btNovoEvento");
    By SIDE_MENU_BTN = By.id("com.claudivan.taskagenda:id/ibtRetroceder");
    By PENDING_TOAST = By.id("com.claudivan.taskagenda:id/btEventosSemana");

    // Elements
    MobileElement addEventBtn;
    MobileElement sideMenuBtn;
    MobileElement pendingToast;

    EventPage eventPage;

    public CalendarPage(AndroidDriver driver) {
        super(driver);
        initPage();
    }

    private void initPage() {
        pendingToast = waitToVisible(PENDING_TOAST);
        addEventBtn = waitToVisible(ADD_EVENT_BTN);
        sideMenuBtn = waitToVisible(SIDE_MENU_BTN);
        eventPage = new EventPage(driver);
    }

    public String getPendingToastMsg(){
        return pendingToast.getText();
    }

    public void addEventToday(){
        addEventBtn.click();
        scrollAndGetElementByName("Today").click();
    }

    public void addEventTomorrow(){
        addEventBtn.click();
        scrollAndGetElementByName("Tomorrow").click();
    }

    public EventPage getEventPage() {
        return eventPage;
    }
}
