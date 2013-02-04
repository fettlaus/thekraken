/**
 * 
 */
package de.fettlaus.thekraken.model;

import java.util.List;

/**
 * @author bachelor
 * 
 */
public interface Model {
	void broadcastMessage(Message msg);

	Connection getConnection(int index);

	List<Connection> getConnections();

	void newConnection(String ip, int port);

	void synchronizeClients();

}
