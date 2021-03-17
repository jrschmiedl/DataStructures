/**
 * 
 */
package edu.ncsu.csc316.dsa.sorter;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;

/**
 * Test Class for CountingSorter.java
 * @author jrschmie Jacob Schmiedl
 */
public class CountingSorterTest {

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

	/** Sorter that allows the students to be sorted by CountingSorter */
	private CountingSorter<Student> sorter;
	
	/**
	 * The Setup for the testing of CountingSorter
	 */
	@Before
	public void setUp() {
		sOne = new Student("OneFirst", "OneLast", 1, 1, 1.0, "oneUnityID");
		sTwo = new Student("TwoFirst", "TwoLast", 2, 2, 2.0, "twoUnityID");
		sThree = new Student("ThreeFirst", "ThreeLast", 3, 3, 3.0, "threeUnityID");
		sFour = new Student("FourFirst", "FourLast", 4, 4, 4.0, "fourUnityID");
		sFive = new Student("FiveFirst", "FiveLast", 5, 5, 5.0, "fiveUnityID");
		
		sorter = new CountingSorter<Student>();
	}

	/**
	 * Tests the sorting of the students.
	 */
	@Test
	public void testSortStudent() {
		Student[] original = { sTwo, sOne, sFour, sThree, sFive };
		sorter.sort(original);
		assertEquals("OneFirst", original[0].getFirst());
		assertEquals("TwoFirst", original[1].getFirst());
		assertEquals("ThreeFirst", original[2].getFirst());
		assertEquals("FourFirst", original[3].getFirst());
		assertEquals("FiveFirst", original[4].getFirst());
	}
}
