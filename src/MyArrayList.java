import java.util.Arrays;
import java.util.Comparator;

public class MyArrayList <T> {
    private int numberOfCells = 10;
    private T[] array = (T[]) new Object[getNumberOfCells()];

    public int getNumberOfCells() {
        return numberOfCells;
    }

    public void setNumberOfCells(int numberOfCells) {
        this.numberOfCells = numberOfCells;
    }

    public void add(T element) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == null) {
                array[i] = element;
                break;
            }
            if (array[array.length - 1] != null) {
              T [] newArray = (T[]) new Object[array.length+1];
              System.arraycopy(array,0, newArray,0, array.length);
              newArray[newArray.length-1]=element;
              array  = newArray;
                System.out.println(Arrays.toString(newArray));
              break;
            }
        }
    }

    public T get(int index) {
        for (int i = 0; i < array.length; i++) {
            if (index == i && index < array.length) {
                return array[index];
            }
        }
        return null;
    }

    public void addAll(MyArrayList<T> list) {
        for (T element : list.array) {
            if (element != null) {
                add(element);
            }
        }

    }

    public void clear() {
        for (int i = 0; i < array.length; i++) {
            if (array[i] != null) {
                array[i] = null;
            }

        }
    }

    public boolean isEmpty() {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == null) {
                return true;
            } else {
                return false;
            }

        }
        return false;
    }

    private int getCurrentSize() {
        int size = 0;
        for (T element : array) {
            if (element != null) {
                size++;
            }
        }
        return size;
    }

    public void sort(Comparator<? super T> c) {
      array = (T[]) mergeSort(array, c, getCurrentSize());
    }

    private Object mergeSort(T[] array, Comparator<? super T> c, int currentSize) {

        if (currentSize <= 1) {
            return array;
        }
        int mid = currentSize / 2;
        T[] left = (T[]) new Object[mid];
        T[] right = (T[]) new Object[currentSize - mid];
        System.arraycopy(array, 0, left, 0, mid);
        System.arraycopy(array, mid, right, 0, currentSize - mid);

        left = (T[]) mergeSort(left, c, mid);
        right = (T[]) mergeSort(right, c, currentSize - mid);

        return merge(left, right, c);
    }

    private T[] merge(T[] left, T[] right, Comparator<? super T> c) {
        T[] result = (T[]) new Object[left.length + right.length];
        int i = 0, k = 0, j = 0;
        while (i < left.length && j < right.length) {
            if (c.compare(left[i], right[j]) <= 0) {
                result[k++] = left[i++];
            } else {
                result[k++] = right[j++];
            }
            while (i < left.length) {
                if (left[i] != null) {
                    result[k++] = left[i];
                }
                i++;
            }
            while (j < right.length) {
                if (right[j] != null) {
                    result[k++] = right[j];
                }
                j++;
            }
        }
        return result;
    }


    @Override
    public String toString() {
        return "MyArrayList{" +
                "numberOfCells=" + numberOfCells +
                ", array=" + Arrays.toString(array) +
                '}';
    }
    // TODO дописать пересоздание массива, копирование, посмотреть в дебаггере что с размером


}