package org.example.sort;
import org.example.collection.MyArrayList;
import java.util.Comparator;

public class BubbleSort<T> implements SortAlgorithm<T> {

    @Override
    public MyArrayList<T> sort(MyArrayList<T> list, Comparator<T> comparator) {

        MyArrayList<T> sortedList = new MyArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            sortedList.add(list.get(i));
        }

        int n = sortedList.size();
        boolean swapped;

        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (comparator.compare(sortedList.get(j), sortedList.get(j + 1)) > 0) {
                    T temp = sortedList.get(j);
                    sortedList.set(j, sortedList.get(j + 1));
                    sortedList.set(j + 1, temp);
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
        return sortedList;
    }
}
