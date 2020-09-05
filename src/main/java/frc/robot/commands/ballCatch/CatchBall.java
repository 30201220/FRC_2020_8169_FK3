/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.ballCatch;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.subsystems.Intake;

public class CatchBall extends CommandBase {
  private Intake m_intake;
  private boolean b = false;
  private boolean x = false;
  private boolean bOldState = false;
  private boolean xOldState = false;
  /**
   * Creates a new CatchBall.
   */
  public CatchBall(Intake intake) {
    m_intake = intake;
    addRequirements(m_intake);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(b){
      if(Robot.m_robotContainer.getDriverButton(Constants.BUTTON_B)){
        m_intake.setMotorIntakeSpeed(0);
        if(Robot.m_robotContainer.getDriverButton(Constants.BUTTON_B) != bOldState){
          b = false;
        }
      }
    } else{
      if(Robot.m_robotContainer.getDriverButton(Constants.BUTTON_B)){
        m_intake.setMotorIntakeSpeed(1);
        if(Robot.m_robotContainer.getDriverButton(Constants.BUTTON_B) != bOldState){
          b = true;
        }
      }
    }
    bOldState = Robot.m_robotContainer.getDriverButton(Constants.BUTTON_B);

    if(x){
      if(Robot.m_robotContainer.getDriverButton(Constants.BUTTON_X)){
        m_intake.setCylinderIntake(false);
        if(Robot.m_robotContainer.getDriverButton(Constants.BUTTON_X) != xOldState){
          x = false;
        }
      }
    } else{
      if(Robot.m_robotContainer.getDriverButton(Constants.BUTTON_X)){
        m_intake.setCylinderIntake(true);
        if(Robot.m_robotContainer.getDriverButton(Constants.BUTTON_X) != xOldState){
          x = true;
        }
      }
    }
    xOldState = Robot.m_robotContainer.getDriverButton(Constants.BUTTON_X);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_intake.setMotorIntakeSpeed(0);
    m_intake.setCylinderIntake(false);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
