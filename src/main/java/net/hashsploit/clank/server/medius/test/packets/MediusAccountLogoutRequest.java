package net.hashsploit.clank.server.medius.test.packets;

import net.hashsploit.clank.server.medius.MediusConstants;
import net.hashsploit.clank.server.medius.MediusMessageType;
import net.hashsploit.clank.server.medius.test.MediusPacket;
import net.hashsploit.clank.server.medius.test.NetInput;
import net.hashsploit.clank.utils.Utils;

import java.io.IOException;

public class MediusAccountLogoutRequest extends MediusPacket {

    private final byte[] messageID = new byte[MediusConstants.MESSAGEID_MAXLEN.value];
    private final byte[] sessionKey = new byte[MediusConstants.SESSIONKEY_MAXLEN.value];

    public MediusAccountLogoutRequest(NetInput netInput) throws IOException {
        super(MediusMessageType.AccountLogout, netInput);
        netInput.readBytes(messageID);
        netInput.readBytes(sessionKey);
    }

    public byte[] getMessageID() {
        return messageID;
    }

    @Override
    public String toString() {
        return "MediusAccountLogoutRequest{" +
                "messageID=" + Utils.bytesToHex(messageID) +
                ", sessionKey=" + Utils.bytesToHex(sessionKey) +
                '}';
    }
}
