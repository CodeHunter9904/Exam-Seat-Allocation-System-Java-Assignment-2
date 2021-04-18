// package Assignment;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Administrator {

	Scanner sc = new Scanner(System.in);
	private String pass = "1234";
	private String userId = "Head001";
	int count = 0;
	int noOfclass;
	static public int capacity = 0;

	Administrator() throws Exception {

		String userId1;
		String pass1;
		System.out.println("Enter User Id::");
		userId1 = sc.nextLine();
		System.out.println("Enter Password::");
		pass1 = sc.nextLine();
		boolean result = login(pass1, userId);
		if (result) {
			lable: do {

				int info;
				System.out.println("1.See Enrolled Student List");
				System.out.println("2.Allocate The class");
				System.out.println("3.See Allocated Class Details ");
				System.out.println("4.Exit ");

				info = sc.nextInt();
				if (info == 2) {
					Path path = Paths.get("Form.txt");

					if (Files.exists(path)) {
						getInfo();
						allocateClass();
					} else {
						System.out.println("Not Enrolled Any one");
					}
				} else if (info == 1) {
					Path path = Paths.get("Form1.txt");

					if (Files.exists(path)) {
						showInfo();
					} else {
						System.out.println("Not Enrolled Any one");
					}
				} else if (info == 3) {
					Path path = Paths.get("Form2.txt");

					if (Files.exists(path)) {
						seeAllocatedDetails();
					} else {
						System.out.println("Allocation is Not Done Yet!!!");
						return;
					}
				} else if (info == 4) {
					break;
				} else {
					System.out.println("Enter Valid Choice!!!!!!");
					continue lable;
				}
			} while (true);
		} else {
			System.out.println("Your user ID or Password is Incorrect!!!!!!!!");
		}
	}

	boolean login(String pass, String userId) {
		if (userId.equals(this.userId) && pass.equals(this.pass)) {
			return true;
		}
		return false;
	}

	void showInfo() throws IOException {
		System.out.println("Student Deatails.....");
		System.out.println("RollNo\tBranch\tSem\tName Of Student");
		System.out.println("------------------------------------------");
		BufferedReader br = new BufferedReader(new FileReader("Form1.txt"));
		String line;
		while ((line = br.readLine()) != null) {
			System.out.println(line);
			count++;
		}
		System.out.println();
		System.out.println("Number Of Student Enroll ==" + count);
		br.close();
	}

	void getInfo() {
		System.out.println("enter no of classes : ");
		noOfclass = sc.nextInt();
		System.out.println("enter capicity of each class : ");
		capacity = sc.nextInt();
	}

	void allocateClass() throws IOException {

		int i, j, classCount = 1;
		if (noOfclass * capacity < count) {
			System.out.println("You Don't Have Enough Capacity Of Class Plaese Update !!! ");
			return;
		}
		BufferedReader br = new BufferedReader(new FileReader("Form1.txt"));

		String currentLine = br.readLine();
		File f = new File("Form2.txt");
		FileWriter fr = new FileWriter(f, true);
		BufferedWriter bw = new BufferedWriter(fr);

		PrintWriter writer = new PrintWriter(f);
		writer.print("");
		writer.close();

		for (j = 1; j <= noOfclass; j++) {
			bw.write("Class" + classCount + ":" + "\n\n");
			for (i = 1; i <= capacity; i++) {
				if (currentLine == null) {
					break;
				}
				String[] studentDetail = currentLine.split("\t");

				int rollNo = Integer.valueOf(studentDetail[0]);
				String name = studentDetail[3];
				String branch = studentDetail[1];
				int sem = Integer.valueOf(studentDetail[2]);

				bw.write(rollNo + "\t" + branch + "\t" + sem + "\t" + name + "\n");
				currentLine = br.readLine();

			}
			classCount++;
			bw.write("\n");
		}
		bw.close();
		br.close();

	}

	void seeAllocatedDetails() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("Form2.txt"));
		String currentLine = br.readLine();
		while (currentLine != null) {
			System.out.println(currentLine);
			currentLine = br.readLine();
		}
		System.out.println();
	}

}