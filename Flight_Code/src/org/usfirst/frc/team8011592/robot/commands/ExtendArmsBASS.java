/*
 * $Revision: 312 $
 * $Date: 2015-02-14 16:15:53 -0500 (Sat, 14 Feb 2015) $
 * $HeadURL: https://ks-kdc-firs1001.ndc.nasa.gov/svn/robotics/2015_Season/Competition_Software/trunk/Flight_Code/src/org/usfirst/frc/team8011592/robot/commands/RaiseArms.java $
 * $Author: wwbenson $
 */

package org.usfirst.frc.team8011592.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team8011592.robot.Robot;

/**
 *
 */
public class ExtendArmsBASS extends Command {

	public ExtendArmsBASS() {
		requires(Robot.bassArms);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.bassArms.extendBASSLowerPiston();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.bassArms.retractBASSLowerPiston();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
