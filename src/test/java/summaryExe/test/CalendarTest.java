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
import summaryExe.logic.enums.EventData;
import summaryExe.logic.pages.CalendarPage;

import java.util.stream.Stream;

import static introToAppium.settingsExe.logic.entites.enums.TestContextKey.KEY_DRIVER;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsStringIgnoringCase;
import static org.junit.jupiter.api.Assertions.*;
import static summaryExe.logic.utils.Date.*;

public class CalendarTest {

}
