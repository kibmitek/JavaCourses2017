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

    /**
     * Method to get Max value from IntArrayList.
     *
     *
     * @return max value
     */
    public int maxValueInefficient(){
        if(size == 0){
            throw new NoSuchElementException();
        }
        return MaxValueRecursive(data, 0, size);
    }

    /**
     * Not effective recursive method returned Max value of int [] array.
     *
     * @param data source array of int
     * @param fromInclusive start index(inclusive)
     * @param toExclusive end index(exclusive)
     * @return
     */
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

    /**
     * Sorts elements in IntArrayList using downfall merge sort.
     *
     */
    public void sortDown(){
        mergeSortDown(data, 0, getSize(), new int[getSize()]);
    }

    /**
     * Sorts elements in IntArrayList using uprise merge sort.
     *
     */
    public void sortUp(){
        mergeSortUp(data, 0, getSize(), new int[getSize()]);
    }


    public int binarySearchRecursive(int value){
        return recusiveSearch(value, 0, size-1);
    }

    /**
     * Recursive binary search implementation.
     *
     * Expected collection is already sorted.
     *
     * @param value value to search
     * @param start start index
     * @param end end index
     * @return index of the value or -indexToInsert -1
     */
    private int recusiveSearch(int value, int start, int end) {
        int mid = (start + end)/2;
        if (data[mid] == value) {
            return mid;
        } else if (data[mid] < value) {

            if(mid == end) {return -mid - 2;}

            start = mid + 1;

        } else if(data[mid] > value){

            if(mid == start) {return -mid - 1;}

            end = mid - 1;
        }

        return recusiveSearch(value, start, end);

    }

    /**
     * Search element in collection.
     *
     * Expected collection is already sorted. Cyclic implementation.
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
            } else {
                end = mid - 1;
            }
            mid = (start + end)/2;
        }while (mid != end && mid != start);

        if (data[mid] == value) {
            return mid;
        }
        else if(data[mid] < value){
            return -mid - 2;
        }else
        return -mid -1;
    }

    /**
     * Sorts array of ints using downfall merge sortDown algorithm.
     *
     * @param data array of ints to be sorted
     * @param startInclusive index start to sortDown from(inclusive)
     * @param endExclusive index to finish sorting to(exclusive)
     * @param free additional empty array with length = data.length
     */
    private static void mergeSortDown(int[] data, int startInclusive, int endExclusive, int[] free) {
        final int length = endExclusive - startInclusive;
        if(length <= 1){
            return;
        }
        final int mid = startInclusive + length/2;
        mergeSortDown(data, startInclusive, mid, free);
        mergeSortDown(data, mid, endExclusive, free);

        merger(data, startInclusive, mid, endExclusive, free);
    }

    /**
     * Sorts array of ints using downfall merge sortDown algorithm.
     *
     * @param data array of ints to be sorted
     * @param startInclusive index start to sortDown from(inclusive)
     * @param endExclusive index to finish sorting to(exclusive)
     * @param free additional empty array with length = data.length
     */
    public static void mergeSortUp(int[] data, int startInclusive, int endExclusive, int[] free){
        final int length = endExclusive - startInclusive;
        for(int k = 1; k < length*2; k *=2){
            for (int i = 0; i < length; i+=k) {
                endExclusive = Math.min(startInclusive + k, length);
                int mid = Math.min(startInclusive + k/2, length);
                merger(data, startInclusive, mid, endExclusive, free);
                startInclusive = endExclusive;
            }
            startInclusive = 0;
        }
    }

    /**
     * Helper merger method for mergeSortDown.
     *
     * @param data array of ints to be sorted
     * @param startInclusive index start to sortDown from(inclusive)
     * @param mid index of the middle of the array
     * @param endExclusive index to finish sorting to(exclusive)
     * @param free additional empty array with length = data.length
     */
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

    /**
     * Ensures required capacity of data
     *
     * @param requiredCapacity
     */
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
