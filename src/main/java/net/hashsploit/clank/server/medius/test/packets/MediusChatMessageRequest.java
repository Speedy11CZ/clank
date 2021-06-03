package net.hashsploit.clank.server.medius.test.packets;

import net.hashsploit.clank.server.medius.MediusConstants;
import net.hashsploit.clank.server.medius.MediusMessageType;
import net.hashsploit.clank.server.medius.objects.MediusChatMessageType;
import net.hashsploit.clank.server.medius.test.MediusPacket;
import net.hashsploit.clank.server.medius.test.NetInput;
import net.hashsploit.clank.utils.Utils;

import java.io.IOException;

public class MediusChatMessageRequest extends MediusPacket {

    private final byte[] messageID = new byte[MediusConstants.MESSAGEID_MAXLEN.value];
    private final byte[] sessionKey = new byte[MediusConstants.SESSIONKEY_MAXLEN.value];
    private final MediusChatMessageType messageType;
    private final int targetID;
    private final String text;

    public MediusChatMessageRequest(NetInput netInput) throws IOException {
        super(MediusMessageType.ChatMessage);
        netInput.readBytes(messageID);
        netInput.readBytes(sessionKey);
        netInput.skipBytes(2);
        messageType = MediusChatMessageType.getByValue(netInput.readInt());
        targetID = netInput.readInt();
        text = netInput.readStringClean(MediusConstants.CHATMESSAGE_MAXLEN);
    }

    public byte[] getMessageID() {
        return messageID;
    }

    public MediusChatMessageType getMessageType() {
        return messageType;
    }

    public int getTargetID() {
        return targetID;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return "MediusChatMessageRequest{" +
                "messageID=" + Utils.bytesToHex(messageID) +
                ", sessionKey=" + Utils.bytesToHex(sessionKey) +
                ", mediusChatMessageType=" + messageType +
                ", targetID=" + targetID +
                ", text='" + text + '\'' +
                '}';
    }
}
