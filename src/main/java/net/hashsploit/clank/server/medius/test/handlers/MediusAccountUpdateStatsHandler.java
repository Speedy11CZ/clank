package net.hashsploit.clank.server.medius.test.handlers;

import net.hashsploit.clank.Clank;
import net.hashsploit.clank.server.MediusClient;
import net.hashsploit.clank.server.medius.MediusCallbackStatus;
import net.hashsploit.clank.server.medius.MediusMessageType;
import net.hashsploit.clank.server.medius.test.MediusPacket;
import net.hashsploit.clank.server.medius.test.MediusPacketHandler;
import net.hashsploit.clank.server.medius.test.NetInput;
import net.hashsploit.clank.server.medius.test.packets.MediusAccountUpdateStatsRequest;
import net.hashsploit.clank.server.medius.test.packets.MediusAccountUpdateStatsResponse;
import net.hashsploit.clank.utils.Utils;

import java.io.IOException;
import java.util.List;

public class MediusAccountUpdateStatsHandler extends MediusPacketHandler {

    private MediusAccountUpdateStatsRequest request;

    public MediusAccountUpdateStatsHandler() {
        super(MediusMessageType.AccountUpdateStats, MediusMessageType.AccountUpdateStatsResponse);
    }

    @Override
    public void read(NetInput netInput, MediusClient client) throws IOException {
        this.request = new MediusAccountUpdateStatsRequest(netInput);

        Clank.getInstance().getDatabase().updatePlayerStats(client.getPlayer().getAccountId(), Utils.bytesToHex(request.getStats()));
    }

    @Override
    public void write(List<MediusPacket> mediusPackets, MediusClient client) throws IOException {
        mediusPackets.add(new MediusAccountUpdateStatsResponse(request.getMessageID(), MediusCallbackStatus.SUCCESS)); //TODO: RETURN ERROR IF DATABASE IS NOT AVAILABLE
    }
}
