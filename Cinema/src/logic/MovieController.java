package logic;

import builder.MovieBuilder;
import helpers.MovieBuilderHelper;
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
    private CinemaController cinemaController;

    public MovieController(CinemaController cinemaController) {
        this.cinemaController = cinemaController;
    }

    public MovieController(){}


    public void addMovie() {
        try {
            Movie movie = MovieBuilder.addMovie();
            cinema.movies.put(movie.getTitle(), movie);
        } catch (InputMismatchException e) {
            System.out.println("Nie udało się utworzyć filmu. Niepoprawne dane!");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Nie można utworzyć kolejnego filmu. Osiągnięto limit pojemności.");
        }
    }

    public void addTimeOfSeance() {
        try {
            System.out.println("Podaj tytuł filmu");
            String title = sc.nextLine();
            Movie movie = findMovieByTitle(title);
            if (movie != null) {
                List<LocalTime> playingHours = movie.getPlayingHours();
                playingHours.add(MovieBuilderHelper.createTimeOfSeance());
                movie.setPlayingHours(playingHours);
            } else {
                System.out.println("Nie ma takiego filmu. Dostępne filmy: ");
                printMovies();
            }
        } catch (NullPointerException e) {
            System.out.println("Nie ma takiego filmu");
        }
    }

    public void printMovies() {
        ConsolePrinter.printMovies(cinema.getMovies());
    }
    Movie findMovieByTitle(String title) {
        return cinema.movies.get(title);
    }

        public void changeMoviePrice () {
            logger.info("Podaj tytuł filmu");
            String title = sc.nextLine();
            try {
                Movie movie = findMovieByTitle(title);
                if (movie != null) {
                    logger.info("Podaj nową cenę: ");
                    movie.setPrice(sc.nextInt());
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

        public void deleteMovie() {
            try {
                logger.info("Podaj tytuł filmu, który chcesz usunąć: ");
                Movie movie = findMovieByTitle(sc.nextLine());
                if (cinemaController.removeMovie(movie))
                    logger.info("Usunięto film");
                else
                    logger.warn("Brak wskazanego filmu");
            } catch (InputMismatchException e) {
                logger.error("Nie udało się usunąć filmu, niepoprawne dane");
            }
        }

    }

