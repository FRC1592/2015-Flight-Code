/*
 * $Revision: 312 $
 * $Date: 2015-02-14 16:15:53 -0500 (Sat, 14 Feb 2015) $
 * $HeadURL: https://ks-kdc-firs1001.ndc.nasa.gov/svn/robotics/2015_Season/Competition_Software/trunk/Flight_Code/src/org/usfirst/frc/team8011592/robot/commands/RaiseArms.java $
 * $Author: wwbenson $
 */

package org.usfirst.frc.team8011592.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team8011592.robot.Constants;
import org.usfirst.frc.team8011592.robot.Robot;
import org.usfirst.frc.team8011592.robot.RobotMap;

/**
 *
 */
public class LowerStack extends Command {
	
	double m_setpoint;
	
	public LowerStack() {
		requires(Robot.stacker);
		m_setpoint = Constants.STACKER_LOW;
	}
	
	public LowerStack(double aSetpoint) {
		requires(Robot.stacker);
		m_setpoint = aSetpoint;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		//No change if stacker is already targeting the specified setpoint
		
		if(m_setpoint != Robot.stacker.getSetpoint()) {
			//Command stacker open before updating setpoint so bin isn't jostled
			Robot.stacker.openStabilizers();
			Timer.delay(Constants.STABIL_DELAY);
			//Set stacker to the new position
			Robot.stacker.setToteState();
			Robot.stacker.setLastSetpoint(Robot.stacker.getSetpoint());
			Robot.stacker.setSetpoint(m_setpoint);
		}
		//Make sure the robot doesn't try to stack with the optical sensor triggers
		RobotMap.autoStack = false;
		//End if stacker doesn't reach setpoint within 0.5s
		setTimeout(2.0);
		// Write to data file
		if (RobotMap.logFile != null) RobotMap.logFile.writeEventFRC("Init" + getName(),RobotMap.timer.getFPGATimestamp());
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.stacker.secureStack();
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return Robot.stacker.onTarget() || isTimedOut();
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
