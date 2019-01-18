package frc.robot.util.motion.exceptions;

public class MissingColumnException extends Exception {

	private static final long serialVersionUID = -8819325514514271424L;


	public MissingColumnException(String theProfile, String columnName)
	{
		super(String.format("Column %s does not exist in profile %s.", columnName, theProfile));
	}
	
	
}
