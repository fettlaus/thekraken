package de.fettlaus.thekraken.model;

import java.util.concurrent.BlockingQueue;

import de.fettlaus.thekraken.events.ModelListener;
import de.fettlaus.thekraken.view.Messages;

public class MessageDispatcher implements Runnable{

	private BlockingQueue<Message> messages;
	private ModelListener lst;

	public MessageDispatcher(BlockingQueue<Message> messages, ModelListener lst) {
		this.messages = messages;	
	}

	@Override
	public void run() {
		Message msg;
		while(true){
			try {
				msg = messages.take();
				System.out.println(msg.toString());
				//lst.fireEvent(null);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
