package Category;
import java.io.*;
import java.util.Vector;
import java.util.Arrays;

public class CategoryDB {
	final static int INT_MAX = Integer.MAX_VALUE; //ī�װ��� ���� �ѵ� �⺻��(int �ִ�)

	private static Vector<String> categoryList = new Vector<String>(Arrays.asList("����", "����", "����", "����", "�Ļ�")); //ī�װ� ���, �⺻������ ����, ����, ����, ����, �Ļ� ī�װ��� �ִ�.
	private static Vector<Integer> categoryLimit = new Vector<Integer>(Arrays.asList(0, INT_MAX, INT_MAX, INT_MAX, INT_MAX)); //ī�װ��� ���� �ѵ�, ������ ������ ī�װ��� ���� �ѵ��� �⺻��
	
	public CategoryDB() { //������
		
		FileReader fileReader=null; 
		
		try {
			fileReader=new FileReader("CategoryDB.txt"); //���� ����
			setCategory(fileReader); //CategoryDB.txt���� ī�װ� ����� �ҷ��´�.
		}
		 
		catch(FileNotFoundException e1) { //������ ���� ���(== ó�� ������ ���)
			
			try {
				
				FileWriter fw = new FileWriter("CategoryDB.txt", true);
				String Category;
				int limit;
				String Limit;
			
				for (int i = 0; i < categoryList.size(); i++) {
					//ī�װ� ����Ʈ�� ����� �⺻ ī�װ����� ���ʴ�� CategoryDB.txt�� �����Ѵ�.
					Category = categoryList.get(i);
					fw.write(Category);
						//ī�װ��� ���� �ѵ� ����
						limit = categoryLimit.get(i);
						Limit = Integer.toString(limit);
						fw.write("\t"); //Tab���� ī�װ� �̸��� �ѵ��� �����Ѵ�.
						fw.write(Limit);
					fw.write("\r\n"); //�ٹٲ�
					fw.flush();
				}
				fw.close();
			}
			catch(IOException e2) {
			//����ó��
			}
		}
	}
	
	public void addedCategory(String c) { //ī�װ� �߰�
		
			if (categoryList.contains(c) == false) { //ī�װ� ��Ͽ� �߰��� ī�װ��� ���ٸ� ����
				categoryList.add(c); //����Ʈ�� �߰�
				categoryLimit.add(INT_MAX); //�����ѵ��� �⺻������ �ڵ����� �߰�
				updateCategory(c, false); //ī�װ� DB ����
			}
	}

	public void deletedCategory(String c) { //ī�װ� ����
		if (categoryList.contains(c) == true) { //ī�װ� ��Ͽ� ������ ī�װ��� �ִٸ� ����
			categoryLimit.remove(categoryList.indexOf(c)); //�ش� �����ѵ� ����
			categoryList.remove(c); //ī�װ� ����
			updateCategory(c, false); //ī�װ� DB ����
		}
	}
	
	public void editedCategory(String oldCategory, String newCategory) { //ī�װ� ����
		
		if (categoryList.contains(oldCategory) == true) {
		
			int index = categoryList.indexOf(oldCategory); //���� ī�װ� ��ġ
			int oldLimit = categoryLimit.get(index); //���� ī�װ��� �����ѵ�
				categoryLimit.remove(index); //�ش� �����ѵ� ����
				categoryList.remove(oldCategory); //ī�װ� ����
				updateCategory(oldCategory, false); //ī�װ� DB ����
		
			if (categoryList.contains(newCategory) == false) { //������ ī�װ� ��Ͽ� �߰�
				categoryList.add(newCategory); //����Ʈ�� �߰�
				categoryLimit.add(oldLimit); //�̸� �����ص� ���� �����ѵ� �߰�
				updateCategory(newCategory, false); //ī�װ� DB ����
			}
		}
	}
	
	public static void editedCategoryLimit(String c, int limit) { //ī�װ� c�� �����ѵ� ����
		
		if (categoryList.contains(c) == true) {
			int index = categoryList.indexOf(c); //ī�װ��� ��ġ index
			categoryLimit.remove(index); //���� �����ѵ� ����
			categoryLimit.add(index, limit); //�ش� ��ġ�� ������ �����ѵ� ����
			updateCategory(c, true); //ī�װ� DB ����
		}
	}

