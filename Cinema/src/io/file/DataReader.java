package io.file;

import data.Movie;


import java.time.LocalTime;
import java.util.Scanner;

public class DataReader {
    private Scanner sc = new Scanner(System.in);
    public ConsolePrinter printer;

    public DataReader(ConsolePrinter printer) {
        this.printer = printer;
    }

    public Movie readAndCreateMovie() {
        System.out.println("Tytuł filmu: ");
        String title = sc.nextLine();
        System.out.println("Długość filmu w minutach: ");
        int length = getInt();
        LocalTime localTime = getTimeOfSeance();
        System.out.println("Podaj cenę ");
        double price = getInt();

        return new Movie(title, length, localTime, price);
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
   // public Seance readAndCreateSeance(){

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