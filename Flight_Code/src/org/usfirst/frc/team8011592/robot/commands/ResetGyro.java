/*
 * $Revision: 432 $
 * $Date: 2015-04-14 15:13:08 -0400 (Tue, 14 Apr 2015) $
 * $HeadURL: https://ks-kdc-firs1001.ndc.nasa.gov/svn/robotics/2015_Season/Competition_Software/trunk/Flight_Code/src/org/usfirst/frc/team8011592/robot/commands/ResetGyro.java $
 * $Author: dedyer1 $
 */

package org.usfirst.frc.team8011592.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team8011592.robot.Robot;
import org.usfirst.frc.team8011592.robot.RobotMap;
import org.usfirst.frc.team8011592.robot.library.AnalogGyro;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */

public class ResetGyro extends Command {

	public ResetGyro() {
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		SmartDashboard.putBoolean("issettingHeading",
				Robot.chassis.settingHeading);

		Robot.chassis.resetGyro();

		Robot.chassis.setHeadingCmd(Robot.chassis.getGyroAngle());

		// Write to data file
		if (RobotMap.logFile != null) RobotMap.logFile.writeEventFRC("Init" + getName(),RobotMap.timer.getFPGATimestamp());
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return true;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
