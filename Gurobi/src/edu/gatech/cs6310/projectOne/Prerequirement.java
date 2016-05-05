package edu.gatech.cs6310.projectOne;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Prerequirement {

	private final File file;

	// number of classes offered in total
	private final int Nc = 18;

	// 2d array for course dependency
	int Ap[][] = new int[Nc][Nc];

	public Prerequirement(File file) throws Exception {
		this.file = file;
	}

	/*
	 * returns the 2d array of course and pre-req.
	 */
	public int[][] courseDependency() {

		Scanner r = readFile();
		// skip header
		r.nextLine();

		while (r.hasNextLine()) {

			// read the next line
			String s = r.nextLine();

			// split at the comma
			String[] s1 = s.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)", -1);

			/*
			 * add the course id to a row and column to the 2d array and assign
			 * value of 1 for the class if it being taken
			 */

			int j = Integer.parseInt(s1[0]); // course id for the column
			int i = Integer.parseInt(s1[1]); // course id for the rows

			// System.out.println(i);
			// System.out.println(j);

			// subtract 1 so as to start counting array from 0
			Ap[i - 1][j - 1] = 1;
		}
		r.close();
		return Ap;
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
