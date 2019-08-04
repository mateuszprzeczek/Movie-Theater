package logic;

import model.Movie;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class OrderController implements Serializable {
    public List<Movie> movies = new ArrayList<>();

    public OrderController(List<Movie> movies) {
        this.movies = movies;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    @Override
    public String toString() {
        return "OrderController{" +
                "movies=" + movies +
                '}';
    }
}
