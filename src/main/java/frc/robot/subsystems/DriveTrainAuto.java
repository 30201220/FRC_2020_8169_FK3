/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Tools;
import frc.robot.commands.carControl.DriveControl;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Units;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.SPI;
import com.kauailabs.navx.frc.AHRS;


public class DriveTrainAuto extends SubsystemBase {
  private CANSparkMax motorLeft1 = DriveTrain.motorLeft1;
  private CANSparkMax motorLeft2 = DriveTrain.motorLeft2;
  private CANSparkMax motorRight1 = DriveTrain.motorRight1;
  private CANSparkMax motorRight2 = DriveTrain.motorRight2;

  private CANEncoder encoderLeft = new CANEncoder(motorLeft1);
  private CANEncoder encoderRight = new CANEncoder(motorRight1);

  private final SpeedControllerGroup m_leftMotors = new SpeedControllerGroup(motorLeft1, motorLeft2);

  // The motors on the right side of the drive.
  private final SpeedControllerGroup m_rightMotors = new SpeedControllerGroup(motorRight1, motorRight2);

  // The robot's drive
  private final DifferentialDrive m_drive = new DifferentialDrive(m_leftMotors, m_rightMotors);

  // The gyro sensor
  private final AHRS m_ahrs = new AHRS(SPI.Port.kMXP);

  // Odometry class for tracking robot pose
  private final DifferentialDriveOdometry m_odometry;

  public DriveTrainAuto(){
    m_ahrs.reset();
    resetEncoders();
    m_odometry = new DifferentialDriveOdometry(Rotation2d.fromDegrees(-m_ahrs.getAngle()));
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    m_odometry.update(Rotation2d.fromDegrees(-m_ahrs.getAngle()),
    getLeftEncoderVelocity() * Math.PI * Units.inchesToMeters(6.0) / 60,
    getRightEncoderVelocity() * Math.PI * Units.inchesToMeters(6.0) / 60
    );
  }

  public void resetEncoders() {
    encoderLeft.setPosition(0);
    encoderRight.setPosition(0);
  }

  public double getLeftEncoderPosition(){
    return -encoderLeft.getPosition()/Constants.TOUCHBOX_RATIO;
  }

  public double getRightEncoderPosition(){
    return encoderRight.getPosition()/Constants.TOUCHBOX_RATIO;
  }

  public double getLeftEncoderVelocity(){
    return encoderLeft.getVelocity()/Constants.TOUCHBOX_RATIO;
  }

  public double getRightEncoderVelocity(){
    return encoderRight.getVelocity()/Constants.TOUCHBOX_RATIO;
  }

  public Pose2d getPose() {
    return m_odometry.getPoseMeters();
  }

  public DifferentialDriveWheelSpeeds getWheelSpeeds() {
    return new DifferentialDriveWheelSpeeds(getLeftEncoderVelocity(), getRightEncoderVelocity());
  }

  public void resetOdometry(Pose2d pose) {
    resetEncoders();
    m_odometry.resetPosition(pose, Rotation2d.fromDegrees(-m_ahrs.getAngle()));
  }

  public void arcadeDrive(double fwd, double rot) {
    m_drive.arcadeDrive(fwd, rot);
  }

  public void tankDriveVolts(double leftVolts, double rightVolts) {
    System.out.println("is running");
    m_leftMotors.set(-leftVolts/12);
    m_rightMotors.set(rightVolts/12);
    System.out.println("LOpt"+-leftVolts);
    System.out.println("ROpt"+rightVolts);
    SmartDashboard.putNumber("leftVolts", leftVolts/12);
    SmartDashboard.putNumber("rightVolts", rightVolts/12);
    SmartDashboard.putNumber("lV", getLeftEncoderVelocity());
    SmartDashboard.putNumber("rV", getRightEncoderVelocity());
    m_drive.feed();
  }

  public double getAverageEncoderDistance() {
    return (getLeftEncoderPosition() + getRightEncoderPosition()) / 2.0;
  }

  public void zeroHeading() {
    m_ahrs.reset();
  }

  public double getHeading() {
    return m_ahrs.getAngle();
  }

  public double getTurnRate() {
    return -m_ahrs.getRate();
  }
  
}
