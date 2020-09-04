/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.ballShoot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.subsystems.Shooter;

public class ShootManual extends CommandBase {
  private Timer time = new Timer();
  private Shooter m_shooter;
  /**
   * Creates a new w.
   */
  public ShootManual(Shooter shooter) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_shooter = shooter;
    addRequirements(m_shooter);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_shooter.setMotorFlyWheelSpeed(1);
    m_shooter.setMotorFisherSpeed(1);
    time.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(time.get() >= 1){
      m_shooter.setMotor2Speed(1);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_shooter.setMotor2Speed(0);
    m_shooter.setMotorFisherSpeed(0);
    m_shooter.setMotorFlyWheelSpeed(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return Robot.m_robotContainer.getDriverButton(Constants.BUTTON_BACK);
  }
}
