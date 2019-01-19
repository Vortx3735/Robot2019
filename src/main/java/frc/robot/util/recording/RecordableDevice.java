package frc.robot.util.recording;

public interface RecordableDevice {

	public String getData();
	public void sendData(String data);
	public void setUpForRecording();
	public void setUpForSending();
	public void resestForNormal();
}
