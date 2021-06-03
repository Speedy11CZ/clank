package net.hashsploit.clank.server.medius.test.packets;

import net.hashsploit.clank.server.medius.MediusConstants;
import net.hashsploit.clank.server.medius.MediusMessageType;
import net.hashsploit.clank.server.medius.test.MediusPacket;
import net.hashsploit.clank.server.medius.test.NetInput;
import net.hashsploit.clank.utils.Utils;

import java.io.IOException;

public class MediusAccountGetIdRequest extends MediusPacket {

    private final byte[] messageID = new byte[MediusConstants.MESSAGEID_MAXLEN.value];
    private final byte[] sessionKey = new byte[MediusConstants.SESSIONKEY_MAXLEN.value];
    private final String accountName;

    public MediusAccountGetIdRequest(NetInput netInput) throws IOException {
        super(MediusMessageType.AccountGetId, netInput);
        netInput.readBytes(messageID);
        netInput.readBytes(sessionKey);
        accountName = netInput.readString(MediusConstants.ACCOUNTNAME_MAXLEN);
    }

    public byte[] getMessageID() {
        return messageID;
    }

    public String getAccountName() {
        return accountName;
    }

    @Override
    public String toString() {
        return "MediusAccountGetIdRequest{" +
                "messageID=" + Utils.bytesToHex(messageID) +
                ", sessionKey=" + Utils.bytesToHex(sessionKey) +
                ", accountName='" + accountName + '\'' +
                '}';
    }
}
