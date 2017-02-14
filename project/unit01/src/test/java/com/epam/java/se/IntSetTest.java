package com.epam.java.se;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Dmitrii on 2/10/2017.
 */
public class IntSetTest {
    @Test
    /**
     * Test for add method verification.
     *
     */
    public void add() throws Exception {
        final IntSet set = new IntSet();

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
        assertTrue(set.contains(64));
        set.add(65);
        assertTrue(set.contains(65));
        set.add(512);
        assertTrue(set.contains(512));
    }

    @Test
    public void remove() throws Exception {
        final IntSet set = new IntSet();
        assertFalse(set.contains(0));
        set.add(0);
        assertTrue(set.contains(0));
        set.remove(0);
        assertFalse(set.contains(0));

        assertFalse(set.contains(63));
        set.add(63);
        assertTrue(set.contains(63));
        set.remove(63);
        assertFalse(set.contains(63));

        assertFalse(set.contains(512));
        set.add(512);
        assertTrue(set.contains(512));
        set.remove(512);
        assertFalse(set.contains(512));

    }

    @Test
    public void contains() throws Exception {
        final IntSet set = new IntSet();
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
        set.add(512);

        assertFalse(set.contains(13));

        for (int i = -1; i < 65; i++) {
            if(i == 0 || i == 7 || i == 45 || i == 64 || i == 512){
                assertTrue(set.contains(i));
            }else {
                assertFalse("At index " + i, set.contains(i));
            }

        }
    }

    @Test
    public void union() throws Exception {
        final IntSet small = new IntSet();
        small.add(0);
        small.add(1);
        small.add(4);
        small.add(5);
        small.add(7);
        small.add(63);

        final IntSet big = new IntSet();
        big.add(2);
        big.add(4);
        big.add(5);
        big.add(8);
        big.add(512);

        final IntSet union = new IntSet();
        union.add(0);
        union.add(1);
        union.add(2);
        union.add(4);
        union.add(5);
        union.add(7);
        union.add(8);
        union.add(63);
        union.add(512);

        for (int j = 0; j < union.getData().length; j++) {
            Assert.assertEquals(union.getData()[j], small.union(big).getData()[j]);
        }
    }

    @Test
    public void intersect() throws Exception {
        final IntSet small = new IntSet();
        small.add(0);
        small.add(1);
        small.add(4);
        small.add(63);
        small.add(512);

        final IntSet big = new IntSet();
        big.add(1);
        big.add(4);
        big.add(5);
        big.add(8);
        big.add(63);
        big.add(200);
        big.add(512);

        final IntSet intersect = new IntSet();
        intersect.add(1);
        intersect.add(4);
        intersect.add(63);
        intersect.add(512);
        for (int j = 0; j < intersect.getData().length; j++) {
            Assert.assertEquals(intersect.getData()[j], small.intersect(big).getData()[j]);
            Assert.assertEquals(intersect.getData()[j], big.intersect(small).getData()[j]);
        }
    }

    @Test
    public void difference() throws Exception {
        final IntSet big = new IntSet();
        big.add(0);
        big.add(1);
        big.add(4);
        big.add(5);
        big.add(7);
        big.add(63);
        big.add(128);

        final IntSet small = new IntSet();
        small.add(1);
        small.add(4);
        small.add(5);
        small.add(8);

        final IntSet diff = new IntSet();
        diff.add(0);
        diff.add(7);
        diff.add(8);
        diff.add(63);
        diff.add(128);

        for (int j = 0; j < diff.getData().length; j++) {
            Assert.assertEquals(diff.getData()[j], big.difference(small).getData()[j]);
        }
    }

    @Test
    public void isSubsetOf() throws Exception {
        //different sets - positive
        final IntSet small = new IntSet();
        small.add(4);
        small.add(5);
        small.add(128);

        final IntSet big = new IntSet();
        big.add(1);
        big.add(4);
        big.add(5);
        big.add(8);
        big.add(63);
        big.add(128);

        assertTrue(small.isSubsetOf(big));
        assertFalse(big.isSubsetOf(small));


        //different sets - negative
        final IntSet small2 = new IntSet();
        small2.add(4);
        small2.add(5);
        small2.add(7);

        assertFalse(small2.isSubsetOf(big));

        //full set of 256
        final IntSet full256 = new IntSet();
        for (int i = 0; i < 256; i++) {
            full256.add(i);
        }

        assertTrue(small.isSubsetOf(full256));
        assertTrue(big.isSubsetOf(full256));
        assertFalse(full256.isSubsetOf(big));

        //clear set
        final IntSet clear = new IntSet();
        assertTrue(clear.isSubsetOf(full256));
        assertTrue(clear.isSubsetOf(small));
        assertFalse(full256.isSubsetOf(clear));

        //equal sets
        final IntSet big2 = new IntSet();
        big2.add(1);
        big2.add(4);
        big2.add(5);
        big2.add(8);
        big2.add(63);
        big2.add(128);
        assertTrue(big.isSubsetOf(big2));
        assertTrue(big2.isSubsetOf(big));
        assertTrue(big.isSubsetOf(big));
    }

}