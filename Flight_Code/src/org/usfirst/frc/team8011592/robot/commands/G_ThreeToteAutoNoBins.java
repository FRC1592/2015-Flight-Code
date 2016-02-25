package org.usfirst.frc.team8011592.robot.commands;

import org.usfirst.frc.team8011592.robot.Constants;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.PrintCommand;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class G_ThreeToteAutoNoBins extends CommandGroup {
    
    public  G_ThreeToteAutoNoBins() {
    	//First Tote
    	addSequential(new PrintCommand("Starting First Tote"));
//    	addSequential(new CloseArms()); //Should happen by default
//    	addParallel(new DriveForward(0.2),0.1);
    	addSequential(new AutoGather());
    	addParallel(new SetStackHeight(Constants.STACKER_HIGH));
    	addSequential(new WaitCommand(2.0));
    	//Second Tote
    	addSequential(new PrintCommand("Starting Second Tote"));
//    	addSequential(new OpenArms(), 0.02);
    	addSequential(new DriveToTote(28.0,0.0,0.5),2.5); //TODO 7deg on flight robot
//    	addSequential(new CloseArms(),0.02);
    	addSequential(new AutoGather());
    	addSequential(new G_StoreTote());
    	//Third Tote
    	addSequential(new PrintCommand("Starting Third Tote"));
//    	addSequential(new OpenArms(), 0.02);
    	addSequential(new DriveToTote(28.0,0.0,0.5),2.5);
//    	addSequential(new CloseArms(),0.02);
    	addSequential(new AutoGather());
    	addParallel(new SetStackHeight(Constants.STACKER_LOW));
    	//Strafe into scoring zone and deploy totes
    	addSequential(new DriveHoldingHeading(0.0,1.0,7.0),2.0); //+goes right
    	addSequential(new ReverseGatherer(-0.6),3.5);
    	addSequential(new OpenArms());
//    	addSequential(new DriveForward(-0.4),0.5);
//    	addSequential(new WaitCommand(10));
    }
}
