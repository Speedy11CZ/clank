package net.hashsploit.clank.server.medius.test.packets;

import net.hashsploit.clank.server.medius.MediusConstants;
import net.hashsploit.clank.server.medius.MediusMessageType;
import net.hashsploit.clank.server.medius.objects.MediusAccountType;
import net.hashsploit.clank.server.medius.test.MediusPacket;
import net.hashsploit.clank.server.medius.test.NetInput;
import net.hashsploit.clank.utils.Utils;

import java.io.IOException;

public class MediusAccountRegistrationRequest extends MediusPacket {

    private final byte[] messageID = new byte[MediusConstants.MESSAGEID_MAXLEN.value];
    private final byte[] sessionKey = new byte[MediusConstants.SESSIONKEY_MAXLEN.value];
    private final MediusAccountType accountType;
    private final String username;
    private final String password;

    public MediusAccountRegistrationRequest(NetInput netInput) throws IOException {
        super(MediusMessageType.AccountRegistration, netInput);
        netInput.readBytes(messageID);
        netInput.readBytes(sessionKey);
        accountType = MediusAccountType.getTypeFromValue(netInput.readInt());
        username = netInput.readString(MediusConstants.USERNAME_MAXLEN);
        password = netInput.readString(MediusConstants.PASSWORD_MAXLEN);
    }

    public byte[] getMessageID() {
        return messageID;
    }

    public MediusAccountType getAccountType() {
        return accountType;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "MediusAccountRegistrationRequest{" +
                "messageID=" + Utils.bytesToHex(messageID) +
                ", sessionKey=" + Utils.bytesToHex(sessionKey) +
                ", accountType=" + accountType +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
