package com.example.namesurfer;

public interface ICountChangeEvent<TEventArgs> {
    void raise(Object source, TEventArgs eventArgs);
}
