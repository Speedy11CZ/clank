package net.hashsploit.clank.server.medius.test.packets;

import net.hashsploit.clank.server.medius.MediusCallbackStatus;
import net.hashsploit.clank.server.medius.MediusMessageType;
import net.hashsploit.clank.server.medius.test.MediusPacket;
import net.hashsploit.clank.server.medius.test.NetOutput;
import net.hashsploit.clank.utils.Utils;

import java.io.IOException;

public class MediusCreateClanResponse extends MediusPacket {

    private final byte[] messageID;
    private final MediusCallbackStatus callbackStatus;
    private final int clanID;

    public MediusCreateClanResponse(byte[] messageID, MediusCallbackStatus callbackStatus, int clanID) {
        super(MediusMessageType.CreateClanResponse);
        this.messageID = messageID;
        this.callbackStatus = callbackStatus;
        this.clanID = clanID;
    }

    @Override
    public void writePacket(NetOutput netOutput) throws IOException {
        netOutput.writeBytes(messageID);
        netOutput.skipBytes(3);
        netOutput.writeObject(callbackStatus);
        netOutput.writeInt(clanID);
    }

    @Override
    public String toString() {
        return "MediusCreateClanResponse{" +
                "messageID=" + Utils.bytesToHex(messageID) +
                ", callbackStatus=" + callbackStatus +
                ", clanID=" + clanID +
                '}';
    }
}
