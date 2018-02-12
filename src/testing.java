/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4286.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
//import com.ctre.phoenix.sensors.PigeonIMU.PigeonState;
/*import com.ctre.phoenix.sensors.PigeonIMU.GeneralStatus;*/

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Gamepad extends Joystick {
	public Gamepad(int port) {
		super(port);
		// TODO Auto-generated constructor stub
	}
	private static final int AXIS_LEFT_X = 1;
	private static final int AXIS_LEFT_Y = 2;
	private static final int AXIS_RIGHT_X = 4;
}

public class Robot extends IterativeRobot {
	private Joystick m_stick = new Joystick(0);
	
	private MecanumDrive m_robotDrive;
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		Talon frontLeft = new Talon(1);
		Talon backLeft = new Talon(2);
		Talon frontRight = new Talon(3);
		Talon backRight = new Talon(4);
		
		m_robotDrive = new MecanumDrive(frontLeft, backLeft, frontRight, backRight);
	}

	/**
	 * This function is run once each time the robot enters autonomous mode.
	 */
	@Override
	public void autonomousInit() {
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
	}

	/**
	 * This function is called once each time the robot enters teleoperated mode.
	 */
	@Override
	public void teleopInit() {
	}

	/**
	 * This function is called periodically during teleoperated mode.
	 */
	@Override
	public void teleopPeriodic() {
		m_robotDrive.driveCartesian(AXIS_LEFT_X, AXIS_LEFT_Y, AXIS_RIGHT_X, 0.0);
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
}
