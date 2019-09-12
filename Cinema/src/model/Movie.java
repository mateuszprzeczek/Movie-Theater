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
    public Movie(){}

    public void setTitle(String title) {
        this.title = title;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setCinemaHallNumber(int cinemaHallNumber) {
        this.cinemaHallNumber = cinemaHallNumber;
    }

    public String getTitle() {
        return title;
    }

    public int getCinemaHallNumber() {
        return cinemaHallNumber;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<LocalTime> getPlayingHours() {
        return playingHours;
    }

    public void setPlayingHours(List<LocalTime> playingHours) {
        this.playingHours = playingHours;
    }

    public int getLength() {
        return length;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return
                 title +
                ", Czas trwania " + length + " minut" +
                ", Godziny seansów " + playingHours.toString() +
                ", Cena " + price + " zł" ;
    }

}
