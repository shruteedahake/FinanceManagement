package dao;

import entity.Expense;
import entity.ExpenseCategory;
import entity.User;
import util.DBConnUtil;
import util.DBPropertyUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FinanceRepositoryImpl implements IFinanceRepository {

	@Override
	public boolean authenticateUser(String username, String password) {
		try (Connection connection = DBConnUtil.getConnection(DBPropertyUtil.getConnectionString(
				"D:\\Hexaware\\CASE Study\\FinanceManagement\\src\\util\\Connection.properties"))) {

			String query = "SELECT COUNT(*) FROM user WHERE username = ? AND password = ?";

			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);

			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				int count = resultSet.getInt(1);
				return count == 1;
			}

			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean createUser(User user) {
		try (Connection connection = DBConnUtil.getConnection(DBPropertyUtil.getConnectionString(
				"D:\\Hexaware\\CASE Study\\FinanceManagement\\src\\util\\Connection.properties"))) {

			String query = "INSERT INTO user (user_id, username, password, email) VALUES (?, ?, ?, ?)";

			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, user.getUserId());
			preparedStatement.setString(2, user.getUserName());
			preparedStatement.setString(3, user.getPassword());
			preparedStatement.setString(4, user.getEmail());

			int rowsAffected = preparedStatement.executeUpdate();
			return rowsAffected > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deleteUser(int userId) {
		try (Connection connection = DBConnUtil.getConnection(DBPropertyUtil.getConnectionString(
				"D:\\Hexaware\\CASE Study\\FinanceManagement\\src\\util\\Connection.properties"))) {

			String query = "DELETE FROM user WHERE user_id = ?";

			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, userId);

			int rowsAffected = preparedStatement.executeUpdate();
			return rowsAffected > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public boolean createExpense(Expense expense) {
		try (Connection connection = DBConnUtil.getConnection(DBPropertyUtil.getConnectionString(
				"D:\\Hexaware\\CASE Study\\FinanceManagement\\src\\util\\Connection.properties"))) {

			String query = "INSERT INTO expenses (expense_id, user_id, amount, category_id, date, description) VALUES (?, ?, ?, ?, ?, ?)";

			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, expense.getExpenseId());
			preparedStatement.setInt(2, expense.getUserId());
			preparedStatement.setDouble(3, expense.getAmount());
			preparedStatement.setInt(4, expense.getCategoryId());
			preparedStatement.setString(5, expense.getDate());
			preparedStatement.setString(6, expense.getDescription());

			int rowsAffected = preparedStatement.executeUpdate();
			return rowsAffected > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deleteExpense(int expenseId) {
		try (Connection connection = DBConnUtil.getConnection(DBPropertyUtil.getConnectionString(
				"D:\\Hexaware\\CASE Study\\FinanceManagement\\src\\util\\Connection.properties"))) {

			String query = "DELETE FROM expenses WHERE expense_id = ?";

			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, expenseId);

			int rowsAffected = preparedStatement.executeUpdate();
			return rowsAffected > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<Expense> getAllExpenses(int userId) {
		List<Expense> expenses = new ArrayList<>();
		String query = "SELECT * FROM expenses WHERE user_id = ?";
		try (Connection connection = DBConnUtil.getConnection(DBPropertyUtil.getConnectionString(
				"D:\\Hexaware\\CASE Study\\FinanceManagement\\src\\util\\Connection.properties"))) {

			PreparedStatement pstmt = connection.prepareStatement(query);
			pstmt.setInt(1, userId);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Expense expense = new Expense();
				expense.setExpenseId(rs.getInt("expense_id"));
				expense.setUserId(rs.getInt("user_id"));
				expense.setAmount(rs.getDouble("amount"));
				expense.setCategoryId(rs.getInt("category_id"));
				expense.setDate(rs.getString("date"));
				expense.setDescription(rs.getString("description"));
				expenses.add(expense);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return expenses;
	}

	@Override
	public boolean updateExpense(int userId, Expense expense) {
		try (Connection connection = DBConnUtil.getConnection(DBPropertyUtil.getConnectionString(
				"D:\\Hexaware\\CASE Study\\FinanceManagement\\src\\util\\Connection.properties"))) {

			String query = "UPDATE expenses set amount = ? where expense_id=?";

			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setDouble(1, expense.getAmount());
			preparedStatement.setInt(2, expense.getExpenseId());

			int rowsAffected = preparedStatement.executeUpdate();
			return rowsAffected > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public boolean createExpenseCategory(ExpenseCategory expenseCategory) {
		try (Connection connection = DBConnUtil.getConnection(DBPropertyUtil.getConnectionString(
				"D:\\Hexaware\\CASE Study\\FinanceManagement\\src\\util\\Connection.properties"))) {

			String query = "INSERT INTO expenseCategories (category_id, category_name) VALUES (?, ?)";

			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, expenseCategory.getCategoryId());
			preparedStatement.setString(2, expenseCategory.getCategoryName());

			int rowsAffected = preparedStatement.executeUpdate();
			return rowsAffected > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}
