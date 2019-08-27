package logic;

import exception.DataImportException;
import exception.InvalidDataException;
import io.file.ConsolePrinter;
import io.file.DataReader;
import io.file.FileManager;
import io.file.SerializableFileManager;
import model.*;
import org.apache.log4j.Logger;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class TicketController {
    private Logger logger = Logger.getLogger(TicketController.class);
    private Scanner sc = new Scanner(System.in);
    private Cinema cinema;

    public TicketController() {
        FileManager fileManager = new SerializableFileManager();
        try {
            cinema = fileManager.importData();
            logger.info("Zaimplementowane dane z pliku: ");
        } catch (DataImportException | InvalidDataException e) {
            logger.info(e.getMessage());
            logger.info("Zainicjowano nową bazę.");
            cinema = new Cinema();
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
        System.out.println("Dodano rezerwację: " + ticket.toString() + "\n");
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
        System.out.println("Podaj nazwisko");
        String lastName = sc.nextLine();
        try {
            CinemaUser user = cinema.findUser(lastName);
            if (user != null){
                user.addTicketToList(createTicket());
            }else {
                logger.info("Użytkownik o podanym nazwisku nie istnieje");
                logger.info("Jeśli jesteś nowym użytkownikiem, prosimy o rejestrację");
            }
        } catch (NullPointerException e) {
            logger.info("Nie posiadamy takiego filmu w repertuarze");
        }
    }
    public void printUserTickets() {
        logger.info("Podaj Nazwisko");
        String lastName = sc.nextLine();
        String notFoundMessage = "Nie odnaleziono uzytkownika o podanym nazwisku";
        cinema.findUserByName(lastName)
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
        ConsolePrinter.printUsers(cinema.getUsers());
    }


    private Movie findMovie() {
        Movie movie = null;
        boolean ok = false;
        while (!ok) {
            try {
                logger.info("Podaj tytuł filmu, który chcesz zarezerwować");
                movie = cinema.findMovieByTitle(sc.nextLine());
                if (movie!=null)
                    ok = true;
                else {
                    logger.info("W naszym repertuarze nie ma filmu o podanym tytule, spróbuj jeszcze raz"
                            + "\n" + "Dostępne Filmy:" + "\n");
                    ConsolePrinter.printMovies(cinema.getMovies());
                }
            } catch (NullPointerException e) {
                logger.error(
                        "Nie posiadamy takiego filmu w naszym repertuarze, podaj ponownie", e);
                ConsolePrinter.printMovies(cinema.getMovies());
            }
        }return movie;
    }
}
