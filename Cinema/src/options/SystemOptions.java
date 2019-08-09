package options;

import exception.NoSuchOptionException;

public enum SystemOptions {
        PRINT_MOVIES(1, "Wyświetlanie dostępnych filmów"),
        ADD_MOVIE(2, "Dodaj film"),
        ADD_TIME_OF_SEANCE(3, "Dodaj godzinę seansu"),
        CHANGE_PRICE(4, "Zmień cenę biletu"),
        PRINT_TICKETS(5, "Pokaż zarezerwowane filmy"),
        DELETE_MOVIE(6, "Usuń film"),
        BACK(7, "Wstecz");

        private int value;
        private String description;

        SystemOptions(int value, String description) {
            this.value = value;
            this.description = description;
        }

        @Override
        public String toString() {
            return value + "-" + description;
        }

        public static SystemOptions createFromInt(int option) throws NoSuchOptionException {
            try {
                return SystemOptions.values()[option-1];
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new NoSuchOptionException("Brak opcji o id: " + option);
            }
        }

}

