package exception;

import java.util.List;

public class MovieNotFoundException extends RuntimeException {
    public MovieNotFoundException(String movieTitle){
            super("Nie znaleziono filmu " + movieTitle);
    }
}
