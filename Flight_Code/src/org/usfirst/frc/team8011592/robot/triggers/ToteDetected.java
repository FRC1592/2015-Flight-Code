package org.usfirst.frc.team8011592.robot.triggers;

import org.usfirst.frc.team8011592.robot.Robot;
import org.usfirst.frc.team8011592.robot.RobotMap;

import edu.wpi.first.wpilibj.buttons.*;

/**
 * A custom button that is triggered when two buttons on a Joystick are
 * simultaneously pressed.
 */
public class ToteDetected extends Trigger {

	
	public ToteDetected() {
	}	
    
    public boolean get() {
        if(Robot.stacker.hasTote() && RobotMap.autoStack) {
        	//Mark Event if logging
            if(RobotMap.logFile != null) RobotMap.logFile.writeEventFRC("Tote Gathered", RobotMap.timer.getFPGATimestamp());
            return true;
        } else return false;
    }
}
