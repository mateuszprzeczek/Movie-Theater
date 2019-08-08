package model;

import exception.MovieAlreadyExistsException;
import exception.NoSuchUserException;

import java.io.Serializable;
import java.util.*;
import java.util.function.Function;

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
    public User findUser(String lastName) throws NullPointerException {
            try{
         users.get(lastName);
    }catch (NullPointerException e){
                System.out.println("Nie ma");
            }
            return users.get(lastName);
        }
    public Movie findMovieByTitle(String title) throws NullPointerException {
        try{
        movies.get(title);
        } catch (NullPointerException e){
            System.out.println("Nie posiadamy tego filmu w repertuarze");
        }
            return movies.get(title);
    }
    public boolean checkIfMovieIsAvailable(String title){
        if (movies.containsKey(title)){
            return true;
        }else {
            return false;
        }
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

