package net.hashsploit.clank.server.medius.test.packets;

import net.hashsploit.clank.server.medius.MediusConstants;
import net.hashsploit.clank.server.medius.MediusMessageType;
import net.hashsploit.clank.server.medius.test.MediusPacket;
import net.hashsploit.clank.server.medius.test.NetInput;
import net.hashsploit.clank.utils.Utils;

import java.io.IOException;

public class MediusCreateClanRequest extends MediusPacket {

    private final byte[] messageID = new byte[MediusConstants.MESSAGEID_MAXLEN.value];
    private final byte[] sessionKey = new byte[MediusConstants.SESSIONKEY_MAXLEN.value];
    private final int applicationID;
    private final String clanName;

    public MediusCreateClanRequest(NetInput netInput) throws IOException {
        super(MediusMessageType.CreateClan, netInput);
        netInput.readBytes(messageID);
        netInput.readBytes(sessionKey);
        applicationID = netInput.readInt();
        clanName = netInput.readStringClean(MediusConstants.CLANNAME_MAXLEN);
    }

    public byte[] getMessageID() {
        return messageID;
    }

    public int getApplicationID() {
        return applicationID;
    }

    public String getClanName() {
        return clanName;
    }

    @Override
    public String toString() {
        return "MediusCreateClanRequest{" +
                "messageID=" + Utils.bytesToHex(messageID) +
                ", sessionKey=" + Utils.bytesToHex(sessionKey) +
                ", applicationID=" + applicationID +
                ", clanName='" + clanName + '\'' +
                '}';
    }
}
