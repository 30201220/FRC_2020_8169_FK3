/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.io.IOException;
import java.nio.file.Path;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.TrajectoryUtil;
import edu.wpi.first.wpilibj.trajectory.constraint.DifferentialDriveVoltageConstraint;
import frc.robot.commands.carControl.*;
import frc.robot.commands.climb.Climbing;
import frc.robot.commands.auto.*;
import frc.robot.commands.ballCatch.CatchBall;
import frc.robot.commands.ballShoot.*;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final DriveTrain m_driveTrain = new DriveTrain();
  private final Intake m_intake = new Intake();
  private final Shooter m_shooter = new Shooter(); 
  private final Climber m_climber = new Climber();

  private final Auto m_auto = new Auto();
  private final DriveControl m_driveControl = new DriveControl(m_driveTrain);
  private final ShootManual m_ShootManual = new ShootManual(m_shooter);
  private final CatchBall m_catchBall = new CatchBall(m_intake);
  private final Climbing m_climbing = new Climbing(m_climber);

  private final XboxController driverController = new XboxController(Constants.XBOX_DRIVER_ID);
  private final XboxController operatorController = new XboxController(Constants.XBOX_OPERATOR_ID);

  private Button dButtonA = new JoystickButton(this.driverController, Constants.BUTTON_A);
	private Button dButtonB = new JoystickButton(this.driverController, Constants.BUTTON_B);
	private Button dButtonX = new JoystickButton(this.driverController, Constants.BUTTON_X);
  private Button dButtonY = new JoystickButton(this.driverController, Constants.BUTTON_Y);
  private Button dButtonRB = new JoystickButton(this.driverController, Constants.BUTTON_RB);
  private Button dButtonLB = new JoystickButton(this.driverController, Constants.BUTTON_LB);
  private Button dButtonSTART = new JoystickButton(this.driverController, Constants.BUTTON_START);
  private Button dButtonLEFT = new JoystickButton(this.driverController, Constants.BUTTON_LEFT);
  private Button dButtonRIGHT = new JoystickButton(this.driverController, Constants.BUTTON_RIGHT);
  
	private Button oButtonA = new JoystickButton(this.operatorController, Constants.BUTTON_A);
	private Button oButtonB = new JoystickButton(this.operatorController, Constants.BUTTON_B);
	private Button oButtonY = new JoystickButton(this.operatorController, Constants.BUTTON_Y);
	private Button oButtonX = new JoystickButton(this.operatorController, Constants.BUTTON_X);
  private Button oButtonLB = new JoystickButton(this.operatorController, Constants.BUTTON_LB);
  private Button oButtonRB = new JoystickButton(this.operatorController, Constants.BUTTON_RB);
  private Button oButtonSTART = new JoystickButton(this.operatorController, Constants.BUTTON_START);
  private Button oButtonBACK = new JoystickButton(this.operatorController, Constants.BUTTON_BACK);

  private String trajectoryJSON = "paths/Test.wpilib.json";
  private Trajectory mTrajectory;
  private final DriveTrainAuto mDriveTrainAuto = new DriveTrainAuto();

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
    setDefaultCommand();
    readTrajectoryPath();
  }

  public void setDefaultCommand(){
   // m_driveTrain.setDefaultCommand(m_driveControl);
    m_intake.setDefaultCommand(m_catchBall);
    m_climber.setDefaultCommand(m_climbing);
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    
  }

  public void readTrajectoryPath(){
    try {
      System.out.println("mTest");
      System.out.println(Filesystem.getDeployDirectory().toPath());
      Path trajectoryPath = Filesystem.getDeployDirectory().toPath().resolve(trajectoryJSON);
      mTrajectory = TrajectoryUtil.fromPathweaverJson(trajectoryPath);
    } catch (IOException ex) {
      DriverStation.reportError("Unable to open trajectory: " + trajectoryJSON, ex.getStackTrace());
    }
  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    System.out.println("getAutonomousCommand Begin...");

    // An ExampleCommand will run in autonomous
    RamseteCommand ramseteCommand = new RamseteCommand(
    mTrajectory,
    mDriveTrainAuto::getPose,
    new RamseteController(2, 0.7),
    new DifferentialDriveKinematics(0.57), 
    mDriveTrainAuto::tankDriveVolts, 
    mDriveTrainAuto);

    // Run path following command, then stop at the end.
    return ramseteCommand.andThen(() -> mDriveTrainAuto.tankDriveVolts(0, 0));
  }

  public boolean getOperatorButton(int axis) {
		return this.operatorController.getRawButton(axis);
	}

	public boolean getDriverButton(int axis) {
		return this.driverController.getRawButton(axis);
	}

	public double getOperatorRawAxis(int axis) {
		return this.operatorController.getRawAxis(axis);
  }

	public double getDriverRawAxis(int axis) {
		return this.driverController.getRawAxis(axis);
  }

  public int getDriverPOV(){
    return this.driverController.getPOV();
  }

  public int getOperatorPOV(){
    return this.operatorController.getPOV();
  }
}
