package builder;

import model.Movie;
import model.Ticket;
import model.User;
import org.apache.log4j.Logger;

import java.util.Random;
import java.util.Scanner;

public class TicketBuilder {
    private static Logger logger = Logger.getLogger(MovieBuilder.class);
    private static Scanner sc = new Scanner(System.in);
    private  User owner;
    private  Movie movie;
    private  int rowNumber;
    private  int seatNumber;


        public static TicketBuilder newInstance() {
            return new TicketBuilder();
        }

        private TicketBuilder() {
        }

        public TicketBuilder setOwner(User owner) {
            this.owner = owner;
            return this;
        }
        public TicketBuilder setMovie(Movie movie){
            this.movie = movie;
            return this;
        }
        public TicketBuilder setRowNumber(int rowNumber){
            this.rowNumber = rowNumber;
            return this;
        }
        public TicketBuilder setSeatNumber(int seatNumber){
            this.seatNumber = seatNumber;
            return this;
        }
        public Ticket build(){
            Ticket ticket = new Ticket();
            ticket.owner = this.owner;
            ticket.movie = this.movie;
            ticket.rowNumber = this.rowNumber;
            ticket.seatNumber = this.seatNumber;
            return ticket;
        }


    public static Ticket addTicket(User user, Movie movie){
        Random random = new Random();
        int rowNumber = random.nextInt(10);
        int seatNumber = random.nextInt(17);
        return TicketBuilder.newInstance()
                .setOwner(user)
                .setMovie(movie)
                .setRowNumber(rowNumber)
                .setSeatNumber(seatNumber)
                .build();
    }
}
