package net.hashsploit.clank.server.medius.test.packets;

import net.hashsploit.clank.server.medius.MediusConstants;
import net.hashsploit.clank.server.medius.MediusMessageType;
import net.hashsploit.clank.server.medius.test.MediusPacket;
import net.hashsploit.clank.server.medius.test.NetInput;
import net.hashsploit.clank.utils.Utils;

import java.io.IOException;

public class MediusChannelList_ExtraInfoOneRequest extends MediusPacket {

    private final byte[] messageID = new byte[MediusConstants.MESSAGEID_MAXLEN.value];
    private final short pageID;
    private final short pageSize;

    public MediusChannelList_ExtraInfoOneRequest(NetInput netInput) throws IOException {
        super(MediusMessageType.ChannelList_ExtraInfo1, netInput);
        netInput.readBytes(messageID);
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
        return "MediusChannelList_ExtraInfoOneRequest{" +
                "messageID=" + Utils.bytesToHex(messageID) +
                ", pageID=" + pageID +
                ", pageSize=" + pageSize +
                '}';
    }
}
