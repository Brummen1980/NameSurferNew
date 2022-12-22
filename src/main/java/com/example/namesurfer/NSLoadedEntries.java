package com.example.namesurfer;

import java.io.*;
import java.util.Hashtable;

public class NSLoadedEntries {
    public static final int DATE_START = 1900;
    public static final int DATE_STEP = 10;
    private Hashtable<String, NSDataEntry> data = new Hashtable<>();
    private String dataFileName;

    public NSLoadedEntries(String sourceFileName) throws IOException {
        File sourceFile = new File(sourceFileName);
        if (!(sourceFile.exists() && sourceFile.isFile())) throw new FileNotFoundException("File is not exist");

        try (BufferedReader br = new BufferedReader(new FileReader(sourceFileName))) {
            String str;
            while ((str = br.readLine()) != null) {
                NSDataEntry nde = new NSDataEntry(str);
                if (data.containsKey(nde.GetName())) data.get(nde.GetName()).Add(nde);
                else data.put(nde.GetName(), nde);
            }
        }

        dataFileName = sourceFile.getPath();
    }
    public boolean loadDataFromFile(String sourceFileName){
        try {
            NSLoadedEntries newList = new NSLoadedEntries(sourceFileName);
            data = newList.data;
            dataFileName = newList.dataFileName;
            return true;
        } catch (IOException e) {
            return false;
        }
    }
    public String getDataFileName() {return dataFileName;}
    public NSDataEntry getEntre(String name){
        return data.get(Utils.Capitalize(name));
    }

}
