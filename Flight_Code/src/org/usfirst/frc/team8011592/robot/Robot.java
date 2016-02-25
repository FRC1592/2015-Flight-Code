/*
* $Revision: 419 $
* $Date: 2015-04-07 21:52:14 -0400 (Tue, 07 Apr 2015) $
* $HeadURL: https://ks-kdc-firs1001.ndc.nasa.gov/svn/robotics/2015_Season/Competition_Software/trunk/Flight_Code/src/org/usfirst/frc/team8011592/robot/Robot.java $
* $Author: dedyer1 $
*/

package org.usfirst.frc.team8011592.robot;

import java.io.IOException;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.internal.HardwareTimer;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team8011592.robot.commands.*;
import org.usfirst.frc.team8011592.robot.subsystems.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	//Define Subsystems
	public static Chassis chassis;
	public static Gatherer gatherer;
	public static StackerPID stacker;
	public static HeadingPID headingPID;
	public static ArmsHorizontal armsHorizontal;
	public static Pneumatics pneumatics;
	public static BassArms bassArms;
	public static BassFingers bassFingers;
	public static OI oi;
	public static PowerDistributionPanel pdp;
	
	//Auto mode selection from Dashboard
	public static int autoCmdSelection;
    Command autonomousCommand;
	
	//TODO: remove
