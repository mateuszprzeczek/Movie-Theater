package builder;

import helpers.MovieBuilderHelper;
import model.Movie;
import org.apache.log4j.Logger;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;


public class MovieBuilder implements Serializable {
    private static Logger logger = Logger.getLogger(MovieBuilder.class);
    private static Scanner sc = new Scanner(System.in);

    public static Movie addMovie() {
        logger.info("Tytuł filmu: ");
        String title = sc.nextLine();
        logger.info("Długość filmu w minutach: ");
        int length = sc.nextInt();
        logger.info("Ile razy w ciagu dnia bedzie wyświetlany film?");
        List<LocalTime> playingHours = MovieBuilderHelper.setNumbersOfViewsPerDay(sc.nextInt());
        logger.info("Podaj nr sali kinowej: ");
        int cinemaHallNumber = sc.nextInt();
        logger.info("Podaj cenę ");
        double price = sc.nextDouble();
        sc.nextLine();
        return Movie.Builder.newInstance()
                .setTitle(title)
                .setLength(length)
                .setPlayingHours(playingHours)
                .setCinemaHallNumber(cinemaHallNumber)
                .setPrice(price)
                .build();
    }

}