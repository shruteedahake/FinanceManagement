package main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import dao.FinanceRepositoryImpl;
import entity.User;
import util.DBConnUtil;
import util.DBPropertyUtil;

public class MainModule {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		boolean exit = false;

		while (!exit) {
			System.out.println("Finance Management System");
			System.out.println("1. User Authentication");
			System.out.println("2. Expense Management");
			System.out.println("3. Expense Categorization");
			System.out.println("4. Reports Generation");
			System.out.println("5. Exit");
			System.out.println("6. Add user");
			System.out.print("Enter your choice: ");

			int choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice) {
			case 1:
				
				authenticateUser();
				break;
			case 2:
				manageExpenses();
				break;
			case 3:
				categorizeExpenses();
				break;
			case 4:
				generateReports();
				break;
			case 5:
				exit = true;
				break;
			case 6:
				addUser();
				break;
			default:
				System.out.println("Invalid choice. Please try again.");
			}
		}
		scanner.close();
		System.out.println("Visit again!");
	}

	private static boolean authenticateUser() {
		String username = null;
		System.out.print("Enter your username: ");
		Scanner scanner = new Scanner(System.in);
		username = scanner.nextLine();

		String query = "SELECT * FROM user WHERE username = ?";
		String connectionString = DBPropertyUtil.getConnectionString(
				"D:\\Hexaware\\CASE Study\\FinanceManagement\\src\\util\\Connection.properties");

		try (Connection connection = DBConnUtil.getConnection(connectionString);
				PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setString(1, username);
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next())
					System.out.println(
							resultSet.getInt(1) + "  " + resultSet.getString(2) + "  " + resultSet.getString(3));
				return resultSet.next();
			}
		} catch (SQLException e) {
			System.err.println("Error authenticating user: " + e.getMessage());
			return false;
		}
	}

	private static void manageExpenses() {
		// Implement expense management functionality
		System.out.println("Expense Management");
		// Add your code here
	}

	private static void addUser() {
		User usr = new User();
		FinanceRepositoryImpl obj = new FinanceRepositoryImpl();
		
		obj.createUser(usr);
		
	}
	private static void categorizeExpenses() {
		// Implement expense categorization functionality
		System.out.println("Expense Categorization");
		// Add your code here
	}

	private static void generateReports() {
		// Implement reports generation functionality
		System.out.println("Reports Generation");
		// Add your code here
	}

}
