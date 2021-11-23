package database;

import java.io.*;
import java.util.Vector;
import Category.CategoryInfo;

import java.util.StringTokenizer;
import ui.*;



public class DB {
	private double[] curBalance=new double[2];	// 현재 보유 자산, [원화,달러]
	private Vector<Vector<String>> datas= new Vector<Vector<String>>();	// [["20211105", "0", "도서", "12000"],[...],[...]]
	private Frame frame = new Frame();
	
	public DB() {
		FileReader fileReader=null;
		
		// 파일을 불러옴
		try {
			fileReader=new FileReader("DB.txt");
			setting(fileReader);
		} 
		// "DB.txt"가 없으면
		catch(FileNotFoundException e) {
			//파일이 없으면 초기설정 UI실행
			frame.frame.setContentPane(new InitPanel(frame.frame));
			frame.frame.setVisible(true);
			// 초기 설정 UI 사용자 입력값을 받아옴
			// datas에 저장
			System.out.println("File not found");	// debug 용
		}
		try {
			fileReader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void setting(FileReader fileReader) {
		BufferedReader buffer=new BufferedReader(fileReader);
		String line="";
		int count=0;  // 몇 번째 라인인지 count, 
		try {
			// curBalance(원화, 외화) 초기화
			while(count<=1 && (line=buffer.readLine())!=null) {
				curBalance[count]=Double.parseDouble(line);
				count++;
			}
			// 한줄 버림
			buffer.readLine();
			 
			// 가계부 본문 읽기
			while((line=buffer.readLine())!=null) {
				StringTokenizer st=new StringTokenizer(line," ");	// 20211105, 0, 도서, 12000
				Vector<String> tmp=new Vector<String>();			// datas에 저장할 임시 객체 생성
				while(st.hasMoreTokens()) {						// tmp=> ["20211105","0","도서","12000"]
					String token=st.nextToken();
					tmp.add(token);				
				}
				datas.add(tmp);				//datas=[ ["20211105","0","도서","12000"], [...], [...], ... ]
			}
			frame.frame.setContentPane(new MainPanel(frame.frame));
			frame.frame.setVisible(true);
		}
		catch(IOException e) {
			// 읽으면서 오류 있으면 예외처리
		}
	}
	
	// 현재자산 getter
	public double[] getCurBalance() {
		return this.curBalance;
	}
	
	// datas getter
	public Vector<Vector<String>> getDatas(){
		return datas;
		//return this.datas;
	}
	
	// 프로그램 종료 전, "DB.txt" 업데이트
	public void update(){
	       try {
	          BufferedWriter pw = new BufferedWriter(new FileWriter("DB.txt"));
	          String input;
	          double KRW = (double)curBalance[0];
	          double USD = (double)curBalance[1];
	          input = Double.toString(KRW);
	          pw.write(input);
	          pw.newLine();
	          input = Double.toString(USD);
	          pw.write(input);
	          pw.newLine();
	          pw.newLine();
	          for(Vector<String> line: datas) {
	        	  String tmp="";
	              for(String value: line) {
	                  tmp+=value+" ";
	              }
	              tmp=tmp.trim();
	              pw.write(tmp);
                  pw.newLine();
	          }
	          pw.close();
	       }
	       catch(IOException e){
	    	// 쓰면서 오류 있으면 예외처리
	       }
	   }
	
	// test 용 메소드
	public void print() {
		for(double balance : curBalance) {
			System.out.println(balance);			
		}
		
		for(Vector<String> line : datas) {
			for(String val : line) {
				System.out.print(val+" ");
			}
			System.out.println();
		}
	}
	// test
}