/*
 * File: AccountServerFactory.java
 * Author: Kevin Gary
 * Date: February 20, 2017
 *
 * Description: Singleton class for an Account Server
 */
package banking.primitive.core;

/*
 * Class: AccountServerFactory
 *
 * Description: Singleton class that can create a new ServerSolution
 */
public class AccountServerFactory {

	protected AccountServerFactory() {

	}

	/**
	 * Method: getMe()
	 * Inputs:
	 * Returns: AccountServerFactory
	 *
	 * Description: Singleton Constructor
	 */
	public static AccountServerFactory getMe() {
		if (_singleton == null) {
			_singleton = new AccountServerFactory();
		}

		return _singleton;
	}

	/**
	 * Method: lookup()
	 * Inputs:
	 * Returns: AccountServer
	 *
	 * Description: Gets an account server, and the singleton ensures only one is used
	 */
	public AccountServer lookup() {
		return new ServerSolution();
	}

	protected static AccountServerFactory _singleton = null;
}
