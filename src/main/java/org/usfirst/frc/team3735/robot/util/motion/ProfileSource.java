package org.usfirst.frc.team3735.robot.util.motion;

public interface ProfileSource {
	
	/**
	 * Uses the given path to retrieve all profiles.
	 * @param atRootPath The path on the file system where all files are stored.
	 */
	MotionProfile withProfilesFromFilesystem (String atRootPath);
	
	/**
	 * Uses the application current working directory as the root path.
	 */
	MotionProfile withProfilesFromFilesystem ();
	
	
	/*
	 * Uses the current jar to find the profiles in the /resources folder
	 */
	MotionProfile withProfilesFromJar ();
	
	MotionProfile withProfilesFromRoborio();


}
