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

public class Climber extends SubsystemBase {
  private CANSparkMax motorClimber1 = new CANSparkMax(Constants.MOTOR_CLIMBER_1_ID, MotorType.kBrushless);
  private CANSparkMax motorClimber2 = new CANSparkMax(Constants.MOTOR_CLIMBER_2_ID, MotorType.kBrushless);

  public Climber() {

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void setMotorClimberSpeed(final double speed){
    motorClimber1.set(Tools.range(speed, 1, -1));
    motorClimber2.set(Tools.range(speed, 1, -1));
  }

  public void setMotorClimberUp(){
    setMotorClimberSpeed(Constants.N_MOTOR_CLIMBER_SPEED);
  }

  public void setMotorClimberDown(){
    setMotorClimberSpeed(-Constants.N_MOTOR_CLIMBER_SPEED);
  }
}
