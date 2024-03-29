/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.ballShoot;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.subsystems.Shooter;

public class ShootManual extends CommandBase {
  private Shooter m_shooter;
  private NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight-shoot");
  private boolean a = false;
  private double Pa = 1;
  private double targetTurnSpeed;
  private double shooterEncoder;
  
  public ShootManual(Shooter shooter) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_shooter = shooter;
    addRequirements(m_shooter);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    SmartDashboard.setDefaultNumber("FS", 0);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double x = table.getEntry("tx").getDouble(0.0);
    double y = table.getEntry("ty").getDouble(0.0);
    double dis = 2 / Math.tan((y + 17) * Math.PI / 180);
    targetTurnSpeed = 0.9888 * (-3.4907 * Math.pow(dis, 2) + 191.95 * dis + 4036.7) -143.91;/*SmartDashboard.getNumber("FS", 7700);*/
    shooterEncoder = m_shooter.getMotorFlyWheelSpeed();
    if (Robot.m_robotContainer.getOperatorButton(Constants.BUTTON_BACK)){
      a = false;
    } else if(Robot.m_robotContainer.getOperatorButton(Constants.BUTTON_START)){
      a = true;
    }
    m_shooter.setMotorFlyWheelSpeed(a ? (targetTurnSpeed + PIDAngle())/7700 : 0); 
    m_shooter.setMotor2Speed(Robot.m_robotContainer.getOperatorButton(Constants.BUTTON_Y) ? 1 : 0);
    int OPOV = Robot.m_robotContainer.getOperatorPOV();
    if(OPOV == 90){
      m_shooter.setMotorHeaderSpeed(1);
    } else if(OPOV == 270){
      m_shooter.setMotorHeaderSpeed(-1);
    } else if(OPOV == 0){
      m_shooter.setMotorHeaderSpeed((x < 0 ? -Math.pow(-x, 0.5) : Math.pow(x, 0.5))/5);
    } else {
      m_shooter.setMotorHeaderSpeed(0);
    }

    SmartDashboard.putNumber("MotorFlyWheelSpeed", shooterEncoder);
    SmartDashboard.putNumber("x", x);
    SmartDashboard.putNumber("y", y);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_shooter.setMotor2Speed(0);
    m_shooter.setMotorFlyWheelSpeed(0);
    m_shooter.setMotorHeaderSpeed(0);
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
