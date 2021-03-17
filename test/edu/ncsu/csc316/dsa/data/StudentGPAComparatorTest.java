/**
 * 
 */
package edu.ncsu.csc316.dsa.data;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;
import edu.ncsu.csc316.dsa.data.StudentGPAComparator;

/**
 * Test Class for StudentGPAComparator.java
 * @author jrschmie Jacob Schmiedl
 */
public class StudentGPAComparatorTest {

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

	/** The StudentGPAComparator being used */
	private StudentGPAComparator comparator;

	/**
	 * The setup tests
	 */
	@Before
	public void setUp() {
		sOne = new Student("OneFirst", "OneLast", 1, 1, 1.0, "oneUnityID");
		sTwo = new Student("TwoFirst", "TwoLast", 2, 2, 2.0, "twoUnityID");
		sThree = new Student("ThreeFirst", "ThreeLast", 3, 3, 3.0, "threeUnityID");
		sFour = new Student("FourFirst", "FourLast", 4, 4, 4.0, "fourUnityID");
		sFive = new Student("FiveFirst", "FiveLast", 5, 5, 5.0, "fiveUnityID");

		comparator = new StudentGPAComparator();
	}
	
	/**
	 * Test that see if the comparator works correctly
	 */
	@Test
	public void compareTest() {
		assertTrue(comparator.compare(sTwo, sOne) > 0);
		assertFalse(comparator.compare(sOne, sTwo) < 0);
		assertTrue(comparator.compare(sThree, sTwo) > 0);
		assertFalse(comparator.compare(sTwo, sThree) < 0);
		assertTrue(comparator.compare(sFour, sThree) > 0);
		assertFalse(comparator.compare(sThree, sFour) < 0);
		assertTrue(comparator.compare(sFive, sFour) > 0);
		assertFalse(comparator.compare(sFour, sFive) < 0);
	}

}
