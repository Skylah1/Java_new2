package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ButtonClick implements ActionListener{
	private JFrame frame;
	private JPanel BackPanel;
	private boolean isBackButton = false;
	
	public ButtonClick(JFrame frame) {
		this.frame = frame;
	}
	
	public ButtonClick(JFrame frame, JPanel BackPanel) {
		this.frame = frame;
		this.BackPanel = BackPanel;
		isBackButton = true;
	}
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton)e.getSource();
		String compareText = b.getText().replace(" ", "");
		
		if(compareText.equals("입력")) {
			frame.setContentPane(new InputPanel(frame));
			frame.setVisible(true);
		}
		else if(compareText.equals("조회")) { // "조회"일 경우 InquiryFrame으로 넘어감
			frame.setContentPane(new InquiryPanel(frame));
			frame.setVisible(true);
		}
		else if(compareText.equals("지출한도설정")) { // "지출 한도 설정"일 경우 ManageFrame으로 넘어감
			frame.setContentPane(new ManagePanel(frame));
			frame.setVisible(true);
		}
		else if(compareText.equals("카테고리관리")) { // "카테고리 관리"일 경우 CategoryManageFrame으로 넘어감
			frame.setContentPane(new CategoryManagePanel(frame));
			frame.setVisible(true);
		}
		else if(compareText.equals("알림")) { // "카테고리 관리"일 경우 CategoryManageFrame으로 넘어감
			// 여기는 아직 미완
			frame.setVisible(true);
		}
		
	}
}