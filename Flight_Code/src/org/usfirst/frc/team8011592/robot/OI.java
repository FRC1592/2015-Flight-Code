/*
* $Revision: 419 $
* $Date: 2015-04-07 21:52:14 -0400 (Tue, 07 Apr 2015) $
* $HeadURL: https://ks-kdc-firs1001.ndc.nasa.gov/svn/robotics/2015_Season/Competition_Software/trunk/Flight_Code/src/org/usfirst/frc/team8011592/robot/OI.java $
* $Author: dedyer1 $
*/

package org.usfirst.frc.team8011592.robot;




import org.usfirst.frc.team8011592.robot.commands.*;
import org.usfirst.frc.team8011592.robot.triggers.*;

//import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.Trigger;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	//Declare Gamepads
	public Joystick driver = new Joystick(0);
	public Joystick manipulator = new Joystick(1);;
	
	//Declare Driver Gamepad Buttons
	public Button toggleGyro = new JoystickButton(driver,9);
	public Button turnTo270 = new JoystickButton(driver,1);
	public Button turnTo180 = new JoystickButton(driver,2);
	public Button turnto90 = new JoystickButton(driver,3);
	public Button turnTo0 = new JoystickButton(driver,4);
	public Button gyroReset = new JoystickButton(driver,10);
	public Button gather = new JoystickButton(driver,6);
	public Button openArms = new JoystickButton(driver,7);
	public Button driveFast = new JoystickButton(driver,5);
	public Button deploy = new JoystickButton(driver,8);
	
	//Declare Manipulator Gamepad Buttons
	public Button raiseStack = new JoystickButton(manipulator, 4);
	public Button lowerStack = new JoystickButton(manipulator, 2);
	public Button deployBASS = new JoystickButton(manipulator,7);
	public Button gatherStack = new JoystickButton(manipulator, 6);
	public Button raiseFingers = new JoystickButton(manipulator, 5);
	public Button secureStack = new JoystickButton(manipulator,1);
	public Button binStack = new JoystickButton(manipulator,3);
	public Button manipDeploy = new JoystickButton(manipulator,8);
	public Button disableStacker = new JoystickButton(manipulator,10);
	public Button turnTest = new JoystickButton(manipulator,9);

//	public Button toggleStabilizers = new JoystickButton(manipulator,5);

	//Declare Triggers
	public Trigger toteDetected = new ToteDetected();
	public Trigger stackerStalled = new MotorStalled(RobotMap.stackerCIM,Constants.STACKER_STALL_CURRENT,Constants.STACKER_STALL_TIME);

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	public OI() {
		//Create Driver Button Triggers
//		toggleFieldOriented.whenPressed(new ToggleFieldOriented());
		toggleGyro.whenPressed(new ToggleGyro());
		turnTo270.whenPressed(new SetHeading(270.0));
		turnTo180.whenPressed(new SetHeading(180.0));
		turnto90.whenPressed(new SetHeading(90.0));
		turnTo0.whenPressed(new SetHeading(0.0));
		gyroReset.whenPressed(new ResetGyro());
		gather.whileHeld(new Gather(false));
		driveFast.whileHeld(new DriveFast());
		deploy.whileHeld(new ReverseGatherer());
		openArms.toggleWhenPressed(new OpenArms());
		
		//Create Manipulator Button Triggers
		lowerStack.whenPressed(new SetStackHeight(Constants.STACKER_LOW));
		raiseStack.whenPressed(new G_SnugNRaise());
		raiseFingers.whileHeld(new RaiseFingersBASS());
		secureStack.whenPressed(new SetStackHeight(Constants.STACKER_TOTE_SECURE));
		binStack.whenPressed(new SetStackHeight(Constants.STACKER_BIN_GATHER));
		deployBASS.toggleWhenPressed(new ExtendArmsBASS());
		gatherStack.whileHeld(new Gather(true));
		manipDeploy.whileHeld(new G_DeployTote());
		disableStacker.toggleWhenPressed(new DisableStacker());
		
		//Create Trigger Triggers
		toteDetected.whenActive(new G_StoreTote());
		stackerStalled.whileActive(new StopStacker());
		
	}

	public Joystick getDriverGamepad() {
		return driver;
	}
	
	public Joystick getManipulatorGamepad() {
		return manipulator;
	}

}
