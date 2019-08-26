package model;

import java.util.List;
import java.util.Objects;

public class CinemaUser extends User {
    private List<Ticket> tickets;

    public CinemaUser(String firstName, String lastName, List<Ticket>tickets){
        super(firstName, lastName);
        this.tickets = tickets;
    }
    public void addTicketToList(Ticket ticket){
        tickets.add(ticket);
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    @Override
    public String toString() {
        return super.toString()+ " rezerwacja biletu na " + tickets.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CinemaUser that = (CinemaUser) o;
        return Objects.equals(getTickets(), that.getTickets());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getTickets());
    }
}
