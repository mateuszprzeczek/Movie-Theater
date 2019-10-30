package logic;

import builder.TicketBuilder;
import helpers.MovieTimeOfSeanceHelper;
import io.file.ConsolePrinter;
import model.CinemaUser;
import model.Movie;
import model.Ticket;
import model.User;
import org.apache.log4j.Logger;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

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
        try { CinemaUser cinemaUser = userController.findCinemaUser(lastName);
            User user = userController.findUser(lastName);
            if (cinemaUser != null){
                movieController.cinema.getCinemaUserMap()
                        .put(cinemaUser.getLastName(), createTicket(cinemaUser));
            }
            else if (user != null){
                List<Ticket>tickets = new ArrayList<>();
                CinemaUser cinemaUser1 = new CinemaUser(user.getFirstName(), user.getLastName(), tickets);
                movieController.cinema.getCinemaUserMap()
                        .put(cinemaUser1.getLastName(), createTicket(cinemaUser1));
            }else {
                logger.info("Użytkownik o podanym nazwisku nie istnieje");
                logger.info("Jeśli jesteś nowym użytkownikiem, prosimy o rejestrację");
            }
        } catch (NullPointerException e) {
            logger.warn("Nie posiadamy takiego filmu w repertuarze");
        }
    }

    private CinemaUser createTicket(CinemaUser owner) {
        Movie movie = findMovie();
        Movie selectedMovie = Movie.Builder.newInstance()
                .setTitle(movie.getTitle())
                .setLength(movie.getLength())
                .setPlayingHours(movie.getPlayingHours())
                .setCinemaHallNumber(movie.getCinemaHallNumber())
                .setPrice(movie.getPrice())
                .build();
        choosePlayingHour(selectedMovie);
            Random random = new Random();
            int rowNumber = random.nextInt(10);
            int seatNumber = random.nextInt(17);
            Ticket ticket = TicketBuilder.newInstance()
                    .setMovie(movie)
                    .setRowNumber(rowNumber)
                    .setSeatNumber(seatNumber)
                    .build();
            List<Ticket> ticketList = owner.getUserTickets();
            ticketList.add(ticket);
            owner.setUserTickets(ticketList);
        logger.info("Dodano rezerwację: " + ticket.toString() + "\n");
        return owner;
    }

    private void choosePlayingHour(Movie movie) {
        boolean ok = false;
        while (!ok) {
            try {
                logger.info("Dostępne godziny");
                logger.info(movie.getPlayingHours().toString());
                List<LocalTime> selectedTime = new ArrayList<>();
                LocalTime readPlayingHour = MovieTimeOfSeanceHelper.createTimeOfSeance();
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
                .map(model.CinemaUser::toString)
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
