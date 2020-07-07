package controller;
import helpers.ConsolePrinter;
import data.ImportData;
import options.*;
import org.apache.log4j.Logger;

import static helpers.OptionsHelper.*;

public class ViewController {

    private final BookingController bookingController;
    private final MovieController movieController;
    private final ImportData importExport;
    private final UserController userController;
    private final Logger logger = Logger.getLogger(ViewController.class);

    public ViewController(BookingController bookingController, MovieController movieController, ImportData importExport, UserController userController) {
        this.bookingController = bookingController;
        this.movieController = movieController;
        this.importExport = importExport;
        this.userController = userController;
    }

    public void mainMenu() {
        logger.info("Witamy serdecznie w naszym kinie!");
        logger.info("Jeśli jesteś klientem wybierz 1. Administrator systemu-wybierz 2");
        int mainOptions;
            do {
                printOptions(Options.mainOptions);
                mainOptions = getOptions(Options.mainOptions);
                switch (mainOptions) {
                    case 1:
                        userMenuDisplay();
                        break;
                    case 2:
                        adminMenuDisplay();
                        break;
                    case 3:
                        importExport.exit();
                        break;
                    default:
                        logger.info("Nie ma takiej opcji. Wybierz ponownie: ");
                }
            } while (mainOptions != 3);
    }

    private void userMenuDisplay() {
        int userOptions;
        do {
            printOptions(Options.userOptions);
            userOptions = getOptions(Options.userOptions);
            switch (userOptions){
                case 1:
                    movieController.printMovies();
                    break;
                case 2:
                    userController.addUser();
                    break;
                case 3:
                    bookingController.addTicket();
                    break;
                case 4:
                    bookingController.printUserTickets();
                    break;
                case 5:
                    break;
                default:
                    logger.info("Nie ma takiej opcji. Wybierz ponownie: ");
            }
        } while (userOptions != 5);
    }

    private void adminMenuDisplay(){
        int adminOptions;
        do {
            printOptions(Options.adminOptions);
            adminOptions = getOptions(Options.adminOptions);
            switch (adminOptions) {
                case 1:
                    movieController.printSeances();
                    break;
                case 2:
                    movieController.addMovie();
                    break;
                case 3:
                    movieController.addSeance();
                case 4:
                    ConsolePrinter.printTickets(movieController.cinema.getCinemaUserMap());
                    break;
                case 5:
                    ConsolePrinter.printUsers(movieController.cinema.getUsers());
                    break;
                case 6:
                    changeMovieValuesDisplay();
                    break;
                case 7:
                    break;
                default:
                    logger.info("Nie ma takiej opcji. Wybierz ponownie: ");
            }
        } while (adminOptions != 7);
    }

    private void changeMovieValuesDisplay(){
        int movieValuesOptions;
        do {
            printOptions(Options.movieFieldsOptions);
            movieValuesOptions = getOptions(Options.movieFieldsOptions);
            switch (movieValuesOptions) {
                case 1:
                    movieController.printMovies();
                    break;
                case 2:
                    movieController.changeMoviePrice();
                    break;
                case 3:
                    movieController.deleteMovie();
                    break;
                case 4:
                    break;
                default:
                    logger.info("Nie ma takiej opcji. Wybierz ponownie: ");
            }
        } while (movieValuesOptions != 4);
    }



}
