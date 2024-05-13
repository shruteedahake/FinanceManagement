package entity;

import java.util.Scanner;

public class User {

	private Scanner sc = new Scanner(System.in);

	public int getUserID() {
		System.out.println("Enter your userId: ");
		int userID = sc.nextInt();

		return userID;
	}

	public String getUsername() {
		System.out.println("Enter your username: ");
		String userName = sc.next();

		return userName;
	}

	public String getPassword() {
		System.out.println("Enter your password: ");
		String password = sc.next();

		return password;
	}

	public String getEmail() {
		System.out.println("Enter your email: ");
		String email = sc.next();

		return email;
	}

}
