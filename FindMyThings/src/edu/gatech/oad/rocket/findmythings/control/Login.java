package edu.gatech.oad.rocket.findmythings.control;


import java.util.ArrayList;

import edu.gatech.oad.rocket.findmythings.model.Admin;
import edu.gatech.oad.rocket.findmythings.model.Member;
import edu.gatech.oad.rocket.findmythings.model.User;

/**
 * A class managing how a User is authenticated.
 * TODO: Actually authenticate users against something.
 * @author Team Rocket
 */
public class Login {

	/**
	 * Static ArrayList to keep user information consistent even after you leave the login screen
	 * and come back. Probably a better way to accomplish this, but it's 1am right now so fuck it.
	 */
	public static ArrayList<Member> data = new ArrayList<Member>();

	/**
	 * Currently logged in user
	 */
	public static Member currUser;


	public Login() {
	}

	static {
		Member[] template = new Member[6]; //For testing login without the need for registration
		template[0] = new User("cchu43@gatech.edu","admin","555-555-5555");
		template[1] = new User("jcole44@gatech.edu","admin","555-555-5555");
		template[2] = new User("tstowell3@gatech.edu","admin","555-555-5555");
		template[3] = new User("zwaldowski@gatech.edu","admin","555-555-5555");
		template[4] = new User("a@a.com","aaaa");
		template[5] = new Admin("ad@min.com","aaaa");
		for(Member m : template)
			data.add(m);
	}

	/**
	 * verify a user
	 * @param m Member to be verified
	 * @return whether or not the member has valid credentials
	 */
	public boolean verifyUser(Member m) {
		boolean found = false;
		int index = data.indexOf(m);
		if(index!=-1) {
			if(m instanceof User && ((User)m).locked())
				((User)data.get(index)).setLock(true);
			else if(data.get(index).getPassword().equals(m.getPassword()))
				found = true;



		}
		return found;
	}

	/**
	 * adds new member to arraylist
	 * @param name user name
	 * @param password user pass
	 */
	public Member register(Member temp) {

		data.add(temp);

		return temp;
	}

	/**
	 * Updates users locked status
	 * @param m
	 */
	public Member update(Member m) {
		if(data.contains(m)) {
			if(data.get(data.indexOf(m)) instanceof User) {
			m = new User(m.getUser(),m.getPassword()); //Return a User
			(((User) m)).setAttempts(((User) data.get(data.indexOf(m))).getAttempts());
			(((User) m)).setLock((((User) data.get(data.indexOf(m))).locked()));
			}
			else m = new Admin(m.getUser(),m.getPassword()); // return admin
		}
		return m;

	}
	
	/**
	 * updates currUser after successfully logging in so currUser knows more than just its email/password
	 * @param m
	 */
	public static void updateUser(Member m) {
		currUser = data.get(data.indexOf(m));
	}
	
	/**
	 * Locks user account in the arraylist
	 * @param m
	 */
	public void checkAttempts(User m) {
		// "the justin case"
		int justincase = data.indexOf(m);
		if(justincase!=-1)
			((User) data.get(justincase)).setAttempts(m.getAttempts());
	}
	



}