//	private static Timer timer = new Timer();


    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {

    	//Initialize the robot actuators and sensors
    	RobotMap.init();
    	pdp = RobotMap.pdp;
    	
    	//Instantiate Subsystems
    	chassis = new Chassis();
    	stacker = new StackerPID();
    	gatherer = new Gatherer();
    	headingPID = new HeadingPID();
    	armsHorizontal = new ArmsHorizontal();
    	bassArms= new BassArms();
    	bassFingers = new BassFingers();
    	pneumatics = new Pneumatics();

    	//Populate SmartDashboard with subsystems data
    	SmartDashboard.putData(chassis);
    	SmartDashboard.putData(stacker);
    	SmartDashboard.putData(gatherer);
    	SmartDashboard.putData(headingPID);
    	SmartDashboard.putData(armsHorizontal);
    	SmartDashboard.putData(bassArms);
//    	SmartDashboard.putData(pneumatics);
    	
    	//Instantiate operator interface
		oi = new OI();
		
		//Populate Auto Callback
//		SmartDashboard.putString("autoCallback", "Waiting For Auto");
		
        //Mark Robot Init Event if logging
        if(RobotMap.logFile != null) RobotMap.logFile.writeEventFRC("Robot Init",RobotMap.timer.getFPGATimestamp());
    }
    
	/**
	 * Run each time disabled
	 */
	public void disabledPeriodic() {
		Scheduler.getInstance().run();

        //Instantiate the command used for the autonomous period
    	//autoCmdSelection = (int) SmartDashboard.getNumber("autoIndex",9999);
    	autoCmdSelection = 0;
    	switch (autoCmdSelection) {
    		case 0: autonomousCommand = new G_ThreeToteAutoTurn(); break;
    		case 1: autonomousCommand = new G_BinAuto(); break;
    		case 2: autonomousCommand = new G_ThreeToteAuto(); break;
    		case 3: autonomousCommand = new G_ThreeToteAutoNoBins(); break;
    		case 4: autonomousCommand = new G_OneToteAuto(); break;
    		case 5: autonomousCommand = new G_OneBinAuto(); break;
    		case 6: autonomousCommand = new G_OneBinAutoDeploy(); break;
    		case 8: autonomousCommand = new G_ThreeToteAutoVision(); break;
    		case 9: autonomousCommand = new G_ThreeToteAutoTime(); break;
    		default: autonomousCommand = null;
    	}
    	//Echo Back selected mode
    	if (autonomousCommand != null) SmartDashboard.putString("autoCallback", autonomousCommand.getName());
    	else SmartDashboard.putString("autoCallback", "No Auto Command");
		
		
		//Display Diagnostic data to the smart dash
    	
		SmartDashboard.putNumber("Ultrasonic Range", chassis.getWallDistance());
		SmartDashboard.putNumber("Gyro Angle", chassis.getGyroAngle());
        SmartDashboard.putNumber("LIDAR Distance", gatherer.getTargetDistance());
        SmartDashboard.putNumber("Pressure Sensor Voltage [V]",RobotMap.pressureSensor.getAverageVoltage());

	}
	
	/**
	 * Run the first time auto runs
	 */
    public void autonomousInit() {
    	
        //Instantiate the command used for the autonomous period
    	//autoCmdSelection = (int) SmartDashboard.getNumber("autoIndex",9999);
    	//autoCmdSelection = 0;
    	switch (autoCmdSelection) {
    		case 0: autonomousCommand = new G_ThreeToteAutoTurn(); break;
    		case 1: autonomousCommand = new G_BinAuto(); break;
    		case 2: autonomousCommand = new G_ThreeToteAuto(); break;
    		case 3: autonomousCommand = new G_ThreeToteAutoNoBins(); break;
    		case 4: autonomousCommand = new G_OneToteAuto(); break;
    		case 5: autonomousCommand = new G_OneBinAuto(); break;
    		case 6: autonomousCommand = new G_OneBinAutoDeploy(); break;
    		case 8: autonomousCommand = new G_ThreeToteAutoVision(); break;
    		case 9: autonomousCommand = new G_ThreeToteAutoTime(); break;
    		default: autonomousCommand = null;
    	}
    	//Echo Back selected mode
    	if (autonomousCommand != null) SmartDashboard.putString("autoCallback", autonomousCommand.getName());
    	else SmartDashboard.putString("autoCallback", "No Auto Command");
    	
    	//Hard coded auto command
//    	autonomousCommand = new G_ThreeToteAutoTurn();
    	
    	//Reset the gyro
    	chassis.resetGyro();
    	
    	//Ensure drive motors are in brake mode
		RobotMap.frontLeftCIM.enableBrakeMode(true);
		RobotMap.rearLeftCIM.enableBrakeMode(true);
		RobotMap.frontRightCIM.enableBrakeMode(true);
		RobotMap.rearRightCIM.enableBrakeMode(true);
	    	
    	// schedule the autonomous command (example)
    	if (autonomousCommand != null) autonomousCommand.start();
    	
    	//Mark event if logging
        if(RobotMap.logFile != null) RobotMap.logFile.writeEventFRC("Entering Auto",RobotMap.timer.getFPGATimestamp());
    }

    /**
     * This function is called periodically during autonomous
     */ 
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
        
        //Call Robot, Subsystem and Command log commands
        if(RobotMap.logFile != null) {
        	Robot.log();
        	chassis.log();
        	stacker.log();
        	gatherer.log();
        	headingPID.log();
        }
    }

    /**
     * Runs the first time teleop is entered
     */
    public void teleopInit() {
		// Kill the auto command
        if (autonomousCommand != null) autonomousCommand.cancel();
        
        //Mark Event if logging data
        if(RobotMap.logFile != null) RobotMap.logFile.writeEventFRC("Entering Teleop",RobotMap.timer.getFPGATimestamp());
        
        //Disable the Gyro
        RobotMap.isGyroEnabled = false;
        //TODO: remove
//        timer.start();

    	//Ensure drive motors are coasting
		RobotMap.frontLeftCIM.enableBrakeMode(false);
		RobotMap.rearLeftCIM.enableBrakeMode(false);
		RobotMap.frontRightCIM.enableBrakeMode(false);
		RobotMap.rearRightCIM.enableBrakeMode(false);
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        //TODO: remove
//        if(timer.get() > 0.5 && timer.get() <= 0.6) stacker.setSetpoint(Constants.STACKER_HIGH);
//        if(timer.get() > 2.5 && timer.get() <= 2.7) stacker.setSetpoint(Constants.STACKER_LOW);
        
        //Populate the Smart Dashboard with System Diagnostic Data
//        SmartDashboard.putNumber("Total Current (A)", pdp.getTotalCurrent());
        SmartDashboard.putBoolean("Tote Detector", Robot.stacker.hasTote());
//        SmartDashboard.putNumber("LIDAR Distance", gatherer.getTargetDistance());
//        System.out.println("LIDAR Distance" + gatherer.getTargetDistance());
        SmartDashboard.putNumber("Pressure",pneumatics.getPressure());
        
        //Call Robot, Subsystem and Command log commands
        if(RobotMap.logFile != null) {
        	Robot.log();
        	chassis.log();
        	stacker.log();
        	gatherer.log();
        	headingPID.log();
        }
    }
  
    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit(){
    	//Keep flush() command in place until we find a reliable way to close
    	//TODO: Maybe just flush when disabled?
    	if(RobotMap.logFile != null) {
    		try {
    			RobotMap.logFile.flush();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
    	}
    	//    		Constants.expo = SmartDashboard.getDouble("exp", Constants.expo);
    	//    		Constants.Kp = SmartDashboard.getDouble("simpleKp", Constants.Kp);
    	//    		Constants.headingKp = SmartDashboard.getDouble("Kp", Constants.headingKp);
    	//    		Constants.headingKd = SmartDashboard.getDouble("Kd", Constants.headingKd);
    	//    		Constants.headingKi = SmartDashboard.getDouble("Ki", Constants.headingKi);
    }     

    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
    
    /**
     * Log data about core robot
     */
    private static void log() {
    	RobotMap.logFile.writeFRC("Time [s]", RobotMap.timer.getFPGATimestamp());
    	RobotMap.logFile.writeFRC("Total Current [A]", pdp.getTotalCurrent());
    	RobotMap.logFile.writeFRC("Bus Voltage [V]", pdp.getVoltage());
    	RobotMap.logFile.writeFRC("Total Energy [J]", pdp.getTotalEnergy());
    	RobotMap.logFile.writeFRC("Accel x [g]",RobotMap.accel.getX());
    	RobotMap.logFile.writeFRC("Accel y [g]",RobotMap.accel.getY());
    	RobotMap.logFile.writeFRC("Accel z [g]",RobotMap.accel.getZ());
    	RobotMap.logFile.writeFRC("Pressure [psi]",pneumatics.getPressure());
    }
}
