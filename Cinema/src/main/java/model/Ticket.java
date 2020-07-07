package model;

import java.io.Serializable;

public class Ticket implements Serializable {

    public Seance seance;
    public int rowNumber;
    public int seatNumber;


    public Ticket() {
    }

    @Override
    public String toString() {
        return ", Rezerwacja " + seance.toString() +
                ", Nr rzędu " + rowNumber +
                ", Nr miejsca " + seatNumber;
    }
}