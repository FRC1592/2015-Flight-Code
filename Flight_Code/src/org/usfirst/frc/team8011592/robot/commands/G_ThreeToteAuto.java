package org.usfirst.frc.team8011592.robot.commands;

import org.usfirst.frc.team8011592.robot.Constants;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.PrintCommand;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class G_ThreeToteAuto extends CommandGroup {
	
	private double heading;
	private double wallDist;
	private double speed;
	private double stopDistance;
    
    public  G_ThreeToteAuto() {
    	
    	heading = 10.0;
    	wallDist = 35.0;
    	speed = 0.35;
    	stopDistance = 40;

    	//First Tote
//    	addSequential(new PrintCommand("Starting First Tote"));
//    	addSequential(new SetStackHeight(Constants.STACKER_LOW),1.5);
    	addSequential(new AutoGather(1.0));
    	addParallel(new SetStackHeight(Constants.STACKER_HIGH));
    	
    	//Second Tote
//    	addSequential(new PrintCommand("Starting Second Tote"));
    	heading = 0;
    	addSequential(new DriveToBin(0.25,heading),1.6);
    	heading = 10;
    	addSequential(new DriveToToteUltrasonic(stopDistance,heading,wallDist,speed),2.5);
    	addSequential(new AutoGather(1.0));
    	heading = 0.0;
//    	addParallel(new DriveToBin(0.3,heading),1.6);
    	addParallel(new G_StoreTote());



    	//Third Tote
//    	addSequential(new PrintCommand("Starting Third Tote"));
    	heading = 0.0;
    	addSequential(new DriveToBin(0.25,heading),1.8);
    	heading = 10.0;
    	stopDistance = 35;
    	addSequential(new DriveToToteUltrasonic(stopDistance,heading,wallDist,speed),2.5);
    	addParallel(new DriveHoldingHeading(0.35, 0.0, heading),0.4);
    	addSequential(new AutoGather(1.0));
    	addParallel(new SetStackHeight(Constants.STACKER_LOW));
    	
    	//Strafe into scoring zone and deploy totes
    	addSequential(new DriveHoldingHeading(0.0,0.0,80.0),0.2); //+goes right
    	addSequential(new DriveHoldingHeading(0.6,0.0,80.0),1.1); //+goes right
//    	addSequential(new DriveHoldingHeading(0.0,0.0,170),0.2); //+goes right

//    	heading = 15;
//    	addSequential(new DriveHoldingHeading(0.0,1.0,heading),2.25); //+goes right
    	addSequential(new ReverseGatherer(-0.6),3.5);
//    	addSequential(new DriveToTote(41, heading, -1.0),1.0);
//    	addSequential(new OpenArms());
    }
}
