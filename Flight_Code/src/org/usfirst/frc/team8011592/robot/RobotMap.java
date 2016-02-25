/*
* $Revision: 432 $
* $Date: 2015-04-14 15:13:08 -0400 (Tue, 14 Apr 2015) $
* $HeadURL: https://ks-kdc-firs1001.ndc.nasa.gov/svn/robotics/2015_Season/Competition_Software/trunk/Flight_Code/src/org/usfirst/frc/team8011592/robot/RobotMap.java $
* $Author: dedyer1 $
*/

package org.usfirst.frc.team8011592.robot;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */

import java.io.File;



import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.usfirst.frc.team8011592.robot.library.AnalogGyro;
import org.usfirst.frc.team8011592.robot.library.BufferedWriterFRC;
import org.usfirst.frc.team8011592.robot.subsystems.LIDAR;
import org.usfirst.frc.team8011592.robot.subsystems.WallRangeFinder;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.internal.HardwareTimer;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.livewindow.LiveWindowSendable;

public class RobotMap {
    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.
    // public static int leftMotor = 1;
    // public static int rightMotor = 2;
    
    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static int rangefinderPort = 1;
    // public static int rangefinderModule = 1;
	
	//Chassis
	public static CANTalon frontLeftCIM, rearLeftCIM, rearRightCIM, frontRightCIM;
	public static RobotDrive driveBase;
	public static DigitalInput testLimitSwitch;
	public static AnalogGyro gyro;
	public static LIDAR lidar;
	public static double headingCMD;
	public static double headingError;
	public static double headingOUT;
	public static double zRate;
	public static boolean isFieldOriented;
	public static boolean isGyroEnabled;
	public static boolean isDriveFastEnabled;
	public static PIDController headingPID;
	//Ultrasonic
	public static WallRangeFinder ultra;
	//System
	public static PowerDistributionPanel pdp;
	public static BuiltInAccelerometer accel;
	public static AnalogInput pressureSensor;
	//Gatherer
	public static CANTalon leftGatherBag, rightGatherBag, bottomGatherBag;
	public static DigitalInput opticalBack;
//	public static DigitalInput opticalArms, opticalTop;
	public static boolean autoStack;
	//BASS
	public static Solenoid rightBassPiston;
	public static Solenoid leftBassPiston;
	public static Solenoid topBassPiston;
	//Stacker
	public static CANTalon stackerCIM;
	public static AnalogInput stackerPot;
	public static double stackerInitSetPoint;
	//Arms
	public static Solenoid armUpDownPiston;
	public static Solenoid armInOutPiston;
	//Stabilizers
	public static Solenoid stabilizers;
	//Data Logger
	public static BufferedWriterFRC logFile;
	public static HardwareTimer timer;
	public static final File logPath = new File("/u/logs/");

	
	/**
	 * Initialize robot actuators and sensors
	 */
	public static void init() {
		//Chassis
		frontLeftCIM = new CANTalon(1);
		rearLeftCIM = new CANTalon(2);
		frontRightCIM = new CANTalon(4); 
		rearRightCIM = new CANTalon(3);
		frontLeftCIM.enableBrakeMode(true);
		rearLeftCIM.enableBrakeMode(true);
		frontRightCIM.enableBrakeMode(true);
		rearRightCIM.enableBrakeMode(true);
		driveBase = new RobotDrive(frontLeftCIM, rearLeftCIM,frontRightCIM, rearRightCIM);
		driveBase.setInvertedMotor(RobotDrive.MotorType.kRearRight, true);
		driveBase.setInvertedMotor(RobotDrive.MotorType.kFrontRight, true);
//		LiveWindow.addActuator("DriveTrain", "Front Left CIM", (LiveWindowSendable) frontLeftCIM);
//		LiveWindow.addActuator("DriveTrain", "Front Right CIM", (LiveWindowSendable) frontRightCIM);
//		LiveWindow.addActuator("DriveTrain", "Back Left CIM", (LiveWindowSendable) rearLeftCIM);
//		LiveWindow.addActuator("DriveTrain", "Back Right CIM", (LiveWindowSendable) rearRightCIM);
		pdp = new PowerDistributionPanel();
		gyro = new AnalogGyro(0);
		lidar = new LIDAR();
		gyro.setDeadband(0.01);
		isGyroEnabled = true;
		isFieldOriented = false;
		isDriveFastEnabled = false;
		headingCMD = 0.0;
		zRate = 0.0;
		
		//ultrasonic
		ultra = new WallRangeFinder(3);
		
		//Gatherer
		bottomGatherBag = new CANTalon(5);
		leftGatherBag = new CANTalon(6);
		rightGatherBag = new CANTalon(7);
		autoStack = false;

//		opticalArms = new DigitalInput(0);
		opticalBack = new DigitalInput(1);
//		opticalTop = new DigitalInput(2);
		
		//Arms
		armInOutPiston = new Solenoid(0,0);
		//	armUpDownPiston = new Solenoid(0,1);
		
		// Stabilizers
		stabilizers = new Solenoid(0,1);
		//BASS
		rightBassPiston = new Solenoid(0,3);
		leftBassPiston = new Solenoid(0,2);
		topBassPiston = new Solenoid(0,4);
		
		//Stacker
		stackerPot = new AnalogInput(2);
		stackerCIM = new CANTalon(8);
//		stackerInitSetPoint = Constants.STACKER_LOW;
		
		//Test
		testLimitSwitch = new DigitalInput(9);
		
		//System Objects
		pdp = new PowerDistributionPanel();
		accel = new BuiltInAccelerometer();
		pressureSensor = new AnalogInput(1);
				
		//Data Logger
		if(Constants.LOGGING_ENABLE) {
			timer = new HardwareTimer();
			if(logPath.isDirectory()) {
				System.out.println("I think a USB drive is mounted as U");		
//				System.out.println("Size " + logPath.getFreeSpace());
				logFile = createLogFile(logPath);
			} else {
				System.out.println("No USB Drive mounted");
				logFile = null;
			}
		} //end Data Logger

	} //end init()
	
	/**
	 * createLogFile creates a log file with a date and time stamp for
	 * logging control system data
	 * 
	 */
	private static BufferedWriterFRC createLogFile(File path) {
//		BufferedWriterFRC w = null;
		LocalDateTime dateTime = LocalDateTime.now();
		File outFile = new File(path.toString() + "/log_" + dateTime.format(DateTimeFormatter.ofPattern("uu_MM_dd_HH_mm_ss")) + ".txt");
		try {
			BufferedWriterFRC w = new BufferedWriterFRC(new OutputStreamWriter(new FileOutputStream(outFile)));
			System.out.println("Log created at " + path.getName() + "/" + outFile.getName());
			return w;
		} catch (FileNotFoundException e) {
			System.out.println("WARNING: No drive available. If drive is mounted, try restarting the roboRIO");	
			return null;
			//e.printStackTrace();
		}
		//return w;
	} //end createLogFile
	
}
