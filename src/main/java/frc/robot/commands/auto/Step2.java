package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj.geometry.Translation2d;
public class Step2 extends SequentialCommandGroup {
    public Step2(){
        super(
          new MoveArm(new Translation2d(0.3,0.2), 0.1),
          new WaitCommand(10),
          new MoveArm(new Translation2d(0.3,0.3), 0.1)
        );
    }
    
}