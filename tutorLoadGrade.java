import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class tutorLoadGrade {

	ArrayList<String> student = new ArrayList<String>();
	ArrayList<String> course = new ArrayList<String>();
	Map<String, LinkedHashMap<String, String>> grade = new LinkedHashMap<String, LinkedHashMap<String, String>>();

	public void displayCourse() {
		for (String c : course) {
			System.out.println(c);
		}
	}

	public void displayCourseGrade(String c) {
		Map<String, String> session = grade.get(c);
		System.out.println("Student, Grades");
		for (String s : student) {
			System.out.println(s + ", " + session.get(s));
		}
	}

	public void displayAllCourseGrade() {
		for (String c : course) {
			System.out.println(c);
			Map<String, String> session = grade.get(c);
			System.out.println("Student, Grades");
			for (String s : student) {
				System.out.println(s + ", " + session.get(s));
			}
			System.out.println();
		}
	}

	public void displayStudent() {
		for (String s : student) {
			System.out.println(s);
		}
	}

	public void mInsertGrade() {
		Map<String, String> session = new LinkedHashMap<String, String>();
		Scanner sc = new Scanner(System.in);

		System.out.println("What document are you grading?");
		String doc = sc.nextLine();

		for (String s : student) {
			System.out.println("What is the grade for " + s + "? ");
			String mark = sc.nextLine();

			session.put(s, mark);
		}

		int i = 0;
		while (i == 0) {
			System.out.println("Is there any other student you want to add?");
			String ans = sc.nextLine();

			if (ans.equals("Yes") || ans.equals("yes") || ans.equals("y")
					|| ans.equals("Y")) {
				System.out.println("Name of student?");
				ans = sc.nextLine();

				System.out.println("What is the grade for " + ans + "?");
				String g = sc.nextLine();

				session.put(ans, g);
			} else {
				i++;
			}
		}

		grade.put(doc, (LinkedHashMap<String, String>) session);
		System.out.println("The detail have been stored!");
	}

	public void editCourse(String c, String s, String update) {
		Map<String, String> session = grade.get(c);
		session.remove(s);
		session.put(s, update);

		grade.remove(c);
		grade.put(c, (LinkedHashMap<String, String>) session);
		System.out.println("Update Successful");

	}

	public void deleteCourse(String c) {
		grade.remove(c);
		course.remove(c);
	}

	public boolean exportStudentGrade(String stud) {
		String url = "/Users/Derrick/Desktop/School/PSD3/forTest/" + stud
				+ ".csv";
		try {

			FileWriter writer = new FileWriter(url);

			for (String c : course) {
				Map<String, String> session = grade.get(c);
				for (String name : student) {

					if (session.containsKey(name)) {
						if (name.equals(stud)) {
							writer.append(name + "," + session.get(name) + ","
									+ c + "\n");
						}
					}
				}
				writer.append("\n");
			}

			// generate whatever data you want

			writer.flush();
			writer.close();
			System.out.println("The grade for has been successfully exported to " + url);
			return true;

		} catch (IOException e) {
			System.out.println("Read File Fail..");
			return false;

		}

	}

	public boolean exportAllGrade() {
		String url = "/Users/Derrick/Desktop/School/PSD3/forTest/all.csv";
		try {

			FileWriter writer = new FileWriter(url);

			for (String c : course) {
				Map<String, String> session = grade.get(c);
				writer.append("Course: " + c + "\n");
				for (String name : student) {
					if (session.containsKey(name)) {
						writer.append(name + "," + session.get(name) + "," + c
								+ "\n");
					}
				}
				writer.append("\n");
			}

			// generate whatever data you want

			writer.flush();
			writer.close();
			System.out.println("The grade for has been successfully exported to " + url);
			return true;
		} catch (IOException e) {
			System.out.println("Read File Fail..");
			return false;

		}

	}

	public boolean exportGrade(String courseName) {

		String url = "/Users/Derrick/Desktop/School/PSD3/forTest/" + courseName
				+ ".csv";
		try {

			FileWriter writer = new FileWriter(url);
			Map<String, String> session = grade.get(courseName);

			for (String name : student) {
				if (session.containsKey(name)) {
					writer.append(name + "," + session.get(name) + "\n");
				}
			}

			// generate whatever data you want

			writer.flush();
			writer.close();
			System.out.println("The grade for " + courseName + " has been successfully exported to " + url);
			return true;
		} catch (IOException e) {
			System.out.println("Read File Fail..");
			return false;

		}
	}

	public boolean importGrade(String courseName, String url) {
		Map<String, String> session = new LinkedHashMap<String, String>();

		BufferedReader br = null;

		try {
			String sCurrentLine;

			br = new BufferedReader(new FileReader(url));

			while ((sCurrentLine = br.readLine()) != null) {

				String[] temp = sCurrentLine.split(",");

				if (!student.contains(temp[0])) {
					System.out.println("Student Not Found");
					System.out.println("Do you want to add " + temp[0]
							+ " into the student database? yes | no");
					Scanner scan = new Scanner(System.in);
					String ans = scan.nextLine();

					if (ans.equals("Yes") || ans.equals("yes")
							|| ans.equals("y") || ans.equals("Y")) {

						student.add(temp[0]);
						System.out.println("Student added to database");
						session.put(temp[0], temp[1]);

					} else {
						System.out.println("Student grade not added");
					}

				} else {
					session.put(temp[0], temp[1]);
					System.out.println(temp[0] + " added");
				}
			}

			course.add(courseName);
			grade.put(courseName, (LinkedHashMap<String, String>) session);
			System.out.println("The grade has been entered into the system!");

			return true;
		} catch (IOException e) {

			System.out.println("File read fail");
			return false;

		} finally {

			try {
				if (br != null)
					br.close();

			} catch (IOException ex) {

				ex.printStackTrace();
				return false;
			}
		}

	}

	public void select(int choice) {
		Scanner scan = new Scanner(System.in);
		String ans = null;
		switch (choice) {
		case 1:
			System.out.println("Enter the file path you want to import?");
			ans = scan.nextLine();

			System.out.println("Enter the name of the course?");
			String cs = scan.nextLine();

			importGrade(cs, ans);
			printUI();
			break;

		case 2:
			mInsertGrade();
			printUI();
			break;

		case 3:
			displayCourse();
			System.out.println("Enter the course you want to view!");
			ans = scan.nextLine();

			displayCourseGrade(ans);
			printUI();
			break;

		case 4:
			displayAllCourseGrade();
			printUI();
			break;
			
		case 5:
			exportAllGrade();
			printUI();
			break;

		case 6:
			displayStudent();
			System.out.println("Which student's grade do you want to export?");
			ans = scan.nextLine();
			
			exportStudentGrade(ans);
			printUI();
			break;
			
		case 7:
			displayCourse();
			System.out.println("Which course grade do you want to export?");
			ans = scan.nextLine();
			
			exportGrade(ans);
			printUI();
			break;
			
		case 8: 
			displayCourse();
			System.out.println("Which course do you want to edit?");
			String c = scan.nextLine();
			
			displayStudent();
			System.out.println("Which student do you want to edit the grade?");
			String s = scan.nextLine();
			
			System.out.println("What grade is he suppose to have?");
			ans = scan.nextLine();
			
			editCourse(c, s, ans);
			printUI();
			break;
			
		case 9:
			displayCourse();
			System.out.println("Which course do you want to delete?");
			ans = scan.nextLine();
			
			deleteCourse(ans);
			printUI();
			break;
			
		default:
			System.out.println("End Program!");
			break;

		}

	}

	public boolean printUI() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Please choose something to do");
		System.out.println("1.\t Import single grades");
		System.out.println("2.\t Manually Insert Grades");
		System.out.println("3.\t View Single Course Grades");
		System.out.println("4.\t View All Course Grades");
		System.out.println("5.\t Export All grades");
		System.out.println("6.\t Export student grades");
		System.out.println("7.\t Export subject grades");
		System.out.println("8.\t Edit student grades");
		System.out.println("9.\t Delete grades");
		System.out.println("0.\t End");

		System.out.println("Select the choice: ");
		int choice = scan.nextInt();
		select(choice);

		return false;
	}

}
