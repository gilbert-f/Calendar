public class Calendar {
	// Instance fields to compute the calendar
	private int month;

	private int year;
	
	// Reference point
	private final int REF_DAY = 1;
	
	/**
	 * Define a calendar for a month and a year (assume that the input is valid)
	 * 
	 * @param year
	 *            the year of the calendar (>= 1)
	 * @param month
	 *            the month of the calendar (>= 1 AND &lt;= 12)
	 */
	public Calendar(int month, int year) {
		this.month = month;
		this.year = year;
	}

	/**
	 * postcondition: result==the calendar nicely formatted in a String.<br>
	 * Example:<br>
	 * 
	 * <pre>
	 * 
	 *  August  2003
	 *  Su Mo Tu We Th Fr Sa
	 *                 1  2
	 *  3  4  5  6  7  8  9
	 *  10 11 12 13 14 15 16
	 *  17 18 19 20 21 22 23
	 *  24 25 26 27 28 29 30
	 *  31
	 * 
	 * </pre>
	 */
	public String toString() {
		// Create an output of string with all of the corresponding
		// days in the month
		String output = nameOfMonth() + " " + year + "\n" +
				"S  M  T  W  T  F  S  \n";
		// For loop that skips the days if the month does not start at
		// that day
		for (int d = 0; d < firstDayOfMonth(); d++) {
			output += "   ";
		}
		// For loop that appends the days to the output
		for (int d = 1; d <= numberOfDaysInMonth(); d++) {
			output += d + " ";
			// If the day is less than or equal to 9
			// an extra space will be appended to align the
			// calendar
			if (d <= 9) {
				output += " ";
			}
			// If the number of days in the week is 7
			// append a line spacing to align the calendar
			// in weeks
			if (((d + firstDayOfMonth()) % 7 == 0) || (d == numberOfDaysInMonth())) {
				output += "\n";
			}
		}
		// return the output
		return output;
	}

   /**
	*  Given the month, reference day (REF_DAY), and year, return which day
	*  of the week it falls on according to the Gregorian calendar.
	*  For month use 1 for January, 2 for February, and so forth.
	*/
	private int firstDayOfMonth() {
		int yearValue = year - (14 - month) / 12;
        int leapYearValue = yearValue + yearValue/4 - yearValue/100 + yearValue/400;
        int monthValue = month + 12 * ((14 - month) / 12) - 2;
        int firstDay = (REF_DAY + leapYearValue + (31*monthValue)/12) % 7;
        // Return the first day
        return firstDay;
	}

   /**
	*  Given the month and year, return the number of days in that
	*  particular month of the particular year by using arrays.
	*/
	private int numberOfDaysInMonth() {
        // days[month] = number of days in month
		// Leave index 0 empty since there is no month 0
        int[] days = { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

        // Check for leap year, if leap year second month
        // of that year is 29 days long
        if (month == 2 && isLeapYear()) days[month] = 29;
        // Return the number of days
        return days[month];
	}
	
   /**
	*  Given the month, return the name of that month
	*  using switch statement.
	*/	
	private String nameOfMonth() {
		String nameOfMonth = "";
		switch (month) {
		case 1:
			nameOfMonth = "January";
			break;
		case 2: 
			nameOfMonth = "Febuary";
			break;
		case 3:
			nameOfMonth = "March";
			break;
		case 4: 
			nameOfMonth = "April";
			break;
		case 5:
			nameOfMonth = "May";
			break;
		case 6: 
			nameOfMonth = "June";
			break;
		case 7: 
			nameOfMonth = "July";
			break;
		case 8:
			nameOfMonth = "August";
			break;
		case 9:
			nameOfMonth = "September";
			break;
		case 10:
			nameOfMonth = "October";
			break;
		case 11:
			nameOfMonth = "November";
			break;
		case 12:
			nameOfMonth = "December";
			break;
		default:
			break;
		}
		// Return the name of that month
		return nameOfMonth;
	}
	
   /**
	*  Given the year, return a boolean to check whether that year is a
	*  leap year.
	*/
	private boolean isLeapYear() {
		return (year % 4 == 0 && year % 100 != 0 || year % 400 == 0);
	}
}
