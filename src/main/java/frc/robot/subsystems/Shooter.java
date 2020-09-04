/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Tools;

public class Shooter extends SubsystemBase {
  CANSparkMax motorShooterFlyWheel = new CANSparkMax(Constants.MOTOR_SHOOTER_FLTWHEEL_ID, MotorType.kBrushless);
  CANSparkMax motorShooterFisher = new CANSparkMax(Constants.MOTOR_SHOOTER_FISHER_ID, MotorType.kBrushless);
  CANSparkMax motorShooter2 = new CANSparkMax(Constants.MOTOR_SHOOTER_2_ID, MotorType.kBrushless);
  public Shooter() {

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void setMotorFlyWheelSpeed(final double speed){
    motorShooterFlyWheel.set(Tools.range(speed, 1, 0));
  }

  public void setMotorFisherSpeed(final double speed){
    motorShooterFisher.set(Tools.range(speed, 1, 0));
  }

  public void setMotor2Speed(final double speed){
    motorShooter2.set(Tools.range(speed, 1, 0));
  }
}
