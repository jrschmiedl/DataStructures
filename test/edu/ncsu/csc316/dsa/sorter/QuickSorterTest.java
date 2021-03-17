package edu.ncsu.csc316.dsa.sorter;

import static org.junit.Assert.*;

import java.util.Comparator;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;
import edu.ncsu.csc316.dsa.data.StudentGPAComparator;

/**
 * Test Class for QuickSorter.java
 * @author jrschmie Jacob Schmiedl
 */
public class QuickSorterTest {

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
	/** Test the sorting by using a list of descending numbers */
	private Integer[] dataDescending = { 5, 4, 3, 2, 1 };
	/** Test the sorting by using a list of random numbers */
	private Integer[] dataRandom = { 4, 1, 5, 3, 2 };

	/** Sorter that allows the integers to be sorted by QuickSorter */
	private QuickSorter<Integer> intSorter;
	/** Sorter that allows the student to be sorted by QuickSorter */
	private QuickSorter<Student> studentSorter;

	
	/**
	 * Setup for the test cases of QuickSorter
	 */
	@Before
	public void setUp() {
		intSorter = new QuickSorter<Integer>();
		studentSorter = new QuickSorter<Student>();
	}
	
	/**
	 * Tests the Comparator for QuickSort
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
		sOne = new Student("OneFirst", "OneLast", 1, 1, 1.0, "oneUnityID");
		sTwo = new Student("TwoFirst", "TwoLast", 2, 2, 2.0, "twoUnityID");
		sThree = new Student("ThreeFirst", "ThreeLast", 3, 3, 3.0, "threeUnityID");
		sFour = new Student("FourFirst", "FourLast", 4, 4, 4.0, "fourUnityID");
		sFive = new Student("FiveFirst", "FiveLast", 5, 5, 5.0, "fiveUnityID");
		Student[] original = { sTwo, sOne, sFour, sThree, sFive };
		studentSorter.sort(original);
		assertEquals(sFive, original[0]);
		assertEquals(sFour, original[1]);
		assertEquals(sOne, original[2]);
		assertEquals(sThree, original[3]);
		assertEquals(sTwo, original[4]);
		
		Comparator<Student> comparator = new StudentGPAComparator();
		QuickSorter<Student> q = new QuickSorter<Student>(QuickSorter.MIDDLE_ELEMENT_SELECTOR);
		q.sort(original);
		QuickSorter<Student> q1 = new QuickSorter<Student>(comparator);
		q1.sort(original);
		QuickSorter<Student> q2 = new QuickSorter<Student>(QuickSorter.FIRST_ELEMENT_SELECTOR);
		q2.sort(original);
		QuickSorter<Student> q3 = new QuickSorter<Student>(QuickSorter.LAST_ELEMENT_SELECTOR);
		q3.sort(original);
		
													
	}
	
}