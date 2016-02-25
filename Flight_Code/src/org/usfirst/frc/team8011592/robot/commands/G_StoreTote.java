package org.usfirst.frc.team8011592.robot.commands;

import org.usfirst.frc.team8011592.robot.Constants;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Store tote
 */
public class G_StoreTote extends CommandGroup {

	public G_StoreTote() {
		addSequential(new LowerStack(Constants.STACKER_STACK));
		addSequential(new RaiseStack());
	}
	
}
