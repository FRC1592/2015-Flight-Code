package org.usfirst.frc.team8011592.robot.commands;

import org.usfirst.frc.team8011592.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class StopStacker extends Command {

	public StopStacker() {
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.stacker.setLastSetpoint(Robot.stacker.getSetpoint());
		if (Robot.stacker.getHeight() > Robot.stacker.getSetpoint()) {
			Robot.stacker.setSetpoint(Robot.stacker.getHeight()+1);
		} else {
			Robot.stacker.setSetpoint(Robot.stacker.getHeight()-1);
		}
			
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
