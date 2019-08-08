package model;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable {
    private String firstName;
    private String lastName;
    private List<Ticket> tickets;

    public User(String firstName, String lastName,List <Ticket> tickets) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.tickets = tickets;

    }
    public void addTicketToList(Ticket ticket){
        tickets.add(ticket);
    }

    public String getLastName() {
        return lastName;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    @Override
    public String toString() {
        return "Klient " + firstName + " " + lastName + "\n" +
                " rezerwacja biletu na " + tickets.toString();
    }

}
