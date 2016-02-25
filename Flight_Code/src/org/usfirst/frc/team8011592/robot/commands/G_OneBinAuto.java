package org.usfirst.frc.team8011592.robot.commands;

import org.usfirst.frc.team8011592.robot.Constants;

import edu.wpi.first.wpilibj.command.CommandGroup;
// This command grabs one bin and strafes left towards the autozone. It will not deploy the bin and so the driver
// will have a bin in the robot at the start of the tele-op period.
// Use if you want to grab bin in robot in the autonomous period.
/**
 *
 */
public class G_OneBinAuto extends CommandGroup {
    private double heading;
    public  G_OneBinAuto() {
//    	addSequential(new CloseArms());
    	heading = 335;
    	addSequential(new SetStackHeight(Constants.STACKER_HIGH));
    	addSequential(new AutoGather(),2.0);
    	addSequential(new DriveHoldingHeading(0.1,-1.0,heading),2.3); //+goes right
    	
    	
    }
}
