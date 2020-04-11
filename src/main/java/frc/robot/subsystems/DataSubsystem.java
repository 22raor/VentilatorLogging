/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.Arrays;

import edu.wpi.first.hal.can.CANExceptionFactory;
import edu.wpi.first.hal.can.CANJNI;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DataSubsystem extends SubsystemBase {
      /* Check the datasheets for your device for the arbitration IDs of the
        messages you want to send.  By convention, this is a bitstring
        containing the model number, manufacturer number, and api number. */
        private static final int MESSAGE1_ARB_ID = 0x1;
        private static final int MESSAGE2_ARB_ID = 0x2;
    
        /*  Device ID, from 0 to 63. */
        private static final int DEVICE_NUMBER = 1;
    
        private IntBuffer status = ByteBuffer.allocateDirect(4).asIntBuffer();
        private IntBuffer messageId = ByteBuffer.allocateDirect(4).asIntBuffer();
        private ByteBuffer data = ByteBuffer.allocateDirect(8);
        private ByteBuffer timestamp = ByteBuffer.allocate(4);

  public double getAnalogData(int port){
    try{ 
      AnalogInput analogDevice = new AnalogInput(port);
      return analogDevice.getVoltage();
    } catch (Exception e){
      return -1;
    }

  }

  public String getCanMessage() {
              /* To receive a message, put the message ID you're looking for in this
            buffer.  CANJNI...ReceiveMessage  will not block waiting for it,
            but just return null if it hasn't been received yet. */
            messageId.clear();
            messageId.put(0, MESSAGE2_ARB_ID | DEVICE_NUMBER);
    
            status.clear();
            byte[] data = CANJNI.FRCNetCommCANSessionMuxReceiveMessage(
                    messageId,
                    0x0,
                    timestamp
            );
    
            if (data != null) {
                CANExceptionFactory.checkStatus(status.get(0), MESSAGE1_ARB_ID);
               return "Received a message: " + new String(data);
            }
            return "empty";
  }
  
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
