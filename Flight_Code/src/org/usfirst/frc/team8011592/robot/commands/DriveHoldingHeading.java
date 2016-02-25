/*
 * $Revision: 398 $
 * $Date: 2015-03-20 10:56:29 -0400 (Fri, 20 Mar 2015) $
 * $HeadURL: https://ks-kdc-firs1001.ndc.nasa.gov/svn/robotics/2015_Season/Competition_Software/trunk/Flight_Code/src/org/usfirst/frc/team8011592/robot/commands/DriveHoldingHeading.java $
 * $Author: dedyer1 $
 */

package org.usfirst.frc.team8011592.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team8011592.robot.Robot;

/**
 *
 */
public class DriveHoldingHeading extends Command {

	private double fwdVelocity, rightVelocity, heading;

	public DriveHoldingHeading(double fwdVelocity,double rightVelocity, double heading) {
		requires(Robot.chassis);
		this.fwdVelocity = fwdVelocity;
		this.rightVelocity = rightVelocity;
		this.heading = heading;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		 Robot.chassis.drivePrescribedHeading(rightVelocity, fwdVelocity, heading);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.chassis.stop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
