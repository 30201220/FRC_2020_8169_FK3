/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.carClimb;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.subsystems.Climber;

public class CarClimb extends CommandBase {
  private Climber m_climber;
  private boolean a = false;
  private boolean aOldState = false;
  private boolean motorEnable = false;
  /**
   * Creates a new CarClimb.
   */
  public CarClimb(Climber climber) {
    m_climber = climber;
    addRequirements(m_climber);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(a){
      if(Robot.m_robotContainer.getDriverButton(Constants.BUTTON_A)){
        m_climber.setCylinderClimber(false);
        if(Robot.m_robotContainer.getDriverButton(Constants.BUTTON_A) != aOldState){
          a = false;
        }
      }
    } else{
      if(Robot.m_robotContainer.getDriverButton(Constants.BUTTON_A)){
        m_climber.setCylinderClimber(true);
        motorEnable = true;
        if(Robot.m_robotContainer.getDriverButton(Constants.BUTTON_A) != aOldState){
          a = true;
        }
      }
    }
    aOldState = Robot.m_robotContainer.getDriverButton(Constants.BUTTON_A);

    if(motorEnable){
      if(Robot.m_robotContainer.getDriverPOV() == 0){
        m_climber.setMotorClimber(0.8);
      } else if(Robot.m_robotContainer.getDriverPOV() == 180){
        m_climber.setMotorClimber(-0.8);
      } else {
        m_climber.setMotorClimber(0);
      }
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
