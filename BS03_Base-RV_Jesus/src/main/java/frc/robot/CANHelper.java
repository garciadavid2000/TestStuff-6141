/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.hal.CANData;
import edu.wpi.first.wpilibj.CAN;

/**
 * Add your docs here.
 */
public class CANHelper {
    public CAN CANSender;
    public CANData data;
    public CANHelper(int deviceID) {
        CANSender = new CAN(deviceID);
        data = new CANData();
    }

    public void writeData(byte[] message, int id) {
        CANSender.writePacketRepeating(message, id, 10);
    }

    public byte[] readData(int id) {
        boolean state = CANSender.readPacketLatest(id, this.data); 
        return this.data.data;
    } 
}