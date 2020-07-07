package controller;

import model.CinemaUser;
import model.User;
import org.apache.log4j.Logger;

import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;

public class UserController {
    private final Logger logger = Logger.getLogger(BookingController.class);
    private final MovieController movieController;
    private final Scanner sc = new Scanner(System.in);

    public UserController(MovieController movieController) {
        this.movieController = movieController;
    }
    void addUser() {
        try {
            User user = userRegistration();
            movieController.cinema.getUsers()
                    .put(user.getLastName(), user);
        } catch (InputMismatchException e) {
            logger.warn("Niepoprawne dane!");
        }
    }
    private User userRegistration(){
        logger.info("Podaj imię");
        String firstName = sc.nextLine();
        logger.info("Podaj nazwisko");
        String lastName = sc.nextLine();
        logger.info("Rejestracja "+ firstName + " " + lastName + " zakończona powodzeniem.");
        return new User(firstName, lastName);
    }

    User findUser(String lastName) {
        try{if
        (movieController.cinema.users.get(lastName) == null){
            System.out.println();
        }
        }catch (NullPointerException e){
            System.out.println();
        }
        return movieController.cinema.users.get(lastName);
    }
    CinemaUser findCinemaUser(String lastName){
        try{if (movieController.cinema.cinemaUserMap.get(lastName) == null){
            System.out.println();
        }
        }catch (NullPointerException e){
            System.out.println();
        }
        return movieController.cinema.cinemaUserMap.get(lastName);
    }
    Optional<CinemaUser> findUserTicketsByName(String lastName) {
        return Optional.ofNullable(movieController.cinema.cinemaUserMap.get(lastName));
    }

}
