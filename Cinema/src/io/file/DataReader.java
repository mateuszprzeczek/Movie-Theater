package io.file;

import model.Movie;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.SimpleFormatter;

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
        printer.printLine("Ile razy w ciagu dnia bedzie wyświetlany film?");
        int howMany = getInt();
        List<LocalTime> localTime = timesOfSeance(howMany);
        printer.printLine("Podaj nr sali kinowej: ");
        int cinemaHallNumber = getInt();
        System.out.println("Podaj cenę ");
        double price = getInt();

        return new Movie(title, length, localTime, cinemaHallNumber, price);
    }
    private List<LocalTime> timesOfSeance (int howMany){
        List<LocalTime> localTimes = new ArrayList<>();
        for (int i = 0; i < howMany; i++){
            printer.printLine("Seans nr " + (i + 1));
            LocalTime localTime = readAndCreateTimeOfSeance();
            localTimes.add(localTime);
        }
        return localTimes;
    }

    public LocalTime readAndCreateTimeOfSeance(){
            System.out.println("Podaj godzinę seansu:");
            int hourOfSeance = getInt();
            System.out.println("Podaj, w której minucie zaczyna się seans: ");
            int minutes = getInt();

             LocalTime localTime = LocalTime.of(hourOfSeance, minutes);

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