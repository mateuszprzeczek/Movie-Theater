package model;

import java.io.Serializable;

public class Ticket implements Serializable {
    public User owner;
    public Movie movie;
    public int rowNumber;
    public int seatNumber;

    public Ticket() {
    }

    @Override
    public String toString() {
        return "Klient " + owner.toString() +
                ", Rezerwacja " + movie.toString() +
                ", Nr rzędu " + rowNumber +
                ", Nr miejsca " + seatNumber;
    }
}