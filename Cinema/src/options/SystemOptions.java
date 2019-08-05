package options;

import exception.NoSuchOptionException;

public enum SystemOptions {
        ADD_MOVIE(1, "Dodanie filmu"),
        PRINT_MOVIES(2, "Wyświetlanie dostępnych filmów"),
        PRINT_TICKETS(3, "Pokaż zarezerwowane filmy"),
        DELETE_MOVIE(4, "Usuwanie filmu"),
        BACK(5, "Wstecz");

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

