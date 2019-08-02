package data;


//Working in progress
//
//
  public class Seance {
    private Movie movie;
    private int cinemaHallNumber;
    private int rowNumber;
    private int seatNumber;

    public Seance(Movie movie, int cinemaHallNumber, int rowNumber, int seatNumber) {
        this.movie = movie;
        this.cinemaHallNumber = cinemaHallNumber;
        this.rowNumber = rowNumber;
        this.seatNumber = seatNumber;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public int getCinemaHallNumber() {
        return cinemaHallNumber;
    }

    public void setCinemaHallNumber(int cinemaHallNumber) {
        this.cinemaHallNumber = cinemaHallNumber;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(int rowNumber) {
        this.rowNumber = rowNumber;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    @Override
    public String toString() {
        return "seance{" +
                "Film:" + movie.toString() + "\n" +
                ", Numer sali kinowej-" + cinemaHallNumber +
                ", Rząd-" + rowNumber +
                ", Miejsce-" + seatNumber +
                '}';
    }
}
