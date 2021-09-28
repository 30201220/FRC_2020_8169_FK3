/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Tools;

public class Shooter extends SubsystemBase {
  CANSparkMax motorFlyLeft = new CANSparkMax(Constants.MOTOR_SHOOTER_FLY_LEAT_ID, MotorType.kBrushless);
  CANSparkMax motorFlyRight = new CANSparkMax(Constants.MOTOR_SHOOTER_FLY_RIGHTL_ID, MotorType.kBrushless);
  CANSparkMax motor2 = new CANSparkMax(Constants.MOTOR_SHOOTER_2_ID, MotorType.kBrushless);
  VictorSPX motorHeader = new VictorSPX(Constants.MOTOR_SHOOTER_HEADER_ID);

  CANEncoder encoderFlyLeft = new CANEncoder(motorFlyLeft);

  public Shooter() {

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
  
  public void setMotorFlyWheelSpeed(final double speed){
    motorFlyLeft.set(Tools.range(speed, 1, 0));
    motorFlyRight.set(-Tools.range(speed, 1, 0));
  }

  public void setMotor2Speed(final double speed){
    motor2.set(Tools.range(speed, 1, 0) * Constants.N_MOTOR_SHOOTER2_SPEED);
  }

  public void setMotorHeaderSpeed(final double speed){
    motorHeader.set(ControlMode.PercentOutput,Tools.range(speed, 1, -1) * Constants.N_MOTOR_HEADER_SPEED);
  }

  public double getMotorFlyWheelSpeed(){
    return encoderFlyLeft.getVelocity() * 1.5;
  }
}
