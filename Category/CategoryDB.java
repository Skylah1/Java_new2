package Category;
import java.io.*;
import java.util.Vector;
import java.util.Arrays;

public class CategoryDB {
	final static int INT_MAX = Integer.MAX_VALUE; //카테고리별 지출 한도 기본값(int 최댓값)

	private static Vector<String> categoryList = new Vector<String>(Arrays.asList("수입", "도서", "유흥", "교통", "식사")); //카테고리 목록, 기본적으로 수입, 도서, 유흥, 교통, 식사 카테고리가 있다.
	private static Vector<Integer> categoryLimit = new Vector<Integer>(Arrays.asList(0, INT_MAX, INT_MAX, INT_MAX, INT_MAX)); //카테고리별 지출 한도, 수입을 제외한 카테고리별 지출 한도는 기본값
	
	public CategoryDB() { //생성자
		
		FileReader fileReader=null; 
		
		try {
			fileReader=new FileReader("CategoryDB.txt"); //파일 생성
			setCategory(fileReader); //CategoryDB.txt에서 카테고리 목록을 불러온다.
		}
		 
		catch(FileNotFoundException e1) { //파일이 없을 경우(== 처음 실행한 경우)
			
			try {
				
				FileWriter fw = new FileWriter("CategoryDB.txt", true);
				String Category;
				int limit;
				String Limit;
			
				for (int i = 0; i < categoryList.size(); i++) {
					//카테고리 리스트에 저장된 기본 카테고리들을 차례대로 CategoryDB.txt에 저장한다.
					Category = categoryList.get(i);
					fw.write(Category);
						//카테고리별 지출 한도 저장
						limit = categoryLimit.get(i);
						Limit = Integer.toString(limit);
						fw.write("\t"); //Tab으로 카테고리 이름과 한도를 구분한다.
						fw.write(Limit);
					fw.write("\r\n"); //줄바꿈
					fw.flush();
				}
				fw.close();
			}
			catch(IOException e2) {
			//예외처리
			}
		}
	}
	
	public void addedCategory(String c) { //카테고리 추가
		
			if (categoryList.contains(c) == false) { //카테고리 목록에 추가할 카테고리가 없다면 실행
				categoryList.add(c); //리스트에 추가
				categoryLimit.add(INT_MAX); //지출한도도 기본값으로 자동으로 추가
				updateCategory(c, false); //카테고리 DB 갱신
			}
	}

	public void deletedCategory(String c) { //카테고리 삭제
		if (categoryList.contains(c) == true) { //카테고리 목록에 삭제할 카테고리가 있다면 실행
			categoryLimit.remove(categoryList.indexOf(c)); //해당 지출한도 삭제
			categoryList.remove(c); //카테고리 삭제
			updateCategory(c, false); //카테고리 DB 갱신
		}
	}
	
	public void editedCategory(String oldCategory, String newCategory) { //카테고리 변경
		
		if (categoryList.contains(oldCategory) == true) {
		
			int index = categoryList.indexOf(oldCategory); //예전 카테고리 위치
			int oldLimit = categoryLimit.get(index); //예전 카테고리의 지출한도
				categoryLimit.remove(index); //해당 지출한도 삭제
				categoryList.remove(oldCategory); //카테고리 삭제
				updateCategory(oldCategory, false); //카테고리 DB 갱신
		
			if (categoryList.contains(newCategory) == false) { //변경할 카테고리 목록에 추가
				categoryList.add(newCategory); //리스트에 추가
				categoryLimit.add(oldLimit); //미리 저장해둔 예전 지출한도 추가
				updateCategory(newCategory, false); //카테고리 DB 갱신
			}
		}
	}
	
	public static void editedCategoryLimit(String c, int limit) { //카테고리 c의 지출한도 수정
		
		if (categoryList.contains(c) == true) {
			int index = categoryList.indexOf(c); //카테고리의 위치 index
			categoryLimit.remove(index); //기존 지출한도 삭제
			categoryLimit.add(index, limit); //해당 위치에 변경할 지출한도 저장
			updateCategory(c, true); //카테고리 DB 갱신
		}
	}

	public static Vector<String> getCategory() { //카테고리 Getter
		
		try { //CategoryDB 업데이트용
			FileReader fileReader = new FileReader("CategoryDB.txt"); //파일 생성
			setCategory(fileReader);
		}
		catch(FileNotFoundException e1) {
			
		}
		return categoryList;
	}
	
	public static Vector<Integer> getCategoryLimit() { //카테고리별 지출한도 Getter
	
		try { //CategoryDB 업데이트용
			FileReader fileReader = new FileReader("CategoryDB.txt"); //파일 생성
			setCategory(fileReader);
		}
		catch(FileNotFoundException e1) {
			
		}
		
		return categoryLimit;
	}
	
	public static void updateCategory(String c, boolean isLimit) { //카테고리 추가 또는 삭제 시 카테고리 DB 갱신, isLimit = 지출한도만 변경 여부
		
		try {
			
			FileWriter fw = new FileWriter("CategoryDB.txt", true);
			int index; //추가되거나 삭제된 카테고리 위치
			int limit; //해당 카테고리의 지출한도
			String Limit;
			if (isLimit == false) { //지출한도 변경 케이스가 아닌 경우
				if (categoryList.contains(c) == true) { //카테고리 추가 시
			
					index = categoryList.indexOf(c);
					fw.write(categoryList.get(index)); //해당 위치의 카테고리를 카테고리 DB에 추가한다.
					limit = categoryLimit.get(index);
					Limit = Integer.toString(limit);
					fw.write("\t");
					fw.write(Limit);	
					fw.write("\r\n");
					fw.flush();
					fw.close(); 
				}
			
				else if (categoryList.contains(c) == false) { //카테고리 삭제 시

					new FileOutputStream("CategoryDB.txt").close(); //카테고리 DB 파일 초기화
				
					try {
					
						String Category;
						int climit;
						String CLimit;
				
						for (int i = 0; i < categoryList.size(); i++) {
							//카테고리 리스트에 저장된 카테고리 목록을 파일에 저장한다.
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
						//예외처리
					}
				}
			}
			else { //지출한도 변경 시
				new FileOutputStream("CategoryDB.txt").close(); //카테고리 DB 파일 초기화
			
				try {
				
					String Category;
					int climit;
					String CLimit;
			
					for (int i = 0; i < categoryList.size(); i++) {
						//카테고리 리스트에 저장된 카테고리 목록을 파일에 저장한다.
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
					//예외처리
				}
			}
		}
			catch(IOException e2) {
				//예외처리
			}
		
	}
	
	public static void setCategory(FileReader fileReader) { //카테고리 DB에서 카테고리 목록을 불러온다.
		
		 try {
			 BufferedReader bufReader = new BufferedReader(fileReader);
			 String line = null;
			 String[] splitline = {"", ""};
			 //카테고리 리스트 초기화
			 categoryList.clear();
			 categoryLimit.clear();
			 
			 while((line = bufReader.readLine()) != null) {
	             splitline = line.split("\t"); //한 줄을 Tab키를 기준으로 나눈다.
	             categoryList.add(splitline[0]);
	             if (splitline.length != 1) {
	             categoryLimit.add(Integer.parseInt(splitline[1]));
	             }
	         }
			 bufReader.close();
			}

		 catch (IOException e) {
			 // 예외처리
		 }
	}
}