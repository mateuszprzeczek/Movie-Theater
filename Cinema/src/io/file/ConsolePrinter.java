package io.file;

import model.CinemaUser;
import model.Movie;
import model.Seance;
import model.User;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ConsolePrinter {
   private static Logger logger = Logger.getLogger(ConsolePrinter.class);

        public static void printMovies(Map<String, Movie> movies){
            if (movies.size() == 0){
                logger.info("Brak filmów");
            }else {
                List<Movie> movieList = new ArrayList<>(movies.values());

                movieList.forEach(System.out::println);
            }
        }
    public static void printSeances(Map<String, Seance> seances){
        if (seances.size() == 0){
            logger.info("Brak Seansów");
        }else {
            List<Seance> movieList = new ArrayList<>(seances.values());

            movieList.forEach(System.out::println);
        }
    }
        public static void printUsers(Map<String, User> users){
            if (users.size() == 0){
                logger.info("Brak użytkowników");
            }else {
                List<User> userList = new ArrayList<>(users.values());
                for (User user : userList) {
                    System.out.println(user);
                }
            }
        }
        public static void printTickets(Map<String, CinemaUser> tickets){
            if (tickets.size() == 0){
                logger.info("Brak zakupionych biletów");
            }else {
                List<CinemaUser> ticketList = new ArrayList<>(tickets.values());
                ticketList.forEach(System.out::println);
            }
        }
    public static void printAudience(int [][]audience) {
        System.out.println("            **************************** ekran ");
        for (int i = 0; i < audience.length; i++) {
            System.out.println();
            System.out.print("Rząd nr " + (i+1) + ": ");
            for (int j = 0; j < 16; ++j) {

                System.out.print(audience[i][j] + " ");
            }
        }
    }
}
