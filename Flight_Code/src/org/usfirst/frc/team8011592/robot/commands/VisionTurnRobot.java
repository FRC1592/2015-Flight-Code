package org.usfirst.frc.team8011592.robot.commands;

import org.usfirst.frc.team8011592.robot.Constants;
import org.usfirst.frc.team8011592.robot.Robot;
import org.usfirst.frc.team8011592.robot.RobotMap;
import org.usfirst.frc.team8011592.robot.library.AnalogGyro;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class VisionTurnRobot extends Command {
	
	private AnalogGyro gyronew;
	double z;
	double heading;
	double turnAngle;
	double gyroAngle;
	
	protected void initialize() {
		gyronew = RobotMap.gyro;
	}

	public VisionTurnRobot() {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.chassis);

	}

	protected void execute() {
		heading = (double) SmartDashboard.getNumber("angle");

		turnAngle = (double) gyronew.getAngle() - heading;		
		Robot.chassis.drivePrescribedHeading(0, 0, turnAngle); // set motor to turn
		
	}
    // Make this return true when this Command no longer needs to run execute()

	protected boolean isFinished() {
		return turnAngle < 3;
		
	}

	protected void end() {
		Robot.chassis.stop();
	}

	protected void interrupted() {
		end();
	}

}