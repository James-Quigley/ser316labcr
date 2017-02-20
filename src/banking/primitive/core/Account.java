/*
 * File: Account.java
 * Author: Kevin Gary
 * Date: February 20, 2017
 *
 * Description: Abstract class for classes Checking and Savings
 */
package banking.primitive.core;

/*
 * Class: Account
 *
 * Description: Abstract class for creating new account types
 */
public abstract class Account implements java.io.Serializable {

    /**
     * Method: Account()
     * Inputs: String n
     * Returns: Void
     *
     * Description: Constructor
     */
    protected Account(String n) {
        _name = n;
        _state = State.OPEN;
    }

    /**
     * Method: Account()
     * Inputs: String n, float b
     * Returns: Void
     *
     * Description: Constructor
     */
    protected Account(String n, float b) {
        this(n); 
        _balance = b;
    }

    /**
     * Method: getBalance()
     * Inputs: -
     * Returns: float
     *
     * Description: Getter for __balance
     */
    public final float getBalance() {
        return _balance;
    }

    /**
     * Method: getName()
     * Inputs: -
     * Returns: String
     *
     * Description: Getter for _name
     */
    public final String getName() {
        return _name;
    }

    /**
     * Method: getState()
     * Inputs: -
     * Returns: STATE
     *
     * Description: Returns the state of the account
     */
    protected final State getState() {
        return _state;
    }

    /**
     * Method: setState()
     * Inputs: State s
     * Returns: void
     *
     * Description: Sets the state of the account
     */
    protected final void setState(State s) {
        _state = s;
    }

    /**
     * Method: getType()
     * Inputs: -
     * Returns: String (either CHECKING or SAVINGS)
     *
     * Description: Returns the type of the account
     */
    public abstract String getType();

    /**
     * Method: deposit()
     * Inputs: float amount
     * Returns: boolean
     *
     * Description: Adds amount to _balance
     */
    public abstract boolean deposit(float amount);

     /**
     * Method: withdraw()
     * Inputs: float amount
     * Returns: boolean
     *
     * Description: Takes money out of an account. If the _balance falls below 0 then the
     * account is moved to an OVERDRAWN state
     */
    public abstract boolean withdraw(float amount);

    /**
     * Method: toString()
     * Inputs: -
     * Returns: String
     *
     * Description: toString method for Account
     */
    public String toString() {
        return "Account " + _name + " has $" + _balance + "and is " + getState()
                + "\n";
    }

    private static final long serialVersionUID = 1L;

    protected enum State {
        OPEN, CLOSED, OVERDRAWN
    };

    protected float _balance = 0.0F;
    protected String _name;
    private State _state;
}
