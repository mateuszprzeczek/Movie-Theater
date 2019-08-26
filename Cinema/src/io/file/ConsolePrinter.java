package io.file;

import model.Movie;
import model.User;
import org.apache.log4j.Logger;

import java.util.List;

public class ConsolePrinter {
   private static Logger logger = Logger.getLogger(ConsolePrinter.class);

        public static void printMovies(List<Movie> movies){
            if (movies.size() == 0){
                logger.info("Brak filmów");
            }else {
                movies.stream().forEach(System.out::println);
            }
        }
        public static void printUsers(List<User> users){
            if (users.size() == 0){
                logger.info("Brak użytkowników");
            }else {
                users.stream().forEach(System.out::println);
            }
        }
        /*public void printTickets(List<Ticket> tickets){
            long count = 0L;
            for (Ticket ticket : tickets) {
                    String toString = ticket.toString();
                    printLine(toString);
                    count++;
            }
            if (count == 0){
                System.out.println("Brak użytkowników.");
            }
        }
        public void printUser(User user){
            user.toString();
            printTickets(user.getTicket());
            }*/

}
