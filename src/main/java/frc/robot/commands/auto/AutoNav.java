/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.auto;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Store;

public class AutoNav extends CommandBase {
  private DriveTrain m_driveTrain;
  private Intake m_intake;
  private Store m_store;
  private NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight-catch");

  public AutoNav(DriveTrain driveTrain, Intake intake, Store store) {
    m_driveTrain = driveTrain;
    m_intake = intake;
    m_store = store;
    addRequirements(m_driveTrain);
    addRequirements(m_intake);
    addRequirements(m_store);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    //m_intake.setCylinderIntake(true);
    //m_intake.setMotorIntakeSpeed(1);
    m_store.setMotorFisherSpeed(-1);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double x = table.getEntry("tx").getDouble(0.0);
    double y = 10.5 - table.getEntry("ty").getDouble(0.0);
    double v = table.getEntry("tv").getDouble(0.0);
    double speeda = x < 0 ? -Math.pow(-x/30, 0.5) : Math.pow(x/30, 0.5);
    double speedl = y < 0 ? -Math.pow(-x/30, 0.5) : Math.pow(x/30, 0.5);
    m_driveTrain.setChassisSpeed((speedl + speeda)/3, (speedl - speeda)/3);


  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_driveTrain.setChassisSpeed(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
