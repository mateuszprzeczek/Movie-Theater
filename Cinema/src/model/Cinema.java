package model;

import exception.MovieAlreadyExistsException;

import java.io.Serializable;
import java.util.*;

public class Cinema implements Serializable {
    private Map<String, Movie> movies = new HashMap<>();
    private Map<String, User> users = new HashMap<>();

    public void addMovie(Movie movie){
    if (movies.containsKey(movie.getTitle())){
        throw new MovieAlreadyExistsException(
                "Film o podanym tytule został już utworzony" + movie.getTitle()
        );
    }
    movies.put(movie.getTitle(), movie);
    }
    public void addUser(User user){
        users.put(user.getLastName(), user);
    }
    public boolean removeMovie(Movie movie){
        if (movies.containsValue(movie)){
            movies.remove(movie.getTitle());
            return true;
        }else {
            return false;
        }
    }
    public Movie findMovieByTitle(String title) {
        return movies.get(title);
    }
    public User findUserByName(String lastName){
        return users.get(lastName);
    }

    public List<Movie> getMovies() {
        return new ArrayList<>(this.movies.values());
    }
    public List<User> getUsers(){
        return new ArrayList<>(this.users.values());
    }


}
