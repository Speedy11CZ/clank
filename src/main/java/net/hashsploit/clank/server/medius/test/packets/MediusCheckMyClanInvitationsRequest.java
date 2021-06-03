package net.hashsploit.clank.server.medius.test.packets;

import net.hashsploit.clank.server.medius.MediusConstants;
import net.hashsploit.clank.server.medius.MediusMessageType;
import net.hashsploit.clank.server.medius.test.MediusPacket;
import net.hashsploit.clank.server.medius.test.NetInput;
import net.hashsploit.clank.utils.Utils;

import java.io.IOException;

public class MediusCheckMyClanInvitationsRequest extends MediusPacket {

    private final byte[] messageID = new byte[MediusConstants.MESSAGEID_MAXLEN.value];
    private final byte[] sessionKey = new byte[MediusConstants.SESSIONKEY_MAXLEN.value];
    private final int start;
    private final int pageSize;

    public MediusCheckMyClanInvitationsRequest(NetInput netInput) throws IOException {
        super(MediusMessageType.GetClanInvitationsSent, netInput);
        netInput.readBytes(messageID);
        netInput.readBytes(sessionKey);
        netInput.skipBytes(2);
        start = netInput.readInt();
        pageSize = netInput.readInt();
    }

    public byte[] getMessageID() {
        return messageID;
    }

    public int getStart() {
        return start;
    }

    public int getPageSize() {
        return pageSize;
    }

    @Override
    public String toString() {
        return "MediusGetClanInvitationsSentRequest{" +
                "messageID=" + Utils.bytesToHex(messageID) +
                ", sessionKey=" + Utils.bytesToHex(sessionKey) +
                ", start=" + start +
                ", pageSize=" + pageSize +
                '}';
    }
}
