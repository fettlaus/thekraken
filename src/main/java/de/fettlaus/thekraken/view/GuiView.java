package de.fettlaus.thekraken.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.Locale;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

public class GuiView implements View {

	private JFrame form_main;
	private JTextField textField_status;
	private JTextField textField_connect;
	private JButton button_connect;
	private DefaultListModel<String> list_targets_model;
	private JButton button_synchronize;
	private JTextArea textArea_messages;
	private JTextArea textArea_uart;
	private JButton button_ping;
	private JButton button_disconnect;
	private JTextField textField_port;
	private JTabbedPane tabbedPane_messages;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JMenu menu_file;
	private JMenu menu_help;
	private JMenuItem menuItem_help_about;
	private JMenu menu_language;
	private JMenuItem menuItem_file_close;
	private JRadioButtonMenuItem radio_language_en_us;
	private JRadioButtonMenuItem radio_language_de_de;
	private JLabel label_addtarget;
	private JLabel label_ip;
	private JLabel label_port;
	private JLabel label_targets;
	private JList<String> list_targets;

	/**
	 * Create the application.
	 */
	public GuiView() {
		initialize();
	}

	@Override
	public void addLogmessage(String timestamp, String target, String msg) {
		final StringBuilder b = new StringBuilder();
		b.append(timestamp).append(" <").append(target).append("> ").append(msg).append("\n");
		textArea_messages.append(b.toString());

	}

	@Override
	public void addUARTMessage(String timestamp, String target, String msg) {
		final StringBuilder b = new StringBuilder();
		b.append(timestamp).append(" <").append(target).append("> ").append(msg).append("\n");
		textArea_uart.append(b.toString());

	}

	@Override
	public int getCurrentClientIndex() {
		return list_targets.getSelectedIndex();
	}

	@Override
	public String getMessageString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getNewClientIP() {
		return textField_connect.getText();
	}

	@Override
	public String getNewClientPort() {
		return textField_port.getText();
	}

	@Override
	public void setClients(String[] clients) {
		list_targets_model.clear();
		for (final String client : clients) {
			list_targets_model.addElement(client);
		}

	}

	@Override
	public void setNotification(String msg) {
		textField_status.setText(msg);

	}

	@Override
	public void subscribeConnectButtonClicked(final ActionListener ev) {
		button_connect.addActionListener(ev);
	}

	@Override
	public void subscribeDisconnectButtonClicked(final ActionListener ev) {
		button_disconnect.addActionListener(ev);

	}

	@Override
	public void subscribePingButtonClicked(final ActionListener ev) {
		button_connect.addActionListener(ev);
	}

	@Override
	public void subscribeSendMessage(ActionListener ev) {
		// TODO Message sending
	}

