/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.revrobotics.CANSparkMaxLowLevel.MotorType;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

  // CAN ID

	public static final int MOTOR_LEFT_1_ID = 2;//2
	public static final int MOTOR_LEFT_2_ID = 1;//1
	public static final int MOTOR_RIGHT_1_ID = 4;//4
  public static final int MOTOR_RIGHT_2_ID = 3;//3

  public static final int MOTOR_INTAKE_ID = 5;

  public static final int MOTOR_SHOOTER_FLY_LEAT_ID = 7;
  public static final int MOTOR_SHOOTER_FLY_RIGHTL_ID = 6;

  public static final int MOTOR_SHOOTER_2_ID = 8;

  public static final int MOTOR_SHOOTER_HEADER_ID = 9;
  public static final int MOTOR_SHOOTER_FISHER_ID = 10;

  public static final int MOTOR_CLIMBER_L = 9;
  public static final int MOTOR_CLIMBER_R = 10;

  public static final int MOTOR_LOLLIPOP_ID = 4;

  // Cylinder ID

  public static final int CYLINDER_INTAKE_ID = 0;
  public static final int CYLINDER_CLIMBER_ID = 1;
  public static final int CYLINDER_LOLLIPOP_ID = 2; 

  // DIO

  public static final int ENCODER_SHOOTER_HEADER_PIN_A = 0;
  public static final int ENCODER_SHOOTER_HEADER_PIN_B = 1;
	

	// Xbox
  /********************************************************* */
  public static final int XBOX_DRIVER_ID = 1;
  public static final int XBOX_OPERATOR_ID = 0;
  
  public static final int LEFT_STICK_X = 0;
  public static final int LEFT_STICK_Y = 1;
  public static final int LEFT_TRIGGER = 2;
  public static final int RIGHT_TRIGGER = 3;
  public static final int RIGHT_STICK_X = 4;
  public static final int RIGHT_STICK_Y = 5;

  public static final int BUTTON_A = 1;
  public static final int BUTTON_B = 2;
  public static final int BUTTON_X = 3;
  public static final int BUTTON_Y = 4;
  public static final int BUTTON_LB = 5;
  public static final int BUTTON_RB = 6;
  public static final int BUTTON_BACK = 7;
  public static final int BUTTON_START = 8;
  public static final int BUTTON_LEFT = 9;
  public static final int BUTTON_RIGHT = 10;

  public static final int DPAD_UP = 0;
  public static final int DPAD_RIGHT = 90;
  public static final int DPAD_DOWN = 180;
  public static final int DPAD_LEFT = 270;
  /********************************************************* */

  // Speed Limit

  public static final double TURNING_RATE = 0.4;
  public static final double N_MOTOR_INTAKE_SPEED = -1;
  public static final double N_MOTOR_SHOOTER2_SPEED = -0.15;
  public static final double N_MOTOR_HEADER_SPEED = 0.5;

  public static final double TOUCHBOX_RATIO = 10.75;
  public static final double HIGRIP_WHEEL = 478.536;
  public static final double SPEED_MOTOR_SPINNER = 0.5;
    
  // unDefind

}
