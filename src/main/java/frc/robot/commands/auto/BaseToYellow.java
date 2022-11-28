package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.geometry.Transform2d;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.RobotContainer;
import frc.robot.commands.auto.MoveRobot;
import frc.robot.commands.auto.MoveRobotSense;
import frc.robot.subsystems.Sensor;

public class BaseToYellow extends SequentialCommandGroup {
    private final static Sensor m_sensor = RobotContainer.m_sensor;

    public BaseToYellow() {
        super(
                new MoveRobot(1, 0.6, 0, 0, 5),
                new MoveRobotSense(0, -10, 0, 0, 0.25, () -> m_sensor.getCobraTotal() > 3500),
                // new MoveRobot(0, -1.1 + 0.3, 0, 0, 5),
                new MoveRobotSense(1, 1, 0, 0, 5, () -> m_sensor.getIRDistance() < 10),
                new Pick());
    }
}
