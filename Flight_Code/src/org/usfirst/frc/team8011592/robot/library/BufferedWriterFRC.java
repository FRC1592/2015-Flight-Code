package org.usfirst.frc.team8011592.robot.library;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;

import edu.wpi.first.wpilibj.internal.HardwareTimer;

public class BufferedWriterFRC extends BufferedWriter {
	
//	private HardwareTimer timer;

	public BufferedWriterFRC(Writer out) {
		super(out);
//		timer = new HardwareTimer();
	}

	public void writeFRC(String name, String value) {
		try {
//			write(Long.toString(System.currentTimeMillis()));
//			write(", " + name + ", ");
			write(name + ", ");
			write(value);
			newLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void writeFRC(String name, double value) {
		try {
//			write(Long.toString(System.currentTimeMillis()));
//			write(", " + name + ", ");
			write(name + ", ");
			write(Double.toString(value));
			newLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void writeFRC(String name, boolean value) {
		try {
//			write(Long.toString(System.currentTimeMillis()));
//			write(", " + name + ", ");
			write(name + ", ");
			if (value) write("1");
			else write("0");
			newLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void writeEventFRC(String name, double time) {
		try {
			write("$" + name + ", ");
			write(Double.toString(time));
//			write(Long.toString(System.currentTimeMillis()));
			newLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

