package org.usfirst.frc.team8011592.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class G_OneToteAuto extends CommandGroup {
    private double heading;
    public  G_OneToteAuto() {
//    	addSequential(new CloseArms());
    	heading = 10;
    	addSequential(new AutoGather());
    	addSequential(new DriveHoldingHeading(0.0,1.0,heading),2.0); //+goes right
    	addSequential(new ReverseGatherer(),3.0);
    	addSequential(new OpenArms());
    	
    	
    }
}
