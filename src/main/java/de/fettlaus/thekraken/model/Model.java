/**
 * 
 */
package de.fettlaus.thekraken.model;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.List;

import de.fettlaus.thekraken.events.ModelListener;

/**
 * @author bachelor
 *
 */
public interface Model {
	//void subscribeNewConnection(ModelListener lst);
	void subscribeNewMessage(ModelListener lst);
	//void subscribeNewUART(ModelListener lst);
	void subscribeConnectionLost(ModelListener lst);
	
	
	List<Connection> getConnections();
	Connection getConnection(int index);
	void synchronizeClients();
	void newConnection(String ip, int port) throws UnknownHostException, IOException;

}
