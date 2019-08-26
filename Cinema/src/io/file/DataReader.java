package io.file;

import model.*;
import org.apache.log4j.Logger;

import java.io.Serializable;
import java.time.DateTimeException;
import java.time.LocalTime;
import java.util.*;


public class DataReader implements Serializable {
    private static Logger logger = Logger.getLogger(DataReader.class);
    private static Scanner sc = new Scanner(System.in);

    public static Movie createMovie() {
        logger.info("Tytuł filmu: ");
        String title = sc.nextLine();
        logger.info("Długość filmu w minutach: ");
        int length = sc.nextInt();
        logger.info("Ile razy w ciagu dnia bedzie wyświetlany film?");
        int howMany = sc.nextInt();
        List<LocalTime> localTime = timesOfSeance(howMany);
        logger.info("Podaj nr sali kinowej: ");
        int cinemaHallNumber = sc.nextInt();
        logger.info("Podaj cenę ");
        double price = sc.nextDouble();
        sc.nextLine();

        return new Movie(title, length, localTime, cinemaHallNumber, price);
    }




    private static List<LocalTime> timesOfSeance (int numbersOfViewsPerDay){
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
                sc.nextLine();
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
            }
        }
            return localTime;
    }
    private static boolean checkTimeFormatIsCorrect(int hour, int minutes){
        return hour <= 23 && hour >= 0 && minutes <= 60 && minutes >= 0;
    }
}