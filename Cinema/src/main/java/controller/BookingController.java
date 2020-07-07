package controller;

import builders.TicketBuilder;
import helpers.ConsolePrinter;
import model.*;
import org.apache.log4j.Logger;

import java.util.*;

public class BookingController {
    private final Logger logger = Logger.getLogger(BookingController.class);
    private final Scanner sc = new Scanner(System.in);
    private final MovieController movieController;
    private final UserController userController;

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
                CinemaUser newCinemaUser = new CinemaUser(user.getFirstName(), user.getLastName(), tickets);
                movieController.cinema.getCinemaUserMap()
                        .put(newCinemaUser.getLastName(), createTicket(newCinemaUser));
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
        Seance seance = movieController.cinema.seancesRepository.get(movie.getTitle());

        //choosePlayingHour(selectedMovie);

        System.out.println("poidaj nr rzedu");
        int rowNumber = sc.nextInt(10);

        System.out.println("podaj nr siedzenia");
        int seatNumber = sc.nextInt(17);
        Map<Integer, Integer> choosenSeat = new HashMap<>();
        choosenSeat.put(rowNumber, seatNumber);
        CinemaHall cinemaHall = new CinemaHall.Builder(seance.getCinemaHall().getNumber()).audience(choosenSeat).build();
        Seance seanceOnSelectedMovie = new Seance.Builder()
                .setMovie(movie)
                .setDateTimeOfSeance(seance.getTimeOfSeance())
                .setCinemaHall(cinemaHall)
                .setPrice(seance.getPrice())
                .build();

//        CinemaHall cinemaHall = new CinemaHall();
//            cinemaHall.setNumber(movie.getCinemaHallNumber());
//            cinemaHall.setPlayingHour(movie.getMovieDisplayPerDay());
//            cinemaHall.setAudience(makeAudience(cinemaHall.getAudience(),rowNumber, seatNumber ));

            Ticket ticket = TicketBuilder.newInstance()
                    .setSeance(seanceOnSelectedMovie)
                    .setRowNumber(rowNumber)
                    .setSeatNumber(seatNumber)
                    .build();
            List<Ticket> ticketList = owner.getUserTickets();
            ticketList.add(ticket);
            owner.setUserTickets(ticketList);
        logger.info("Dodano rezerwację: " + ticket.toString() + "\n");
        return owner;
    }
    public static int[][] makeAudience(int [][]audience, int colNmb, int rowNmb) {
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 16; ++j) {
                audience[i][j] = j;
                if (i == colNmb-1 && j == rowNmb-1){
                    audience[i][j] = 0;
                }
            }
        }return audience;
    }

//    private void choosePlayingHour(Movie movie) {
//        boolean ok = false;
//        while (!ok) {
//            try {
//                logger.info("Dostępne godziny");
//                Seance availableTimes = movieController.cinema.seancesRepository.get(movie.getTitle());
//                logger.info(availableTimes.getTimeOfSeance().toString());
////                List<LocalTime> selectedTime = new ArrayList<>();
////                LocalTime readPlayingHour = MovieTimeOfSeanceHelper.createTimeOfSeance();
////                if (availableTimes.getTimeOfSeance().equals(readPlayingHour)){
////                    selectedTime.add(readPlayingHour);
////                    movie = Movie.Builder.newInstance().setMovieDisplayPerDay(selectedTime).build();
//
//                    ok = true;
//                } else {
//                    logger.info("Nie wyświetlamy tego filmu o podanej godzinie");
//                }
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//        }
//    }

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
        boolean movieBooked = false;
        while (!movieBooked) {
            try {
                logger.info("Podaj tytuł filmu, który chcesz zarezerwować");
                ConsolePrinter.printMovies(movieController.cinema.getMovies());
                movie = movieController.findMovieByTitle();

                if (movie!=null)
                    movieBooked = true;

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
