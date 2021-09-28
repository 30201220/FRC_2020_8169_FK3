package frc.robot;

public class Tools{

  public Tools(){
  }

  public static double range(final double value,final double max,final double min){
    if(value >= max)
      return max;
    else if(value <= min)
      return min;
    else 
      return value;
  }
}