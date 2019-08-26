package app;

import logic.CinemaControl;
import logic.MovieController;
import logic.TicketController;
import model.Cinema;

public class CinemaApp {
    public static void main(String[] args)  {
        MovieController movieController = new MovieController(new Cinema());
        TicketController ticketController = new TicketController(new Cinema());
        final String APP_NAME = "Kino v4.0";
        System.out.println(APP_NAME);
        CinemaControl cinemaControl = new CinemaControl(ticketController, movieController);
        cinemaControl.initialLoop();
    }

}
