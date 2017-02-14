package com.epam.java.se;

/**
 * Created by Dmitrii on 2/10/2017.
 */
public class IntSet {
    private long[] data;

    public IntSet(){
        this (new long[]{0b0});
    }

    private IntSet(long[] data){
        long[] tmp = new long[data.length];
        System.arraycopy(data, 0, tmp, 0, data.length);
        this.data = tmp;
    }

    public long[] getData(){
        return data;
    }

    /**
     * Adds element to com.epam.java.se.IntSet.
     *
     * @param value element value
     */
    public void add(int value){
        if(value < 0 || value > Integer.MAX_VALUE){
            return;
        }
        int i = value/64;
        value %= 64;
        long tmp[] = (this.data.length < i+1) ? new long[i+1] : new long[this.data.length];
        System.arraycopy(this.data, 0,tmp,0, this.data.length);
        for (int j = 0; j < this.data.length; j++) {
            tmp[j] |= data[j];
        }

        tmp[i] |= 1L << value;

        this.data = tmp;

    }

    /**
     * Removes element from com.epam.java.se.IntSet by value.
     *
     * @param value value of element to be removed
     */
    public void remove(int value){
        if(value < 0 || value > Integer.MAX_VALUE){
            return;
        }
        int i = value/64;
        value %= 64;
        data[i] &= ~(1L << value);
    }

    /**
     * Checks if com.epam.java.se.IntSet contains the element by value.
     *
     * @param value element value to be checked
     * @return true if contains, else false
     */
    public boolean contains(int value){
        if(value < 0 || value > Integer.MAX_VALUE){
            return false;
        }

        int i = value/64;
        if(data.length < i+1){
            return false;
        }
        value %= 64;
        final long mask[] = new long[i+1];
        mask[i] = 1L << value;

        final long res = data[i] & mask[i];
        return res !=0;
    }

    /**
     * Returnes union of this com.epam.java.se.IntSet with other Set64 from parameter.
     *
     * @param other com.epam.java.se.IntSet instance to make union with this
     * @return the union of this com.epam.java.se.IntSet and other com.epam.java.se.IntSet instance
     */
    public IntSet union(IntSet other){
        final int thisLength = this.data.length;
        final int otherLength = other.data.length;
        //union based on small array
        long[] tmp;
        if(thisLength < otherLength){
            tmp = new long[otherLength];
            System.arraycopy(other.data, 0,tmp,0, otherLength);

            for (int i = 0; i < thisLength; i++) {
                tmp[i] |= this.data[i];
            }

        }else {
            tmp = new long[thisLength];
            System.arraycopy(this.data, 0,tmp,0, thisLength);

            for (int i = 0; i < otherLength; i++) {
                tmp[i] |= other.data[i];
            }
        }

        return new IntSet(tmp);

    }

    /**
     * Returnes intersect of two com.epam.java.se.IntSet instances.
     *
     * @param other com.epam.java.se.IntSet instance
     * @return com.epam.java.se.IntSet intersect
     */
    public IntSet intersect(IntSet other){
        final int thisLength = this.data.length;
        final int otherLength = other.data.length;

        long[] res = new long[this.data.length];
        System.arraycopy(this.data, 0,res,0,thisLength);

        if(thisLength < otherLength){
            for (int i = 0; i < thisLength; i++) {
                res[i] &= other.data[i];
            }

        }else {
            for (int i = 0; i < otherLength; i++) {
                res[i] &= other.data[i];
            }
        }

        return new IntSet(res);
    }

    /**
     * Returnes difference between this com.epam.java.se.IntSet instance and other com.epam.java.se.IntSet instance.
     *
     * Difference is set of elements from this com.epam.java.se.IntSet but not from other com.epam.java.se.IntSet +
     * + set of elements from other com.epam.java.se.IntSet but not from this com.epam.java.se.IntSet
     *
     * @param other com.epam.java.se.IntSet instance
     * @return difference
     */
    public IntSet difference(IntSet other){
        final int thisLength = this.data.length;
        final int otherLength = other.data.length;

        long[] res = new long[this.data.length];
        System.arraycopy(this.data, 0,res,0,thisLength);

        if(thisLength < otherLength){
            for (int i = 0; i < thisLength; i++) {
                res[i] ^= other.data[i];
            }

        }else {
            for (int i = 0; i < otherLength; i++) {
                res[i] ^= other.data[i];
            }
        }

        return new IntSet(res);
    }

    /**
     * Checks if this com.epam.java.se.IntSet is subset of other.
     *
     * @param other other com.epam.java.se.IntSet to be checked if it contains this com.epam.java.se.IntSet
     * @return true if this com.epam.java.se.IntSet is subset of other
     *
     */
    public boolean isSubsetOf(IntSet other){
        if (this.data.length > other.data.length){
            return false;
        }
        final IntSet dif = this.difference(other);
        for (int i = 0; i < this.data.length; i++) {
            if ((dif.data[i] | this.data[i]) != other.data[i]){
                return false;
            }
        }

        return true;
    }
}
