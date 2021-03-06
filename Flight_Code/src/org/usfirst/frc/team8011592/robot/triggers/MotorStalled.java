package org.usfirst.frc.team8011592.robot.triggers;

import org.usfirst.frc.team8011592.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.buttons.Trigger;

/**
 *
 */
public class MotorStalled extends Trigger {
	
	private CANTalon m_motor;
	private int m_stallCurrent;
	private int m_count;
	private int m_stallTime; //sec
	
	public MotorStalled(CANTalon motor, int stallCurrent, int stallTimeMS) {
		m_motor = motor;
		m_stallCurrent = stallCurrent;
		m_count = 0;
		m_stallTime = stallTimeMS;
	}
    
    public boolean get() {
    	//If current is greater than stall current, increment counter
    	if(m_motor.getOutputCurrent() >= m_stallCurrent) {
    		m_count += 1;
    		System.out.println("Motor Stall Count: " + m_count * 20 + "ms");
    		//count is multiplied by 0.02 sec/cycle * 1000 millisec/sec = 20
    		if(m_count * 20 >= m_stallTime) {
        		System.out.println("Motor Stalled");
    			//Write to data file
    	        if(RobotMap.logFile != null) {
    	        	RobotMap.logFile.writeEventFRC("Motor" + Integer.toString(m_motor.getDeviceID()) + " Stalled", RobotMap.timer.getFPGATimestamp());
    	        }
    			return true;
    		}
    	} else m_count = 0; //if current drops below stall limit, reset counter
        return false;
    }
}
