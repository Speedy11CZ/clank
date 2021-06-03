package net.hashsploit.clank.server.medius.test.packets;

import net.hashsploit.clank.server.medius.MediusCallbackStatus;
import net.hashsploit.clank.server.medius.MediusMessageType;
import net.hashsploit.clank.server.medius.test.MediusPacket;
import net.hashsploit.clank.server.medius.test.NetOutput;
import net.hashsploit.medius.crypto.Utils;

import java.io.IOException;

public class MediusAddToBuddyListResponse extends MediusPacket {

    private final byte[] messageID;
    private final MediusCallbackStatus callbackStatus;

    public MediusAddToBuddyListResponse(byte[] messageID, MediusCallbackStatus callbackStatus) {
        super(MediusMessageType.AddToBuddyListResponse);
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
        return "MediusAddToBuddyListResponse{" +
                "messageID=" + Utils.bytesToHex(messageID) +
                ", callbackStatus=" + callbackStatus +
                '}';
    }
}
