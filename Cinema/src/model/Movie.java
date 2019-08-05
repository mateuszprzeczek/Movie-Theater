package model;

import java.io.Serializable;
import java.time.LocalTime;

public class Movie implements Serializable{
    private String title;
    private int length;
    private LocalTime playingHour;
    private int cinemaHallNumber;
    private double price;

    public Movie(String title, int length, LocalTime playingHour, int cinemaHallNumber, double price) {
        this.title = title;
        this.length = length;
        this.playingHour = playingHour;
        this.cinemaHallNumber = cinemaHallNumber;
        this.price = price;
    }

    String getTitle() {
        return title;
    }

    int getCinemaHallNumber() {
        return cinemaHallNumber;
    }

    @Override
    public String toString() {
        return
                "Tytuł: " + title +
                ", Czas trwania " + length + " minut" +
                ", Godziny seansów " + playingHour +
                ", Cena " + price + " zł" ;
    }

}
