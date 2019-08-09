package model;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.List;

public class Movie implements Serializable{
    private String title;
    private int length;
    private List<LocalTime> playingHours;
    private int cinemaHallNumber;
    private double price;

    public Movie(String title, int length, List<LocalTime> playingHours, int cinemaHallNumber, double price) {
        this.title = title;
        this.length = length;
        this.playingHours = playingHours;
        this.cinemaHallNumber = cinemaHallNumber;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void addPlayingHourToList(LocalTime localTime){
        playingHours.add(localTime);
    }

    int getCinemaHallNumber() {
        return cinemaHallNumber;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return
                "Tytuł: " + title +
                ", Czas trwania " + length + " minut" +
                ", Godziny seansów " + playingHours.toString() +
                ", Cena " + price + " zł" ;
    }

}
