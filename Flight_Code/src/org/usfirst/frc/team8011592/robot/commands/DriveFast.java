/*
 * $Revision: 391 $
 * $Date: 2015-03-16 20:32:47 -0400 (Mon, 16 Mar 2015) $
 * $HeadURL: https://ks-kdc-firs1001.ndc.nasa.gov/svn/robotics/2015_Season/Competition_Software/trunk/Flight_Code/src/org/usfirst/frc/team8011592/robot/commands/Gather.java $
 * $Author: wwbenson $
 */

package org.usfirst.frc.team8011592.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team8011592.robot.Robot;
import org.usfirst.frc.team8011592.robot.RobotMap;

/**
 *
 */
public class DriveFast extends Command {


	public DriveFast() {
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		RobotMap.isDriveFastEnabled = true;
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		RobotMap.isDriveFastEnabled = true;
		
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		RobotMap.isDriveFastEnabled = false;

	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		RobotMap.isDriveFastEnabled = false;

	}
}
