package introToAppium.settingsExe.logic.entites.enums;

public enum SettingsOptions {
    DISPLAY("Display"),
    NETWORK_AND_INTERNET("Network & internet"),

    ;

    private String value;
    SettingsOptions(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
