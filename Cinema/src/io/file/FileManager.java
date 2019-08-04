package io.file;

import model.Cinema;

public interface FileManager {
    Cinema importData();
    void exportData(Cinema cinema);

}
