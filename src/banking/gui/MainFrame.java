/*
 * File: MainFrame.java
 * Author: Kevin Gary
 * Date: February 20, 2017
 *
 * Description: Main GUI set-up for Lab4 project 
 */
package banking.gui;

import banking.primitive.core.Account;
import banking.primitive.core.AccountServer;
import banking.primitive.core.AccountServerFactory;

import java.io.*;
import java.util.*;
import java.awt.FlowLayout;
import java.awt.Container;
import java.awt.event.*;
import javax.swing.*;

/*
 * Class: MainFrame
 *
 * Description: Main GUI set-up for Lab4 project 
 */
@SuppressWarnings("serial")
class MainFrame extends JFrame {

	/**
	 * Method: MainFrame()
	 * Inputs: String propertyFile
	 * Returns: Void
	 *
	 * Description: Constructor
	 */
	public MainFrame(String propertyFile) throws IOException {

		//** initialize _myServer
		_myServer = AccountServerFactory.getMe().lookup();

		_props = new Properties();

		FileInputStream fis = null; 
		try {
			fis =  new FileInputStream(propertyFile);
			_props.load(fis);
			fis.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
			throw ioe;
		}
		constructForm();
	}

	/**
	 * Method: MainFrame()
	 * Inputs: String propertyFile
	 * Returns: Void
	 *
	 * Description: Constructs main graphic interface
	 */
	private void constructForm() {
		//*** Make these read from properties
		_typeLabel		= new JLabel(_props.getProperty("_typeLabel"));
		_nameLabel		= new JLabel(_props.getProperty("_nameLabel"));
		_balanceLabel	= new JLabel(_props.getProperty("_balanceLabel"));

		Object[] accountTypes = {"Savings", "Checking"};
		_typeOptions = new JComboBox(accountTypes);
		_nameField = new JTextField(20);
		_balanceField = new JTextField(20);

		_newAccountButton = new JButton("New Account");
		JButton _depositButton = new JButton("Deposit");
		JButton _withdrawButton = new JButton("Withdraw");
		JButton saveButton = new JButton("Save Accounts");
		_displayAccountsButton = new JButton("List Accounts");
		JButton displayAllAccountsButton = new JButton("All Accounts");

		this.addWindowListener(new FrameHandler());
		_newAccountButton.addActionListener(new NewAccountHandler());
		_displayAccountsButton.addActionListener(new DisplayHandler());
		displayAllAccountsButton.addActionListener(new DisplayHandler());
		_depositButton.addActionListener(new DepositHandler());
		_withdrawButton.addActionListener(new WithdrawHandler());
		saveButton.addActionListener(new SaveAccountsHandler());		
		
		Container pane = getContentPane();
		pane.setLayout(new FlowLayout());
		
		JPanel panel1 = new JPanel();
		panel1.add(_typeLabel);
		panel1.add(_typeOptions);
		
		JPanel panel2 = new JPanel();
		panel2.add(_displayAccountsButton);
		panel2.add(displayAllAccountsButton);
		panel2.add(saveButton);
		
		JPanel panel3 = new JPanel();
		panel3.add(_nameLabel);
		panel3.add(_nameField);
		
		JPanel panel4 = new JPanel();
		panel4.add(_balanceLabel);
		panel4.add(_balanceField);
		
		JPanel panel5 = new JPanel();
		panel5.add(_newAccountButton);
		panel5.add(_depositButton);
		panel5.add(_withdrawButton);

		pane.add(panel1);
		pane.add(panel2);
		pane.add(panel3);
		pane.add(panel4);
		pane.add(panel5);
		
		setSize(400, 250);
	}

	/**
 	* Class: DisplayHandler
 	*
 	* Description: Complete a handler for a "list accounts" button 
 	*/
	class DisplayHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			List<Account> accounts = null;
			if (e.getSource() == _displayAccountsButton) {
				accounts = _myServer.getActiveAccounts();
			} else {
				accounts = _myServer.getAllAccounts();
			}
			StringBuffer sb = new StringBuffer();
			Account thisAcct = null;
			for (Iterator<Account> li = accounts.iterator(); li.hasNext();) {
				thisAcct = (Account)li.next();
				sb.append(thisAcct.toString()+"\n");
			}

			JOptionPane.showMessageDialog(null, sb.toString());
		}
	}

	/**
 	* Class: NewAccountHandler
 	*
 	* Description: Complete a handler for new account button 
 	*/
	class NewAccountHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String type = _typeOptions.getSelectedItem().toString();
			String name = _nameField.getText();
			String balance = _balanceField.getText();

			if (_myServer.newAccount(type, name, Float.parseFloat(balance))) {
				JOptionPane.showMessageDialog(null, "Account created successfully");
			} else {
				JOptionPane.showMessageDialog(null, "Account not created!");
			}
		}
	}

	/**
 	* Class: SaveAccountHandler
 	*
 	* Description: Complete a handler for new account button 
 	*/
	class SaveAccountsHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				_myServer.saveAccounts();
				JOptionPane.showMessageDialog(null, "Accounts saved");
			} catch (IOException exc) {
				JOptionPane.showMessageDialog(null, "Error saving accounts");
			}
		}
	}

	/**
 	* Class: DepositHandler
 	*
 	* Description: Complete a handler for deposit button 
 	*/
	class DepositHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String name = _nameField.getText();
			String balance = _balanceField.getText();
			Account acc = _myServer.getAccount(name);
			if (acc != null && acc.deposit(Float.parseFloat(balance))) {
				JOptionPane.showMessageDialog(null, "Deposit successful");
			} else {
				JOptionPane.showMessageDialog(null, "Deposit unsuccessful");
			}		
		}
	}

	/**
 	* Class: WithdrawHandler
 	*
 	* Description: Complete a handler for deposit button 
 	*/
	class WithdrawHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String name = _nameField.getText();
			String balance = _balanceField.getText();
			Account acc = _myServer.getAccount(name);
			if (acc != null && acc.withdraw(Float.parseFloat(balance))) {
				JOptionPane.showMessageDialog(null, "Withdrawal successful");
			} else {
				JOptionPane.showMessageDialog(null, "Withdrawal unsuccessful");
			}		
		}
	}
	
	/**
 	* Class: FrameHandler
 	*
 	* Description: Complete a handler for the Frame that terminates 
	* (System.exit(1)) on windowClosing event
 	*/
	static class FrameHandler extends WindowAdapter {
		public void windowClosing(WindowEvent e) {

			System.exit(0);
		}
	}

	private AccountServer	_myServer;
	private Properties		_props;
	private JLabel			_typeLabel;
	private JLabel			_nameLabel;
	private JLabel			_balanceLabel;
	private JComboBox		_typeOptions;
	private JTextField		_nameField;
	private JTextField		_balanceField;
	private JButton 		_depositButton;
	private JButton 		_withdrawButton;
	private JButton			_newAccountButton;
	private JButton			_displayAccountsButton;
	private JButton			_displayODAccountsButton;
}
