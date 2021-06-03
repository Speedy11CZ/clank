package net.hashsploit.clank.server.medius.test.packets;

import net.hashsploit.clank.server.medius.MediusCallbackStatus;
import net.hashsploit.clank.server.medius.MediusConstants;
import net.hashsploit.clank.server.medius.MediusMessageType;
import net.hashsploit.clank.server.medius.test.MediusPacket;
import net.hashsploit.clank.server.medius.test.NetOutput;
import net.hashsploit.clank.utils.Utils;

import java.io.IOException;

public class MediusChannelListResponse extends MediusPacket {

    private final byte[] messageID;
    private final MediusCallbackStatus callbackStatus;
    private final int worldID;
    private final String lobbyName;
    private final int playerCount;
    private final boolean endOfList;

    public MediusChannelListResponse(byte[] messageID, MediusCallbackStatus callbackStatus, int mediusWorldId, String lobbyName, int playerCount, boolean endOfList) {
        super(MediusMessageType.ChannelListResponse);
        this.messageID = messageID;
        this.callbackStatus = callbackStatus;
        this.worldID = mediusWorldId;
        this.lobbyName = lobbyName;
        this.playerCount = playerCount;
        this.endOfList = endOfList;
    }

    public MediusChannelListResponse(byte[] messageID, MediusCallbackStatus callbackStatus) {
        super(MediusMessageType.ChannelListResponse);
        this.messageID = messageID;
        this.callbackStatus = callbackStatus;
        this.worldID = 0;
        this.lobbyName = "";
        this.playerCount = 0;
        this.endOfList = true;
    }

    @Override
    public void writePacket(NetOutput netOutput) throws IOException {
        netOutput.writeBytes(messageID);
        netOutput.skipBytes(3);
        netOutput.writeObject(callbackStatus);
        netOutput.writeInt(worldID);
        netOutput.writeString(lobbyName, MediusConstants.LOBBYNAME_MAXLEN);
        netOutput.writeInt(playerCount);
        netOutput.writeBoolean(endOfList);
    }

    @Override
    public String toString() {
        return "MediusChannelListResponse{" +
                "messageID=" + Utils.bytesToHex(messageID) +
                ", callbackStatus=" + callbackStatus +
                ", worldID=" + worldID +
                ", lobbyName='" + lobbyName + '\'' +
                ", playerCount=" + playerCount +
                ", endOfList=" + endOfList +
                '}';
    }
}
