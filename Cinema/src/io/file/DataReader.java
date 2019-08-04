package io.file;

import logic.CinemaControl;
import logic.OrderController;
import model.Cinema;
import model.Movie;
import model.User;


import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

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
   public User createUser(){
        printer.printLine("Podaj imię");
        String firstName = sc.nextLine();
        printer.printLine("Podaj nazwisko");
        String lastName = sc.nextLine();
        printer.printLine("Jakie filmy rezerwujesz? Wymień po przecinku:");
        OrderController order = takeOrder();
        return new User(firstName, lastName, order);

   }
    public OrderController takeOrder(){
        List<String> order = orderMovieByTitle();
        List<Movie> movies = Cinema.bookMovie(order);
        return new OrderController(movies);
    }

    public List<String> orderMovieByTitle(){
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();
        String [] split = line.replaceAll(" ", "").split(",");
        return Arrays.stream(split)
                .collect(Collectors.toList());
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