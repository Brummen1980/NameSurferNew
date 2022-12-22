package com.example.namesurfer;

import java.util.ArrayList;

public class CountChangeEventHandler<TEventArgs> {
    private final ArrayList<ICountChangeEvent<TEventArgs>> delegateArray = new ArrayList<>();
    public void addListener(ICountChangeEvent<TEventArgs> listener) {delegateArray.add(listener);}
    public void raise(Object source, TEventArgs eventArgs) {
        if (delegateArray.size() > 0) delegateArray.forEach(p -> p.raise(source, eventArgs));
    }
}
