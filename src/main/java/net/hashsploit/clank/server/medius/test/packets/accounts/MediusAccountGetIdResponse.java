package net.hashsploit.clank.server.medius.test.packets.accounts;

import net.hashsploit.clank.server.medius.MediusCallbackStatus;
import net.hashsploit.clank.server.medius.MediusMessageType;
import net.hashsploit.clank.server.medius.test.NetOutput;
import net.hashsploit.clank.server.medius.test.MediusPacket;
import net.hashsploit.clank.utils.Utils;

import java.io.IOException;

public class MediusAccountGetIdResponse extends MediusPacket {

    private final byte[] messageID;
    private final int accountID;
    private final MediusCallbackStatus callbackStatus;

    public MediusAccountGetIdResponse(byte[] messageID, int accountID, MediusCallbackStatus callbackStatus) {
        super(MediusMessageType.AccountGetIdResponse);
        this.messageID = messageID;
        this.accountID = accountID;
        this.callbackStatus = callbackStatus;
    }

    public MediusAccountGetIdResponse(byte[] messageID, MediusCallbackStatus callbackStatus) {
        this(messageID, 0, callbackStatus);
    }

    @Override
    public void writePacket(NetOutput netOutput) throws IOException {
        netOutput.writeBytes(messageID);
        netOutput.skipBytes(3);
        netOutput.writeInt(accountID);
        netOutput.writeObject(callbackStatus);
    }

    @Override
    public String toString() {
        return "MediusAccountGetIdResponse{" +
                "messageID=" + Utils.bytesToHex(messageID) +
                ", accountID=" + accountID +
                ", callbackStatus=" + callbackStatus +
                '}';
    }
}
