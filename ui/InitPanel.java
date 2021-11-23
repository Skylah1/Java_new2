package ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.util.Stack;

import javax.swing.*;
import database.DB;

public class InitPanel extends BasicPanel {
	
	
	private ImageIcon init = new ImageIcon("images/init.png"); // 이미지 로딩
	private JLabel initimg=new JLabel("",init,SwingConstants.CENTER); // 레이블 생성
			
	//private JTextField expenlimit= new JTextField(13);
	private JTextField curassetwon= new JTextField(13);
	private JTextField curassetdol= new JTextField(13);
	
	//private JLabel exli = new JLabel("  지출 한도         "); // 라벨
	private JLabel curassw = new JLabel("\n  현재 자산 (원)  ");
	private JLabel curassd = new JLabel("\n  현재 자산 ($)    ");

	private SaveToDB SaveCurAsset; //확인 버튼
	private JButton OK = new JButton("확인");
	private DB database;
	
	public InitPanel(JFrame frame) { // 생성자
		buttonclick = new ButtonClick(frame);
		SaveCurAsset = new SaveToDB(frame);
		InitObject();
		CenterPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 30)); // 왼쪽정렬, 수직거리 30
		CenterPanel.add(initimg); // 순서대로 저장
		//CenterPanel.add(exli);
		//CenterPanel.add(expenlimit);
		CenterPanel.add(curassw);
		CenterPanel.add(curassetwon);
		CenterPanel.add(curassd);
		CenterPanel.add(curassetdol);
		SouthPanel.add(OK);
	}
	
	
	
	private void InitObject() { // 초기화 작업 및 초기세팅
		
		//expenlimit.setBackground(Color.WHITE);
		//expenlimit.setForeground(Color.BLACK);
		//expenlimit.setFont(new Font("함초롱바탕", Font.BOLD, 18)); 
		
		
		curassetwon.setBackground(Color.WHITE);
		curassetwon.setForeground(Color.BLACK);
		curassetwon.setFont(new Font("함초롱바탕", Font.BOLD, 18));
		
		
		curassetdol.setBackground(Color.WHITE);
		curassetdol.setForeground(Color.BLACK);
		curassetdol.setFont(new Font("함초롱바탕", Font.BOLD, 18)); 
		
		OK.setBackground(Color.WHITE);
		OK.setForeground(Color.BLACK);
		OK.setFont(new Font("함초롱바탕", Font.BOLD, 18)); 
		
		// ========================================================================
		
		//exli.setFont(new Font("함초롱바탕", Font.BOLD, 18)); 
		curassw.setFont(new Font("함초롱바탕", Font.BOLD, 18)); 
		curassd.setFont(new Font("함초롱바탕", Font.BOLD, 18)); 
		
		OK.addActionListener(SaveCurAsset);

		curassetwon.addKeyListener(new KeyAdapter() { //현재자산(원) 입력 , comma 자동 입력
			public void keyTyped(KeyEvent e) {
				JTextField t = (JTextField)e.getSource();
				if((e.getKeyChar() == '0') ||(e.getKeyChar() == '1') ||(e.getKeyChar() == '2') ||
						(e.getKeyChar() == '3') ||(e.getKeyChar() == '4') ||(e.getKeyChar() == '5') ||
						(e.getKeyChar() == '6') ||(e.getKeyChar() == '7') ||(e.getKeyChar() == '8') ||
						(e.getKeyChar() == '9')) {
				}
				else e.consume();
				if(t.getText().length()>=16) e.consume();
			}
			public void keyReleased(KeyEvent e) { 
				JTextField t = (JTextField)e.getSource();
				String CommaMoney = ""; // 여기에 콤마 포함 저장 문자열 
				Stack<Character> MoneyToComma = addComma(t.getText()); // 스택에 저장(addComma함수에 string 금액 넣어줘야함)
				
				while(!MoneyToComma.isEmpty()) { // pop
					CommaMoney += MoneyToComma.pop();
				}
				t.setText(CommaMoney); // 텍스트 지정
			}
		});
		
		curassetdol.addKeyListener(new KeyAdapter() { //현재자산($) 입력, 소수점 입력
			int count = 0;
			public void keyTyped(KeyEvent e) {
				JTextField t = (JTextField)e.getSource();
				if((e.getKeyChar() == '0') ||(e.getKeyChar() == '1') ||(e.getKeyChar() == '2') ||
						(e.getKeyChar() == '3') ||(e.getKeyChar() == '4') ||(e.getKeyChar() == '5') ||
						(e.getKeyChar() == '6') ||(e.getKeyChar() == '7') ||(e.getKeyChar() == '8') ||
						(e.getKeyChar() == '9') ||(e.getKeyChar() == ',') ) { //comma 입력은 수동 (소숫점 넣으니까 addComma 고장나서)
				}
				
				else if((e.getKeyChar() == '.') && (count == 0) && t.getText().length() >= 1) { //소숫점 표기
					count++;
				}
				
				else e.consume();
				if(t.getText().length()>=10) e.consume();
			}
		});
	}
	
	private Stack<Character> addComma(String Money) {  					// 돈 콤마 추가해주는 함수
		Money = Money.replaceAll(",","");
		Money = Money.replaceAll("원","");
		Stack<Character> ShowMoney = new Stack<Character>();
		for(int i=0; i<Money.length(); i++) {
			if(i%3==0 && i!= 0) {
				ShowMoney.add(',');
			}
			ShowMoney.push(Money.charAt(Money.length()-1  - i));
		}
		return ShowMoney;
	}
	//확인 버튼 눌렀을 때  저장 및 화면 이동
	private class SaveToDB implements ActionListener {
		
		private String[] curasset = new String[2]; //0: won, 1: dollar
		private JFrame frame;
		
		public SaveToDB(JFrame frame) {
			this.frame = frame;
		}
		
		public void actionPerformed(ActionEvent e) {
			
			curasset[0] = curassetwon.getText().replaceAll(",", ""); //textfield에서 현재자산 get
			curasset[1] = curassetdol.getText().replaceAll(",", "");
			
			try {
				
				FileWriter fw = new FileWriter("DB.txt", true); //DB.txt 생성
			
				for (int i = 0; i < 2; i++) {
					//카테고리 리스트에 저장된 기본 카테고리들을 차례대로 CategoryDB.txt에 저장한다.
					
					fw.write(curasset[i]); //DB.txt에 현재자산 저장
					fw.write("\r\n"); //줄바꿈
					fw.flush();
				}
				fw.close();
			}
			
			catch(IOException e2) {
			//예외처리
			}
			
			frame.setContentPane(new MainPanel(frame)); //화면 전환
			frame.setVisible(true);

		}
	}
	
	
	
}