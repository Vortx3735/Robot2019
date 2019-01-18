package org.usfirst.frc.team3735.robot.util.motion;



import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.usfirst.frc.team3735.robot.util.motion.exceptions.MissingColumnException;

public class MotionProfile implements ProfileName, ProfileSource {

	private static CSVFormat _theFormat = CSVFormat.newFormat(',').withFirstRecordAsHeader();

	enum ColumnsFromFile
	{
		x,
		y,
		velocity,
		heading
	};
	
	enum ColumnsFromFileRaw{
		x,
		y,
		heading,
		left,
		right
	}
	
	enum Side
	{
		left,
		right
	}
	
	private MotionProfile ()
	{
	}
	
	
	public static ProfileName builder ()
	{
		return new MotionProfile ();
	}

	private void assertHeaderIsValid (String forFile, Map<String, Integer> columnNames) throws MissingColumnException
	{
		
		for (ColumnsFromFile column : ColumnsFromFile.values() )
		{
			if (!columnNames.containsKey(column.toString()))
				throw new MissingColumnException (forFile, column.toString());
		}
		
	}

	public final MotionSet make () throws IOException, MissingColumnException {
		
		
		CSVParser leftParser = CSVParser.parse(getStream (Side.left), Charset.forName("ASCII"), _theFormat);
		CSVParser rightParser = CSVParser.parse(getStream(Side.right), Charset.forName("ASCII"), _theFormat);
		
		assertHeaderIsValid(getProfileName(Side.right), rightParser.getHeaderMap());
		assertHeaderIsValid(getProfileName(Side.left), leftParser.getHeaderMap());

		return new MotionSet (leftParser, rightParser);
	}
	
	public final MotionSet makeRaw() throws IOException, MissingColumnException{
		CSVParser parser = CSVParser.parse(getStream(_profName), Charset.forName("ASCII"), _theFormat);
		return new MotionSet(parser);
		
	}

	
	private String getProfileName (Side trackSide)
	{
		return String.format("%s_%s_detailed.csv", _profName, trackSide);
	}
	
	
	private InputStream getStream (Side trackSide) throws FileNotFoundException
	{
		
		if (!_fromJar)
		{
			Path p = FileSystems.getDefault().getPath(_rootPath, getProfileName(trackSide) );

			if (!p.toFile().exists())
				throw new FileNotFoundException(p.toString());
			
			return new FileInputStream (p.toFile());
		}
		else
		{
			return getClass().getResourceAsStream(String.format("%s/%s", _rootPath, getProfileName(trackSide))); 
		}
		
	}
	
	private InputStream getStream(String name)  throws FileNotFoundException{
		if (!_fromJar)
		{
			Path p = FileSystems.getDefault().getPath(_rootPath, name);

			if (!p.toFile().exists())
				throw new FileNotFoundException(p.toString());
			
			return new FileInputStream (p.toFile());
		}
		else
		{
			return getClass().getResourceAsStream(String.format("%s/%s", _rootPath, name)); 
		}
	}


	private String _rootPath;
	private Boolean _fromJar = false;
	
	@Override
	public MotionProfile withProfilesFromFilesystem(String atPath) {
		_rootPath = atPath;
		_fromJar = false;
		return this;
	}


	@Override
	public MotionProfile withProfilesFromJar() {
		_rootPath = "/resources";
		_fromJar = true;
		return this;
	}

	@Override
	public MotionProfile withProfilesFromFilesystem() {
		_rootPath = System.getProperty("user.dir");
		_fromJar = false;
		return this;
	}
	
	@Override
	public MotionProfile withProfilesFromRoborio() {
		_rootPath = "/home/lvuser/";
		_fromJar = false;
		return this;
	}
	
	


	private String _profName;
	@Override
	public ProfileSource withProfileName(String profileName) {
		_profName = profileName;

		return this;
	}
}
