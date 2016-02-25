/**
* $Revision: 411 $
* $Date: 2015-03-24 20:52:20 -0400 (Tue, 24 Mar 2015) $
* $HeadURL: https://ks-kdc-firs1001.ndc.nasa.gov/svn/robotics/2015_Season/Competition_Software/trunk/Flight_Code/src/org/usfirst/frc/team8011592/robot/subsystems/HeadingPID.java $
* $Author: dedyer1 $
*/

package org.usfirst.frc.team8011592.robot.subsystems;

import org.usfirst.frc.team8011592.robot.Constants;
import org.usfirst.frc.team8011592.robot.Robot;
import org.usfirst.frc.team8011592.robot.RobotMap;

import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 *
 */
public class HeadingPID extends PIDSubsystem {

    // Initialize your subsystem here
	private double error, rateCmd;
	
    public HeadingPID() {
        super(Constants.HEADING_KP, Constants.HEADING_KI, Constants.HEADING_KD);
        setSetpoint(0.0);
       // setAbsoluteTolerance(3.0/180); //Deadband of 3 deg normalized to 180 deg full scale
        setAbsoluteTolerance(3.0); //Deadband of 3 deg normalized to 180 deg full scale
        enable();
        
    	//Advanced:
        //PIDController pid = getPIDController();
        //pid.setPID(p, i, d); //could be used to update constants in an update method
        //setInputRange(-180.0, 180.0); //deg
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    protected double returnPIDInput() {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;
    	error = Robot.chassis.headingError;
		if (Math.abs(error)>180){
			error-=360 * Math.signum(error);
		}    	
    //	return returnVal / 180; //normalize to 180 deg full scale error
    	return error;

    }
    
    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
    	rateCmd = output;
    	//Rate limit the command
    	if(Math.abs(rateCmd) > Constants.ROTATE_CMD_LIMIT) {
    		rateCmd = Constants.ROTATE_CMD_LIMIT * Math.signum(rateCmd);
    	}
    	//Provide deadband on rate command
    	if(Math.abs(rateCmd) < Constants.ZAXIS_DB) rateCmd=0.0;
    	//Set rate command in chassis
    	Robot.chassis.zRateCmd = rateCmd;
    }

	public void log() {

		RobotMap.logFile.writeFRC("Heading Controller Error [deg]",error);
		RobotMap.logFile.writeFRC("Heading Controller Cmd []",rateCmd);
	}
}
