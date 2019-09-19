package model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Cinema implements Serializable {

    public Map<String, Movie> movies = new HashMap<>();
    public Map<String, User> users = new HashMap<>();
    public Map<String, Ticket> tickets = new HashMap<>();


    public Cinema() {
    }

    public Map<String, Movie> getMovies() {
        return movies;
    }

    public Map<String, User> getUsers() {
        return users;
    }

    public Map<String, Ticket> getTickets() {
        return tickets;
    }

    @Override
    public String toString() {
        return "Cinema{" +
                "movies=" + movies +
                ", users=" + users +
                ", tickets=" + tickets +
                '}';
    }
}


