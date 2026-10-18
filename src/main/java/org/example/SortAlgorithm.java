package org.example;

import java.util.Comparator;

public interface SortAlgorithm<T> {
    void sort(MyArrayList<T> list, Comparator<T> comparator);
}
