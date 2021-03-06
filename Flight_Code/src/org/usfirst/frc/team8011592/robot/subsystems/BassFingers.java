package org.usfirst.frc.team8011592.robot.subsystems;

import org.usfirst.frc.team8011592.robot.RobotMap;
import org.usfirst.frc.team8011592.robot.commands.LowerFingersBASS;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class BassFingers extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private Solenoid topPiston;
	
    public BassFingers() {
    	topPiston = RobotMap.topBassPiston;
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new LowerFingersBASS());
    }
    
    public void extendBASSTopPiston(){
    	topPiston.set(false);
    }
    public void retractBASSTopPiston(){
    	topPiston.set(true);
    }
}

