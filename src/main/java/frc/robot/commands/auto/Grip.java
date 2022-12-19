package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.trajectory.TrapezoidProfile;
//WPI imports
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Globals;
//RobotContainer import
import frc.robot.RobotContainer;
import frc.robot.subsystems.Arm;
//Subsystem imports


/**
 * SimpleDrive class
 * <p>
 * This class drives a motor 
 */
public class Grip extends CommandBase
{
    //Grab the subsystem instance from RobotContainer
    private final static Arm m_arm = RobotContainer.m_arm;
    private double dT = 0.02;
    private boolean m_endFlag = false;
    private TrapezoidProfile.Constraints m_constraints;
    private TrapezoidProfile.State m_goal;
    private TrapezoidProfile.State m_setpoint;
    private TrapezoidProfile m_profile;
    private int isOpen;
    private double targetAngle;
    private int[][] itemGripperSizes = {
        {120,60}, // Dettol
        {120,50}, // Jagabee
        {120,60}, // Coke
    };
    /**
     * This command opens or closes the gripper
     * <p>
     * 
     * @param open - 1 for open or 0 for close
     * @param maxSpeed - max speed of servo
     */
    //This move the robot a certain distance following a trapezoidal speed profile.
    public Grip(int pos, double maxSpeed)
    {
        
        isOpen = pos;
        m_constraints = new TrapezoidProfile.Constraints(maxSpeed, maxSpeed);
        
    }

    /**
     * Runs before execute
     */
    @Override
    public void initialize()
    {   
        targetAngle = itemGripperSizes[Globals.curItem][isOpen];
        double start_pos = m_arm.getServoAngle2();
        
        m_goal = new TrapezoidProfile.State(targetAngle, 0);
        m_setpoint = new TrapezoidProfile.State(start_pos, 0);
        m_endFlag = false;
    }
    /**
     * Condition to end speed profile
     */
    public boolean endCondition()
    {
        return false;
    }
    /**
     * Called continously until command is ended
     */
    @Override
    public void execute()
    {
        //Create a new profile to calculate the next setpoint(speed) for the profile
        m_profile = new TrapezoidProfile(m_constraints, m_goal, m_setpoint);
        
        m_setpoint = m_profile.calculate(dT);
        
        m_arm.setServoAngle2(m_setpoint.position);
        if (m_profile.isFinished(dT)) {
            //distance reached End the command
            //m_arm.setServoAngle0( m_goal.position);
            m_endFlag = true;
        }
        
    }

    /**
     * Called when the command is told to end or is interrupted
     */
    @Override
    public void end(boolean interrupted)
    {

    }

    /**
     * Creates an isFinished condition if needed
     */
    @Override
    public boolean isFinished()
    {
        return m_endFlag;
    }
    

}