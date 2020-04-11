package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DataSubsystem;

public class RobotContainer {

    public static DataSubsystem dataSubsystem = new DataSubsystem();

    public RobotContainer() {

    }

    public Command getAutonomousCommand() {
        return null;
    }

}