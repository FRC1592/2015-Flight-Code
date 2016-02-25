/**
* $Revision: 421 $
* $Date: 2015-04-09 08:54:45 -0400 (Thu, 09 Apr 2015) $
* $HeadURL: https://ks-kdc-firs1001.ndc.nasa.gov/svn/robotics/2015_Season/Competition_Software/trunk/Flight_Code/src/org/usfirst/frc/team8011592/robot/subsystems/Gatherer.java $
* $Author: dedyer1 $
*/

package org.usfirst.frc.team8011592.robot.subsystems;

import org.usfirst.frc.team8011592.robot.Constants;
import org.usfirst.frc.team8011592.robot.RobotMap;
import org.usfirst.frc.team8011592.robot.commands.GatherWithJoystick;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Gatherer extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private CANTalon leftGatherMotor;
	private CANTalon rightGatherMotor;
	private CANTalon lowerGatherMotor;
	private LIDAR lidar;
	
	public Gatherer() {
		leftGatherMotor = RobotMap.leftGatherBag;
		rightGatherMotor = RobotMap.rightGatherBag;
		lowerGatherMotor = RobotMap.bottomGatherBag;
		lidar = RobotMap.lidar;
	}

    public void initDefaultCommand() {
    	setDefaultCommand(new GatherWithJoystick());
    }
    
    /**
     * Run the gatherer
     * @param speed: (+) intakes
     */
	public void setSpeed(double speed) {
		if (speed > 0)	lowerGatherMotor.set(speed * Constants.GATHERER_BED_SCALE);
		else lowerGatherMotor.set(speed);					//+ intakes
		leftGatherMotor.set(-speed);						//- intakes
		rightGatherMotor.set(speed); 						//+ intakes
	}
	
	/**
	 * Sets arms to rotate opposing directions to sweep bins away
	 * @param speed: +sets right arm to intake and left to spit-out
	 */
	public void setSpeedOpposing(double speed) {
		lowerGatherMotor.set(0.0);
		leftGatherMotor.set(speed);							//- intakes
		rightGatherMotor.set(speed); 						//+ intakes
	}	
	
	/**
	 * Run the gatherer from a joystick throttle
	 * @param manip: joystick to read the throttle from
	 */
	public void takeJoystickInput(Joystick manip) {
		double speed = manip.getThrottle();
		if (speed > 0)	lowerGatherMotor.set(speed * 0.9);
		else lowerGatherMotor.set(speed); 					//+ intakes
		leftGatherMotor.set(-speed);						//- intakes
		rightGatherMotor.set(speed); 						//+ intakes
	}
	
	/**
	 * Stop the gatherer
	 */
	public void stop() {
		leftGatherMotor.set(0.0);
		rightGatherMotor.set(0.0);
		lowerGatherMotor.set(0.0);
	}
	
	/**
	 * Get the distance to a target in front of roboot
	 * @return distance to target in inches
	 */
	public double getTargetDistance() {
		return lidar.getDistance() * 0.3937 - 2; //convert cm to in and subtract the mounting bias
	}
	
	public void log() {
		//Motor Voltages
		RobotMap.logFile.writeFRC("Left Arm Motor Voltage [V]", leftGatherMotor.getOutputVoltage());
		RobotMap.logFile.writeFRC("Right Arm Motor Voltage [V]", rightGatherMotor.getOutputVoltage());
		RobotMap.logFile.writeFRC("Roller Arm Motor Voltage [V]", lowerGatherMotor.getOutputVoltage());
		//Motor Currents		
		RobotMap.logFile.writeFRC("Left Arm Motor Current [A]", leftGatherMotor.getOutputCurrent());
		RobotMap.logFile.writeFRC("Right Arm Motor Current [A]", rightGatherMotor.getOutputCurrent());
		RobotMap.logFile.writeFRC("Roller Bed Motor Current [A]", lowerGatherMotor.getOutputCurrent());
		//LIDAR
		RobotMap.logFile.writeFRC("LIDAR Distance [in]", getTargetDistance());
	}
	
}

