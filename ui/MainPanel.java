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

public class MainPanel extends BasicPanel { // GUIƲ Frame
	enum Button{
		expenseIncomeInputButton, categoryManageButton, inquiryButton,
		manageButton, alarmButton
	}
	
	
	private JButton[] MainButton = new JButton[5]; // ��ư
	private ImageIcon bulb = new ImageIcon("images/bulb.png"); // �̹��� �ε�
	private JLabel tip = new JLabel("Today's Tip! ",bulb,SwingConstants.LEFT); // ������ �� ���̺�
	//private JLabel antProgram = new JLabel("       ������ �˶���"); // ���α׷� �̸� ���̺�
	private ImageIcon img = new ImageIcon("images/ants.png"); // �̹��� �ε�
	private JLabel antProgram=new JLabel("",img,SwingConstants.LEFT); // ���̺� ����
	
	public MainPanel(JFrame frame) {
		buttonclick = new ButtonClick(frame);
		MainButton[Button.expenseIncomeInputButton.ordinal()] = new JButton("��                  ��"); // 0
		MainButton[Button.categoryManageButton.ordinal()] = new JButton("��                  ȸ"); // 1
		MainButton[Button.inquiryButton.ordinal()] = new JButton("����   �ѵ�   ����"); // 2 
		MainButton[Button.manageButton.ordinal()] = new JButton("ī�װ�      ����"); // 3
		MainButton[Button.alarmButton.ordinal()] = new JButton("��                  ��"); // 4
		
		GUISetting(); // ��ư ��ġ ����
	}
	
	/* BasicFrame�� �⺻���� Ʋ �̿��Ұ���
	 * �⺻���� �׵θ�, �־���� �� �г��� BasicPanel���� �غ�Ǿ� ����.
	 * ��ư���� BorderPanel�� �־���� �ϹǷ� BorderPanel�� ��ġ������ BorderPanel���� �����ϰ� ���⿡ ��ư�� �־���
	 * */
	private void GUISetting() {
		CenterPanel.setLayout(new GridLayout(7,1,1,30)); // �̰� �ۼ��Ǵ� �κ��� GridLayout(5�� 1�� vGap = 25)
		
		tip.setFont(new Font("���� ���", Font.BOLD, 17)); // �� ����
		antProgram.setFont(new Font("���� ���", Font.BOLD, 30)); // ���α׷� �̸� ����
		CenterPanel.add(antProgram); 
		
		for(int i=0; i<5; i++) { // CenterPanel�� ������ ��ư�� �߰�
			MainButton[i].setBackground(Color.BLACK);
			MainButton[i].setForeground(Color.WHITE);
			MainButton[i].setFont(new Font("���� ���", Font.BOLD, 18));
			MainButton[i].addActionListener(buttonclick);
			CenterPanel.add(MainButton[i]); // ���⿡ ��ư�� GridLayout���� �߰�
		}
		CenterPanel.add(tip);
	}
}