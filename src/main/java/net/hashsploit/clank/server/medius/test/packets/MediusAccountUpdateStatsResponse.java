package net.hashsploit.clank.server.medius.test.packets;

import net.hashsploit.clank.server.medius.MediusCallbackStatus;
import net.hashsploit.clank.server.medius.MediusMessageType;
import net.hashsploit.clank.server.medius.test.MediusPacket;
import net.hashsploit.clank.server.medius.test.NetOutput;
import net.hashsploit.clank.utils.Utils;

import java.io.IOException;

public class MediusAccountUpdateStatsResponse extends MediusPacket {

    private final byte[] messageID;
    private final MediusCallbackStatus callbackStatus;

    public MediusAccountUpdateStatsResponse(byte[] messageID, MediusCallbackStatus callbackStatus) {
        super(MediusMessageType.AccountUpdateStatsResponse);
        this.messageID = messageID;
        this.callbackStatus = callbackStatus;
    }

    @Override
    public void writePacket(NetOutput netOutput) throws IOException {
        netOutput.writeBytes(messageID);
        netOutput.skipBytes(3);
        netOutput.writeObject(callbackStatus);
    }

    @Override
    public String toString() {
        return "MediusAccountUpdateStatsResponse{" +
                "messageID=" + Utils.bytesToHex(messageID) +
                ", callbackStatus=" + callbackStatus +
                '}';
    }
}
