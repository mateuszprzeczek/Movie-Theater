package io.file;

import model.Movie;
import java.time.LocalTime;
import java.util.Scanner;

public class DataReader {
    private Scanner sc = new Scanner(System.in);
    private ConsolePrinter printer;

    public DataReader(ConsolePrinter printer) {
        this.printer = printer;
    }


    public Movie readAndCreateMovie() {
        System.out.println("Tytuł filmu: ");
        String title = sc.nextLine();
        System.out.println("Długość filmu w minutach: ");
        int length = getInt();
        LocalTime localTime = getTimeOfSeance();
        printer.printLine("Podaj nr sali kinowej: ");
        int cinemaHallNumber = getInt();
        System.out.println("Podaj cenę ");
        double price = getInt();

        return new Movie(title, length, localTime, cinemaHallNumber, price);
    }

    private LocalTime getTimeOfSeance(){
        Scanner sc = new Scanner(System.in);
            System.out.println("Podaj godzinę seansu:");
            int hourOfSeance = sc.nextInt();
            sc.nextLine();
            System.out.println("Podaj minuty: ");
            int minutes = sc.nextInt();
            sc.nextLine();
        LocalTime localTime;
        localTime = LocalTime.of(hourOfSeance, minutes);

        return localTime;
}

    public String getString() {
        return sc.nextLine();
    }

    public int getInt() {
        try {
            return sc.nextInt();
        } finally {
            sc.nextLine();
        }
    }
    public void close(){
        sc.close();
    }
}