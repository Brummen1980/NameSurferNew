package com.example.namesurfer;

import java.io.*;
import java.util.Dictionary;
import java.util.Hashtable;

public class NSData {
    private Hashtable<String, NSDataEntre> data = new Hashtable<>();

    public NSData(String sourceFileName) throws IOException {
        if (sourceFileName == null || sourceFileName.isEmpty()) sourceFileName = "assets/names-data.txt";
        File sourceFile = new File(sourceFileName);
        if (!(sourceFile.exists() && sourceFile.isFile())) throw new FileNotFoundException("File is not exist");

        BufferedReader br = new BufferedReader(new FileReader(sourceFileName));
        try {
            String str;
            while ((str = br.readLine()) != null) {
                NSDataEntre nde = new NSDataEntre(str);
                if (data.containsKey(nde.GetName())) data.get(nde.GetName()).Add(nde);
                else data.put(nde.GetName(), nde);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            br.close();
        }
    }

    public NSDataEntre GetEntre(String name){
        return data.get(Utils.Capitalize(name));
    }

}
