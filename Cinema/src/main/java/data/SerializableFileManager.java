package data;

import exception.DataExportException;
import exception.DataImportException;
import model.Cinema;

import java.io.*;

public class SerializableFileManager implements FileManager {
    private static final String FILE_NAME = "Cinema.o";
    @Override
    public void exportData(Cinema cinema){
        try (var fos = new FileOutputStream(FILE_NAME);
             var oos = new ObjectOutputStream(fos)){
            oos.writeObject(cinema);
        } catch (FileNotFoundException e){
            throw new DataExportException("Brak pliku " + FILE_NAME);
        } catch (IOException e){
            throw new DataExportException("Błąd zapisu danych do pliku " + FILE_NAME );
        }
    }
    @Override
    public Cinema importData(){
        try(var fis = new FileInputStream(FILE_NAME);
            var ois = new ObjectInputStream(fis)
        ){
            return (Cinema) ois.readObject();
        } catch (FileNotFoundException e){
            throw new DataImportException("Brak pliku " + FILE_NAME);
        } catch (IOException e){
            throw new DataImportException("Błąd odczytu pliku " + FILE_NAME);
        } catch (ClassNotFoundException e){
            throw new DataImportException("Niezgodny typ danych w pliku " + FILE_NAME);
        }
    }
}
