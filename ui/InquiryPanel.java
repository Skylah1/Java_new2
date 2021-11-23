package ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Stack;
import java.util.Vector;




public class InquiryPanel extends BasicPanel {
	
	private ImageIcon inquiry = new ImageIcon("images/inquiry.png"); // 이미지 로딩
	private JLabel inquiryimg=new JLabel("",inquiry,SwingConstants.CENTER); // 레이블 생성
	
	// 간격 조절
	private JLabel Line = new JLabel("aaaaaaaaaa"); // 라벨
	private JLabel Line1 = new JLabel("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"); // 라벨
	private JLabel Line2 = new JLabel("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"); // 라벨
	private JLabel Line3 = new JLabel("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"); // 라벨
	private JLabel Line4 = new JLabel("aaaaaaaaaa"); // 라벨
	private JLabel Line5 = new JLabel("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"); // 라벨
	
	
	// ========================================================================================
	
	private JLabel Choose1 = new JLabel(" 조회할 목록 선택                                         "); // 라벨
	private JLabel chNum = new JLabel("  수치           "); // 라벨
	private JLabel chChart = new JLabel("\n  차트           ");
	
	private JLabel Choose2 = new JLabel(" 예상 수입/지출 금액 조회 방식 선택       "); // 라벨
	private ButtonGroup num_chart = new ButtonGroup(); // 라디오 버튼 그룹
	private JRadioButton num1 = new JRadioButton("다음달          ", true); // 라디오 버튼
	private JRadioButton num2 = new JRadioButton("이번달  ");
	private JRadioButton chart1 = new JRadioButton("월 별            "); // 라디오 버튼
	private JRadioButton chart2 = new JRadioButton("카테고리별");
	
	
	
	// ========================================================================================
	
	private ButtonGroup incom_expen = new ButtonGroup(); // 라디오 버튼 그룹
	private JRadioButton income = new JRadioButton("  수입                        ", true); // 라디오 버튼
	private JRadioButton expenses = new JRadioButton("  지출 ");
	
	
	// ========================================================================================

	public InquiryPanel(JFrame frame) { // 생성자
		
		buttonclick = new ButtonClick(frame);
		InitObject();
		CenterPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 30)); // 왼쪽정렬, 수직거리 30
		CenterPanel.add(Line);
		CenterPanel.add(inquiryimg); // 순서대로 저장
		CenterPanel.add(Line5);
		
		CenterPanel.add(Choose1);
		CenterPanel.add(income);
		CenterPanel.add(expenses);
		CenterPanel.add(Line2);
		
		CenterPanel.add(Choose2);
		CenterPanel.add(chNum);
		CenterPanel.add(num1);
		CenterPanel.add(num2);
		//CenterPanel.add(Line4);

		CenterPanel.add(chChart);
		CenterPanel.add(chart1);
		CenterPanel.add(chart2);
		
	}
	
	
	
	
	private void InitObject() { // 초기화 작업 및 초기세팅
		
		Line.setBackground(Color.WHITE);
		Line.setForeground(Color.WHITE);
		Line.setFont(new Font("함초롱바탕", Font.BOLD, 12)); 
		
		Line1.setBackground(Color.WHITE);
		Line1.setForeground(Color.WHITE);
		Line1.setFont(new Font("함초롱바탕", Font.BOLD, 10)); 
		
		Line2.setBackground(Color.WHITE);
		Line2.setForeground(Color.WHITE);
		Line2.setFont(new Font("함초롱바탕", Font.BOLD, 10)); 
		
		Line3.setBackground(Color.WHITE);
		Line3.setForeground(Color.WHITE);
		Line3.setFont(new Font("함초롱바탕", Font.BOLD, 10)); 
		
		Line4.setBackground(Color.WHITE);
		Line4.setForeground(Color.WHITE);
		Line4.setFont(new Font("함초롱바탕", Font.BOLD, 10)); 
		
		Line5.setBackground(Color.WHITE);
		Line5.setForeground(Color.WHITE);
		Line5.setFont(new Font("함초롱바탕", Font.BOLD, 10));
		
		// ========================================================================
		
		Choose1.setBackground(Color.BLACK);
		Choose1.setForeground(Color.BLACK);
		Choose1.setFont(new Font("함초롱바탕", Font.BOLD, 17)); 
		
		Choose2.setBackground(Color.BLACK);
		Choose2.setForeground(Color.BLACK);
		Choose2.setFont(new Font("함초롱바탕", Font.BOLD, 17)); 
		
		
		num1.setBackground(Color.WHITE);
		num1.setForeground(Color.BLACK);
		num1.setFont(new Font("함초롱바탕", Font.BOLD, 17)); 
		
		num2.setBackground(Color.WHITE);
		num2.setForeground(Color.BLACK);
		num2.setFont(new Font("함초롱바탕", Font.BOLD, 17)); 
		
	
		chart1.setBackground(Color.WHITE);
		chart1.setForeground(Color.BLACK);
		chart1.setFont(new Font("함초롱바탕", Font.BOLD, 17));
		
		chart2.setBackground(Color.WHITE);
		chart2.setForeground(Color.BLACK);
		chart2.setFont(new Font("함초롱바탕", Font.BOLD, 17));
		
		num_chart.add(num1); num_chart.add(num2);
		num_chart.add(chart1); num_chart.add(chart2);
		
		// ========================================================================
		
		
		income.setBackground(Color.WHITE);
		income.setForeground(Color.BLACK);
		income.setFont(new Font("함초롱바탕", Font.BOLD, 17)); 
		
		expenses.setBackground(Color.WHITE);
		expenses.setForeground(Color.BLACK);
		expenses.setFont(new Font("함초롱바탕", Font.BOLD, 17)); 
		
		incom_expen.add(income); incom_expen.add(expenses);
		
		// ========================================================================
		
		chNum.setFont(new Font("함초롱바탕", Font.BOLD, 17)); 
		chChart.setFont(new Font("함초롱바탕", Font.BOLD, 17)); 
		
	}

}