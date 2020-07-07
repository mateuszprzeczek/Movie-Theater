package app;

import controller.BookingController;
import data.ImportData;
import controller.MovieController;
import controller.UserController;
import controller.ViewController;

public class CinemaApp {
    public static void main(String[] args)  {
        final String APP_NAME = "Kino v5.0";
        System.out.println(APP_NAME);
        MovieController movieController = new MovieController();
        ImportData importExport = new ImportData(movieController);

        importExport.importData();
        UserController userController = new UserController(movieController);
        BookingController bookingController = new BookingController(movieController, userController);

        ViewController applicationDisplay = new ViewController(bookingController, movieController, importExport, userController);
        applicationDisplay.mainMenu();
    }

}
