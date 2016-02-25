package org.usfirst.frc.team8011592.robot.commands;

import org.usfirst.frc.team8011592.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class TurnToHeading extends Command {
	
	private double heading;
	
	protected void initialize() {
	}

	public TurnToHeading(double heading) {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.chassis);
		this.heading = heading;
	}

	protected void execute() {
		
//		turnAngle = (double) gyronew.getAngle() - heading;
		Robot.chassis.drivePrescribedHeading(0, 0, heading); // set motor to turn

	}
    // Make this return true when this Command no longer needs to run execute()

	protected boolean isFinished() {
		return Robot.headingPID.onTarget();
		
	}

	protected void end() {
		Robot.chassis.stop();
	}

	protected void interrupted() {
		end();
	}

}