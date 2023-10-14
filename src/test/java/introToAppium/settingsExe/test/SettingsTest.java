package introToAppium.settingsExe.test;

import introToAppium.settingsExe.infra.AppiumConfig;
import introToAppium.settingsExe.infra.AppiumDriverManager;
import introToAppium.settingsExe.logic.context.TestContext;
import introToAppium.settingsExe.logic.pages.SettingsPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.stream.Stream;

import static introToAppium.settingsExe.logic.entites.enums.TestContextKey.KEY_DRIVER;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.hamcrest.MatcherAssert.*;

public class SettingsTest {
    private static TestContext testContext;
    private final String platformName = "Android";
    private final String platformVersion = "14";
    private final String deviceName = "emulator-5554";
    private final String appPackage = "com.android.settings";
    private final String appActivity = ".Settings";
    private final String expectedConnection = "Connected";
    private SettingsPage settingsPage;


    @BeforeEach
    public void setUp() {
        testContext = new TestContext();
        DesiredCapabilities caps = AppiumConfig.configuration(platformName, platformVersion, deviceName, appPackage, appActivity);
        testContext.put(KEY_DRIVER, AppiumDriverManager.getDriver(caps));
        settingsPage = new SettingsPage(testContext.get(KEY_DRIVER));
    }

    @AfterEach
    public void tearUp() {
        testContext = null;
        AppiumDriverManager.quitDriver();
    }

    @Test
    public void Auto_Rotate_Display_Switch_Off_Test() {
        // Arrange
        settingsPage.clickOnDisplay();
        // Act
        settingsPage.getDisplayPage().toggleSwitchAutoRotate();
        // Assert
        assertFalse(settingsPage.getDisplayPage().isAutoRotateChecked());
    }

    @Test
    public void Validate_Connection_If_Connected_Test() {
        // Arrange
        settingsPage.clickOnNetworkAndInternet();
        settingsPage.getNetworkPage().clickOnInternet();
        // Act
        String status = settingsPage.getNetworkPage().getConnectionStatus();
        // Assert
        assertEquals(expectedConnection, status);
    }

    @ParameterizedTest
    @MethodSource("getSearchInput")
    public void Search_And_Activate_Dark_Theme_Test(String searchInput) {
        // Arrange
        settingsPage.search(searchInput);
        // Act
        String actual = settingsPage.getFirstItemTitleAfterSearch();
        System.out.println("search input: '"+searchInput+"' | search result: '"+actual+"'");
        // Assert
        assertThat(actual, containsStringIgnoringCase(searchInput));

    }

    private static Stream<Arguments> getSearchInput() {
        return Stream.of(
                Arguments.of("dark"),
                Arguments.of("youtube"),
                Arguments.of("music"),
                Arguments.of("disturb"),
                Arguments.of("lock"),
                Arguments.of("screen"),
                Arguments.of("face")
        );
    }
}
