package frc.robot.commands.tele;

import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Globals;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.OmniDrive;
import frc.robot.subsystems.Sensor;

// This command will be run during teleop mode
public class TeleCmd extends CommandBase
{
    /**
     * Bring in Subsystem and Gamepad code
     */
    private final OmniDrive m_omnidrive;
    private final Sensor m_sensor;
    private final Arm m_arm;
    private final OI m_oi;

    Double joyXpos = 0.3;
    Double joyYpos = 0.3; //to have an intermediate variable for joystick control
    Translation2d pos;

    /**
     * Constructor
     */
    public TeleCmd(OmniDrive omnidrive, OI oi, Arm arm)
    {
        m_omnidrive = RobotContainer.m_omnidrive;
        m_sensor = RobotContainer.m_sensor;
        m_oi = RobotContainer.m_oi;
        m_arm = RobotContainer.m_arm;
        addRequirements(m_omnidrive);
    }

    /**
     * Code here will run once when the command is called for the first time
     */
    @Override
    public void initialize()
    {
    }

    /**
     * Code here will run continuously every robot loop until the command is stopped
     */
    @Override
    public void execute()
    {
        /**
         * Get Joystick data
         */
        // Right stick for X-Y control
        // Left stick for W (rotational) control
     

        double x = m_oi.getRightDriveX();
        double y = -m_oi.getRightDriveY(); // Down is positive. Need to negate

        double w = -m_oi.getLeftDriveX(); // X-positive is CW. Need to negate

        //arm up and down (y axis)
        double up = m_oi.getDriveRightTrigger();
        double down = m_oi.getDriveLeftTrigger();

        //arm preset positions
        boolean btnY = m_oi.getDriveYButton();
        boolean btnX = m_oi.getDriveXButton();
        boolean btnA = m_oi.getDriveAButton();
        boolean btnB = m_oi.getDriveBButton();

        // Handle button B press to move the arm to (0.3, 0.3)
        if (btnY) {
            joyXpos = 0.35;
            joyYpos = 0.30;
        }
        else if (btnX){
            joyXpos = 0.25;
            joyYpos = 0.25;
        }
        else if (btnA){
            joyXpos = 0.20;
            joyYpos = 0.20;
        }
        else if (btnB){
            joyXpos = 0.30;
            joyYpos = 0.30;
        }
        else if (up > 0.1){
            joyYpos = joyYpos - 0.005;
        }
        else if (down > 0.1){
            joyYpos = joyYpos + 0.005;
        }
       else {
            // Joystick control for arm position
            joyXpos = joyXpos + (x * 0.004);
            // joyYpos = joyYpos + (y * 0.004);
        }

        pos = new Translation2d(joyXpos, joyYpos);
        new MoveArm(pos, 12.0).schedule();

        // Unrelated to arm control, leave it as is
        m_arm.setCameraAngle(m_arm.getSliderCamera());
        m_arm.setGripper(m_arm.getSliderGripper());
    }

    /**
     * When the command is stopped or interrupted, this code is run
     */
    @Override
    public void end(boolean interrupted)
    {
        // Stop the drivetrain motors
        m_omnidrive.setMotorOut012(0, 0, 0);
    }

    /**
     * Check to see if the command is finished
     */
    @Override
    public boolean isFinished()
    {
        return false;
    }
}
