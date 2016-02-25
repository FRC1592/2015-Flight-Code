package org.usfirst.frc.team8011592.robot.commands;

import org.usfirst.frc.team8011592.robot.Robot;
import org.usfirst.frc.team8011592.robot.RobotMap;
import org.usfirst.frc.team8011592.robot.library.AnalogGyro;
import org.usfirst.frc.team8011592.robot.subsystems.LIDAR;

import edu.wpi.first.wpilibj.command.Command;

/**
 *Open arms and drive forward until an object is in the arms, then stop driving
 *and close the arms around it.
 */
public class DriveToBin extends Command {

	private double fwdSpeed;
	private double headcmd;
	
	
	public DriveToBin(double speed,double headcmd) { //inches from object to stop, heading to hold in deg
		requires(Robot.chassis);
		requires(Robot.armsHorizontal);
		requires(Robot.gatherer);
		
		this.fwdSpeed = speed;
		this.headcmd = headcmd;
//		setInterruptible(false);
		
    }

    // Called just before this Command runs the first time
    protected void initialize() {
//		armsOpen.start();
//    	setTimeout(3.0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
//		Robot.chassis.drivePrescribedHeading(0, 0.2, headcmd);
		Robot.chassis.drivePrescribedHeading(0, fwdSpeed, headcmd);
		Robot.gatherer.setSpeedOpposing(-0.8);
    }
    
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
//    	System.out.println(Robot.gatherer.getTargetDistance());
//    	System.out.println(Robot.gatherer.getTargetDistance() < setpoint);
//        return (Robot.gatherer.getTargetDistance() < setpoint);
//        return isTimedOut();
    	return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.chassis.stop();
    	Robot.armsHorizontal.close();
//    	armsOpen.cancel();
//    	System.out.println("Ending DriveToTote");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
//    	System.out.println("Interrupted DriveToTote");
    	end();
    }
}
