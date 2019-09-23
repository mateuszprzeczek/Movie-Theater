package io.file;

import exception.DataExportException;
import exception.DataImportException;
import exception.InvalidDataException;
import logic.MovieController;
import model.Cinema;

import java.util.Scanner;

public class ImportExport {
    private FileManager fileManager;
    private Scanner sc = new Scanner(System.in);
    private MovieController movieController;

    public ImportExport(MovieController movieController) {
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


    public void exit() {
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
