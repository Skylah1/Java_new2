/*
 * 320줄에 DB저장 메소드 필요
 * */
package ui;

import java.awt.Color;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.LocalDate;
import java.util.Stack;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import Category.CategoryDB;

import javax.swing.JFrame;

public class InputPanel extends BasicPanel{
	
	// 간격 조절
		private JLabel Line = new JLabel("aaaaaaaa"); // 라벨
		private JLabel Line1 = new JLabel("aaaaaaaaa"); // 라벨
		private JLabel Line2 = new JLabel("aaaaaaaaaaaaaaaaaaaaa"); // 라벨
		private JLabel Line3 = new JLabel("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"); // 라벨
		private JLabel Line4 = new JLabel("aaaaaaaaaaaaaaaaaaaaaaaaaaa"); // 라벨

		
		private ImageIcon input = new ImageIcon("images/input.png"); // 이미지 로딩
		private JLabel inputimg=new JLabel("",input,SwingConstants.CENTER); // 레이블 생성
		
		private String[] YearArray = new String[16]; // 문자열 배열 년, 월, 일 
		private String[] MonthArray = new String[12];
		private Vector<String> DateArray = new Vector<String>();
		
		Vector<String> Clist = CategoryDB.getCategory();	//카테고리 목록 저장 
	
		private String[] CategoryArray;
		
		private JComboBox<String> Year; // 년, 월, 일 저장할 콤보박스
		private JComboBox<String> Month;
		private JComboBox<String> Date;
		private JComboBox Category = new JComboBox(Clist);
		
		private ButtonGroup KRW_USD = new ButtonGroup(); // 라디오 버튼 그룹
		
		private JRadioButton KRW = new JRadioButton("  원화                     ", true); // 라디오 버튼
		private JRadioButton USD = new JRadioButton("  외화                     ");
		
		private JLabel Calendar = new JLabel("날짜 설정         "); // 라벨
		private JLabel YearLabel = new JLabel(" 년  ");
		private JLabel MonthLabel = new JLabel(" 월  ");
		private JLabel DateLabel = new JLabel(" 일");
		private JLabel CategoryLabel = new JLabel("카테고리 설정 ");
		private JLabel PriceLabel = new JLabel(" 금액 입력       ");
		
		private BackButton GOtoBack = new BackButton();
		
		private JTextField Input = new JTextField("금액 입력(엔터)", 10); ////////////// 이걸로 입력
		private GoToBackPanel backbutton;
		
		private Vector<String> ForDBString = new Vector<String>(); // DB에 저장할 string
		private String ForDBYear; // DB저장용
		private String ForDBMonth;
		private String ForDBDate;
		private String ForDBCategory;
		private String ForDBisKRW = "0";
		private String ForDBMoney;
		
