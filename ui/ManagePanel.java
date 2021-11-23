package ui;

import javax.swing.*;

import Category.CategoryDB;

import manage.ExpenseLimit;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Stack;
import java.util.Vector;

public class ManagePanel extends BasicPanel {

	// 간격 조절
	private JLabel Line = new JLabel("aaaaaaaaaa"); // 라벨
	private JLabel Line1 = new JLabel("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"); // 라벨
	private JLabel Line2 = new JLabel("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"); // 라벨
	private JLabel Line3 = new JLabel("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"); // 라벨
		
	private ImageIcon manage = new ImageIcon("images/manage.png"); // 이미지 로딩
	private JLabel manageimg=new JLabel("",manage,SwingConstants.CENTER); // 레이블 생성
	
	Vector<String> Clist = CategoryDB.getCategory();	//카테고리 목록 저장 
	
	private JComboBox Category; // 카테고리 콤보박스
	private JLabel cate = new JLabel("  카테고리 선택         "); // 라벨
	
	// =======================================================================
	
	private JTextField curexpenlimit= new JTextField(10);
	private JLabel current = new JLabel("  현재 지출 한도        "); // 라벨
	private Vector<Integer> CLimitList = CategoryDB.getCategoryLimit();
	
	private JTextField changexpenlimit= new JTextField("금액 입력(엔터)",10);
	private JLabel change = new JLabel("  변경할 지출 한도    "); // 라벨
	
	private GoToBackPanel backbutton;
	private BackButton GOtoBack = new BackButton();
	
	private String SelectCategory;
	private String SelectExpenLimit;
	
	public ManagePanel(JFrame frame) { // 생성자
		buttonclick = new ButtonClick(frame);
		backbutton = new GoToBackPanel(frame);
		InitObject();
		CenterPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 30)); // 왼쪽정렬, 수직거리 30
		CenterPanel.add(Line); // 순서대로 저장
		CenterPanel.add(manageimg);
		CenterPanel.add(Line1);
		
		CenterPanel.add(cate);
		CenterPanel.add(Category);
		CenterPanel.add(Line2);
		
		CenterPanel.add(current);
		CenterPanel.add(curexpenlimit);
		CenterPanel.add(Line3);
		
		CenterPanel.add(change);
		CenterPanel.add(changexpenlimit);
		
		SouthPanel.setLayout(new FlowLayout(FlowLayout.LEFT)); // 뒤로가기
		SouthPanel.add(GOtoBack);
	}
	
	
	
	private void InitObject() { // 초기화 작업 및 초기세팅
		
		Line.setBackground(Color.WHITE);
		Line.setForeground(Color.WHITE);
		Line.setFont(new Font("함초롱바탕", Font.BOLD, 12)); 
		
		Line1.setBackground(Color.WHITE);
		Line1.setForeground(Color.WHITE);
		Line1.setFont(new Font("함초롱바탕", Font.BOLD, 12)); 
		
		Line2.setBackground(Color.WHITE);
		Line2.setForeground(Color.WHITE);
		Line2.setFont(new Font("함초롱바탕", Font.BOLD, 12)); 
		
		Line3.setBackground(Color.WHITE);
		Line3.setForeground(Color.WHITE);
		Line3.setFont(new Font("함초롱바탕", Font.BOLD, 12)); 
		
		// ------------------------------------------------------카테고리 콤보박스, (현재 지출한도)텍스트 라벨 지정-----------------------------------------------
		Clist.remove(0);
		Category = new JComboBox(Clist);
		Category.setBackground(Color.WHITE); 							
		Category.setForeground(Color.BLACK);
		Category.setFont(new Font("함초롱바탕", Font.BOLD, 18)); 
		Category.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox cb = (JComboBox)e.getSource();
				String CommaMoney = ""; // 여기에 콤마 포함 저장 문자열 
				Stack<Character> MoneyToComma = addComma(String.valueOf(CLimitList.toArray()[cb.getSelectedIndex()])); // 스택에 저장(addComma함수에 string 금액 넣어줘야함)
				
				while(!MoneyToComma.isEmpty()) { // pop
					CommaMoney += MoneyToComma.pop();
				}
				curexpenlimit.setText(CommaMoney); // 텍스트 지정
				SelectCategory = (String)cb.getSelectedItem();
			}
		});
		Category.setSelectedIndex(0); // 초기 설정(0번쨰 가리키게)
		//---------------------------------------------------------------------------------------------------------------------------------
		
		curexpenlimit.setBackground(Color.WHITE);
		curexpenlimit.setForeground(Color.BLACK);
		curexpenlimit.setFont(new Font("함초롱바탕", Font.BOLD, 18));
		curexpenlimit.addKeyListener(new KeyAdapter() {									// 입력할때 콤마 표시
			public void keyTyped(KeyEvent e) { e.consume(); }
			public void keyReleased(KeyEvent e) { Category.setSelectedIndex(Category.getSelectedIndex()); }
			
		});
		
		//----------------------------------------------------------------변경할 지출 한도 텍스트 입력 (콤마 포함)------------------------------------
		changexpenlimit.setBackground(Color.WHITE);
		changexpenlimit.setForeground(Color.BLACK);
		changexpenlimit.setFont(new Font("함초롱바탕", Font.BOLD, 18)); 
		changexpenlimit.addKeyListener(new KeyAdapter() {									// 입력할때 콤마 표시
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
		changexpenlimit.addActionListener(new ActionListener() { // 엔터 할 경우 데이터 저장
			public void actionPerformed(ActionEvent e) {
				JTextField t = (JTextField)e.getSource();
				ExpenseLimit expenselimit = new ExpenseLimit();
				SelectExpenLimit = (String)t.getText().replaceAll(",", "");
				expenselimit.setExpenseLimit(SelectCategory, Integer.parseInt(SelectExpenLimit));
				Category.setSelectedIndex(Category.getSelectedIndex()); // 현재 지출 한도 업데이트
				t.setText("");
			}
		});
		changexpenlimit.addFocusListener(new FocusListener() { // 포커스할 경우 초기화 시키는거
			public void focusGained(FocusEvent e) {
				JTextField t = (JTextField)e.getSource();
				if(t.getText().equals("금액 입력(엔터)")) t.setText(""); // 금액 입력 일 경우 초기화
			}
			public void focusLost(FocusEvent e) {}
		});
		
		// ========================================================================
		
		cate.setFont(new Font("함초롱바탕", Font.BOLD, 18)); 
		current.setFont(new Font("함초롱바탕", Font.BOLD, 18)); 
		change.setFont(new Font("함초롱바탕", Font.BOLD, 18)); 
		
		GOtoBack.addActionListener(backbutton); // 뒤로가기
		
	}
	
	private Stack<Character> addComma(String Money) {  					// 돈 콤마 추가해주는 함수
		Money = Money.replaceAll(",","");
		Money=Money.replaceAll("원","");
		Stack<Character> ShowMoney = new Stack<Character>();
		for(int i=0; i<Money.length(); i++) {
			if(i%3==0 && i!= 0) {
				ShowMoney.add(',');
			}
			ShowMoney.push(Money.charAt(Money.length()-1  - i));
		}
		return ShowMoney;
	}
	
	private class GoToBackPanel implements ActionListener{ // 뒤로가기 actionListener >> 뒤로가기 버튼에 적용
		private JFrame frame;
		public GoToBackPanel(JFrame frame) {
			this.frame = frame;
		}
		public void actionPerformed(ActionEvent e) {
			frame.setContentPane(new MainPanel(frame));
			frame.setVisible(true);
		}
	}
	
}