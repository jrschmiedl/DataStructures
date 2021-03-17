/**
 * 
 */
package edu.ncsu.csc316.dsa.sorter;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;

/**
 * Test Class for RadixSorter.java
 * @author jrschmie Jacob Schmiedl
 */
public class RadixSorterTest {

	/**
	 * Tests the Radix Sorter
	 */
	@Test
	public void testRadixSorter() {
		Student sOne = new Student("OneFirst", "OneLast", 1, 1, 1.0, "oneUnityID");
		Student sTwo = new Student("TwoFirst", "TwoLast", 2, 2, 2.0, "twoUnityID");
		Student sThree = new Student("ThreeFirst", "ThreeLast", 3, 3, 3.0, "threeUnityID");
		Student sFour = new Student("FourFirst", "FourLast", 4, 4, 4.0, "fourUnityID");
		Student sFive = new Student("FiveFirst", "FiveLast", 5, 5, 5.0, "fiveUnityID");
		Student[] original = { sTwo, sOne, sFour, sThree, sFive };
		RadixSorter<Student> r = new RadixSorter<Student>();
		r.sort(original);
		assertEquals("OneFirst", original[0].getFirst());
		assertEquals("TwoFirst", original[1].getFirst());
		assertEquals("ThreeFirst", original[2].getFirst());
		assertEquals("FourFirst", original[3].getFirst());
		assertEquals("FiveFirst", original[4].getFirst());
	}
}
