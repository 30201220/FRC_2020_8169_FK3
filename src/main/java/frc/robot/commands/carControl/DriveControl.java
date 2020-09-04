/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.carControl;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.subsystems.DriveTrain;

public class DriveControl extends CommandBase {
  private final DriveTrain m_driveTrain;

  /**
   * Creates a new driveControl.
   */
  public DriveControl(DriveTrain driveTrain) {
    m_driveTrain = driveTrain;
    addRequirements(m_driveTrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_driveTrain.setMotorSpeed(0, 0);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double triggerVal = Robot.m_robotContainer.getDriverRawAxis(Constants.RIGHT_TRIGGER) - Robot.m_robotContainer.getDriverRawAxis(Constants.LEFT_TRIGGER);
    double stick = Robot.m_robotContainer.getDriverRawAxis(Constants.LEFT_STICK_X) * (Constants.TURNING_RATE);
    
    double speedl = (triggerVal + stick);
    double speedr = (triggerVal - stick);
    
    m_driveTrain.setMotorSpeed(speedl, speedr);

    SmartDashboard.putNumber("speedl", speedl);
    SmartDashboard.putNumber("speedr", speedr);
    SmartDashboard.putNumber("LeftEncoder", m_driveTrain.getLeftMotorsPosition());
    SmartDashboard.putNumber("RightEncoder", m_driveTrain.getRightMotorsPosition());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_driveTrain.setMotorSpeed(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
