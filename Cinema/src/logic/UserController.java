package logic;

import builder.UserBuilder;
import model.Ticket;
import model.User;
import org.apache.log4j.Logger;

import java.util.InputMismatchException;
import java.util.Optional;

public class UserController {
    private Logger logger = Logger.getLogger(BookingController.class);
    private MovieController movieController;

    public UserController(MovieController movieController) {
        this.movieController = movieController;
    }
    public void addUser() {
        try {
            User user = UserBuilder.userRegistration();
            movieController.cinema.getUsers()
                    .put(user.getLastName(), user);
        } catch (InputMismatchException e) {
            logger.warn("Niepoprawne dane!");
        }
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
