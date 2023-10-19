package summaryExe.logic.pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class CalendarPage extends BasePage {
    // Locators
    private final By ADD_EVENT_BTN = By.id("com.claudivan.taskagenda:id/btNovoEvento");
    private final By SIDE_MENU_BTN = By.id("com.claudivan.taskagenda:id/ibtRetroceder");
    private final By PENDING_TOAST = By.id("com.claudivan.taskagenda:id/btEventosSemana");
    private final By PERMISSION_ALLOW = By.id("com.android.permissioncontroller:id/permission_allow_button");

    // Elements
    MobileElement addEventBtn;
    MobileElement sideMenuBtn;
    MobileElement pendingToast;
    MobileElement allowPermissionBtn;


    AddEventPage addEventPage;
    EditEventPage editEventPage;

    public CalendarPage(AndroidDriver driver) {
        super(driver);
        initPage();
    }

    private void initPage() {
        addEventBtn = waitToVisible(ADD_EVENT_BTN);
        sideMenuBtn = waitToVisible(SIDE_MENU_BTN);
        addEventPage = new AddEventPage(driver);
    }

    public String getPendingToastMsg() {
        pendingToast = waitToVisible(PENDING_TOAST);
        return pendingToast.getText();
    }

    public void addEventToday() {
        addEventBtn.click();
        scrollAndGetElementByName("Today").click();
    }

    public void addEventTomorrow() {
        addEventBtn.click();
        scrollAndGetElementByName("Tomorrow").click();
    }

    public void clickOnEventByName(String eventName) {
        scrollAndGetElementByName(eventName).click();
        editEventPage = new EditEventPage(driver);
    }

    public void closePermissionPopUp() {
        allowPermissionBtn = waitToVisible(PERMISSION_ALLOW);
        allowPermissionBtn.click();
    }

    public AddEventPage getAddEventPage() {
        return addEventPage;
    }

    public EditEventPage getEditEventPage() {
        return editEventPage;
    }
}
