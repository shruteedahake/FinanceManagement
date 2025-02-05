package dao;

import java.util.List;

import entity.Expense;
import entity.ExpenseCategory;
import entity.User;

public interface IFinanceRepository {
	boolean createUser(User user);
	boolean createExpense(Expense expense);
	boolean deleteUser(int userId);
	boolean deleteExpense(int expenseId);
	List<Expense> getAllExpenses(int userId);
	boolean updateExpense(int userId, Expense expense);
	boolean createExpenseCategory(ExpenseCategory expenseCategory);
	boolean authenticateUser(String username, String password);
}
