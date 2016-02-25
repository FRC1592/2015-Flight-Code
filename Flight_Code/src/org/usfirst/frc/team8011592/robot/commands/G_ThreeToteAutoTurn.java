package org.usfirst.frc.team8011592.robot.commands;

import org.usfirst.frc.team8011592.robot.Constants;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.PrintCommand;
import edu.wpi.first.wpilibj.command.WaitCommand;
//This command grabs the three totes and deploys in the autozone. It uses the rollers to push bins out of way.
// Then uses Lidar and Ultrasonic to line up on totes. It turns 90degrees and drive forward to the autozone. It then
// turns another 90degrees and deploys the three totes.
/**
 *
 */
public class G_ThreeToteAutoTurn extends CommandGroup {
	
	private double heading;
	private double heading_bias;
	private double wallDist;
	private double speed;
	private double stopDistance;
    
    public  G_ThreeToteAutoTurn() {
    	
    	heading_bias = 0.0;
    	wallDist = 28.0;
    	speed = 0.35;
    	stopDistance = 40;

    	//First Tote
    	addParallel(new SetStackHeight(Constants.STACKER_HIGH));
    	addParallel(new G_StoreTote());
    	addSequential(new AutoGather(1.0));
    	addSequential(new WaitCommand(0.2));
    	
    	//Second Tote
    	heading = 0+heading_bias;
    	addSequential(new DriveToBin(0.25,heading),2.0);
    	heading = 10+heading_bias;
    	addSequential(new DriveToToteUltrasonic(stopDistance,heading,30,speed),2.5);
    	addSequential(new AutoGather(1.0));
    	heading = 4+heading_bias;
    	addParallel(new G_StoreTote());



    	//Third Tote
//    	addSequential(new PrintCommand("Starting Third Tote"));
//    	heading = 2.5;
    	addSequential(new DriveToBin(0.25,heading),2.0);
    	heading = 10.0+heading_bias;
    	stopDistance = 40;
//    	wallDist = 31;
    	addSequential(new DriveToToteUltrasonic(stopDistance,heading,wallDist,speed),2.5);
//    	addParallel(new DriveHoldingHeading(0.35, 0.0, heading),0.4);
    	addSequential(new AutoGather(1.0));
    	addParallel(new SetStackHeight(Constants.STACKER_LOW));
    	
    	//Turn and drive forward into scoring zone and turn againg then deploy totes
    	addSequential(new TurnToHeading(90+heading_bias),0.2); //+turns right
//    	addSequential(new DriveHoldingHeading(-0.2,0.0,100.0+heading_bias),0.4); //+drives forward
    	addSequential(new DriveHoldingHeading(0.6,0.0,90.0+heading_bias),1.80); //+drives forward
    	addSequential(new TurnToHeading(20+heading_bias),0.6); //+turns right
    	addSequential(new ReverseGatherer(-0.6),3.5);
    	addSequential(new OpenArms());
    }
}
