package com.example.namesurfer;

import java.io.*;
import java.util.Hashtable;

public class NSData {
    public static final int DATE_START = 1900;
    public static final int DATE_STEP = 10;
    private final Hashtable<String, NSDataEntre> data = new Hashtable<>();
    private String dataFileName;

    public NSData(String sourceFileName) throws IOException {
        if (sourceFileName == null || sourceFileName.isEmpty()) sourceFileName = "assets/names-data.txt";
        File sourceFile = new File(sourceFileName);
        if (!(sourceFile.exists() && sourceFile.isFile())) throw new FileNotFoundException("File is not exist");

        try (BufferedReader br = new BufferedReader(new FileReader(sourceFileName))) {
            String str;
            while ((str = br.readLine()) != null) {
                NSDataEntre nde = new NSDataEntre(str);
                if (data.containsKey(nde.GetName())) data.get(nde.GetName()).Add(nde);
                else data.put(nde.GetName(), nde);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        dataFileName = sourceFile.getPath();
    }

    public String getDataFileName() {return dataFileName;}
    public NSDataEntre getEntre(String name){
        return data.get(Utils.Capitalize(name));
    }

}
