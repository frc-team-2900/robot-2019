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

public class OpenHook extends Command {
  public OpenHook() {
   requires(Robot.hook);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if(Robot.m_oi.controller.getRawAxis(2)>0&&Robot.m_oi.controller.getRawAxis(3)==0){
    RobotMap.hookMotor.set(Robot.m_oi.controller.getRawAxis(2));
    }
    else if(Robot.m_oi.controller.getRawAxis(3)>0&&Robot.m_oi.controller.getRawAxis(2)==0){
      RobotMap.hookMotor.set(-Robot.m_oi.controller.getRawAxis(3));
    }
    else {
      RobotMap.hookMotor.set(0);
      System.out.println("Both axies are above 0");
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
    RobotMap.hookMotor.set(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
