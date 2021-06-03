package net.hashsploit.clank.server.medius.test.packets;

import net.hashsploit.clank.server.medius.MediusConstants;
import net.hashsploit.clank.server.medius.MediusMessageType;
import net.hashsploit.clank.server.medius.test.MediusPacket;
import net.hashsploit.clank.server.medius.test.NetInput;
import net.hashsploit.clank.utils.Utils;

import java.io.IOException;
public class MediusChannelInfoRequest extends MediusPacket {

    private final byte[] messageID = new byte[MediusConstants.MESSAGEID_MAXLEN.value];
    private final byte[] sessionKey = new byte[MediusConstants.SESSIONKEY_MAXLEN.value];
    private final int worldID;

    public MediusChannelInfoRequest(NetInput netInput) throws IOException {
        super(MediusMessageType.ChannelInfo, netInput);
        netInput.readBytes(messageID);
        netInput.readBytes(sessionKey);
        netInput.skipBytes(2);
        worldID = netInput.readInt();
    }

    public byte[] getMessageID() {
        return messageID;
    }

    public int getWorldID() {
        return worldID;
    }

    @Override
    public String toString() {
        return "MediusChannelInfoRequest{" +
                "messageID=" + Utils.bytesToHex(messageID) +
                ", sessionKey=" + Utils.bytesToHex(sessionKey) +
                ", worldID=" + worldID +
                '}';
    }
}
