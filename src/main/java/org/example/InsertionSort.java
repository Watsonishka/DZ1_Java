package org.example;
import java.util.Comparator;

public class InsertionSort <T> implements SortAlgorithm<T>{
    @Override
    public MyArrayList<T> sort(MyArrayList<T> list, Comparator<T> comparator) {
        MyArrayList<T> sortedList = new MyArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            sortedList.add(list.get(i));
        }

        int n = sortedList.size();
        for (int i = 1; i < n; i++) {
            T key = sortedList.get(i);
            int j = i - 1;

            while (j >= 0 && comparator.compare(sortedList.get(j), key) > 0) {
                sortedList.set(j + 1, sortedList.get(j));
                j--;
            }
            sortedList.set(j + 1, key);
        }
        return sortedList;
    }
}