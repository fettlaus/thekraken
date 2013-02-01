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
	void closeConnection(Connection con) throws IOException;

	Connection getConnection(int index);

	List<Connection> getConnections();

	void newConnection(String ip, int port) throws UnknownHostException, IOException;

	// void subscribeNewUART(ModelListener lst);
	void subscribeConnectionLost(ModelListener lst);

	// void subscribeNewConnection(ModelListener lst);
	void subscribeNewMessage(ModelListener lst);

	void synchronizeClients();

}
