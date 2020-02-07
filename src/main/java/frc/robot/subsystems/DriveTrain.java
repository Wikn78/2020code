/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.TankDrive;


/**
 * Add your docs here.
 */
public class DriveTrain extends Subsystem {

  /*************************************************************************** 
                              Talon Declaration
    ***************************************************************************/
  public WPI_TalonSRX leftMotor1 = new WPI_TalonSRX(RobotMap.Left_Motor_1);
  public  WPI_TalonSRX leftMotor2 = new WPI_TalonSRX(RobotMap.Left_Motor_2);
  public WPI_TalonSRX rightMotor1 = new WPI_TalonSRX(RobotMap.Right_Motor_1);
  public WPI_TalonSRX rightMotor2 = new WPI_TalonSRX(RobotMap.Right_Motor_2);
  
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
     setDefaultCommand(new TankDrive());
  }
  /*************************************************************************** 
                          Talon Setters
    ***************************************************************************/
  public void SetLeftTurn(double speed){
    leftMotor1.setInverted(true);
    leftMotor2.setInverted(true);
    rightMotor1.setInverted(true);
    rightMotor2.setInverted(true);
    leftMotor1.set(speed);
    leftMotor2.set(speed);

    rightMotor1.set(speed);
    rightMotor2.set(speed);
  }
  public void SetRightTurn(double speed){
    leftMotor1.setInverted(false);
    leftMotor2.setInverted(false);
    leftMotor1.set(speed);
    leftMotor2.set(speed);
    rightMotor1.setInverted(false);
    rightMotor2.setInverted(false);
    rightMotor1.set(speed);
    rightMotor2.set(speed);
  }
  
  public void SetLeftMotors(double speed){
    leftMotor1.setInverted(true);
    leftMotor2.setInverted(true);
    leftMotor1.set(speed);
    leftMotor2.set(speed);
  }


  public void SetRightMotors(double speed){
    rightMotor1.setInverted(false);
    rightMotor2.setInverted(false);
    rightMotor1.set(speed);
    rightMotor2.set(speed);
  }
}
