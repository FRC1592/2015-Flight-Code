/*
 * $Revision: 343 $
 * $Date: 2015-02-24 02:10:31 -0500 (Tue, 24 Feb 2015) $
 * $HeadURL: https://ks-kdc-firs1001.ndc.nasa.gov/svn/robotics/2015_Season/Competition_Software/trunk/Flight_Code/src/org/usfirst/frc/team8011592/robot/commands/Gather.java $
 * $Author: rvaldez4 $
 */

package org.usfirst.frc.team8011592.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team8011592.robot.Robot;
import org.usfirst.frc.team8011592.robot.RobotMap;

/**
 *
 */
public class AutoFlipBins extends Command {


	public AutoFlipBins() {
		requires(Robot.gatherer);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
//		System.out.println("Starting AutoGather Command");
		setTimeout(0.5);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.gatherer.setSpeed(0.8);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return RobotMap.opticalBack.get();
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.gatherer.stop();

	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
