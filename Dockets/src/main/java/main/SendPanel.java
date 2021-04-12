package main;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;

public class SendPanel extends JPanel {
	
	JButton senderButton;

	public SendPanel() {
		this.setBackground(Color.blue);
		
		senderButton = new JButton("Send Message!");
		
		this.add(senderButton);
		
	}

	public void setListener(Nexus nPrime) {
		
		senderButton.addActionListener(nPrime);
		
	}

	public JButton getButton() {
		return senderButton;
	}
	
}
