package ui;

import java.awt.*;
import javax.swing.*;

public class Frame{
	public JFrame frame = new JFrame("������ �˶���");
	
	public Frame() {
		frame.setTitle("������ �˶���");
		frame.setSize(430, 700);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}