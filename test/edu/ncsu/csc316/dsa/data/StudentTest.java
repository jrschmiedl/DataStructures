/**
 * 
 */
package edu.ncsu.csc316.dsa.data;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;

/**
 * Test Class for Student.java
 * @author jrschmie Jacob Schmiedl
 */
public class StudentTest {

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
	/** Student Six */
	private Student sSix;


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
		sSix = new Student("FiveFirst", "FiveLast", 4, 5, 5.0, "fiveUnityID");
	}
	/**
	 * Testing first setter
	 */
	@Test
	public void testSetFirst() {
		sOne.setFirst("newOne");
		assertEquals("newOne", sOne.getFirst());
		sTwo.setFirst("newTwo");
		assertEquals("newTwo", sTwo.getFirst());
		sThree.setFirst("newThree");
		assertEquals("newThree", sThree.getFirst());
		sFour.setFirst("newFour");
		assertEquals("newFour", sFour.getFirst());
		sFive.setFirst("newFive");
		assertEquals("newFive", sFive.getFirst());
	}

	/**
	 * Testing last setter
	 */
	@Test
	public void testSetLast() {
		sOne.setLast("newOne");
		assertEquals("newOne", sOne.getLast());
		sTwo.setLast("newTwo");
		assertEquals("newTwo", sTwo.getLast());
		sThree.setLast("newThree");
		assertEquals("newThree", sThree.getLast());
		sFour.setLast("newFour");
		assertEquals("newFour", sFour.getLast());
		sFive.setLast("newFive");
		assertEquals("newFive", sFive.getLast());
	}

	/**
	 * Testing id setter
	 */
	@Test
	public void testSetId() {
		sOne.setId(100);
		assertEquals(100, sOne.getId());
	}

	/**
	 * Testing gpa setter
	 */
	@Test
	public void testSetGpa() {
		sOne.setGpa(3.51);
		assertEquals(3.51, sOne.getGpa(), 0.001);
	}
	
	/**
	 * Testing unity id setter
	 */
	@Test
	public void testSetUnityID() {
		sOne.setUnityID("oneUnity");
		assertEquals("oneUnity", sOne.getUnityID());
	}

	/**
	 * Tests the compareTo method
	 */
	@Test
	public void testCompareTo() {
		assertTrue(sOne.compareTo(sTwo) < 0);
		assertTrue(sTwo.compareTo(sOne) > 0);
		assertTrue(sOne.compareTo(sOne) == 0);
		assertTrue(sTwo.compareTo(sTwo) == 0);
		assertTrue(sFive.compareTo(sSix) > 0);
	}
	
	/**
	 * Testing the toString method
	 */
	@Test
	public void testToString() {
		assertEquals("Student [first=OneFirst, last=OneLast, id=1, creditHours=1, gpa=1.0, unityID=oneUnityID]", sOne.toString());
	}
	
	/**
	 * Testing the hashcode method
	 */
	@Test
	public void testHashCode() {
		assertNotNull(sOne.hashCode());
	}
	
	/**
	 * Tests the equals method
	 */
	@Test
	public void testEquals() {
		assertTrue(sOne.equals(sOne));
		assertFalse(sOne.equals(sTwo));
		assertEquals(1, sOne.getCreditHours());
	}

}
