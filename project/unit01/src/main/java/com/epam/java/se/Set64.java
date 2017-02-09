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

    public void add(int value){
        if(value < 0 || value > 63){
            return;
        }

        data |= 1L << value;
    }

    public void remove(int value){
        if(value < 0 || value > 63){
            return;
        }

        data &= ~(1L << value);
    }

    public boolean contains(int value){
        if(value < 0 || value > 63){
            return false;
        }

        final long mask = 1L << value;
        final long res = data & mask;
        return res !=0;
    }

    public Set64 union(Set64 other){
        final long result = this.data | other.data;
        return new Set64(result);
    }

    public Set64 intersect(Set64 other){
        final long result = this.data & other.data;
        return new Set64(result);
    }

    public Set64 difference(Set64 other){
        final long result = this.data - other.data;
        return new Set64(result);
    }

    /**
     *
     * check if this is subset of other
     *
     */
    public boolean isSubsetOf(Set64 other){
        if(other.data == -1){
            return true;
        }

        if(abs(other.data) < abs(this.data)){
            return false;
        }

        for (int i = 0; i < 64; i++) {
            if (this.contains(i))
                if (!other.contains(i)) {
                    return false;
                }
            }
        return true;
    }

}

