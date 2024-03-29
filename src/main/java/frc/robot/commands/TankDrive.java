/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class TankDrive extends Command {
  public TankDrive() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.driveTrain);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    /*************************************************************************** 
                            Controllers Declaration
    ***************************************************************************/
    
    double rightTrigger = Robot.m_oi.GetDriversRawAxis(RobotMap.Right_Trigger);
    double leftTrigger = Robot.m_oi.GetDriversRawAxis(RobotMap.Left_Trigger);
    double leftStickX = Robot.m_oi.GetDriversRawAxis(RobotMap.Left_Stick_X);

    /*************************************************************************** 
                            Motors Move
    ***************************************************************************/
    if(rightTrigger > .1 || leftStickX > .1 || leftStickX < -.1){
      Robot.driveTrain.SetLeftMotors(-rightTrigger - leftStickX);
      Robot.driveTrain.SetRightMotors(-rightTrigger + leftStickX);
    }
    else if(leftTrigger > .1 || leftStickX > .1 || leftStickX < -.1){
      Robot.driveTrain.SetLeftMotors(leftTrigger - leftStickX);
      Robot.driveTrain.SetRightMotors(leftTrigger + leftStickX);
    }
    else{

      Robot.driveTrain.SetLeftMotors(0);
      Robot.driveTrain.SetRightMotors(0);
    }






  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.driveTrain.SetLeftMotors(0);
    Robot.driveTrain.SetRightMotors(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    this.end();
  }
}
