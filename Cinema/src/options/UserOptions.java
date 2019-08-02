package options;

import exception.NoSuchOptionException;
import io.file.ConsolePrinter;
import io.file.DataReader;

import java.util.InputMismatchException;

public enum UserOptions {
    EXIT(0, "Wyjście"),
    PRINT_MOVIES(1, "Wyświetl obecny repertuar."),
    BOOK(2, "Zarezerwuj bilet"),
    PRINT_TICKETS(3, "Pokaż zarezerwowane filmy"),
    BACK(4, "Wstecz.");

    int value;
    String description;

    UserOptions(int value, String description) {
        this.value = value;
        this.description = description;
    }
    private ConsolePrinter printer = new ConsolePrinter();
    private DataReader dataReader = new DataReader(printer);

    @Override
    public String toString() {
        return value + "-" + description;
    }

    public static UserOptions createFromInt(int option) throws NoSuchOptionException {
        try {
            return UserOptions.values()[option];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new NoSuchOptionException("Brak opcji o id: " + option);
        }
    }

}

