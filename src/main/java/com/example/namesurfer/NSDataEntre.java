package com.example.namesurfer;

public class NSDataEntre {
    private String name = "";
    private int[] values = new int[12];

    public NSDataEntre(String sourceString)
    {
        String[] s =  sourceString.split(" ");
        if (s.length != 13) throw new IllegalArgumentException("Source string is not valid");
        name = Utils.Capitalize(s[0]);
        for (int i = 0; i < 12; i++) {
            values[i] = Integer.parseInt(s[i + 1]);
        }
    }

    public String GetName() {return name;}
    public int GetValue(int index) {return values[index];}
    public void Add(NSDataEntre dataEntre){
        if (name != dataEntre.name) throw new IllegalArgumentException("Names of entries are not equal");
        for (int i = 0; i < 12; i++) values[i] += dataEntre.values[i];
    }
}
