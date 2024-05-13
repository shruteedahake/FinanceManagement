package dao;

import entity.Expense;
import entity.User;
import util.DBConnUtil;
import util.DBPropertyUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class FinanceRepositoryImpl implements IFinanceRepository {
	@Override
	public boolean createUser(User user) {
		try (Connection connection = DBConnUtil.getConnection(DBPropertyUtil.getConnectionString(
				"D:\\Hexaware\\CASE Study\\FinanceManagement\\src\\util\\Connection.properties"))) {
			
			String query = "INSERT INTO user (user_id, username, passowrd, email) VALUES (?, ?, ?, ?)";

			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, user.getUserID());
			preparedStatement.setString(2, user.getUsername());
			preparedStatement.setString(3, user.getPassword());
			preparedStatement.setString(4, user.getEmail());

			int rowsAffected = preparedStatement.executeUpdate();
			return rowsAffected > 0;
		} catch (SQLException e) {
			e.printStackTrace(); // Log or handle the exception appropriately
			return false;
		}
	}

	@Override
	public boolean createExpense(Expense expense) {
		// Implementation to add expense to the database
		return true; // Return true if successful, false otherwise
	}

	@Override
	public boolean deleteUser(int userId) {
		// Implementation to delete user from the database
		return true; // Return true if successful, false otherwise
	}

	@Override
	public boolean deleteExpense(int expenseId) {
		// Implementation to delete expense from the database
		return true; // Return true if successful, false otherwise
	}

	@Override
	public List<Expense> getAllExpenses(int userId) {
		// Implementation to retrieve all expenses of a user from the database
		return null; // Return a list of expenses
	}

	@Override
	public boolean updateExpense(int userId, Expense expense) {
		// Implementation to update expense in the database
		return true; // Return true if successful, false otherwise
	}
}
