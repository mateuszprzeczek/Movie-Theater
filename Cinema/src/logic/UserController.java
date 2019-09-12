package logic;

import model.Ticket;
import model.User;
import org.apache.log4j.Logger;

import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;

public class UserController {
    private Logger logger = Logger.getLogger(BookingController.class);
    private Scanner sc = new Scanner(System.in);
    private MovieController movieController;

    public UserController(MovieController movieController) {
        this.movieController = movieController;
    }
    public void addUser() {
        try {
            User user = createUser();
            movieController.cinema.getUsers()
                    .put(user.getLastName(), user);
        } catch (InputMismatchException e) {
            logger.warn("Niepoprawne dane!");
        }
    }
    private User createUser(){
        logger.info("Podaj imię");
        String firstName = sc.nextLine();
        logger.info("Podaj nazwisko");
        String lastName = sc.nextLine();
        logger.info("Rejestracja "+ firstName + " " + lastName + " zakończona powodzeniem.");
        return new User(firstName, lastName);
    }

    User findUser(String lastName) throws NullPointerException {
        try{
            movieController.cinema.users.get(lastName);
        }catch (NullPointerException e){
            System.out.println("Nie ma takiego użytkownika");
        }
        return movieController.cinema.users.get(lastName);
    }
    Optional<Ticket> findUserTicketsByName(String lastName) {
        return Optional.ofNullable(movieController.cinema.tickets.get(lastName));
    }

}
