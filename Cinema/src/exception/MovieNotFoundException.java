package exception;

import java.util.List;

public class MovieNotFoundException extends RuntimeException {
    public MovieNotFoundException(List<String> movieTitle){
            super("Nie znaleziono filmu " + movieTitle);
    }
}
