//Written by Ayodeji Marquis

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class NTupleComparatorTest {

    NTupleComparator nTupleComparator;

    @Test
    public void testGetPercentage() throws FileNotFoundException {
        nTupleComparator = new NTupleComparator("syns", "file1", "file2", 0);
        assertEquals(nTupleComparator.getPercentage(), 100);
        nTupleComparator = new NTupleComparator("syns", "file1", "testfile2", 0);
        assertEquals(nTupleComparator.getPercentage(), 50);
    }

    // test % for different N
    @Test
    public void testGetPercentageForN() throws FileNotFoundException {
        nTupleComparator = new NTupleComparator("syns", "file1", "file2", 2);
        assertEquals(nTupleComparator.getPercentage(), 100);
        nTupleComparator = new NTupleComparator("syns", "file1", "testfile2", 2);
        assertEquals(nTupleComparator.getPercentage(), (double) 2 * 100 / 3);
        nTupleComparator = new NTupleComparator("syns", "file1", "file2", 4);
        assertEquals(nTupleComparator.getPercentage(), 100);
        nTupleComparator = new NTupleComparator("syns", "file1", "testfile2", 4);
        assertEquals(nTupleComparator.getPercentage(), 0);
    }
}