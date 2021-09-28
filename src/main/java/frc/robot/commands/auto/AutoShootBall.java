/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.auto;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Store;

public class AutoShootBall extends CommandBase {
  private Shooter m_shooter;
  private Store m_store;
  private Intake m_intake;

  private Timer m_time = new Timer();
  private NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight-shoot");
  private double targetTurnSpeed;
  private double shooterEncoder;
  private double Pa = 1;
  public AutoShootBall(Shooter shooter, Store store, Intake intake) {
    m_shooter = shooter;
    m_store = store;
    m_intake = intake;
    addRequirements(m_shooter);
    addRequirements(m_store);
    addRequirements(m_intake);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_time.reset();
    m_time.start();
    m_intake.setCylinderIntake(true);
    m_intake.setMotorIntakeSpeed(1);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double x = table.getEntry("tx").getDouble(0.0);
    double y = table.getEntry("ty").getDouble(0.0);
    double v = table.getEntry("tv").getDouble(0.0);
    double dis = 2 / Math.tan((y + 17) * Math.PI / 180);
    targetTurnSpeed = 0.9888 * (-3.4907 * Math.pow(dis, 2) + 191.95 * dis + 4036.7) - 143.91;
    shooterEncoder = m_shooter.getMotorFlyWheelSpeed();
    m_shooter.setMotorFlyWheelSpeed((targetTurnSpeed + PIDAngle())/7700);
    m_shooter.setMotorHeaderSpeed((x < 0 ? -Math.pow(-x, 0.5) : Math.pow(x, 0.5))/5);
    if(m_time.get() >= 1){
      m_shooter.setMotor2Speed(1);
      m_store.setMotorFisherSpeed(-1);
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

  public double PIDAngle(){
    double error = targetTurnSpeed - (shooterEncoder); // Error = Target - Actual
    //this.integral += (error*.02); // Integral is increased by the error*time (which is .02 seconds using normal IterativeRobot)
    //derivative = (error - this.previous_error) / .02;
    return Pa*error ;//+ I*this.integral + D*derivative;
  }
}
