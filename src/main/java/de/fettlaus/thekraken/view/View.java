package de.fettlaus.thekraken.view;

/**
 * @author bachelor
 * 
 */
public interface View {
	void addLogmessage(String timestamp, String target, String msg);

	void addUARTMessage(String timestamp, String target, String msg);

	void setClients(String[] clients);

	void setNotification(String msg);


}
