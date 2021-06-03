package net.hashsploit.clank.server.medius.test.packets;

import net.hashsploit.clank.server.medius.MediusCallbackStatus;
import net.hashsploit.clank.server.medius.MediusConstants;
import net.hashsploit.clank.server.medius.MediusMessageType;
import net.hashsploit.clank.server.medius.test.MediusPacket;
import net.hashsploit.clank.server.medius.test.NetOutput;
import net.hashsploit.clank.utils.Utils;

import java.io.IOException;

public class MediusChannelInfoResponse extends MediusPacket {

    private final byte[] messageID;
    private final MediusCallbackStatus callbackStatus;
    private final String lobbyName;
    private final int activePlayerCount;
    private final int maxPlayers;

    public MediusChannelInfoResponse(byte[] messageID, MediusCallbackStatus callbackStatus, String lobbyName, int activePlayerCount, int maxPlayers) {
        super(MediusMessageType.ChannelInfoResponse);
        this.messageID = messageID;
        this.callbackStatus = callbackStatus;
        this.lobbyName = lobbyName;
        this.activePlayerCount = activePlayerCount;
        this.maxPlayers = maxPlayers;
    }

    @Override
    public void writePacket(NetOutput netOutput) throws IOException {
        netOutput.writeBytes(messageID);
        netOutput.skipBytes(3);
        netOutput.writeObject(callbackStatus);
        netOutput.writeString(lobbyName, MediusConstants.LOBBYNAME_MAXLEN);
        netOutput.writeInt(activePlayerCount);
        netOutput.writeInt(maxPlayers);
    }

    @Override
    public String toString() {
        return "MediusChannelInfoResponse{" +
                "messageID=" + Utils.bytesToHex(messageID) +
                ", callbackStatus=" + callbackStatus +
                ", lobbyName='" + lobbyName + '\'' +
                ", activePlayerCount=" + activePlayerCount +
                ", maxPlayers=" + maxPlayers +
                '}';
    }
}
