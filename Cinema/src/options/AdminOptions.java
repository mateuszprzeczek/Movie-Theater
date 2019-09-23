package options;

public enum AdminOptions {
        PRINT_MOVIES(1, "Wyświetlanie dostępnych filmów"),
        ADD_MOVIE(2, "Dodaj film"),
        PRINT_USERS(3, "Wyświetl zarejestrowanych użytkowników"),
        PRINT_TICKETS(4, "Wyświetl zarezerwowane filmy"),
        CHANGE_MOVIE_VALUES(5, "Zmień godzinę/cenę/usuń film"),
        BACK(6, "Wstecz");

        private int value;
        private String description;

        AdminOptions(int value, String description) {
            this.value = value;
            this.description = description;
        }
        @Override
        public String toString() {
            return value + "-" + description;
        }
}

