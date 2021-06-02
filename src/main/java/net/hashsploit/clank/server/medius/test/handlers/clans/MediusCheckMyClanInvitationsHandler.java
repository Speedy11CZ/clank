package net.hashsploit.clank.server.medius.test.handlers.clans;

import net.hashsploit.clank.server.MediusClient;
import net.hashsploit.clank.server.medius.MediusCallbackStatus;
import net.hashsploit.clank.server.medius.MediusMessageType;
import net.hashsploit.clank.server.medius.test.NetInput;
import net.hashsploit.clank.server.medius.test.MediusPacket;
import net.hashsploit.clank.server.medius.test.MediusPacketHandler;
import net.hashsploit.clank.server.medius.test.packets.clans.MediusCheckMyClanInvitationsRequest;
import net.hashsploit.clank.server.medius.test.packets.clans.MediusCheckMyClanInvitationsResponse;

import java.io.IOException;
import java.util.List;

public class MediusCheckMyClanInvitationsHandler extends MediusPacketHandler {

    private MediusCheckMyClanInvitationsRequest request;

    public MediusCheckMyClanInvitationsHandler() {
        super(MediusMessageType.CheckMyClanInvitations, MediusMessageType.CheckMyClanInvitationsResponse);
    }

    @Override
    public void read(NetInput netInput, MediusClient client) throws IOException {
        this.request = new MediusCheckMyClanInvitationsRequest(netInput);
    }

    @Override
    public void write(List<MediusPacket> mediusPackets, MediusClient client) throws IOException {
        mediusPackets.add(new MediusCheckMyClanInvitationsResponse(request.getMessageID(), MediusCallbackStatus.NO_RESULT));
    }
}
