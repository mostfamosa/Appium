package introToAppium.settingsExe.logic.entites.enums;

public enum NetworkOptions {
    INTERNET("Internet"),

    ;

    private String value;
    NetworkOptions(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
