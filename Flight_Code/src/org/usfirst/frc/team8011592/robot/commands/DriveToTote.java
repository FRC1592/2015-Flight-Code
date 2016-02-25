package org.usfirst.frc.team8011592.robot.commands;

import org.usfirst.frc.team8011592.robot.Constants;
import org.usfirst.frc.team8011592.robot.Robot;
import org.usfirst.frc.team8011592.robot.RobotMap;
import org.usfirst.frc.team8011592.robot.library.AnalogGyro;
import org.usfirst.frc.team8011592.robot.subsystems.LIDAR;

import edu.wpi.first.wpilibj.command.Command;

/**
 *Open arms and drive forward until an object is in the arms, then stop driving
 *and close the arms around it.
 */
public class DriveToTote extends Command {

	private double setpoint;
	private double headcmd;
	private Command armsOpen;
	private double distance;
	private double speed;
	private double initialDistance;
	private double y, mx;
	
	LIDAR lidar;
	
	public DriveToTote(double setpoint,double headcmd,double speed) { //inches from object to stop, heading to hold in deg
		requires(Robot.chassis);
		requires(Robot.armsHorizontal);

		this.setpoint=setpoint;
		this.headcmd = headcmd;
		this.speed = speed;
		
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	initialDistance = Robot.gatherer.getTargetDistance();
    	Robot.armsHorizontal.close();

    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
//    	distance = Robot.gatherer.getTargetDistance();
//    	mx = 1 - Constants.CHASSIS_KP*initialDistance;
//    	if (mx < 0){
//    		mx=0;
//    	}
//    	y = distance / initialDistance - 0.5;
		Robot.chassis.drivePrescribedHeading(0, speed, headcmd);
//		Robot.armsHorizontal.open();
		
		
    }
    
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (Robot.gatherer.getTargetDistance() < setpoint);
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.chassis.stop();
    	Robot.armsHorizontal.close();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
//    	System.out.println("Interrupted DriveToTote");
    	end();
    }
}
