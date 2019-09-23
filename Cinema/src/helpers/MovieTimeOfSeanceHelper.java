package helpers;

import org.apache.log4j.Logger;

import java.time.DateTimeException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class MovieTimeOfSeanceHelper {
    private static Logger logger = Logger.getLogger(MovieTimeOfSeanceHelper.class);
    private static Scanner sc = new Scanner(System.in);

    public static List<LocalTime> setNumbersOfViewsPerDay (int numbersOfViewsPerDay){
        List<LocalTime> localTimes = new ArrayList<>();
        for (int i = 0; i < numbersOfViewsPerDay; i++){
            logger.info("Seans nr " + (i + 1));
            LocalTime localTime = createTimeOfSeance();
            localTimes.add(localTime);
        }
        return localTimes;
    }

    public static LocalTime createTimeOfSeance() throws DateTimeException {
        LocalTime localTime = null;
        boolean compatibleTimeFormat = false;
        while (!compatibleTimeFormat){
            try {
                logger.info("Podaj godzinę seansu:");
                int hourOfSeance = sc.nextInt();

                logger.info("Podaj, w której minucie zaczyna się seans: ");
                int minutes = sc.nextInt();
                sc.nextLine();
                if (checkTimeFormatIsCorrect(hourOfSeance, minutes)) {
                    localTime = LocalTime.of(hourOfSeance, minutes);
                    compatibleTimeFormat = true;
                }else {
                    logger.info("Niepoprawny format, spróbuj jeszcze raz");
                }
            }catch (InputMismatchException e){
                logger.info("Niepoprawny format wpisywanych danych, spróbuj jeszcze raz");
                sc.nextLine();
            }
        }
        return localTime;
    }
    private static boolean checkTimeFormatIsCorrect(int hour, int minutes){
        if (hour <= 23) if (hour >= 0) if (minutes <= 60) return minutes >= 0;
        return false;
    }

}
