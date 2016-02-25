package org.usfirst.frc.team8011592.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class G_CollectBin extends CommandGroup {

	public G_CollectBin() {
		addParallel(new RaiseFingersBASS(),1.0);
		addSequential(new ExtendArmsBASS());
	}
}
