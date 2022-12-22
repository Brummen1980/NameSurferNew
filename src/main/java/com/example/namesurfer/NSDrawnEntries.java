package com.example.namesurfer;

import org.jetbrains.annotations.NotNull;

public class NSDrawnEntries {

    public CountChangeEventHandler<Integer> getOnCountChanged() {return  drawnList.onCountChanged;}
    private final EvHashtable<String, NSDataEntry> drawnList = new EvHashtable<>();
    public void add(@NotNull NSDataEntry entry){
        if (drawnList.containsKey(entry.GetName())) throw new IllegalArgumentException("This name is already drawn");
        drawnList.put(entry.GetName(), entry);
    }
    public void clear() {
        drawnList.clear();}
}
