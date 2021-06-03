package net.hashsploit.clank.server.medius.test.packets;

import net.hashsploit.clank.server.medius.MediusCallbackStatus;
import net.hashsploit.clank.server.medius.MediusMessageType;
import net.hashsploit.clank.server.medius.test.MediusPacket;
import net.hashsploit.clank.server.medius.test.NetOutput;
import net.hashsploit.clank.utils.Utils;

import java.io.IOException;

public class MediusCreateGameResponse extends MediusPacket {

    private final byte[] messageID;
    private final MediusCallbackStatus callbackStatus;
    private final int newWorldID;

    public MediusCreateGameResponse(byte[] messageID, MediusCallbackStatus callbackStatus, int newWorldID) {
        super(MediusMessageType.CreateGameResponse);
        this.messageID = messageID;
        this.callbackStatus = callbackStatus;
        this.newWorldID = newWorldID;
    }

    @Override
    public void writePacket(NetOutput netOutput) throws IOException {
        netOutput.writeBytes(messageID);
        netOutput.skipBytes(3);
        netOutput.writeObject(callbackStatus);
        netOutput.writeInt(newWorldID);
    }

    @Override
    public String toString() {
        return "MediusCreateGameResponse{" +
                "messageID=" + Utils.bytesToHex(messageID) +
                ", callbackStatus=" + callbackStatus +
                ", newWorldID=" + newWorldID +
                '}';
    }
}