	@Override
	public void subscribeTimesyncButtonClicked(final ActionListener ev) {
		button_synchronize.addActionListener(ev);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		form_main = new JFrame();
		form_main.setMinimumSize(new Dimension(500, 300));
		form_main.setBounds(100, 100, 864, 531);
		form_main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		final JPanel panel_main = new JPanel();
		form_main.setContentPane(panel_main);
		final GridBagLayout gbl_panel_main = new GridBagLayout();
		gbl_panel_main.columnWeights = new double[] { 1.0, 0.0, 0.0 };
		gbl_panel_main.rowWeights = new double[] { 0.0, 1.0, 0.0, 0.0 };
		panel_main.setLayout(gbl_panel_main);

		final JMenuBar menuBar_main = new JMenuBar();
		menuBar_main.setMinimumSize(new Dimension(0, 21));
		final GridBagConstraints gbc_menuBar_main = new GridBagConstraints();
		gbc_menuBar_main.anchor = GridBagConstraints.NORTH;
		gbc_menuBar_main.fill = GridBagConstraints.BOTH;
		gbc_menuBar_main.insets = new Insets(0, 0, 5, 5);
		gbc_menuBar_main.gridwidth = 3;
		gbc_menuBar_main.gridx = 0;
		gbc_menuBar_main.gridy = 0;
		panel_main.add(menuBar_main, gbc_menuBar_main);

		menu_file = new JMenu();
		menuBar_main.add(menu_file);

		menu_language = new JMenu();
		menu_file.add(menu_language);

		radio_language_en_us = new JRadioButtonMenuItem();
		radio_language_en_us.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Locale.setDefault(Locale.ENGLISH);
				Locale.setDefault(Locale.US);
				Messages.reloadBundle();
				GuiView.this.load_strings();
			}
		});
		buttonGroup.add(radio_language_en_us);
		radio_language_en_us.setSelected(true);
		menu_language.add(radio_language_en_us);

		radio_language_de_de = new JRadioButtonMenuItem();
		buttonGroup.add(radio_language_de_de);
		radio_language_de_de.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Locale.setDefault(Locale.GERMAN);
				Locale.setDefault(Locale.GERMANY);
				Messages.reloadBundle();
				GuiView.this.load_strings();
			}
		});
		menu_language.add(radio_language_de_de);
		final String lang = Locale.getDefault().getLanguage();
		if (lang.equals("de")) {
			radio_language_de_de.setSelected(true);
		} else {
			radio_language_en_us.setSelected(true);
		}
		menuItem_file_close = new JMenuItem();
		menu_file.add(menuItem_file_close);

		menu_help = new JMenu();
		menuBar_main.add(menu_help);

		menuItem_help_about = new JMenuItem();
		menuItem_help_about.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(form_main, Messages.getString("About.text"), Messages.getString("About.title"), JOptionPane.INFORMATION_MESSAGE);
			}
		});
		menu_help.add(menuItem_help_about);

		final JSeparator main_separator = new JSeparator();
		main_separator.setPreferredSize(new Dimension(2, 0));
		main_separator.setOrientation(SwingConstants.VERTICAL);
		final GridBagConstraints gbc_main_separator = new GridBagConstraints();
		gbc_main_separator.gridheight = 2;
		gbc_main_separator.fill = GridBagConstraints.VERTICAL;
		gbc_main_separator.insets = new Insets(4, 4, 4, 4);
		gbc_main_separator.gridx = 1;
		gbc_main_separator.gridy = 1;
		panel_main.add(main_separator, gbc_main_separator);

		final JPanel panel_targets = new JPanel();
		final GridBagConstraints gbc_panel_targets = new GridBagConstraints();
		gbc_panel_targets.fill = GridBagConstraints.VERTICAL;
		gbc_panel_targets.gridheight = 2;
		gbc_panel_targets.insets = new Insets(5, 5, 5, 5);
		gbc_panel_targets.gridx = 2;
		gbc_panel_targets.gridy = 1;
		panel_main.add(panel_targets, gbc_panel_targets);
		final GridBagLayout gbl_panel_targets = new GridBagLayout();
		gbl_panel_targets.columnWidths = new int[] { 0, 0, 0 };
		gbl_panel_targets.columnWeights = new double[] { 0.0, 1.0, 0.0 };
		gbl_panel_targets.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 };
		panel_targets.setLayout(gbl_panel_targets);

		label_addtarget = new JLabel();
		final GridBagConstraints gbc_label_addtarget = new GridBagConstraints();
		gbc_label_addtarget.gridwidth = 3;
		gbc_label_addtarget.insets = new Insets(2, 2, 5, 2);
		gbc_label_addtarget.gridx = 0;
		gbc_label_addtarget.gridy = 0;
		panel_targets.add(label_addtarget, gbc_label_addtarget);

		label_ip = new JLabel();
		final GridBagConstraints gbc_label_ip = new GridBagConstraints();
		gbc_label_ip.insets = new Insets(2, 2, 5, 5);
		gbc_label_ip.anchor = GridBagConstraints.WEST;
		gbc_label_ip.gridx = 0;
		gbc_label_ip.gridy = 1;
		panel_targets.add(label_ip, gbc_label_ip);

		label_port = new JLabel();
		final GridBagConstraints gbc_label_port = new GridBagConstraints();
		gbc_label_port.anchor = GridBagConstraints.EAST;
		gbc_label_port.insets = new Insets(2, 2, 5, 5);
		gbc_label_port.gridx = 0;
		gbc_label_port.gridy = 2;
		panel_targets.add(label_port, gbc_label_port);

		textField_port = new JTextField();
		textField_port.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				textField_port.setText("");
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (textField_port.getText().isEmpty()) {
					textField_port.setText(Messages.getString("View.textField_port.text"));
				}
			}
		});
		final GridBagConstraints gbc_textField_port = new GridBagConstraints();
		gbc_textField_port.insets = new Insets(2, 2, 2, 2);
		gbc_textField_port.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_port.gridx = 1;
		gbc_textField_port.gridy = 2;
		panel_targets.add(textField_port, gbc_textField_port);
		textField_port.setColumns(10);

		label_targets = new JLabel();
		final GridBagConstraints gbc_label_targets = new GridBagConstraints();
		gbc_label_targets.gridwidth = 3;
		gbc_label_targets.insets = new Insets(2, 2, 5, 2);
		gbc_label_targets.gridx = 0;
		gbc_label_targets.gridy = 4;
		panel_targets.add(label_targets, gbc_label_targets);

		final JScrollPane scrollPane_targets = new JScrollPane();
		scrollPane_targets.setSize(new Dimension(20, 0));
		scrollPane_targets.setMaximumSize(new Dimension(30, 32767));
		scrollPane_targets.setPreferredSize(new Dimension(30, 3));
		final GridBagConstraints gbc_scrollPane_targets = new GridBagConstraints();
		gbc_scrollPane_targets.insets = new Insets(2, 2, 5, 2);
		gbc_scrollPane_targets.weighty = 1.0;
		gbc_scrollPane_targets.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_targets.gridwidth = 3;
		gbc_scrollPane_targets.gridx = 0;
		gbc_scrollPane_targets.gridy = 5;
		panel_targets.add(scrollPane_targets, gbc_scrollPane_targets);

		list_targets_model = new DefaultListModel<String>();
		list_targets = new JList<String>(list_targets_model);
		list_targets.setAlignmentX(Component.LEFT_ALIGNMENT);
		scrollPane_targets.setViewportView(list_targets);
		list_targets.setAlignmentY(Component.TOP_ALIGNMENT);

		final JSeparator separator_target = new JSeparator();
		separator_target.setPreferredSize(new Dimension(0, 3));
		final GridBagConstraints gbc_separator_target = new GridBagConstraints();
		gbc_separator_target.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator_target.gridwidth = 3;
		gbc_separator_target.insets = new Insets(2, 2, 5, 2);
		gbc_separator_target.gridx = 0;
		gbc_separator_target.gridy = 3;
		panel_targets.add(separator_target, gbc_separator_target);

		textField_connect = new JTextField();
		textField_connect.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (textField_connect.getText().equals(Messages.getString("View.textField_connect.text"))) {
					textField_connect.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (textField_connect.getText().isEmpty()) {
					textField_connect.setText(Messages.getString("View.textField_connect.text"));
				}
			}
		});
		textField_connect.setMinimumSize(new Dimension(120, 19));
		textField_connect.setPreferredSize(new Dimension(100, 19));
		textField_connect.setColumns(11);
		final GridBagConstraints gbc_textField_connect = new GridBagConstraints();
		gbc_textField_connect.weightx = 0.5;
		gbc_textField_connect.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_connect.insets = new Insets(2, 2, 2, 2);
		gbc_textField_connect.gridx = 1;
		gbc_textField_connect.gridy = 1;
		panel_targets.add(textField_connect, gbc_textField_connect);

		button_connect = new JButton();

		final GridBagConstraints gbc_button_connect = new GridBagConstraints();
		gbc_button_connect.gridheight = 2;
		gbc_button_connect.weightx = 0.5;
		gbc_button_connect.fill = GridBagConstraints.BOTH;
		gbc_button_connect.insets = new Insets(2, 2, 2, 2);
		gbc_button_connect.gridx = 2;
		gbc_button_connect.gridy = 1;
		panel_targets.add(button_connect, gbc_button_connect);

		button_ping = new JButton();
		final GridBagConstraints gbc_button_ping = new GridBagConstraints();
		gbc_button_ping.gridwidth = 2;
		gbc_button_ping.insets = new Insets(2, 2, 2, 5);
		gbc_button_ping.fill = GridBagConstraints.HORIZONTAL;
		gbc_button_ping.gridx = 0;
		gbc_button_ping.gridy = 6;
		panel_targets.add(button_ping, gbc_button_ping);

		button_disconnect = new JButton();
		final GridBagConstraints gbc_button_disconnect = new GridBagConstraints();
		gbc_button_disconnect.insets = new Insets(2, 2, 2, 2);
		gbc_button_disconnect.fill = GridBagConstraints.HORIZONTAL;
		gbc_button_disconnect.gridx = 2;
		gbc_button_disconnect.gridy = 6;
		panel_targets.add(button_disconnect, gbc_button_disconnect);

		final JPanel panel_status = new JPanel();
		final GridBagConstraints gbc_panel_status = new GridBagConstraints();
		gbc_panel_status.insets = new Insets(5, 5, 5, 5);
		gbc_panel_status.anchor = GridBagConstraints.NORTH;
		gbc_panel_status.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_status.gridwidth = 3;
		gbc_panel_status.gridx = 0;
		gbc_panel_status.gridy = 3;
		panel_main.add(panel_status, gbc_panel_status);
		final GridBagLayout gbl_panel_status = new GridBagLayout();
		gbl_panel_status.columnWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		gbl_panel_status.rowWeights = new double[] { 0.0 };
		panel_status.setLayout(gbl_panel_status);

		textField_status = new JTextField();
		textField_status.setEditable(false);
		final GridBagConstraints gbc_textField_status = new GridBagConstraints();
		gbc_textField_status.gridwidth = 3;
		gbc_textField_status.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_status.gridx = 0;
		gbc_textField_status.gridy = 0;
		panel_status.add(textField_status, gbc_textField_status);
		textField_status.setColumns(10);

		tabbedPane_messages = new JTabbedPane(SwingConstants.TOP);
		final GridBagConstraints gbc_tabbedPane_messages = new GridBagConstraints();
		gbc_tabbedPane_messages.weighty = 0.9;
		gbc_tabbedPane_messages.weightx = 1.0;
		gbc_tabbedPane_messages.insets = new Insets(0, 5, 5, 5);
		gbc_tabbedPane_messages.fill = GridBagConstraints.BOTH;
		gbc_tabbedPane_messages.gridx = 0;
		gbc_tabbedPane_messages.gridy = 1;
		panel_main.add(tabbedPane_messages, gbc_tabbedPane_messages);

		final JPanel panel_messages = new JPanel();
		tabbedPane_messages.addTab(Messages.getString("View.panel_messages.title"), null, panel_messages, null); //$NON-NLS-1$ 
		panel_messages.setLayout(new BorderLayout(0, 0));

		final JScrollPane scrollPane_messages = new JScrollPane();
		scrollPane_messages.setViewportBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollPane_messages.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panel_messages.add(scrollPane_messages, BorderLayout.CENTER);

		textArea_messages = new JTextArea();
		textArea_messages.setColumns(2);
		scrollPane_messages.setViewportView(textArea_messages);

		final JLabel lblNewLabel = new JLabel();
		scrollPane_messages.setColumnHeaderView(lblNewLabel);

		final JPanel panel_uart = new JPanel();
		tabbedPane_messages.addTab(Messages.getString("View.panel_uart.title"), null, panel_uart, null); //$NON-NLS-1$ 
		panel_uart.setLayout(new BorderLayout(0, 0));

		final JScrollPane scrollPane_uart = new JScrollPane();
		scrollPane_uart.setViewportBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollPane_uart.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panel_uart.add(scrollPane_uart, BorderLayout.CENTER);

		textArea_uart = new JTextArea();
		textArea_uart.setColumns(2);
		scrollPane_uart.setViewportView(textArea_uart);

		button_synchronize = new JButton();

		final GridBagConstraints gbc_button_synchronize = new GridBagConstraints();
		gbc_button_synchronize.fill = GridBagConstraints.HORIZONTAL;
		gbc_button_synchronize.insets = new Insets(0, 5, 5, 5);
		gbc_button_synchronize.gridx = 0;
		gbc_button_synchronize.gridy = 2;
		panel_main.add(button_synchronize, gbc_button_synchronize);
		load_strings();
		form_main.setVisible(true);

	}

	protected void load_strings() {
		button_connect.setText(Messages.getString("View.button_connect.text"));
		button_disconnect.setText(Messages.getString("View.button_disconnect.text"));
		button_ping.setText(Messages.getString("View.button_ping.text"));
		button_synchronize.setText(Messages.getString("View.button_synchronize.text"));
		form_main.setTitle(Messages.getString("View.form_main.title"));
		label_addtarget.setText(Messages.getString("View.label_addtarget.text"));
		label_ip.setText(Messages.getString("View.label_ip.text"));
		label_port.setText(Messages.getString("View.label_port.text"));
		label_targets.setText(Messages.getString("View.label_targets.text"));
		menu_file.setText(Messages.getString("View.menu_file.s"));
		menu_help.setText(Messages.getString("View.menu_help.text"));
		menu_language.setText(Messages.getString("View.menu_language.text"));
		menuItem_file_close.setText(Messages.getString("View.menuItem_file_close.text"));
		menuItem_help_about.setText(Messages.getString("View.menuItem_help_close_1.text"));
		radio_language_de_de.setText(Messages.getString("View.radio_language_de_de.text"));
		radio_language_en_us.setText(Messages.getString("View.radio_language_en_us.text"));
		tabbedPane_messages.setTitleAt(0, Messages.getString("View.panel_messages.title"));
		tabbedPane_messages.setTitleAt(1, Messages.getString("View.panel_uart.title"));
		textField_connect.setText(Messages.getString("View.textField_connect.text"));
		textField_port.setText(Messages.getString("View.textField_port.text"));

	}

}
