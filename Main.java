import java.util.*;

public class Main {

	static ArrayList<String> user = new ArrayList<String>();
	static Map<String, String> pwd = new LinkedHashMap<String, String>();
	static Map<String, String> role = new LinkedHashMap<String, String>();

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		
		initUser();
		
		StudentAttendance sa = new StudentAttendance();
		tutorLoadGrade tg = new tutorLoadGrade();
		int chk = 3, func = 0;;
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter your username");
		while (func == 0 && chk != 0) {
			if (chk < 3) {
				System.out
				.println("The username is not found. Please try again. ");
			}
			
			String name = sc.nextLine();

			if (checkUser(name)) {
				System.out.println("User found, please enter your password. ");
				func++;
				chk = 3;
				
				while (func == 1 && chk != 0) {
					
					if (chk < 3) {
						System.out.println("The password you typed is incorrect, please try again.");
					}
					
					String pwd = sc.nextLine();
					
					if (checkPwd(name, pwd)) {
						func++;
						
						if (checkRole(name).equals("Admin")) {
							
							Thread t = new Thread();
							t.start();
							System.out.print("User found. Loading Menu, please wait");
							for (int i = 0; i < 4; i++) {
								Thread.sleep(1000);
								System.out.print(".");
							}
							
							System.out.println();
							System.out.println("Menu successfully loaded. \n");
							tg.printUI();
							chk = 0;
							break;
							
						} else if (checkRole(name).equals("Tutor")) {
							
							Thread t = new Thread();
							t.start();
							System.out.print("User found. Loading Menu, please wait");
							for (int i = 0; i < 4; i++) {
								Thread.sleep(1000);
								System.out.print(".");
							}
							
							System.out.println();
							System.out.println("Menu successfully loaded. \n");
							
							sa.printUI();
							chk = 0;
							break;
						}
						
					} else {
						chk --;
						
						if (chk == 0) {
							System.out.println("You have exceeded your tries. Please do it later.");
						}
						
					}
				}
			} else {
				chk--;
				
				if (chk == 0) {
					System.out.println("You have exceeded your tries. Please do it later.");
				}
				
			}
		}
	}

	public static boolean checkUser(String name) {
		for (String k : user) {
			if (k.equals(name)) {
				return true;
			}
		}

		return false;
	}

	public static boolean checkPwd(String u, String p) {
		return (pwd.get(u).equals(p));
	}
	
	public static String checkRole(String u) {
		return (role.get(u));
	}
	
	public static void initUser() {
		user.add("Derrick");
		pwd.put("Derrick", "derrick");
		role.put("Derrick", "Admin");
		
		user.add("Reuben");
		pwd.put("Reuben", "reuben");
		role.put("Reuben", "Tutor");
		
		user.add("Jason");
		pwd.put("Jason", "jason");
		role.put("Jason", "Admin");
		
		user.add("Kevin");
		pwd.put("Kevin", "kevin");
		role.put("Kevin", "Tutor");
		
		user.add("NgaiFong");
		pwd.put("NgaiFong", "ngaifong");
		role.put("NgaiFong", "Tutor");
	}

}
