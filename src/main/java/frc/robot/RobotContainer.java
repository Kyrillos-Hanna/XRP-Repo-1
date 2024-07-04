// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.commands.TeleopArcadeDriveCmd;
import frc.robot.subsystems.Drivetrain;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj.xrp.XRPServo;
import frc.robot.subsystems.Arm;

public class RobotContainer {
  private final CommandXboxController m_XboxController = new CommandXboxController(0);
  private final Drivetrain m_drivetrain = new Drivetrain(0, 1);
  private final TeleopArcadeDriveCmd m_TeleopArcadeDriveCmd = new TeleopArcadeDriveCmd(m_drivetrain, m_XboxController::getLeftY, m_XboxController::getRightY);
  private final XRPServo m_servo = new XRPServo(4);
  private final Arm m_arm = new Arm(m_servo);


  public RobotContainer() {
    configureBindings();
  }

  private void configureBindings() {
   // m_drivetrain.setDefaultCommand(m_TeleopArcadeDriveCmd); 

  //  m_drivetrain.setDefaultCommand(getArcadeDriveCommand());
  m_drivetrain.setDefaultCommand(new RunCommand(() -> { m_drivetrain.arcadeDrive(-m_XboxController.getLeftY(), m_XboxController.getRightX());}, m_drivetrain));
  m_arm.setDefaultCommand(moveArm(0));
  m_XboxController.a().whileTrue(moveArm(45));
  m_XboxController.b().whileTrue(moveArm(90));
  m_XboxController.x().whileTrue(moveArm(135));
  m_XboxController.y().whileTrue(moveArm(180));
  
  } 

  private Command getArcadeDriveCommand() {
    return new RunCommand(() -> { m_drivetrain.arcadeDrive(-m_XboxController.getLeftY(), m_XboxController.getRightX());}, m_drivetrain);
  }

  private Command moveArm(int angle) {
    return new RunCommand(() -> m_arm.setAngle(angle), m_arm);
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
