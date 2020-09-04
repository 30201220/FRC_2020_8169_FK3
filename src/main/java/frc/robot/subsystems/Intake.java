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

public class Intake extends SubsystemBase {
  private CANSparkMax motorIntake = new CANSparkMax(Constants.MOTOR_INTAKE_ID, MotorType.kBrushless);
  private Solenoid cylinderIntake = new Solenoid(Constants.CYLINDER_INTAKE_ID);

  public Intake() {

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void setMotorIntakeSpeed(final double speed){
    motorIntake.set(Tools.range(speed, 1, 0) * Constants.N_MOTOR_INTAKE_SPEED);
  }

  public void setCylinderIntake(final boolean cy){
    cylinderIntake.set(cy);
  }
}
