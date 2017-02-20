/*
 * File: Checking.java
 * Author: Kevin Gary
 * Date: February 20, 2017
 *
 * Description: Savings Bank Account Class
 */
package banking.primitive.core;

/*
 * Class: Savings
 *
 * Description: Savings Bank Account Class 
 */
public class Savings extends Account {

	/**
	 * Method: Savings()
	 * Inputs: String account name
	 * Returns: Void
	 *
	 * Description: Constructor
	 */
	public Savings(String name) {
		super(name);
	}

	/**
	 * Method: Savings()
	 * Inputs: String account name, float balance
	 * Returns: void
	 *
	 * Description: Constructor
	 */
	public Savings(String name, float balance) throws IllegalArgumentException {
		super(name, balance);
	}

	/**
	 * Method: getType()
	 * Inputs: -
	 * Returns: String
	 *
	 * Description: returns string literal of account type
	 */
	public String getType() {
		return "Checking";
	}

	/**
	 * Method: depost()
	 * Inputs: float amount to deposir
	 * Returns: boolean success message
	 *
	 * Description: A deposit comes with a fee of 50 cents per deposit
	 */
	public boolean deposit(float amount) {
		if (getState() != State.CLOSED && amount > 0.0f) {
			_balance = _balance + amount - 0.50F;
			if (_balance >= 0.0f) {
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
	 * Description: A withdrawal. After 3 withdrawals a fee of $1 is added to each withdrawal.
	 * An account whose balance dips below 0 is in an OVERDRAWN state
	 */
	public boolean withdraw(float amount) {
		if (getState() == State.OPEN && amount > 0.0f) {
			_balance = _balance - amount;
			_numWithdraws++;
			if (_numWithdraws > 3)
				_balance = _balance - 1.0f;
			// KG BVA: should be < 0
			if (_balance <= 0.0f) {
				setState(State.OVERDRAWN);
			}
			return true;
		}
		return false;
	}

	/**
	 * Method: toString()
	 * Inputs: -
	 * Returns: String
	 *
	 * Description: returns account as string with name and balance
	 */
	public String toString() {
		return "Savings: " + getName() + ": " + getBalance();
	}

	private static final long serialVersionUID = 111L;
	private int _numWithdraws = 0;
}
