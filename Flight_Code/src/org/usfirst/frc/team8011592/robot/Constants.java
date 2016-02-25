/*
* $Revision: 437 $
* $Date: 2015-04-30 13:28:46 -0400 (Thu, 30 Apr 2015) $
* $HeadURL: https://ks-kdc-firs1001.ndc.nasa.gov/svn/robotics/2015_Season/Competition_Software/trunk/Flight_Code/src/org/usfirst/frc/team8011592/robot/Constants.java $
* $Author: dedyer1 $
*/

package org.usfirst.frc.team8011592.robot;

/**
 * This file contains global constants used by the robot
 * @author Dustin
 *
 */

public class Constants {
	//Gatherer
	public static final double GATHERER_BED_SCALE = 0.7;		// Reduction on gatherer bed speed relative to intake wheels
	//TODO: *There is a hard-coded scale when using manipulator joystick*
	//Chassis Rotation Controller
	public static final double HEADING_KP = 0.02; 				// [1/deg] HEADING_KP = 0.040;
	public static final double HEADING_KI = 0.001;     			// [1/deg/Ts] HEADING_KI = 0.0001;
	public static final double HEADING_KD = 0.10;        		// [Ts/deg] HEADING_KD = 0.1;
	//Chassis Rotation Limits and deadband
	public static final double ROTATE_CMD_LIMIT = 0.7;			// [] Max rotation cmd sent to chassis
	public static final double ZAXIS_DB = 0.05;        			// [] Deadband for z-axis joystick and heading controller
	public static final double HEADING_OFFSET = 0.0;    		// [deg] Adjust to compensate for robot not initialized in field oriented configuration
	//Chassis Driver Scaling
	public static final double EXPO = 2.0; 						// [] Rotation stick exponential ramp
	public static final double FWD_REV_SCALE = 0.6;  			// [] Scale the drive forward reverse speed
	public static final double STRAFE_SCALE = 0.8;  			// [] Scale the drive strafe speed
	public static final double TURN_SCALE = 0.9;  				// [] Scale the drive turn speed
	//Stacker Pot
	public static final double POT_SCALE = 11;	 				// [in/V] TODO: 1=11, 2=10.77, P=-6.435
	public static final double POT_OFFSET = 2.03;				// [V] TODO: 1=2.03, 2=2.065 P=3.016, 801:2.410
	//Stacker Setpoints
	public static final double STACKER_LOW = 2.5;				// [in]
	public static final double STACKER_STACK = 4.0;				// [in]
	public static final double STACKER_HIGH = 20.5;				// [in] TODO: 20.5 on 1592 flight
	public static final double STACKER_BIN_GATHER = 13.0;		// [in]
	public static final double STACKER_TOTE_SECURE = 7.0;		// [in]
	//Stacker limits
	public static final double STACKER_MIN = 0.5;				// [in]
	public static final double STACKER_MAX = 23;				// [in]
	public static final double STACKER_DOWN_LIM = -0.75; 		// []
	//Stabilizers
	public static final double STABIL_DELAY = 0.04;				// [s] delay after commanding stabilizers open when lowering stack
	public static final double LOWER_ZONE = 2;					// [in] distance to hold stabilizer open when setting stack on tote
	//Stacker Controller
	public static final double STACKER_T = 0.05;   				// [s] period
	public static final double STACKER_KP = 0.25;				// [1/in]  0.3, 0.2
	public static final double STACKER_KI = 0.3 * STACKER_T;	// [1/in/Ts] 0.8
	public static final double STACKER_KD = 0.0 / STACKER_T;	// [Ts/in]
	public static final double STACKER_TOL = 0.5;				// [in]
	//Stacker Stall protection
	public static final int STACKER_STALL_CURRENT = 70; 		// [A]
	public static final int STACKER_STALL_TIME = 200; 			// [ms]
	//Tote Detector filter
	public static final int OPTICAL_THRESH = 3;					// [counts]
	public static final int TOTE_COUNTS_FOR_DETECTION = 3;
	//Stabilizer
	public static final double STABILIZER_ZONE = 2.5;			// [in] window about stacker setpoint to clamp stack
	//Data Logger
	public static final boolean LOGGING_ENABLE = true;
	//Ultrasonic Sensor
	public static final double MAXULTRA_KP = 0.1;				// [1/in] strafe gain for wall following
	
}
