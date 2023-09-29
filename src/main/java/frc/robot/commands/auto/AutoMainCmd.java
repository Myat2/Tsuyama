package frc.robot.commands.auto;



import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ProxyScheduleCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.OmniDrive;
import frc.robot.subsystems.Sensor;
import frc.robot.subsystems.Vision;
import frc.robot.Astar.Layout;

/**
 * DriveMotor class
 * <p>
 * This class creates the inline auto command to drive the motor
 */
public class AutoMainCmd extends SequentialCommandGroup
{   
    int count = 0;
    double temp;


	public AutoMainCmd() 
    {
        
        
        super
        (
            new MoveArm(new Translation2d(0.3,0.3), 0.1),
            new WaitCommand(2),
            new MoveArm(new Translation2d(0.3,0.4), 0.1),
            new WaitCommand(2),
            new MoveArm(new Translation2d(0.3,0.2), 0.1),
            new WaitCommand(2),
            new MoveArm(new Translation2d(0.3,0.3), 0.1)
        );
            
    }
    
}