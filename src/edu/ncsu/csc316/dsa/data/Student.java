/**
 * 
 */
package edu.ncsu.csc316.dsa.data;

/**
 * A student is comparable and identifiable.
 * Students have a first name, last name, id number, 
 * number of credit hours, gpa, and unityID.
 * 
 * @author Dr. King
 *
 */
public class Student implements Comparable<Student>, Identifiable {

	/** First name of the student */
	private String first;
	/** Last name of the student */
	private String last;
	/** The id number associated with the student */
	private int id;
	/** How many credit hours the student has */
	private int creditHours;
	/** The student's gpa */
	private double gpa;
	/** The unity id associated with the student */
	private String unityID;
	
	/**
	 * The Constructor for Student
	 * @param first the first name of the student
	 * @param last the last name of the student 
	 * @param id the id that is associated with the student
	 * @param creditHours how many credit hours the student has
	 * @param gpa the gpa the student has
	 * @param unityID the unityID that is associated with the student
	 */
	public Student(String first, String last, int id, int creditHours, double gpa, String unityID) {
		setFirst(first);
		setLast(last);
		setId(id);
		setCreditHours(creditHours);
		setGpa(gpa);
		setUnityID(unityID);
	}

	/**
	 * Getter for the first name
	 * @return the first
	 */
	public String getFirst() {
		return first;
	}

	/**
	 * Setter for the first name
	 * @param first the first to set
	 */
	public void setFirst(String first) {
		this.first = first;
	}

	/**
	 * Getter for the last name
	 * @return the last
	 */
	public String getLast() {
		return last;
	}

	/**
	 * Setter for the last name
	 * @param last the last to set
	 */
	public void setLast(String last) {
		this.last = last;
	}

	/**
	 * Getter for the id of the student
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Setter for the id of the student
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Getter for the credit hours that the student has
	 * @return the creditHours
	 */
	public int getCreditHours() {
		return creditHours;
	}

	/**
	 * Setter for the credit hours that the student has
	 * @param creditHours the creditHours to set
	 */
	public void setCreditHours(int creditHours) {
		this.creditHours = creditHours;
	}

	/**
	 * Getter for the student's gpa
	 * @return the gpa
	 */
	public double getGpa() {
		return gpa;
	}

	/**
	 * Setter for the student's gpa
	 * @param gpa the gpa to set
	 */
	public void setGpa(double gpa) {
		this.gpa = gpa;
	}

	/**
	 * Getter for the student's unity id
	 * @return the unityID
	 */
	public String getUnityID() {
		return unityID;
	}

	/**
	 * Setter for the student's unity id
	 * @param unityID the unityID to set
	 */
	public void setUnityID(String unityID) {
		this.unityID = unityID;
	}

	/**
	 * Creates the hashCode for the object
	 * @return the hashCode that belongs to the student.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + creditHours;
		result = prime * result + ((first == null) ? 0 : first.hashCode());
		long temp;
		temp = Double.doubleToLongBits(gpa);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + id;
		result = prime * result + ((last == null) ? 0 : last.hashCode());
		result = prime * result + ((unityID == null) ? 0 : unityID.hashCode());
		return result;
	}

	/**
	 * Equals method for the object
	 * @param obj an object that is being tested
	 * @return boolean whether they equal each other or not
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Student) {
			Student s = (Student) obj;
			if (first.equals(s.getFirst()) && last.equals(s.getLast()) && id == s.getId()) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String toString() {
		return "Student [first=" + first + ", last=" + last + ", id=" + id + ", creditHours=" + creditHours + ", gpa="
				+ gpa + ", unityID=" + unityID + "]";
	}

	/**
	 * Compare to method for the student object
	 * @param o the Student that is being compared.
	 * @return an integer that is used to evaluate the student.
	 */
	@Override
	public int compareTo(Student o) {
		if (last.compareTo(o.getLast()) == 0 && first.compareTo(o.getFirst()) == 0 && id == o.getId()) {
			return 0;
		} else if (last.compareTo(o.getLast()) != 0) {
			if (last.compareTo(o.getLast()) < 0) {
				return -1;
			}
			return 1;
		} else if (first.compareTo(o.getFirst()) != 0) {
			if (first.compareTo(o.getFirst()) < 0) {
				return -2;
			}
			return 2;
		} else {
			if (id < o.getId()) {
				return -3;
			}
			return 3;
		}
	}
	
	
}

