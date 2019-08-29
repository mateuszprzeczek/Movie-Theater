package logic;

import io.file.ConsolePrinter;
import io.file.DataReader;
import model.*;
import org.apache.log4j.Logger;
import java.time.LocalTime;
import java.util.*;

public class TicketController {
    private Logger logger = Logger.getLogger(TicketController.class);
    private Scanner sc = new Scanner(System.in);
    private MovieController movieController;

    public TicketController(MovieController movieController){
        this.movieController = movieController;
    }
    public void addUser() {
        try {
            CinemaUser user = createUser();
            movieController.cinema.addUser(user);
        } catch (InputMismatchException e) {
            logger.warn("Niepoprawne dane!");
        }
    }

    public Ticket createTicket() {
        Movie movie = findMovie();
        Movie userMovie = new Movie(movie.getTitle(), movie.getLength(),
                movie.getPlayingHours(), movie.getCinemaHallNumber(), movie.getPrice());
        choosePlayingHour(userMovie);
        Random random = new Random();
        int rowNumber = random.nextInt(10);
        int seatNumber = random.nextInt(17);
        Ticket ticket = new Ticket(userMovie, rowNumber, seatNumber);
        logger.info("Dodano rezerwację: " + ticket.toString() + "\n");
        return ticket;
    }
    public void choosePlayingHour(Movie movie) {
        boolean ok = false;
        while (!ok) {
            try {
                logger.info("Dostępne godziny");
                logger.info(movie.getPlayingHours().toString());
                List<LocalTime> selectedTime = new ArrayList<>();
                LocalTime readPlayingHour = DataReader.createTimeOfSeance();
                if (movie.getPlayingHours().contains(readPlayingHour)){
                    selectedTime.add(readPlayingHour);
                    movie.setPlayingHours(selectedTime);
                    ok = true;
                } else {
                    logger.info("Nie wyświetlamy tego filmu o podanej godzinie");
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    public void addTicket() {
        logger.info("Podaj nazwisko");
        String lastName = sc.nextLine();
        try {
            CinemaUser user = movieController.cinema.findUser(lastName);
            if (user != null){
                user.addTicketToList(createTicket());
            }else {
                logger.info("Użytkownik o podanym nazwisku nie istnieje");
                logger.info("Jeśli jesteś nowym użytkownikiem, prosimy o rejestrację");
            }
        } catch (NullPointerException e) {
            logger.warn("Nie posiadamy takiego filmu w repertuarze");
        }
    }
    public void printUserTickets() {
        logger.info("Podaj Nazwisko");
        String lastName = sc.nextLine();
        String notFoundMessage = "Nie odnaleziono uzytkownika o podanym nazwisku";
        movieController.cinema.findUserByName(lastName)
                .map(User::toString)
                .ifPresentOrElse(System.out::println, () -> logger.error(notFoundMessage));
    }
    public CinemaUser createUser(){
        logger.info("Podaj imię");
        String firstName = sc.nextLine();
        logger.info("Podaj nazwisko");
        String lastName = sc.nextLine();
        logger.info("Rejestracja "+ firstName + " " + lastName + " zakończona powodzeniem.");
        List<Ticket> listOfTickets = new ArrayList<>();
        Ticket ticket = createTicket();
        listOfTickets.add(ticket);
        return new CinemaUser(firstName, lastName, listOfTickets);
    }
    public void printUsers() {
        ConsolePrinter.printUsers(movieController.cinema.getUsers());
    }


    private Movie findMovie() {
        Movie movie = null;
        boolean ok = false;
        while (!ok) {
            try {
                logger.info("Podaj tytuł filmu, który chcesz zarezerwować");
                movie = movieController.cinema.findMovieByTitle(sc.nextLine());
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
