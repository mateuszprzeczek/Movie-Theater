package options;

public enum UserOptions {
    PRINT_MOVIES(1, "Wyświetl obecny repertuar."),
    USER_REGISTRATION(2, "Rejestracja użytkownika"),
    ADD_TICKET(3,"Zarezerwuj bilet"),
    PRINT_TICKETS(4, "Pokaż zarezerwowane filmy"),
    BACK(5, "Wstecz.");

    int value;
    String description;

    UserOptions(int value, String description) {
        this.value = value;
        this.description = description;
    }

    @Override
    public String toString() {
        return value + "-" + description;
    }
}

