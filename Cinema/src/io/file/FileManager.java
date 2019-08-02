package io.file;

import data.Cinema;
import data.Movie;

public interface FileManager {
    Cinema importData();
    void exportData(Cinema cinema);

}
