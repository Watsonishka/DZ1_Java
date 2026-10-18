package org.example;
import java.util.Comparator;

public class InsertionSort <T> implements SortAlgorithm<T>{
    @Override
    public void sort(MyArrayList<T> list, Comparator<T> comparator) {
        int n = list.size();

        for (int i = 1; i < n; i++) {
            T key = list.get(i);
            int j = i - 1;

            while (j >= 0 && comparator.compare(list.get(j), key) > 0) {
                list.set(j + 1, list.get(j));
                j--;
            }

            list.set(j + 1, key);
        }
    }
}
