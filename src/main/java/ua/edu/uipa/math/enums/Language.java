package ua.edu.uipa.math.enums;

public enum Language {

    EN("en", "English"),
    RU("ru", "Russian"),
    UA("ua", "Ukrainian");

    private String code;
    private String name;

    Language(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
