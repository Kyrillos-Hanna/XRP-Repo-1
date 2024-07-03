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

public class RobotContainer {
  private final CommandXboxController m_XboxController = new CommandXboxController(0);
  private final Drivetrain m_drivetrain = new Drivetrain(0, 1);
  private final TeleopArcadeDriveCmd m_TeleopArcadeDriveCmd = new TeleopArcadeDriveCmd(m_drivetrain, m_XboxController::getLeftY, m_XboxController::getRightY);



  public RobotContainer() {
    configureBindings();
  }

  private void configureBindings() {
    m_drivetrain.setDefaultCommand(m_TeleopArcadeDriveCmd);
  }

  private Command getArcadeDriveCommand() {
    return new RunCommand(() -> { m_drivetrain.arcadeDrive(-m_XboxController.getLeftY(), m_XboxController.getRightX());}, m_drivetrain);
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
