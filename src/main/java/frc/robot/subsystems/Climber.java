/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Tools;

public class Climber extends SubsystemBase {
  private CANSparkMax motorClimberL = new CANSparkMax(Constants.MOTOR_CLIMBER_L, MotorType.kBrushless);
  private CANSparkMax motorClimberR = new CANSparkMax(Constants.MOTOR_CLIMBER_R, MotorType.kBrushless);
  private Solenoid cylinderClimber = new Solenoid(Constants.CYLINDER_CLIMBER_ID);
  /**
   * Creates a new Climber.
   */
  public Climber() {

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void setCylinderClimber(final boolean cy){
    cylinderClimber.set(cy);
  }

  public void setMotorClimber(double speed){
    motorClimberL.set(Tools.range(speed, 1, -1));
    motorClimberR.set(Tools.range(speed, 1, -1));
  }
}
