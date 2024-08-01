// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.Supplier; //To create lambda data types

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Drivetrain;

public class TeleopArcadeDriveCmd extends Command {
  private final Drivetrain m_drivetrain;
  private final Supplier<Double> m_forwardSupplier;
  private final Supplier<Double> m_turnSupplier;

  /** Creates a new TeleopArcadeDriveCmd. */
  public TeleopArcadeDriveCmd(final Drivetrain iDrivetrain, final Supplier<Double> iForwardSupplier, final Supplier<Double> iTurnSupplier) {
    m_drivetrain = iDrivetrain;
    m_forwardSupplier = iForwardSupplier;
    m_turnSupplier = iTurnSupplier;
    addRequirements(iDrivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_drivetrain.arcadeDrive(-m_forwardSupplier.get(), m_turnSupplier.get());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
