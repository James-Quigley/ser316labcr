/*
 * File: AccountServer.java
 * Author: Kevin Gary
 * Date: February 20, 2017
 *
 * Description: Interface for a server that manages bank accounts
 */

package banking.primitive.core;

import java.io.IOException;
import java.util.List;

public interface AccountServer {

	/**
	 * Method: getAccount()
	 * Inputs: String account name
	 * Returns: Account with name, or null
	 *
	 * Description: getter for a specific account
	 */
	public Account	getAccount(String name);

	/**
	 * Method: getAllAccounts()
	 * Inputs: 
	 * Returns: A list of all Accounts
	 *
	 * Description: getter for all accounts
	 */
	public List<Account> getAllAccounts();

	/**
	 * Method: getActiveAccounts()
	 * Inputs: 
	 * Returns: A list of all Accounts that are active
	 *
	 * Description: getter for all active accounts
	 */
	public List<Account> getActiveAccounts();

	/**
	 * Method: newAccount()
	 * Inputs: 
	 * 		String - account type (checking or savings)
	 * 		String - account name
	 * 		Float - starting balance
	 * Returns: Boolean - if account creation was successful
	 *
	 * Description: creates a new account from params
	 */
	public boolean	newAccount(String type, String name, float balance) throws IllegalArgumentException;

	/**
	 * Method: closeAccount()
	 * Inputs: String account name
	 * Returns: Boolean if account closing was successful
	 *
	 * Description: Closes a specific account
	 */
	public boolean	closeAccount(String name);
	
	/**
	 * Method: saveAccounts()
	 * Inputs: 
	 * Returns: 
	 *
	 * Description: Writes all accounts to accounts.ser
	 */
	public void	saveAccounts() throws IOException;
}
