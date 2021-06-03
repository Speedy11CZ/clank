package net.hashsploit.clank.server.medius.test.packets;

import net.hashsploit.clank.server.medius.MediusConstants;
import net.hashsploit.clank.server.medius.MediusMessageType;
import net.hashsploit.clank.server.medius.objects.MediusChatMessageType;
import net.hashsploit.clank.server.medius.test.MediusPacket;
import net.hashsploit.clank.server.medius.test.NetOutput;
import net.hashsploit.clank.utils.Utils;

import java.io.IOException;

public class MediusChatFwdMessageResponse extends MediusPacket {

    private final byte[] messageID;
    private final String accountName;
    private final String message;
    private final int sourceAccountID;
    private final MediusChatMessageType messageType;

    public MediusChatFwdMessageResponse(byte[] messageID, String accountName, String message, int sourceAccountID, MediusChatMessageType messageType) {
        super(MediusMessageType.ChatFwdMessage);
        this.messageID = messageID;
        this.accountName = accountName;
        this.message = message;
        this.sourceAccountID = sourceAccountID;
        this.messageType = messageType;
    }

    @Override
    public void writePacket(NetOutput netOutput) throws IOException {
        netOutput.writeBytes(messageID);
        netOutput.skipBytes(3);
        netOutput.writeInt(sourceAccountID);
        netOutput.writeString(accountName, MediusConstants.ACCOUNTNAME_MAXLEN);
        netOutput.writeObject(messageType);
        netOutput.writeString(message, MediusConstants.CHATMESSAGE_MAXLEN);
    }

    @Override
    public String toString() {
        return "MediusChatFwdMessageResponse{" +
                "messageID=" + Utils.bytesToHex(messageID) +
                ", accountName='" + accountName + '\'' +
                ", message='" + message + '\'' +
                ", sourceAccountID=" + sourceAccountID +
                ", messageType=" + messageType +
                '}';
    }
}
