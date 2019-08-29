package logic;

import exception.NoSuchOptionException;
import model.Cinema;
import options.InitialOptions;
import options.SystemOptions;
import options.UserOptions;
import org.apache.log4j.Logger;

import java.util.InputMismatchException;
import java.util.Scanner;

public class CinemaController {
    private Scanner sc = new Scanner(System.in);
    public Cinema cinema;
    private TicketController ticketController;
    private MovieController movieController;
    private Logger logger = Logger.getLogger(CinemaController.class);



    public CinemaController(TicketController ticketController, MovieController movieController, Cinema cinema) {
        this.cinema = cinema;
        this.ticketController = ticketController;
        this.movieController = movieController;
    }

    public void initialLoop() {
        InitialOptions iOptions;
        logger.info("Witamy serdecznie w naszym kinie!");
        logger.info("Jeśli jesteś klientem wybierz 1. Administrator systemu-wybierz 2");
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
                    movieController.exit();
                    break;
                default:
                    logger.info("Nie ma takiej opcji. Wybierz ponownie: ");
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
                logger.warn(e.getMessage() + "Podaj ponownie: ");
            } catch (InputMismatchException i) {
                logger.warn("Wprowadzono wartość, która nie jest liczbą. Wprowadź ponownie: ");}
        }return option;
    }
    private void printInitialOptions() {
        for (InitialOptions option : InitialOptions.values())
            logger.info(option.toString());
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
                    ticketController.addUser();
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
                    logger.info("Nie ma takiej opcji. Wybierz ponownie: ");
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
                logger.warn(e.getMessage() + "Podaj ponownie: ");
            } catch (InputMismatchException ignored) {
                logger.warn("Wprowadzono wartość, która nie jest liczbą. Wprowadź ponownie: ");
            }
        }return option;
    }
    private void printUserOptions() {
        logger.info("Wybierz opcję: ");
        for (UserOptions option : UserOptions.values())
            logger.info(option.toString());
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
                    logger.info("Nie ma takiej opcji. Wybierz ponownie: ");
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
                logger.warn(e.getMessage() + "Podaj ponownie: ");
            } catch (InputMismatchException ignored) {
                logger.warn("Wprowadzono wartość, która nie jest liczbą. Wprowadź ponownie: "); }
        }return option;
    }
    private void printSystemOptions() {
        logger.info("Wybierz opcję: ");
        for (SystemOptions option : SystemOptions.values())
            logger.info(option.toString());
    }
    private void addTimeOfSeance(){
        movieController.addTimeOfSeance();
    }
    private void addMovie(){
        movieController.addMovie();
    }

    private void changePrice(){
        movieController.changeMoviePrice();
    }
    private void printMovies(){
        movieController.printMovies();
    }
    private void printUsers() {
        ticketController.printUsers();
    }
}
