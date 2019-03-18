package arithmetic;

import javax.swing.JOptionPane;

public abstract class ReadInt {
	
	/**
	 * Prompt the user for an integer value, and read this value.
	 * Calls read with an initial null value, which will keep prompting for
	 * input until a correctly formatted value is given.
	 */
	public static int read(String message) {
		String input = (String) JOptionPane.showInputDialog(message); // attempt to read value
		return parse(message,input); // parse the input - will repeat if cannot be parsed
	}
	
	/**
	 * Attempt to parse a string that should represent an integer value, which can be passed
	 * to an integer function.
	 * If the string is null, or the parse fails prompt the user for input.
	 * @param message a description of the input - used when prompting the user
	 * @param input the string to be parsed
	 * @return the int the input (argument or user prompted) parses to
	 */
    protected static int parse(String message,String input) {
    	String prompt;
    	do {
    		if (input == null || input.compareTo("") == 0) { // no input value
    			prompt = "No value provided.\n" + message;
    		} else {
    			// try to parse the input 
    			try {
    				int result = Integer.parseInt(input);
    				return result;
    			} catch (NumberFormatException nfe) { // input was not a double
    				prompt = "Value was incorrectly formatted\nInput must be a correctly formatted integer value.\n" + message;
    			}
    		}
    		// input was empty or parsing failed - prompt for new input
    		input = (String) JOptionPane.showInputDialog(prompt);
    	} while (true); // code exits when a valid number is input
    }
    
}
