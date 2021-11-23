package database;
import java.util.Vector;
import java.time.LocalDateTime; // LocalDateTime
import java.time.format.DateTimeFormatter;	// DateTimeFormatter
import java.util.HashMap;
import java.util.StringTokenizer;


public class Calculator {
	final double RATE = RateCrawler.getRate(); // RATE==1190.0, 1 USD == 1190.0 KRW
	Vector<Vector<String>> DBlist;
	double [] curBalanceList;
	public Calculator(Vector<Vector<String>> DBL, double Balance[]) {
		this.DBlist = DBL;
		this.curBalanceList = Balance;
	}
	
	public Vector<Vector<String>> CalcPerCategory(String KU){	//매개변수로 받은 KU("0", "1", "2")에 따라 자료를 정렬해준다 [[총액], [도서 130000 0.08086], [유흥 99470 0.06187], ..., ...]
		Vector<Vector<String>> cal=new Vector<>();
		Vector<String> Clist = Category.CategoryDB.getCategory();	//카테고리 목록 저장
		int i=0;	//각 카테고리별 지출 금액을 저장할 변수
		String M = Money(KU);
		Vector<String> setM=new Vector<String>();	// 원화/외화/총합 지출액 저장
		setM.add(M);
		cal.add(setM);
		for(String Fline: Clist) {	// Fline == ["도서"]
			if(!(Fline.equals("수입"))){				//수입인 경우는 고려하지 않음
				Vector<String> tmp=new Vector<String>();	// 저장용 변수
				for(Vector<String> Sline: DBlist) {		//카테고리 수 만큼 반복
					if(Sline.get(2).equals(Fline) && Sline.get(1).equals(KU)) {		//수입이 아니면서 같은 카테고리인 지출들의 금액을 합한다
						tmp.removeAllElements();	//tmp를 초기화해 최근 값만 남긴다
						tmp.add(Sline.get(2));		//tmp에 카테고리 이름을 저장
						i += Integer.parseInt(Sline.get(3));	//합한 금액을 저장
						String s = Integer.toString(i);		//정수를 문자열로 바꾼다
						tmp.add(s);		//tmp에 가장 최근에 합한 금액을 저장
					}
					else if(Sline.get(2).equals(Fline) && KU.equals("2")){
						tmp.removeAllElements();	//tmp를 초기화해 최근 값만 남긴다
						tmp.add(Sline.get(2));		//tmp에 카테고리 이름을 저장
						if(Sline.get(1).equals("1")) {
							i += RATE*Integer.parseInt(Sline.get(3));	//합한 금액을 저장
						}
						else
							i += Integer.parseInt(Sline.get(3));	//합한 금액을 저장		
						String s = Integer.toString(i);		//정수를 문자열로 바꾼다
						tmp.add(s);		//tmp에 가장 최근에 합한 금액을 저장
					}
				}
				//각 카테고리별로 지출에서 차지하는 비율 저장
				double k = Double.parseDouble(M);	//총 지출 저장
				double per = i/k;					//카테고리가 차지하는 비율 저장
				String PerS = Double.toString(per);	
				tmp.add(PerS);						//벡터에 비율 값 저장
				i=0;	//다음 카테고리 작업을 위해 초기화
				cal.add(tmp);	//결과 벡터에 저장
			}
		}
		return cal;
	}
	
	public String Money(String KU) {
		int i=0;
		String money="";
		for(Vector<String> line: DBlist) {
			if(line.get(1).equals("0") && !(line.get(2).equals("수입")) && line.get(1).equals(KU))
				i += Integer.parseInt(line.get(3));	//합한 금액을 저장
			else if(line.get(1).equals("1")  && !(line.get(2).equals("수입")) && line.get(1).equals(KU)) 
				i += Integer.parseInt(line.get(3));	//합한 금액을 저장
			else if(!(line.get(2).equals("수입")) && KU.equals("2")) {
				if(line.get(1).equals("1")) {
					i += RATE*Integer.parseInt(line.get(3));	//합한 금액을 저장
				}
				else
					i += Integer.parseInt(line.get(3));	//합한 금액을 저장
			}
		}
		money = Integer.toString(i);
		return money;
	}
	
