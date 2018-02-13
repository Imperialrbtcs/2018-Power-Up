/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4286.robot;

//import com.ctre.phoenix.sensors.PigeonIMU;

import edu.wpi.first.wpilibj.CounterBase;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
//import com.ctre.phoenix.sensors.PigeonIMU.PigeonState;
/*import com.ctre.phoenix.sensors.PigeonIMU.GeneralStatus;*/
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */

public class Robot extends IterativeRobot {
	Joystick joystick = new Joystick(0);
	Talon frontLeft, backLeft, frontRight, backRight, climbingOne, climbingTwo, lift, grabber;
	MecanumDrive m_robotDrive;
	Encoder encoder;
	final double kUpdatePeriod = 0.005;
	boolean LBump, RBump, A, B, X, Y;
	SmartDashboard sd;

	//PigeonIMU pidgey;
	
	@Override
	public void robotInit() {
		
		frontLeft = new Talon(0);
		backLeft = new Talon(1);
		frontRight = new Talon(2);
		backRight = new Talon(3);
		encoder = new Encoder(4, 5, false, CounterBase.EncodingType.k4X);
		climbingOne = new Talon(6);
		climbingTwo = new Talon(7);
		lift = new Talon(8);
		grabber = new Talon(9);
		
		sd = new SmartDashboard();

		//Wheels are flipped, invert every wheel
		frontLeft.setInverted(true);
		backLeft.setInverted(true);
		backRight.setInverted(true);
		frontRight.setInverted(true);
		
		m_robotDrive = new MecanumDrive(frontLeft, backLeft, frontRight, backRight);
		
	}

	@Override
	public void autonomousInit() {
	}

	@Override
	public void autonomousPeriodic() {
	}

	@Override
	public void teleopInit() {
	}

	@SuppressWarnings("static-access")
	@Override
	public void teleopPeriodic() {
		
		Double XAxis, YAxis, ZAxis;
		
		while(isOperatorControl() && isEnabled()) {
			
			sd.putNumber("Uno", 10);
			
			XAxis = joystick.getRawAxis(1);
			YAxis = joystick.getRawAxis(0);
			ZAxis = joystick.getRawAxis(2);	
		
			LBump = joystick.getRawButton(5); //4 count from 0
			RBump = joystick.getRawButton(6);
			B = joystick.getRawButton(2);
			X = joystick.getRawButton(3);
			A = joystick.getRawButton(1);
			Y = joystick.getRawButton(4);
		
		
			if (RBump) { climb(.666); }
			else if (LBump) { climb(-.666); }
			else { climb(0); }
		
			if (B) { grabber(.5); }
			else if (X) { grabber(-.5); }
			else { grabber(0); } 
		
			if (Y) { lift(.666); }
			else if (A) { lift(-.666); }
			else { lift(0); }
		
			//driveCartesian: Vector r = <Y, X, Z>: But it drives inverse unless you do <X, Y, Z>
			drive(XAxis, YAxis, ZAxis);
		}
	}
	@Override
	public void testPeriodic() {
	}
	
	
	public void climb(double speed) { //Pos speed = up, neg speed = down
		climbingOne.set(speed);
		climbingTwo.set(-speed);
	}
	
	public void grabber(double speed) {
		grabber.set(speed);
	}
	
	public void lift(double speed) {
		lift.set(speed);
	}
	
	public void drive(double XAxis, double YAxis, double ZAxis) {
		m_robotDrive.driveCartesian(-YAxis, XAxis, -ZAxis);
	}
	
	public void drive(double XAxis, double YAxis, double ZAxis, double gyro) {
		m_robotDrive.driveCartesian(XAxis, YAxis, ZAxis, gyro);
	}
	
}
