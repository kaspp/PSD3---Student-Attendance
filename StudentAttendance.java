import java.io.BufferedReader;
import java.io.Console;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class StudentAttendance {

	ArrayList<String> student = new ArrayList<String>();
	ArrayList<String> coursen = new ArrayList<String>();
	Map<String, LinkedHashMap<String, String>> course = new LinkedHashMap<String, LinkedHashMap<String, String>>();



	public void select(int s) {
		Scanner scan = new Scanner(System.in);
		switch (s) {
		case 1:	
			System.out.println("Enter the url for the file you want to import");
			String url = scan.nextLine();
			
			System.out.println("Enter the name: ");
			String name = scan.nextLine();
			
			System.out.println("Reading Attendance....");
			
			if (!ReadAttendance(name, url)) {
				System.out.println("Read fail");
			}
		
			printUI();
			break;
			
		case 2:
			
			displayAllCourse();
			System.out.println("Which Course are you editing?");
			String coursea = scan.nextLine();
			
			getAllStudent();
			System.out.println("Which Student ");
			String studn = scan.nextLine();
			
			System.out.println("What do you want to update");
			String update = scan.nextLine();
			
			editAttendance(studn, coursea, update);
			printUI();
			break;
			
		case 3:
			
			displayAllCourse();
			System.out.println("Which course do you want to delete?");
			coursea = scan.nextLine();
			
			deleteSession(coursea);
			
			printUI();
			break;

		case 4:
			getAllStudent();
			printUI();
			break;
		
			
		case 5: 
			displayAllCourse();
			printUI();
			break;
			
		case 6:
			displayAllCourse();
			System.out.println("Which course is the student from?");
			coursea = scan.nextLine();
			
			displayAttendance(coursea);
			System.out.println("Which student do you want to edit?");
			studn = scan.nextLine();
			
			markLate(coursea, studn);
			displayAttendance(coursea);
			printUI();
			break;
			
		default:
			System.out.println("Exit program");
			break;
		}
	}

	public void printUI() {

		Scanner scan = new Scanner(System.in);
		System.out.println("Please choose something to do");
		System.out.println("1.\t Import attendance");
		System.out.println("2.\t Edit attendance");
		System.out.println("3.\t Delete attendance");
		System.out.println("4.\t Show student");
		System.out.println("5.\t Show sessions");
		System.out.println("6.\t Mark Late Student");
		System.out.println("0.\t Exit Program");

		System.out.println("Select the choice: ");
		int choice = scan.nextInt();
		select(choice);
		//String choice = c.readLine("Select the choice: ");
		//select(Integer.parseInt(choice));
	}

	public void markLate(String coursea, String studentn) {
		Map<String, String> session = course.get(coursea);

		session.remove(studentn);
		session.put(studentn, "Late");

		course.remove(coursea);
		course.put(coursea, (LinkedHashMap<String, String>) session);
	}

	public void displayAttendance(String courseName) {
		Map<String, String> temp = course.get(courseName);

		for (String name : student) {
			System.out.println(name + " is " + temp.get(name) + " on "
					+ courseName);
		}

	}

	public void displayAllCourse() {
		for (String temp : coursen) {
			System.out.println(temp);
		}
	}

	public void editAttendance(String studentn, String coursea, String update) {

		Map<String, String> session = course.get(coursea);
		session.remove(studentn);
		session.put(studentn, update);
		course.remove(coursea);
		course.put(coursea, (LinkedHashMap<String, String>) session);
	}

	public void deleteSession(String coursea) {
		if (course.containsKey(coursea)) {
			course.remove(coursea);
			coursen.remove(coursea);
			System.out.println("Removed!");
		} else {
			System.out.println("Session not found");
		}
	}

	public void initStudent() {
		for (int i = 1; i < 11; i++) {
			if (i < 10)
				if (!student.contains("00" + i))
					student.add("00" + i);
			else
				if (!student.contains("0" + i))
					student.add("0" + i);
		}
	}

	public boolean ReadAttendance(String courseName, String url) {
		Map<String, String> session = new LinkedHashMap<String, String>();
		
		//initStudent();
		
		BufferedReader br = null;

		try {
			String sCurrentLine;

			br = new BufferedReader(new FileReader(url));

			while ((sCurrentLine = br.readLine()) != null) {

				String[] temp = sCurrentLine.split(",");

				if (student.contains(temp[0])) {

					session.put(temp[0], temp[1]);
					System.out.println(temp[0] + " added");
				
				} else {
					
					System.out.println("Student Not Found");
					student.add(temp[0]);
					System.out.println("Do you want to add " + temp[0] + " into the student database? yes | no");
					Scanner scan = new Scanner(System.in);
					String ans = scan.nextLine();
					
					if (ans.equals("Yes") || ans.equals("yes") || ans.equals("y") || ans.equals("Y")) {
					System.out.println("Student added to database");
					session.put(temp[0], temp[1]);
					System.out.println(temp[0] + " added");
					} else {
						System.out.println("Student not added to the database, nor will he be added into this session. " +
								"Run the document again.");
					}
					
				}

			}
			coursen.add(courseName);
			course.put(courseName, (LinkedHashMap<String, String>) session);
			System.out.println("Attendance Added!");
			System.out.println();
			System.out.println();
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

	public void getAllStudent() {
		for (String name : student) {
			System.out.println("Student : " + name);
		}
	}

}
