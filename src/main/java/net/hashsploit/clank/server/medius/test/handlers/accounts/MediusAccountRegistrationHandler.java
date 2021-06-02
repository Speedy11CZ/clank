package net.hashsploit.clank.server.medius.test.handlers.accounts;

import net.hashsploit.clank.server.MediusClient;
import net.hashsploit.clank.server.medius.MediusCallbackStatus;
import net.hashsploit.clank.server.medius.MediusMessageType;
import net.hashsploit.clank.server.medius.test.MediusPacket;
import net.hashsploit.clank.server.medius.test.MediusPacketHandler;
import net.hashsploit.clank.server.medius.test.NetInput;
import net.hashsploit.clank.server.medius.test.packets.accounts.MediusAccountRegistrationRequest;
import net.hashsploit.clank.server.medius.test.packets.accounts.MediusAccountRegistrationResponse;

import java.io.IOException;
import java.util.List;

public class MediusAccountRegistrationHandler extends MediusPacketHandler {

    private MediusAccountRegistrationRequest request;

    public MediusAccountRegistrationHandler() {
        super(MediusMessageType.AccountRegistration, MediusMessageType.AccountRegistrationResponse);
    }

    @Override
    public void read(NetInput netInput, MediusClient client) throws IOException {
        this.request = new MediusAccountRegistrationRequest(netInput);
    }

    @Override
    public void write(List<MediusPacket> mediusPackets, MediusClient client) throws IOException {
        mediusPackets.add(new MediusAccountRegistrationResponse(request.getMessageID(), MediusCallbackStatus.SUCCESS, 0));  //TODO: RETURN REAL ID FROM DATABASE
    }
}
