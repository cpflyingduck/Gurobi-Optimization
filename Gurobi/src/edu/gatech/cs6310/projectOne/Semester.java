package edu.gatech.cs6310.projectOne;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class Semester {

	private final File file;

	// number of semesters
	private final int Nsem = 12;
	// number of classes
	private final int Nc = 18;

	// 2d array for course and their offerings
	int Ac[][] = new int[Nc][Nsem];

	public Semester(File file) throws Exception {
		this.file = file;
	}

	/*
	 * returns the 2d array of class and semester to use in GUROBI
	 */
	public int[][] courseOffering() {

		Scanner r = readFile();
		// skip header
		r.nextLine();

		while (r.hasNextLine()) {

			// i keeps track of rows which is equal to the course id
			for (int i = 0; i < Nc; i++) {
				// read the next line
				String s = r.nextLine();
				// split at the comma
				String[] s1 = s.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)", -1);

				/*
				 * integer j represents the term. Fall (0, 3, 6, 9), summer term
				 * (2, 5, 8, 11) and spring term (1, 4, 7, 10)
				 */

				int x = Integer.parseInt(s1[3]); // fall term

				/*
				 * check if x is 1 or 0 for fall term and assign the value to
				 * the matrix accordingly (1 if x is 1)
				 */
				if (x == 1) {
					for (int j = 0; j < Nsem; j = j + 3) {
						Ac[i][j] = 1;
					}
				}

				int y = Integer.parseInt(s1[4]); //spring term

				/*
				 * check if y is 1 or 0 for spring and assign the value to the
				 * matrix accordingly (1 if y is 1)
				 */
				if (y == 1) {
					for (int j = 1; j < Nsem; j = j + 3) {
						Ac[i][j] = 1;
					}
				}

				int z = Integer.parseInt(s1[5]); //summer term

				/*
				 * check if z is 1 or 0 for summer and assign the value to the
				 * matrix accordingly (1 if z is 1)
				 */
				if (z == 1) {
					for (int j = 2; j < Nsem; j = j + 3) {
						Ac[i][j] = 1;
					}
				}
			}
		}
		r.close();
		return Ac;
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
