package com.epam.java.se;

import static java.lang.Math.abs;

/**
 * Created by Dmitrii_Semenov on 2/9/2017.
 */
public class Set64 {

    private long data;

    public Set64() {
        this(0b0);
    }

    private Set64(long data){
        this.data = data;
    }

    public long getData(){
        return this.data;
    }

    /**
     * Adds element to Set64.
     *
     * @param value element value
     */
    public void add(int value){
        if(value < 0 || value > 63){
            return;
        }
        data |= 1L << value;
    }

    /**
     * Removes element from Set64 by value.
     *
     * @param value value of element to be removed
     */
    public void remove(int value){
        if(value < 0 || value > 63){
            return;
        }
        data &= ~(1L << value);
    }

    /**
     * Checks if Set64 contains the element by value.
     *
     * @param value element value to be checked
     * @return true if contains, else false
     */
    public boolean contains(int value){
        if(value < 0 || value > 63){
            return false;
        }

        final long mask = 1L << value;
        final long res = data & mask;
        return res !=0;
    }

    /**
     * Returnes union of this Set64 with other Set64 from parameter.
     *
     * @param other Set64 instance to make union with this
     * @return the union of this Set64 and other Set64 instance
     */
    public Set64 union(Set64 other){
        final long result = this.data | other.data;
        return new Set64(result);
    }

    /**
     * Returnes intersect of two Set64 instances.
     *
     * @param other Set64 instance
     * @return Set64 intersect
     */
    public Set64 intersect(Set64 other){
        final long result = this.data & other.data;
        return new Set64(result);
    }

    /**
     * Returnes difference between this Set64 instance and other Set64 instance.
     *
     * Difference is set of elements from this Set64 but not from other Set64 +
     * + set of elements from other Set64 but not from this Set64
     *
     * @param other Set64 instance
     * @return difference
     */
    public Set64 difference(Set64 other){
        final long res = this.data ^ other.data;
        return new Set64(res);
    }

    /**
     * Checks if this Set64 instance is subset of other Set64 instance. Non-commutative.
     *
     * @param other other Set64 method
     * @return true, if this Set64 is subset of other Set64, else false.
     */
    public boolean isSubsetOf(Set64 other){
        final long dif = this.data ^ other.data;
        if ((dif | this.data) == other.data){
            return true;
        }
        return false;
    }

}


