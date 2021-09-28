/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.turnTable;

import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.subsystems.Lollipop;

public class turnOnColor extends CommandBase {
  private Lollipop m_lollipop;
  private final I2C.Port i2cPort = I2C.Port.kOnboard;
  private final ColorSensorV3 colorSensor = new ColorSensorV3(i2cPort);
  private final ColorMatch colorMatcher = new ColorMatch();
  private final Color kBlueTarget = ColorMatch.makeColor(0.107, 0.385, 0.509);
  private final Color kGreenTarget = ColorMatch.makeColor(0.169, 0.597, 0.234);
  private final Color kRedTarget = ColorMatch.makeColor(0.586, 0.328, 0.085);
  private final Color kYellowTarget = ColorMatch.makeColor(0.344, 0.554, 0.101);
  private String targetColor, colorString;
  private int delay;
  boolean a;
  int color = 0;
  String [] colorArray = {"Blue", "Red", "Green", "Yellow"};
  boolean oldRB = false, oldLB = false;
  boolean chooseColorMode = false;

  public turnOnColor(Lollipop lollipop) {
    m_lollipop = lollipop;
    addRequirements(m_lollipop);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_lollipop.setCylinderLollipop(true);
    colorMatcher.addColorMatch(kBlueTarget);
    colorMatcher.addColorMatch(kGreenTarget);
    colorMatcher.addColorMatch(kRedTarget);
    colorMatcher.addColorMatch(kYellowTarget);
    targetColor = null;
    a = false;
    delay = 0;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    Color detectedColor = colorSensor.getColor();
    ColorMatchResult match = colorMatcher.matchClosestColor(detectedColor);
    if (match.color == kBlueTarget) {
      colorString = "Blue";
    } else if (match.color == kRedTarget) {
      colorString = "Red";
    } else if (match.color == kGreenTarget) {
      colorString = "Green";
    } else if (match.color == kYellowTarget) {
      colorString = "Yellow";
    } else {
      colorString = "Unknown";
    }

    SmartDashboard.putString("color", colorString);

    if(Robot.m_robotContainer.getDriverButton(Constants.BUTTON_RIGHT)){
      m_lollipop.setMotorLollipop(Constants.SPEED_MOTOR_SPINNER);
    }

    boolean ButtonRB = Robot.m_robotContainer.getDriverButton(Constants.BUTTON_RB);
    boolean ButtonLB = Robot.m_robotContainer.getDriverButton(Constants.BUTTON_LB);
    if(ButtonRB){
      if(ButtonRB != oldRB){
        ++ color;
        if(color == 4){
          color = 0;
        }
      }
    }
    if(ButtonLB){
      if(ButtonLB !=oldLB){
        -- color;
        if(color == -1){
          color = 3;
        } 
      }
    }
    oldRB = ButtonRB;
    oldLB = ButtonLB;
    targetColor = colorArray[color];
    SmartDashboard.putString("targetColor", targetColor);
    if(Robot.m_robotContainer.getDriverButton(Constants.BUTTON_X)){
      chooseColorMode = true;
      m_lollipop.setMotorLollipop(Constants.SPEED_MOTOR_SPINNER);
      delay = 0;
    }
    if(chooseColorMode){
      if(colorString == targetColor){
        delay += 1;
        if(delay >= 20){
          m_lollipop.setMotorLollipop(0);
          a = true;
          chooseColorMode = false;
        }
      }
    } else {
      m_lollipop.setMotorLollipop(Robot.m_robotContainer.getDriverButton(Constants.BUTTON_B) ? Constants.SPEED_MOTOR_SPINNER : 0);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_lollipop.setCylinderLollipop(false);
    m_lollipop.setMotorLollipop(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return Robot.m_robotContainer.getDriverButton(Constants.BUTTON_BACK);
  }
}
