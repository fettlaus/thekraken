package de.fettlaus.thekraken.view;

import java.awt.event.ActionListener;

/**
 * @author bachelor
 *
 */
public interface View{
	void subscribePingButtonClicked(final ActionListener ev);
	void subscribeSendMessage(final ActionListener ev);
	void subscribeConnectButtonClicked(final ActionListener ev);
	void subscribeDisconnectButtonClicked(final ActionListener ev);
	void subscribeTimesyncButtonClicked(final ActionListener ev);
	
	void addLogmessage(String timestamp, String target, String msg);
	void addUARTMessage(String timestamp, String target, String msg);
	void setNotification(String msg);
	void setClients(String[] clients);
	String getMessageString();
	String getNewClientIP();
	String getNewClientPort();
	int getCurrentClientIndex();

}
