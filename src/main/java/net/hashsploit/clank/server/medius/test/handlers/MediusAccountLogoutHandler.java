package net.hashsploit.clank.server.medius.test.handlers;

import net.hashsploit.clank.server.MediusClient;
import net.hashsploit.clank.server.medius.MediusCallbackStatus;
import net.hashsploit.clank.server.medius.MediusMessageType;
import net.hashsploit.clank.server.medius.test.MediusPacket;
import net.hashsploit.clank.server.medius.test.MediusPacketHandler;
import net.hashsploit.clank.server.medius.test.NetInput;
import net.hashsploit.clank.server.medius.test.packets.MediusAccountLogoutRequest;
import net.hashsploit.clank.server.medius.test.packets.MediusAccountLogoutResponse;

import java.io.IOException;
import java.util.List;

public class MediusAccountLogoutHandler extends MediusPacketHandler {

    private MediusAccountLogoutRequest request;

    public MediusAccountLogoutHandler() {
        super(MediusMessageType.AccountLogout, MediusMessageType.AccountLogoutResponse);
    }

    @Override
    public void read(NetInput netInput, MediusClient client) throws IOException {
        this.request = new MediusAccountLogoutRequest(netInput);
    }

    @Override
    public void write(List<MediusPacket> mediusPackets, MediusClient client) throws IOException {
        mediusPackets.add(new MediusAccountLogoutResponse(request.getMessageID(), MediusCallbackStatus.SUCCESS));
    }
}
