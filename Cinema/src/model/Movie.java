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


    private Movie(){}

    public static class Builder{
        private String title;
        private int length;
        private List<LocalTime> playingHours;
        private int cinemaHallNumber;
        private double price;

        public static Builder newInstance(){
            return new Builder();
        }
        private Builder(){}

        public Builder setTitle(String title){
            this.title = title;
            return this;
        }
        public Builder setLength(int length){
            this.length = length;
            return this;
        }
        public Builder setPlayingHours(List<LocalTime> playingHours){
            this.playingHours = playingHours;
            return this;
        }
        public Builder setCinemaHallNumber(int cinemaHallNumber){
            this.cinemaHallNumber = cinemaHallNumber;
            return this;
        }
        public Builder setPrice(double price){
            this.price = price;
            return this;
        }
        public Movie build(){
            Movie movie = new Movie();
            movie.title = this.title;
            movie.length = this.length;
            movie.playingHours = this.playingHours;
            movie.cinemaHallNumber = this.cinemaHallNumber;
            movie.price = this.price;

            return movie;
        }
    }

    public String getTitle() {
        return title;
    }

    public int getLength() {
        return length;
    }

    public List<LocalTime> getPlayingHours() {
        return playingHours;
    }

    public int getCinemaHallNumber() {
        return cinemaHallNumber;
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
                this.title + ", Czas trwania " + this.length +
                        " minut" + ", Godziny seansów " + this.playingHours.toString() +
                        ", Cena " + this.price + " zł";
    }

}
