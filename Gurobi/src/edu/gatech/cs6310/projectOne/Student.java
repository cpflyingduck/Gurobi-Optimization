package edu.gatech.cs6310.projectOne;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Student {
	private final File file;

	// number of classes offered in total
	private static final int Nc = 18;
	// total number of semester
	private static final int Nsem = 12;

	public Student(File file) throws Exception {
		this.file = file;
	}

	// this method returns the total number of students in the file
	public int numberOfStudents() {

		Scanner r = readFile();
		// skip header
		r.nextLine();

		List<Integer> studentList = new ArrayList<Integer>();

		while (r.hasNextLine()) {
			// read the next line
			String s = r.nextLine();

			// split at the comma
			String[] s1 = s.split(",");

			int i = Integer.parseInt(s1[0]); // student

			if (!studentList.contains(i)) {
				studentList.add(i);
			}
		}
		r.close();
		return studentList.size();
	}

	/*
	 * returns the 3d array of student, class and semester to use in GUROBI
	 * solver
	 */
	public int[][][] studentClassesSemester() {

		int Ns = numberOfStudents();

		// 3d array for student, course and semester
		int A[][][] = new int[Ns][Nc][Nsem];

		Scanner r = readFile();
		// skip header
		r.nextLine();

		while (r.hasNextLine()) {

			// read the next line
			String s = r.nextLine();

			// split at the comma
			String[] s1 = s.split(",");

			/*
			 * add the student number, course id and semester id to the 3 d
			 * array and assign value of 1 for the class is being taken
			 */

			int i = Integer.parseInt(s1[0]); // student
			int j = Integer.parseInt(s1[1]); // course id
			int k = Integer.parseInt(s1[2]); // semester id

			// subtract 1 so as to start counting array from 0
			A[i - 1][j - 1][k - 1] = 1;
		}
		r.close();
		return A;
	}

	// this method returns the student and course matrix
	public int[][] studentClasses() {

		int Ns = numberOfStudents();

		// 2d array for student and course
		int B[][] = new int[Ns][Nc];

		Scanner r = readFile();
		// skip header
		r.nextLine();

		while (r.hasNextLine()) {

			// read the next line
			String s = r.nextLine();

			// split at the comma
			String[] s1 = s.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)", -1);

			/*
			 * add the student number and course if for 2 d array and assign
			 * value of 1 for the class is being taken
			 */

			int i = Integer.parseInt(s1[0]); // student
			int j = Integer.parseInt(s1[1]); // course id

			/*
			 * subtract 1 so as to start counting array from 0 this matrix gives
			 * the As matrix as in the note
			 */

			B[i - 1][j - 1] = 1;
		}
		r.close();
		return B;
	}

	// this method reads the file and returns the content in it
	private Scanner readFile() {
		try {
			Scanner reader = new Scanner(this.file);
			return reader;
		} catch (FileNotFoundException ex) {
			return null;
		}
	}
}
