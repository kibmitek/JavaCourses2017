package com.epam.java.se;

import org.junit.Ignore;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by Dmitrii_Semenov on 2/9/2017.
 */
public class Set64Test {
    @Test
    public void add() throws Exception {
        final Set64 set = new Set64();

        assertFalse(set.contains(0));

        set.add(0);

        assertTrue(set.contains(0));

        assertFalse(set.contains(15));

        set.add(15);
        set.add(15);

        assertTrue(set.contains(15));

        set.add(-1);
        assertFalse(set.contains(-1));
        set.add(63);
        assertTrue(set.contains(63));
        set.add(64);
        assertFalse(set.contains(64));

    }

    @Test
    public void remove() throws Exception {
        final Set64 set = new Set64();
        assertFalse(set.contains(0));
        set.add(0);
        assertTrue(set.contains(0));
        set.remove(0);
        assertFalse(set.contains(0));
    }

    @Test
    public void contains() throws Exception {
        final Set64 set = new Set64();
        for (int i = -1; i < 65; i++) {
            assertFalse(set.contains(i));
        }

        set.add(-1);
        set.add(-1);
        set.add(0);
        set.add(0);
        set.add(64);
        set.add(64);
        set.add(7);
        set.add(45);

        assertFalse(set.contains(13));

        for (int i = -1; i < 65; i++) {
            if(i == 0 || i == 7 || i == 45){
                assertTrue(set.contains(i));
            }else {
                assertFalse("At index " + i, set.contains(i));
            }

        }
    }

    @Test
    public void union() throws Exception {
        final Set64 small = new Set64();
        small.add(0);
        small.add(1);
        small.add(4);
        small.add(5);
        small.add(7);
        small.add(63);

        final Set64 big = new Set64();
        big.add(1);
        big.add(4);
        big.add(5);
        big.add(8);

        final Set64 union = new Set64();
        union.add(0);
        union.add(1);
        union.add(4);
        union.add(5);
        union.add(7);
        union.add(8);
        union.add(63);

        assertEquals(union.getData(), small.union(big).getData());
    }

    @Test
    public void intersect() throws Exception {
        final Set64 small = new Set64();
        small.add(0);
        small.add(1);
        small.add(4);
        small.add(5);
        small.add(7);
        small.add(63);

        final Set64 big = new Set64();
        big.add(1);
        big.add(4);
        big.add(5);
        big.add(8);
        big.add(63);

        final Set64 intersect = new Set64();
        intersect.add(1);
        intersect.add(4);
        intersect.add(5);
        intersect.add(63);

        assertEquals(intersect.getData(), small.intersect(big).getData());

    }

    @Test
    public void difference() throws Exception{
        final Set64 small = new Set64();
        small.add(0);
        small.add(1);
        small.add(4);
        small.add(5);
        small.add(7);
        small.add(63);

        final Set64 big = new Set64();
        big.add(1);
        big.add(4);
        big.add(5);
        big.add(8);

        final Set64 diff = new Set64();
        diff.add(0);
        diff.add(7);
        diff.add(8);
        diff.add(63);

        System.out.println("expected: " + Long.toBinaryString(diff.getData()));
        System.out.println("actual  : " + Long.toBinaryString(small.difference(big).getData()));

        assertEquals(diff.getData(), small.difference(big).getData());
    }

    @Test
    public void isSubsetOf() throws Exception{
        //different sets - positive
        final Set64 small = new Set64();
        small.add(4);
        small.add(5);

        final Set64 big = new Set64();
        big.add(1);
        big.add(4);
        big.add(5);
        big.add(8);
        big.add(63);

        assertTrue(small.isSubsetOf(big));
        assertFalse(big.isSubsetOf(small));

        //different sets - negative
        final Set64 small2 = new Set64();
        small2.add(4);
        small2.add(5);
        small2.add(7);

        assertFalse(small2.isSubsetOf(big));

        //full set
        final Set64 full = new Set64();
        for (int i = 0; i < 64; i++) {
            full.add(i);
        }

        assertTrue(small.isSubsetOf(full));
        assertTrue(big.isSubsetOf(full));
        assertFalse(full.isSubsetOf(big));

        //clear set
        final Set64 clear = new Set64();
        assertTrue(clear.isSubsetOf(full));
        assertTrue(clear.isSubsetOf(small));
        assertFalse(full.isSubsetOf(clear));

        //equal sets
        final Set64 big2 = new Set64();
        big2.add(1);
        big2.add(4);
        big2.add(5);
        big2.add(8);
        big2.add(63);
        assertTrue(big.isSubsetOf(big2));
        assertTrue(big2.isSubsetOf(big));
        assertTrue(big.isSubsetOf(big));
    }
}