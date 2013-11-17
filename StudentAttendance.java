import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

public class StudentAttendance {

	ArrayList<String> student = new ArrayList<String>();
	ArrayList<String> coursen = new ArrayList<String>();
	Map<String, LinkedHashMap<String, String>> course = new LinkedHashMap<String, LinkedHashMap<String, String>>();

	public void select(int s) throws InterruptedException {
		Scanner scan = new Scanner(System.in);

		switch (s) {
		case 1:
			

			if (!ReadAttendance()) {
				System.out.println("Read fail");
			}

			printUI();
			break;

		case 2:
			int chk = 0;
			String coursea = null;
			String studn = null;
			while (chk == 0) {
				displayAllCourse();
				System.out.println("Which Course are you editing?");
				coursea = scan.nextLine();

				if (checkCourse(coursea)) {
					chk++;

					while (chk == 1) {
						getAllStudent();
						System.out.println("Which Student ");
						studn = scan.nextLine();
						if (checkStudent(studn)) {
							chk++;

							while (chk == 2) {
								System.out
										.println("What do you want to update (Present | Absent | Late)");
								String update = scan.nextLine();

								if (update.equals("Present")
										|| update.equals("Absent")
										|| update.equals("Late")) {
									chk++;
									editAttendance(studn, coursea, update);
								}
							}
						} else {
							System.out
									.println("Wrong input, please key in again.");
						}
					}
				} else {
					System.out.println("Wrong input, please key in again.");
				}

			}
			printUI();
			break;

		case 3:
			chk = 0;
			while (chk == 0) {
				displayAllCourse();
				System.out.println("Which course do you want to delete?");
				coursea = scan.nextLine();
				if (checkCourse(coursea)) {
					chk++;
					deleteSession(coursea);
				} else {
					System.out.println("Wrong input, please key in again.");
				}
			}
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
			chk = 0;

			while (chk == 0) {
				displayAllCourse();
				System.out.println("Which course is the student from?");
				coursea = scan.nextLine();

				if (checkCourse(coursea)) {
					chk++;
					while (chk == 1) {
						displayAttendance(coursea);
						System.out
								.println("Which student do you want to edit?");
						studn = scan.nextLine();

						if (checkStudent(studn)) {
							chk++;
							markLate(coursea, studn);
							displayAttendance(coursea);
						} else {
							System.out
									.println("Wrong input, please key in again.");
						}
					}
				} else {
					System.out.println("Wrong input, please key in again.");
				}
			}
			printUI();
			break;

		case 7:

			chk = 0;

			while (chk == 0) {
				displayAllCourse();
				System.out.println("Which lesson do you want to view?");
				coursea = scan.nextLine();

				if (checkCourse(coursea)) {
					chk++;
					viewSA(coursea);
				} else {
					System.out.println("Wrong input, please key in again.");
				}
			}
			printUI();
			break;

		case 0:
			Thread t = new Thread();
			t.start();
			System.out.print("Exiting Program");
			for (int i = 0; i < 4; i++) {
				Thread.sleep(1000);
				System.out.print(".");
			}
			System.out.println();
			System.out.println("Exit Success.");
			System.out.println();
			break;

		default:
			System.out.println("Invalid Selection");
			printUI();
			break;
		}
	}

	public void printUI() throws NumberFormatException, InterruptedException {

		int i = 0;
		Scanner scan = new Scanner(System.in);
		System.out.println("Please choose something to do");
		System.out.println(++i + ".\t Import attendance");
		System.out.println(++i + ".\t Edit attendance");
		System.out.println(++i + ".\t Delete attendance");
		System.out.println(++i + ".\t Show student");
		System.out.println(++i + ".\t Show sessions");
		System.out.println(++i + ".\t Mark Late Student");
		System.out.println(++i + ".\t View Student attendance");

		System.out.println("0.\t Exit Program");

		System.out.println("Select the choice: ");

		String choice = scan.nextLine();
		if (isInteger(choice)) {
			select(Integer.parseInt(choice));
		} else {
			printUI();
		}

	}

	public void viewSA(String coursea) {
		Map<String, String> session = course.get(coursea);

		for (String n : student) {
			System.out.println(n + " " + session.get(n));
		}
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
			System.out.println(name + " is " + temp.get(name) + " for "
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
				else if (!student.contains("0" + i))
					student.add("0" + i);
		}
	}

	public boolean ReadAttendance() {
		Map<String, String> session = new LinkedHashMap<String, String>();
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Enter the url for the file you want to import");
		JFrame j = new JFrame();
		j.toFront();
		String url = saveMap(j);
		j.dispose();

		if (url == null) {
			System.out.println("Not file selected. ");
			System.out.println("Exiting function");
			return false;
		}

		System.out.println("Enter the course name: ");
		String name = scan.nextLine();

		System.out.println("Reading Attendance....");
		// initStudent();

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

					boolean chk = false;
					while (chk == false) {
						System.out.println("Student Not Found");

						System.out.println("Do you want to add " + temp[0]
								+ " into the student database? yes | no");
						scan = new Scanner(System.in);
						String ans = scan.nextLine();
						if (ans.equals("Yes") || ans.equals("yes")
								|| ans.equals("y") || ans.equals("Y")) {
							student.add(temp[0]);
							System.out.println("Student added to database");
							session.put(temp[0], temp[1]);
							System.out.println(temp[0] + " added");

							chk = true;

						} else if (ans.equals("No") || ans.equals("no")
								|| ans.equals("N") || ans.equals("n")) {

							System.out
									.println("Student not added to the database, nor will he be added into this session. "
											+ "Run the document again.");

							chk = true;
						} else {
							System.out.println("Please try again.");
						}
					}

				}

			}
			coursen.add(name);
			course.put(name, (LinkedHashMap<String, String>) session);
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
				System.out.println("Reading Fail!");
				return false;
			}
		}

	}

	public void getAllStudent() {
		for (String name : student) {
			System.out.println("Student : " + name);
		}
	}

	public String saveMap(JFrame f) {
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new File(
				"/Users/Derrick/Desktop/School/PSD3/forTest/"));
		FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV",
				"csv");
		chooser.setFileFilter(filter);
		int retrival = chooser.showOpenDialog(f);
		if (retrival == JFileChooser.APPROVE_OPTION) {
			try {
				return chooser.getSelectedFile().getAbsolutePath();

			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

		return null;

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

	public boolean checkStudent(String name) {
		return (student.contains(name));
	}

	public boolean checkCourse(String name) {
		return (coursen.contains(name));
	}
}
