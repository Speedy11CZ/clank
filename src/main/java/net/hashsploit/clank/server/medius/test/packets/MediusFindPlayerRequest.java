package net.hashsploit.clank.server.medius.test.packets;

import net.hashsploit.clank.server.medius.MediusConstants;
import net.hashsploit.clank.server.medius.MediusMessageType;
import net.hashsploit.clank.server.medius.objects.MediusPlayerSearchType;
import net.hashsploit.clank.server.medius.test.MediusPacket;
import net.hashsploit.clank.server.medius.test.NetInput;
import net.hashsploit.clank.utils.Utils;

import java.io.IOException;

public class MediusFindPlayerRequest extends MediusPacket {

    private final byte[] messageID = new byte[MediusConstants.MESSAGEID_MAXLEN.value];
    private final byte[] sessionKey = new byte[MediusConstants.SESSIONKEY_MAXLEN.value];
    private final MediusPlayerSearchType searchType;
    private final int accountID;
    private final String accountName;

    public MediusFindPlayerRequest(NetInput netInput) throws IOException {
        super(MediusMessageType.FindPlayer, netInput);
        netInput.readBytes(messageID);
        netInput.readBytes(sessionKey);
        netInput.skipBytes(2);
        searchType = MediusPlayerSearchType.getTypeFromValue(netInput.readInt());
        accountID = netInput.readInt();
        accountName = netInput.readString(MediusConstants.PLAYERNAME_MAXLEN);
    }

    public byte[] getMessageID() {
        return messageID;
    }

    public MediusPlayerSearchType getSearchType() {
        return searchType;
    }

    public int getAccountID() {
        return accountID;
    }

    public String getAccountName() {
        return accountName;
    }

    @Override
    public String toString() {
        return "MediusFindPlayerRequest{" +
                "messageID=" + Utils.bytesToHex(messageID) +
                ", sessionKey=" + Utils.bytesToHex(sessionKey) +
                ", searchType=" + searchType +
                ", accountID=" + accountID +
                ", accountName='" + accountName + '\'' +
                '}';
    }
}
