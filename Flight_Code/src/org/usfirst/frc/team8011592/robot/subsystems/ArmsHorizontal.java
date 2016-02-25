/*
* $Revision: 324 $
* $Date: 2015-02-19 00:37:25 -0500 (Thu, 19 Feb 2015) $
* $HeadURL: https://ks-kdc-firs1001.ndc.nasa.gov/svn/robotics/2015_Season/Competition_Software/trunk/Flight_Code/src/org/usfirst/frc/team8011592/robot/subsystems/Arms.java $
* $Author: dedyer1 $
*/

package org.usfirst.frc.team8011592.robot.subsystems;

import org.usfirst.frc.team8011592.robot.RobotMap;
import org.usfirst.frc.team8011592.robot.commands.CloseArms;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ArmsHorizontal extends Subsystem {
    
	private Solenoid piston;

	public ArmsHorizontal() {
		piston = RobotMap.armInOutPiston;
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new CloseArms());
    }
    
	public void open() {
		piston.set(false);
	}

	public void close() {
		piston.set(true);
	}
	
}

