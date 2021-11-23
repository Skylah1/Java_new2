package ui;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
/*
 * ��� ���. BasicFrame ���, ������ �� ���� InitSetting()�Լ� ������ ȣ���ϸ� �ȵ˴ϴ�!! �ڵ����� ȣ�������!
 *  - ��� �κп� ��ư������ �ִ� ��� (�������� BorderPanel >> CenterPanel)
 *  1. BorderPanel ��ġ������ ����������� null�̳� gridLayout�̳� ex) CenterPanel.setLayout(null);
 *  2. BorderPanel�� add������� ex) CenterPanel.add(new JButton("�޷�"));
 *  - ���� Acitve Ŭ������ ButtonClickŬ�������� �������ּ���!
 *  - ��ư Ŭ���� �۵��ϰ� �Ϸ��� MainButton.addActionListener(buttonclick); �̷��� ����� �ؿ�!
 *  buttonclick�� �� BasicPanelŬ���� �ȿ� �ֱ� ������ buttonclick = new ButtonClick(frame)�� ����� �մϴ�.
 *  ���⿡�� frame�� �����ڷ� �޾�����ؿ�!
 *  ��ư�̳� �� �����Ǵ� ũ�⳪ ��Ʈ Ŭ������ ���� ����� ���ÿ� �������ַ� �ߴµ� �̺κ� ���� ����ؾ��Ұ� �����ϴ�.(���� ���� ������ �ؾ��ϱ� ����)
 *  �ڷΰ��� ��ư, Ȯ�� ��ư�� ����ؾ��Ұ� �����ϴ�.
 *  ��!
 */
public class BasicPanel extends JPanel{
	private JPanel BorderPanel = new JPanel(); // ��� �κ��� �г�
	private JPanel[] BlackBorder = new JPanel[4]; // �����ڸ� ������ �׵θ�
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

		setBackground(Color.WHITE);			// ���
		setLayout(new BorderLayout());		// ��ġ������ ����
		
		BorderPanel.setBackground(Color.WHITE);			// �г� ���� ����
		for(int i=0; i<4; i++) {
			BlackBorder[i] = new JPanel();
			BlackBorder[i].setBackground(Color.BLACK);
		}
		
		add(BorderPanel, BorderLayout.CENTER); // BorderLayout���� �־��� �� inputPanel�� �־���� �Ұ���
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