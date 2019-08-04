package model;

import java.io.Serializable;
import java.time.LocalTime;

public class Movie implements Serializable{
    private String title;
    private int length;
    private LocalTime playingHour;
    private double price;

    public Movie(String title, int length, LocalTime playingHour, double price) {
        this.title = title;
        this.length = length;
        this.playingHour = playingHour;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public LocalTime getPlayingHour() {
        return playingHour;
    }

    public void setPlayingHours(LocalTime playingHour) {
        this.playingHour = playingHour;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return
                "Tytuł: " + title + '\'' +
                ", Czas trwania " + length + " minut" +
                ", Godziny seansów " + playingHour +
                ", Cena " + price + " zł" + "\n";
    }
}
