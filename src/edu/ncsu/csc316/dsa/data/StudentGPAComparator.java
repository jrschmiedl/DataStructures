/**
 * 
 */
package edu.ncsu.csc316.dsa.data;

import java.util.Comparator;

/**
 * Comparator for comparing Students based on GPA
 * @author Dr. King
 *
 */
public class StudentGPAComparator implements Comparator<Student> {

	/**
	 * StudentGPAComparator Constructor
	 */
	public StudentGPAComparator() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Compares students based on GPA in descending order
	 */
	@Override
	public int compare(Student one, Student two) {
		double oneGPA = one.getGpa();
		double twoGPA = two.getGpa();
		if (oneGPA > twoGPA) {
			return 1;
		} else if (oneGPA > twoGPA) {
			return -1;
		}
		return 0;
	}
}

