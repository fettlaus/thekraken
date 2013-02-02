package de.fettlaus.thekraken;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.List;

import javax.swing.SwingWorker;

import com.google.common.eventbus.DeadEvent;
import com.google.common.eventbus.Subscribe;

import de.fettlaus.thekraken.events.EventBus;
import de.fettlaus.thekraken.model.Connection;
import de.fettlaus.thekraken.model.Message;
import de.fettlaus.thekraken.model.MessageType;
import de.fettlaus.thekraken.model.Model;
import de.fettlaus.thekraken.view.View;

public class Presenter {
	Model model;
	View view;

	public Presenter(Model model, View view) {
		super();
		this.model = model;
		this.view = view;

		connectEvents();
	}

	@Subscribe
	public void deadHandler(DeadEvent dead) {
		System.out.println(dead.getEvent().toString());
	}

	@Subscribe
	public void handleConnectionList(List<Connection> conlist) {
		final int connections_size = conlist.size();
		final String[] connections_txt = new String[connections_size];
		for (int i = 0; i < connections_size; ++i) {
			final Connection this_con = conlist.get(i);
			connections_txt[i] = this_con.getAddress() + ":" + this_con.getPort();
		}
		view.setClients(connections_txt);
	}

	@Subscribe
	public void handleNewMessage(Message msg) {
		try {
			final String timestamp = String.valueOf(msg.getTimestamp());
			final String connection = msg.getConnection().getAddress();
			final MessageType type = msg.getType();
			if (type == MessageType.UART) {
				view.addUARTMessage(timestamp, connection, msg.getMessage());
			} else if (type == MessageType.MESS) {
				view.addLogmessage(timestamp, connection, msg.getMessage());
			}
		} catch (final ClassCastException e) {
			e.printStackTrace();
		}
	}

	private void connectEvents() {
		EventBus.instance().register(this);
		view.subscribeConnectButtonClicked(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new SwingWorker<Void, Void>() {
					@Override
					public Void doInBackground() {
						int port;
						String host;
						try {
							port = Integer.parseInt(view.getNewClientPort());
							host = view.getNewClientIP();
							model.newConnection(host, port);
							view.setNotification("Connection established!");
						} catch (final NumberFormatException e1) {
							view.setNotification("Wrong Port specified");
						} catch (final UnknownHostException e2) {
							view.setNotification("Can't find host!");
						} catch (final IOException e3) {
							view.setNotification("Can't establish connection");
						}
						return null;
					}
				}.execute();

			}
		});

	}

}
