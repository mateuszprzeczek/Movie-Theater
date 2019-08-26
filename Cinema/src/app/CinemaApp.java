package app;

import logic.CinemaControl;
import logic.MovieController;
import logic.TicketController;

public class CinemaApp {
    public static void main(String[] args)  {
        MovieController movieController = new MovieController();
        TicketController ticketController = new TicketController();
        final String APP_NAME = "Kino v4.0";
        System.out.println(APP_NAME);
        CinemaControl cinemaControl = new CinemaControl(ticketController, movieController);
        cinemaControl.initialLoop();
    }

}
