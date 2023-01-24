package com.solvd.hospitalsystem;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class ListAdapter<T> extends XmlAdapter<List<T>, List<T>> {

    @Override
    public List<T> unmarshal(List<T> v){
        return v;
    }

    @Override
    public List<T> marshal(List<T> v) {
        if (v == null) {
            return new ArrayList<T>();
        }
        return v;
    }
}

