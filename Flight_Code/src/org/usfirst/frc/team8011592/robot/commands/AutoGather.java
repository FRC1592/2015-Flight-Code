/*
 * $Revision: 343 $
 * $Date: 2015-02-24 02:10:31 -0500 (Tue, 24 Feb 2015) $
 * $HeadURL: https://ks-kdc-firs1001.ndc.nasa.gov/svn/robotics/2015_Season/Competition_Software/trunk/Flight_Code/src/org/usfirst/frc/team8011592/robot/commands/Gather.java $
 * $Author: rvaldez4 $
 */

package org.usfirst.frc.team8011592.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team8011592.robot.Constants;
import org.usfirst.frc.team8011592.robot.Robot;
import org.usfirst.frc.team8011592.robot.RobotMap;

/**
 *
 */
public class AutoGather extends Command {

	private double speed = 0.8;
	private static int toteCounts = 0;
	public AutoGather() {
		requires(Robot.gatherer);
	}
	public AutoGather(double speed) {
		requires(Robot.gatherer);
		this.speed = speed;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
//		System.out.println("Starting AutoGather Command");

	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.gatherer.setSpeed(speed);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		if (RobotMap.opticalBack.get()) {
			toteCounts++;
		} else toteCounts = 0;
		
		if (toteCounts >=Constants.TOTE_COUNTS_FOR_DETECTION) {
			return true;}
		else return false;
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
