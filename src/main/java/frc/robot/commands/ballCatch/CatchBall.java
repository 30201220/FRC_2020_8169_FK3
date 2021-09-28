/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.ballCatch;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Store;

public class CatchBall extends CommandBase {
  private Intake m_intake;
  private Store m_store;
  private boolean b = false;
  private boolean x = false;
  private boolean bOldState = false;
  private boolean xOldState = false;

  public CatchBall(Intake intake, Store store) {
    m_intake = intake;
    m_store = store;
    addRequirements(m_intake);
    addRequirements(m_store);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    boolean statusButtonB = Robot.m_robotContainer.getOperatorButton(Constants.BUTTON_B);
    if(b){
      if(statusButtonB){
        m_intake.setMotorIntakeSpeed(0);
        if(statusButtonB != bOldState){
          b = false;
        }
      }
    } else{
      if(statusButtonB){
        m_intake.setMotorIntakeSpeed(1);
        if(statusButtonB != bOldState){
          b = true;
        }
      }
    }
    bOldState = statusButtonB;
    boolean statusButtonX = Robot.m_robotContainer.getOperatorButton(Constants.BUTTON_X);
    if(x){
      if(statusButtonX){
        m_intake.setCylinderIntake(false);
        if(statusButtonX != xOldState){
          x = false;
        }
      }
    } else{
      if(statusButtonX){
        m_intake.setCylinderIntake(true);
        if(statusButtonX != xOldState){
          x = true;
        }
      }
    }
    xOldState = statusButtonX;
    SmartDashboard.putBoolean("IntakeMotor", x);

    if(Robot.m_robotContainer.getOperatorButton(Constants.BUTTON_RB)) {
      m_store.setMotorFisherSpeed(1);
    } else if(Robot.m_robotContainer.getOperatorButton(Constants.BUTTON_LB)) {
      m_store.setMotorFisherSpeed(-1);
    } else {
      m_store.setMotorFisherSpeed(0);
    }
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
