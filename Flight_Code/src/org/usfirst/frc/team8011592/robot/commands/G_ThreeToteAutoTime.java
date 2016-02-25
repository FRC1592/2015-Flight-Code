package org.usfirst.frc.team8011592.robot.commands;

import org.usfirst.frc.team8011592.robot.Constants;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.PrintCommand;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class G_ThreeToteAutoTime extends CommandGroup {
    
	private double heading;
    public  G_ThreeToteAutoTime() {
    	//First Tote
//    	addSequential(new PrintCommand("Starting First Tote"));
    	heading = 10;
    	addSequential(new AutoGather());
    	addParallel(new SetStackHeight(Constants.STACKER_HIGH));
    	//Second Tote
    	
    	addSequential(new DriveToBin(28.0,0),2.5); //TODO 7deg on flight robot
    	heading = 12.5;
    	addSequential(new DriveToTote(31.0,heading,0.3),2.5);
    	addSequential(new AutoGather());
    	addParallel(new G_StoreTote());

    	//Third Tote
    	heading = 10;
    	addSequential(new DriveToBin(28,heading),2.5);
    	
    	heading = 12.5;
    	
    	addSequential(new DriveToTote(40.0,heading,0.3),2.5);

    	addSequential(new AutoGather());
    	addParallel(new SetStackHeight(Constants.STACKER_LOW));
    	//Strafe into scoring zone and deploy totes
//    	heading = 90;
//    	addSequential(new TurnToHeading(heading),0.5);
    	addSequential(new DriveHoldingHeading(0.0,1.0,heading),3.0); //+goes right
    	addSequential(new ReverseGatherer(-0.6),3.5);
    	addSequential(new OpenArms());

    }
}
