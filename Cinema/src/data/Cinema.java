package data;

import exception.MovieAlreadyExistsException;

import java.io.Serializable;
import java.util.*;

public class Cinema implements Serializable {
    private Map<String, Movie> movies = new HashMap<>();


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
    public Collection<Movie> getMovies(){
        ArrayList<Movie> list = new ArrayList<>(this.movies.values());
        return list;
    }
}