		public InputPanel(JFrame frame) { // 생성자
			super();
			backbutton = new GoToBackPanel(frame); // BasicPanel에 있는 buttonclick
			InitObject();
			CenterPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 30)); // 왼쪽정렬, 수직거리 30
			CenterPanel.add(Line);
			CenterPanel.add(inputimg); // 순서대로 저장
			CenterPanel.add(Line1);
			CenterPanel.add(Line2);
			CenterPanel.add(Calendar);
			CenterPanel.add(Year);
			CenterPanel.add(YearLabel);
			CenterPanel.add(Month);
			CenterPanel.add(MonthLabel);
			CenterPanel.add(Date);
			CenterPanel.add(DateLabel);
			CenterPanel.add(Line3);
			CenterPanel.add(CategoryLabel);
			CenterPanel.add(Category);
			CenterPanel.add(Line4);
			CenterPanel.add(KRW);
			CenterPanel.add(USD);
			CenterPanel.add(PriceLabel);
			CenterPanel.add(Input);
			SouthPanel.setLayout(new FlowLayout(FlowLayout.LEFT)); // 뒤로가기
			SouthPanel.add(GOtoBack);
			
			SettingComboBox();
			
			InitSetting();
		}
		 
		private void InitObject() { // 초기화 작업 및 초기세팅
			
			Line.setBackground(Color.WHITE);
			Line.setForeground(Color.WHITE);
			Line.setFont(new Font("함초롱바탕", Font.BOLD, 16)); 
			
			Line1.setBackground(Color.WHITE);
			Line1.setForeground(Color.WHITE);
			Line1.setFont(new Font("함초롱바탕", Font.BOLD, 16)); 
			
			Line2.setBackground(Color.WHITE);
			Line2.setForeground(Color.WHITE);
			Line2.setFont(new Font("함초롱바탕", Font.BOLD, 16)); 
			
			Line3.setBackground(Color.WHITE);
			Line3.setForeground(Color.WHITE);
			Line3.setFont(new Font("함초롱바탕", Font.BOLD, 16)); 
			
			Line4.setBackground(Color.WHITE);
			Line4.setForeground(Color.WHITE);
			Line4.setFont(new Font("함초롱바탕", Font.BOLD, 16)); 
			
			for(int i=0; i<16; i++) YearArray[i] = String.valueOf(i+2015); // 2015~2030년 구성
			Year = new JComboBox<String>(YearArray);
			Year.setBackground(Color.WHITE);
			Year.setForeground(Color.BLACK);
			Year.setFont(new Font("함초롱바탕", Font.BOLD, 16));
			Year.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e){
					JComboBox<String> cb = (JComboBox<String>)e.getSource();
					ForDBYear = (String) cb.getSelectedItem();
				}
			});
			
			for(int i=0; i<28; i++) DateArray.add(String.valueOf(i+1));
			for(int i=0; i<12; i++) MonthArray[i] = String.valueOf(i+1); // 1~12월 구성
			Month = new JComboBox<String>(MonthArray);
			Month.setBackground(Color.WHITE);
			Month.setForeground(Color.BLACK);
			Month.setFont(new Font("함초롱바탕", Font.BOLD, 16)); 
			Month.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JComboBox<String> cb = (JComboBox<String>)e.getSource();
					ForDBMonth = (String) cb.getSelectedItem();
					if(ForDBMonth.equals("1") || ForDBMonth.equals("3")||ForDBMonth.equals("5")||ForDBMonth.equals("7")|| // 달에 따른 일 콤보박스 변경
							ForDBMonth.equals("8")||ForDBMonth.equals("10")||ForDBMonth.equals("12")) {
						if(!DateArray.contains("29")) {
							DateArray.add("29"); 
							DateArray.add("30"); 
							DateArray.add("31"); 
						}
						else if(!DateArray.contains("30")) {
							DateArray.add("30"); 
							DateArray.add("31"); 
						}
						else if(!DateArray.contains("31")) DateArray.add("31"); 
					}
					else if(ForDBMonth.equals("4") || ForDBMonth.equals("6")||ForDBMonth.equals("9")||ForDBMonth.equals("11")) {
						if(!DateArray.contains("29")) {
							DateArray.add("29"); 
							DateArray.add("30"); 
						}
						else if(!DateArray.contains("30")) DateArray.add("30"); 
						else if(DateArray.contains("31")) DateArray.remove("31");
					}
					else if(ForDBMonth.equals("2")) {
						if(ForDBYear.equals("2016") || ForDBYear.equals("2020") || ForDBYear.equals("2024") ||
								ForDBYear.equals("2028")) {
							if(!DateArray.contains("28")) DateArray.add("29");
							DateArray.remove("31"); DateArray.remove("30");
						}
						else {
							DateArray.remove("31"); DateArray.remove("30"); DateArray.remove("29");
						}
					}
				}
			});
			
			Date = new JComboBox(DateArray); // 여기 해야함
			Date.setBackground(Color.WHITE);
			Date.setForeground(Color.BLACK);
			Date.setFont(new Font("함초롱바탕", Font.BOLD, 16)); 
			Date.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JComboBox cb = (JComboBox)e.getSource();
					ForDBDate = (String) cb.getSelectedItem();
				}
			});
			
			Category.setBackground(Color.WHITE);
			Category.setForeground(Color.BLACK);
			Category.setFont(new Font("함초롱바탕", Font.BOLD, 16)); 
			Category.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JComboBox cb = (JComboBox)e.getSource();
					ForDBCategory = (String) cb.getSelectedItem();
				}
			});
			
			// ---------------------------------------------------------------------------------콤보박스
			KRW.setBackground(Color.WHITE);
			KRW.setForeground(Color.BLACK);
			KRW.setFont(new Font("함초롱바탕", Font.BOLD, 17)); 
			USD.setBackground(Color.WHITE);
			USD.setForeground(Color.BLACK);
			USD.setFont(new Font("함초롱바탕", Font.BOLD, 17)); 
			KRW_USD.add(KRW); KRW_USD.add(USD);
			KRW.addItemListener(new ForRadioButton());
			USD.addItemListener(new ForRadioButton());
			
			//----------------------------------------------------------------------------------라디오 버튼
			
			Calendar.setFont(new Font("함초롱바탕", Font.BOLD, 17)); 
			YearLabel.setFont(new Font("함초롱바탕", Font.BOLD, 17)); 
			MonthLabel.setFont(new Font("함초롱바탕", Font.BOLD, 17)); 
			DateLabel.setFont(new Font("함초롱바탕", Font.BOLD, 17)); 
			CategoryLabel.setFont(new Font("함초롱바탕", Font.BOLD, 17)); 
			PriceLabel.setFont(new Font("함초롱바탕", Font.BOLD, 17)); 
			
			// ---------------------------------------------------------------------------------라벨
			
			GOtoBack.addActionListener(backbutton); // 뒤로가기 구현

			// ----------------------------------------------------------------------------------버튼
			
			Input.setFont(new Font("함초롱바탕", Font.BOLD, 16));
			// Input.setBorder(new LineBorder(Color.BLACK,4)); // 테두리
			Input.setBackground(Color.WHITE);
			Input.setForeground(Color.BLACK);
			Input.setCaretColor(Color.BLACK); // 깜박이 색
			Input.addActionListener(new InputData());
			Input.addKeyListener(new KeyAdapter() {									// 입력할때 콤마 표시
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
			
			Input.addFocusListener(new FocusListener() { // 포커스할 경우 초기화 시키는거
				public void focusGained(FocusEvent e) {
					JTextField t = (JTextField)e.getSource();
					if(t.getText().equals("금액 입력(엔터)")) t.setText(""); // 금액 입력 일 경우 초기화
				}
				public void focusLost(FocusEvent e) {}
			});
				
			// ----------------------------------------------------------------------------------텍스트 필드
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
		
		private void SettingComboBox() { // 콤보박스 초기 날짜 설정(현재 날짜로)
			LocalDate now = LocalDate.now();
			Year.setSelectedIndex(now.getYear()-2015);
			Month.setSelectedIndex(now.getMonthValue()-1);
			Date.setSelectedIndex(now.getDayOfMonth()-1);
			Category.setSelectedIndex(0);
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
		
		private class InputData implements ActionListener{ // 텍스트필드 actionListenr  // 여기에서 DB저장!!
			public void actionPerformed(ActionEvent e) {
				JTextField t = (JTextField)e.getSource();
				ForDBMoney = (String)t.getText();
				ForDBMoney = ForDBMoney.replaceAll(",", "");
				ForDBString.add(ForDBYear + ForDBMonth + ForDBDate); // 여기에 20211126 으로 저장됨
				ForDBString.add(ForDBisKRW); // 0, 1 저장(원화 외화)
				ForDBString.add(ForDBCategory); // 카테고리 저장
				ForDBString.add(ForDBMoney); // 금액 저장
				// 여기 ForDBString에 정보가 저장되어있습니다! Vector<String>으로 되어있어요 ***************************************여기요 여기! *********************
				// << 여기에서 ForDBString이용해서 DB에 저장하면 됩니다.
				for(int i=0; i<4; i++) { // << 이거는 단순 확인용 출력임 
					System.out.print(ForDBString.toArray()[i]);
				}
				System.out.println();
				ForDBString.clear();

				t.setText("");
			}
		}
		
		private class ForRadioButton implements ItemListener{ // 라디오 버튼 ItemListener
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
					if(e.getItem() == KRW) ForDBisKRW = "0"; 
					else ForDBisKRW = "1"; 
				}
			}
		}
	}