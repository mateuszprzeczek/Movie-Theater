package helpers;

import exception.NoSuchOptionException;
import io.file.ConsolePrinter;
import options.AdminOptions;
import options.MainOptions;
import options.MovieFieldsOptions;
import options.UserOptions;
import org.apache.log4j.Logger;

import java.util.InputMismatchException;
import java.util.Scanner;

public class OptionsHelper {
    private static Logger logger = Logger.getLogger(ConsolePrinter.class);
    private static Scanner sc = new Scanner(System.in);

    public static MainOptions getMainOptions() {
        boolean optionOk = false;
        MainOptions option = null;
        while (!optionOk) {
            try {
                option = MainOptions.createFromInt(sc.nextInt());
                sc.nextLine();
                optionOk = true;
            } catch (NoSuchOptionException e) {
                logger.warn(e.getMessage() + "Podaj ponownie: ");
            } catch (InputMismatchException i) {
                logger.warn("Wprowadzono wartość, która nie jest liczbą. Wprowadź ponownie: ");}
        }return option;
    }
    public static void printInitialOptions() {
        for (MainOptions option : MainOptions.values())
            logger.info(option.toString());
    }

    public static AdminOptions getAdminOptions() {
        boolean optionOk = false;
        AdminOptions option = null;
        while (!optionOk) {
            try {
                option = AdminOptions.createFromInt(sc.nextInt());
                sc.nextLine();
                optionOk = true;
            } catch (NoSuchOptionException e) {
                logger.warn(e.getMessage() + "Podaj ponownie: ");
            } catch (InputMismatchException ignored) {
                logger.warn("Wprowadzono wartość, która nie jest liczbą. Wprowadź ponownie: "); }
        }return option;
    }
    public static void printSystemOptions() {
        logger.info("Wybierz opcję: ");
        for (AdminOptions option : AdminOptions.values())
            logger.info(option.toString());
    }

    public static UserOptions getUserOptions() {
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
    public static void printUserOptions() {
        logger.info("Wybierz opcję: ");
        for (UserOptions option : UserOptions.values())
            logger.info(option.toString());
    }
    public static MovieFieldsOptions getMovieFieldsOption() {
        boolean optionOk = false;
        MovieFieldsOptions movieFieldsOptions = null;
        while (!optionOk) {
            try {
                movieFieldsOptions = MovieFieldsOptions.createFromInt(sc.nextInt());
                sc.nextLine();
                optionOk = true;
            } catch (NoSuchOptionException e) {
                logger.warn(e.getMessage() + "Podaj ponownie: ");
            } catch (InputMismatchException ignored) {
                logger.warn("Wprowadzono wartość, która nie jest liczbą. Wprowadź ponownie: ");
            }
        }return movieFieldsOptions;
    }
    public static void printMovieFieldsOptions() {
        logger.info("Wybierz opcję: ");
        for (MovieFieldsOptions option : MovieFieldsOptions.values())
            logger.info(option.toString());
    }

}
