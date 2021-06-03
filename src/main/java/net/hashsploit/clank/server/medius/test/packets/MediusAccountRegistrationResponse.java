package net.hashsploit.clank.server.medius.test.packets;

import net.hashsploit.clank.server.medius.MediusCallbackStatus;
import net.hashsploit.clank.server.medius.MediusMessageType;
import net.hashsploit.clank.server.medius.test.MediusPacket;
import net.hashsploit.clank.server.medius.test.NetOutput;
import net.hashsploit.medius.crypto.Utils;

import java.io.IOException;

public class MediusAccountRegistrationResponse extends MediusPacket {

    private final byte[] messageID;
    private final MediusCallbackStatus callbackStatus;
    private final int accountID;

    public MediusAccountRegistrationResponse(byte[] messageID, MediusCallbackStatus callbackStatus, int accountID) {
        super(MediusMessageType.AccountRegistrationResponse);
        this.messageID = messageID;
        this.callbackStatus = callbackStatus;
        this.accountID = accountID;
    }

    @Override
    public void writePacket(NetOutput netOutput) throws IOException {
        netOutput.writeBytes(messageID);
        netOutput.skipBytes(3);
        netOutput.writeObject(callbackStatus);
        netOutput.writeInt(accountID);
    }

    @Override
    public String toString() {
        return "MediusAccountRegistrationResponse{" +
                "messageID=" + Utils.bytesToHex(messageID) +
                ", callbackStatus=" + callbackStatus +
                ", accountID=" + accountID +
                '}';
    }
}
