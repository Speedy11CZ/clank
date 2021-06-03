package net.hashsploit.clank.server.medius.test.packets.buddies;

import net.hashsploit.clank.server.medius.MediusConstants;
import net.hashsploit.clank.server.medius.MediusMessageType;
import net.hashsploit.clank.server.medius.test.MediusPacket;
import net.hashsploit.clank.server.medius.test.NetInput;
import net.hashsploit.clank.utils.Utils;

import java.io.IOException;

public class MediusAddToBuddyListRequest extends MediusPacket {

    private final byte[] messageID = new byte[MediusConstants.MESSAGEID_MAXLEN.value];
    private final byte[] sessionKey = new byte[MediusConstants.SESSIONKEY_MAXLEN.value];
    private final int accountID;

    public MediusAddToBuddyListRequest(NetInput netInput) throws IOException {
        super(MediusMessageType.AddToBuddyList, netInput);
        netInput.readBytes(messageID);
        netInput.readBytes(sessionKey);
        netInput.skipBytes(2);
        accountID = netInput.readInt();
    }

    public byte[] getMessageID() {
        return messageID;
    }

    public int getAccountID() {
        return accountID;
    }

    @Override
    public String toString() {
        return "MediusAddToBuddyListRequest{" +
                "messageID=" + Utils.bytesToHex(messageID) +
                ", sessionKey=" + Utils.bytesToHex(sessionKey) +
                ", accountID=" + accountID +
                '}';
    }
}
