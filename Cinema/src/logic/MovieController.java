package logic;

import io.file.ConsolePrinter;
import io.file.DataReader;
import model.Cinema;
import model.Movie;
import org.apache.log4j.Logger;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MovieController {
    Logger logger = Logger.getLogger(MovieController.class);
    private Scanner sc = new Scanner(System.in);
    private Cinema cinema;


    public MovieController(Cinema cinema) {
        this.cinema = cinema;
    }

    public void addMovie() {
        try {
            Movie movie = DataReader.createMovie();
            cinema.addMovie(movie);
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
                movie.addPlayingHourToList(DataReader.createTimeOfSeance());
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
    public Movie findMovieByTitle(String title) {
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
            }
        }

        public void deleteMovie() {
            try {
                logger.info("Podaj tytuł filmu, który chcesz usunąć: ");
                Movie movie = findMovieByTitle(sc.nextLine());
                if (cinema.removeMovie(movie))
                    logger.info("Usunięto film");
                else
                    logger.warn("Brak wskazanego filmu");
            } catch (InputMismatchException e) {
                logger.error("Nie udało się utworzyć filmu, niepoprawne dane");
            }
        }
    }

