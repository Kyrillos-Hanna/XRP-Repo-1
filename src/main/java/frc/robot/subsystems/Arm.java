// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.xrp.XRPServo;


public class Arm extends SubsystemBase {
  XRPServo m_arm;
  /** Creates a new Arm. */
  public Arm(XRPServo arm) {
    m_arm = arm;
    m_arm.setAngle(0);
  }

  public void setAngle (int angle) {
    m_arm.setAngle(angle);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public Command moveArmCommand(int angle) {
    return run(() -> setAngle(angle));
  }
}
