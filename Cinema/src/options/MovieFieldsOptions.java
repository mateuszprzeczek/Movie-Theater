package options;

import exception.NoSuchOptionException;

public enum MovieFieldsOptions {
    ADD_TIME_OF_SEANCE(1, "Dodaj godzinę seansu"),
    CHANGE_PRICE(2, "Zmień cenę biletu"),
    DELETE_MOVIE(3, "Usuń film"),
    BACK(4, "Wstecz");

    private int value;
    private String description;

    MovieFieldsOptions(int value, String description) {
        this.value = value;
        this.description = description;
    }

    @Override
    public String toString() {
        return value + "-" + description;
    }

    public static MovieFieldsOptions createFromInt(int option) throws NoSuchOptionException {
        try {
            return MovieFieldsOptions.values()[option-1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new NoSuchOptionException("Brak opcji o id: " + option);
        }
    }
}