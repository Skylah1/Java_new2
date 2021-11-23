package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Stack;
import java.util.Vector;

import Category.*;


public class CategoryManagePanel extends BasicPanel {
	
	private ImageIcon catema = new ImageIcon("images/catema.png"); // 이미지 로딩
	private JLabel catemaimg=new JLabel("",catema,SwingConstants.CENTER); // 레이블 생성

	// 간격 조절
	private JLabel Line = new JLabel("aaaaaa"); // 라벨
	private JLabel Line1 = new JLabel("aaaaaaaaaaa"); // 라벨
	private JLabel Line2 = new JLabel("aaaaaaaaaa"); // 라벨
	private JLabel Line3 = new JLabel("aaaaaaaa"); // 라벨
	private JLabel Line4 = new JLabel("aaaaaaa"); // 라벨
	private JLabel Line5 = new JLabel("aaaaaaaa"); // 라벨
	
	CategoryInfo CInfo = new CategoryInfo(); // 카테고리 정보
	
	private JButton add = new JButton("추가"); // 버튼
	private JButton delete = new JButton("삭제");
	private JButton change = new JButton("변경");
	
	
	// ==========================================================================
	
	
	private JTextField addName = new JTextField(12);
	private JLabel addN = new JLabel("  추가할 카테고리 이름                "); // 라벨
	
	
	// ==========================================================================
	
	private Vector<String> delCateArr = CategoryDB.getCategory();
	private JComboBox<String> delCate = new JComboBox<String>(delCateArr); // 수치 저장할 콤보박스
	private JLabel delcate = new JLabel(" 삭제할 카테고리 선택                      "); // 라벨
	
	
	private Vector<String> chaCateArr = CategoryDB.getCategory();
	private JComboBox<String> chaCate = new JComboBox<String>(chaCateArr); // 수치 저장할 콤보박스
	private JLabel chacate = new JLabel(" 변경할 카테고리 선택                     "); // 라벨
	
	private JTextField chaName = new JTextField("변경할 카테고리 이름 입력",15);
	//private JLabel chaN = new JLabel("변경할 카테고리 이름 "); // 라벨

	
	// ==========================================================================
		
	public CategoryManagePanel(JFrame frame) { // 생성자
		buttonclick = new ButtonClick(frame);
		InitObject();
		CenterPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 30)); // 왼쪽정렬, 수직거리 30
		CenterPanel.add(Line);
		CenterPanel.add(catemaimg);
		
		CenterPanel.add(addN); // 순서대로 저장
		CenterPanel.add(addName);
		CenterPanel.add(Line1);
		CenterPanel.add(add);
		
		CenterPanel.add(delcate);
		CenterPanel.add(delCate);
		CenterPanel.add(Line2);
		CenterPanel.add(delete);
		
		
		CenterPanel.add(chacate);
		CenterPanel.add(chaCate);
		CenterPanel.add(Line3);
		
		CenterPanel.add(Line5);
		//CenterPanel.add(chaN);
		CenterPanel.add(chaName);
		CenterPanel.add(Line4);
		CenterPanel.add(change);
		

	}
	
	
	
	private void InitObject() { // 초기화 작업 및 초기세팅
		
		Line.setBackground(Color.WHITE);
		Line.setForeground(Color.WHITE);
		Line.setFont(new Font("함초롱바탕", Font.BOLD, 18)); 
		
		Line1.setBackground(Color.WHITE);
		Line1.setForeground(Color.WHITE);
		Line1.setFont(new Font("함초롱바탕", Font.BOLD, 18)); 
		
		Line2.setBackground(Color.WHITE);
		Line2.setForeground(Color.WHITE);
		Line2.setFont(new Font("함초롱바탕", Font.BOLD, 18)); 
		
		Line3.setBackground(Color.WHITE);
		Line3.setForeground(Color.WHITE);
		Line3.setFont(new Font("함초롱바탕", Font.BOLD, 18)); 
		
		Line4.setBackground(Color.WHITE);
		Line4.setForeground(Color.WHITE);
		Line4.setFont(new Font("함초롱바탕", Font.BOLD, 18)); 
		
		Line5.setBackground(Color.WHITE);
		Line5.setForeground(Color.WHITE);
		Line5.setFont(new Font("함초롱바탕", Font.BOLD, 18)); 
		
		// ========================================================================
		
		
		add.setBackground(new Color(244,244,244));
		add.setForeground(Color.BLACK);
		add.setFont(new Font("함초롱바탕", Font.BOLD, 17)); 
		
		
		delete.setBackground(new Color(244,244,244));
		delete.setForeground(Color.BLACK);
		delete.setFont(new Font("함초롱바탕", Font.BOLD, 17));
		
		
		change.setBackground(new Color(244,244,244));
		change.setForeground(Color.BLACK);
		change.setFont(new Font("함초롱바탕", Font.BOLD, 17)); 
		
		
		// ========================================================================
		
		addName.setBackground(Color.WHITE);
		addName.setForeground(Color.BLACK);
		addName.setFont(new Font("함초롱바탕", Font.BOLD, 17)); 
		
		delCate.setBackground(Color.WHITE);
		delCate.setForeground(Color.BLACK);
		delCate.setFont(new Font("함초롱바탕", Font.BOLD, 17)); 
		
		
		chaCate.setBackground(Color.WHITE);
		chaCate.setForeground(Color.BLACK);
		chaCate.setFont(new Font("함초롱바탕", Font.BOLD, 17)); 
		
		chaName.setBackground(Color.WHITE);
		chaName.setForeground(Color.BLACK);
		chaName.setFont(new Font("함초롱바탕", Font.BOLD, 17)); 
		
		// ========================================================================
		
		
		addN.setFont(new Font("함초롱바탕", Font.BOLD, 17)); 
		delcate.setFont(new Font("함초롱바탕", Font.BOLD, 17)); 
		chacate.setFont(new Font("함초롱바탕", Font.BOLD, 17)); 
		//chaN.setFont(new Font("함초롱바탕", Font.BOLD, 18));
		
		delCate.setPreferredSize(new Dimension(185,30)); //combobox 크기 조절
		chaCate.setPreferredSize(new Dimension(185,30));
		
		add.addActionListener(new ActionListener() { //추가
			public void actionPerformed(ActionEvent e) {
				CInfo.addCategory(addName.getText()); //카테고리 추가
			}
		});
		
		delete.addActionListener(new ActionListener() { //삭제
			public void actionPerformed(ActionEvent e) {
				String delCategory = delCate.getSelectedItem().toString(); //combobox 선택된 값 String으로 얻기
				CInfo.deleteCategory(delCategory); //카테고리 삭제
			}
		});
		
		change.addActionListener(new ActionListener() { //변경
			public void actionPerformed(ActionEvent e) {
				String chaCategory = chaCate.getSelectedItem().toString(); //combobox 선택된 값 String으로 얻기
				CInfo.editCategory( chaCategory, chaName.getText()); //(chaCategory에서 chaName로 카테고리 변경
			}
		});
		
		chaName.addFocusListener(new FocusListener() { // 포커스할 경우 초기화 시키는거
			public void focusGained(FocusEvent e) {
				JTextField t = (JTextField)e.getSource();
				if(t.getText().equals("변경할 카테고리 이름 입력")) t.setText(""); // 금액 입력 일 경우 초기화
			}
			public void focusLost(FocusEvent e) {}
		});
		
	}
}