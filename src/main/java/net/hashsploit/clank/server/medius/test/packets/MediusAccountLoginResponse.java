package net.hashsploit.clank.server.medius.test.packets.accounts;

import net.hashsploit.clank.server.medius.MediusCallbackStatus;
import net.hashsploit.clank.server.medius.MediusMessageType;
import net.hashsploit.clank.server.medius.objects.MediusAccountType;
import net.hashsploit.clank.server.medius.objects.NetConnectionInfo;
import net.hashsploit.clank.server.medius.test.MediusPacket;
import net.hashsploit.clank.server.medius.test.NetOutput;
import net.hashsploit.clank.utils.Utils;

import java.io.IOException;

public class MediusAccountLoginResponse extends MediusPacket {

    private final byte[] messageID;
    private final MediusCallbackStatus callbackStatus;
    private final int accountID;
    private final MediusAccountType accountType;
    private final int worldID;
    private final NetConnectionInfo connectInfo;

    public MediusAccountLoginResponse(byte[] messageID, MediusCallbackStatus callbackStatus, int accountID, MediusAccountType accountType, int worldID, NetConnectionInfo connectInfo) {
        super(MediusMessageType.AccountLoginResponse);
        this.messageID = messageID;
        this.callbackStatus = callbackStatus;
        this.accountID = accountID;
        this.accountType = accountType;
        this.worldID = worldID;
        this.connectInfo = connectInfo;

        System.out.println(this);
    }

    @Override
    public void writePacket(NetOutput netOutput) throws IOException {
        netOutput.writeBytes(messageID);
        netOutput.skipBytes(3);
        netOutput.writeObject(callbackStatus);
        netOutput.writeInt(accountID);
        netOutput.writeObject(accountType);
        netOutput.writeInt(worldID);
        netOutput.writeObject(connectInfo);
    }

    @Override
    public String toString() {
        return "MediusAccountLoginResponse{" +
                "messageID=" + Utils.bytesToHex(messageID) +
                ", callbackStatus=" + callbackStatus +
                ", accountID=" + accountID +
                ", accountType=" + accountType +
                ", worldID=" + worldID +
                ", connectInfo=" + connectInfo.toString() +
                '}';
    }
}