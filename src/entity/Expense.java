package entity;

public class Expense {
	
	private int expenseId;
	private int userId;
	private double amount;
	private int categoryId;
	private String date;
	private String description;
	
	public Expense() {}

	public Expense(int expenseId, int userId, double amount, int categoryId, String date, String description) {
	        this.expenseId = expenseId;
	        this.userId = userId;
	        this.amount = amount;
	        this.categoryId = categoryId;
	        this.date = date;
	        this.description = description;
	}
	
	public void setUserId(int userId) {
		this.userId=userId;
	}
	
	public void setExpenseId(int expenseId) {
		this.expenseId=expenseId;
	}
	
	public void setAmount(double amount) {
		this.amount=amount;
	}
	
	public void setCategoryId(int categoryId) {
		this.categoryId=categoryId;
	}
	
	public void setDate(String date) {
		this.date=date;
	}
	
	public void setDescription(String description) {
		this.description=description;
	}
	
	public int getExpenseId() {
		return expenseId;
	}
	
	public int getUserId() {
		return userId;
	}
	
	public double getAmount() {
		return amount;
	}
	
	public int getCategoryId() {
		return categoryId;
	}
	
	public String getDate() {
		return date;
	}
	
	public String getDescription() {
		return description;
	}
	
}
