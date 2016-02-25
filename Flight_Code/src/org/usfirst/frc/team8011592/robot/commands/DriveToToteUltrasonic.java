package org.usfirst.frc.team8011592.robot.commands;

import org.usfirst.frc.team8011592.robot.Robot;
import org.usfirst.frc.team8011592.robot.RobotMap;
import org.usfirst.frc.team8011592.robot.library.AnalogGyro;

import edu.wpi.first.wpilibj.command.Command;

/**
 *Open arms and drive forward until an object is in the arms, then stop driving
 *and close the arms around it.
 */
public class DriveToToteUltrasonic extends Command {

	private double setpoint;
	private double headcmd;
	private double wallDist;
	private double speed;
	//  Ultrasonic distance option

	public DriveToToteUltrasonic(double setpoint,double headcmd, double wallDist, double speed) { //inches from object to stop, heading to hold in deg
		requires(Robot.chassis);
		requires(Robot.armsHorizontal);
		
		this.setpoint = setpoint;
		this.headcmd = headcmd;
		this.wallDist = wallDist;
		this.speed = speed;
//		setInterruptible(false);
		
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.chassis.driveWallDist(speed, headcmd,wallDist);
		Robot.armsHorizontal.open();
    }
    
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
//    	System.out.println(Robot.gatherer.getTargetDistance());
//    	System.out.println(Robot.gatherer.getTargetDistance() < setpoint);
        return (Robot.gatherer.getTargetDistance() < setpoint);
//    	return false;
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
