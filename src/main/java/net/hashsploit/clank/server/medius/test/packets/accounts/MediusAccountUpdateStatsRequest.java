package net.hashsploit.clank.server.medius.test.packets.accounts;

import net.hashsploit.clank.server.medius.MediusConstants;
import net.hashsploit.clank.server.medius.MediusMessageType;
import net.hashsploit.clank.server.medius.test.MediusPacket;
import net.hashsploit.clank.server.medius.test.NetInput;
import net.hashsploit.medius.crypto.Utils;

import java.io.IOException;

public class MediusAccountUpdateStatsRequest extends MediusPacket {

    private final byte[] messageID = new byte[MediusConstants.MESSAGEID_MAXLEN.value];
    private final byte[] sessionKey = new byte[MediusConstants.SESSIONKEY_MAXLEN.value];
    private final byte[] stats = new byte[MediusConstants.ACCOUNTSTATS_MAXLEN.value];

    public MediusAccountUpdateStatsRequest(NetInput netInput) throws IOException {
        super(MediusMessageType.AccountUpdateStats, netInput);
        netInput.readBytes(messageID);
        netInput.readBytes(sessionKey);
        netInput.readBytes(stats);
    }

    public byte[] getMessageID() {
        return messageID;
    }

    public byte[] getStats() {
        return stats;
    }

    @Override
    public String toString() {
        return "MediusAccountUpdateStatsRequest{" +
                "messageID=" + Utils.bytesToHex(messageID) +
                ", sessionKey=" + Utils.bytesToHex(sessionKey) +
                ", stats=" + Utils.bytesToHex(stats) +
                '}';
    }
}
