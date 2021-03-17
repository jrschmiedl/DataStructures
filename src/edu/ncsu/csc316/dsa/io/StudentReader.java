/**
 * 
 */
package edu.ncsu.csc316.dsa.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

import edu.ncsu.csc316.dsa.data.Student;

/**
 * StudentReader processes input CSV files that contain
 * student information.
 * 
 * Input CSV files should be in the following format:
 * 
 *    FIRST_NAME,LAST_NAME,UNITY_ID,STUDENT_ID,GPA,CREDIT_HOURS
 * 
 * @author Dr. King
 *
 */
public class StudentReader {

	/**
	 * Student Reader Constructor
	 */
	public StudentReader() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Returns the input CSV file as an array of Student objects
	 * @param filePath - the path to the input CSV file
	 * @return an array of Student objects
	 */
	public static Student[] readInputAsArray(String filePath) {
		Student[] list = new Student[10];
		try (Scanner scan = new Scanner(new FileInputStream(filePath), "UTF8")) {
			//skips
			scan.nextLine();
			int index = 0;
			// while there is still text
			while (scan.hasNextLine()) {
				// if index is larger or equal to list length
				if (index >= list.length) {
					list = Arrays.copyOf(list, list.length * 2 + 1);
				}
				// goes to the processLine method.
				list[index] = processLine(scan.nextLine());
				index++;
			}
			list = Arrays.copyOf(list, index);
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("File not found: " + e.getMessage());
		}
		return list;
	}
	
	/**
	 * Processes a single line from the input file to construct a Student.
	 * @param line - the input line from the input file
	 * @return a Student representation of the input line
	 */
	private static Student processLine(String nextLine) {
		try {
			Scanner in = new Scanner(nextLine);
			in.useDelimiter(",");
			String first = in.next();
			String last = in.next();
			String unityID = in.next();
			int id = in.nextInt();
			double gpa = in.nextDouble();
			int creditHours = in.nextInt();
			
			Student s = new Student(first, last, id, creditHours, gpa, unityID);
			in.close();
			
			return s;
		} catch (Exception e) {
			throw new IllegalArgumentException();
		}
	}
}
