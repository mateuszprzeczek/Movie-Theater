package data;

import controller.MovieController;
import exception.DataExportException;

import java.util.Scanner;

public class ExportData {
    private FileManager fileManager;
    private Scanner sc = new Scanner(System.in);
    private MovieController movieController;

    public ExportData(MovieController movieController) {
        this.movieController = movieController;
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
