package org.usfirst.frc.team8011592.robot.commands;

import org.usfirst.frc.team8011592.robot.Constants;
import org.usfirst.frc.team8011592.robot.Robot;
import org.usfirst.frc.team8011592.robot.RobotMap;
import org.usfirst.frc.team8011592.robot.library.AnalogGyro;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class VisionAutoGrab extends Command {
	
	AnalogGyro gyronew;
	private Command armsOpen;
	private double x, y;
	private double z;
	Gather gather;
	private double heading;
	
	public VisionAutoGrab(int headcmd) {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.chassis);
		requires(Robot.armsHorizontal);
		this.heading = headcmd;
	}
	protected void initialize() {
		SetStackHeight setStack = new SetStackHeight(Constants.STACKER_LOW);
		gyronew = RobotMap.gyro;
		armsOpen = new OpenArms();
		gyronew.reset();
		
		
	}

	protected void execute() {
		Robot.armsHorizontal.open();
		x = (double) SmartDashboard.getNumber("OFFSET_X");
		y = (double) SmartDashboard.getNumber("OFFSET_Y");
		Robot.chassis.drivePrescribedHeading(10*x, 1.25*y, heading);
	}
    // Make this return true when this Command no longer needs to run execute()

	protected boolean isFinished() {
		return y < 0.05;
	}
	protected void end() {
    	Robot.chassis.stop();
    	Robot.armsHorizontal.close();
	}
	protected void interrupted() {
		end();
	}

}
