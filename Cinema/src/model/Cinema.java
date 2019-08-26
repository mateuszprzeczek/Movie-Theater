package model;

import exception.MovieAlreadyExistsException;
import java.io.Serializable;
import java.util.*;

public class Cinema implements Serializable {

    public Map<String, Movie> movies = new HashMap<>();
    public Map<String, CinemaUser> users = new HashMap<>();


    public void addMovie(Movie movie){
    if (movies.containsKey(movie.getTitle())){
        throw new MovieAlreadyExistsException(
                "Film o podanym tytule został już utworzony" + movie.getTitle()
        );
    }
    movies.put(movie.getTitle(), movie);
    }
    public void addUser(CinemaUser user){
        users.put(user.getLastName(), user);
    }

    public Movie findMovieByTitle(String title) {
        return movies.get(title);
    }

    public boolean removeMovie(Movie movie){
        if (movies.containsValue(movie)){
            movies.remove(movie.getTitle());
            return true;
        }else {
            return false;
        }
    }
    public CinemaUser findUser(String lastName) throws NullPointerException {
            try{
         users.get(lastName);
    }catch (NullPointerException e){
                System.out.println("Nie ma");
            }
            return users.get(lastName);
        }


    public Optional<User> findUserByName(String lastName) {
         return Optional.ofNullable(users.get(lastName));
    }

    public List<Movie> getMovies() {
        return new ArrayList<>(this.movies.values());
    }
    public List<User> getUsers(){
        return new ArrayList<>(this.users.values());
    }

}

