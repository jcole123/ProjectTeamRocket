package edu.gatech.cs2340_sp13.teamrocket.findmythings;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;



public class Login {
	
	public Login() {
		
	}
	/**
	 * verify a user
	 * @param m Member to be verified
	 * @return whether or not the member has valid credentials
	 */
	public boolean verifyUser(Member m) {
		Scanner scan;
		boolean found = false;
		File pass = new File("test.txt");
		try {
			scan = new Scanner(pass); // file not found, will fix later
			while(scan.hasNextLine() && !found) {
				String[] str = scan.nextLine().split(":");
				if(m.getUser().equals(str[0]))	
					if(m.getPassword().equals(str[1]))
						found = true;
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return found;
	}
	/**
	 * In the future, will create a new member, I'm lazy so as of now it just adds the user to a text file to test registration
	 * @param name user name
	 * @param password user pass
	 */
	public Member register(String name, String password) {
		try {
		    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("nopasswordsinhere.txt", true)));  //store usernames and passwords in super secure text file
		    out.println(name + ":" + password);
		    out.close();
		} catch (IOException e) {
		    System.out.println("This can't be life");
		}
		return new Member(name, password);
		
	}

}