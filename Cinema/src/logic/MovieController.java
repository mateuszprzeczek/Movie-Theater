package logic;

import helpers.MovieTimeOfSeanceHelper;
import io.file.ImportExport;
import io.file.ConsolePrinter;
import model.Cinema;
import model.Movie;
import org.apache.log4j.Logger;

import java.time.LocalTime;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class MovieController {
    private Logger logger = Logger.getLogger(MovieController.class);
    private Scanner sc = new Scanner(System.in);
    public Cinema cinema;
    private ImportExport importExport;


    public MovieController(ImportExport importExport){
        this.importExport = importExport;
    }

    public MovieController() {
    }

    void addMovie() {
        try {
            Movie movie = createMovie();
            cinema.movies.put(movie.getTitle(), movie);
        } catch (InputMismatchException e) {
            System.out.println("Nie udało się utworzyć filmu. Niepoprawne dane!");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Nie można utworzyć kolejnego filmu. Osiągnięto limit pojemności.");
        }
    }
    private Movie createMovie() {
        logger.info("Tytuł filmu: ");
        String title = sc.nextLine();
        logger.info("Długość filmu w minutach: ");
        int length = sc.nextInt();
        logger.info("Ile razy w ciagu dnia bedzie wyświetlany film?");
        List<LocalTime> playingHours = MovieTimeOfSeanceHelper.setNumbersOfViewsPerDay(sc.nextInt());
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

    void addTimeOfSeance() {
        try {
            logger.info("Podaj tytuł filmu");
            String title = sc.nextLine();
            Movie movie = findMovieByTitle(title);
            if (movie != null) {
                List<LocalTime> playingHours = movie.getPlayingHours();
                playingHours.add(MovieTimeOfSeanceHelper.createTimeOfSeance());
                movie = Movie.Builder.newInstance().setPlayingHours(playingHours).build();
            } else {
                logger.info("Nie ma takiego filmu. Dostępne filmy: ");
                printMovies();
            }
        } catch (NullPointerException e) {
            logger.warn("Nie ma takiego filmu");
        }
    }

    void printMovies() {
        ConsolePrinter.printMovies(cinema.getMovies());
    }
    Movie findMovieByTitle(String title) {
        return cinema.movies.get(title);
    }

        void changeMoviePrice() {
            logger.info("Podaj tytuł filmu");
            String title = sc.nextLine();
            try {
                Movie movie = findMovieByTitle(title);
                if (movie != null) {
                    logger.info("Podaj nową cenę: ");
                    movie = Movie.Builder.newInstance().setPrice(sc.nextInt()).build();
                    sc.nextLine();
                } else {
                    logger.warn("Nie ma takiego filmu. Dostępne filmy: ");
                    printMovies();
                }
            } catch (NullPointerException e) {
                logger.error("Nie ma takiego filmu");
            } catch (InputMismatchException e){
                logger.error("Niepoprawne dane!");
            }
        }
    boolean removeMovie(Movie movie){
        if (cinema.movies.containsValue(movie)){
            cinema.movies.remove(movie.getTitle());
            return true;
        }else {
            return false;
        }
    }
        void deleteMovie() {
            try {
                logger.info("Podaj tytuł filmu, który chcesz usunąć: ");
                Movie movie = findMovieByTitle(sc.nextLine());
                if (removeMovie(movie))
                    logger.info("Usunięto film");
                else
                    logger.warn("Brak wskazanego filmu");
            } catch (InputMismatchException e) {
                logger.error("Nie udało się usunąć filmu, niepoprawne dane");
            }
        }

    }

