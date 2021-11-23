package ui;

import java.awt.*;
import javax.swing.*;

public class Frame{
	public JFrame frame = new JFrame("°³¹ÌÀÇ ¾Ë¶ã»ì¶ã");
	
	public Frame() {
		frame.setTitle("°³¹ÌÀÇ ¾Ë¶ã»ì¶ã");
		frame.setSize(430, 700);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}