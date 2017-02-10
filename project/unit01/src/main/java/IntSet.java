import java.lang.reflect.Array;

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

//        for (int j = 0; j < data.length; j++) {
//            System.out.println("result data[" + j + "]: " + Long.toBinaryString(data[j]));
//        }
    }

    public void remove(int value){
        if(value < 0 || value > Integer.MAX_VALUE){
            return;
        }
        int i = value/64;
        value %= 64;
        data[i] &= ~(1L << value);
    }

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
