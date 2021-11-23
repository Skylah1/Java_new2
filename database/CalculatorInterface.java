package database;

import java.util.Vector;
import java.util.HashMap;

public interface CalculatorInterface {
	public Vector<Vector<String>> getIncomePerDate(); 
	public Vector<Vector<String>> getOutcomePerDate();
	public HashMap<String,Double> calcIncome();
	public HashMap<String,Double> calcOutcome();
	
}


//구현 완료
//기능#2: 오늘 날짜를 가져와서 뽑고 -> 해당 연도랑 월로 이번 달에 입력한 수입 전부 --> getIncomePerDate 	// 화면설계 슬라이드 7
//기능#3: 오늘 날짜를 가져와서 뽑고 -> 해당 연도랑 월로 이번 달에 입력한 수입 전부 --> getOutcomePerDate // 화면설계 슬라이드 8
//기능#4: 최근 6개월 수입 한달단위로 출력 -> 8월 얼마 9월 얼마  --> calcIncome() // 화면설계 슬라이드 9
//기능#5: 최근 6개월 지출 한달단위로 출력 -> 8월 얼마 9월 얼마 --> calcOutcome()   // 화면설계 슬라이드 10


//미구현
//기능#1: 다음달 예상 지출금액 --> getExpectedExpense
//기능#5: 전체 저장내용 중 카테고리별 출력 -> 파이차트    --> calcPerCategory()