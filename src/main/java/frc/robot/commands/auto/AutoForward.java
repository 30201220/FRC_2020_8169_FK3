/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.Ultrasonic.Unit;
import edu.wpi.first.wpilibj.util.Units;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class AutoForward extends CommandBase {
  private DriveTrain m_driveTrain;
  private double m_meter;
  private double P = 0.08;
  /**
   * Creates a new AutoForword.
   */
  public AutoForward(DriveTrain driveTrain, double meter) {
    m_driveTrain = driveTrain;
    addRequirements(m_driveTrain);
    m_meter = meter;
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_driveTrain.zeroEncoder();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_driveTrain.setChassisSpeed(
      PIDInstance(m_meter - m_driveTrain.getLeftMotorsPosition() * Units.inchesToMeters(6) * Math.PI/10.75, P), 
      PIDInstance(m_meter - m_driveTrain.getRightMotorsPosition() * Units.inchesToMeters(6) * Math.PI/10.75, P)
    );
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_driveTrain.setChassisSpeed(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return Math.abs(m_driveTrain.getLeftMotorsPosition() - m_meter) <= 0.01 && Math.abs(m_driveTrain.getRightMotorsPosition() - m_meter) <= 0.01;
  }

  private double PIDInstance(double error, double P){
    return P*error;
  }
}