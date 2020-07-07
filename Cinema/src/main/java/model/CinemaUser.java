package model;

import java.io.Serializable;
import java.util.List;

public class CinemaUser extends User implements Serializable {
    public List<Ticket> userTickets;

    public CinemaUser(String firstName, String lastName, List<Ticket> userTickets) {
        super(firstName, lastName);
        this.userTickets = userTickets;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Ticket> getUserTickets() {
        return userTickets;
    }

    public void setUserTickets(List<Ticket> userTickets) {
        this.userTickets = userTickets;
    }

    @Override
    public String toString() {
        return "Klient" + firstName + " " + lastName + " Bilet na " + userTickets.toString() ;
    }
}
