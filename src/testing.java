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

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	Joystick psController;
	Talon kFrontLeft, kRearLeft, kFrontRight, kRearRight;
	MecanumDrive myDrive;
	
	double vertVel, horVel, rotation;
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		psController = new Joystick(0);
		kFrontLeft = new Talon(0);
		kRearLeft = new Talon(1);
		kFrontRight = new Talon(2);
		kRearRight = new Talon(3);
		myDrive = new MecanumDrive(kFrontLeft, kRearLeft, kFrontRight, kRearRight);
		
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
		while (isOperatorControl() && isEnabled()){
			vertVel = psController.getRawAxis(1);
			horVel = psController.getRawAxis(0);
			rotation = psController.getRawAxis(4);
			myDrive.driveCartesian(vertVel, horVel, rotation, 0.00);
		}
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
}
