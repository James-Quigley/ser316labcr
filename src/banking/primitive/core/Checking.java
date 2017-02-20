/*
 * File: Checking.java
 * Author: Kevin Gary
 * Date: February 20, 2017
 *
 * Description: Checking Bank Account Class
 */
package banking.primitive.core;

/*
 * Class: Checking
 *
 * Description: Checking Bank Account Class 
 */
public class Checking extends Account {

	private static final long _SERIALVERSIONUID = 11L;
	
	/**
	 * Method: Checking()
	 * Inputs: String account name
	 * Returns: Void
	 *
	 * Description: Constructor
	 */
	private Checking(String name) {
		super(name);
	}

	/**
	 * Method: createChecking()
	 * Inputs: String account name
	 * Returns: Checking Account
	 *
	 * Description: indirect Constructor
	 */
    public static Checking createChecking(String name) {
        return new Checking(name);
    }

    /**
	 * Method: Checking()
	 * Inputs: String account name, Float initial balance
	 * Returns: Void
	 *
	 * Description: Constructor
	 */
	public Checking(String name, float balance) {
		super(name, balance);
	}

	/**
	 * Method: getType()
	 * Inputs: 
	 * Returns: String
	 *
	 * Description: returns string literal of account type
	 */
	public String getType() {
		return "Checking";
	}
	
	/**
	 * Method: deposit()
	 * Inputs: Float amount
	 * Returns: Boolean
	 *
	 * Description: Adds amount to account
	 */
	public boolean deposit(float amount) {
		if (getState() != State.CLOSED && amount > 0.0f) {
			balance = balance + amount;
			if (balance >= 0.0f) {
				setState(State.OPEN);
			}
			return true;
		}
		return false;
	}

	/**
	 * Method: withdraw()
	 * Inputs: Float amount
	 * Returns: Boolean
	 *
	 * Description: Removes amount from account.
	 */
	public boolean withdraw(float amount) {
		if (amount > 0.0f) {		
			// KG: incorrect, last balance check should be >=
			if (getState() == State.OPEN || (getState() == State.OVERDRAWN && balance > -100.0f)) {
				balance = balance - amount;
				_numWithdraws++;
				if (_numWithdraws > 10) {
					balance = balance - 2.0f;
				}
				if (balance < 0.0f) {
					setState(State.OVERDRAWN);
				}
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Method: toString()
	 * Inputs: 
	 * Returns: String
	 *
	 * Description: returns account as string with name and balance
	 */
	public String toString() {
		return "Checking: " + getName() + ": " + getBalance();
	}
	
	private int _numWithdraws = 0;
}
