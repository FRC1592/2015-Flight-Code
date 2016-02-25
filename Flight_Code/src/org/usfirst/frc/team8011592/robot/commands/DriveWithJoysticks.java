/*
 * $Revision: 398 $
 * $Date: 2015-03-20 10:56:29 -0400 (Fri, 20 Mar 2015) $
 * $HeadURL: https://ks-kdc-firs1001.ndc.nasa.gov/svn/robotics/2015_Season/Competition_Software/trunk/Flight_Code/src/org/usfirst/frc/team8011592/robot/commands/DriveWithJoysticks.java $
 * $Author: dedyer1 $
 */

package org.usfirst.frc.team8011592.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team8011592.robot.Robot;
import org.usfirst.frc.team8011592.robot.RobotMap;

/**
 *
 */
public class DriveWithJoysticks extends Command {

	public DriveWithJoysticks() {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.chassis);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		//If logging, mark event
		if (RobotMap.logFile != null) RobotMap.logFile.writeEventFRC("Init" + getName(),RobotMap.timer.getFPGATimestamp());
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (RobotMap.isGyroEnabled) {
			Robot.chassis.takeJoystickInputs(Robot.oi.getDriverGamepad());
			// Robot.chassis.takeJoystickInputsNoGyro(Robot.oi.getDriverGamepad());
		} else {
			Robot.chassis.takeJoystickInputsNoGyro(Robot.oi.getDriverGamepad());
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		//If logging, mark event
		if (RobotMap.logFile != null) RobotMap.logFile.writeEventFRC("Finish" + getName(),RobotMap.timer.getFPGATimestamp());
		//Stop drive train
		Robot.chassis.stop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		//If logging, mark event
		if (RobotMap.logFile != null) RobotMap.logFile.writeEventFRC("Pause" + getName(),RobotMap.timer.getFPGATimestamp());
		//TODO: remove?
		end();
	}
}
