package entity;

public class ExpenseCategory {
	private int categoryId;
	private String categoryName;
	
	public void setCategoryId(int categoryId) {
		this.categoryId=categoryId;
	}
	
	public void setCategoryName(String categoryName) {
		this.categoryName=categoryName;
	}
	
	public int getCategoryId() {
		return categoryId;
	}
	
	public String getCategoryName() {
		return categoryName;
	}
}
