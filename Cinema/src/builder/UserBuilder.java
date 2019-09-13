package builder;

import model.User;
import org.apache.log4j.Logger;

import java.util.Scanner;

public class UserBuilder {
    private static Logger logger = Logger.getLogger(MovieBuilder.class);
    private static Scanner sc = new Scanner(System.in);

    public static User userRegistration(){
        logger.info("Podaj imię");
        String firstName = sc.nextLine();
        logger.info("Podaj nazwisko");
        String lastName = sc.nextLine();
        logger.info("Rejestracja "+ firstName + " " + lastName + " zakończona powodzeniem.");
        return new User(firstName, lastName);
    }
}
