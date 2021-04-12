package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class HermesPanel extends JPanel implements ActionListener {
	
	TargetPanel tp = new TargetPanel();
	BundlePanel bp = new BundlePanel();
	SendPanel sp = new SendPanel();
	
	public HermesPanel() {
		tp.setTargetListener(this);
		
		this.setLayout(new BoxLayout(this,BoxLayout.LINE_AXIS));
		
		this.add(tp);
		this.add(bp);
		this.add(sp);
		
	}

	public void setTargets(ArrayList<Thread> threads) {
		
		for (Thread thread : threads) {
			tp.addTarget(thread.getName());
		}
		
	}

	public void setSendListener(Nexus nPrime) {
		sp.setListener(nPrime);
		
	}

	public SendPanel getSender() {
	
		return sp;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String selection = (String) tp.targets.getSelectedItem();
		for(int i = 0 ; i < Nexus.threads.size() ; i++) {
			if(Nexus.threads.get(i).getName() == selection) {
				bp.emptyStuff();
				for (String item : Nexus.things.get(i).stuff) {
					bp.populateStuff(item);
				}
			}
		}
		
		//
		
		
		//tp.targets.addItem(e);
		
	}

	public void setItemListener(Nexus nPrime) {
		bp.setItemAddButton(nPrime);
		
	}

	public String getBundleItem() {
		return bp.getNuevoItem();
		
	}

	public String getSelected() {
		return tp.getSelected();
		
	}

	public int getHPChange() {
		return bp.getHPNumber();
	}
}
