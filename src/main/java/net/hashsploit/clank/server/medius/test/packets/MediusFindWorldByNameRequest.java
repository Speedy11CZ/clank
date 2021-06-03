package net.hashsploit.clank.server.medius.test.packets;

import net.hashsploit.clank.server.medius.MediusConstants;
import net.hashsploit.clank.server.medius.MediusMessageType;
import net.hashsploit.clank.server.medius.test.MediusPacket;
import net.hashsploit.clank.server.medius.test.NetInput;
import net.hashsploit.clank.utils.Utils;

import java.io.IOException;

public class MediusFindWorldByNameRequest extends MediusPacket {

    private final byte[] messageID = new byte[MediusConstants.MESSAGEID_MAXLEN.value];
    private final byte[] sessionKey = new byte[MediusConstants.SESSIONKEY_MAXLEN.value];
    private final String worldName;
    private final int worldType;

    public MediusFindWorldByNameRequest(NetInput netInput) throws IOException {
        super(MediusMessageType.FindWorldByName, netInput);
        netInput.readBytes(messageID);
        netInput.readBytes(sessionKey);
        worldName = netInput.readString(MediusConstants.WORLDNAME_MAXLEN);
        netInput.skipBytes(2);
        worldType = netInput.readInt();
    }

    public byte[] getMessageID() {
        return messageID;
    }

    public String getWorldName() {
        return worldName;
    }

    public int getWorldType() {
        return worldType;
    }

    @Override
    public String toString() {
        return "MediusFindWorldByNameRequest{" +
                "messageID=" + Utils.bytesToHex(messageID) +
                ", sessionKey=" + Utils.bytesToHex(sessionKey) +
                ", worldName='" + worldName + '\'' +
                ", worldType=" + worldType +
                '}';
    }
}
