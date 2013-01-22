/**
 * 
 */
package de.fettlaus.thekraken.model;

import java.net.InetAddress;
import java.util.List;

import de.fettlaus.thekraken.events.KrakenListener;

/**
 * @author bachelor
 *
 */
public interface Model {
	void subscribeNewConnection(KrakenListener lst);
	void subscribeNewMessage(KrakenListener lst);
	void subscribeNewUART(KrakenListener lst);
	
	
	List<Connection> getConnections();
	Connection getConnection(int index);
	void synchronizeClients();
	void newConnection(InetAddress ip, int port);

}
