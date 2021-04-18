// package Assignment;

import java.util.*;

public class StudentForms {
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);

		do {
			System.out.println("Enter '1' To Continue And '0' To Exit System");
			int c = sc.nextInt();
			if (c == 0)
				break;
			char choice;
			System.out.println("What type of user you are? Enter the 'A' For Administrator 'S' For Student");
			choice = sc.next().charAt(0);
			if (choice == 'A') {
				Administrator Ad = new Administrator();
			} else if (choice == 'S') {
				Student St = new Student();
			} else {
				System.out.println("Enter valid choice!!!!!");
			}
		} while (true);

	}
}
