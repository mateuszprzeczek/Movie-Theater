package builder;

import helpers.MovieBuilderHelper;
import model.Movie;
import org.apache.log4j.Logger;

import java.io.Serializable;
import java.util.Scanner;


public class MovieBuilder implements Serializable {
    private static Logger logger = Logger.getLogger(MovieBuilder.class);
    private static Scanner sc = new Scanner(System.in);

    public static Movie addMovie() {
        Movie movie = new Movie();
        logger.info("Tytuł filmu: ");
        movie.setTitle(sc.nextLine());
        logger.info("Długość filmu w minutach: ");
        movie.setLength(sc.nextInt());
        logger.info("Ile razy w ciagu dnia bedzie wyświetlany film?");
        movie.setPlayingHours(MovieBuilderHelper.setNumbersOfViewsPerDay(sc.nextInt()));
        logger.info("Podaj nr sali kinowej: ");
        movie.setCinemaHallNumber(sc.nextInt());
        logger.info("Podaj cenę ");
        movie.setPrice(sc.nextDouble());
        sc.nextLine();
        return movie;
    }


}