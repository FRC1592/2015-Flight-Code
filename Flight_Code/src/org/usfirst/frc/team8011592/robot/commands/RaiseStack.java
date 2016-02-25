/*
 * $Revision: 312 $
 * $Date: 2015-02-14 16:15:53 -0500 (Sat, 14 Feb 2015) $
 * $HeadURL: https://ks-kdc-firs1001.ndc.nasa.gov/svn/robotics/2015_Season/Competition_Software/trunk/Flight_Code/src/org/usfirst/frc/team8011592/robot/commands/RaiseArms.java $
 * $Author: wwbenson $
 */

package org.usfirst.frc.team8011592.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team8011592.robot.Constants;
import org.usfirst.frc.team8011592.robot.Robot;
import org.usfirst.frc.team8011592.robot.RobotMap;

/**
 *
 */
public class RaiseStack extends Command {

	public RaiseStack() {
		requires(Robot.stacker);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.stacker.setLastSetpoint(Robot.stacker.getSetpoint());
//		Robot.stacker.setToteState();
		Robot.stacker.setSetpoint(Constants.STACKER_HIGH);
		setTimeout(1.5);
		// Write to Data file
		if (RobotMap.logFile != null) {
			RobotMap.logFile.writeEventFRC("Init" + getName(),
					RobotMap.timer.getFPGATimestamp());
		}
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
