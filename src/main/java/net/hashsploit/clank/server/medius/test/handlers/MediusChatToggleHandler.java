package net.hashsploit.clank.server.medius.test.handlers;

import net.hashsploit.clank.server.MediusClient;
import net.hashsploit.clank.server.medius.MediusCallbackStatus;
import net.hashsploit.clank.server.medius.MediusMessageType;
import net.hashsploit.clank.server.medius.test.MediusPacket;
import net.hashsploit.clank.server.medius.test.MediusPacketHandler;
import net.hashsploit.clank.server.medius.test.NetInput;
import net.hashsploit.clank.server.medius.test.packets.MediusChatToggleRequest;
import net.hashsploit.clank.server.medius.test.packets.MediusChatToggleResponse;

import java.io.IOException;
import java.util.List;

public class MediusChatToggleHandler extends MediusPacketHandler {

    private MediusChatToggleRequest request;

    public MediusChatToggleHandler() {
        super(MediusMessageType.ChatToggle, MediusMessageType.ChatToggleResponse);
    }

    @Override
    public void read(NetInput netInput, MediusClient client) throws IOException {
        this.request = new MediusChatToggleRequest(netInput);
    }

    @Override
    public void write(List<MediusPacket> mediusPackets, MediusClient client) throws IOException {
        mediusPackets.add(new MediusChatToggleResponse(request.getMessageID(), MediusCallbackStatus.SUCCESS));
    }
}
