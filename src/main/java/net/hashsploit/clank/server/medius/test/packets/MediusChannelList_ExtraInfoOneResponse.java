package net.hashsploit.clank.server.medius.test.packets;

import net.hashsploit.clank.server.ChatColor;
import net.hashsploit.clank.server.medius.MediusCallbackStatus;
import net.hashsploit.clank.server.medius.MediusConstants;
import net.hashsploit.clank.server.medius.MediusMessageType;
import net.hashsploit.clank.server.medius.objects.MediusWorldGenericFieldLevelType;
import net.hashsploit.clank.server.medius.objects.MediusWorldSecurityLevelType;
import net.hashsploit.clank.server.medius.test.MediusPacket;
import net.hashsploit.clank.server.medius.test.NetOutput;
import net.hashsploit.clank.utils.Utils;

import java.io.IOException;

public class MediusChannelList_ExtraInfoOneResponse extends MediusPacket {

    private final byte[] messageID;
    private final MediusCallbackStatus callbackStatus;
    private final int worldID;
    private final short playerCount;
    private final short maxPlayers;
    private final MediusWorldSecurityLevelType securityLevel;
    private final int genericField1;
    private final int genericField2;
    private final int genericField3;
    private final int genericField4;
    private final MediusWorldGenericFieldLevelType genericFieldLevel;
    private final String lobbyName;
    private final boolean endOfList;

    public MediusChannelList_ExtraInfoOneResponse(byte[] messageID, MediusCallbackStatus callbackStatus, int worldID, short playerCount, short maxPlayers, MediusWorldSecurityLevelType securityLevel, int genericField1, int genericField2, int genericField3, int genericField4, MediusWorldGenericFieldLevelType genericFieldLevel, String lobbyName, boolean endOfList) {
        super(MediusMessageType.ChannelList_ExtraInfo);
        this.messageID = messageID;
        this.callbackStatus = callbackStatus;
        this.worldID = worldID;
        this.playerCount = playerCount;
        this.maxPlayers = maxPlayers;
        this.securityLevel = securityLevel;
        this.genericField1 = genericField1;
        this.genericField2 = genericField2;
        this.genericField3 = genericField3;
        this.genericField4 = genericField4;
        this.genericFieldLevel = genericFieldLevel;
        this.lobbyName = lobbyName;
        this.endOfList = endOfList;
    }

    public MediusChannelList_ExtraInfoOneResponse(byte[] messageID, MediusCallbackStatus callbackStatus) {
        this(messageID, callbackStatus, (short) 0, (short) 0, (short) 0, MediusWorldSecurityLevelType.WORLD_SECURITY_NONE, 0, 0, 0, 0, MediusWorldGenericFieldLevelType.WORLD_GENERIC_FIELD_LEVEL_0, "", true);
    }

    @Override
    public void writePacket(NetOutput netOutput) throws IOException {
        netOutput.writeBytes(messageID);
        netOutput.skipBytes(3);
        netOutput.writeObject(callbackStatus);
        netOutput.writeInt(worldID);
        netOutput.writeShort(playerCount);
        netOutput.writeShort(maxPlayers);
        netOutput.writeObject(securityLevel);
        netOutput.writeInt(genericField1);
        netOutput.writeInt(genericField2);
        netOutput.writeInt(genericField3);
        netOutput.writeInt(genericField4);
        netOutput.writeObject(genericFieldLevel);
        netOutput.writeBytes(Utils.padByteArray(ChatColor.parse(lobbyName), MediusConstants.LOBBYNAME_MAXLEN.value));
        netOutput.writeBoolean(endOfList);
    }

    @Override
    public String toString() {
        return "MediusChannelList_ExtraInfoOneResponse{" +
                "messageID=" + Utils.bytesToHex(messageID) +
                ", callbackStatus=" + callbackStatus +
                ", worldID=" + worldID +
                ", playerCount=" + playerCount +
                ", maxPlayers=" + maxPlayers +
                ", securityLevel=" + securityLevel +
                ", genericField1=" + genericField1 +
                ", genericField2=" + genericField2 +
                ", genericField3=" + genericField3 +
                ", genericField4=" + genericField4 +
                ", genericFieldLevel=" + genericFieldLevel +
                ", lobbyName='" + lobbyName + '\'' +
                ", endOfList=" + endOfList +
                '}';
    }
}
