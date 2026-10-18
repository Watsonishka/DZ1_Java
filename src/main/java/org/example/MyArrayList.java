package org.example;

public class MyArrayList<T>  {
    private final int INIT_SIZE = 16;
    private final int CUT_RATE = 4;
    private Object[] array = new Object[INIT_SIZE];
    private int pointer = 0;

    public void add(T item) {
        if (pointer == array.length) {
            resize(array.length * 2);
        }
        array[pointer++] = item;
    }

    public T get(int index) {
        return (T)array[index];
    }

    public void remove(int index) {
        for (int i = index; i<pointer; i++ ) {
            array[i] = array[i + 1];
        }
        array[pointer] = null;
        pointer--;
        if (array.length > INIT_SIZE && pointer < array.length / CUT_RATE) {
            resize(array.length / 2);
        }
    }

    public int size() {
        return pointer;
    }

    public boolean isEmpty() {
        return pointer == 0;
    }

    public java.util.stream.Stream<T> stream() {
        return java.util.stream.IntStream.range(0, pointer)
                .mapToObj(i -> (T) array[i]);
    }

    public void set(int index, T item) {
        if (index < 0 || index >= pointer) {
            throw new IndexOutOfBoundsException("Индекс: " + index + ", размер: " + pointer);
        }
        array[index] = item;
    }

    private void resize(int newLength) {
        var newArray = new Object[newLength];
        System.arraycopy(array, 0, newArray, 0, pointer);
        array = newArray;
    }

}