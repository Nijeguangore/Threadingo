package main;

import java.awt.Color;

import javax.swing.JComboBox;
import javax.swing.JPanel;

public class TargetPanel extends JPanel {
	
	JComboBox targets;

	public TargetPanel() {
		this.setBackground(Color.red);
		
		targets = new JComboBox();
		
		this.add(targets);
	}

	public void addTarget(String target) {
		targets.addItem(target);
		
	}

	public void setTargetListener(HermesPanel hermesPanel) {
		this.targets.addActionListener(hermesPanel);
	}

	public String getSelected() {
		return (String) targets.getSelectedItem();
	}
}
