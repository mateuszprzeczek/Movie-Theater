package options;

import app.CinemaControl;
import exception.NoSuchOptionException;
import io.file.ConsolePrinter;
import io.file.DataReader;

import java.util.InputMismatchException;

public enum SystemOptions {
        EXIT(0, "Wyjście z programu"),
        ADD_MOVIE(1, "Dodanie filmu"),
        PRINT_MOVIES(2, "Wyświetlanie dostępnych filmów"),
        DELETE_MOVIE(3, "Usuwanie filmu"),
        BACK(4, "Wstecz");

        private int value;
        private String description;

        SystemOptions(int value, String description) {
            this.value = value;
            this.description = description;
        }
        private ConsolePrinter printer = new ConsolePrinter();
        private DataReader dataReader = new DataReader(printer);

        @Override
        public String toString() {
            return value + "-" + description;
        }

        public static SystemOptions createFromInt(int option) throws NoSuchOptionException {
            try {
                return SystemOptions.values()[option];
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new NoSuchOptionException("Brak opcji o id: " + option);
            }
        }

}

