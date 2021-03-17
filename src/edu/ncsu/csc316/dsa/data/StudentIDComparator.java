package edu.ncsu.csc316.dsa.data;

import java.util.Comparator;

/**
 * Comparator to compare students based on id number
 * @author Dr. King
 *
 */
public class StudentIDComparator implements Comparator<Student> {

	/**
	 * StudentIDComparator Constructor
	 */
	public StudentIDComparator() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Compares students based on id number in ascending order
	 */
	@Override
	public int compare(Student one, Student two) {
		double oneID = one.getId();
		double twoID = two.getId();
		if (oneID > twoID) {
			return 1;
		} else if (oneID < twoID) {
			return -1;
		}
		return 0;
	}
}
