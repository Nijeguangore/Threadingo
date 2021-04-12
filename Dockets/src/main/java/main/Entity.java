package main;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.google.gson.Gson;

public class Entity implements Runnable {

	Queue<String> messages;
	
	String name;
	
	long vitality = 100L;
	
	private static int UNO = 0;
	
	public boolean waitOnMessage = false,noKill = true;


	public List<String> stuff;
	
	public Entity() {
		messages = new LinkedList<String>();
		stuff = new ArrayList<String>();
		stuff.add("Rope");
		stuff.add("Pills");
		
		if(UNO++ == 0) {
			stuff.add("Shells");
		}
		
	}
	
	@Override
	public void run() {
		
		while(noKill) {
			if(waitOnMessage) {
				waitOnMessage = false;
				
				Thread meThread = Thread.currentThread();
				synchronized( meThread ) {
					 try {
						 System.out.println("Wait"+meThread.getName());
						meThread.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				while(!messages.isEmpty()) {
					Gson gson = new Gson();
					Message top = gson.fromJson(messages.poll(), Message.class);
					handleMessage(top);
				}
				
			}
//			System.out.println("Freedom!");
		}
		
		System.out.println("I am become Dead.");
	}

	private void handleMessage(Message top) {
		if(top.action.equals("Vit-U")) {
			this.vitality += top.healthDelta;
		}
		System.out.println(this.vitality);
	}

	public void messageBreak() {
		waitOnMessage = true;
		
		
	}

	public void killSwitch() {
		noKill = false;
	}

	public void addMessage(String jSONMessage) {
		
		messages.add(jSONMessage);
		
	}
}
