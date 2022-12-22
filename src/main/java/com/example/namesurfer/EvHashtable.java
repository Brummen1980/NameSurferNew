package com.example.namesurfer;

import java.util.Hashtable;
import java.util.Map;

public class EvHashtable<K, V> extends Hashtable<K, V> {
    public CountChangeEventHandler<Integer> onCountChanged = new CountChangeEventHandler<>();

    @Override
    public synchronized V put(K key, V value) {
        V ret = super.put(key, value);
        onCountChanged.raise(this, size());
        return ret;
    }

    @Override
    public synchronized void putAll(Map<? extends K, ? extends V> t) {
        super.putAll(t);
        onCountChanged.raise(this, size());
    }

    @Override
    public synchronized V remove(Object key) {
        V ret = super.remove(key);
        onCountChanged.raise(this, size());
        return ret;
    }

    public EvHashtable() {
        super();
    }

    @Override
    public synchronized void clear() {
        super.clear();
        onCountChanged.raise(this, 0);
    }
}
