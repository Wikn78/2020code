/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

 /*
  public static enum MOTORS{
    //driveTrain
    Left_Motor_1, //Talon 4
    Left_Motor_2, //Talon 3
    Right_Motor_1,//Talon 2
    Right_Motor_2,//Talon 1
    //ballTrain
    Ball_Motor //Spark
    //hangTrain

  }
  */
	public static final int Left_Motor_1 = 3;
  public static final int Left_Motor_2 = 4;

	public static final int Right_Motor_1 = 1;
  public static final int Right_Motor_2 = 2;

  //public static final int Ball_Motor_Intake1 = 0;
  //public static final int Ball_Motor_Intake2 = 0;
  public static final int Ball_Motor_Launch1 = 0;
  public static final int Ball_Motor_Launch2 = 1;
  public static final int Solenoid_1 = 0;
  public static final int Solenoid_2 = 1;
  public static final int Solenoid_3 = 2;
  //controllers
  public static final int Xbox_Controller = 0;
  
	public static final int Left_Stick_Y = 1;
  public static final int Right_Stick_Y = 5;
  public static final int Left_Trigger = 2;
  public static final int Right_Trigger = 3;

  

  public static final int A_button = 1;
  public static final int B_button = 2;
  public static final int X_button = 3;
  public static final int Y_button = 4;
  public static final int Left_Bumber = 5;
  public static final int Right_Bumber = 6;
  public static final int Start_button = 7;
  public static final int Back_button = 8;
  public static final int Dpad_Up = 0;
  public static final int Dpad_Down = 180;
  public static final int Dpad_Right = 90;
  public static final int Dpad_left = 270;
public static final int Left_Stick_X = 0;
public static final int Right_Stick_X = 4;
public static final int Compreser_1 = 0;

  
  
  // If you are using multiple modules, make sure to define both the port
  // number and the module. For example you with a rangefinder:
  // public static int rangefinderPort = 1;
  // public static int rangefinderModule = 1;

}
