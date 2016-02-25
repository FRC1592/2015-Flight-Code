package org.usfirst.frc.team8011592.robot.subsystems;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.SensorBase;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Timer;
import org.usfirst.frc.team8011592.robot.Constants;

/**
 *
 */
public class WallRangeFinder extends SensorBase implements PIDSource {
    
	AnalogInput maxChan;
	private double output;
	
	public WallRangeFinder(int analogInputChannel){
		maxChan = new AnalogInput(analogInputChannel);
		initUltra();
	}
	
	public void initUltra(){

	}

	public double getDistance(){
		
		output = maxChan.getVoltage()*512.0/5.0;
		Timer.delay(0.01);
		//System.out.println(output);
		return output;
		
	}

	@Override
	public double pidGet() {
		// TODO Auto-generated method stub
		return getDistance();
	}
	
}

