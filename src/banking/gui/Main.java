/*
 * File: Main.java
 * Author: Kevin Gary
 * Date: February 20, 2017
 *
 * Description: Main.java is the main class for the project. It contains
 * the main() method and will start the banking program
 */
package banking.gui;

import javax.swing.JFrame;

/**
 * class: Main
 * 
 * Description: Main is the main class for the project. It contains
 * the main() method and will start the banking program
 */
final class Main {
	/**
	 * Method: Main()
	 * Inputs: -
	 * Returns: Void
	 * 
	 * Description: Constructor
	 */
	private Main() {
	}
	
	/**
	 * Method: main()
	 * Inputs: String[] args
	 * Returns: Void
	 * 
	 * Description: main() method for Lab4 project
	 */
	public static void main(final String[] args) throws Exception {

		if (args.length != 1) {
			System.out.println("Usage: java FormMain <property file>");
			System.exit(1);
		}

		String propertyFile = args[0];
		JFrame frame = new MainFrame(propertyFile);
		frame.setVisible(true);

	}
}
