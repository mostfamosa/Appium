package summaryExe.logic.pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.By;

public class AddEventPage extends BasePage {
    // Locators
    private final By EVENT_NAME_INPUT = By.id("com.claudivan.taskagenda:id/etTitulo");
    private final By EVENT_DESCRIPTION_INPUT = By.id("com.claudivan.taskagenda:id/etDescricao");
    private final By SAVE_BTN = By.id("com.claudivan.taskagenda:id/item_salvar");
    private final By AM_BTN = By.id("android:id/am_label");
    private final By PM_BTN = By.id("android:id/pm_label");
    private final By ERROR_TOAST = By.id("com.claudivan.taskagenda:id/snackbar_text");
    private final By NAVIGATE_BACK = By.xpath("//android.widget.ImageButton[@content-desc=\"Navigate up\"]");

    // Clock PopUp
    private final By EVENT_TIME = By.id("com.claudivan.taskagenda:id/btHora");
    private final By SET_TIME_BY_KEYBOARD = By.id("android:id/toggle_mode");
    private final By EVENT_END_TIME = By.id("com.claudivan.taskagenda:id/btAddHoraFim");
    private final By EVENT_END_TIME_END = By.id("com.claudivan.taskagenda:id/btHoraFim");
    private final By OK_TIME_BTN = By.id("android:id/button1");
    private final By CANCEL_TIME_BTN = By.id("android:id/button2");
    private final By MINUTES_INPUT = By.id("android:id/input_minute");
    private final String HOURS_LOCATOR = "//android.widget.RadialTimePickerView.RadialPickerTouchHelper[@content-desc=\"%d\"]";

    // Elements
    MobileElement eventNameInput;
    MobileElement eventDescInput;
    MobileElement saveBtn;
    MobileElement errorMsg;
    MobileElement navigateBack;

    // Clock PopUp
    MobileElement selectTimeBtn;
    MobileElement setTimeByKeyboard;
    MobileElement amBtn;
    MobileElement pmBtn;
    MobileElement selectEndTimeBtn;
    MobileElement okTimeBtn;
    MobileElement cancelTimeBtn;

    public AddEventPage(AndroidDriver<AndroidElement> myDriver) {
        super(myDriver);
    }

    public String getErrorMsg(){
        errorMsg = waitToVisible(ERROR_TOAST);
        return errorMsg.getText();
    }
    public void addNewEvent(String name, String description, int startHour, int startMin, int endHour, int endMin) {
        addEventName(name);
        addEventDescription(description);
        addTimeToEvent(startHour, startMin, endHour, endMin);
        saveEvent();
    }

    public void addEventName(String name) {
        eventNameInput = waitToVisible(EVENT_NAME_INPUT);
        eventNameInput.clear();
        eventNameInput.sendKeys(name);
    }

    public void addEventDescription(String desc) {
        eventDescInput = waitToVisible(EVENT_DESCRIPTION_INPUT);
        eventDescInput.clear();
        eventDescInput.sendKeys(desc);
    }

    public void saveEvent() {
        saveBtn = waitToVisible(SAVE_BTN);
        saveBtn.click();
    }

    public void addTimeToEvent(int startHour, int startMin, int endHour, int endMin) {
        selectTimeBtn = waitToVisible(EVENT_TIME);
        selectTimeBtn.click();

        amBtn = waitToVisible(AM_BTN);
        pmBtn = waitToVisible(PM_BTN);
        //deal with 24h format
        if (startHour > 12) {
            pmBtn.click();
            startHour -= 12;
        } else if (startHour == 12) {
            pmBtn.click();
        } else if (startHour == 0) {
            amBtn.click();
            startHour = 12;
        } else amBtn.click();

        selectHour(startHour);
        selectMinutes(startMin);
        confirmTime();

        //add ending time
        selectEndTimeBtn = waitToVisible(EVENT_END_TIME);
        selectEndTimeBtn.click();
        amBtn = waitToVisible(AM_BTN);
        pmBtn = waitToVisible(PM_BTN);
        //deal with 24h format
        if (endHour > 12) {
            pmBtn.click();
            endHour -= 12;
        } else if (endHour == 12) {
            pmBtn.click();
        } else if (endHour == 0) {
            amBtn.click();
            endHour = 12;
        } else amBtn.click();

        selectHour(endHour);
        selectMinutes(endMin);
        confirmTime();
    }

    private void confirmTime() {
        okTimeBtn = waitToVisible(OK_TIME_BTN);
        okTimeBtn.click();
    }

    private void cancelTime() {
        cancelTimeBtn = waitToVisible(CANCEL_TIME_BTN);
        cancelTimeBtn.click();
    }

    private void selectHour(int hour) {
        By EVENT_HOUR = By.xpath(String.format(HOURS_LOCATOR, hour));
        waitToVisible(EVENT_HOUR).click();
    }

    public void navigateBack(){
        navigateBack = waitToVisible(NAVIGATE_BACK);
        navigateBack.click();
    }
    private void selectMinutes(int minutes) {
        setTimeByKeyboard = waitToVisible(SET_TIME_BY_KEYBOARD);
        setTimeByKeyboard.click();
        waitToVisible(MINUTES_INPUT).sendKeys(""+minutes);
        scrollAndGetElementByName("OK");
    }

    public void editTimeToEvent(int startHour, int startMin, int endHour, int endMin) {
        selectTimeBtn = waitToVisible(EVENT_TIME);
        selectTimeBtn.click();

        amBtn = waitToVisible(AM_BTN);
        pmBtn = waitToVisible(PM_BTN);
        //deal with 24h format
        if (startHour > 12) {
            pmBtn.click();
            startHour -= 12;
        } else if (startHour == 12) {
            pmBtn.click();
        } else if (startHour == 0) {
            amBtn.click();
            startHour = 12;
        } else amBtn.click();

        selectHour(startHour);
        selectMinutes(startMin);
        confirmTime();

        //add ending time
        selectEndTimeBtn = waitToVisible(EVENT_END_TIME_END);
        selectEndTimeBtn.click();
        amBtn = waitToVisible(AM_BTN);
        pmBtn = waitToVisible(PM_BTN);
        //deal with 24h format
        if (endHour > 12) {
            pmBtn.click();
            endHour -= 12;
        } else if (endHour == 12) {
            pmBtn.click();
        } else if (endHour == 0) {
            amBtn.click();
            endHour = 12;
        } else amBtn.click();

        selectHour(endHour);
        selectMinutes(endMin);
        confirmTime();
    }
}
