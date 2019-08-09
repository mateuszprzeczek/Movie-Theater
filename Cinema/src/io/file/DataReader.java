package io.file;

import model.*;
import java.io.Serializable;
import java.time.DateTimeException;
import java.time.LocalTime;
import java.util.*;


public class DataReader implements Serializable {
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

    public LocalTime readAndCreateTimeOfSeance() throws DateTimeException {
        LocalTime localTime = null;
        boolean ok = false;
        while (!ok){
            try {
                System.out.println("Podaj godzinę seansu:");
                int hourOfSeance = getInt();
                System.out.println("Podaj, w której minucie zaczyna się seans: ");
                int minutes = getInt();
                if (changeTimeFormatIsCorrect(hourOfSeance, minutes)) {
                    localTime = LocalTime.of(hourOfSeance, minutes);
                    ok = true;
                }else {
                    printer.printLine("Niepoprawny format, spróbuj jeszcze raz");
                }
            }catch (DateTimeException e){
                System.err.println(e);
            }
        }
            return localTime;
    }
    private boolean changeTimeFormatIsCorrect(int hour, int minutes){
        if (hour > 23 || hour < 0 || minutes > 60 || minutes < 0){
            return false;
        }else {
            return true;
        }
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