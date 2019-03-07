import uwcse.graphics.*; // To create TextShape and Rectangle
import java.util.StringTokenizer; // To parse a String
import java.awt.Font; // To select a font for the display of the calendar
import javax.swing.JOptionPane; // To use dialog boxes 
import java.awt.Color; // To select colors

/**
 * CSC 142 - Homework<br>
 * 
 * A CalendarDisplay is a graphics window that displays a calendar for a given
 * year and month. A button on the window allows the user to select the year and
 * month of the calendar.
 * 
 * Gilbert Febrianto
 */

public class CalendarDisplay extends GWindowEventAdapter {
	// Graphics display
	private GWindow window;

	// To compute the calendar
	private Calendar calendar;
	
	// Instance fields to create the calendar
	private int month;

	private String stringInputMonth;

	/**
	 * Create the display
	 */
	public CalendarDisplay() {
		// Create the window
		window = new GWindow("Calendar", 400, 300);
		window.setExitOnClose();

		// Listen for any click on the window
		window.addEventHandler(this);
		
		// Calls the inputCalendar() method to input
		// the month and year of the calendar
		inputCalendar();
	}

	/**
	 * Click on the window. If it is on the button, input a new calendar.
	 */
	public void mousePressed(GWindowEvent e) {
		// Location of the click
		int x = e.getX();
		int y = e.getY();

		// If the user clicks on the the button
		// Input calendar
		if (x >= 130 && x <= 255 && y >= 250 && y <= 280) {
			// Calls the inputCalendar() method to input
			// the month and year of the calendar
			inputCalendar();
		}
	}

	/**
	 * Starts the application
	 */
	public static void main(String[] args) {
		new CalendarDisplay();
	}

	/**
	 * Draw the button to input calendar
	 */
	public void drawButton() {
		// Create a button to input the calendar
		Rectangle button = new Rectangle(130, 250, 125,
				30, Color.GREEN, true);
		// Create a text to indicate that it is a button
		// to input calendar
		TextShape text = new TextShape("NEW CALENDAR", 143, 260, Color.BLACK, 
				new Font("Times New Romans", Font.BOLD, 12));
		
		// Erase the window every time inputting the calendar
		window.erase();
		// Adds button to window
		window.add(button);
		// Adds text to window
		window.add(text);
	}
	
	/**
	 * Ask the user to input calendar
	 */
	private void inputCalendar() {
		// Calls the draw button method
		drawButton();
		
		String errorMessageMonth = "";
		// Execute the loop at least once
		do {
		    // Show input dialog with current error message, if any
		    stringInputMonth = JOptionPane.showInputDialog(errorMessageMonth + "Enter month.");
		   
		    // If the stringInputMonth has a value, go into the statement
		    if (stringInputMonth != null ) {
		    	// Accept only valid int within the range of int
		    	try {
		    		// Parse the stringInputMonth into int
			        month = Integer.parseInt(stringInputMonth);
			       
			        // If month is greater than 12 or less than 1,
			        // go into the statement
			        if (month > 12 || month < 1) {
			        	// Change the display of errorMessageMonth
			        	errorMessageMonth = "That month is not within the \n" + "allowed range!\n";
			        }
			        
			        else {
			        	// Change the display of errorMessageMonth to null,
			        	// so the loop closes
			            errorMessageMonth = ""; // no more error
			        }
			    }
		    	
		    	// If the month is an invalid int, go into the statement
		    	catch (NumberFormatException e) {
		    		// The typed text was not an integer
			        errorMessageMonth = "The text you typed is not a number of a month \n"
			        		+ "or you have not input anything.\n";
			    }
		    }
		    
		    // Break out of the loop is cancel is pressed
		    else break;
		  // Execute the loop as long as the errorMessageMonth is not empty
		} while (!errorMessageMonth.isEmpty());
		
		String errorMessageYear = " ";
		// Execute the loop when errorMessageYear is not empty and stringInputYear has a value
		while (!errorMessageYear.isEmpty() && stringInputMonth != null) {
		    // Show input dialog with current error message, if any
		    String stringInputYear = JOptionPane.showInputDialog(errorMessageYear + "Enter year.");
		   
		    // Goes in to the loop if the stringInputYear is not null
		    if (stringInputYear != null) {
		    	// Accept only valid int within the range of int
		    	try {
		    		// Parse the stringInputYear into int
		    		int year = Integer.parseInt(stringInputYear);
		    		
		    		// If the year is less than 1 or more than the range of int
		    		if (year < 1 || year > 2147483647) {
		    			// Change the display of error message
		    			errorMessageYear = "That year is not within the \n" + "allowed range!\n";
		    		}
		    		
		    		else {
		    			// Compute the calendar
		    			calendar = new Calendar(month, year);

		    			int yCoordinate = 50;
		    			
		    			// Use StringTokenizer to to extract each line in the 
		    			// String output into TextShape by Calendar.toString()
		    			StringTokenizer st = new StringTokenizer(calendar.toString(),"\n"); 
		    			while (st.hasMoreTokens()) { 
		    				String line = st.nextToken(); 
		    				TextShape days = new TextShape(line, 50, yCoordinate, Color.BLACK,
		    						new Font("COURIER", Font.BOLD, 25));
		    				window.add(days);
		    				yCoordinate += 25;
		    			}
		    			// Display error message to null so the loop closes
		    			errorMessageYear = ""; // no more error
		    		}
		    	}
		    	
		    	// If the year is an invalid int, go into the statement
		    	catch (NumberFormatException e) {
		    		// The typed text was not an integer
		    		errorMessageYear = "The text you typed is not a number of a year\n"
		    				+ "or you have not input anything.\n";
		    	}
		    }
		    
		 // Break out of the loop is cancel is pressed
		    else break;
		}
	}
}
