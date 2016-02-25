package org.usfirst.frc.team8011592.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Store tote
 */

public class G_DeployTote extends CommandGroup {
	
	
	public G_DeployTote() {
		addSequential(new LowerStack());
		addSequential(new ReverseGatherer());
	}
	public G_DeployTote(double speed) {
		addSequential(new LowerStack());
		addSequential(new ReverseGatherer(speed));
	}
	
}
