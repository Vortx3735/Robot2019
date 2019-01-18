package frc.robot.util.recording;

public class Filer {
	
	public static String make(String name, double d) {
		return name + ":" + d + "#";
	}
	
	public static String make(String name, double d, int decPlaces) {
		return name + ":" + String.format("%." + String.valueOf(decPlaces) + "f", d) + "#";

	}
	
	public static double getDouble(String name, String s) {
		String st = getValue(name, s);
		double d = Double.parseDouble(st);
		//System.out.println("Parsing " + st + " as "  + d);
		return d;
	}
	
	public static String getValue(String name, String s) {
		String cat = s.substring(s.indexOf(name));
		return cat.substring(cat.indexOf(":") + 1, cat.indexOf("#"));
	}
	
	
//	public static ArrayList<DriveState> getDriveStateArr(String filename) {
//		ArrayList<DriveState> array;
//		String filePath;
//		Scanner sc;
//    	Thread n = new Thread() {
//    		@Override
//    		public void run() {
//    	    	array = new ArrayList<>();
//    			filePath = ""  + filename + ".csv";
//    			//filePath = "C:\\Users\\Andrew\\Desktop\\"  + name + ".txt";
//
//    			try{
//    				sc = new Scanner(new File(filePath));
//    			}catch(Exception e){
////    				e.printStackTrace();
//    				System.out.println("Could not find file: " + filePath);
//    				return;
//    			}
//    	    	while(sc.hasNextLine()) {
//    	    		array.add(DriveState.fromString(sc.nextLine()));
//    	    	}
//    		}
//    	};
//    	n.start();
//	}
}
