/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Tools;

public class Store extends SubsystemBase {
  private VictorSPX motorFisher = new VictorSPX(Constants.MOTOR_SHOOTER_FISHER_ID); 

  public Store() {

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void setMotorFisherSpeed(final double speed){
    motorFisher.set(ControlMode.PercentOutput,Tools.range(speed, 1, -1));
  }
  
}
