/*
 * 320�ٿ� DB���� �޼ҵ� �ʿ�
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
	
	// ���� ����
		private JLabel Line = new JLabel("aaaaaaaa"); // ��
		private JLabel Line1 = new JLabel("aaaaaaaaa"); // ��
		private JLabel Line2 = new JLabel("aaaaaaaaaaaaaaaaaaaaa"); // ��
		private JLabel Line3 = new JLabel("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"); // ��
		private JLabel Line4 = new JLabel("aaaaaaaaaaaaaaaaaaaaaaaaaaa"); // ��

		
		private ImageIcon input = new ImageIcon("images/input.png"); // �̹��� �ε�
		private JLabel inputimg=new JLabel("",input,SwingConstants.CENTER); // ���̺� ����
		
		private String[] YearArray = new String[16]; // ���ڿ� �迭 ��, ��, �� 
		private String[] MonthArray = new String[12];
		private Vector<String> DateArray = new Vector<String>();
		
		Vector<String> Clist = CategoryDB.getCategory();	//ī�װ� ��� ���� 
	
		private String[] CategoryArray;
		
		private JComboBox<String> Year; // ��, ��, �� ������ �޺��ڽ�
		private JComboBox<String> Month;
		private JComboBox<String> Date;
		private JComboBox Category = new JComboBox(Clist);
		
		private ButtonGroup KRW_USD = new ButtonGroup(); // ���� ��ư �׷�
		
		private JRadioButton KRW = new JRadioButton("  ��ȭ                     ", true); // ���� ��ư
		private JRadioButton USD = new JRadioButton("  ��ȭ                     ");
		
		private JLabel Calendar = new JLabel("��¥ ����         "); // ��
		private JLabel YearLabel = new JLabel(" ��  ");
		private JLabel MonthLabel = new JLabel(" ��  ");
		private JLabel DateLabel = new JLabel(" ��");
		private JLabel CategoryLabel = new JLabel("ī�װ� ���� ");
		private JLabel PriceLabel = new JLabel(" �ݾ� �Է�       ");
		
		private BackButton GOtoBack = new BackButton();
		
		private JTextField Input = new JTextField("�ݾ� �Է�(����)", 10); ////////////// �̰ɷ� �Է�
		private GoToBackPanel backbutton;
		
		private Vector<String> ForDBString = new Vector<String>(); // DB�� ������ string
		private String ForDBYear; // DB�����
		private String ForDBMonth;
		private String ForDBDate;
		private String ForDBCategory;
		private String ForDBisKRW = "0";
		private String ForDBMoney;
		
		public InputPanel(JFrame frame) { // ������
			super();
			backbutton = new GoToBackPanel(frame); // BasicPanel�� �ִ� buttonclick
			InitObject();
			CenterPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 30)); // ��������, �����Ÿ� 30
			CenterPanel.add(Line);
			CenterPanel.add(inputimg); // ������� ����
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
			SouthPanel.setLayout(new FlowLayout(FlowLayout.LEFT)); // �ڷΰ���
			SouthPanel.add(GOtoBack);
			
			SettingComboBox();
			
			InitSetting();
		}
		 
		private void InitObject() { // �ʱ�ȭ �۾� �� �ʱ⼼��
			
			Line.setBackground(Color.WHITE);
			Line.setForeground(Color.WHITE);
			Line.setFont(new Font("���ʷչ���", Font.BOLD, 16)); 
			
			Line1.setBackground(Color.WHITE);
			Line1.setForeground(Color.WHITE);
			Line1.setFont(new Font("���ʷչ���", Font.BOLD, 16)); 
			
			Line2.setBackground(Color.WHITE);
			Line2.setForeground(Color.WHITE);
			Line2.setFont(new Font("���ʷչ���", Font.BOLD, 16)); 
			
			Line3.setBackground(Color.WHITE);
			Line3.setForeground(Color.WHITE);
			Line3.setFont(new Font("���ʷչ���", Font.BOLD, 16)); 
			
			Line4.setBackground(Color.WHITE);
			Line4.setForeground(Color.WHITE);
			Line4.setFont(new Font("���ʷչ���", Font.BOLD, 16)); 
			
			for(int i=0; i<16; i++) YearArray[i] = String.valueOf(i+2015); // 2015~2030�� ����
			Year = new JComboBox<String>(YearArray);
			Year.setBackground(Color.WHITE);
			Year.setForeground(Color.BLACK);
			Year.setFont(new Font("���ʷչ���", Font.BOLD, 16));
			Year.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e){
					JComboBox<String> cb = (JComboBox<String>)e.getSource();
					ForDBYear = (String) cb.getSelectedItem();
				}
			});
			
			for(int i=0; i<28; i++) DateArray.add(String.valueOf(i+1));
			for(int i=0; i<12; i++) MonthArray[i] = String.valueOf(i+1); // 1~12�� ����
			Month = new JComboBox<String>(MonthArray);
			Month.setBackground(Color.WHITE);
			Month.setForeground(Color.BLACK);
			Month.setFont(new Font("���ʷչ���", Font.BOLD, 16)); 
			Month.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JComboBox<String> cb = (JComboBox<String>)e.getSource();
					ForDBMonth = (String) cb.getSelectedItem();
					if(ForDBMonth.equals("1") || ForDBMonth.equals("3")||ForDBMonth.equals("5")||ForDBMonth.equals("7")|| // �޿� ���� �� �޺��ڽ� ����
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
			
			Date = new JComboBox(DateArray); // ���� �ؾ���
			Date.setBackground(Color.WHITE);
			Date.setForeground(Color.BLACK);
			Date.setFont(new Font("���ʷչ���", Font.BOLD, 16)); 
			Date.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JComboBox cb = (JComboBox)e.getSource();
					ForDBDate = (String) cb.getSelectedItem();
				}
			});
			
			Category.setBackground(Color.WHITE);
			Category.setForeground(Color.BLACK);
			Category.setFont(new Font("���ʷչ���", Font.BOLD, 16)); 
			Category.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JComboBox cb = (JComboBox)e.getSource();
					ForDBCategory = (String) cb.getSelectedItem();
				}
			});
			
			// ---------------------------------------------------------------------------------�޺��ڽ�
			KRW.setBackground(Color.WHITE);
			KRW.setForeground(Color.BLACK);
			KRW.setFont(new Font("���ʷչ���", Font.BOLD, 17)); 
			USD.setBackground(Color.WHITE);
			USD.setForeground(Color.BLACK);
			USD.setFont(new Font("���ʷչ���", Font.BOLD, 17)); 
			KRW_USD.add(KRW); KRW_USD.add(USD);
			KRW.addItemListener(new ForRadioButton());
			USD.addItemListener(new ForRadioButton());
			
			//----------------------------------------------------------------------------------���� ��ư
			
			Calendar.setFont(new Font("���ʷչ���", Font.BOLD, 17)); 
			YearLabel.setFont(new Font("���ʷչ���", Font.BOLD, 17)); 
			MonthLabel.setFont(new Font("���ʷչ���", Font.BOLD, 17)); 
			DateLabel.setFont(new Font("���ʷչ���", Font.BOLD, 17)); 
			CategoryLabel.setFont(new Font("���ʷչ���", Font.BOLD, 17)); 
			PriceLabel.setFont(new Font("���ʷչ���", Font.BOLD, 17)); 
			
			// ---------------------------------------------------------------------------------��
			
			GOtoBack.addActionListener(backbutton); // �ڷΰ��� ����

			// ----------------------------------------------------------------------------------��ư
			
			Input.setFont(new Font("���ʷչ���", Font.BOLD, 16));
			// Input.setBorder(new LineBorder(Color.BLACK,4)); // �׵θ�
			Input.setBackground(Color.WHITE);
			Input.setForeground(Color.BLACK);
			Input.setCaretColor(Color.BLACK); // ������ ��
			Input.addActionListener(new InputData());
			Input.addKeyListener(new KeyAdapter() {									// �Է��Ҷ� �޸� ǥ��
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
			
			Input.addFocusListener(new FocusListener() { // ��Ŀ���� ��� �ʱ�ȭ ��Ű�°�
				public void focusGained(FocusEvent e) {
					JTextField t = (JTextField)e.getSource();
					if(t.getText().equals("�ݾ� �Է�(����)")) t.setText(""); // �ݾ� �Է� �� ��� �ʱ�ȭ
				}
				public void focusLost(FocusEvent e) {}
			});
				
			// ----------------------------------------------------------------------------------�ؽ�Ʈ �ʵ�
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
		
		private void SettingComboBox() { // �޺��ڽ� �ʱ� ��¥ ����(���� ��¥��)
			LocalDate now = LocalDate.now();
			Year.setSelectedIndex(now.getYear()-2015);
			Month.setSelectedIndex(now.getMonthValue()-1);
			Date.setSelectedIndex(now.getDayOfMonth()-1);
			Category.setSelectedIndex(0);
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
		
		private class InputData implements ActionListener{ // �ؽ�Ʈ�ʵ� actionListenr  // ���⿡�� DB����!!
			public void actionPerformed(ActionEvent e) {
				JTextField t = (JTextField)e.getSource();
				ForDBMoney = (String)t.getText();
				ForDBMoney = ForDBMoney.replaceAll(",", "");
				ForDBString.add(ForDBYear + ForDBMonth + ForDBDate); // ���⿡ 20211126 ���� �����
				ForDBString.add(ForDBisKRW); // 0, 1 ����(��ȭ ��ȭ)
				ForDBString.add(ForDBCategory); // ī�װ� ����
				ForDBString.add(ForDBMoney); // �ݾ� ����
				// ���� ForDBString�� ������ ����Ǿ��ֽ��ϴ�! Vector<String>���� �Ǿ��־�� ***************************************����� ����! *********************
				// << ���⿡�� ForDBString�̿��ؼ� DB�� �����ϸ� �˴ϴ�.
				for(int i=0; i<4; i++) { // << �̰Ŵ� �ܼ� Ȯ�ο� ����� 
					System.out.print(ForDBString.toArray()[i]);
				}
				System.out.println();
				ForDBString.clear();

				t.setText("");
			}
		}
		
		private class ForRadioButton implements ItemListener{ // ���� ��ư ItemListener
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
					if(e.getItem() == KRW) ForDBisKRW = "0"; 
					else ForDBisKRW = "1"; 
				}
			}
		}
	}