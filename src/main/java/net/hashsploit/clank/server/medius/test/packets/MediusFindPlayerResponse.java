package net.hashsploit.clank.server.medius.test.packets;

import net.hashsploit.clank.server.medius.MediusCallbackStatus;
import net.hashsploit.clank.server.medius.MediusConstants;
import net.hashsploit.clank.server.medius.MediusMessageType;
import net.hashsploit.clank.server.medius.objects.MediusApplicationType;
import net.hashsploit.clank.server.medius.test.MediusPacket;
import net.hashsploit.clank.server.medius.test.NetOutput;
import net.hashsploit.clank.utils.Utils;

import java.io.IOException;

public class MediusFindPlayerResponse extends MediusPacket {

    private final byte[] messageID;
    private final MediusCallbackStatus callbackStatus;
    private final int applicatonID;
    private final String applicationName;
    private final MediusApplicationType applicationType;
    private final int worldID;
    private final int accountID;
    private final String accountName;
    private final boolean endOfList;

    public MediusFindPlayerResponse(byte[] messageID, MediusCallbackStatus callbackStatus, int applicatonID, String applicationName, MediusApplicationType applicationType, int worldID, int accountID, String accountName, boolean endOfList) {
        super(MediusMessageType.FindPlayerResponse);
        this.messageID = messageID;
        this.callbackStatus = callbackStatus;
        this.applicatonID = applicatonID;
        this.applicationName = applicationName;
        this.applicationType = applicationType;
        this.worldID = worldID;
        this.accountID = accountID;
        this.accountName = accountName;
        this.endOfList = endOfList;
    }

    @Override
    public void writePacket(NetOutput netOutput) throws IOException {
        netOutput.writeBytes(messageID);
        netOutput.skipBytes(3);
        netOutput.writeObject(callbackStatus);
        netOutput.writeInt(applicatonID);
        netOutput.writeString(applicationName, MediusConstants.APPNAME_MAXLEN);
        netOutput.writeObject(applicationType);
        netOutput.writeInt(worldID);
        netOutput.writeInt(accountID);
        netOutput.writeString(accountName, MediusConstants.PLAYERNAME_MAXLEN);
        netOutput.writeBoolean(endOfList);
    }

    @Override
    public String toString() {
        return "MediusFindPlayerResponse{" +
                "messageID=" + Utils.bytesToHex(messageID) +
                ", callbackStatus=" + callbackStatus +
                ", applicatonID=" + applicatonID +
                ", applicationName='" + applicationName + '\'' +
                ", applicationType=" + applicationType +
                ", worldID=" + worldID +
                ", accountID=" + accountID +
                ", accountName='" + accountName + '\'' +
                ", endOfList=" + endOfList +
                '}';
    }
}
