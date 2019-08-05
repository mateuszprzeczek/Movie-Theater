package options;

import exception.NoSuchOptionException;

public enum InitialOptions {
    EXIT(0, "Wyjście."),
    USER(1, "Klient"),
    ADMIN(2, "Administrator Systemu");

    int value;
    String description;

    InitialOptions(int value, String description) {
        this.value = value;
        this.description = description;
    }
    @Override
    public String toString() {
        return value + "-" + description;
    }

    public static InitialOptions createFromInt(int option) throws NoSuchOptionException {
        try {
            return InitialOptions.values()[option];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new NoSuchOptionException("Brak opcji o id: " + option);
        }
    }
}
