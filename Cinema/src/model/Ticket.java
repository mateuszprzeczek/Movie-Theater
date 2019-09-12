package model;

import java.io.Serializable;

  public class Ticket implements Serializable {
      private User owner;
      private Movie movie;
      private int rowNumber;
      private int seatNumber;

      public Ticket(User owner, Movie movie, int rowNumber, int seatNumber) {
          this.owner = owner;
          this.movie = movie;
          this.rowNumber = rowNumber;
          this.seatNumber = seatNumber;
      }

      @Override
      public String toString() {
          return  owner.toString() + " Film pt:  " + movie.toString() + "\n" +
                  ", Numer sali kinowej-" + movie.getCinemaHallNumber() +
                  ", Rząd-" + rowNumber +
                  ", Miejsce-" + seatNumber + "\n";
      }
  }
