/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

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
    public static SpeedControllerGroup left;
   public static SpeedControllerGroup right;
   public static AHRS ahrs;
   public static DifferentialDrive robotDrive;
   public static int controllerport =0;
    public static int axisvalueleft = 2;
   public static  int axisvalueright =5;
   //Initializes all the motors
 public static void init(){
    topLeft= new Spark(0);
    bottomLeft= new Spark(1);
    topRight= new Spark(2);
    bottomRight= new Spark(3);
    left = new SpeedControllerGroup(topLeft, bottomLeft);
    right = new SpeedControllerGroup(topRight, bottomRight);
    
    //sensor
    ahrs= new AHRS(SPI.Port.kMXP);
  robotDrive= new DifferentialDrive(left, right);
  robotDrive.setSafetyEnabled(true);
  robotDrive.setExpiration(0.1);
  robotDrive.setMaxOutput(1.0);


  }

  
}
