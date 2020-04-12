/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import java.io.IOException;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.server.ServerListenerThread;

public class DataCommand extends CommandBase {
  /**
   * Creates a new IntakeCommand.
   */
  public DataCommand() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.dataSubsystem);
  }

  // Called when the command is initially scheduled.
  public ServerListenerThread serverThread;
  @Override
  public void initialize() {
    		
		System.out.println("Server starting...");



		try {
			serverThread = new ServerListenerThread(2717);
			serverThread.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double a = RobotContainer.dataSubsystem.getAnalogData(1);
    String b = RobotContainer.dataSubsystem.getCanMessage();
   serverThread.setHtml("<html><head><title>Test</title></head><body><h1>" + "Analog: " + a + " CAN: "+ b +"</h1></body></html>");
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
