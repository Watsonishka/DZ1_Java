package org.example.sort;

import org.example.collection.MyArrayList;

import java.util.Comparator;

public interface SortAlgorithm<T> {
    MyArrayList<T> sort(MyArrayList<T> list, Comparator<T> comparator);
}
