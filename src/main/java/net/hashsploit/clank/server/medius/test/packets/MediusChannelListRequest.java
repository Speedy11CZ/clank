package net.hashsploit.clank.server.medius.test.packets;

import net.hashsploit.clank.server.medius.MediusConstants;
import net.hashsploit.clank.server.medius.MediusMessageType;
import net.hashsploit.clank.server.medius.test.MediusPacket;
import net.hashsploit.clank.server.medius.test.NetInput;
import net.hashsploit.clank.utils.Utils;

import java.io.IOException;

public class MediusChannelListRequest extends MediusPacket {

    private final byte[] messageID = new byte[MediusConstants.MESSAGEID_MAXLEN.value];
    private final byte[] sessionKey = new byte[MediusConstants.SESSIONKEY_MAXLEN.value];
    private final short pageID;
    private final short pageSize;

    public MediusChannelListRequest(NetInput netInput) throws IOException {
        super(MediusMessageType.ChannelList, netInput);
        netInput.readBytes(messageID);
        netInput.readBytes(sessionKey);
        this.pageID = netInput.readShort();
        this.pageSize = netInput.readShort();
    }

    public byte[] getMessageID() {
        return messageID;
    }

    public short getPageID() {
        return pageID;
    }

    public short getPageSize() {
        return pageSize;
    }

    @Override
    public String toString() {
        return "MediusChannelListRequest{" +
                "messageID=" + Utils.bytesToHex(messageID) +
                ", sessionKey=" + Utils.bytesToHex(sessionKey) +
                ", pageID=" + pageID +
                ", pageSize=" + pageSize +
                '}';
    }
}
