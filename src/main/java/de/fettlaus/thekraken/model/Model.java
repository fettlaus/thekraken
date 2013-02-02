/**
 * 
 */
package de.fettlaus.thekraken.model;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.List;

/**
 * @author bachelor
 * 
 */
public interface Model {
	void closeConnection(Connection con) throws IOException;

	Connection getConnection(int index);

	List<Connection> getConnections();

	void newConnection(String ip, int port) throws UnknownHostException, IOException;

	void synchronizeClients();

}
