package io.file;

import exception.DataExportException;
import exception.DataImportException;
import model.Cinema;

import java.io.*;

public class SerializableFileManager implements FileManager{
    private static final String FILE_NAME = "Cinema.o";
    @Override
    public void exportData(Cinema cinema){
        try (FileOutputStream fos = new FileOutputStream(FILE_NAME);
             ObjectOutputStream oos = new ObjectOutputStream(fos)){
            oos.writeObject(cinema);
        } catch (FileNotFoundException e){
            throw new DataExportException("Brak pliku " + FILE_NAME);
        } catch (IOException e){
            throw new DataExportException("Błąd zapisu danych do pliku " + FILE_NAME );
        }
    }
    @Override
    public Cinema importData(){
        try(FileInputStream fis = new FileInputStream(FILE_NAME);
            ObjectInputStream ois = new ObjectInputStream(fis)
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
