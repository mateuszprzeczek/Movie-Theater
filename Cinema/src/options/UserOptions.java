package options;

import exception.NoSuchOptionException;

public enum UserOptions {
    PRINT_MOVIES(1, "Wyświetl obecny repertuar."),
    BOOK(2, "Zarezerwuj bilet"),
    ADD_TICKET(3,"Zarezerwuj następny bilet"),
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

    public static UserOptions createFromInt(int option) throws NoSuchOptionException {
        try {
            return UserOptions.values()[option-1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new NoSuchOptionException("Brak opcji o id: " + option);
        }
    }

}

