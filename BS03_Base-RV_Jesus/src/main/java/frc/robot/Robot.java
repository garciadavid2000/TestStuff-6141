package frc.robot;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.CAN;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.shuffleboard.SimpleWidget;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.AutonomousRoutine;
import frc.robot.subsystems.*;

public class Robot extends TimedRobot {

  public static Drivetrain m_drive;
  public static OI m_oi;
  public static Leds m_Leds;
  public static CommandGroup routine;
  public static Claw m_claw;
  public static Tower m_tower;
  public static ShuffleboardTab tab_autonomo, tab_vision, tab_virtual;
  public static NetworkTableEntry entryAngulo, entryDistance, entryTolerance,
                                   entryFinish, entryAngle,
                                   entryDiff, entryUnidade, entryVirtualAngle, 
                                   entryVirtualDistance;

  @Override
  public void robotInit() {

    RobotMap.CANSensores = new CANHelper(RobotMap.CAN_SENSORES_DEVICEID);
    RobotMap.CANControladores = new CANHelper(RobotMap.CAN_CONTROLADORES_DEVICEID);

    m_drive = new Drivetrain();
    m_claw = new Claw();
    m_Leds = new Leds();
    m_tower = new Tower();

    //Deve sempre ser o último, possível problema de inicialização
    m_oi = new OI();
    routine = new AutonomousRoutine();

    tab_autonomo = Shuffleboard.getTab("Autonomo");
    tab_vision = Shuffleboard.getTab("Vision");
    tab_virtual = Shuffleboard.getTab("Virtual");
    entryDiff = tab_vision.add("Difference", 0.0).getEntry();
    entryAngulo = tab_autonomo.add("Angulo", 0).getEntry();
    entryDistance = tab_autonomo.add("Distance", 0).getEntry();
    entryTolerance = tab_autonomo.add("Tolerance", 0).getEntry();
    entryVirtualDistance = tab_virtual.add("Distance", 0.0).getEntry();
    entryVirtualAngle = tab_virtual.add("Angulo", 0.0).getEntry();
    entryFinish = tab_autonomo.add("Acabou", 0).getEntry();
  }

  @Override
  public void robotPeriodic() {
  }

  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void autonomousInit() {
    SmartDashboard.putNumber("fim", 0);
    routine.start();
  }

  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
    
  }

  @Override
  public void teleopInit() {
    routine.cancel();
  }

  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void testInit(){
    Robot.m_drive.navX.reset();
  }

  @Override
  public void testPeriodic() {
  }
}