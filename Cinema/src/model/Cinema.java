package model;

import exception.MovieAlreadyExistsException;
import exception.MovieNotFoundException;

import java.io.Serializable;
import java.util.*;

public class Cinema implements Serializable {
    public static Map<String, Movie> movies = new HashMap<>();


    public void addMovie(Movie movie){
    if (movies.containsKey(movie.getTitle())){
        throw new MovieAlreadyExistsException(
                "Film o podanym tytule został już utworzony" + movie.getTitle()
        );
    }
    movies.put(movie.getTitle(), movie);
    }
    public boolean removeMovie(Movie movie){
        if (movies.containsValue(movie)){
            movies.remove(movie.getTitle());
            return true;
        }else {
            return false;
        }
    }
    public List<Movie> getMovies(){
        return new ArrayList<>(this.movies.values());
    }
    public static List<Movie> bookMovie(List<String> movieTitle){
        Set<String> keys = movies.keySet();
        List<Movie> list = new ArrayList<>();
        for (String key : keys){
            for (String title : movieTitle){
                if (key.equals(title)){
                    list.add(movies.get(key));
                }
            }
        }
        return list;
    }


}
