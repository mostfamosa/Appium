package summaryExe.test;

import introToAppium.settingsExe.infra.AppiumConfig;
import introToAppium.settingsExe.infra.AppiumDriverManager;
import introToAppium.settingsExe.logic.context.TestContext;
import io.qameta.allure.Description;
import io.qameta.allure.Story;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import summaryExe.logic.enums.EventData;
import summaryExe.logic.pages.CalendarPage;

import static introToAppium.settingsExe.logic.entites.enums.TestContextKey.KEY_DRIVER;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static summaryExe.logic.utils.DateAndTimeFormating.*;

public class CalendarTest {
    private static TestContext testContext;
    private final String platformName = "Android";
    private final String platformVersion = "14";
    private final String deviceName = "emulator-5554";
    private final String appPackage = "com.claudivan.taskagenda";
    private final String appActivity = ".Activities.MainActivity";
    private CalendarPage calendarPage;

    // dummy data for events
    String NO_PENDING_EVENT = "NO PENDING EVENT";
    String ONE_PENDING_EVENT = "1 PENDING EVENT";
    String TWO_PENDING_EVENT = "2 PENDING EVENTS";
    String eventName = "Tzahi Party";
    String eventDesc = "Everyone is welcome";
    String eventDate = getTomorrowDate();
    String eventType = "Task";
    //String eventTime = "will add it later";

    @BeforeEach
    public void setUp() {
        testContext = new TestContext();
        DesiredCapabilities caps = AppiumConfig.configuration(platformName, platformVersion, deviceName, appPackage, appActivity);
        testContext.put(KEY_DRIVER, AppiumDriverManager.getDriver(caps));
        calendarPage = new CalendarPage(testContext.get(KEY_DRIVER));
    }

    @AfterEach
    public void tearUp() {
        testContext = null;
        AppiumDriverManager.quitDriver();
    }

    @Test
    @Description("Verify adding new event for tomorrow by pending event message")
    @Story("user trying to create new event for tomorrow")
    public void Add_New_Event_Tomorrow_Validate_By_Counter_Pending_Event_Test() {
        // Arrange
        calendarPage.addEventTomorrow();
        // Act
        calendarPage.getAddEventPage().addNewEvent(eventName, eventDesc, 14, 30, 17, 22);
        // Assert
        assertEquals(ONE_PENDING_EVENT, calendarPage.getPendingToastMsg());
    }

    @Test
    @Description("Adding new event for tomorrow without name")
    @Story("user trying to create new event without a name")
    public void Add_New_Event_Without_Name_Error_Test() {
        // Arrange
        calendarPage.addEventTomorrow();
        // Act
        calendarPage.getAddEventPage().addNewEvent("", eventDesc, 14, 30, 17, 22);
        // Assert
        assertEquals("Highlighted fields are mandatory", calendarPage.getAddEventPage().getErrorMsg());
    }

    @Test
    @Description("Verify Error Message After Adding Wrong Time")
    @Story("user trying to create new event with wrong time")
    public void Add_New_Event_With_Time_Error_Test() {
        // Arrange
        calendarPage.addEventTomorrow();
        // Act
        calendarPage.getAddEventPage().addNewEvent(eventName, eventDesc, 14, 30, 8, 22);
        // Assert
        assertEquals("End time cannot be earlier than start time", calendarPage.getAddEventPage().getErrorMsg());
    }

    @Test
    @Description("Verify adding new event for today by pending event message")
    @Story("user trying to create new event for today")
    public void Add_New_Event_Today_Validate_By_Counter_Pending_Event_Test() {
        // Arrange
        calendarPage.addEventToday();
        // Act
        calendarPage.getAddEventPage().addNewEvent(eventName, eventDesc, 14, 30, 17, 22);
        // Assert
        assertEquals(ONE_PENDING_EVENT, calendarPage.getPendingToastMsg());
    }

    @Test
    @Description("Validate all event data after adding it")
    @Story("user adding new event for tomorrow and validate the data")
    public void Add_New_Event_Tomorrow_Validate_Data_Test() {
        // Arrange
        calendarPage.addEventTomorrow();
        // Act
        calendarPage.getAddEventPage().addNewEvent(eventName, eventDesc, 12, 0, 14, 0);
        calendarPage.clickOnEventByName(eventName);
        String expectedTime = formatTimeRange(12,0,14,0);
        // Assert
        assertAll(
                () -> assertEquals(eventName, calendarPage.getEditEventPage().getEventData(EventData.NAME)),
                () -> assertEquals(eventDesc, calendarPage.getEditEventPage().getEventData(EventData.DESCRIPTION)),
                () -> assertEquals(eventType, calendarPage.getEditEventPage().getEventData(EventData.TYPE)),
                () -> assertEquals(eventDate, calendarPage.getEditEventPage().getEventData(EventData.DATE)),
                () -> assertEquals(expectedTime, calendarPage.getEditEventPage().getEventData(EventData.TIME))
        );
    }

