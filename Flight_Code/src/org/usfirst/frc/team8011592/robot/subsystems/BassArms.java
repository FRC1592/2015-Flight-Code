/*
* $Revision: 419 $

* 
* $Date: 2015-04-07 21:52:14 -0400 (Tue, 07 Apr 2015) $
* $HeadURL: https://ks-kdc-firs1001.ndc.nasa.gov/svn/robotics/2015_Season/Competition_Software/trunk/Flight_Code/src/org/usfirst/frc/team8011592/robot/subsystems/BassArms.java $
* $Author: dedyer1 $
*/

package org.usfirst.frc.team8011592.robot.subsystems;

//import org.usfirst.frc.team8011592.robot.commands.extendbottom;
//import org.usfirst.frc.team8011592.robot.commands.extendtop;
//import org.usfirst.frc.team8011592.robot.commands.retractbttom;
//import org.usfirst.frc.team8011592.robot.commands.retracttop;

import org.usfirst.frc.team8011592.robot.RobotMap;
import org.usfirst.frc.team8011592.robot.commands.RetractArmsBASS;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.Button;

/**
 *
 */
	public class BassArms extends Subsystem {
        
        private Solenoid leftPiston;
        private Solenoid rightPiston;
       // Put methods for controlling this subsystem
       
    public BassArms() {
    	leftPiston = RobotMap.leftBassPiston;
    	rightPiston = RobotMap.rightBassPiston;
    }

    public void initDefaultCommand() {
        setDefaultCommand(new RetractArmsBASS());
    }
    
    public void extendBASSLowerPiston(){
    	leftPiston.set(true);
    	rightPiston.set(true);
    	//Add time to the Top Piston and the Left and Right Piston
    	//Make Left and Right Pistons Parallel
    	//Add seq to extend top
    }
    
    public void retractBASSLowerPiston(){
    	leftPiston.set(false);
    	rightPiston.set(false);
    	//seq to retract top for a period of time
    	//seq the bottom to to retract
    	//Then extend top after time
    	
    }

}
	
	