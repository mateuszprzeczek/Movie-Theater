package logic;

import exception.*;
import model.*;
import io.file.ConsolePrinter;
import io.file.DataReader;
import io.file.FileManager;
import io.file.SerializableFileManager;
import options.InitialOptions;
import options.SystemOptions;
import options.UserOptions;

import java.util.*;


public class CinemaControl {
    private ConsolePrinter printer = new ConsolePrinter();
    private DataReader dataReader = new DataReader(printer);
    private FileManager fileManager;
    private Cinema cinema;


    public CinemaControl() {
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


    public void initialLoop() {
        InitialOptions iOptions;
        System.out.println("Witamy serdecznie w naszym kinie!");
        System.out.println("Jeśli jesteś klientem wybierz 1. Administrator systemu-wybierz 2");
        do {
            printInitialOptions();
            iOptions = getInitialOptions();
            switch (iOptions){
                case USER:
                    controlLoop();
                    break;
                case ADMIN:
                    systemLoop();
                    break;
                case EXIT:
                    exit();
                    break;
                default:
                    printer.printLine("Nie ma takiej opcji. Wybierz ponownie: ");
            }
        } while (iOptions != InitialOptions.EXIT );
    }
    private void controlLoop() {
        UserOptions uOptions;
        do {
            printUserOptions();
            uOptions = getUserOptions();
            switch (uOptions){
                case PRINT_MOVIES:
                    printMovies();
                    break;
                case USER_REGISTRATION:
                    addUser();
                    break;
                case ADD_TICKET:
                    addTicket();
                    break;
                case PRINT_TICKETS:
                    printUser();
                    break;
                case BACK:
                    break;
                default:
                    printer.printLine("Nie ma takiej opcji. Wybierz ponownie: ");
            }
        } while (uOptions != UserOptions.BACK);

    }


    private void systemLoop(){
        SystemOptions sOptions;
        do {
            printSystemOptions();
            sOptions = getSystemOptions();
            switch (sOptions) {
                case PRINT_MOVIES:
                    printMovies();
                    break;
                case ADD_MOVIE:
                    addMovie();
                    break;
                case ADD_PLAYING_HOUR:
                    addTimeOfSeance();
                    break;
                case CHANGE_PRICE:
                    changeMoviePrice();
                    break;
                case PRINT_TICKETS:
                    printUsers();
                    break;
                case DELETE_MOVIE:
                    deleteMovie();
                    break;
                case BACK:
                    break;
                default:
                    printer.printLine("Nie ma takiej opcji. Wybierz ponownie: ");
            }
        } while (sOptions != SystemOptions.BACK);
    }
    private InitialOptions getInitialOptions() {
        boolean optionOk = false;
        InitialOptions option = null;
        while (!optionOk) {
            try {
                option = InitialOptions.createFromInt(dataReader.getInt());
                optionOk = true;
            } catch (NoSuchOptionException e) {
                printer.printLine(e.getMessage() + "Podaj ponownie: ");
            } catch (InputMismatchException i) {
                printer.printLine("Wprowadzono wartość, która nie jest liczbą. Wprowadź ponownie: ");
            }
        }
        return option;
    }

    private void printInitialOptions() {
        for (InitialOptions option : InitialOptions.values()) {
            printer.printLine(option.toString());
        }
    }
    private SystemOptions getSystemOptions() {
        boolean optionOk = false;
        SystemOptions option = null;
        while (!optionOk) {
            try {
                option = SystemOptions.createFromInt(dataReader.getInt());
                optionOk = true;
            } catch (NoSuchOptionException e) {
                printer.printLine(e.getMessage() + "Podaj ponownie: ");
            } catch (InputMismatchException ignored) {
                printer.printLine("Wprowadzono wartość, która nie jest liczbą. Wprowadź ponownie: ");
            }
        }
        return option;
    }

    private void printSystemOptions() {
        printer.printLine("Wybierz opcję: ");
        for (SystemOptions option : SystemOptions.values()) {
            printer.printLine(option.toString());
        }
    }
    private UserOptions getUserOptions() {
        boolean optionOk = false;
        UserOptions option = null;
        while (!optionOk) {
            try {
                option = UserOptions.createFromInt(dataReader.getInt());
                optionOk = true;
            } catch (NoSuchOptionException e) {
                printer.printLine(e.getMessage() + "Podaj ponownie: ");
            } catch (InputMismatchException ignored) {
                printer.printLine("Wprowadzono wartość, która nie jest liczbą. Wprowadź ponownie: ");
            }
        }
        return option;
    }
    private void printUserOptions() {
        printer.printLine("Wybierz opcję: ");
        for (UserOptions option : UserOptions.values()) {
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
    private void addUser() {
        try {
            CinemaUser user = createUser();
            cinema.addUser(user);
        } catch (InputMismatchException e) {
            printer.printLine("Niepoprawne dane!");
        }
    }

    private Ticket createTicket() {
        Movie movie = findMovie();
        Random random = new Random();
        int rowNumber = random.nextInt(10);
        int seatNumber = random.nextInt(17);
        Ticket ticket = new Ticket(movie, rowNumber, seatNumber);
        printer.printLine("Dodano rezerwację: " + ticket.toString() + "\n");
        return ticket;
    }
    private CinemaUser createUser(){
        printer.printLine("Podaj imię");
        String firstName = dataReader.getString();
        printer.printLine("Podaj nazwisko");
        String lastName = dataReader.getString();
        printer.printLine("Rejestracja "+ firstName + " " + lastName + " zakończona powodzeniem.");
        List<Ticket> listOfTickets = new ArrayList<>();
        Ticket ticket = createTicket();
        listOfTickets.add(ticket);
        return new CinemaUser(firstName, lastName, listOfTickets);
    }



    private Movie findMovie() {
        printer.printLine("Podaj tytuł filmu, który chcesz zarezerwować");
        Movie movie = null;
        boolean ok = false;
        while (!ok) {
            try {
                movie = cinema.findMovieByTitle(dataReader.getString());

                if (movie!=null){

                    ok = true;
                }
                else {
                    printer.printLine("W naszym repertuarze nie ma filmu o podanym tytule, spróbuj jeszcze raz");
                    printer.printLine("Dostępne Filmy:" + "\n");
                    printMovies();
                    printer.printLine("\n" + "Podaj tytuł filmu: ");
                }
            } catch (NullPointerException e) {
                printer.printLine(
                        "Nie posiadamy takiego filmu w naszym repertuarze, podaj ponownie");
                printMovies();
            }

        }return movie;
    }
    private void addTimeOfSeance(){
        printer.printLine("Podaj tytuł filmu");
        String title = dataReader.getString();
        try {
            Movie movie = cinema.findMovieByTitle(title);
            if (movie != null){
                movie.addPlayingHourToList(dataReader.readAndCreateTimeOfSeance());
            }else {
                printer.printLine("Nie ma takiego filmu. Dostępne filmy: ");
                printMovies();
            }
        }catch (NullPointerException e){
            printer.printLine("Nie ma takiego filmu");
        }
    }
    private void changeMoviePrice(){
        printer.printLine("Podaj tytuł filmu");
        String title = dataReader.getString();
        try {
            Movie movie = cinema.findMovieByTitle(title);
            if (movie != null){
                printer.printLine("Podaj nową cenę: ");
                movie.setPrice(dataReader.getInt());
            }else {
                printer.printLine("Nie ma takiego filmu. Dostępne filmy: ");
                printMovies();
            }
        }catch (NullPointerException e){
            printer.printLine("Nie ma takiego filmu");
        }
    }

    private void addTicket() {
        printer.printLine("Podaj nazwisko");
        String lastName = dataReader.getString();
            try {
                CinemaUser user = cinema.findUser(lastName);
                if (user != null){
                    user.addTicketToList(createTicket());
                }else {
                    printer.printLine("Użytkownik o podanym nazwisku nie istnieje");
                    printer.printLine("Jeśli jesteś nowym użytkownikiem, prosimy o rejestrację");
                }
            } catch (NullPointerException e) {
                printer.printLine("Nie posiadamy takiego filmu w repertuarze");
            }
    }
    private void printMovies() {
        printer.printMovies(cinema.getMovies());
    }
    private void printUsers() {
        printer.printUsers(cinema.getUsers());
    }

    private void printUser() {
        printer.printLine("Podaj Nazwisko");
        String lastName = dataReader.getString();
        String notFoundMessage = "Nie odnaleziono uzytkownika o podanym nazwisku";
        cinema.findUserByName(lastName)
                .map(User::toString)
                .ifPresentOrElse(System.out::println, () -> System.out.println(notFoundMessage));
    }

    private void deleteMovie(){
        try {
            printer.printLine("Podaj tytuł filmu, który chcesz usunąć: ");
            Movie movie = cinema.findMovieByTitle(dataReader.getString());
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


}
