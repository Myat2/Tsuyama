package frc.robot.commands.tele;

import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Globals;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.OmniDrive;
import frc.robot.subsystems.Sensor;

//This command will be run during teleop mode
public class TeleCmd extends CommandBase
{
    /**
     * Bring in Subsystem and Gamepad code
     */
    private final OmniDrive m_omnidrive;
    private final Sensor m_sensor;
    private final Arm m_arm;
    private final OI m_oi;
    private final int[] cvModes = {-2,-1,0,1,2,3,4,5};
    private int cvSelector = 0;


    private double permX = 0.3;
    private double permY = 0.3; 
    private double prevX;
    private double prevY;
    Translation2d pos;
    Translation2d prevpos;
    /**
     * Constructor
     */
    public TeleCmd(OmniDrive omnidrive, OI oi, Arm arm)
    {
        m_omnidrive = RobotContainer.m_omnidrive;
        m_sensor = RobotContainer.m_sensor;
        m_oi = RobotContainer.m_oi;
        m_arm = RobotContainer.m_arm;
        addRequirements(m_omnidrive); //add the drive subsystem as a requirement 
		//addRequirements(m_menu); 
    }

    /**
     * Code here will run once when the command is called for the first time
     */
    @Override
    public void initialize()
    {
        prevX = m_arm.getSliderX();
        prevY = m_arm.getSliderY();
        cvSelector = 0;
    }

    /**
     * Code here will run continously every robot loop until the command is stopped
     */
    @Override
    public void execute()
    {
        /**
         * Get Joystick data
         */
        //Right stick for X-Y control
        //Left stick for W (rotational) control
        double x = m_oi.getRightDriveX();
        double y = -m_oi.getRightDriveY();//Down is positive. Need to negate

        double w = -m_oi.getLeftDriveX(); //X-positive is CW. Need to negate

        boolean open = m_oi.getDriveAButton();
        boolean cvModeSW = m_oi.getDriveBButton();
        boolean lower = m_oi.getDriveRightBumper();
       
        
        //Get other buttons?
        // m_omnidrive.setRobotSpeedXYW(x*0.4, y*0.4, w*Math.PI/2);


        // permX = permX + x*0.1;
        // permY = permY + y*0.1;

        // if controller change then arm move, else check if slider change then if nothing change maintain position.
        if (x != 0 || y != 0)
        {
            pos = new Translation2d(m_arm.getSliderX() + m_oi.getRightDriveX() , m_arm.getSliderY() -m_oi.getRightDriveY() );
        }
        else if (prevX != m_arm.getSliderX() || prevY != m_arm.getSliderY() )
        {
            pos = new Translation2d(m_arm.getSliderX(), m_arm.getSliderY());
        }
        else
        {
            pos = prevpos;
        }

        pos = new Translation2d(m_arm.getSliderX(), m_arm.getSliderY());
        m_arm.setArmPos(pos);


    

        m_arm.setCameraAngle(m_arm.getSliderCamera());
        m_arm.setGripper(m_arm.getSliderGripper());
        prevpos = pos;
        prevX = m_arm.getSliderX();
        prevY = m_arm.getSliderY();
    }

    /**
     * When the comamnd is stopped or interrupted this code is run
     * <p>
     * Good place to stop motors in case of an error
     */
    @Override
    public void end(boolean interrupted)
    {
        m_omnidrive.setMotorOut012(0, 0, 0);
    }

    /**
     * Check to see if command is finished
     */
    @Override
    public boolean isFinished()
    {
        return false;
    }
}