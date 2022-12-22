package com.example.namesurfer;

import javafx.scene.control.Alert;

import java.io.IOException;

public class NSData {
    private static final String DATA_FILENAME_DEFAULT = "assets/names-data.txt";
    public static final String MSG_CANT_READ_DATA_FILE = "Can't read a data file %s";
    public static final String MSG_TRY_OPEN_ANOTHER_FILE = "Try to open another file";
    private static NSLoadedEntries loadedEntries;
    public static NSLoadedEntries getLoadedEntries() {return loadedEntries;}
    public static NSDrawnEntries getDrawnEntries() {return drawnEntries;}
    private static final NSDrawnEntries drawnEntries = new NSDrawnEntries();

    static {
        try {
            loadedEntries = new NSLoadedEntries(DATA_FILENAME_DEFAULT);
        } catch (IOException e) {
            SimpleAlert.showMessage(Alert.AlertType.ERROR, String.format(MSG_CANT_READ_DATA_FILE,
                    DATA_FILENAME_DEFAULT), MSG_TRY_OPEN_ANOTHER_FILE);
        }
    }



}
