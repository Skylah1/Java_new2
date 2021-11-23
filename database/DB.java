package database;

import java.io.*;
import java.util.Vector;
import Category.CategoryInfo;

import java.util.StringTokenizer;
import ui.*;



public class DB {
	private double[] curBalance=new double[2];	// ���� ���� �ڻ�, [��ȭ,�޷�]
	private Vector<Vector<String>> datas= new Vector<Vector<String>>();	// [["20211105", "0", "����", "12000"],[...],[...]]
	private Frame frame = new Frame();
	
	public DB() {
		FileReader fileReader=null;
		
		// ������ �ҷ���
		try {
			fileReader=new FileReader("DB.txt");
			setting(fileReader);
		} 
		// "DB.txt"�� ������
		catch(FileNotFoundException e) {
			//������ ������ �ʱ⼳�� UI����
			frame.frame.setContentPane(new InitPanel(frame.frame));
			frame.frame.setVisible(true);
			// �ʱ� ���� UI ����� �Է°��� �޾ƿ�
			// datas�� ����
			System.out.println("File not found");	// debug ��
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
		int count=0;  // �� ��° �������� count, 
		try {
			// curBalance(��ȭ, ��ȭ) �ʱ�ȭ
			while(count<=1 && (line=buffer.readLine())!=null) {
				curBalance[count]=Double.parseDouble(line);
				count++;
			}
			// ���� ����
			buffer.readLine();
			 
			// ����� ���� �б�
			while((line=buffer.readLine())!=null) {
				StringTokenizer st=new StringTokenizer(line," ");	// 20211105, 0, ����, 12000
				Vector<String> tmp=new Vector<String>();			// datas�� ������ �ӽ� ��ü ����
				while(st.hasMoreTokens()) {						// tmp=> ["20211105","0","����","12000"]
					String token=st.nextToken();
					tmp.add(token);				
				}
				datas.add(tmp);				//datas=[ ["20211105","0","����","12000"], [...], [...], ... ]
			}
			frame.frame.setContentPane(new MainPanel(frame.frame));
			frame.frame.setVisible(true);
		}
		catch(IOException e) {
			// �����鼭 ���� ������ ����ó��
		}
	}
	
	// �����ڻ� getter
	public double[] getCurBalance() {
		return this.curBalance;
	}
	
	// datas getter
	public Vector<Vector<String>> getDatas(){
		return datas;
		//return this.datas;
	}
	
	// ���α׷� ���� ��, "DB.txt" ������Ʈ
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
	    	// ���鼭 ���� ������ ����ó��
	       }
	   }
	
	// test �� �޼ҵ�
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