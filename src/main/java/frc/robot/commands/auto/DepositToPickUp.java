package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.RobotContainer;
import frc.robot.commands.auto.MoveRobot;
import frc.robot.commands.auto.MoveRobotSense;
import frc.robot.subsystems.Sensor;

public class DepositToPickUp extends SequentialCommandGroup {
    private final static Sensor m_sensor = RobotContainer.m_sensor;
    private static double maxSpeed1 = 0.2;
    private static double maxSpeed2 = 0.5;
    public DepositToPickUp() {
        super(
            new Place(),
            new MoveRobotSense(1, -10, 0, 0, maxSpeed1, ()-> m_sensor.getFrontIRDistance() > 70),
            new MoveRobotSense(0, 5, 0, 0, maxSpeed1, ()-> m_sensor.getFrontIRDistance() < 50),
            new MoveRobotSense(1, 5, 0, 0, maxSpeed1,() -> m_sensor.getFrontIRDistance() < 15),
            new MoveRobotSense(0, 10, 0, 0, maxSpeed1, () -> m_sensor.getCobraTotal() > 3500),
            new MoveRobotSense(1, 1, 0, 0, maxSpeed1, () -> m_sensor.getFrontIRDistance() < 10)
                );
    }
}