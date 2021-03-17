/**
 * 
 */
package edu.ncsu.csc316.dsa.sorter;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;

/**
 * Test Class for StudentGPAComparator.java
 * @author jrschmie Jacob Schmiedl
 */
public class SelectionSorterTest {

	/** Test the sorting by using a list of ascending numbers */
	private Integer[] dataAscending = { 1, 2, 3, 4, 5 };
	/** Test the sorting by using a list of descending numbers */
	private Integer[] dataDescending = { 5, 4, 3, 2, 1 };
	/** Test the sorting by using a list of random numbers */
	private Integer[] dataRandom = { 3, 4, 5, 2, 1 };

	/** Sorter that allows the integers to be sorted by SelectionSorter */
	private SelectionSorter<Integer> intSorter;
	
	/**
	 * The setup for the testing of SelectionSorter
	 */
	@Before
	public void setUp() {
		intSorter = new SelectionSorter<Integer>();
	}
	
	/**
	 * Tests the Comparator for SelectionSort
	 */
	@Test
	public void testSortIntegers() {
		//ascending list
		intSorter.sort(dataAscending);
		assertEquals(Integer.valueOf(1), dataAscending[0]);
		assertEquals(Integer.valueOf(2), dataAscending[1]);
		assertEquals(Integer.valueOf(3), dataAscending[2]);
		assertEquals(Integer.valueOf(4), dataAscending[3]);
		assertEquals(Integer.valueOf(5), dataAscending[4]);

		//descending list
		intSorter.sort(dataDescending);
		assertEquals(Integer.valueOf(1), dataDescending[0]);
		assertEquals(Integer.valueOf(2), dataDescending[1]);
		assertEquals(Integer.valueOf(3), dataDescending[2]);
		assertEquals(Integer.valueOf(4), dataDescending[3]);
		assertEquals(Integer.valueOf(5), dataDescending[4]);

		//random list
		intSorter.sort(dataRandom);
		assertEquals(Integer.valueOf(1), dataRandom[0]);
		assertEquals(Integer.valueOf(2), dataRandom[1]);
		assertEquals(Integer.valueOf(3), dataRandom[2]);
		assertEquals(Integer.valueOf(4), dataRandom[3]);
		assertEquals(Integer.valueOf(5), dataRandom[4]);
	}
	
	/**
	 * Tests if the sorting of the students works
	 */
	@Test
	public void testSortStudent() {
		Student sOne = new Student("OneFirst", "OneLast", 1, 1, 1.0, "oneUnityID");
		Student sTwo = new Student("TwoFirst", "TwoLast", 2, 2, 2.0, "twoUnityID");
		Student sThree = new Student("ThreeFirst", "ThreeLast", 3, 3, 3.0, "threeUnityID");
		Student sFour = new Student("FourFirst", "FourLast", 4, 4, 4.0, "fourUnityID");
		Student sFive = new Student("FiveFirst", "FiveLast", 5, 5, 5.0, "fiveUnityID");
		Student[] original = { sTwo, sOne, sFour, sThree, sFive };
		SelectionSorter<Student> r = new SelectionSorter<Student>();
		r.sort(original);
		assertEquals("FiveLast", original[0].getLast());
		assertEquals("FourLast", original[1].getLast());
		assertEquals("OneLast", original[2].getLast());
		assertEquals("ThreeLast", original[3].getLast());
		assertEquals("TwoLast", original[4].getLast());
	}

}
