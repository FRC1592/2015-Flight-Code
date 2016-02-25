/*
 * $Revision: 343 $
 * $Date: 2015-02-24 02:10:31 -0500 (Tue, 24 Feb 2015) $
 * $HeadURL: https://ks-kdc-firs1001.ndc.nasa.gov/svn/robotics/2015_Season/Competition_Software/trunk/Flight_Code/src/org/usfirst/frc/team8011592/robot/commands/ToggleFieldOriented.java $
 * $Author: rvaldez4 $
 */

package org.usfirst.frc.team8011592.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team8011592.robot.Robot;
import org.usfirst.frc.team8011592.robot.RobotMap;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ToggleFieldOriented extends Command {

	// private boolean value;

	public ToggleFieldOriented() {
		// Use requires() here to declare subsystem dependencies
		// requires(Robot.chassis);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		RobotMap.isFieldOriented = !RobotMap.isFieldOriented;

		// if(RobotMap.isFieldOriented) Robot.headingPID.enable();
		// else Robot.headingPID.disable();
		Robot.headingPID.enable(); // enable for both robot and field oriented

		SmartDashboard.putBoolean("isFieldOriented", RobotMap.isFieldOriented);
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
