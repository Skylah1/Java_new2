package ui;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
/*
 * 사용 방법. BasicFrame 상속, 생성자 맨 위에 InitSetting()함수 이제는 호출하면 안됩니다!! 자동으로 호출해줘요!
 *  - 가운데 부분에 버튼같은거 넣는 방법 (수정됐음 BorderPanel >> CenterPanel)
 *  1. BorderPanel 배치관리자 설정해줘야함 null이나 gridLayout이나 ex) CenterPanel.setLayout(null);
 *  2. BorderPanel에 add해줘야함 ex) CenterPanel.add(new JButton("메롱"));
 *  - 이제 Acitve 클래스는 ButtonClick클래스에서 변경해주세요!
 *  - 버튼 클릭시 작동하게 하려면 MainButton.addActionListener(buttonclick); 이렇게 해줘야 해요!
 *  buttonclick은 이 BasicPanel클래스 안에 있기 때문에 buttonclick = new ButtonClick(frame)을 해줘야 합니다.
 *  여기에서 frame은 생성자로 받아줘야해요!
 *  버튼이나 라벨 고정되는 크기나 폰트 클래스로 만들어서 선언과 동시에 적용해주려 했는데 이부분 따로 얘기해야할것 같습니다.(서로 같은 디자인 해야하기 때문)
 *  뒤로가기 버튼, 확인 버튼도 얘기해야할것 같습니다.
 *  끝!
 */
public class BasicPanel extends JPanel{
	private JPanel BorderPanel = new JPanel(); // 가운데 부분의 패널
	private JPanel[] BlackBorder = new JPanel[4]; // 가장자리 검은색 테두리
	protected JPanel CenterPanel = new JPanel();
	protected JPanel NorthPanel = new JPanel();
	protected JPanel SouthPanel = new JPanel();
	protected JPanel WestPanel = new JPanel();
	protected JPanel EastPanel = new JPanel();
	
	protected ButtonClick buttonclick;

	public BasicPanel() {
		InitSetting();
	}
	
	protected void InitSetting() {									

		setBackground(Color.WHITE);			// 배경
		setLayout(new BorderLayout());		// 배치관리자 설정
		
		BorderPanel.setBackground(Color.WHITE);			// 패널 배경색 설정
		for(int i=0; i<4; i++) {
			BlackBorder[i] = new JPanel();
			BlackBorder[i].setBackground(Color.BLACK);
		}
		
		add(BorderPanel, BorderLayout.CENTER); // BorderLayout으로 넣어줌 이 inputPanel에 넣어줘야 할거임
		add(BlackBorder[0], BorderLayout.NORTH);
		add(BlackBorder[1], BorderLayout.SOUTH);
		add(BlackBorder[2], BorderLayout.WEST);
		add(BlackBorder[3], BorderLayout.EAST);
		
		BorderPanel.setLayout(new BorderLayout());
		BorderPanel.add(CenterPanel, BorderLayout.CENTER);
		BorderPanel.add(NorthPanel, BorderLayout.NORTH);
		BorderPanel.add(SouthPanel, BorderLayout.SOUTH);
		BorderPanel.add(WestPanel, BorderLayout.WEST);
		BorderPanel.add(EastPanel, BorderLayout.EAST);
		
		CenterPanel.setBackground(Color.WHITE);
		NorthPanel.setBackground(Color.WHITE);
		SouthPanel.setBackground(Color.WHITE);
		WestPanel.setBackground(Color.WHITE);
		EastPanel.setBackground(Color.WHITE);
	}
}