package com.epam.java.se;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by Dmitrii_Semenov on 2/13/2017.
 */
public class IntArrayListTest {

    /**
     * Test downfall sorting against native sort method.
     *
     * @throws Exception
     */
    @Test
    public void sortDownTest() throws Exception {
        final int[] ints = {12, 0, -13, 666, 2, 56, 56, 56, 120, -1, 1, 0, Integer.MAX_VALUE, Integer.MIN_VALUE};
        final int[] expected = Arrays.copyOf(ints, ints.length);
        Arrays.sort(expected);

        final IntArrayList list = new IntArrayList(ints);

        list.sortDown();

        for (int i = 0; i < expected.length; i++) {
            assertEquals("i = " + i, expected[i], list.get(i));

        }
    }

    /**
     * Test uprise sorting against native sort method.
     *
     * @throws Exception
     */
    @Test
    public void sortUpTest() throws Exception{
        final int[] ints = {12, 0, -13, 666, 2, 56, 56, 56, 120, -1, 1, 0, Integer.MAX_VALUE, Integer.MIN_VALUE};
        final int[] expected = Arrays.copyOf(ints, ints.length);
        Arrays.sort(expected);

        final IntArrayList list = new IntArrayList(ints);

        list.sortUp();
        for (int i = 0; i < list.getSize(); i++) {
            System.out.print(" " + list.get(i));
        }

        for (int i = 0; i < expected.length; i++) {
            assertEquals("i = " + i, expected[i], list.get(i));

        }

    }

    /**
     * Test binary search: recursive implementation.
     *
     * @throws Exception
     */
    @Test
    public void binarySearchRecursive() throws Exception {
        final int[] ints = {12, 0, -13, 666, 2, 56, 56, 56, 120, -1, 1, 0, Integer.MAX_VALUE, Integer.MIN_VALUE};
        final IntArrayList list = new IntArrayList(ints);
        list.sortDown();

        for (int i = 0; i < list.getSize(); i++) {

            System.out.print(" " + list.get(i));
        }

        final int[] searchInts = {Integer.MIN_VALUE, -13, -20, 0, 2, 12, 119, 120, 130, Integer.MAX_VALUE};
        final int[] expectedIndexes = {0, 1, -2, 3, 6, 7, -12, 11, -13, 13};

        for (int i = 0; i < searchInts.length; i++) {
            assertEquals(expectedIndexes[i], list.binarySearchRecursive(searchInts[i]));
        }
    }

    /**
     * Test binary search: cyclic implementation.
     *
     * @throws Exception
     */
    @Test
    public void binarySearch() throws Exception {
        final int[] ints = {12, 0, -13, 666, 2, 56, 56, 56, 120, -1, 1, 0, Integer.MAX_VALUE, Integer.MIN_VALUE};
        final IntArrayList list = new IntArrayList(ints);
        list.sortDown();

        for (int i = 0; i < list.getSize(); i++) {

            System.out.print(" " + list.get(i));
        }

        final int[] searchInts = {Integer.MIN_VALUE, -13, -20, 0, 2, 12, 119, 120, 130, Integer.MAX_VALUE};
        final int[] expectedIndexes = {0, 1, -2, 3, 6, 7, -12, 11, -13, 13};

        for (int i = 0; i < searchInts.length; i++) {
            assertEquals(expectedIndexes[i], list.binarySearch(searchInts[i]));
        }
    }

}