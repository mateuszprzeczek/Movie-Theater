package model;

import java.io.Serializable;

public class Ticket implements Serializable {
    public User owner;
    public Movie movie;
    public int rowNumber;
    public int seatNumber;

    public Ticket(User owner, Movie movie, int rowNumber, int seatNumber) {
        this.owner = owner;
        this.movie = movie;
        this.rowNumber = rowNumber;
        this.seatNumber = seatNumber;
    }

    public Ticket() {
    }

    public User getOwner() {
        return owner;
    }

    public Movie getMovie() {
        return movie;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public void setRowNumber(int rowNumber) {
        this.rowNumber = rowNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "owner=" + owner.toString() +
                ", movie=" + movie.toString() +
                ", rowNumber=" + rowNumber +
                ", seatNumber=" + seatNumber +
                '}';
    }
}