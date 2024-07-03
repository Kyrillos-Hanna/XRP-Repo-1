// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.xrp.XRPMotor;

public class Drivetrain extends SubsystemBase {
  private final XRPMotor m_leftMotor;
  private final XRPMotor m_rightMotor;
  /** Creates a new Drivetrain. */
  public Drivetrain(final int leftPort, final int rightPort) {
    m_leftMotor = new XRPMotor(leftPort);
    m_rightMotor = new XRPMotor(rightPort);
    m_rightMotor.setInverted(true);
  }

  public void arcadeDrive(final double fwd, final double turn) {
    double leftSpeed = fwd + turn;
    double rightSpeed = fwd - turn;
    m_leftMotor.set(leftSpeed);
    m_rightMotor.set(rightSpeed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
