package net.hashsploit.clank.server.medius.test.packets.clans;

import net.hashsploit.clank.server.medius.MediusCallbackStatus;
import net.hashsploit.clank.server.medius.MediusConstants;
import net.hashsploit.clank.server.medius.MediusMessageType;
import net.hashsploit.clank.server.medius.test.MediusPacket;
import net.hashsploit.clank.server.medius.test.NetOutput;
import net.hashsploit.clank.utils.Utils;

import java.io.IOException;

public class MediusCheckMyClanInvitationsResponse extends MediusPacket {

    private final byte[] messageID;
    private final MediusCallbackStatus callbackStatus;
    private final int clanInvitationID;
    private final int clanID;
    private final int responseStatus; //TODO: ENUMERATION
    private final String message;
    private final int leaderAccountID;
    private final String leaderAccountName;
    private final boolean endOfList;

    public MediusCheckMyClanInvitationsResponse(byte[] messageID, MediusCallbackStatus callbackStatus, int clanInvitationID, int clanID, int responseStatus, String message, int leaderAccountID, String leaderAccountName, boolean endOfList) {
        super(MediusMessageType.CheckMyClanInvitationsResponse);
        this.messageID = messageID;
        this.callbackStatus = callbackStatus;
        this.clanInvitationID = clanInvitationID;
        this.clanID = clanID;
        this.responseStatus = responseStatus;
        this.message = message;
        this.leaderAccountID = leaderAccountID;
        this.leaderAccountName = leaderAccountName;
        this.endOfList = endOfList;
    }

    public MediusCheckMyClanInvitationsResponse(byte[] messageID, MediusCallbackStatus callbackStatus) {
        this(messageID, callbackStatus, 0, 0, 0, "", 0, "", true);
    }

    @Override
    public void writePacket(NetOutput netOutput) throws IOException {
        netOutput.writeBytes(messageID);
        netOutput.skipBytes(3);
        netOutput.writeObject(callbackStatus);
        netOutput.writeInt(clanInvitationID);
        netOutput.writeInt(clanID);
        netOutput.writeInt(responseStatus);
        netOutput.writeString(message, MediusConstants.CLANMSG_MAXLEN);
        netOutput.writeInt(leaderAccountID);
        netOutput.writeString(leaderAccountName, MediusConstants.ACCOUNTNAME_MAXLEN);
        netOutput.writeBoolean(endOfList);
    }

    @Override
    public String toString() {
        return "MediusCheckMyClanInvitationsResponse{" +
                "messageID=" + Utils.bytesToHex(messageID) +
                ", callbackStatus=" + callbackStatus +
                ", clanInvitationID=" + clanInvitationID +
                ", clanID=" + clanID +
                ", responseStatus=" + responseStatus +
                ", message='" + message + '\'' +
                ", leaderAccountID=" + leaderAccountID +
                ", leaderAccountName='" + leaderAccountName + '\'' +
                ", endOfList=" + endOfList +
                '}';
    }
}
