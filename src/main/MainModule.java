package main;

import java.util.List;
import java.util.Scanner;

import dao.FinanceRepositoryImpl;
import entity.Expense;
import entity.ExpenseCategory;
import entity.User;
import myexceptions.ExpenseNotFoundException;
import myexceptions.UserNotFoundException;

public class MainModule {

	static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {

		boolean exit = false;

		while (!exit) {
			System.out.println("Finance Management System");
			System.out.println("1. User Management");
			System.out.println("2. Expense Management");
			System.out.println("3. Add Expense Category");
			System.out.println("4. Reports Generation");
			System.out.println("5. Exit");
			System.out.print("Enter your choice: ");

			int choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice) {
			case 1:
				System.out.println("1. User Authentication");
				System.out.println("2. Add user");
				System.out.println("3. Delete user");
				System.out.println("Select your option : ");
				int option = scanner.nextInt();
				if (option == 1) {
					authenticateUser();
				} else if (option == 2) {
					addUser();
				} else if (option == 3) {
					try {
						deleteUser();
					} catch (UserNotFoundException e) {
						e.printStackTrace();
					}
				} else {
					System.out.println("Invalid Choice!");
				}
				break;

			case 2:
				System.out.println("1. Add expenses");
				System.out.println("2. Update Expenses");
				System.out.println("3. Delete Expenses");
				System.out.println("Select your option : ");
				int option1 = scanner.nextInt();
				if (option1 == 1) {
					addExpenses();
				} else if (option1 == 2) {
					try {
						updateExpenses();
					} catch (ExpenseNotFoundException e) {
						e.printStackTrace();
					}
				} else if (option1 == 3) {
					try {
						deleteExpense();
					} catch (ExpenseNotFoundException e) {
						e.printStackTrace();
					}
				} else {
					System.out.println("Invalid Choice!");
				}
				break;
			case 3:
				createExpenseCategory();
				break;
			case 4:
				try {
					try {
						generateReports();
					} catch (ExpenseNotFoundException e) {
						e.printStackTrace();
					}
				} catch (UserNotFoundException e) {
					e.printStackTrace();
				}
				break;
			case 5:
				exit = true;
				break;
			default:
				System.out.println("Invalid choice. Please try again.");
			}
		}
		scanner.close();
		System.out.println("Visit again!");
	}

	public static boolean authenticateUser() {
		FinanceRepositoryImpl obj = new FinanceRepositoryImpl();

		scanner.nextLine();
		System.out.println("Enter username : ");
		String username = scanner.nextLine();

		System.out.println("Enter password : ");
		String password = scanner.next();

		boolean authenticated = obj.authenticateUser(username, password);

		if (authenticated) {
			System.out.println("Login successful!");
			System.out.println(" ");
		} else {
			System.out.println("Login failed! Invalid username or password.");
			System.out.println(" ");
		}
		return authenticated;

	}

	public static boolean addUser() {
		User usr = new User();
		FinanceRepositoryImpl obj = new FinanceRepositoryImpl();

		System.out.println("Enter UserId : ");
		int userId = scanner.nextInt();
		scanner.nextLine();
		usr.setUserId(userId);

		System.out.println("Enter Username : ");
		String userName = scanner.nextLine();
		usr.setUserName(userName);

		System.out.println("Enter password : ");
		String password = scanner.next();
		usr.setPassword(password);

		System.out.println("Enter email Id : ");
		String email = scanner.next();
		usr.setEmail(email);
		
		System.out.println("User added successfully.");
		System.out.println(" ");
		
		return obj.createUser(usr);

	}

	private static void deleteUser() throws UserNotFoundException {
		FinanceRepositoryImpl obj = new FinanceRepositoryImpl();

		System.out.println("Enter user Id to delete : ");
		int userId = scanner.nextInt();

		boolean result=obj.deleteUser(userId);
		if(result) {
			System.out.println("User deleted successfully!");			
		}else {
			throw new UserNotFoundException("User not found");
		}
		System.out.println(" ");

	}

	public static boolean addExpenses() {
		Expense exp = new Expense();
		FinanceRepositoryImpl obj = new FinanceRepositoryImpl();

		System.out.println("Enter expense Id : ");
		int expenseId = scanner.nextInt();
		exp.setExpenseId(expenseId);

		System.out.println("Enter user Id : ");
		int userId = scanner.nextInt();
		exp.setUserId(userId);

		System.out.println("Enter the amount : ");
		double amount = scanner.nextDouble();
		exp.setAmount(amount);

		System.out.println("Enter the category Id : ");
		int categoryId = scanner.nextInt();
		exp.setCategoryId(categoryId);

		System.out.println("Enter the date : ");
		String date = scanner.next();
		exp.setDate(date);
		scanner.nextLine();

		System.out.println("Enter the description about the expense : ");
		String description = scanner.nextLine();
		exp.setDescription(description);

		System.out.println("Expense added successfully!");
		System.out.println(" ");
		
		return obj.createExpense(exp);
		
	}

	private static void updateExpenses() throws ExpenseNotFoundException {
		Expense exp = new Expense();
		FinanceRepositoryImpl obj = new FinanceRepositoryImpl();

		System.out.println("Enter the userId whose expense is to be updated");
		int userId = scanner.nextInt();

		System.out.println("Enter the expsnese Id to change the amount of : ");
		int expenseId = scanner.nextInt();
		exp.setExpenseId(expenseId);

		System.out.println("Enter the amount : ");
		double amount = scanner.nextDouble();
		exp.setAmount(amount);
		
		boolean result=obj.updateExpense(userId, exp);
		if(result) {
			System.out.println("Expense updated successfully!");			
		}else {
			throw new ExpenseNotFoundException("Expense not found");
		}
		System.out.println(" ");
	}

	private static void deleteExpense() throws ExpenseNotFoundException {
		FinanceRepositoryImpl obj = new FinanceRepositoryImpl();

		System.out.println("Enter the expense Id to delete : ");
		int expenseId = scanner.nextInt();

		boolean result =obj.deleteExpense(expenseId);
		if(result) {
			System.out.println("Expense deleted successfully!");			
		}else {
			throw new ExpenseNotFoundException("Expense not found");
		}
		System.out.println(" ");
	}

	private static void createExpenseCategory() {
		ExpenseCategory expenseCategory = new ExpenseCategory();
		FinanceRepositoryImpl obj = new FinanceRepositoryImpl();

		System.out.println("Enter the category Id : ");
		int categoryId = scanner.nextInt();
		expenseCategory.setCategoryId(categoryId);
		scanner.nextLine();

		System.out.println("Enter the category name : ");
		String categoryName = scanner.nextLine();
		expenseCategory.setCategoryName(categoryName);

		obj.createExpenseCategory(expenseCategory);
		System.out.println("Expense category added successfully!");
		System.out.println(" ");
	}

	public static boolean generateReports() throws UserNotFoundException, ExpenseNotFoundException {
		FinanceRepositoryImpl obj = new FinanceRepositoryImpl();

		System.out.println("Enter the userId:");
		int userId = scanner.nextInt();

		obj.getAllExpenses(userId);
		
		List<Expense> allExpenses = obj.getAllExpenses(userId);
		for (Expense expense : allExpenses) {
			System.out.println("Expense ID: " + expense.getExpenseId());
			System.out.println("User ID: " + expense.getUserId());
			System.out.println("Amount: " + expense.getAmount());
			System.out.println("Category ID: " + expense.getCategoryId());
			System.out.println("Date: " + expense.getDate());
			System.out.println("Description: " + expense.getDescription());
			System.out.println(" ");
			System.out.println(" ");
		}
		if(allExpenses.size()>0) {
			return true;
		}else {
			return false;
		}
	}
}
