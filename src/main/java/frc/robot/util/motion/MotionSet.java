package frc.robot.util.motion;

import frc.robot.subsystems.Drive;
import frc.robot.util.calc.VortxMath;
import frc.robot.util.motion.exceptions.ColumnValueMismatchException;
import frc.robot.util.profiling.Position;
import frc.robot.util.recording.DriveState;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.util.ArrayList;
import java.util.Iterator;

public class MotionSet implements Iterable<MotionData> {
	
	private CSVParser _leftParser;
	private CSVParser _rightParser;
	private long _lineNum = 1;
	
	class IteratorImpl implements Iterator<MotionData> 
	{
		private Iterator<CSVRecord> _left;
		private Iterator<CSVRecord> _right;
		
		IteratorImpl (MotionSet theSet)
		{
			_left = theSet._leftParser.iterator();
			_right = theSet._rightParser.iterator();
		}

		@Override
		public boolean hasNext() {
			return _left.hasNext() && _right.hasNext();
		}

		@Override
		public MotionData next() {
			try {
				return new MotionData (_left.next(), _right.next (), ++_lineNum);
			} catch (NumberFormatException | ColumnValueMismatchException e) {
				throw new RuntimeException (e);
			}
		}
		
	}
	


	MotionSet (CSVParser left, CSVParser right)
	{
		_leftParser = left;
		_rightParser = right;
	}

	MotionSet (CSVParser record){
		_leftParser = record;
	}

	@Override
	public Iterator<MotionData> iterator() {
		return new IteratorImpl(this);
	}
	
	public ArrayList<DriveState> list(){
		ArrayList<DriveState> arr = new ArrayList<>();
	
		for (MotionData d : this)
		{
			arr.add(new DriveState(
					new Position(d.getCenterX(), d.getCenterY(), d.getHeading()),
					d.getLeftV(), 
					d.getRightV()));
		}
		
		return arr;
	}
	
	public ArrayList<DriveState> listRaw(){
		ArrayList<DriveState> arr = new ArrayList<>();
		for (CSVRecord record : _leftParser){
			double _centerX = (Double.valueOf(record.get(MotionProfile.ColumnsFromFileRaw.x)));

			double _centerY = (Double.valueOf(record.get(MotionProfile.ColumnsFromFileRaw.y)));

			double _heading = Double.valueOf(record.get(MotionProfile.ColumnsFromFileRaw.heading));

			double _leftV = Double.valueOf(record.get(MotionProfile.ColumnsFromFileRaw.left));
			
			double _rightV = Double.valueOf(record.get(MotionProfile.ColumnsFromFileRaw.right));
			arr.add(new DriveState(new Position(_centerX, _centerY, _heading), _leftV, _rightV));
		}
		return arr;

	}

	
	public ArrayList<DriveState> reverseList(){
		ArrayList<DriveState> arr = new ArrayList<>();
		Iterator<MotionData> it = iterator();
	
		for (MotionData d : this)
		{
			arr.add(new DriveState(
					new Position(d.getCenterX(), d.getCenterY(), VortxMath.reverseYaw(d.getHeading())), 
					Drive.speedToPercent(-d.getRightV()),
					Drive.speedToPercent(-d.getLeftV())));
		}
		


		
		return arr;
	}
	
	


}
