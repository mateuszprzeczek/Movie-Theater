package model;

import java.io.Serializable;

public class Ticket implements Serializable {

    public Movie movie;
    public int rowNumber;
    public int seatNumber;

    public Ticket() {
    }

    @Override
    public String toString() {
        return ", Rezerwacja " + movie.toString() +
                ", Nr rzędu " + rowNumber +
                ", Nr miejsca " + seatNumber;
    }
}