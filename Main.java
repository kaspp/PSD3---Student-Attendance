import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		StudentAttendance sa = new StudentAttendance();
		tutorLoadGrade tg = new tutorLoadGrade();
		int i = 1;
		Scanner sc = new Scanner(System.in);

		while (i != 0) {
			System.out.println("Please choose what you want to do?");
			System.out.println("1.\t Grades");
			System.out.println("2.\t Attendance");
			System.out.println("3.\t Exit");

			String ans = sc.nextLine();
			if (isInteger(ans)) {
				
				switch (Integer.parseInt(ans)) {
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

	public static boolean isInteger(String str) {
		int size = str.length();

		for (int i = 0; i < size; i++) {
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}

		return size > 0;
	}

}
