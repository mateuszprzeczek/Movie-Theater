package model;

import builders.MonthsBuilder;

import java.time.LocalTime;

public class Seance extends MonthsBuilder {
    private LocalTime timeOfSeance;
    private CinemaHall cinemaHall;
    private Movie movie;
    private double price;

    public Seance(Builder builder) {
        super(builder);
        timeOfSeance = builder.timeOfSeance;
        cinemaHall = builder.cinemaHall;
        movie = builder.movie;
        price = builder.price;
    }

    public static class Builder extends MonthsBuilder.Builder<Builder> {
            private LocalTime timeOfSeance;
            private CinemaHall cinemaHall;
            private Movie movie;
            private double price;


//            public static Movie.Builder newInstance(){
//                return new Movie.Builder();
//            }
public Builder(){}

            public Builder setDateTimeOfSeance(LocalTime timeOfSeance){
                this.timeOfSeance = timeOfSeance;
                return this;
            }
            public Builder setCinemaHall(CinemaHall cinemaHall){
                this.cinemaHall = cinemaHall;
                return this;
            }

            public Builder setMovie(Movie movie){
                this.movie = movie;
                return this;
            }
            public Builder setPrice(double price){
                this.price = price;
                return this;
            }
            public Seance build(){
                return new Seance(this);
            }

            @Override
            protected Builder self() {
                return this;
            }
        }

    public Movie getMovie() {
        return movie;
    }

    public LocalTime getTimeOfSeance() {
        return timeOfSeance;
    }

    public void setTimeOfSeance(LocalTime timeOfSeance) {
        this.timeOfSeance = timeOfSeance;
    }

    public CinemaHall getCinemaHall() {
        return cinemaHall;
    }

    public void setCinemaHall(CinemaHall cinemaHall) {
        this.cinemaHall = cinemaHall;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Seans na film " + movie.toString() +
                "czas =" + timeOfSeance +
                ", Sala nr " + cinemaHall.toString() +
                ", price=" + price +
                '}';
    }

}



