package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.Thread.State;
import java.util.ArrayList;

import javax.swing.WindowConstants;

import com.google.gson.Gson;

public class Nexus implements ActionListener{
	
	public static ArrayList<Thread> threads = new ArrayList<Thread>();
	public static ArrayList<Entity> things = new ArrayList<Entity>();
	HermesPanel hp = new HermesPanel();
	static Nexus nPrime;
	
	static final short Amigo = 0, Enemigo = 1; 

	public static void main(String[] args) {
		nPrime = new Nexus();
		
		Entity ent1 = new Entity();
		Thread char1 = new Thread(ent1);
		char1.setName("Enemigo");
		
		Entity ent2 = new Entity();
		Thread char2 = new Thread(ent2);
		char2.setName("Amigo");
		
		Nexus.threads.add(char2);
		Nexus.things.add(ent2);
		
		Nexus.threads.add(char1);
		Nexus.things.add(ent1);

		
		MessagePane mp = new MessagePane();
		
		nPrime.hp.setTargets(nPrime.threads);
		nPrime.hp.setSendListener(nPrime);
		nPrime.hp.setItemListener(nPrime);
		
		mp.add(nPrime.hp);
		
		char1.start();
		ent1.messageBreak();
		
		char2.start();
		ent2.messageBreak();
		
		mp.setVisible(true);
		mp.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == hp.getSender().getButton()) {
			String mainThreadName = nPrime.hp.getSelected();
			
			int hpDelta = nPrime.hp.getHPChange();
			
			Message mainsaje = new Message();
			mainsaje.healthDelta = hpDelta;
			mainsaje.action = "Vit-U";
			mainsaje.itemDelta = "";
			
			Gson JsonTool = new Gson();
			String JSONMessage = JsonTool.toJson(mainsaje);
			
			if(mainThreadName.equals("Amigo")) {
				synchronized(  ((Thread)(threads.get(Amigo)))  ) {
					Thread amigoThread = threads.get(Amigo);
					Entity amigoEntity = things.get(Amigo);
					amigoEntity.addMessage(JSONMessage);
					
					amigoThread.notify();
					
					amigoEntity.messageBreak();
				}
			}
			else if(mainThreadName.equals("Enemigo")) {
				synchronized(  ((Thread)(threads.get(Enemigo)))  ) {
					Thread enemigoThread = threads.get(Enemigo);
					Entity enemigoEntity = things.get(Enemigo);
					enemigoEntity.addMessage(JSONMessage);
					
					enemigoThread.notify();
					
					enemigoEntity.messageBreak();
				}
			}
			//((Entity)(things.get(1))).killSwitch();
		}
		else {
			System.out.println(nPrime.hp.getBundleItem());
		}
		
	}


}
