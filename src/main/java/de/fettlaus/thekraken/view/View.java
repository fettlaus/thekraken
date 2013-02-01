package de.fettlaus.thekraken.view;

import java.awt.event.ActionListener;

/**
 * @author bachelor
 * 
 */
public interface View {
	void addLogmessage(String timestamp, String target, String msg);

	void addUARTMessage(String timestamp, String target, String msg);

	int getCurrentClientIndex();

	String getMessageString();

	String getNewClientIP();

	String getNewClientPort();

	void setClients(String[] clients);

	void setNotification(String msg);

	void subscribeConnectButtonClicked(final ActionListener ev);

	void subscribeDisconnectButtonClicked(final ActionListener ev);

	void subscribePingButtonClicked(final ActionListener ev);

	void subscribeSendMessage(final ActionListener ev);

	void subscribeTimesyncButtonClicked(final ActionListener ev);

}
