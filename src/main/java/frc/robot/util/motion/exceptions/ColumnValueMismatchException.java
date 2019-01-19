package frc.robot.util.motion.exceptions;


public class ColumnValueMismatchException extends Exception {

	
	public ColumnValueMismatchException (String c, long line, String vLeft, String vRight)
	{
		super (String.format("Column %s mismatch on line %d (%s != %s)", c, line, vLeft, vRight));
		
	}
	
}
