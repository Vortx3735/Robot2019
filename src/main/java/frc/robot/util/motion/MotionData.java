package frc.robot.util.motion;

import frc.robot.util.motion.MotionProfile.ColumnsFromFile;
import frc.robot.util.motion.exceptions.ColumnValueMismatchException;
import org.apache.commons.csv.CSVRecord;

public class MotionData {

	// TODO: Need to separate returned data into left and right.

	private Double _centerX;
	private Double _centerY;
	private Double _heading;
	private Double _leftV;
	private Double _rightV;

	private long _lineNumber;

	MotionData(CSVRecord left, CSVRecord right, long lineNum)
			throws NumberFormatException, ColumnValueMismatchException {
		_lineNumber = lineNum;

		_centerX = (Double.valueOf(left.get(MotionProfile.ColumnsFromFile.y))
				+ Double.valueOf(right.get(MotionProfile.ColumnsFromFile.y))) / 2.0;

		_centerX = -(_centerX - 13.5)*12;
		
		_centerY = (Double.valueOf(left.get(MotionProfile.ColumnsFromFile.x))
				+ Double.valueOf(right.get(MotionProfile.ColumnsFromFile.x))) / 2.0;
		_centerY*=12.0;
		assertEqualValues(Double.valueOf(left.get(MotionProfile.ColumnsFromFile.heading)),
				Double.valueOf(right.get(MotionProfile.ColumnsFromFile.heading)),
				MotionProfile.ColumnsFromFile.heading);
		_heading = Double.valueOf(left.get(MotionProfile.ColumnsFromFile.heading));
		_heading = -Math.toDegrees(_heading);
		_leftV = Double.valueOf(left.get(MotionProfile.ColumnsFromFile.velocity)) * 12;
		_rightV = Double.valueOf(right.get(MotionProfile.ColumnsFromFile.velocity)) * 12;

	}
	
	/**
	 * This one gets raw drive values, that were recorded
	 * @param left
	 * @param right
	 * @param lineNum
	 * @param flag
	 * @throws NumberFormatException
	 * @throws ColumnValueMismatchException
	 */
	MotionData(CSVRecord record, long lineNum)
			throws NumberFormatException {
		_lineNumber = lineNum;
		_centerX = (Double.valueOf(record.get(MotionProfile.ColumnsFromFileRaw.x)));

		_centerY = (Double.valueOf(record.get(MotionProfile.ColumnsFromFileRaw.y)));

		_heading = Double.valueOf(record.get(MotionProfile.ColumnsFromFileRaw.heading));

		_leftV = Double.valueOf(record.get(MotionProfile.ColumnsFromFileRaw.left));
		
		_rightV = Double.valueOf(record.get(MotionProfile.ColumnsFromFileRaw.right));

	}

	private void assertEqualValues(Double l, Double r, ColumnsFromFile c) throws ColumnValueMismatchException {
		if (l.compareTo(r) != 0)
			throw new ColumnValueMismatchException(c.toString(), _lineNumber, l.toString(), r.toString());
	}

	public Double getCenterX() {
		return _centerX;
	}

	public Double getCenterY() {
		return _centerY;
	}

	public Double getHeading() {
		return _heading;
	}

	public Double getLeftV() {
		return _leftV;
	}

	public Double getRightV() {
		return _rightV;
	}

	@Override
	public String toString() {
		return String.format("%d: CenterX: %f CenterY: %f Heading: %f Left Velocity: %f Right Velocity: %f",
				_lineNumber, _centerX, _centerY, _heading, _leftV, _rightV);
	}

}
