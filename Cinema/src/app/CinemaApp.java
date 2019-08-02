package app;

import data.Cinema;

public class CinemaApp {
    public static void main(String[] args) {
        final String APP_NAME = "Kino v1.0";
        System.out.println(APP_NAME);
        CinemaControl cinemaControl = new CinemaControl();
        cinemaControl.initialLoop();
    }

}
