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

    public static List<LocalTime> setHowManyTimesMovieWillBeShownDuringTheDay (int howManyTimesMovieWillBeShownDuringTheDay, int movieLength){
        List<LocalTime> movieDisplayHoursPerDay = new ArrayList<>();
        LocalTime checkMovieTimeOverlapToNextMovie = null;
        for (int i = 0; i < howManyTimesMovieWillBeShownDuringTheDay; i++){
            logger.info("Seans nr " + (i + 1));
            LocalTime movieDisplayHour = createTimeOfSeance();

            if (checkMovieTimeOverlapToNextMovie != null && checkMovieTimeOverlapToNextMovie.isAfter(movieDisplayHour)){
                System.out.println("Nie możesz ustawić tej godziny. Długość filmu na to nie pozwala");
                i--;
            }else {
                movieDisplayHoursPerDay.add(movieDisplayHour);
            }
            checkMovieTimeOverlapToNextMovie = movieDisplayHour.plusMinutes(movieLength);
        }
        return movieDisplayHoursPerDay;
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
