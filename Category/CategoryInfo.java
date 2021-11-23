package Category;

import java.util.Vector;
import manage.ExpenseLimit;

public class CategoryInfo {
	
	//private String CategoryName;
	/*
	 private boolean isAdded;
	 private boolean isDeleted;
	 private boolean isEdited;
	 */
	CategoryDB CDB;
	
	public CategoryInfo() {
		this.CDB = new CategoryDB();
	}
	
	public void addCategory(String c) {
		CDB.addedCategory(c);
	}
	
	public void deleteCategory(String c) {
		CDB.deletedCategory(c);
	}
	
	public void editCategory(String oldCategory, String newCategory) {
		CDB.editedCategory(oldCategory, newCategory);
	}
	
	public void printCategory() { //����׿� �޼ҵ�, ī�װ� ��� Ȯ�ο�
		Vector<String> CList = CDB.getCategory();
		for (int i = 0; i < CList.size(); i++) {
			System.out.println (CList.get(i));
		}
		System.out.println();
	}
	
	public void printCategoryLimit() { //����׿� �޼ҵ�, ī�װ� �����ѵ� Ȯ�ο�
		Vector<Integer> CLimit = CDB.getCategoryLimit();
		for (int i = 0; i < CLimit.size(); i++) {
			System.out.println (CLimit.get(i));
		}
		System.out.println();
	}
	
	public void editCategoryLimit(String c, int limit) { //����׿� �޼ҵ�, �����ѵ� ���� Ȯ�ο�
		CDB.editedCategoryLimit(c, limit);
	}

	//public static void main(String[] args) { //test
		
		/*
		CInfo.addCategory("��"); //add test
		CInfo.editCategoryLimit("��", 10000); //editLimit test
		
		CInfo.printCategory();
		CInfo.printCategoryLimit();
		
		CInfo.editCategoryLimit("��", 15000); //editLimit test
		
		CInfo.printCategory();
		CInfo.printCategoryLimit();
		
		CInfo.deleteCategory("����"); //del test
		
		CInfo.printCategory();
		CInfo.printCategoryLimit();
		
		CInfo.editCategoryLimit("�Ļ�", 30000); //editLimit test
		
		CInfo.printCategory();
		CInfo.printCategoryLimit();
		
		CInfo.editCategory("�Ļ�", "����"); //edit test
		
		CInfo.printCategory();
		CInfo.printCategoryLimit();*/
	}