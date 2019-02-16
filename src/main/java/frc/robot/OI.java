/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.AssistedDrive;
import frc.robot.commands.AutoPlaceHatchPanel;
import frc.robot.commands.DriveWithJoysticks;
import frc.robot.commands.OpenHook;
import frc.robot.commands.OpenRamp;
import frc.robot.commands.PullHookIn;
import frc.robot.commands.PushHookOut;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  //// CREATING BUTTONS
  // One type of button is a joystick button which is any button on a
  //// joystick.
  // You create one by telling it which joystick it's on and which button
  // number it is.
  public  Joystick controller;
  public JoystickButton a;
  public JoystickButton b;
  public JoystickButton x;
  public JoystickButton y;

  // There are a few additional built in buttons you can use. Additionally,
  // by subclassing Button you can create custom triggers and bind those to
  // commands the same as any other Button.
  public OI(){
    controller= new Joystick(RobotMap.controllerport);
    a= new JoystickButton(controller,RobotMap.aButton);
    b= new JoystickButton(controller, RobotMap.bButton);
    x= new JoystickButton (controller,RobotMap.xButton);
    y= new JoystickButton (controller,RobotMap.xButton);

    a.toggleWhenPressed(new AssistedDrive());
    b.toggleWhenPressed(new OpenHook());
    x.toggleWhenPressed(new AutoPlaceHatchPanel());
    y.toggleWhenPressed(new PushHookOut());

    SmartDashboard.putData("Tank Drive", new DriveWithJoysticks());
    SmartDashboard.putData("Assisted Drive", new AssistedDrive());
    SmartDashboard.putData("Open Hook", new OpenHook());
    SmartDashboard.putData("Auto Place Hatch Panel", new AutoPlaceHatchPanel());
    SmartDashboard.putData("Open Ramp",new OpenRamp());
    SmartDashboard.putData("Push hook in", new PullHookIn());
    SmartDashboard.putData("Push hook out", new PushHookOut());

  }

  //// TRIGGERING COMMANDS WITH BUTTONS
  // Once you have a button, it's trivial to bind it to a button in one of
  // three ways:

  // Start the command when the button is pressed and let it run the command
  // until it is finished as determined by it's isFinished method.

  // Run the command while the button is being held down and interrupt it once
  // the button is released.
  // button.whileHeld(new ExampleCommand());

  // Start the command when the button is released and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenReleased(new ExampleCommand());
}