	public static Vector<String> getCategory() { //ī�װ� Getter
		
		try { //CategoryDB ������Ʈ��
			FileReader fileReader = new FileReader("CategoryDB.txt"); //���� ����
			setCategory(fileReader);
		}
		catch(FileNotFoundException e1) {
			
		}
		return categoryList;
	}
	
	public static Vector<Integer> getCategoryLimit() { //ī�װ��� �����ѵ� Getter
	
		try { //CategoryDB ������Ʈ��
			FileReader fileReader = new FileReader("CategoryDB.txt"); //���� ����
			setCategory(fileReader);
		}
		catch(FileNotFoundException e1) {
			
		}
		
		return categoryLimit;
	}
	
	public static void updateCategory(String c, boolean isLimit) { //ī�װ� �߰� �Ǵ� ���� �� ī�װ� DB ����, isLimit = �����ѵ��� ���� ����
		
		try {
			
			FileWriter fw = new FileWriter("CategoryDB.txt", true);
			int index; //�߰��ǰų� ������ ī�װ� ��ġ
			int limit; //�ش� ī�װ��� �����ѵ�
			String Limit;
			if (isLimit == false) { //�����ѵ� ���� ���̽��� �ƴ� ���
				if (categoryList.contains(c) == true) { //ī�װ� �߰� ��
			
					index = categoryList.indexOf(c);
					fw.write(categoryList.get(index)); //�ش� ��ġ�� ī�װ��� ī�װ� DB�� �߰��Ѵ�.
					limit = categoryLimit.get(index);
					Limit = Integer.toString(limit);
					fw.write("\t");
					fw.write(Limit);	
					fw.write("\r\n");
					fw.flush();
					fw.close(); 
				}
			
				else if (categoryList.contains(c) == false) { //ī�װ� ���� ��

					new FileOutputStream("CategoryDB.txt").close(); //ī�װ� DB ���� �ʱ�ȭ
				
					try {
					
						String Category;
						int climit;
						String CLimit;
				
						for (int i = 0; i < categoryList.size(); i++) {
							//ī�װ� ����Ʈ�� ����� ī�װ� ����� ���Ͽ� �����Ѵ�.
							Category = categoryList.get(i);
							fw.write(Category);
								climit = categoryLimit.get(i);
								CLimit = Integer.toString(climit);
								fw.write("\t");
								fw.write(CLimit);
							fw.write("\r\n");
							fw.flush();
						}
						fw.close();
					}
					catch(IOException e2) {
						//����ó��
					}
				}
			}
			else { //�����ѵ� ���� ��
				new FileOutputStream("CategoryDB.txt").close(); //ī�װ� DB ���� �ʱ�ȭ
			
				try {
				
					String Category;
					int climit;
					String CLimit;
			
					for (int i = 0; i < categoryList.size(); i++) {
						//ī�װ� ����Ʈ�� ����� ī�װ� ����� ���Ͽ� �����Ѵ�.
						Category = categoryList.get(i);
						fw.write(Category);
							climit = categoryLimit.get(i);
							CLimit = Integer.toString(climit);
							fw.write("\t");
							fw.write(CLimit);
						fw.write("\r\n");
						fw.flush();
					}
					fw.close();
				}
				catch(IOException e2) {
					//����ó��
				}
			}
		}
			catch(IOException e2) {
				//����ó��
			}
		
	}
	
	public static void setCategory(FileReader fileReader) { //ī�װ� DB���� ī�װ� ����� �ҷ��´�.
		
		 try {
			 BufferedReader bufReader = new BufferedReader(fileReader);
			 String line = null;
			 String[] splitline = {"", ""};
			 //ī�װ� ����Ʈ �ʱ�ȭ
			 categoryList.clear();
			 categoryLimit.clear();
			 
			 while((line = bufReader.readLine()) != null) {
	             splitline = line.split("\t"); //�� ���� TabŰ�� �������� ������.
	             categoryList.add(splitline[0]);
	             if (splitline.length != 1) {
	             categoryLimit.add(Integer.parseInt(splitline[1]));
	             }
	         }
			 bufReader.close();
			}

		 catch (IOException e) {
			 // ����ó��
		 }
	}
}