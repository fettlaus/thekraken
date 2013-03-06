package de.fettlaus.thekraken.view;

/**
 * @author bachelor
 * 
 */
public interface View {
	void addHostMessage(String timestamp, String msg);

	void addLogmessage(String timestamp, String target, String msg);

	void addUARTMessage(String timestamp, String target, String msg, String diff);

	void setClients(String[] clients);

	void setNotification(String msg);

}
