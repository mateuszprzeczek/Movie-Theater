package app;

import data.Cinema;
import data.Movie;
import exception.DataExportException;
import exception.DataImportException;
import exception.InvalidDataException;
import exception.NoSuchOptionException;
import io.file.ConsolePrinter;
import io.file.DataReader;
import io.file.FileManager;
import io.file.SerializableFileManager;

import java.util.Comparator;
import java.util.InputMismatchException;

public class CinemaControl {
    private ConsolePrinter printer = new ConsolePrinter();
    private DataReader dataReader = new DataReader(printer);
    private FileManager fileManager;

    private Cinema cinema;

    CinemaControl() {
        fileManager = new SerializableFileManager();
        try {
            cinema = fileManager.importData();
            printer.printLine("Zaimplementowane dane z pliku: ");
        } catch (DataImportException | InvalidDataException e) {
            printer.printLine(e.getMessage());
            printer.printLine("Zainicjowano nową bazę.");
            cinema = new Cinema();
        }
    }

    public void controlLoop() {
        Option option;
        System.out.println("Witamy serdecznie w naszym kinie!");
        System.out.println("Dzisiaj gramy: ");
        printMovies();

        do {
            printOptions();
            option = getOption();
            switch (option) {
                case ADD_MOVIE:
                    addMovie();
                    break;
                case PRINT_MOVIES:
                    printMovies();
                    break;
                case DELETE_MOVIE:
                    deleteMovie();
                    break;
                case EXIT:
                    exit();
                    break;
                default:
                    printer.printLine("Nie ma takiej opcji. Wybierz ponownie: ");
            }
        } while (option != Option.EXIT);
    }

    private Option getOption() {
        boolean optionOk = false;
        Option option = null;
        while (!optionOk) {
            try {
                option = Option.createFromInt(dataReader.getInt());
                optionOk = true;
            } catch (NoSuchOptionException e) {
                printer.printLine(e.getMessage() + "Podaj ponownie: ");
            } catch (InputMismatchException ignored) {
                printer.printLine("Wprowadzono wartość, która nie jest liczbą. Wprowadź ponownie: ");
            }
        }
        return option;
    }

    private void printOptions() {
        printer.printLine("Wybierz opcję: ");
        for (Option option : Option.values()) {
            printer.printLine(option.toString());
        }
    }

    private void addMovie() {
        try {
            Movie movie = dataReader.readAndCreateMovie();
            cinema.addMovie(movie);
        } catch (InputMismatchException e) {
            printer.printLine("Nie udało się utworzyć filmu. Niepoprawne dane!");
        } catch (ArrayIndexOutOfBoundsException e) {
            printer.printLine("Nie można utworzyć kolejnego filmu. Osiągnięto limit pojemności.");
        }
    }

    private void printMovies() {
        printer.printMovies(cinema.getMovies());
    }


    private void deleteMovie(){
        try {
            Movie movie = dataReader.readAndCreateMovie();
            if (cinema.removeMovie(movie))
                printer.printLine("Usunięto film");
            else
                printer.printLine("Brak wskazanego filmu");
        } catch (InputMismatchException e){
            printer.printLine("Nie udało się utworzyć filmu, niepoprawne dane");
        }
    }



    private void exit() {
        try {
            fileManager.exportData(cinema);
            printer.printLine("Eksport danych do pliku zakonczony powodzeniem");
        } catch (DataExportException e){
            printer.printLine(e.getMessage());
        }
        printer.printLine("Koniec programu...");
        dataReader.close();
    }

    private enum Option {
        EXIT(0, "Wyjście z programu"),
        ADD_MOVIE(1, "Dodanie filmu"),
        PRINT_MOVIES(2, "Wyświetlanie dostępnych filmów"),
        DELETE_MOVIE(3, "Usuwanie filmu");

        private int value;
        private String description;

        Option(int value, String description) {
            this.value = value;
            this.description = description;
        }

        @Override
        public String toString() {
            return value + "-" + description;
        }

        static Option createFromInt(int option) throws NoSuchOptionException {
            try {
                return Option.values()[option];
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new NoSuchOptionException("Brak opcji o id: " + option);
            }
        }
    }
}
