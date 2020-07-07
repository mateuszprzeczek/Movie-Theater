package model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Cinema implements Serializable {

    public Map<String, Movie> movies = new HashMap<>();
    public Map<String, Ticket> tickets = new HashMap<>();
    public Map<String, User> users = new HashMap<>();
    public Map<String, CinemaUser> cinemaUserMap = new HashMap<>();
    public Map<String, Seance> seancesRepository = new HashMap<>();


    public Cinema() {
    }

    public Map<String, Movie> getMovies() {
        return movies;
    }
    public Map<String, Seance> getSeances() {
        return seancesRepository;
    }


    public Map<String, Ticket> getTickets() {
        return tickets;
    }

    public Map<String, CinemaUser> getCinemaUserMap() {
        return cinemaUserMap;
    }

    public Map<String, User> getUsers() {
        return users;
    }

    @Override
    public String toString() {
        return   movies.values().toString() +
                ", użytkownik" + cinemaUserMap.values().toString() +
                ", bilety" + tickets.values().toString() +
                '}';
    }
}


