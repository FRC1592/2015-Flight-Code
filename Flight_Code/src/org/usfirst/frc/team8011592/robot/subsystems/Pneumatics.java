package org.usfirst.frc.team8011592.robot.subsystems;

import org.usfirst.frc.team8011592.robot.RobotMap;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The Pneumatics subsystem contains the compressor and a pressure sensor.
 * 
 * NOTE: The simulator currently doesn't support the compressor or pressure sensors.
 */
public class Pneumatics extends Subsystem {
	AnalogInput pressureSensor;
	Compressor compressor;

	private static final double SLOPE = 110/(4.926-0.47);			//[psi/V]
	private static final double BIAS = 0.47;			//[V]
	private static final double MAX_PRESSURE = 110;		//[psi]
	
	public Pneumatics() {
		pressureSensor = RobotMap.pressureSensor;
//		compressor = new Compressor();

		LiveWindow.addSensor("Pneumatics", "Pressure Sensor", pressureSensor);
	}

	/**
	 * No default command
	 */
	public void initDefaultCommand() {}

	/**
	 * Start the compressor going. The compressor automatically starts and stops as it goes above and below maximum pressure.
	 */
//	public void start() {
//		compressor.start();
//	}

	/**
	 * @return Whether or not the system is fully pressurized.
	 */
	public boolean isPressurized() {
		return MAX_PRESSURE <= pressureSensor.getVoltage();
	}
	
	/**
	 * Gets pressure from sensor in psi
	 */
	public double getPressure() {
		return (pressureSensor.getAverageVoltage() - BIAS) * SLOPE;
//		return pressureSensor.getAverageVoltage();
	}

	/**
	 * Puts the pressure on the SmartDashboard.
	 */
	public void writePressure() {
		SmartDashboard.putNumber("Pressure", pressureSensor.getVoltage());
	}
}
