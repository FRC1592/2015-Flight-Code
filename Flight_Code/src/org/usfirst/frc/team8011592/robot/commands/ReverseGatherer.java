/*
 * $Revision: 419 $
 * $Date: 2015-04-07 21:52:14 -0400 (Tue, 07 Apr 2015) $
 * $HeadURL: https://ks-kdc-firs1001.ndc.nasa.gov/svn/robotics/2015_Season/Competition_Software/trunk/Flight_Code/src/org/usfirst/frc/team8011592/robot/commands/ReverseGatherer.java $
 * $Author: dedyer1 $
 */

package org.usfirst.frc.team8011592.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team8011592.robot.Constants;
import org.usfirst.frc.team8011592.robot.Robot;
import org.usfirst.frc.team8011592.robot.RobotMap;

/**
 * 
 * @author Bill
 *
 */

public class ReverseGatherer extends Command {
	
	private double speed = -0.4; //default deployment speed
	
	public ReverseGatherer() {
		requires(Robot.gatherer);
		requires(Robot.stacker);
	}
	
	public ReverseGatherer(double speed) {
		requires(Robot.gatherer);
		requires(Robot.stacker);
		this.speed = speed;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		// Write to data file
		if (RobotMap.logFile != null) {
			RobotMap.logFile.writeEventFRC("Init" + getName(),
					RobotMap.timer.getFPGATimestamp());
		}
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.gatherer.setSpeed(speed);
		//If stack is down (deploying), open stabilizers, else driver is trying to get a tote straightened out
		if(Robot.stacker.getSetpoint()==Constants.STACKER_LOW) Robot.stacker.openStabilizers();
		else Robot.stacker.squeezeStabilizers();
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
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
