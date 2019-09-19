package logic;

import builder.TicketBuilder;
import helpers.MovieBuilderHelper;
import io.file.ConsolePrinter;
import model.Movie;
import model.Ticket;
import model.User;
import org.apache.log4j.Logger;

import java.time.LocalTime;
import java.util.*;

public class BookingController {
    private Logger logger = Logger.getLogger(BookingController.class);
    private Scanner sc = new Scanner(System.in);
    private MovieController movieController;
    private UserController userController;

    public BookingController(MovieController movieController, UserController userController){
        this.movieController = movieController;
        this.userController = userController;
    }

    void addTicket() {
        logger.info("Podaj nazwisko");
        String lastName = sc.nextLine();
        try {
            User user = userController.findUser(lastName);
            if (user != null){
                movieController.cinema.getTickets()
                        .put(user.getLastName(), createTicket(user));
            }else {
                logger.info("Użytkownik o podanym nazwisku nie istnieje");
                logger.info("Jeśli jesteś nowym użytkownikiem, prosimy o rejestrację");
            }
        } catch (NullPointerException e) {
            logger.warn("Nie posiadamy takiego filmu w repertuarze");
        }
    }

    private Ticket createTicket(User owner) {
        Movie movie = findMovie();
        Movie selectedMovie = Movie.Builder.newInstance()
                .setTitle(movie.getTitle())
                .setLength(movie.getLength())
                .setPlayingHours(movie.getPlayingHours())
                .setCinemaHallNumber(movie.getCinemaHallNumber())
                .setPrice(movie.getPrice())
                .build();
        choosePlayingHour(selectedMovie);
        Ticket ticket = TicketBuilder.addTicket(owner, selectedMovie);
        logger.info("Dodano rezerwację: " + ticket.toString() + "\n");
        return ticket;
    }

    private void choosePlayingHour(Movie movie) {
        boolean ok = false;
        while (!ok) {
            try {
                logger.info("Dostępne godziny");
                logger.info(movie.getPlayingHours().toString());
                List<LocalTime> selectedTime = new ArrayList<>();
                LocalTime readPlayingHour = MovieBuilderHelper.createTimeOfSeance();
                if (movie.getPlayingHours().contains(readPlayingHour)){
                    selectedTime.add(readPlayingHour);
                    movie = Movie.Builder.newInstance().setPlayingHours(selectedTime).build();
                    ok = true;
                } else {
                    logger.info("Nie wyświetlamy tego filmu o podanej godzinie");
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    void printUserTickets() {
        logger.info("Podaj Nazwisko");
        String lastName = sc.nextLine();
        String notFoundMessage = "Nie odnaleziono uzytkownika o podanym nazwisku";
        userController.findUserTicketsByName(lastName)
                .map(model.Ticket::toString)
                .ifPresentOrElse(System.out::println, () -> logger.error(notFoundMessage));
    }

    private Movie findMovie() {
        Movie movie = null;
        boolean ok = false;
        while (!ok) {
            try {
                logger.info("Podaj tytuł filmu, który chcesz zarezerwować");
                movie = movieController.findMovieByTitle(sc.nextLine());
                if (movie!=null)
                    ok = true;
                else {
                    logger.info("W naszym repertuarze nie ma filmu o podanym tytule, spróbuj jeszcze raz"
                            + "\n" + "Dostępne Filmy:" + "\n");
                    ConsolePrinter.printMovies(movieController.cinema.getMovies());
                }
            } catch (NullPointerException e) {
                logger.error(
                        "Nie posiadamy takiego filmu w naszym repertuarze, podaj ponownie", e);
                ConsolePrinter.printMovies(movieController.cinema.getMovies());
            }
        }return movie;
    }
}
