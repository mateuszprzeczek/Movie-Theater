package helpers;

import org.apache.log4j.Logger;

import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class OptionsHelper {
    private static final Logger logger = Logger.getLogger(ConsolePrinter.class);
    private static final Scanner sc = new Scanner(System.in);


    public static Integer getOptions(Map<Integer, String> options) {
        boolean optionSelected = false;
        Integer returningOption = null;
        while (!optionSelected) {
            try {
                int userSelectedOption = sc.nextInt();
                sc.nextLine();

                if (userSelectedOption <= options.size() + 1) {
                    optionSelected = true;
                    returningOption = userSelectedOption;
                }
            } catch (IndexOutOfBoundsException e) {
                logger.warn(e.getMessage() + "Podaj ponownie: ");
            } catch (InputMismatchException i) {
                sc.nextLine();
                logger.warn("Wprowadzono  która nie jest liczbą. Wprowadź ponownie: ");
            }
        }
        return returningOption;

    }


    public static void printOptions(Map<Integer, String> options) {
        Set<Map.Entry<Integer, String>> entries = options.entrySet();
        for (Map.Entry<Integer, String> option : entries)
            logger.info(option.toString());
    }
}








