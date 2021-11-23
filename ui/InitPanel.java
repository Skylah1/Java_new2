package ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.util.Stack;

import javax.swing.*;
import database.DB;

public class InitPanel extends BasicPanel {
	
	
	private ImageIcon init = new ImageIcon("images/init.png"); // �̹��� �ε�
	private JLabel initimg=new JLabel("",init,SwingConstants.CENTER); // ���̺� ����
			
	//private JTextField expenlimit= new JTextField(13);
	private JTextField curassetwon= new JTextField(13);
	private JTextField curassetdol= new JTextField(13);
	
	//private JLabel exli = new JLabel("  ���� �ѵ�         "); // ��
	private JLabel curassw = new JLabel("\n  ���� �ڻ� (��)  ");
	private JLabel curassd = new JLabel("\n  ���� �ڻ� ($)    ");

	private SaveToDB SaveCurAsset; //Ȯ�� ��ư
	private JButton OK = new JButton("Ȯ��");
	private DB database;
	
	public InitPanel(JFrame frame) { // ������
		buttonclick = new ButtonClick(frame);
		SaveCurAsset = new SaveToDB(frame);
		InitObject();
		CenterPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 30)); // ��������, �����Ÿ� 30
		CenterPanel.add(initimg); // ������� ����
		//CenterPanel.add(exli);
		//CenterPanel.add(expenlimit);
		CenterPanel.add(curassw);
		CenterPanel.add(curassetwon);
		CenterPanel.add(curassd);
		CenterPanel.add(curassetdol);
		SouthPanel.add(OK);
	}
	
	
	
	private void InitObject() { // �ʱ�ȭ �۾� �� �ʱ⼼��
		
		//expenlimit.setBackground(Color.WHITE);
		//expenlimit.setForeground(Color.BLACK);
		//expenlimit.setFont(new Font("���ʷչ���", Font.BOLD, 18)); 
		
		
		curassetwon.setBackground(Color.WHITE);
		curassetwon.setForeground(Color.BLACK);
		curassetwon.setFont(new Font("���ʷչ���", Font.BOLD, 18));
		
		
		curassetdol.setBackground(Color.WHITE);
		curassetdol.setForeground(Color.BLACK);
		curassetdol.setFont(new Font("���ʷչ���", Font.BOLD, 18)); 
		
		OK.setBackground(Color.WHITE);
		OK.setForeground(Color.BLACK);
		OK.setFont(new Font("���ʷչ���", Font.BOLD, 18)); 
		
		// ========================================================================
		
		//exli.setFont(new Font("���ʷչ���", Font.BOLD, 18)); 
		curassw.setFont(new Font("���ʷչ���", Font.BOLD, 18)); 
		curassd.setFont(new Font("���ʷչ���", Font.BOLD, 18)); 
		
		OK.addActionListener(SaveCurAsset);

		curassetwon.addKeyListener(new KeyAdapter() { //�����ڻ�(��) �Է� , comma �ڵ� �Է�
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
		
		curassetdol.addKeyListener(new KeyAdapter() { //�����ڻ�($) �Է�, �Ҽ��� �Է�
			int count = 0;
			public void keyTyped(KeyEvent e) {
				JTextField t = (JTextField)e.getSource();
				if((e.getKeyChar() == '0') ||(e.getKeyChar() == '1') ||(e.getKeyChar() == '2') ||
						(e.getKeyChar() == '3') ||(e.getKeyChar() == '4') ||(e.getKeyChar() == '5') ||
						(e.getKeyChar() == '6') ||(e.getKeyChar() == '7') ||(e.getKeyChar() == '8') ||
						(e.getKeyChar() == '9') ||(e.getKeyChar() == ',') ) { //comma �Է��� ���� (�Ҽ��� �����ϱ� addComma ���峪��)
				}
				
				else if((e.getKeyChar() == '.') && (count == 0) && t.getText().length() >= 1) { //�Ҽ��� ǥ��
					count++;
				}
				
				else e.consume();
				if(t.getText().length()>=10) e.consume();
			}
		});
	}
	
	private Stack<Character> addComma(String Money) {  					// �� �޸� �߰����ִ� �Լ�
		Money = Money.replaceAll(",","");
		Money = Money.replaceAll("��","");
		Stack<Character> ShowMoney = new Stack<Character>();
		for(int i=0; i<Money.length(); i++) {
			if(i%3==0 && i!= 0) {
				ShowMoney.add(',');
			}
			ShowMoney.push(Money.charAt(Money.length()-1  - i));
		}
		return ShowMoney;
	}
	//Ȯ�� ��ư ������ ��  ���� �� ȭ�� �̵�
	private class SaveToDB implements ActionListener {
		
		private String[] curasset = new String[2]; //0: won, 1: dollar
		private JFrame frame;
		
		public SaveToDB(JFrame frame) {
			this.frame = frame;
		}
		
		public void actionPerformed(ActionEvent e) {
			
			curasset[0] = curassetwon.getText().replaceAll(",", ""); //textfield���� �����ڻ� get
			curasset[1] = curassetdol.getText().replaceAll(",", "");
			
			try {
				
				FileWriter fw = new FileWriter("DB.txt", true); //DB.txt ����
			
				for (int i = 0; i < 2; i++) {
					//ī�װ� ����Ʈ�� ����� �⺻ ī�װ����� ���ʴ�� CategoryDB.txt�� �����Ѵ�.
					
					fw.write(curasset[i]); //DB.txt�� �����ڻ� ����
					fw.write("\r\n"); //�ٹٲ�
					fw.flush();
				}
				fw.close();
			}
			
			catch(IOException e2) {
			//����ó��
			}
			
			frame.setContentPane(new MainPanel(frame)); //ȭ�� ��ȯ
			frame.setVisible(true);

		}
	}
	
	
	
}