package data;

import controller.MovieController;
import exception.DataImportException;
import exception.InvalidDataException;
import io.file.SerializableFileManager;
import model.Cinema;

import java.util.Scanner;

public class ImportData {
    private FileManager fileManager;
    private final Scanner sc = new Scanner(System.in);
    private final MovieController movieController;

    public ImportData(MovieController movieController) {
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

}
