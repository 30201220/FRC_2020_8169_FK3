/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Tools;
import frc.robot.commands.carControl.DriveControl;

public class DriveTrain extends SubsystemBase {
  public static final CANSparkMax motorLeft1 = new CANSparkMax(Constants.MOTOR_LEFT_1_ID, MotorType.kBrushless);
  public static final CANSparkMax motorLeft2 = new CANSparkMax(Constants.MOTOR_LEFT_2_ID, MotorType.kBrushless);
  public static final CANSparkMax motorRight1 = new CANSparkMax(Constants.MOTOR_RIGHT_1_ID, MotorType.kBrushless);
  public static final CANSparkMax motorRight2 = new CANSparkMax(Constants.MOTOR_RIGHT_2_ID, MotorType.kBrushless);

  private CANEncoder encoderLeft = new CANEncoder(motorLeft1);
  private CANEncoder encoderRight = new CANEncoder(motorRight1);
  
  public DriveTrain() {
  }
  
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void setMotorLeftSpeed(final double speedl){
    motorLeft1.set(Tools.range(speedl, 1, -1));
    motorLeft2.set(Tools.range(speedl, 1, -1));
  }
  
  public void setMotorRightSpeed(final double speedr){
    motorRight1.set(-Tools.range(speedr, 1, -1));
    motorRight2.set(-Tools.range(speedr, 1, -1));
  }

  public void setMotorSpeed(final double speedl, final double speedr){
    setMotorLeftSpeed(speedl);
    setMotorRightSpeed(speedr);
  }

  public double getLeftMotorsPosition(){
    return encoderLeft.getPosition();
  }

  public double getRightMotorsPosition(){
    return encoderRight.getPosition();
  }

  public double getLeftMotorsVelocity(){
    return encoderLeft.getVelocity();
  }

  public double getRightMotorsVelocity(){
    return encoderRight.getVelocity();
  }
}
