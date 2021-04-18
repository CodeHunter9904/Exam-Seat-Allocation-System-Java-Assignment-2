// package Assignment;

import java.util.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

class marksCompare implements Comparator<Student> {
	public int compare(Student s1, Student s2) {
		return s1.rollNo - s2.rollNo;
	}
}

public class Student {
	public static File f = new File("Form.txt");
	protected String name, branch;
	protected int rollNo, sem;
	Scanner sc = new Scanner(System.in);

	Student() throws Exception {
		System.out.println("Press '1' For Enroll '2' For Check Allotment");
		int c = sc.nextInt();
		if (c == 1) {
			FillDetails();
		} else if (c == 2) {
			Path path = Paths.get("Form.txt");

			if (Files.exists(path)) {
				checkInfo();
			} else {
				System.out.println("No One Enrolled Yet!!!!");
				return;
			}
		} else {
			System.out.println("Enter Valid Choice!!!!!!");

		}
		SortTextFile();
	}

	Student(int rollNo, String name, String branch, int sem) {
		this.rollNo = rollNo;
		this.name = name;
		this.branch = branch;
		this.sem = sem;
	}

	static void writefile(String name, String branch, int rollNo, int sem) throws Exception {
		File f = new File("Form.txt");

		FileWriter fr = new FileWriter(f, true);
		BufferedWriter bw = new BufferedWriter(fr);
		bw.write(rollNo + "\t" + name + "\t" + branch + "\t" + sem + "\n");

		bw.close();
		fr.close();
	}

	void FillDetails() throws Exception {
		String name, branch;
		int rollNo, sem;
		sc.nextLine();
		System.out.println("Enter Your Name:");
		name = sc.nextLine();
		System.out.println("Enter Your Branch:");
		branch = sc.nextLine();
		System.out.println("Enter Your RollNo:");
		rollNo = sc.nextInt();
		System.out.println("Enter Your Semester:");
		sem = sc.nextInt();

		writefile(name, branch, rollNo, sem);
	}

	void checkInfo() throws Exception {
		System.out.println("Enter your RollNumber :");
		int Rnum = sc.nextInt();
		search(Rnum);

	}

	void search(int Rnum) throws Exception {
		int count = 0, f = 0;
		int res;

		BufferedReader br = new BufferedReader(new FileReader("Form1.txt"));
		String line;
		while ((line = br.readLine()) != null) {
			String[] Line1 = line.split("\t");
			count++;
			if (Rnum == Integer.valueOf(Line1[0])) {
				f = 1;
				break;
			}
		}
		if (f == 0) {
			System.out.println("You Are Not Enrolled !! Please Enroll!!!");
		} else {
			if (Administrator.capacity == 0) {
				System.out.println("Sorry ! Allotment Is Not Done Yet!!!!");
			} else {
				if (count % Administrator.capacity == 0) {
					res = count / Administrator.capacity;
					System.out.println("Your Allocation is In Class " + res);
				} else {
					res = (count / Administrator.capacity) + 1;
					System.out.println("Your Allocation is In Class " + res);
				}
			}

		}
		br.close();
	}

	void SortTextFile() throws Exception {
		BufferedReader reader = new BufferedReader(new FileReader("Form.txt"));
		ArrayList<Student> studentRecords = new ArrayList<Student>();
		String currentLine = reader.readLine();

		while (currentLine != null) {
			String[] studentDetail = currentLine.split("\t");

			int rollNo = Integer.valueOf(studentDetail[0]);
			String name = studentDetail[1];
			String branch = studentDetail[2];
			int sem = Integer.valueOf(studentDetail[3]);
			studentRecords.add(new Student(rollNo, name, branch, sem));
			currentLine = reader.readLine();
		}

		Collections.sort(studentRecords, new marksCompare());
		BufferedWriter writer = new BufferedWriter(new FileWriter("Form1.txt"));
		for (Student student : studentRecords) {

			writer.write(student.rollNo + "\t" + student.branch + "\t" + student.sem + "\t" + student.name);

			writer.newLine();
		}

		reader.close();
		writer.close();
	}

}