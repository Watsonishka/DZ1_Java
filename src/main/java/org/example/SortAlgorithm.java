package org.example;

import java.util.Comparator;

public interface SortAlgorithm<T> {
    MyArrayList<T> sort(MyArrayList<T> list, Comparator<T> comparator);
}
