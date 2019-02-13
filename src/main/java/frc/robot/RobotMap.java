/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
  // For example to map the left and right motors, you could define the
  // following variables to use with your drivetrain subsystem.
    public static SpeedController topLeft;
    public static SpeedController bottomLeft;
    public static SpeedController topRight;
    public static SpeedController bottomRight;
    public static SpeedController rampMotor;
    public static SpeedController hookActuator;
    public static SpeedController hookMotor;
    public static SpeedControllerGroup left;
   public static SpeedControllerGroup right;
   public static AHRS ahrs;
   public static DifferentialDrive robotDrive;
   public static NetworkTableInstance table;
   //controller port can be found on driver station
   public static int controllerport =1;
    public static int axisvalueleft =1;
   public static  int axisvalueright =5;
   //drive straight pid values
   public static double drivetrainKP= 1.0;
   public static double drivetrainKD =0;
   public static double drivetrainKI=0;
   public static int aButton =1;
   public static int bButton =2;
   public static int xButton =3;
   public static int yButton =4;
   public static double autoSpeed=0.3;   

   //Initializes all the motors
 public static void init(){
    topLeft= new Spark(3);
    bottomLeft= new Spark(2);
    topRight= new Spark(4);
    bottomRight= new Spark(1);
    left = new SpeedControllerGroup(topLeft, bottomLeft);
    right = new SpeedControllerGroup(topRight, bottomRight);
    rampMotor= new Talon(5);
    hookActuator= new Talon(7);
    hookMotor = new Talon(6);
    //sensor
    try{
    ahrs= new AHRS(SPI.Port.kMXP);
    }
    catch(RuntimeException e){
        DriverStation.reportError("Error instantiating AHRS", true);
    }
    SmartDashboard.putBoolean("AHRS callibrating", ahrs.isCalibrating());
    //drivetrain
  robotDrive= new DifferentialDrive(left, right);
  robotDrive.setSafetyEnabled(true);
  robotDrive.setExpiration(0.1);
  robotDrive.setMaxOutput(1.0);
  robotDrive.setRightSideInverted(true);

  table= NetworkTableInstance.getDefault();
 

  

  }

  
}
