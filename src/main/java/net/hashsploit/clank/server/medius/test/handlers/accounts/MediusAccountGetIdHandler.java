package net.hashsploit.clank.server.medius.test.handlers.accounts;

import net.hashsploit.clank.server.MediusClient;
import net.hashsploit.clank.server.medius.MediusCallbackStatus;
import net.hashsploit.clank.server.medius.MediusMessageType;
import net.hashsploit.clank.server.medius.test.NetInput;
import net.hashsploit.clank.server.medius.test.MediusPacket;
import net.hashsploit.clank.server.medius.test.MediusPacketHandler;
import net.hashsploit.clank.server.medius.test.packets.accounts.MediusAccountGetIdRequest;
import net.hashsploit.clank.server.medius.test.packets.accounts.MediusAccountGetIdResponse;

import java.io.IOException;
import java.util.List;

public class MediusAccountGetIdHandler extends MediusPacketHandler {

    private MediusAccountGetIdRequest request;

    public MediusAccountGetIdHandler() {
        super(MediusMessageType.AccountGetId, MediusMessageType.AccountGetIdResponse);
    }

    @Override
    public void read(NetInput netInput, MediusClient client) throws IOException {
        this.request = new MediusAccountGetIdRequest(netInput);
    }

    @Override
    public void write(List<MediusPacket> mediusPackets, MediusClient client) throws IOException {
        mediusPackets.add(new MediusAccountGetIdResponse(request.getMessageID(), 0, MediusCallbackStatus.SUCCESS)); //TODO: RETURN REAL ID FROM DATABASE
    }
}
