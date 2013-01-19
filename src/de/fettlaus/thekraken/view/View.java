package de.fettlaus.thekraken.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import de.fettlaus.thekraken.ViewInterface;
import de.fettlaus.thekraken.events.KrakenListener;

import java.awt.GridBagLayout;
import javax.swing.JTextField;
import javax.swing.JMenuBar;
import javax.swing.JButton;
import javax.swing.JList;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Component;

import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JMenu;
import javax.swing.JSeparator;
import java.awt.Dimension;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.border.BevelBorder;
import javax.swing.SwingConstants;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class View implements ViewInterface, Runnable{

	private JFrame form_main;
	private JTextField textField_status;
	private JTextField textField_connect;
	private JButton button_connect;
	private JList<String> list_targets;
	private JButton button_synchronize;
	private JTextArea textArea_messages;
	private JTextArea textArea_uart;

	/**
	 * Create the application.
	 */
	public View() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		form_main = new JFrame();
		form_main.setMinimumSize(new Dimension(500, 300));
		form_main.setTitle(Messages.getString("View.form_main.title")); //$NON-NLS-1$ //$NON-NLS-1$
		form_main.setBounds(100, 100, 864, 531);
		form_main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel_main = new JPanel();
		form_main.setContentPane(panel_main);
		GridBagLayout gbl_panel_main = new GridBagLayout();
		gbl_panel_main.columnWeights = new double[]{1.0, 0.0, 0.0};
		gbl_panel_main.rowWeights = new double[]{0.0, 1.0, 0.0, 0.0};
		panel_main.setLayout(gbl_panel_main);
		
		JMenuBar menuBar_main = new JMenuBar();
		menuBar_main.setMinimumSize(new Dimension(0, 21));
		GridBagConstraints gbc_menuBar_main = new GridBagConstraints();
		gbc_menuBar_main.anchor = GridBagConstraints.NORTH;
		gbc_menuBar_main.fill = GridBagConstraints.BOTH;
		gbc_menuBar_main.insets = new Insets(0, 0, 5, 5);
		gbc_menuBar_main.gridwidth = 3;
		gbc_menuBar_main.gridx = 0;
		gbc_menuBar_main.gridy = 0;
		panel_main.add(menuBar_main, gbc_menuBar_main);
		
		JMenu menu_file = new JMenu(Messages.getString("View.menu_file.s")); //$NON-NLS-1$ //$NON-NLS-1$
		menuBar_main.add(menu_file);
		
		JSeparator main_separator = new JSeparator();
		main_separator.setPreferredSize(new Dimension(2, 0));
		main_separator.setOrientation(SwingConstants.VERTICAL);
		GridBagConstraints gbc_main_separator = new GridBagConstraints();
		gbc_main_separator.gridheight = 2;
		gbc_main_separator.fill = GridBagConstraints.VERTICAL;
		gbc_main_separator.insets = new Insets(0, 0, 5, 5);
		gbc_main_separator.gridx = 1;
		gbc_main_separator.gridy = 1;
		panel_main.add(main_separator, gbc_main_separator);
		
		JPanel panel_targets = new JPanel();
		GridBagConstraints gbc_panel_targets = new GridBagConstraints();
		gbc_panel_targets.fill = GridBagConstraints.VERTICAL;
		gbc_panel_targets.gridheight = 2;
		gbc_panel_targets.insets = new Insets(0, 0, 5, 5);
		gbc_panel_targets.gridx = 2;
		gbc_panel_targets.gridy = 1;
		panel_main.add(panel_targets, gbc_panel_targets);
		GridBagLayout gbl_panel_targets = new GridBagLayout();
		gbl_panel_targets.columnWeights = new double[]{0.0, 0.0};
		gbl_panel_targets.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0};
		panel_targets.setLayout(gbl_panel_targets);
		
		JLabel label_addtarget = new JLabel(Messages.getString("View.label_addtarget.text")); //$NON-NLS-1$ //$NON-NLS-1$
		GridBagConstraints gbc_label_addtarget = new GridBagConstraints();
		gbc_label_addtarget.gridwidth = 2;
		gbc_label_addtarget.insets = new Insets(0, 0, 5, 0);
		gbc_label_addtarget.gridx = 0;
		gbc_label_addtarget.gridy = 0;
		panel_targets.add(label_addtarget, gbc_label_addtarget);
		
		JLabel label_targets = new JLabel(Messages.getString("View.label_targets.text")); //$NON-NLS-1$ //$NON-NLS-1$
		GridBagConstraints gbc_label_targets = new GridBagConstraints();
		gbc_label_targets.gridwidth = 2;
		gbc_label_targets.insets = new Insets(0, 0, 5, 5);
		gbc_label_targets.gridx = 0;
		gbc_label_targets.gridy = 3;
		panel_targets.add(label_targets, gbc_label_targets);
		
		JScrollPane scrollPane_targets = new JScrollPane();
		scrollPane_targets.setSize(new Dimension(20, 0));
		scrollPane_targets.setMaximumSize(new Dimension(30, 32767));
		scrollPane_targets.setPreferredSize(new Dimension(30, 3));
		GridBagConstraints gbc_scrollPane_targets = new GridBagConstraints();
		gbc_scrollPane_targets.weighty = 1.0;
		gbc_scrollPane_targets.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_targets.gridwidth = 2;
		gbc_scrollPane_targets.gridx = 0;
		gbc_scrollPane_targets.gridy = 4;
		panel_targets.add(scrollPane_targets, gbc_scrollPane_targets);
		
		list_targets = new JList<String>();
		list_targets.setAlignmentX(Component.LEFT_ALIGNMENT);
		scrollPane_targets.setViewportView(list_targets);
		list_targets.setAlignmentY(Component.TOP_ALIGNMENT);
		
		JSeparator separator_target = new JSeparator();
		separator_target.setPreferredSize(new Dimension(0, 3));
		GridBagConstraints gbc_separator_target = new GridBagConstraints();
		gbc_separator_target.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator_target.gridwidth = 2;
		gbc_separator_target.insets = new Insets(0, 0, 5, 0);
		gbc_separator_target.gridx = 0;
		gbc_separator_target.gridy = 2;
		panel_targets.add(separator_target, gbc_separator_target);
		
		textField_connect = new JTextField();
		textField_connect.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				textField_connect.setText("");
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(textField_connect.getText().isEmpty())
					textField_connect.setText(Messages.getString("View.textField_connect.text"));
			}
		});
		textField_connect.setText(Messages.getString("View.textField_connect.text")); //$NON-NLS-1$
		textField_connect.setMinimumSize(new Dimension(120, 19));
		textField_connect.setPreferredSize(new Dimension(100, 19));
		textField_connect.setColumns(11);
		GridBagConstraints gbc_textField_connect = new GridBagConstraints();
		gbc_textField_connect.weightx = 0.5;
		gbc_textField_connect.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_connect.insets = new Insets(0, 0, 5, 5);
		gbc_textField_connect.gridx = 0;
		gbc_textField_connect.gridy = 1;
		panel_targets.add(textField_connect, gbc_textField_connect);
		
		button_connect = new JButton(Messages.getString("View.btnNewButton.text"));

		button_connect.setText(Messages.getString("View.button_connect.text")); //$NON-NLS-1$

		GridBagConstraints gbc_button_connect = new GridBagConstraints();
		gbc_button_connect.weightx = 0.5;
		gbc_button_connect.fill = GridBagConstraints.HORIZONTAL;
		gbc_button_connect.insets = new Insets(0, 0, 5, 0);
		gbc_button_connect.gridx = 1;
		gbc_button_connect.gridy = 1;
		panel_targets.add(button_connect, gbc_button_connect);
		
		JPanel panel_status = new JPanel();
		GridBagConstraints gbc_panel_status = new GridBagConstraints();
		gbc_panel_status.insets = new Insets(5, 5, 5, 5);
		gbc_panel_status.anchor = GridBagConstraints.NORTH;
		gbc_panel_status.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_status.gridwidth = 3;
		gbc_panel_status.gridx = 0;
		gbc_panel_status.gridy = 3;
		panel_main.add(panel_status, gbc_panel_status);
		GridBagLayout gbl_panel_status = new GridBagLayout();
		gbl_panel_status.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_status.rowWeights = new double[]{0.0};
		panel_status.setLayout(gbl_panel_status);
		
		textField_status = new JTextField();
		textField_status.setEditable(false);
		GridBagConstraints gbc_textField_status = new GridBagConstraints();
		gbc_textField_status.gridwidth = 3;
		gbc_textField_status.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_status.gridx = 0;
		gbc_textField_status.gridy = 0;
		panel_status.add(textField_status, gbc_textField_status);
		textField_status.setColumns(10);
		
		JTabbedPane tabbedPane_messages = new JTabbedPane(JTabbedPane.TOP);
		GridBagConstraints gbc_tabbedPane_messages = new GridBagConstraints();
		gbc_tabbedPane_messages.weighty = 0.9;
		gbc_tabbedPane_messages.weightx = 1.0;
		gbc_tabbedPane_messages.insets = new Insets(0, 5, 5, 5);
		gbc_tabbedPane_messages.fill = GridBagConstraints.BOTH;
		gbc_tabbedPane_messages.gridx = 0;
		gbc_tabbedPane_messages.gridy = 1;
		panel_main.add(tabbedPane_messages, gbc_tabbedPane_messages);
		
		JPanel panel_messages = new JPanel();
		tabbedPane_messages.addTab(Messages.getString("View.panel_messages.title"), null, panel_messages, null); //$NON-NLS-1$ //$NON-NLS-1$
		panel_messages.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane_messages = new JScrollPane();
		scrollPane_messages.setViewportBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollPane_messages.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panel_messages.add(scrollPane_messages, BorderLayout.CENTER);
		
		textArea_messages = new JTextArea();
		textArea_messages.setColumns(2);
		scrollPane_messages.setViewportView(textArea_messages);
		
		JLabel lblNewLabel = new JLabel(Messages.getString("View.lblNewLabel.text")); //$NON-NLS-1$ //$NON-NLS-1$
		scrollPane_messages.setColumnHeaderView(lblNewLabel);
		
		JPanel panel_uart = new JPanel();
		tabbedPane_messages.addTab(Messages.getString("View.panel_uart.title"), null, panel_uart, null); //$NON-NLS-1$ //$NON-NLS-1$
		panel_uart.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane_uart = new JScrollPane();
		scrollPane_uart.setViewportBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollPane_uart.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panel_uart.add(scrollPane_uart, BorderLayout.CENTER);
		
		textArea_uart = new JTextArea();
		textArea_uart.setColumns(2);
		scrollPane_uart.setViewportView(textArea_uart);
		
		button_synchronize = new JButton(Messages.getString("View.btnNewButton_1.text")); //$NON-NLS-1$
		button_synchronize.setText(Messages.getString("View.button_synchronize.text")); //$NON-NLS-1$
		GridBagConstraints gbc_button_synchronize = new GridBagConstraints();
		gbc_button_synchronize.fill = GridBagConstraints.HORIZONTAL;
		gbc_button_synchronize.insets = new Insets(0, 5, 5, 5);
		gbc_button_synchronize.gridx = 0;
		gbc_button_synchronize.gridy = 2;
		panel_main.add(button_synchronize, gbc_button_synchronize);
	}

	public void run() {
		try {
			form_main.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void subscribePingButtonClicked(final KrakenListener ev) {

		
	}

	public void subscribeSendMessage(KrakenListener ev) {
		
	}

	public void subscribeConnectButtonClicked(final KrakenListener ev) {
		button_connect.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				ev.fireEvent(View.this);
			}		
		});
	}

	public void subscribeDisconnectButtonClicked(KrakenListener ev) {
		// TODO Auto-generated method stub
		
	}

	public void subscribeTimesyncButtonClicked(KrakenListener ev) {
		// TODO Auto-generated method stub
		
	}

	public void addLogmessage(String timestamp, String msg) {
		// TODO Auto-generated method stub
		
	}

	public void setClients(String[] clients) {
		// TODO Auto-generated method stub
		
	}

	public String getMessageString() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getNewClient() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getCurrentClientIndex() {
		// TODO Auto-generated method stub
		return 0;
	}

	public String getNewConnectionField() {
		// TODO Auto-generated method stub
		return null;
	}

	public void addLogmessage(String timestamp, String target, String msg) {
		// TODO Auto-generated method stub
		
	}

	public void addUARTMessage(String timestamp, String target, String msg) {
		// TODO Auto-generated method stub
		
	}

	public void setNotification(String msg) {
		textField_status.setText(msg);
		
	}

}
