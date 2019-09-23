package app;

import logic.BookingController;
import io.file.ImportExport;
import logic.MovieController;
import logic.UserController;
import logic.ApplicationDisplay;

public class CinemaApp {
    public static void main(String[] args)  {
        final String APP_NAME = "Kino v5.0";
        System.out.println(APP_NAME);
        MovieController movieController = new MovieController();
        ImportExport importExport = new ImportExport(movieController);

        importExport.importData();
        UserController userController = new UserController(movieController);
        BookingController bookingController = new BookingController(movieController, userController);

        ApplicationDisplay applicationDisplay = new ApplicationDisplay(bookingController, movieController, importExport, userController);
        applicationDisplay.mainMenu();
    }

}
