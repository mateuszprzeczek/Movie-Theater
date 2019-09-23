package options;

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
}