	public Vector<Vector<String>> CalcPerCategorysum(){	//카테고리별 총 지출 반환
		Vector<Vector<String>> result= CalcPerCategory("2");	//Vector<Srting> 반환 자료구조 [["도서", "100000"], ["유흥", "150000"]] // 카테고리, 원화/외화, 금액], "2"를 원화외화 합을 지정하는 문자로 사용
		//test 코드
		for(Vector<String> line : result) {
			for(String val : line) {
				System.out.print(val+" ");
			}
			System.out.println();
		}
		return result;	//결과 반환
	}
	
	public Vector<Vector<String>> CalcPerCategoryKRW(){	//카테고리별 원화 지출 반환
		Vector<Vector<String>> result= CalcPerCategory("0");	//Vector<Srting> 반환 자료구조 [["도서", "100000"], ["유흥", "150000"]] // 카테고리, 원화/외화, 금액]
		//test 코드
		for(Vector<String> line : result) {
			for(String val : line) {
				System.out.print(val+" ");
			}
			System.out.println();
		}
		return result;	//결과 반환
	}
	
	public Vector<Vector<String>> CalcPerCategoryUSD(){	////카테고리별 외화 지출 반환
		Vector<Vector<String>> result= CalcPerCategory("1");	//Vector<Srting> 반환 자료구조 [["도서", "100000"], ["유흥", "150000"]] // 카테고리, 원화/외화, 금액]
		//test 코드
		for(Vector<String> line : result) {
			for(String val : line) {
				System.out.print(val+" ");
			}
			System.out.println();
		}
		return result;	//결과 반환
	}
    
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public Vector<Vector<String>> getIncomePerDate() {
		Vector<Vector<String>> result=new Vector<Vector<String>>();	// 반환 자료구조 >> [["1", "0", "100000"], ["2", "0", "50000"], ["20", "0", "5000000"]] // 일, 원화/외화, 금액 순
		
		String today = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")); // today == "yyyyMMdd"
		String thisMonth=today.substring(0,6); // thisMonth=="yyyyMM"
		
		for(Vector<String> line: DBlist) {  // line == ["20211105", "0", "도서", "12000"]
			Vector<String> tmp=new Vector<String>();	// 저장용 변수
			if(thisMonth.equals(line.get(0).substring(0,6)) && line.get(2).equals("수입")) { // 오늘날짜와 연,월이 같고, 카테고리가 "수입"이면,
				// 일 추가
				if(line.get(0).charAt(6)=='0') {	// 일이 01~09인 경우 1~9만 저장
					tmp.add(line.get(0).substring(7,8));
				}
				else {  							// 일이 10~31인 경우 그대로 저장
					tmp.add(line.get(0).substring(6,8));
				}
				tmp.add(line.get(1));	// 원화/외화 추가
				tmp.add(line.get(3));	// 금액 추가
										// tmp==["1","0","100000"]
				result.add(tmp);
			}
		}
		return result;
	}
	
	
	public Vector<Vector<String>> getOutcomePerDate() {
		Vector<Vector<String>> result=new Vector<Vector<String>>();	// 반환 자료구조 >> [["1", "0", "100000"], ["2", "0", "50000"], ["20", "0", "5000000"]] // 일, 원화/외화, 금액 순
		
		String today = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")); // today == "yyyyMMdd"
		String thisMonth=today.substring(0,6); // thisMonth=="yyyyMM"
		
		for(Vector<String> line: DBlist) {  // line == ["20211105", "0", "도서", "12000"]
			Vector<String> tmp=new Vector<String>();	// 저장용 변수
			if(thisMonth.equals(line.get(0).substring(0,6)) && !line.get(2).equals("수입")) { // 오늘날짜와 연,월이 같고, 카테고리가 "수입"이 아니면,
				// 일 추가
				if(line.get(0).charAt(6)=='0') {	// 일이 01~09인 경우 1~9만 저장
					tmp.add(line.get(0).substring(7,8));
				}
				else {  							// 일이 10~31인 경우 그대로 저장
					tmp.add(line.get(0).substring(6,8));
				}
				tmp.add(line.get(1));	// 원화/외화 추가
				tmp.add(line.get(3));	// 금액 추가
										// tmp==["1","0","100000"]
				result.add(tmp);
			}
		}
		return result;
	}
	
	
	public HashMap<String, Double> calcIncome() {
		HashMap<String,Double> map=new HashMap<String,Double>();	// 자료구조 {"202105":100000, "202106":2000, ... }
		
		// hashmap 초기화
		String today = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")); // today == "yyyyMMdd"
		int intCurYear=Integer.parseInt(today.substring(0,4)); // curYear==yyyy
		int intCurMonth=Integer.parseInt(today.substring(4,6)); // curMonth==MM
		int recent=6;	// 최근 6개월
		for(int i=0;i<recent;++i) {	
			if(intCurMonth<=0) {
				intCurMonth+=12;
				intCurYear-=1;
			}
			if(intCurMonth<10) {
				map.put(Integer.toString(intCurYear)+"0"+Integer.toString(intCurMonth), 0.0);				
			}
			else {
				map.put(Integer.toString(intCurYear)+Integer.toString(intCurMonth), 0.0);
			}
			intCurMonth--;
		}
		
		// hashmap 업데이트
		for(Vector<String> line: DBlist) {  // line == ["20211105", "0", "도서", "12000"]
			if(map.containsKey(line.get(0).substring(0,6))  && line.get(2).equals("수입")) { // 조회 날짜와 연,월이 같고, 카테고리가 "수입"이면,
				map.put(line.get(0).substring(0,6), map.get(line.get(0).substring(0,6))+Double.parseDouble(line.get(3)));
			}
		}
		return map;
	}
	
		
	public HashMap<String, Double> calcOutcome() {
		HashMap<String,Double> map=new HashMap<String,Double>();	// 자료구조 {"202105":100000, "202106":2000, ... }
		
		// hashmap 초기화
		String today = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")); // today == "yyyyMMdd"
		int intCurYear=Integer.parseInt(today.substring(0,4)); // curYear==yyyy
		int intCurMonth=Integer.parseInt(today.substring(4,6)); // curMonth==MM
		int recent=6;	// 최근 6개월
		for(int i=0;i<recent;++i) {	
			if(intCurMonth<=0) {
				intCurMonth+=12;
				intCurYear-=1;
			}
			if(intCurMonth<10) {
				map.put(Integer.toString(intCurYear)+"0"+Integer.toString(intCurMonth), 0.0);
			}
			else {
				map.put(Integer.toString(intCurYear)+Integer.toString(intCurMonth), 0.0);
			}
			intCurMonth--;
		}
		
		// hashmap 업데이트
		for(Vector<String> line: DBlist) {  // line == ["20211105", "0", "도서", "12000"]
			if(map.containsKey(line.get(0).substring(0,6))  && !line.get(2).equals("수입")) { // 조회 날짜와 연,월이 같고, 카테고리가 "수입"이 아니면,
				map.put(line.get(0).substring(0,6), map.get(line.get(0).substring(0,6))+Double.parseDouble(line.get(3)));
			}
		}
		return map;
	}
	
	
	/*
	public Vector<Vector<String>> getOutcomePerDate() {
		Vector<Vector<String>> result=new Vector<Vector<String>>();	// 반환 자료구조 >> [["1", "0", "100000"], ["2", "0", "50000"], ["20", "0", "5000000"]] // 일, 원화/외화, 금액 순
		
		String today = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")); // today == "yyyyMMdd"
		String thisMonth=today.substring(0,6); // thisMonth=="yyyyMM"
		
		for(Vector<String> line: DBlist) {  // line == ["20211105", "0", "도서", "12000"]
			Vector<String> tmp=new Vector<String>();	// 저장용 변수
			if(thisMonth.equals(line.get(0).substring(0,6)) && !line.get(2).equals("수입")) { // 오늘날짜와 연,월이 같고, 카테고리가 "수입"이 아니면,
				// 일 추가
				if(line.get(0).charAt(6)=='0') {	// 일이 01~09인 경우 1~9만 저장
					tmp.add(line.get(0).substring(7,8));
				}
				else {  							// 일이 10~31인 경우 그대로 저장
					tmp.add(line.get(0).substring(6,8));
				}
				tmp.add(line.get(1));	// 원화/외화 추가
				tmp.add(line.get(3));	// 금액 추가
										// tmp==["1","0","100000"]
				result.add(tmp);
			}
		}
		
		for(Vector<String> line : result) {
			for(String val : line) {
				System.out.print(val+" ");
			}
			System.out.println();
		}
		return result;
	}*/
	
	
	

}