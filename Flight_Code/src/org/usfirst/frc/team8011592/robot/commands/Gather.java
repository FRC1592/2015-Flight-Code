/*
 * $Revision: 419 $
 * $Date: 2015-04-07 21:52:14 -0400 (Tue, 07 Apr 2015) $
 * $HeadURL: https://ks-kdc-firs1001.ndc.nasa.gov/svn/robotics/2015_Season/Competition_Software/trunk/Flight_Code/src/org/usfirst/frc/team8011592/robot/commands/Gather.java $
 * $Author: dedyer1 $
 */

package org.usfirst.frc.team8011592.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team8011592.robot.Robot;
import org.usfirst.frc.team8011592.robot.RobotMap;

/**
 *
 */
public class Gather extends Command {

	private boolean m_autoStack;

	public Gather(boolean autoStack) {
		requires(Robot.gatherer);
		m_autoStack = autoStack;

	}

	// Called just before this Command runs the first time
	protected void initialize() {
		RobotMap.autoStack = m_autoStack;
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.gatherer.setSpeed(1.0);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
