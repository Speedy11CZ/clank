package net.hashsploit.clank.server.medius.test.packets;

import net.hashsploit.clank.server.medius.MediusCallbackStatus;
import net.hashsploit.clank.server.medius.MediusConstants;
import net.hashsploit.clank.server.medius.MediusMessageType;
import net.hashsploit.clank.server.medius.objects.MediusApplicationType;
import net.hashsploit.clank.server.medius.objects.MediusWorldStatus;
import net.hashsploit.clank.server.medius.test.MediusPacket;
import net.hashsploit.clank.server.medius.test.NetOutput;
import net.hashsploit.clank.utils.Utils;

import java.io.IOException;

public class MediusFindWorldByNameResponse extends MediusPacket {

    private final byte[] messageID;
    private final MediusCallbackStatus callbackStatus;
    private final int applicationID;
    private final String applicationName;
    private final MediusApplicationType applicationType;
    private final int worldID;
    private final String worldName;
    private final MediusWorldStatus worldStatus;
    private final boolean endOfList;

    public MediusFindWorldByNameResponse(byte[] messageID, MediusCallbackStatus callbackStatus, int applicationID, String applicationName, MediusApplicationType applicationType, int worldID, String worldName, MediusWorldStatus worldStatus, boolean endOfList) {
        super(MediusMessageType.FindWorldByNameResponse);
        this.messageID = messageID;
        this.callbackStatus = callbackStatus;
        this.applicationID = applicationID;
        this.applicationName = applicationName;
        this.applicationType = applicationType;
        this.worldID = worldID;
        this.worldName = worldName;
        this.worldStatus = worldStatus;
        this.endOfList = endOfList;
    }

    @Override
    public void writePacket(NetOutput netOutput) throws IOException {
        netOutput.writeBytes(messageID);
        netOutput.skipBytes(3);
        netOutput.writeObject(callbackStatus);
        netOutput.writeInt(applicationID);
        netOutput.writeString(applicationName, MediusConstants.APPNAME_MAXLEN);
        netOutput.writeObject(applicationType);
        netOutput.writeInt(worldID);
        netOutput.writeString(worldName, MediusConstants.WORLDNAME_MAXLEN);
        netOutput.writeObject(worldStatus);
        netOutput.writeBoolean(endOfList);
    }

    @Override
    public String toString() {
        return "MediusFindWorldByNameResponse{" +
                "messageID=" + Utils.bytesToHex(messageID) +
                ", callbackStatus=" + callbackStatus +
                ", applicationID=" + applicationID +
                ", applicationName='" + applicationName + '\'' +
                ", applicationType=" + applicationType +
                ", worldID=" + worldID +
                ", worldName='" + worldName + '\'' +
                ", worldStatus=" + worldStatus +
                ", endOfList=" + endOfList +
                '}';
    }
}
