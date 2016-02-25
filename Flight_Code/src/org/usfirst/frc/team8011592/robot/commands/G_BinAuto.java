package org.usfirst.frc.team8011592.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class G_BinAuto extends CommandGroup {
    
    public  G_BinAuto() {
    	//Extent arms to collect bins and leave out for time
    	addParallel(new G_CollectBin(),6.0);
    	//Wait to ensure bins are collected
    	addSequential(new WaitCommand(2.0));
    	//Pull Bins into auto zone
		addSequential(new DriveHoldingHeading(0.5,0.0,0),1.25);
    }
}
