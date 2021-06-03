package net.hashsploit.clank.server.medius.test.handlers;

import net.hashsploit.clank.server.MediusClient;
import net.hashsploit.clank.server.medius.MediusCallbackStatus;
import net.hashsploit.clank.server.medius.MediusMessageType;
import net.hashsploit.clank.server.medius.test.MediusPacket;
import net.hashsploit.clank.server.medius.test.MediusPacketHandler;
import net.hashsploit.clank.server.medius.test.NetInput;
import net.hashsploit.clank.server.medius.test.packets.MediusAddToBuddyListRequest;
import net.hashsploit.clank.server.medius.test.packets.MediusAddToBuddyListResponse;

import java.io.IOException;
import java.util.List;

public class MediusAddToBuddyListHandler extends MediusPacketHandler {

    private MediusAddToBuddyListRequest request;

    public MediusAddToBuddyListHandler() {
        super(MediusMessageType.AddToBuddyList, MediusMessageType.AddToBuddyListResponse);
    }

    @Override
    public void read(NetInput netInput, MediusClient client) throws IOException {
        this.request = new MediusAddToBuddyListRequest(netInput);
    }

    @Override
    public void write(List<MediusPacket> mediusPackets, MediusClient client) throws IOException {
        mediusPackets.add(new MediusAddToBuddyListResponse(request.getMessageID(), MediusCallbackStatus.SUCCESS));  //TODO: SAVE BUDDY INTO DATABASE
    }
}
