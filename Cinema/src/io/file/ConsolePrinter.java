package io.file;

import data.Movie;

import java.util.Collection;

public class ConsolePrinter {

        public void printMovies(Collection<Movie> movies){
            long count = 0L;
            for (Movie movie : movies) {
                    String toString = movie.toString();
                    printLine(toString);
                    count++;

            }
            if (count == 0){
                System.out.println("Brak Filmów.");
            }
        }


        public void printLine(String text){
            System.out.println(text);
        }

}
