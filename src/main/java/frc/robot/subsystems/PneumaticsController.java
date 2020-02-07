/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;


import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.PneumaticsCommand;

/**
 * Add your docs here.
 */
public class PneumaticsController extends Subsystem {
  /*************************************************************************** 
                            Soloenoid Declaration
    ***************************************************************************/
  private Solenoid mySolenoid1 = new Solenoid(RobotMap.Solenoid_1);
  private Solenoid mySolenoid2 = new Solenoid(RobotMap.Solenoid_2);
  private Solenoid mySolenoid3 = new Solenoid(RobotMap.Solenoid_3);
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  
  /*************************************************************************** 
                              Soloenoid Controllers
    ***************************************************************************/
  public void PneumaticsOn(){

    mySolenoid1.set(true);
    mySolenoid2.set(true);
    mySolenoid3.set(true);


  }

  public void PneumaticsOff(){

    mySolenoid1.set(false);
    mySolenoid2.set(false);
    mySolenoid3.set(false);
  }
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new PneumaticsCommand());
  }
}
