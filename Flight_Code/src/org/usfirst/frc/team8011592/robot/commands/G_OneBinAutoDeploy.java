package org.usfirst.frc.team8011592.robot.commands;

import org.usfirst.frc.team8011592.robot.Constants;

import edu.wpi.first.wpilibj.command.CommandGroup;
// This command will grab bin and strafe left in the autonomous period. It will then deploy the bin in the autozone
/**
 *
 */
public class G_OneBinAutoDeploy extends CommandGroup {
    private double heading;
    public  G_OneBinAutoDeploy() {
//    	addSequential(new CloseArms());
    	heading = 335;
    	addSequential(new SetStackHeight(Constants.STACKER_HIGH));
    	addSequential(new AutoGather(),2.0);
    	addSequential(new DriveHoldingHeading(0.0,-1.0,heading),2.0); //+goes right
    	addSequential(new ReverseGatherer(-1.0),3.0);
    	addSequential(new OpenArms());
    	
    	
    }
}
