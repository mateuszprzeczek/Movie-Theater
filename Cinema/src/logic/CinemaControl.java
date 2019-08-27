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
    private Scanner sc = new Scanner(System.in);
    private FileManager fileManager;
    private Cinema cinema;
    private TicketController ticketController;
    private MovieController movieController;


    public CinemaControl(TicketController ticketController, MovieController movieController) {
        this.ticketController = ticketController;
        this.movieController = movieController;
        fileManager = new SerializableFileManager();
        try {
            cinema = fileManager.importData();
            System.out.println("Zaimplementowane dane z pliku: ");
        } catch (DataImportException | InvalidDataException e) {
            System.out.println(e.getMessage());
            System.out.println("Zainicjowano nową bazę.");
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
                    userLoop();
                    break;
                case ADMIN:
                    systemLoop();
                    break;
                case EXIT:
                    exit();
                    break;
                default:
                    System.out.println("Nie ma takiej opcji. Wybierz ponownie: ");
            }
        } while (iOptions != InitialOptions.EXIT );
    }

    private InitialOptions getInitialOptions() {
        boolean optionOk = false;
        InitialOptions option = null;
        while (!optionOk) {
            try {
                option = InitialOptions.createFromInt(sc.nextInt());
                sc.nextLine();
                optionOk = true;
            } catch (NoSuchOptionException e) {
                System.out.println(e.getMessage() + "Podaj ponownie: ");
            } catch (InputMismatchException i) {
                System.out.println("Wprowadzono wartość, która nie jest liczbą. Wprowadź ponownie: ");}
        }return option;
    }
    private void printInitialOptions() {
        for (InitialOptions option : InitialOptions.values())
            System.out.println(option.toString());
    }
    private void exit() {
        try {
            fileManager.exportData(cinema);
            System.out.println("Eksport danych do pliku zakonczony powodzeniem");
        } catch (DataExportException e){
            System.out.println(e.getMessage());
        }
        System.out.println("Koniec programu...");
        sc.close();
    }

    private void userLoop() {
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
                    ticketController.addTicket();
                    break;
                case PRINT_TICKETS:
                    ticketController.printUserTickets();
                    break;
                case BACK:
                    break;
                default:
                    System.out.println("Nie ma takiej opcji. Wybierz ponownie: ");
            }
        } while (uOptions != UserOptions.BACK);
    }
    private UserOptions getUserOptions() {
        boolean optionOk = false;
        UserOptions option = null;
        while (!optionOk) {
            try {
                option = UserOptions.createFromInt(sc.nextInt());
                sc.nextLine();
                optionOk = true;
            } catch (NoSuchOptionException e) {
                System.out.println(e.getMessage() + "Podaj ponownie: ");
            } catch (InputMismatchException ignored) {
                System.out.println("Wprowadzono wartość, która nie jest liczbą. Wprowadź ponownie: ");
            }
        }return option;
    }
    private void printUserOptions() {
        System.out.println("Wybierz opcję: ");
        for (UserOptions option : UserOptions.values())
            System.out.println(option.toString());
    }

    private void addUser() {
        try {
            CinemaUser user = ticketController.createUser();
            cinema.addUser(user);
        } catch (InputMismatchException e) {
            System.out.println("Niepoprawne dane!");
        }
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
                case ADD_TIME_OF_SEANCE:
                    addTimeOfSeance();
                    break;
                case CHANGE_PRICE:
                    changePrice();
                    break;
                case PRINT_TICKETS:
                    printUsers();
                    break;
                case DELETE_MOVIE:
                    movieController.deleteMovie();
                    break;
                case BACK:
                    break;
                default:
                    System.out.println("Nie ma takiej opcji. Wybierz ponownie: ");
            }
        } while (sOptions != SystemOptions.BACK);
    }

    private SystemOptions getSystemOptions() {
        boolean optionOk = false;
        SystemOptions option = null;
        while (!optionOk) {
            try {
                option = SystemOptions.createFromInt(sc.nextInt());
                sc.nextLine();
                optionOk = true;
            } catch (NoSuchOptionException e) {
                System.out.println(e.getMessage() + "Podaj ponownie: ");
            } catch (InputMismatchException ignored) {
                System.out.println("Wprowadzono wartość, która nie jest liczbą. Wprowadź ponownie: "); }
        }return option;
    }
    private void printSystemOptions() {
        System.out.println("Wybierz opcję: ");
        for (SystemOptions option : SystemOptions.values())
            System.out.println(option.toString());
    }
    private void addTimeOfSeance(){
        movieController.addTimeOfSeance();
    }
    public void addMovie(){
        movieController.addMovie();
    }

    private void changePrice(){
        movieController.changeMoviePrice();
    }
    private void printMovies(){
        movieController.printMovies();
    }
    public void printUsers() {
        ticketController.printUsers();
    }
}
