/**
 * 
 */
package edu.ncsu.csc316.dsa.sorter;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;

/**
 * Test Class for BubbleSorter.java
 * @author jrschmie Jacob Schmiedl
 */
public class BubbleSorterTest {

	/** Student One */
	private Student sOne;
	/** Student Two */
	private Student sTwo;
	/** Student Three */
	private Student sThree;
	/** Student Four */
	private Student sFour;
	/** Student Five */
	private Student sFive;

	/** Test the sorting by using a list of ascending numbers */
	private Integer[] dataAscending = { 1, 2, 3, 4, 5 };
	/** Test the sorting by using a list of random numbers */
	private Integer[] dataRandom = { 3, 4, 5, 2, 1 };

	/**
	 * Tests the Comparator for BubbleSort
	 */
	@Test
	public void testBubbleSorterComparatorOfE() {
		BubbleSorter<Integer> b = new BubbleSorter<Integer>();
		b.sort(dataAscending);
		assertEquals(Integer.valueOf(1), dataAscending[0]);
		assertEquals(Integer.valueOf(2), dataAscending[1]);
		assertEquals(Integer.valueOf(3), dataAscending[2]);
		assertEquals(Integer.valueOf(4), dataAscending[3]);
		assertEquals(Integer.valueOf(5), dataAscending[4]);
		
		b.sort(dataRandom);
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
		
		sOne = new Student("OneFirst", "OneLast", 1, 1, 1.0, "oneUnityID");
		sTwo = new Student("TwoFirst", "TwoLast", 2, 2, 2.0, "twoUnityID");
		sThree = new Student("ThreeFirst", "ThreeLast", 3, 3, 3.0, "threeUnityID");
		sFour = new Student("FourFirst", "FourLast", 4, 4, 4.0, "fourUnityID");
		sFive = new Student("FiveFirst", "FiveLast", 5, 5, 5.0, "fiveUnityID");
		BubbleSorter<Student> studentSorter = new BubbleSorter<Student>();
		Student[] original = { sTwo, sOne, sFour, sThree, sFive };
		studentSorter.sort(original);
		assertEquals(sFive, original[0]);
		assertEquals(sFour, original[1]);
		assertEquals(sOne, original[2]);
		assertEquals(sThree, original[3]);
		assertEquals(sTwo, original[4]);
	}
}
