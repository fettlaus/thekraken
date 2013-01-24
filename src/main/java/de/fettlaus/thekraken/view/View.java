package de.fettlaus.thekraken.view;

import de.fettlaus.thekraken.events.ViewListener;

/**
 * @author bachelor
 *
 */
public interface View{
	void subscribePingButtonClicked(final ViewListener ev);
	void subscribeSendMessage(final ViewListener ev);
	void subscribeConnectButtonClicked(final ViewListener ev);
	void subscribeDisconnectButtonClicked(final ViewListener ev);
	void subscribeTimesyncButtonClicked(final ViewListener ev);
	
	void addLogmessage(String timestamp, String target, String msg);
	void addUARTMessage(String timestamp, String target, String msg);
	void setNotification(String msg);
	void setClients(String[] clients);
	String getMessageString();
	String getNewClientIP();
	String getNewClientPort();
	int getCurrentClientIndex();

}
