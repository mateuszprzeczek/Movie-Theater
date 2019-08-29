package app;

import logic.CinemaController;
import logic.MovieController;
import logic.TicketController;
import model.Cinema;

public class CinemaApp {
    public static void main(String[] args)  {
        final String APP_NAME = "Kino v5.0";
        System.out.println(APP_NAME);

        MovieController movieController = new MovieController();
        TicketController ticketController = new TicketController(movieController);
        Cinema cinema = movieController.importData();

        CinemaController cinemaControl = new CinemaController(ticketController, movieController, cinema);
        cinemaControl.initialLoop();
    }

}
