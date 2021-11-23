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
	
	public Vector<Vector<String>> CalcPerCategory(String KU){	//�Ű������� ���� KU("0", "1", "2")�� ���� �ڷḦ �������ش� [[�Ѿ�], [���� 130000 0.08086], [���� 99470 0.06187], ..., ...]
		Vector<Vector<String>> cal=new Vector<>();
		Vector<String> Clist = Category.CategoryDB.getCategory();	//ī�װ� ��� ����
		int i=0;	//�� ī�װ��� ���� �ݾ��� ������ ����
		String M = Money(KU);
		Vector<String> setM=new Vector<String>();	// ��ȭ/��ȭ/���� ����� ����
		setM.add(M);
		cal.add(setM);
		for(String Fline: Clist) {	// Fline == ["����"]
			if(!(Fline.equals("����"))){				//������ ���� ������� ����
				Vector<String> tmp=new Vector<String>();	// ����� ����
				for(Vector<String> Sline: DBlist) {		//ī�װ� �� ��ŭ �ݺ�
					if(Sline.get(2).equals(Fline) && Sline.get(1).equals(KU)) {		//������ �ƴϸ鼭 ���� ī�װ��� ������� �ݾ��� ���Ѵ�
						tmp.removeAllElements();	//tmp�� �ʱ�ȭ�� �ֱ� ���� �����
						tmp.add(Sline.get(2));		//tmp�� ī�װ� �̸��� ����
						i += Integer.parseInt(Sline.get(3));	//���� �ݾ��� ����
						String s = Integer.toString(i);		//������ ���ڿ��� �ٲ۴�
						tmp.add(s);		//tmp�� ���� �ֱٿ� ���� �ݾ��� ����
					}
					else if(Sline.get(2).equals(Fline) && KU.equals("2")){
						tmp.removeAllElements();	//tmp�� �ʱ�ȭ�� �ֱ� ���� �����
						tmp.add(Sline.get(2));		//tmp�� ī�װ� �̸��� ����
						if(Sline.get(1).equals("1")) {
							i += RATE*Integer.parseInt(Sline.get(3));	//���� �ݾ��� ����
						}
						else
							i += Integer.parseInt(Sline.get(3));	//���� �ݾ��� ����		
						String s = Integer.toString(i);		//������ ���ڿ��� �ٲ۴�
						tmp.add(s);		//tmp�� ���� �ֱٿ� ���� �ݾ��� ����
					}
				}
				//�� ī�װ����� ���⿡�� �����ϴ� ���� ����
				double k = Double.parseDouble(M);	//�� ���� ����
				double per = i/k;					//ī�װ��� �����ϴ� ���� ����
				String PerS = Double.toString(per);	
				tmp.add(PerS);						//���Ϳ� ���� �� ����
				i=0;	//���� ī�װ� �۾��� ���� �ʱ�ȭ
				cal.add(tmp);	//��� ���Ϳ� ����
			}
		}
		return cal;
	}
	
	public String Money(String KU) {
		int i=0;
		String money="";
		for(Vector<String> line: DBlist) {
			if(line.get(1).equals("0") && !(line.get(2).equals("����")) && line.get(1).equals(KU))
				i += Integer.parseInt(line.get(3));	//���� �ݾ��� ����
			else if(line.get(1).equals("1")  && !(line.get(2).equals("����")) && line.get(1).equals(KU)) 
				i += Integer.parseInt(line.get(3));	//���� �ݾ��� ����
			else if(!(line.get(2).equals("����")) && KU.equals("2")) {
				if(line.get(1).equals("1")) {
					i += RATE*Integer.parseInt(line.get(3));	//���� �ݾ��� ����
				}
				else
					i += Integer.parseInt(line.get(3));	//���� �ݾ��� ����
			}
		}
		money = Integer.toString(i);
		return money;
	}
	
	public Vector<Vector<String>> CalcPerCategorysum(){	//ī�װ��� �� ���� ��ȯ
		Vector<Vector<String>> result= CalcPerCategory("2");	//Vector<Srting> ��ȯ �ڷᱸ�� [["����", "100000"], ["����", "150000"]] // ī�װ�, ��ȭ/��ȭ, �ݾ�], "2"�� ��ȭ��ȭ ���� �����ϴ� ���ڷ� ���
		//test �ڵ�
		for(Vector<String> line : result) {
			for(String val : line) {
				System.out.print(val+" ");
			}
			System.out.println();
		}
		return result;	//��� ��ȯ
	}
	
	public Vector<Vector<String>> CalcPerCategoryKRW(){	//ī�װ��� ��ȭ ���� ��ȯ
		Vector<Vector<String>> result= CalcPerCategory("0");	//Vector<Srting> ��ȯ �ڷᱸ�� [["����", "100000"], ["����", "150000"]] // ī�װ�, ��ȭ/��ȭ, �ݾ�]
		//test �ڵ�
		for(Vector<String> line : result) {
			for(String val : line) {
				System.out.print(val+" ");
			}
			System.out.println();
		}
		return result;	//��� ��ȯ
	}
	
	public Vector<Vector<String>> CalcPerCategoryUSD(){	////ī�װ��� ��ȭ ���� ��ȯ
		Vector<Vector<String>> result= CalcPerCategory("1");	//Vector<Srting> ��ȯ �ڷᱸ�� [["����", "100000"], ["����", "150000"]] // ī�װ�, ��ȭ/��ȭ, �ݾ�]
		//test �ڵ�
		for(Vector<String> line : result) {
			for(String val : line) {
				System.out.print(val+" ");
			}
			System.out.println();
		}
		return result;	//��� ��ȯ
	}
    
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public Vector<Vector<String>> getIncomePerDate() {
		Vector<Vector<String>> result=new Vector<Vector<String>>();	// ��ȯ �ڷᱸ�� >> [["1", "0", "100000"], ["2", "0", "50000"], ["20", "0", "5000000"]] // ��, ��ȭ/��ȭ, �ݾ� ��
		
		String today = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")); // today == "yyyyMMdd"
		String thisMonth=today.substring(0,6); // thisMonth=="yyyyMM"
		
		for(Vector<String> line: DBlist) {  // line == ["20211105", "0", "����", "12000"]
			Vector<String> tmp=new Vector<String>();	// ����� ����
			if(thisMonth.equals(line.get(0).substring(0,6)) && line.get(2).equals("����")) { // ���ó�¥�� ��,���� ����, ī�װ��� "����"�̸�,
				// �� �߰�
				if(line.get(0).charAt(6)=='0') {	// ���� 01~09�� ��� 1~9�� ����
					tmp.add(line.get(0).substring(7,8));
				}
				else {  							// ���� 10~31�� ��� �״�� ����
					tmp.add(line.get(0).substring(6,8));
				}
				tmp.add(line.get(1));	// ��ȭ/��ȭ �߰�
				tmp.add(line.get(3));	// �ݾ� �߰�
										// tmp==["1","0","100000"]
				result.add(tmp);
			}
		}
		return result;
	}
	
	
	public Vector<Vector<String>> getOutcomePerDate() {
		Vector<Vector<String>> result=new Vector<Vector<String>>();	// ��ȯ �ڷᱸ�� >> [["1", "0", "100000"], ["2", "0", "50000"], ["20", "0", "5000000"]] // ��, ��ȭ/��ȭ, �ݾ� ��
		
		String today = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")); // today == "yyyyMMdd"
		String thisMonth=today.substring(0,6); // thisMonth=="yyyyMM"
		
		for(Vector<String> line: DBlist) {  // line == ["20211105", "0", "����", "12000"]
			Vector<String> tmp=new Vector<String>();	// ����� ����
			if(thisMonth.equals(line.get(0).substring(0,6)) && !line.get(2).equals("����")) { // ���ó�¥�� ��,���� ����, ī�װ��� "����"�� �ƴϸ�,
				// �� �߰�
				if(line.get(0).charAt(6)=='0') {	// ���� 01~09�� ��� 1~9�� ����
					tmp.add(line.get(0).substring(7,8));
				}
				else {  							// ���� 10~31�� ��� �״�� ����
					tmp.add(line.get(0).substring(6,8));
				}
				tmp.add(line.get(1));	// ��ȭ/��ȭ �߰�
				tmp.add(line.get(3));	// �ݾ� �߰�
										// tmp==["1","0","100000"]
				result.add(tmp);
			}
		}
		return result;
	}
	
	
	public HashMap<String, Double> calcIncome() {
		HashMap<String,Double> map=new HashMap<String,Double>();	// �ڷᱸ�� {"202105":100000, "202106":2000, ... }
		
		// hashmap �ʱ�ȭ
		String today = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")); // today == "yyyyMMdd"
		int intCurYear=Integer.parseInt(today.substring(0,4)); // curYear==yyyy
		int intCurMonth=Integer.parseInt(today.substring(4,6)); // curMonth==MM
		int recent=6;	// �ֱ� 6����
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
		
		// hashmap ������Ʈ
		for(Vector<String> line: DBlist) {  // line == ["20211105", "0", "����", "12000"]
			if(map.containsKey(line.get(0).substring(0,6))  && line.get(2).equals("����")) { // ��ȸ ��¥�� ��,���� ����, ī�װ��� "����"�̸�,
				map.put(line.get(0).substring(0,6), map.get(line.get(0).substring(0,6))+Double.parseDouble(line.get(3)));
			}
		}
		return map;
	}
	
		
	public HashMap<String, Double> calcOutcome() {
		HashMap<String,Double> map=new HashMap<String,Double>();	// �ڷᱸ�� {"202105":100000, "202106":2000, ... }
		
		// hashmap �ʱ�ȭ
		String today = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")); // today == "yyyyMMdd"
		int intCurYear=Integer.parseInt(today.substring(0,4)); // curYear==yyyy
		int intCurMonth=Integer.parseInt(today.substring(4,6)); // curMonth==MM
		int recent=6;	// �ֱ� 6����
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
		
		// hashmap ������Ʈ
		for(Vector<String> line: DBlist) {  // line == ["20211105", "0", "����", "12000"]
			if(map.containsKey(line.get(0).substring(0,6))  && !line.get(2).equals("����")) { // ��ȸ ��¥�� ��,���� ����, ī�װ��� "����"�� �ƴϸ�,
				map.put(line.get(0).substring(0,6), map.get(line.get(0).substring(0,6))+Double.parseDouble(line.get(3)));
			}
		}
		return map;
	}
	
	
	/*
	public Vector<Vector<String>> getOutcomePerDate() {
		Vector<Vector<String>> result=new Vector<Vector<String>>();	// ��ȯ �ڷᱸ�� >> [["1", "0", "100000"], ["2", "0", "50000"], ["20", "0", "5000000"]] // ��, ��ȭ/��ȭ, �ݾ� ��
		
		String today = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")); // today == "yyyyMMdd"
		String thisMonth=today.substring(0,6); // thisMonth=="yyyyMM"
		
		for(Vector<String> line: DBlist) {  // line == ["20211105", "0", "����", "12000"]
			Vector<String> tmp=new Vector<String>();	// ����� ����
			if(thisMonth.equals(line.get(0).substring(0,6)) && !line.get(2).equals("����")) { // ���ó�¥�� ��,���� ����, ī�װ��� "����"�� �ƴϸ�,
				// �� �߰�
				if(line.get(0).charAt(6)=='0') {	// ���� 01~09�� ��� 1~9�� ����
					tmp.add(line.get(0).substring(7,8));
				}
				else {  							// ���� 10~31�� ��� �״�� ����
					tmp.add(line.get(0).substring(6,8));
				}
				tmp.add(line.get(1));	// ��ȭ/��ȭ �߰�
				tmp.add(line.get(3));	// �ݾ� �߰�
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