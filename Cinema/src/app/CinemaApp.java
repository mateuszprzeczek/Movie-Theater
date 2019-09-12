package app;

import logic.BookingController;
import logic.CinemaController;
import logic.MovieController;
import logic.UserController;
import view.ApplicationDisplay;

public class CinemaApp {
    public static void main(String[] args)  {
        final String APP_NAME = "Kino v5.0";
        System.out.println(APP_NAME);
        MovieController movieController = new MovieController();
        CinemaController cinemaController = new CinemaController(movieController);

        cinemaController.importData();
        UserController userController = new UserController(movieController);
        BookingController bookingController = new BookingController(movieController, userController);

        ApplicationDisplay applicationDisplay = new ApplicationDisplay(bookingController, movieController, cinemaController, userController);
        applicationDisplay.mainMenu();
    }

}
