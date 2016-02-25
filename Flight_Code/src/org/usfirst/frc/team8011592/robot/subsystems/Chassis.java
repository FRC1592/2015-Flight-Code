/**
* $Revision: 432 $
* $Date: 2015-04-14 15:13:08 -0400 (Tue, 14 Apr 2015) $
* $HeadURL: https://ks-kdc-firs1001.ndc.nasa.gov/svn/robotics/2015_Season/Competition_Software/trunk/Flight_Code/src/org/usfirst/frc/team8011592/robot/subsystems/Chassis.java $
* $Author: dedyer1 $
*/

package org.usfirst.frc.team8011592.robot.subsystems;

import org.usfirst.frc.team8011592.robot.commands.*;
import org.usfirst.frc.team8011592.robot.Constants;
import org.usfirst.frc.team8011592.robot.Robot;
import org.usfirst.frc.team8011592.robot.RobotMap;
import org.usfirst.frc.team8011592.robot.library.AnalogGyro;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Chassis extends Subsystem {

	//	private CANTalon frontLeft, rearLeft, rearRight, frontRight;
	private RobotDrive driveBase;
	private AnalogGyro gyro;
	private CANTalon frontLeft, frontRight, rearLeft, rearRight;
	public boolean settingHeading = false;
	public double gyroAngle;
	public double headingError;
	public double headingCMD;
	public boolean isFieldOriented;
	public static boolean isDriveFastEnabled;

	public double ans;
	public double zRateCmd, rateError;	
	//Ultrasonic sensor Parameters
	private WallRangeFinder ultra;
	private double wallDistance;
	private double strafelevel;

	public Chassis() {
		driveBase = RobotMap.driveBase;
		gyro = RobotMap.gyro;
		headingCMD = RobotMap.headingCMD;
		//Individual Motors are only for current logging
		frontLeft = RobotMap.frontLeftCIM;
		frontRight = RobotMap.frontRightCIM;
		rearLeft = RobotMap.rearLeftCIM;
		rearRight = RobotMap.rearRightCIM;
		//Ultrasonic Sensor
		ultra = RobotMap.ultra;
	}
	

	public void initDefaultCommand() {
		setDefaultCommand(new DriveWithJoysticks());
	}
	
	public double getWallDistance() {
		return ultra.getDistance();
	}
	
	public void resetGyro() {
		AnalogGyro.reset(); //Method is static in Analog Gyro Class
	}
	
	public void setHeadingCmd(double cmd) {
		headingCMD = cmd;
	}
	
	public double getGyroAngle() {
		return gyro.getAngle();
	}
	
	// Drive using the ultrasonic sensor to hold distance from wall
	public void driveWallDist(double forward, double clockwiseHeadingCmd, double ultraSetpoint){
		wallDistance = ultra.getDistance();
		strafelevel = -(wallDistance-ultraSetpoint)*Constants.MAXULTRA_KP;
		if(Math.abs(strafelevel)>1){
			strafelevel = 1*Math.signum(strafelevel);  // maximum strafelevel of 1
		}
		System.out.print(wallDistance);
		System.out.print(' ');
		System.out.print(strafelevel);
		System.out.print(' ');
		System.out.println(ultraSetpoint);
		gyroAngle = gyro.getAngle();
		headingCMD = clockwiseHeadingCmd;
		//Calculate Heading Error
		headingError = calcHeadingErr(headingCMD, gyroAngle);
		//Command drivebase
		driveBase.mecanumDrive_Cartesian(strafelevel,-forward,zRateCmd, 0.0);
	}
	
	public void drivePrescribedHeadingField(double right, double forward, double clockwiseHeadingCmd)
	{
		gyroAngle = gyro.getAngle();
		headingCMD = clockwiseHeadingCmd;
		//Calculate Heading Error
		headingError = calcHeadingErr(headingCMD, gyroAngle);
		//Command drivebase
		driveBase.mecanumDrive_Cartesian(right,-forward,zRateCmd, gyroAngle);
		headingCMD = gyro.getAngle();  // update headingCMD to smooth drive type switching
	}
	
	public void drivePrescribedHeading(double right, double forward, double clockwiseHeadingCmd)
	{
		gyroAngle = gyro.getAngle();
		headingCMD = clockwiseHeadingCmd;
		//Calculate Heading Error
		headingError = calcHeadingErr(headingCMD, gyroAngle);
		//Command drivebase
		driveBase.mecanumDrive_Cartesian(right,-forward,zRateCmd, 0.0);
		headingCMD = gyro.getAngle();  // update headingCMD to smooth drive type switching
		//  ***************************
		//Populate Smartdashboard with diagnostic data
//		SmartDashboard.putNumber("gyroAngle", gyroAngle);
//		SmartDashboard.putNumber("headingCMD", headingCMD);
//		SmartDashboard.putNumber("headingError", headingError);
//		SmartDashboard.putNumber("zRateCmd", zRateCmd);
//		SmartDashboard.putBoolean("isFieldOriented", RobotMap.isFieldOriented);
	}
	
	public void takeJoystickInputsNoGyro(Joystick driver) {
		
		if(RobotMap.isDriveFastEnabled){
			driveBase.mecanumDrive_Cartesian(joyExpo(driver.getX()),joyExpo(driver.getY()),joyExpo(driver.getZ()), 0.0);
			headingCMD = gyro.getAngle();  // update headingCMD to smooth drive type switching
		}
		
		else{
		driveBase.mecanumDrive_Cartesian(joyExpo(driver.getX())*Constants.STRAFE_SCALE,joyExpo(driver.getY())*Constants.FWD_REV_SCALE,joyExpo(driver.getZ()), 0.0);
		headingCMD = gyro.getAngle();  // update headingCMD to smooth drive type switching
	}
	}
	
	public void driveForward(double speed) {
		driveBase.mecanumDrive_Cartesian(0.0,-speed, 0.0, 0.0); //positive is backwards
	}
	
	public void strafe(double speed) {
		driveBase.mecanumDrive_Cartesian(speed,0.0, 0.0, 0.0); //positive is ?
	}

	public void takeJoystickInputs(Joystick driver) {
		gyroAngle = gyro.getAngle();  // offset is included in AnalogGyro class	
		if(Math.abs(driver.getZ()) > Constants.ZAXIS_DB) {
			if(RobotMap.isFieldOriented) {
				driveBase.mecanumDrive_Cartesian(joyExpo(driver.getX())*Constants.STRAFE_SCALE,joyExpo(driver.getY())*Constants.FWD_REV_SCALE,joyExpo(driver.getZ()*Constants.TURN_SCALE), gyroAngle);
			} 
			else {
				driveBase.mecanumDrive_Cartesian(joyExpo(driver.getX())*Constants.STRAFE_SCALE,joyExpo(driver.getY())*Constants.FWD_REV_SCALE,joyExpo(driver.getZ()*Constants.TURN_SCALE), 0.0);
			}
			headingCMD = gyro.getAngle();
		}
		else {
			headingError = calcHeadingErr(headingCMD, gyroAngle);
			// Drive the Robot with heading hold
			if(RobotMap.isFieldOriented) {
				driveBase.mecanumDrive_Cartesian(joyExpo(driver.getX())*Constants.STRAFE_SCALE,joyExpo(driver.getY())*Constants.FWD_REV_SCALE,zRateCmd, gyroAngle);
			} 
			else {
				driveBase.mecanumDrive_Cartesian(joyExpo(driver.getX())*Constants.STRAFE_SCALE,joyExpo(driver.getY())*Constants.FWD_REV_SCALE,zRateCmd, 0.0);
			}
		}
		
		//  ***************************
		//Populate Smartdashboard with diagnostic data
		SmartDashboard.putNumber("Stick X", joyExpo(driver.getX()));
		SmartDashboard.putNumber("Stick y", joyExpo(driver.getY()));
		SmartDashboard.putNumber("Stick z", driver.getZ());
		SmartDashboard.putNumber("gyroAngle", gyroAngle);
		SmartDashboard.putNumber("headingCMD", headingCMD);
		SmartDashboard.putNumber("headingError", headingError);
		SmartDashboard.putNumber("zRateCmd", zRateCmd);
//		SmartDashboard.putBoolean("isFieldOriented", RobotMap.isFieldOriented);
		
	}

	private double joyExpo(double stickInput){
		//  This function takes a joystick input and applies an exponential scaling
		//  For expo=1.5, the return value varies from about 50% of commanded at low inputs,
		//  To 80-100% of commanded at high rates
		double stickOutput;
		
		//Convert linear input magnitude to exponential from 0
		stickOutput = Math.exp(Math.abs(stickInput) * Constants.EXPO) -1;
		//Normalize to unit magnitude
		stickOutput = stickOutput / (Math.exp(Constants.EXPO) - 1);
		//Reapply polarity
		stickOutput = Math.signum(stickInput) * stickOutput;
		
		return stickOutput;
	}
	
	private double calcHeadingErr(double cmd, double act) {
		// keep heading command between 0 and 360
		cmd = cmd % 360.0;  
		if (cmd < 0) cmd += 360;
		//  Calculate the heading Error
		double err=act-cmd;
		// move heading error to the shortest path
		if (Math.abs(err) > 180) err -= 360 * Math.signum(err);
		return err;
	}

	public void stop() {
		driveBase.drive(0,0);
	}

	public void log() {
		//Gyro
		RobotMap.logFile.writeFRC("Gyro Angle [deg]", gyroAngle);
		RobotMap.logFile.writeFRC("Gyro Rate [dps]", gyro.getRate());
		//Heading Control
		RobotMap.logFile.writeFRC("Heading Error [deg]",headingError);
    	RobotMap.logFile.writeFRC("Heading Command [deg]",Robot.chassis.headingCMD);
    	RobotMap.logFile.writeFRC("Chassis Rate Command []",zRateCmd);
    	//Motor Current
		RobotMap.logFile.writeFRC("Rear Left Current[A]",rearLeft.getOutputCurrent());
		RobotMap.logFile.writeFRC("Rear Right Current [A]",rearRight.getOutputCurrent());
		RobotMap.logFile.writeFRC("Front Left Current [A]",frontLeft.getOutputCurrent());
		RobotMap.logFile.writeFRC("Front Right Current [A]",frontRight.getOutputCurrent());
		//Motor Voltage
		RobotMap.logFile.writeFRC("Rear Left Voltage [V]",rearLeft.getOutputVoltage());
		RobotMap.logFile.writeFRC("Rear Right Voltage [V]",rearRight.getOutputVoltage());
		RobotMap.logFile.writeFRC("Front Left Voltage [V]",frontLeft.getOutputVoltage());
		RobotMap.logFile.writeFRC("Front Right Voltage [V]",frontRight.getOutputVoltage());
//		RobotMap.logFile.writeFRC("Chassis Current Cmd", getCurrentCommand().toString());
	}

}