package introToAppium.settingsExe.logic.entites.enums;

public enum DisplayOptions {
    AUTO_ROTATE_SCREEN_SWITCH("Auto-rotate screen"),

    ;

    private String value;
    DisplayOptions(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
