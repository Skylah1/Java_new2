package ui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import javax.swing.JFrame;

public class MainPanel extends BasicPanel { // GUI틀 Frame
	enum Button{
		expenseIncomeInputButton, categoryManageButton, inquiryButton,
		manageButton, alarmButton
	}
	
	
	private JButton[] MainButton = new JButton[5]; // 버튼
	private ImageIcon bulb = new ImageIcon("images/bulb.png"); // 이미지 로딩
	private JLabel tip = new JLabel("Today's Tip! ",bulb,SwingConstants.LEFT); // 오늘의 팁 레이블
	//private JLabel antProgram = new JLabel("       개미의 알뜰살뜰"); // 프로그램 이름 레이블
	private ImageIcon img = new ImageIcon("images/ants.png"); // 이미지 로딩
	private JLabel antProgram=new JLabel("",img,SwingConstants.LEFT); // 레이블 생성
	
	public MainPanel(JFrame frame) {
		buttonclick = new ButtonClick(frame);
		MainButton[Button.expenseIncomeInputButton.ordinal()] = new JButton("입                  력"); // 0
		MainButton[Button.categoryManageButton.ordinal()] = new JButton("조                  회"); // 1
		MainButton[Button.inquiryButton.ordinal()] = new JButton("지출   한도   설정"); // 2 
		MainButton[Button.manageButton.ordinal()] = new JButton("카테고리      관리"); // 3
		MainButton[Button.alarmButton.ordinal()] = new JButton("알                  림"); // 4
		
		GUISetting(); // 버튼 위치 조정
	}
	
	/* BasicFrame의 기본적인 틀 이용할거임
	 * 기본적인 테두리, 넣어줘야 할 패널은 BasicPanel에서 준비되어 있음.
	 * 버튼들은 BorderPanel에 넣어줘야 하므로 BorderPanel의 배치관리자 BorderPanel으로 설정하고 여기에 버튼을 넣어줌
	 * */
	private void GUISetting() {
		CenterPanel.setLayout(new GridLayout(7,1,1,30)); // 이게 작성되는 부분의 GridLayout(5행 1열 vGap = 25)
		
		tip.setFont(new Font("맑은 고딕", Font.BOLD, 17)); // 팁 설정
		antProgram.setFont(new Font("맑은 고딕", Font.BOLD, 30)); // 프로그램 이름 설정
		CenterPanel.add(antProgram); 
		
		for(int i=0; i<5; i++) { // CenterPanel에 설정한 버튼들 추가
			MainButton[i].setBackground(Color.BLACK);
			MainButton[i].setForeground(Color.WHITE);
			MainButton[i].setFont(new Font("맑은 고딕", Font.BOLD, 18));
			MainButton[i].addActionListener(buttonclick);
			CenterPanel.add(MainButton[i]); // 여기에 버튼들 GridLayout으로 추가
		}
		CenterPanel.add(tip);
	}
}