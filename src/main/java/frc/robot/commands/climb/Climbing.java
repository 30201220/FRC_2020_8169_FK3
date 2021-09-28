/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.climb;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Climber;

public class Climbing extends CommandBase {
  private final Climber m_climber;
  /**
   * Creates a new Climbing.
   */
  public Climbing(Climber climber) {
    m_climber = climber;
    addRequirements(m_climber);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_climber.setMotorClimberSpeed(0);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(Robot.m_robotContainer.getDriverPOV() == 0){
      m_climber.setMotorClimberUp();
    } else if(Robot.m_robotContainer.getDriverPOV() == 180){
      m_climber.setMotorClimberDown();
    } else{
      m_climber.setMotorClimberSpeed(0);
    }
    //m_climber.setMotorClimberSpeed(RobotContainer.getDriverRawAxis(Constants.RIGHT_STICK_Y));
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_climber.setMotorClimberSpeed(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
