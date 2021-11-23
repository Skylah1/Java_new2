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

	// ���� ����
	private JLabel Line = new JLabel("aaaaaaaaaa"); // ��
	private JLabel Line1 = new JLabel("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"); // ��
	private JLabel Line2 = new JLabel("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"); // ��
	private JLabel Line3 = new JLabel("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"); // ��
		
	private ImageIcon manage = new ImageIcon("images/manage.png"); // �̹��� �ε�
	private JLabel manageimg=new JLabel("",manage,SwingConstants.CENTER); // ���̺� ����
	
	Vector<String> Clist = CategoryDB.getCategory();	//ī�װ� ��� ���� 
	
	private JComboBox Category; // ī�װ� �޺��ڽ�
	private JLabel cate = new JLabel("  ī�װ� ����         "); // ��
	
	// =======================================================================
	
	private JTextField curexpenlimit= new JTextField(10);
	private JLabel current = new JLabel("  ���� ���� �ѵ�        "); // ��
	private Vector<Integer> CLimitList = CategoryDB.getCategoryLimit();
	
	private JTextField changexpenlimit= new JTextField("�ݾ� �Է�(����)",10);
	private JLabel change = new JLabel("  ������ ���� �ѵ�    "); // ��
	
	private GoToBackPanel backbutton;
	private BackButton GOtoBack = new BackButton();
	
	private String SelectCategory;
	private String SelectExpenLimit;
	
	public ManagePanel(JFrame frame) { // ������
		buttonclick = new ButtonClick(frame);
		backbutton = new GoToBackPanel(frame);
		InitObject();
		CenterPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 30)); // ��������, �����Ÿ� 30
		CenterPanel.add(Line); // ������� ����
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
		
		SouthPanel.setLayout(new FlowLayout(FlowLayout.LEFT)); // �ڷΰ���
		SouthPanel.add(GOtoBack);
	}
	
	
	
	private void InitObject() { // �ʱ�ȭ �۾� �� �ʱ⼼��
		
		Line.setBackground(Color.WHITE);
		Line.setForeground(Color.WHITE);
		Line.setFont(new Font("���ʷչ���", Font.BOLD, 12)); 
		
		Line1.setBackground(Color.WHITE);
		Line1.setForeground(Color.WHITE);
		Line1.setFont(new Font("���ʷչ���", Font.BOLD, 12)); 
		
		Line2.setBackground(Color.WHITE);
		Line2.setForeground(Color.WHITE);
		Line2.setFont(new Font("���ʷչ���", Font.BOLD, 12)); 
		
		Line3.setBackground(Color.WHITE);
		Line3.setForeground(Color.WHITE);
		Line3.setFont(new Font("���ʷչ���", Font.BOLD, 12)); 
		
		// ------------------------------------------------------ī�װ� �޺��ڽ�, (���� �����ѵ�)�ؽ�Ʈ �� ����-----------------------------------------------
		Clist.remove(0);
		Category = new JComboBox(Clist);
		Category.setBackground(Color.WHITE); 							
		Category.setForeground(Color.BLACK);
		Category.setFont(new Font("���ʷչ���", Font.BOLD, 18)); 
		Category.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox cb = (JComboBox)e.getSource();
				String CommaMoney = ""; // ���⿡ �޸� ���� ���� ���ڿ� 
				Stack<Character> MoneyToComma = addComma(String.valueOf(CLimitList.toArray()[cb.getSelectedIndex()])); // ���ÿ� ����(addComma�Լ��� string �ݾ� �־������)
				
				while(!MoneyToComma.isEmpty()) { // pop
					CommaMoney += MoneyToComma.pop();
				}
				curexpenlimit.setText(CommaMoney); // �ؽ�Ʈ ����
				SelectCategory = (String)cb.getSelectedItem();
			}
		});
		Category.setSelectedIndex(0); // �ʱ� ����(0���� ����Ű��)
		//---------------------------------------------------------------------------------------------------------------------------------
		
		curexpenlimit.setBackground(Color.WHITE);
		curexpenlimit.setForeground(Color.BLACK);
		curexpenlimit.setFont(new Font("���ʷչ���", Font.BOLD, 18));
		curexpenlimit.addKeyListener(new KeyAdapter() {									// �Է��Ҷ� �޸� ǥ��
			public void keyTyped(KeyEvent e) { e.consume(); }
			public void keyReleased(KeyEvent e) { Category.setSelectedIndex(Category.getSelectedIndex()); }
			
		});
		
		//----------------------------------------------------------------������ ���� �ѵ� �ؽ�Ʈ �Է� (�޸� ����)------------------------------------
		changexpenlimit.setBackground(Color.WHITE);
		changexpenlimit.setForeground(Color.BLACK);
		changexpenlimit.setFont(new Font("���ʷչ���", Font.BOLD, 18)); 
		changexpenlimit.addKeyListener(new KeyAdapter() {									// �Է��Ҷ� �޸� ǥ��
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
				String CommaMoney = ""; // ���⿡ �޸� ���� ���� ���ڿ� 
				Stack<Character> MoneyToComma = addComma(t.getText()); // ���ÿ� ����(addComma�Լ��� string �ݾ� �־������)
				
				while(!MoneyToComma.isEmpty()) { // pop
					CommaMoney += MoneyToComma.pop();
				}
				t.setText(CommaMoney); // �ؽ�Ʈ ����
			}
		});
		changexpenlimit.addActionListener(new ActionListener() { // ���� �� ��� ������ ����
			public void actionPerformed(ActionEvent e) {
				JTextField t = (JTextField)e.getSource();
				ExpenseLimit expenselimit = new ExpenseLimit();
				SelectExpenLimit = (String)t.getText().replaceAll(",", "");
				expenselimit.setExpenseLimit(SelectCategory, Integer.parseInt(SelectExpenLimit));
				Category.setSelectedIndex(Category.getSelectedIndex()); // ���� ���� �ѵ� ������Ʈ
				t.setText("");
			}
		});
		changexpenlimit.addFocusListener(new FocusListener() { // ��Ŀ���� ��� �ʱ�ȭ ��Ű�°�
			public void focusGained(FocusEvent e) {
				JTextField t = (JTextField)e.getSource();
				if(t.getText().equals("�ݾ� �Է�(����)")) t.setText(""); // �ݾ� �Է� �� ��� �ʱ�ȭ
			}
			public void focusLost(FocusEvent e) {}
		});
		
		// ========================================================================
		
		cate.setFont(new Font("���ʷչ���", Font.BOLD, 18)); 
		current.setFont(new Font("���ʷչ���", Font.BOLD, 18)); 
		change.setFont(new Font("���ʷչ���", Font.BOLD, 18)); 
		
		GOtoBack.addActionListener(backbutton); // �ڷΰ���
		
	}
	
	private Stack<Character> addComma(String Money) {  					// �� �޸� �߰����ִ� �Լ�
		Money = Money.replaceAll(",","");
		Money=Money.replaceAll("��","");
		Stack<Character> ShowMoney = new Stack<Character>();
		for(int i=0; i<Money.length(); i++) {
			if(i%3==0 && i!= 0) {
				ShowMoney.add(',');
			}
			ShowMoney.push(Money.charAt(Money.length()-1  - i));
		}
		return ShowMoney;
	}
	
	private class GoToBackPanel implements ActionListener{ // �ڷΰ��� actionListener >> �ڷΰ��� ��ư�� ����
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