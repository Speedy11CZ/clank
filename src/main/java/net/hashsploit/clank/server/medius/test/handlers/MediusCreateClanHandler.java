package net.hashsploit.clank.server.medius.test.handlers;

import net.hashsploit.clank.server.MediusClient;
import net.hashsploit.clank.server.medius.MediusCallbackStatus;
import net.hashsploit.clank.server.medius.MediusMessageType;
import net.hashsploit.clank.server.medius.test.MediusPacket;
import net.hashsploit.clank.server.medius.test.MediusPacketHandler;
import net.hashsploit.clank.server.medius.test.NetInput;
import net.hashsploit.clank.server.medius.test.packets.MediusCreateClanRequest;
import net.hashsploit.clank.server.medius.test.packets.MediusCreateClanResponse;

import java.io.IOException;
import java.util.List;

public class MediusCreateClanHandler extends MediusPacketHandler {

    private MediusCreateClanRequest request;

    public MediusCreateClanHandler() {
        super(MediusMessageType.CreateClan, MediusMessageType.CreateClanResponse);
    }

    @Override
    public void read(NetInput netInput, MediusClient client) throws IOException {
        this.request = new MediusCreateClanRequest(netInput);
    }

    @Override
    public void write(List<MediusPacket> mediusPackets, MediusClient client) throws IOException {
        mediusPackets.add(new MediusCreateClanResponse(request.getMessageID(), MediusCallbackStatus.SUCCESS, 0));  //TODO: RETURN REAL NEW ID FROM DATABASE
    }
}
