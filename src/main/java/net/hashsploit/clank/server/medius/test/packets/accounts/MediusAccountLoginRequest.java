package net.hashsploit.clank.server.medius.test.packets.accounts;

import net.hashsploit.clank.server.medius.MediusConstants;
import net.hashsploit.clank.server.medius.MediusMessageType;
import net.hashsploit.clank.server.medius.test.MediusPacket;
import net.hashsploit.clank.server.medius.test.NetInput;
import net.hashsploit.clank.utils.Utils;

import java.io.IOException;

public class MediusAccountLoginRequest extends MediusPacket {

    private final byte[] messageID = new byte[MediusConstants.MESSAGEID_MAXLEN.value];
    private final byte[] sessionKey = new byte[MediusConstants.SESSIONKEY_MAXLEN.value];
    private final String username;
    private final String password;

    public MediusAccountLoginRequest(NetInput netInput) throws IOException {
        super(MediusMessageType.AccountLogin, netInput);
        netInput.readBytes(messageID);
        netInput.readBytes(sessionKey);
        username = netInput.readString(MediusConstants.USERNAME_MAXLEN);
        password = netInput.readString(MediusConstants.PASSWORD_MAXLEN);
    }

    public byte[] getMessageID() {
        return messageID;
    }

    public byte[] getSessionKey() {
        return sessionKey;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "MediusAccountLoginRequest{" +
                "messageID=" + Utils.bytesToHex(messageID) +
                ", sessionKey=" + Utils.bytesToHex(sessionKey) +
                ", username='" + username + '\'' +
                ", password=########'" + '\'' +
                '}';
    }
}
