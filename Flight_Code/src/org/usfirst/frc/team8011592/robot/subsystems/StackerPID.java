package org.usfirst.frc.team8011592.robot.subsystems;

import org.usfirst.frc.team8011592.robot.Constants;
import org.usfirst.frc.team8011592.robot.RobotMap;
import org.usfirst.frc.team8011592.robot.commands.StabilizeStack;
import org.usfirst.frc.team8011592.robot.library.PIDSubsystem1592;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class StackerPID extends PIDSubsystem1592 {
	
	private AnalogInput pot;
	private CANTalon motor;
	private double height;
	private DigitalInput opticalBack;
	private Solenoid stabilizer;
	int m_opticalCount = 0;
	boolean totePresentAtSetpointChange;
	double lastSetpoint = 0;
	boolean commandedDown,toteAboveBottomTote;

    // Initialize your subsystem here
    public StackerPID() {
    	super(Constants.STACKER_KP,Constants.STACKER_KI,Constants.STACKER_KD,Constants.STACKER_T);
    	
    	//Map actuators and sensors
    	pot = RobotMap.stackerPot;
    	motor = RobotMap.stackerCIM;
    	opticalBack = RobotMap.opticalBack;
    	stabilizer = RobotMap.stabilizers;
    	
    	//Set controller options
//        setSetpoint(RobotMap.stackerInitSetPoint); //Sets where the PID controller should move the system
    	setSetpoint(getHeight());	//Inititalize stacker to current position
        setInputRange(Constants.STACKER_MIN, Constants.STACKER_MAX);
        setAbsoluteTolerance(Constants.STACKER_TOL);
//        setOutputRange(-0.75, 1.0); //controller works on - = down, + = up
        enable(); //Enables the PID controller.
    }
    
    public void initDefaultCommand() {
        setDefaultCommand(new StabilizeStack());
    }
    
    protected double returnPIDInput() {
    	height = getHeight();    	
    	SmartDashboard.putNumber("Pot Voltage", pot.getAverageVoltage());
    	SmartDashboard.putNumber("Pot Height", height);
    	SmartDashboard.putNumber("Stacker Current [A]",motor.getOutputCurrent());
//		SmartDashboard.putBoolean("Optical Back", opticalBack.get());
    	return height;
    }
    
    public void secureStack() {
//    	if (Math.abs(getHeight()-getSetpoint()) > Constants.STABILIZER_ZONE) openStabilizers();
//    	else squeezeStabilizers();
    	if (isStacking() || isSettingStackDown()) openStabilizers();
    	else squeezeStabilizers();
    }
    
    public void squeezeStabilizers() {
    	stabilizer.set(false);
    }
    
    public void openStabilizers() {
    	stabilizer.set(true);
    }
    
    public void setToteState() {
    	totePresentAtSetpointChange = hasTote();
    }
    
    public void setLastSetpoint(double aSetpoint) {
    	lastSetpoint = aSetpoint;
    }
    
    protected void usePIDOutput(double output) {
    	//Limit down speed when setting stack
    	if (isSettingStackDown() && output < Constants.STACKER_DOWN_LIM) output = Constants.STACKER_DOWN_LIM;
    	
    	//output inverted b/c up is negative voltage to the motor
        motor.set(-output);
    }
    
    public boolean isSettingStackDown(){
    	//true condition: going down with empty chamber or the first x inches with a tote underneath
    	commandedDown = getHeight()-getSetpoint() > Constants.STABILIZER_ZONE;
    	toteAboveBottomTote = (lastSetpoint-getHeight() <= Constants.LOWER_ZONE);
    	return commandedDown && ((totePresentAtSetpointChange && toteAboveBottomTote) || !totePresentAtSetpointChange);
    }
    
    public boolean isStacking(){
    	return (getSetpoint()-getHeight()) > Constants.STABILIZER_ZONE;
    }
    
    public boolean hasTote() {
    	//Count number of high pulses to filter out spurious triggers
		if(opticalBack.get()) m_opticalCount += 1;
		else m_opticalCount = 0;
		return m_opticalCount >= Constants.OPTICAL_THRESH;
	}
    
	public double getHeight() {
		return (pot.getAverageVoltage() - Constants.POT_OFFSET) * Constants.POT_SCALE; //[in]
	}
    
    public void log() {
		RobotMap.logFile.writeFRC("Stacker Height [in]", getHeight());    	
		RobotMap.logFile.writeFRC("Stacker Motor Voltage [V]", motor.getOutputVoltage());
		RobotMap.logFile.writeFRC("Stacker Motor Current [A]", motor.getOutputCurrent());
		RobotMap.logFile.writeFRC("Stacker Setpoint [in]", getSetpoint());
		RobotMap.logFile.writeFRC("Tote Sensor []", opticalBack.get());
		RobotMap.logFile.writeFRC("Int Error [degTs]",super.getPIDController().getIntError());
    }
}
