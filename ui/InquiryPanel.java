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
	
	private ImageIcon inquiry = new ImageIcon("images/inquiry.png"); // �̹��� �ε�
	private JLabel inquiryimg=new JLabel("",inquiry,SwingConstants.CENTER); // ���̺� ����
	
	// ���� ����
	private JLabel Line = new JLabel("aaaaaaaaaa"); // ��
	private JLabel Line1 = new JLabel("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"); // ��
	private JLabel Line2 = new JLabel("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"); // ��
	private JLabel Line3 = new JLabel("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"); // ��
	private JLabel Line4 = new JLabel("aaaaaaaaaa"); // ��
	private JLabel Line5 = new JLabel("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"); // ��
	
	
	// ========================================================================================
	
	private JLabel Choose1 = new JLabel(" ��ȸ�� ��� ����                                         "); // ��
	private JLabel chNum = new JLabel("  ��ġ           "); // ��
	private JLabel chChart = new JLabel("\n  ��Ʈ           ");
	
	private JLabel Choose2 = new JLabel(" ���� ����/���� �ݾ� ��ȸ ��� ����       "); // ��
	private ButtonGroup num_chart = new ButtonGroup(); // ���� ��ư �׷�
	private JRadioButton num1 = new JRadioButton("������          ", true); // ���� ��ư
	private JRadioButton num2 = new JRadioButton("�̹���  ");
	private JRadioButton chart1 = new JRadioButton("�� ��            "); // ���� ��ư
	private JRadioButton chart2 = new JRadioButton("ī�װ���");
	
	
	
	// ========================================================================================
	
	private ButtonGroup incom_expen = new ButtonGroup(); // ���� ��ư �׷�
	private JRadioButton income = new JRadioButton("  ����                        ", true); // ���� ��ư
	private JRadioButton expenses = new JRadioButton("  ���� ");
	
	
	// ========================================================================================

	public InquiryPanel(JFrame frame) { // ������
		
		buttonclick = new ButtonClick(frame);
		InitObject();
		CenterPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 30)); // ��������, �����Ÿ� 30
		CenterPanel.add(Line);
		CenterPanel.add(inquiryimg); // ������� ����
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
	
	
	
	
	private void InitObject() { // �ʱ�ȭ �۾� �� �ʱ⼼��
		
		Line.setBackground(Color.WHITE);
		Line.setForeground(Color.WHITE);
		Line.setFont(new Font("���ʷչ���", Font.BOLD, 12)); 
		
		Line1.setBackground(Color.WHITE);
		Line1.setForeground(Color.WHITE);
		Line1.setFont(new Font("���ʷչ���", Font.BOLD, 10)); 
		
		Line2.setBackground(Color.WHITE);
		Line2.setForeground(Color.WHITE);
		Line2.setFont(new Font("���ʷչ���", Font.BOLD, 10)); 
		
		Line3.setBackground(Color.WHITE);
		Line3.setForeground(Color.WHITE);
		Line3.setFont(new Font("���ʷչ���", Font.BOLD, 10)); 
		
		Line4.setBackground(Color.WHITE);
		Line4.setForeground(Color.WHITE);
		Line4.setFont(new Font("���ʷչ���", Font.BOLD, 10)); 
		
		Line5.setBackground(Color.WHITE);
		Line5.setForeground(Color.WHITE);
		Line5.setFont(new Font("���ʷչ���", Font.BOLD, 10));
		
		// ========================================================================
		
		Choose1.setBackground(Color.BLACK);
		Choose1.setForeground(Color.BLACK);
		Choose1.setFont(new Font("���ʷչ���", Font.BOLD, 17)); 
		
		Choose2.setBackground(Color.BLACK);
		Choose2.setForeground(Color.BLACK);
		Choose2.setFont(new Font("���ʷչ���", Font.BOLD, 17)); 
		
		
		num1.setBackground(Color.WHITE);
		num1.setForeground(Color.BLACK);
		num1.setFont(new Font("���ʷչ���", Font.BOLD, 17)); 
		
		num2.setBackground(Color.WHITE);
		num2.setForeground(Color.BLACK);
		num2.setFont(new Font("���ʷչ���", Font.BOLD, 17)); 
		
	
		chart1.setBackground(Color.WHITE);
		chart1.setForeground(Color.BLACK);
		chart1.setFont(new Font("���ʷչ���", Font.BOLD, 17));
		
		chart2.setBackground(Color.WHITE);
		chart2.setForeground(Color.BLACK);
		chart2.setFont(new Font("���ʷչ���", Font.BOLD, 17));
		
		num_chart.add(num1); num_chart.add(num2);
		num_chart.add(chart1); num_chart.add(chart2);
		
		// ========================================================================
		
		
		income.setBackground(Color.WHITE);
		income.setForeground(Color.BLACK);
		income.setFont(new Font("���ʷչ���", Font.BOLD, 17)); 
		
		expenses.setBackground(Color.WHITE);
		expenses.setForeground(Color.BLACK);
		expenses.setFont(new Font("���ʷչ���", Font.BOLD, 17)); 
		
		incom_expen.add(income); incom_expen.add(expenses);
		
		// ========================================================================
		
		chNum.setFont(new Font("���ʷչ���", Font.BOLD, 17)); 
		chChart.setFont(new Font("���ʷչ���", Font.BOLD, 17)); 
		
	}

}