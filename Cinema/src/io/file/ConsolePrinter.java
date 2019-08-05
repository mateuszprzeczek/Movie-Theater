package io.file;

import model.Movie;
import model.User;
import java.util.List;

public class ConsolePrinter {

        public void printMovies(List<Movie> movies){
            long count = 0L;
            for (Movie movie : movies) {
                    String toString = movie.toString();
                    printLine(toString);
                    count++;
            }
            if (count == 0){
                System.out.println("Brak Filmów.");
            }
        }
        public void printUsers(List<User> users){
            long count = 0L;
            for (User user : users) {
                    String toString = user.toString();
                    printLine(toString);
                    count++;
            }
            if (count == 0){
                System.out.println("Brak użytkowników.");
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



        public void printLine(String text){
            System.out.println(text);
        }

}
