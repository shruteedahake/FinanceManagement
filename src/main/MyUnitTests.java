package main;

import static org.junit.Assert.*;
import org.junit.Test;

import dao.FinanceRepositoryImpl;
import myexceptions.ExpenseNotFoundException;
import myexceptions.UserNotFoundException;

public class MyUnitTests {
	
	@Test
	public void testAddUserSuccess()
	{
		assertTrue(MainModule.addUser());
	}
	
	@Test
	public void testAddUserFailure()
	{
		assertFalse(MainModule.addUser());
	}
	
	@Test
	public void testAddExpenseSuccess() {
		assertTrue(MainModule.addExpenses());
	}
	
	@Test
	public void testAddExpenseFailure() {
		assertFalse(MainModule.addExpenses());
	}
	
	@Test
	public void testDisplayAllExpensesSuccess() 
	{
		try {
			assertTrue(MainModule.generateReports());
		} catch (UserNotFoundException e) {
			e.printStackTrace();
		} catch (ExpenseNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testDisplayAllExpensesFailure() 
	{
		try {
			assertFalse(MainModule.generateReports());
		} catch (UserNotFoundException e) {
			e.printStackTrace();
		} catch (ExpenseNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testDeleteExpenseSuccess() {
		FinanceRepositoryImpl financeRepository = new FinanceRepositoryImpl();
	    int expenseId = 3; 

	    boolean result = financeRepository.deleteExpense(expenseId);

	    assertTrue(result); 
	 }
	
    @Test
    public void testDeleteExpenseNotFoundFailure() {
        FinanceRepositoryImpl financeRepository = new FinanceRepositoryImpl();
        int expenseId = 1; 

        boolean deletionSuccessful = financeRepository.deleteExpense(expenseId);

        assertFalse(deletionSuccessful);
    }
    
	
}
