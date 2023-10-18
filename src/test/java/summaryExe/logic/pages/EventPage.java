package summaryExe.logic.pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.By;

public class EventPage extends BasePage {
    // Locators
    private final By EVENT_NAME_INPUT = By.id("com.claudivan.taskagenda:id/etTitulo");
    private final By EVENT_DESCRIPTION_INPUT = By.id("com.claudivan.taskagenda:id/etDescricao");
    private final By SAVE_BTN = By.id("com.claudivan.taskagenda:id/item_salvar");

    // Clock PopUp
    private final By EVENT_TIME = By.id("com.claudivan.taskagenda:id/btHora");
    private final By EVENT_END_TIME = By.id("com.claudivan.taskagenda:id/btAddHoraFim");
    private final By OK_TIME_BTN = By.id("android:id/button1");
    private final By CANCEL_TIME_BTN = By.id("android:id/button2");
    private final String HOURS_LOCATOR = "//android.widget.RadialTimePickerView.RadialPickerTouchHelper[@content-desc=\"%d\"]";
    private final String MINUTES_LOCATOR = "//android.widget.RadialTimePickerView.RadialPickerTouchHelper[@content-desc=\"%d\"]";

    // Elements
    MobileElement eventNameInput;
    MobileElement eventDescInput;
    MobileElement saveBtn;

    // Clock PopUp
    MobileElement selectTimeBtn;
    MobileElement selectEndTimeBtn;
    MobileElement okTimeBtn;
    MobileElement cancelTimeBtn;

    public EventPage(AndroidDriver<AndroidElement> myDriver) {
        super(myDriver);
    }

    public void addNewEvent(String name,String description,int startHour, int startMin, int endHour, int endMin){
        addEventName(name);
        addEventDescription(description);
        addTimeToEvent(startHour,startMin,endHour,endMin);
        saveEvent();
    }

    private void addEventName(String name) {
        eventNameInput = waitToVisible(EVENT_NAME_INPUT);
        eventNameInput.clear();
        eventNameInput.sendKeys(name);
    }

    private void addEventDescription(String desc) {
        eventDescInput = waitToVisible(EVENT_DESCRIPTION_INPUT);
        eventDescInput.clear();
        eventDescInput.sendKeys(desc);
    }

    private void saveEvent() {
        saveBtn = waitToVisible(SAVE_BTN);
        saveBtn.click();
    }

    private void addTimeToEvent(int startHour, int startMin, int endHour, int endMin) {
        selectTimeBtn = waitToVisible(EVENT_TIME);

        selectTimeBtn.click();
        selectHour(startHour);
        selectMinutes(startMin);
        confirmTime();

        //add ending time
        selectEndTimeBtn = waitToVisible(EVENT_END_TIME);
        selectEndTimeBtn.click();
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

    private void selectMinutes(int minutes) {
        By EVENT_MINUTES = By.xpath(String.format(MINUTES_LOCATOR, minutes));
        waitToVisible(EVENT_MINUTES).click();
    }
}
