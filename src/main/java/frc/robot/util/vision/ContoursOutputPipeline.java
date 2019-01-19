package frc.robot.util.vision;

import org.opencv.core.MatOfPoint;

import java.util.ArrayList;

public interface ContoursOutputPipeline {
	
	public ArrayList<MatOfPoint> filterContoursOutput();

}
