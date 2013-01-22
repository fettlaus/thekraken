package de.fettlaus.thekraken.view;

import de.fettlaus.thekraken.events.KrakenListener;

/**
 * @author bachelor
 *
 */
public interface View{
	void subscribePingButtonClicked(final KrakenListener ev);
	void subscribeSendMessage(final KrakenListener ev);
	void subscribeConnectButtonClicked(final KrakenListener ev);
	void subscribeDisconnectButtonClicked(final KrakenListener ev);
	void subscribeTimesyncButtonClicked(final KrakenListener ev);
	
	void addLogmessage(String timestamp, String target, String msg);
	void addUARTMessage(String timestamp, String target, String msg);
	void setNotification(String msg);
	void setClients(String[] clients);
	String getMessageString();
	String getNewClientIP();
	String getNewClientPort();
	int getCurrentClientIndex();

}
