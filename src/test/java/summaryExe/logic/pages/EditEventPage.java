package summaryExe.logic.pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ByIdOrName;
import summaryExe.logic.enums.EventData;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EditEventPage extends BasePage {

    // Locators
    private final By DELETE_BTN = By.id("com.claudivan.taskagenda:id/item_excluir");
    private final By CONFIRM_DELETE_BTN = By.id("android:id/button1");
    private final By PERMISSION_ALLOW = By.id("com.android.permissioncontroller:id/permission_allow_button");
    private final By EDIT_BTN = By.id("com.claudivan.taskagenda:id/item_editar");
    private final By COMPLETED_CHECK = By.id("com.claudivan.taskagenda:id/cbEventoConcluido");
    private final By EVENT_TYPE = By.id("com.claudivan.taskagenda:id/tvTipo");
    private final By EVENT_NAME = By.id("com.claudivan.taskagenda:id/tvTitulo");
    private final By EVENT_DESCRIPTION = By.id("com.claudivan.taskagenda:id/tvDescricao");
    private final By EVENT_DATE = By.id("com.claudivan.taskagenda:id/tvData");
    private final By EVENT_TIME = By.id("com.claudivan.taskagenda:id/tvHora");

    // Elements
    MobileElement deleteBtn;
    MobileElement confirmDeleteBtn;
    MobileElement allowPermissionBtn;
    MobileElement editBtn;
    MobileElement completedCheck;
    MobileElement eventType;
    MobileElement eventName;
    MobileElement eventDesc;
    MobileElement eventDate;
    MobileElement eventTime;


    public EditEventPage(AndroidDriver<AndroidElement> myDriver) {
        super(myDriver);
        pageInit();
    }

    private void pageInit() {
        deleteBtn = waitToVisible(DELETE_BTN);
        editBtn = waitToVisible(EDIT_BTN);
        completedCheck = waitToVisible(COMPLETED_CHECK);
        eventType = waitToVisible(EVENT_TYPE);
        eventName = waitToVisible(EVENT_NAME);
        eventDesc = waitToVisible(EVENT_DESCRIPTION);
        eventDate = waitToVisible(EVENT_DATE);
        eventTime = waitToVisible(EVENT_TIME);
    }

    public void clickOnDelete(){
        deleteBtn.click();
        confirmDeleteBtn = waitToVisible(CONFIRM_DELETE_BTN);
        confirmDeleteBtn.click();
        allowPermissionBtn = waitToVisible(PERMISSION_ALLOW);
        allowPermissionBtn.click();
    }
    public void clickOnEdit(){
        editBtn.click();
    }
    public void checkCompleted(){
        completedCheck.click();
    }
    public String getEventData(EventData requestedData){
        switch (requestedData) {
            case NAME -> {
                return eventName.getText();
            }
            case DESCRIPTION -> {
                return eventDesc.getText();
            }
            case DATE -> {
                return convertDateFormat(eventDate.getText());
            }
            case TIME -> {
                return eventTime.getText();
            }
            case TYPE -> {
                return eventType.getText();
            }
        }
        throw new IllegalArgumentException("Requested Data: "+requestedData+" NOT FOUND/HANDLED!");
    }

    private String convertDateFormat(String inputDate) {
        try {
            SimpleDateFormat inputFormat = new SimpleDateFormat("EEE, dd MMM yyyy");
            SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date date = inputFormat.parse(inputDate);
            return outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            throw new RuntimeException("Can't convert the date: " + inputDate);
        }
    }
}
