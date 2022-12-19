package com.example.namesurfer;

import java.util.Objects;

public class NSDataEntre {
    public static final int VALUES_COUNT = 12;
    private String name = "";
    private final int[] values = new int[VALUES_COUNT];

    public NSDataEntre(String sourceString)
    {
        String[] s =  sourceString.split(" ");
        if (s.length != VALUES_COUNT + 1) throw new IllegalArgumentException("Source string is not valid");
        name = Utils.Capitalize(s[0]);
        for (int i = 0; i < VALUES_COUNT; i++) values[i] = Integer.parseInt(s[i + 1]);
    }

    public String GetName() {return name;}
    public int GetValue(int index) {return values[index];}
    public void Add(NSDataEntre dataEntre){
        if (!Objects.equals(name, dataEntre.name))
            throw new IllegalArgumentException("Names of entries are not equal");
        for (int i = 0; i < VALUES_COUNT; i++) values[i] += dataEntre.values[i];
    }
}
