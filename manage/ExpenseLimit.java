package manage;

import java.util.Vector;



public class ExpenseLimit {
	private int expenseLimit;
	private String category;
	public ExpenseLimit() {
		this.expenseLimit=0;
		this.category="";
	}
	public void setExpenseLimit(String C, int E) {
		expenseLimit=E;
		category+=C;
		Vector<String> CList = Category.CategoryDB.getCategory();
		Vector<Integer> CLimit = Category.CategoryDB.getCategoryLimit();
		for (int i = 0; i < CLimit.size(); i++) {
			if(category.equals(CList.get(i))) {
				Category.CategoryDB.editedCategoryLimit(category, expenseLimit);
			}
		}
	}	
}