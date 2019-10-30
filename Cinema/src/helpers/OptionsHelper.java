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
                int userInput = sc.nextInt();
                option = createMainOptionsFromInt(userInput);
                optionOk = true;
            } catch (NoSuchOptionException e) {
                logger.warn(e.getMessage() + "Podaj ponownie: ");
            } catch (InputMismatchException i) {
                sc.nextLine();
                logger.warn("Wprowadzono  która nie jest liczbą. Wprowadź ponownie: ");}
        }return option;
    }
    public static MainOptions createMainOptionsFromInt(int option) throws NoSuchOptionException {
        try {
            return MainOptions.values()[option];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new NoSuchOptionException("Brak opcji o id: " + option);
        }
    }
    public static void printMainOptions() {
        for (MainOptions option : MainOptions.values())
            logger.info(option.toString());
    }

    public static AdminOptions getAdminOptions() {
        boolean optionValueExist = false;
        AdminOptions option = null;
        while (!optionValueExist) {
            try {
                option = createAdminOptionsFromInt(sc.nextInt());
                optionValueExist = true;
            } //catch (InputMismatchException e) {
                //logger.info("Wprowadzono wartość, która nie jest liczbą. Wprowadź ponownie: ");
             catch (InputMismatchException | NoSuchOptionException e) {
                 sc.nextLine();
                logger.warn("Nie ma takiej opcji. Podaj ponownie: ");
            }
        }
            return option;
    }
    public static AdminOptions createAdminOptionsFromInt(int option) throws NoSuchOptionException {
        try {
            return AdminOptions.values()[option-1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new NoSuchOptionException("Brak opcji o id: " + option);
        }
    }
    public static void printAdminOptions() {
        logger.info("Wybierz opcję: ");
        for (AdminOptions option : AdminOptions.values())
            logger.info(option.toString());
    }

    public static UserOptions getUserOptions() {
        boolean optionOk = false;
        UserOptions option = null;
        while (!optionOk) {
            try {
                option = createUserOptionsFromInt(sc.nextInt());
                optionOk = true;
            } catch (NoSuchOptionException e) {
                sc.nextLine();
                logger.warn(e.getMessage() + "Podaj ponownie: ");
            } catch (InputMismatchException ignored) {
                sc.nextLine();
                logger.warn("Wprowadzono  która nie jest liczbą. Wprowadź ponownie: ");
            }
        }return option;
    }
    public static void printUserOptions() {
        logger.info("Wybierz opcję: ");
        for (UserOptions option : UserOptions.values())
            logger.info(option.toString());
    }
    public static UserOptions createUserOptionsFromInt(int option) throws NoSuchOptionException {
        try {
            return UserOptions.values()[option-1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new NoSuchOptionException("Brak opcji o id: " + option);
        }
    }
    public static MovieFieldsOptions getMovieFieldsOption() {
        boolean optionOk = false;
        MovieFieldsOptions movieFieldsOptions = null;
        while (!optionOk) {
            try {
                movieFieldsOptions = createMovieFieldsOptionsFromInt(sc.nextInt());
                optionOk = true;
            } catch (NoSuchOptionException e) {
                sc.nextLine();
                logger.warn(e.getMessage() + "Podaj ponownie: ");
            } catch (InputMismatchException ignored) {
                sc.nextLine();
                logger.warn("Wprowadzono  która nie jest liczbą. Wprowadź ponownie: ");
            }
        }return movieFieldsOptions;
    }
    public static MovieFieldsOptions createMovieFieldsOptionsFromInt(int option) throws NoSuchOptionException {
        try {
            return MovieFieldsOptions.values()[option-1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new NoSuchOptionException("Brak opcji o id: " + option);
        }
    }
    public static void printMovieFieldsOptions() {
        logger.info("Wybierz opcję: ");
        for (MovieFieldsOptions option : MovieFieldsOptions.values())
            logger.info(option.toString());
    }


}
