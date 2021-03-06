/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.DriveWithJoysticks;

/**
 * Add your docs here.
 */
public class Drivetrain extends Subsystem implements PIDOutput {
  //private final SpeedControllerGroup left = RobotMap.left;
  //private final SpeedControllerGroup right = RobotMap.right;
  private final DifferentialDrive robotDrive = RobotMap.robotDrive;
  private final AHRS ahrs = RobotMap.ahrs;
   public PIDController pController;
   private double error;

//basic tank drive- we might not need this code
 // public void setSpeed(double rightspeed,double leftspeed){
   // left.set(leftspeed);
    //right.set(rightspeed);
    //SmartDashboard.putString("Speed", "Speed set ");
  //}

  //will be run at the beginning of the match
  public void setupPConroller(double kp, double ki,double kd){
    pController= new PIDController(kp,ki,kd,ahrs,this);
    pController.setInputRange(-180, 180);
    pController.setOutputRange(-1.0, 1.0);
    pController.setAbsoluteTolerance(2.0);
    pController.setContinuous(true);
    pController.disable();
  }
  /*this is the thing that Isabel doesn't want to use:
   If you press a button the robot will automatically drive straight and all 
   you control with the sticks is the speed
  */
  public void driveStraight(){
    pController.enable();
      pController.setSetpoint(ahrs.getYaw());
      error=0;//updated periodically
      
    
  //speed is average of the 2 stick values
  double magnitude = (Robot.m_oi.controller.getRawAxis(RobotMap.axisvalueleft)+Robot.m_oi
  .controller.getRawAxis(RobotMap.axisvalueright))/2;
  robotDrive.tankDrive(-(magnitude+error), -(magnitude-error));
  }
  //rotate to a given angle
  public void rotateToAngle(double angle){
    DriverStation.reportWarning("Rotating", false);
    pController.enable();
    pController.setSetpoint(angle);
    error=0;
    robotDrive.tankDrive(error, error);
  }
  public void driveToHatchPanel(){
pController.enable();
pController.setSetpoint(ahrs.getYaw());
error=0;
robotDrive.tankDrive(-(error+RobotMap.autoSpeed), -(RobotMap.autoSpeed-error));
  }
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
     setDefaultCommand(new DriveWithJoysticks());
  }

  @Override
  public void pidWrite(double output) {
  error=output;
  }
  
}
