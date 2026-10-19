package org.example;
import java.util.stream.Stream;
import java.util.stream.IntStream;

public class MyArrayList<T>  {
    private static final int INIT_SIZE = 16;
    private static final int CUT_RATE = 4;
    private Object[] array = new Object[INIT_SIZE];
    private int pointer = 0;

    public void add(T item) {
        if (pointer == array.length) {
            resize(array.length * 2);
        }
        array[pointer++] = item;
    }

    public T get(int index) {
        checkIndex(index);
        return (T) array[index];
    }

    public T remove(int index) {
        checkIndex(index);
        T removed = (T) array[index];

        for (int i = index; i < pointer - 1; i++) {
            array[i] = array[i + 1];
        }
        array[--pointer] = null;

        if (array.length > INIT_SIZE && pointer < array.length / CUT_RATE) {
            resize(Math.max(INIT_SIZE, array.length / 2));
        }
        return removed;
    }

    public int size() {
        return pointer;
    }

    public boolean isEmpty() {
        return pointer == 0;
    }

    public Stream<T> stream() {
        return IntStream.range(0, pointer).mapToObj(i -> (T) array[i]);
    }

    public void set(int index, T item) {
        checkIndex(index);
        array[index] = item;
    }

    private void resize(int newLength) {
        if (newLength < pointer) {
            throw new IllegalArgumentException("Новый размер меньше количества элементов");
        }

        Object[] newArray = new Object[newLength];
        System.arraycopy(array, 0, newArray, 0, pointer);
        array = newArray;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= pointer) {
            throw new IndexOutOfBoundsException("Индекс: " + index + ", размер: " + pointer
            );
        }
    }
}