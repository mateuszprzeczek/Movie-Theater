package options;

public enum MainOptions {
    EXIT(0, "Wyjście."),
    USER(1, "Klient"),
    ADMIN(2, "Administrator Systemu");

    int value;
    String description;

    MainOptions(int value, String description) {
        this.value = value;
        this.description = description;
    }
    @Override
    public String toString() {
        return value + "-" + description;
    }
}

