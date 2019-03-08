/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class RotateToHatchPanel extends Command {
  private double origyaw;
  private double rotateangle;
  public RotateToHatchPanel() {
    requires(Robot.drivetrain);
  }

  // Called just before t
  //his Command runs the first time
  @Override
  protected void initialize() {
    RobotMap.robotDrive.stopMotor();
DriverStation.reportWarning("iNITIALIZING", false);
   origyaw= RobotMap.ahrs.getYaw();
   rotateangle=RobotMap.table.getTable("ChichenVision").getEntry("tapeDetected").getBoolean(false)?
    (double)RobotMap.table.getTable("ChichenVision").getEntry("tapeYaw").getNumber(100):100;
    Robot.drivetrain.setupPConroller(RobotMap.drivetrainKP, RobotMap.drivetrainKI, RobotMap.drivetrainKD);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
 Robot.drivetrain.rotateToAngle(origyaw);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return rotateangle==100?true:(RobotMap.ahrs.getYaw()-origyaw==rotateangle) ;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    RobotMap.robotDrive.stopMotor();
    Robot.drivetrain.pController.disable();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    RobotMap.robotDrive.stopMotor();
    Robot.drivetrain.pController.disable();
  }
}
