package logic;

import exception.DataExportException;
import exception.DataImportException;
import exception.InvalidDataException;
import io.file.FileManager;
import io.file.SerializableFileManager;
import model.Cinema;
import model.Movie;

import java.util.Scanner;

public class CinemaController {
    private FileManager fileManager;
    private Scanner sc = new Scanner(System.in);
    private MovieController movieController;

    public CinemaController(MovieController movieController) {
        this.movieController = movieController;
    }

    public void importData(){
        fileManager = new SerializableFileManager();
        try {
            movieController.cinema = fileManager.importData();
            System.out.println("Zaimplementowane dane z pliku: ");
        } catch (DataImportException | InvalidDataException e) {
            System.out.println(e.getMessage());
            System.out.println("Zainicjowano nową bazę.");
            movieController.cinema = new Cinema();
        }
    }

    boolean removeMovie(Movie movie){
        if (movieController.cinema.movies.containsValue(movie)){
            movieController.cinema.movies.remove(movie.getTitle());
            return true;
        }else {
            return false;
        }
    }
    void exit() {
        try {
            fileManager.exportData(movieController.cinema);
            System.out.println("Eksport danych do pliku zakonczony powodzeniem");
        } catch (DataExportException e){
            System.out.println(e.getMessage());
        }
        System.out.println("Koniec programu...");
        sc.close();
    }
}