    @Test
    @Description("Validate all event data after adding it")
    @Story("user adding new event for today and validate the data")
    public void Add_New_Event_Today_Validate_Data_Test() {
        // Arrange
        eventDate = getTodayDate();
        calendarPage.addEventToday();
        // Act
        calendarPage.getAddEventPage().addNewEvent(eventName, eventDesc, 12, 0, 14, 0);
        calendarPage.clickOnEventByName(eventName);
        String expectedTime = formatTimeRange(12,0,14,0);
        // Assert
        assertAll(
                () -> assertEquals(eventName, calendarPage.getEditEventPage().getEventData(EventData.NAME)),
                () -> assertEquals(eventDesc, calendarPage.getEditEventPage().getEventData(EventData.DESCRIPTION)),
                () -> assertEquals(eventType, calendarPage.getEditEventPage().getEventData(EventData.TYPE)),
                () -> assertEquals(eventDate, calendarPage.getEditEventPage().getEventData(EventData.DATE)),
                () -> assertEquals(expectedTime, calendarPage.getEditEventPage().getEventData(EventData.TIME))
        );
    }

    @Test
    @Description("Adding multiple events then validate counter")
    @Story("user adding multiple events")
    public void Add_New_Two_Events_Validate_By_Counter_Test() {
        // Arrange
        calendarPage.addEventTomorrow();
        calendarPage.getAddEventPage().addNewEvent(eventName, eventDesc, 14, 30, 17, 22);
        // Act
        calendarPage.addEventTomorrow();
        calendarPage.getAddEventPage().addNewEvent(eventName, eventDesc, 16, 30, 17, 22);
        calendarPage.closePermissionPopUp();
        // Assert
        assertEquals(TWO_PENDING_EVENT, calendarPage.getPendingToastMsg());
    }

    @Test
    @Description("Validate the event data after editing event name")
    @Story("user edit the event's name")
    public void Edit_Event_Name_Test() {
        // Arrange
        calendarPage.addEventTomorrow();
        calendarPage.getAddEventPage().addNewEvent(eventName, eventDesc, 12, 0, 13, 0);
        calendarPage.clickOnEventByName(eventName);

        eventName = "Assaf's Party";

        calendarPage.getEditEventPage().clickOnEdit();
        calendarPage.getAddEventPage().addEventName(eventName);
        calendarPage.getAddEventPage().saveEvent();
        // Act
        calendarPage.clickOnEventByName(eventName);
        // Assert
        assertEquals(eventName, calendarPage.getEditEventPage().getEventData(EventData.NAME));
    }

    @Test
    @Description("Validate the event data after editing event description")
    @Story("user edit the event's description")
    public void Edit_Event_Description_Test() {
        // Arrange
        calendarPage.addEventTomorrow();
        calendarPage.getAddEventPage().addNewEvent(eventName, eventDesc, 12, 0, 13, 0);
        calendarPage.clickOnEventByName(eventName);

        eventDesc = "I hope you all can make it";

        calendarPage.getEditEventPage().clickOnEdit();
        calendarPage.getAddEventPage().addEventDescription(eventDesc);
        calendarPage.getAddEventPage().saveEvent();
        // Act
        calendarPage.clickOnEventByName(eventDesc);
        // Assert
        assertEquals(eventDesc, calendarPage.getEditEventPage().getEventData(EventData.DESCRIPTION));
    }

    @Test
    @Description("Validate the event data after editing event time")
    @Story("user edit the event's time")
    public void Edit_Event_Time_Test() {
        // Arrange
        calendarPage.addEventTomorrow();
        calendarPage.getAddEventPage().addNewEvent(eventName, eventDesc, 12, 0, 13, 0);
        calendarPage.clickOnEventByName(eventName);

        calendarPage.getEditEventPage().clickOnEdit();
        calendarPage.getAddEventPage().editTimeToEvent(15,30,17,0);
        // Act
        calendarPage.getAddEventPage().saveEvent();
        // Assert
        assertEquals(formatTimeRange(15,30,17,0), calendarPage.getEditEventPage().getEventData(EventData.TIME));
    }

    @Test
    @Description("Deleting an event then validate the counter")
    @Story("user deletes the event")
    public void Delete_Event_Successfully_Test() {
        // Arrange
        calendarPage.addEventTomorrow();
        calendarPage.getAddEventPage().addNewEvent(eventName, eventDesc, 12, 0, 15, 0);
        // Act
        calendarPage.clickOnEventByName(eventName);
        calendarPage.getEditEventPage().clickOnDelete();
        // Assert
        assertEquals(NO_PENDING_EVENT, calendarPage.getPendingToastMsg());
    }

    @Test
    @Description("Add new event then cancel it and validate it didn't save")
    @Story("user after adding the event data decide to cancel")
    public void Add_Event_Then_Cancel_Nigative_Test() {
        // Arrange
        calendarPage.addEventTomorrow();
        calendarPage.getAddEventPage().addEventName(eventName);
        calendarPage.getAddEventPage().addEventDescription(eventDesc);
        // Act
        calendarPage.getAddEventPage().navigateBack();
        // Assert
        assertEquals(NO_PENDING_EVENT, calendarPage.getPendingToastMsg());
    }


//    @ParameterizedTest
//    @MethodSource("getSearchInput")
//    public void Search_And_Activate_Dark_Theme_Test(String searchInput) {
//        // Arrange
//        settingsPage.search(searchInput);
//        // Act
//        String actual = settingsPage.getFirstItemTitleAfterSearch();
//        System.out.println("search input: '"+searchInput+"' | search result: '"+actual+"'");
//        // Assert
//        assertThat(actual, containsStringIgnoringCase(searchInput));
//
//    }
//
//    private static Stream<Arguments> getSearchInput() {
//        return Stream.of(
//                Arguments.of("dark"),
//                Arguments.of("youtube"),
//                Arguments.of("music"),
//                Arguments.of("disturb"),
//                Arguments.of("lock"),
//                Arguments.of("screen"),
//                Arguments.of("face")
//        );
//    }
}
