package main;

import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

public class BundlePanel extends JPanel {
	
	JButton addItem = null;
	JTextField itemInput;
	JSpinner healthNetChange;
	JComboBox<String> itemCombo;

	public BundlePanel() {
		this.setBackground(Color.green);
		this.setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
		JLabel bigLabel = new JLabel("Message!");
		this.add(bigLabel);
		
		JPanel healthDropDown = new JPanel();
		JLabel healthLabel = new JLabel("Health:");
		
		SpinnerNumberModel model1 = new SpinnerNumberModel(0, -150, 150, 1); 
		healthNetChange = new JSpinner(model1);
		
		
		JComponent editor = healthNetChange.getEditor();
		JFormattedTextField tf = ((JSpinner.DefaultEditor) editor).getTextField();
		tf.setColumns(3);
		tf.setEditable(false);

		
		
		healthDropDown.add(healthLabel);
		healthDropDown.add(healthNetChange);
		healthDropDown.setBackground(Color.cyan);
		
		JPanel itemDropDown = new JPanel();
		String[] items = {"Rope","MPotion","Fishing Rod"};
		itemCombo = new JComboBox<String>(items);
		itemInput = new JTextField("NewItem?",32);
		addItem = new JButton("Add to Inventory!");
		
		itemDropDown.add(itemCombo);
		itemDropDown.add(itemInput);
		itemDropDown.add(addItem);
		itemDropDown.setBackground(Color.yellow);
		
		this.add(healthDropDown);
		this.add(itemDropDown);
	}

	public void populateStuff(String item) {
		itemCombo.addItem(item);
		
	}

	public void emptyStuff() {
		itemCombo.removeAllItems();
		
	}

	public void setItemAddButton(Nexus n1) {
		addItem.addActionListener(n1);
	}

	public String getNuevoItem() {
		return itemInput.getText();
	}

	public int getHPNumber() {
		return (int) healthNetChange.getValue();
	}
	
}
