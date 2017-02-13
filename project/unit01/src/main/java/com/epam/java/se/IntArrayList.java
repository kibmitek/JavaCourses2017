package com.epam.java.se;

import java.util.Arrays;
import java.util.NoSuchElementException;


/**
 * Created by Dmitrii on 2/13/2017.
 */
public class IntArrayList {
    private int[] data;
    private int size;

    public IntArrayList(int[] data){
        this.data = Arrays.copyOf(data, data.length);
        size = data.length;
    }

    public IntArrayList(){
        data = new int[10];
        size = 0;
    }

    public void add(int value){
        insureCapacity(size +1);
        data[size] = value;
        size ++;
    }

    public int get(int i){
        if(i < 0 || i >= getCapacity()){
            throw new IndexOutOfBoundsException();
        }
        return data[i];
    }

    public int getSize(){
        return size;
    }

    public int maxValueInefficient(){
        if(size == 0){
            throw new NoSuchElementException();
        }
        return MaxValueRecursive(data, 0, size);
    }

    private int MaxValueRecursive(int[] data, int fromInclusive, int toExclusive) {
        final int length = toExclusive - fromInclusive;
        if(length == 1){
            return data[fromInclusive];
        }else if (length == 0){
            return Integer.MIN_VALUE;
        }
        final int mid = fromInclusive + length/2;
        return Math.max(
                MaxValueRecursive(data, fromInclusive, mid),
                MaxValueRecursive(data, mid, toExclusive)
        );
    }

    public void sort(){
        mergeSort(data, 0, getSize(), new int[getSize()]);
    }

    /**
     * Expected collection to be sorted
     *
     * @param value value to find in collection
     * @return index of the value or -indexToInsert -1
     */
    public int binarySearch(int value){
        int start = 0;
        int mid = size/2;
        int end = size-1;
        do {
            if (data[mid] == value) {
                return mid;
            } else if (data[mid] < value) {
                start = mid + 1;
                mid = start + (end - start)/2 + 1;
            } else {
                end = mid - 1;
                mid = (end) / 2;
            }
        }while (mid != end || mid != start);
        if (data[mid] == value) {
            return mid;
        }
        if(mid == start){
            return -(start + 1) - 1;
        }else
        return -(end + 1) - 1;
    }

    private static void mergeSort(int[] data, int startInclusive, int endExclusive, int[] free) {
        final int length = endExclusive - startInclusive;
        if(length <= 1){
            return;
        }
        final int mid = startInclusive + length/2;
        mergeSort(data, startInclusive, mid, free);
        mergeSort(data, mid, endExclusive, free);

        merger(data, startInclusive, mid, endExclusive, free);
    }

    private static void merger(int[] data, int startInclusive, int mid, int endExclusive, int[] free) {
        System.arraycopy(data, startInclusive, free, startInclusive, endExclusive - startInclusive);
        int i = startInclusive;
        int j = mid;
        for(int k = startInclusive; k < endExclusive; k++){
            if(i >= mid){
                data[k] = free[j++];
            }else if(j >= endExclusive){
                data[k] = free[i++];
            }else if(free[i] < free[j]){
                data[k] = free[i++];
            }else{
                data[k] = free[j++];
            }
        }

    }

    private void insureCapacity(int requiredCapacity) {
        if(requiredCapacity <= getCapacity()){
            return;
        }

        final int newCapacity = Math.max(requiredCapacity, (getCapacity() * 3) / 2 +1);
        data = Arrays.copyOf(data,newCapacity);
    }

    private int getCapacity() {
        return data.length;
    }

}
