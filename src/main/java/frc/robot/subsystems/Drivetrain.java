// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.FunctionalCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.xrp.XRPMotor;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.xrp.XRPGyro;

public class Drivetrain extends SubsystemBase {
  private final XRPMotor m_leftMotor;
  private final XRPMotor m_rightMotor;
  private final XRPGyro m_Gyro;
  private final PIDController m_PIDcontroller = new PIDController(0.09, 0, 0.00135);
  /** Creates a new Drivetrain. */
  public Drivetrain(final int leftPort, final int rightPort) {
    m_leftMotor = new XRPMotor(leftPort);
    m_rightMotor = new XRPMotor(rightPort);
    m_Gyro = new XRPGyro();
    m_rightMotor.setInverted(true);
  }

  public void arcadeDrive(final double fwd, final double turn) {
    double leftSpeed = fwd - turn;
    double rightSpeed = fwd + turn;
    m_leftMotor.set(leftSpeed);
    m_rightMotor.set(rightSpeed);
  }

  public double getAngleZ() {
    return m_Gyro.getAngleZ();
  }

  public double getAngleY() {
    return m_Gyro.getAngleY();
  }

  public double getAngleX() {
    return m_Gyro.getAngleX();
  }

  private void goToSetpoint(double angle) {
    double turnSpeed = m_PIDcontroller.calculate(getAngleZ(), angle);
    arcadeDrive(0, turnSpeed);
  }

  private boolean isAtSetpoint(double angle) {
    return MathUtil.isNear(angle, getAngleZ(), 0.5);
  }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("AngleX", getAngleX());
    SmartDashboard.putNumber("AngleY", getAngleY());
    SmartDashboard.putNumber("AngleZ", getAngleZ());
  }

  public Command turnRobotCommand(int angle) {
    return new FunctionalCommand(
      () -> {},
      () -> goToSetpoint(angle), 
      interrupted -> arcadeDrive(0,0),
      () -> isAtSetpoint(angle),
      this);
  }
}
