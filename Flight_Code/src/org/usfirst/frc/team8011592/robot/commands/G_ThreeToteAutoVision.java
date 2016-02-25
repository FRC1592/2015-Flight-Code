package org.usfirst.frc.team8011592.robot.commands;

import org.usfirst.frc.team8011592.robot.Constants;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.PrintCommand;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class G_ThreeToteAutoVision extends CommandGroup {
	
	private double heading;
	private double wallDist;
	private double speed;
	private double stopDistance;
    
    public  G_ThreeToteAutoVision() {
    	
    	heading = 10.0;
    	wallDist = 35.0;
    	speed = 0.3;
    	stopDistance = 35.0;
    	
    	//First Tote
//    	addSequential(new PrintCommand("Starting First Tote"));

    	addSequential(new AutoGather());
    	
    	addParallel(new SetStackHeight(Constants.STACKER_HIGH));
    	
//    	addSequential(new WaitCommand(2.0));
    	
    	//Second Tote
//    	addSequential(new PrintCommand("Starting Second Tote"));
    	
    	addSequential(new DriveToBin(28.0,heading),2.5); //TODO 7deg on flight robot
//    	addSequential(new DriveToBinUltrasonic(28.0,wallDist,heading,0.25),2.5); //TODO 7deg on flight robot
    	
//    	addSequential(new DriveToToteTime(1.0,2.0),2.5); //sequence on time
//    	addSequential(new DriveToTote(31.0,heading,0.3),2.5);
//    	addSequential(new DriveToToteUltrasonic(stopDistance,heading,wallDist,speed),2.5);
    	addSequential(new VisionAutoGrab(0),3);
    	
    	addSequential(new AutoGather());
    	
    	addParallel(new G_StoreTote());

    	//Third Tote
//    	addSequential(new PrintCommand("Starting Third Tote"));
    	heading = 12.5;
    	addSequential(new DriveToBin(28.0,heading),2.5);
//    	addSequential(new DriveToBinUltrasonic(28.0,wallDist,heading,0.25),2.5); //TODO 7deg on flight robot
    	
//    	addParallel(new SetStackHeight(Constants.STACKER_HIGH));
    	
//    	addSequential(new DriveToTote(31.0,heading,0.3),2.5); //31 works for a speed of .3
//    	addSequential(new DriveToToteTime(1.0,0.0),2.5); //sequence on time
    	heading = 15.0;
    	stopDistance = 40;
    	addSequential(new DriveToToteUltrasonic(stopDistance,heading,wallDist,speed),2.5);
    	
    	addSequential(new AutoGather());
    	addParallel(new SetStackHeight(Constants.STACKER_LOW));
    	//Strafe into scoring zone and deploy totes
    	addSequential(new DriveHoldingHeading(0.0,1.0,heading),2.5); //+goes right
    	addSequential(new ReverseGatherer(-0.6),3.5);
    	addSequential(new OpenArms());
    }
}
