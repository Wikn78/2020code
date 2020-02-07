/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;



import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.ExampleCommand;
import frc.robot.subsystems.BallTrain;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.HangTrain;
import frc.robot.subsystems.PneumaticsController;
import oi.limelightvision.limelight.frc.LimeLight;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

  /*************************************************************************** 
                              SubSystem Decalation
    ***************************************************************************/
  public static ExampleSubsystem m_subsystem = new ExampleSubsystem();
  public static DriveTrain driveTrain = new DriveTrain();
  public static BallTrain ballTrain = new BallTrain();
  public static HangTrain hangTrain = new HangTrain();
  public static PneumaticsController pneumaticsController = new PneumaticsController();
  public static Compressor compressor = new Compressor(0);
  public static OI m_oi;
  

/*************************************************************************** 
                              LimeLight Variables
    ***************************************************************************/
  
  private final LimeLight myLimeLight = new LimeLight();
  NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
  final double maxTurn = 0.1;
  final double maxDrive = .4;
  final double stopDistance = 15;
  final double distanceToTarget = myLimeLight.getTargetArea();
  final double rotDegToTarget = myLimeLight.getdegRotationToTarget();
/*************************************************************************** 
                              Static Variables
    ***************************************************************************/
  
  
  Command m_autonomousCommand;
  SendableChooser<Command> m_chooser = new SendableChooser<>();

  /**
   * This function is run when the robot is first started up and should be used
   * for any initialization code.
   */
  @Override
  public void robotInit() {
    m_oi = new OI();
    m_chooser.setDefaultOption("Default Auto", new ExampleCommand());
    // chooser.addOption("My Auto", new MyAutoCommand());
    SmartDashboard.putData("Auto mode", m_chooser);
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for
   * items like diagnostics that you want ran during disabled, autonomous,
   * teleoperated and test.
   *
   * <p>
   * This runs after the mode specific periodic functions, but before LiveWindow
   * and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {

  }

  /**
   * This function is called once each time the robot enters Disabled mode. You
   * can use it to reset any subsystem information you want to clear when the
   * robot is disabled.
   */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable chooser
   * code works with the Java SmartDashboard. If you prefer the LabVIEW Dashboard,
   * remove all of the chooser code and uncomment the getString code to get the
   * auto name from the text box below the Gyro
   *
   * <p>
   * You can add additional auto modes by adding additional commands to the
   * chooser code above (like the commented example) or additional comparisons to
   * the switch structure below with additional strings & commands.
   */
  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_chooser.getSelected();

    /*
     * String autoSelected = SmartDashboard.getString("Auto Selector", "Default");
     * switch(autoSelected) { case "My Auto": autonomousCommand = new
     * MyAutoCommand(); break; case "Default Auto": default: autonomousCommand = new
     * ExampleCommand(); break; }
     */

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.start();
    }
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {

    /*************************************************************************** 
                          LimeLight Tracking Autonomous
    ***************************************************************************/
    Scheduler.getInstance().run();
    final double distanceToTarget = myLimeLight.getTargetArea();
    final double rotDegToTarget = myLimeLight.getdegRotationToTarget();
    if(myLimeLight.getIsTargetFound()){

      if(rotDegToTarget > 2){
                
        driveTrain.SetRightTurn(maxTurn);
        
      }
      else if(rotDegToTarget < -2){

        driveTrain.SetLeftTurn(maxTurn);

      }
      else if(stopDistance >= distanceToTarget  && (rotDegToTarget < 2 && rotDegToTarget > -2)){
        
        driveTrain.rightMotor1.set(-maxDrive);
        driveTrain.leftMotor2.set(-maxDrive);
      }
      else if(stopDistance <= distanceToTarget && (rotDegToTarget < 2 && rotDegToTarget > -2)){
        driveTrain.rightMotor1.set(maxDrive);
        driveTrain.leftMotor2.set(maxDrive);
      }
      else{

        driveTrain.SetLeftMotors(0);
        driveTrain.SetRightMotors(0);
      }

    }
    else{
      //no target DRIVE!
      driveTrain.SetLeftMotors(-.1);
      driveTrain.SetRightMotors(-.1);
      
    }
  


  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    
    /*************************************************************************** 
                              Compressor On/Off
    ***************************************************************************/
    compressor.setClosedLoopControl(true);
    boolean enabled = compressor.enabled();
    boolean pressureSwitch = compressor.getPressureSwitchValue();
    double current = compressor.getCompressorCurrent();
    if(pressureSwitch && !enabled){
      compressor.start();
      
    }
    else {
      compressor.stop();
    }
    Scheduler.getInstance().run();

     /*************************************************************************** 
                              LimeLight Tracking
    ***************************************************************************/
     final boolean auto = m_oi.driverController.getAButton();
    final double distanceToTarget = myLimeLight.getTargetArea();
    final double rotDegToTarget = myLimeLight.getdegRotationToTarget();
    final NetworkTableEntry sSX = table.getEntry("tx");
    final double sX = sSX.getDouble(0.0);
       

        if (auto)
        {

          //myLimeLight.setCamMode(CamMode.kvision);
          //myLimeLight.setLEDMode(LedMode.kforceOn);
          if (myLimeLight.getIsTargetFound())
          {
              
              if(rotDegToTarget > 2){
                
                driveTrain.SetRightTurn(maxTurn);
                
              }
              else if(rotDegToTarget < -2){

                driveTrain.SetLeftTurn(maxTurn);

              }
              else if(stopDistance >= distanceToTarget  && (rotDegToTarget < 2 && rotDegToTarget > -2)){
                
                driveTrain.rightMotor1.set(-maxDrive);
                driveTrain.leftMotor2.set(-maxDrive);
              }
              else if(stopDistance <= distanceToTarget && (rotDegToTarget < 2 && rotDegToTarget > -2)){
                driveTrain.rightMotor1.set(maxDrive);
                driveTrain.leftMotor2.set(maxDrive);

              }
              
           // m_drive.arcadeDrive(m_LimelightDriveCommand, m_LimelightSteerCommand);


            

          } 
          else
          {

            driveTrain.SetLeftMotors(0);
            driveTrain.SetRightMotors(0);
          }
        }
        
          //myLimeLight.setLEDMode(LedMode.kforceOff);
          //myLimeLight.setCamMode(CamMode.kdriver);
          

  }
  
  
  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }




}
