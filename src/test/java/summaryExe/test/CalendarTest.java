package summaryExe.test;

import introToAppium.settingsExe.infra.AppiumConfig;
import introToAppium.settingsExe.infra.AppiumDriverManager;
import introToAppium.settingsExe.logic.context.TestContext;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.remote.DesiredCapabilities;
import summaryExe.logic.pages.CalendarPage;

import java.util.stream.Stream;

import static introToAppium.settingsExe.logic.entites.enums.TestContextKey.KEY_DRIVER;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsStringIgnoringCase;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class CalendarTest {
    private static TestContext testContext;
    private final String platformName = "Android";
    private final String platformVersion = "14";
    private final String deviceName = "emulator-5554";
    private final String appPackage = "com.claudivan.taskagenda";
    private final String appActivity = ".Activities.MainActivity";
    private CalendarPage calendarPage;


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
    public void Add_New_Event_Test() {
        // Arrange
        calendarPage.addEventTomorrow();
        // Act
        calendarPage.getAddEventPage().addNewEvent("Tzahi Party","Everyone is welcome",12,0,2,0);
        // Assert
        calendarPage.clickOnEventByName("Tzahi Party");
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
