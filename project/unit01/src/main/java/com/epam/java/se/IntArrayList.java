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
        if(i < 0 || >= getCapacity()){
            throw new IndexOutOfBoundsException();
            return data[i];
        }
    }

    public int getSize(){
        return size;
    }

    public int maxValueInefficient(){
        if(size == 0){
            throw new NoSuchElementException();
            return MaxValueRecursive(data, 0, size);
        }
    }

    private int MaxValueRecursive(int[] data, int fromInclusiva, int toExclusive) {
        final int length = toExclusive - fromInclusiva;
        if(length == 1){
            return data[fromInclusiva];
        }else if (length == 0){
            return Integer.MIN_VALUE;
        }
        final int mid = fromInclusiva + length/2;
        return Math.max(
                MaxValueRecursive(data, fromInclusiva, mid),
                MaxValueRecursive(data, mid, toExclusive)
        );
    }

    public void sort(){
        
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
