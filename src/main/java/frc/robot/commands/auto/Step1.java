package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;

public class Step1 extends SequentialCommandGroup {
    public Step1(){
        super(
          new MoveArm(new Translation2d(0.2,0.1), 0.1),
          new WaitCommand(10),
          new MoveArm(new Translation2d(0.3,0.3), 0.1)

        );
    }
    
}