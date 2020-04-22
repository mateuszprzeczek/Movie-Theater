package app;

import logic.BookingController;
import helpers.ImportExport;
import logic.MovieController;
import logic.UserController;
import logic.ViewController;

public class CinemaApp {
    public static void main(String[] args)  {
        final String APP_NAME = "Kino v5.0";
        System.out.println(APP_NAME);
        MovieController movieController = new MovieController();
        ImportExport importExport = new ImportExport(movieController);

        importExport.importData();
        UserController userController = new UserController(movieController);
        BookingController bookingController = new BookingController(movieController, userController);

        ViewController applicationDisplay = new ViewController(bookingController, movieController, importExport, userController);
        applicationDisplay.mainMenu();
    }

}
