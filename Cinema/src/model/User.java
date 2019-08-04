package model;

import logic.OrderController;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String firstName;
    private String lastName;
    private OrderController reservedMovies;

    public User(String firstName, String lastName, OrderController reservedMovies) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.reservedMovies = reservedMovies;

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

    public OrderController getReservedMovies() {
        return reservedMovies;
    }

    public void setReservedMovies(OrderController reservedMovies) {
        this.reservedMovies = reservedMovies;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", reservedMovies=" + reservedMovies +
                '}';
    }
}
