package net.hashsploit.clank.server.medius.test.packets;

import net.hashsploit.clank.server.medius.MediusConstants;
import net.hashsploit.clank.server.medius.MediusMessageType;
import net.hashsploit.clank.server.medius.test.MediusPacket;
import net.hashsploit.clank.server.medius.test.NetInput;
import net.hashsploit.clank.utils.Utils;

import java.io.IOException;

public class MediusClearGameListFilterZeroRequest extends MediusPacket {

    private final byte[] messageID = new byte[MediusConstants.MESSAGEID_MAXLEN.value];

    public MediusClearGameListFilterZeroRequest(NetInput netInput) throws IOException {
        super(MediusMessageType.ClearGameListFilter0, netInput);
        netInput.readBytes(messageID);
    }

    public byte[] getMessageID() {
        return messageID;
    }

    @Override
    public String toString() {
        return "MediusClearGameListFilterZeroRequest{" +
                "messageID=" + Utils.bytesToHex(messageID) +
                '}';
    }
}
