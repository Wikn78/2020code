/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;


import edu.wpi.first.wpilibj.PWMVictorSPX;

import edu.wpi.first.wpilibj.command.Subsystem;

import frc.robot.RobotMap;

import frc.robot.commands.BallLaunch;


/**
 * Add your docs here.
 */
public class BallTrain extends Subsystem {

  
  private PWMVictorSPX ballMotorLaunch1 = new PWMVictorSPX(RobotMap.Ball_Motor_Launch1);
  private PWMVictorSPX ballMotorLaunch2 = new PWMVictorSPX(RobotMap.Ball_Motor_Launch2);
  


  /*public void SetBallMotorIntake(double speed){

    ballMotorIntake1.set(speed);
    ballMotorIntake2.set(speed);


  }
  */
  public void SetBallMotorLaunch(double speed){

    ballMotorLaunch1.set(-speed);
    ballMotorLaunch2.set(-speed);
  }
  

  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    
      

      setDefaultCommand(new BallLaunch());
    
    
    
  }
  
}
