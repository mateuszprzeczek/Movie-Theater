package logic;
import io.file.ConsolePrinter;
import io.file.ImportExport;
import options.AdminOptions;
import options.MainOptions;
import options.MovieFieldsOptions;
import options.UserOptions;
import org.apache.log4j.Logger;

import static helpers.OptionsHelper.*;

public class ApplicationDisplay {
    private BookingController bookingController;
    private MovieController movieController;
    private ImportExport importExport;
    private UserController userController;
    private Logger logger = Logger.getLogger(ApplicationDisplay.class);

    public ApplicationDisplay(BookingController bookingController, MovieController movieController, ImportExport importExport, UserController userController) {
        this.bookingController = bookingController;
        this.movieController = movieController;
        this.importExport = importExport;
        this.userController = userController;
    }

    public void mainMenu() {
        MainOptions mainOptions;
        logger.info("Witamy serdecznie w naszym kinie!");
        logger.info("Jeśli jesteś klientem wybierz 1. Administrator systemu-wybierz 2");
            do {
                printMainOptions();
                mainOptions = getMainOptions();
                switch (mainOptions) {
                    case USER:
                        userMenuDisplay();
                        break;
                    case ADMIN:
                        adminMenuDisplay();
                        break;
                    case EXIT:
                        importExport.exit();
                        break;
                    default:
                        logger.info("Nie ma takiej opcji. Wybierz ponownie: ");
                }
            } while (mainOptions != MainOptions.EXIT);
    }
    private void userMenuDisplay() {
        UserOptions userOptions;
        do {
            printUserOptions();
            userOptions = getUserOptions();
            switch (userOptions){
                case PRINT_MOVIES:
                    movieController.printMovies();
                    break;
                case USER_REGISTRATION:
                    userController.addUser();
                    break;
                case ADD_TICKET:
                    bookingController.addTicket();
                    break;
                case PRINT_TICKETS:
                    bookingController.printUserTickets();
                    break;
                case BACK:
                    break;
                default:
                    logger.info("Nie ma takiej opcji. Wybierz ponownie: ");
            }
        } while (userOptions != UserOptions.BACK);
    }

    private void adminMenuDisplay(){
        AdminOptions adminOptions;
        do {
            printAdminOptions();
            adminOptions = getAdminOptions();
            switch (adminOptions) {
                case PRINT_MOVIES:
                    movieController.printMovies();
                    break;
                case ADD_MOVIE:
                    movieController.addMovie();
                    break;
                case PRINT_TICKETS:
                    ConsolePrinter.printTickets(movieController.cinema.getCinemaUserMap());
                    break;
                case PRINT_USERS:
                    ConsolePrinter.printUsers(movieController.cinema.getUsers());
                    break;
                case CHANGE_MOVIE_VALUES:
                    changeMovieValuesDisplay();
                    break;
                case BACK:
                    break;
                default:
                    logger.info("Nie ma takiej opcji. Wybierz ponownie: ");
            }
        } while (adminOptions != AdminOptions.BACK);
    }

    private void changeMovieValuesDisplay(){
        MovieFieldsOptions movieFieldsOptions;
        do {
            printMovieFieldsOptions();
            movieFieldsOptions = getMovieFieldsOption();
            switch (movieFieldsOptions) {
                case ADD_TIME_OF_SEANCE:
                    movieController.addTimeOfSeance();
                    break;
                case CHANGE_PRICE:
                    movieController.changeMoviePrice();
                    break;
                case DELETE_MOVIE:
                    movieController.deleteMovie();
                    break;
                case BACK:
                    break;
                default:
                    logger.info("Nie ma takiej opcji. Wybierz ponownie: ");
            }
        } while (movieFieldsOptions != MovieFieldsOptions.BACK);
    }



}
