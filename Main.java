import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		StudentAttendance sa = new StudentAttendance();
		tutorLoadGrade tg = new tutorLoadGrade();
		int i = 1;
		Scanner sc = new Scanner(System.in);
		/*
		 * tg.importGrade("PSD4",
		 * "/Users/Derrick/Desktop/School/PSD3/forTest/PSD.csv");
		 * tg.importGrade("ALG4",
		 * "/Users/Derrick/Desktop/School/PSD3/forTest/PSD.csv");
		 * tg.importGrade("PL4",
		 * "/Users/Derrick/Desktop/School/PSD3/forTest/PSD.csv");
		 * //sa.printUI();
		 * 
		 * tg.exportAllGrade(); tg.exportStudentGrade("Derrick");
		 */
		while (i != 0) {
			System.out.println("Please choose what you want to do?");
			System.out.println("1.\t Grades");
			System.out.println("2.\t Attendance");
			System.out.println("3.\t Exit");

			int ans = sc.nextInt();
			switch (ans) {
			case 1:
				tg.printUI();
				break;

			case 2:
				sa.printUI();
				break;
				
			case 3:
				i = 0;
				System.out.println("Thank you and Good bye!");
				break;

			}

		}
	}

}
