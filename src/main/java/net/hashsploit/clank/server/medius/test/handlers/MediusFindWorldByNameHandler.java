package net.hashsploit.clank.server.medius.test.handlers;

import net.hashsploit.clank.server.MediusClient;
import net.hashsploit.clank.server.medius.MediusCallbackStatus;
import net.hashsploit.clank.server.medius.MediusMessageType;
import net.hashsploit.clank.server.medius.objects.MediusApplicationType;
import net.hashsploit.clank.server.medius.objects.MediusWorldStatus;
import net.hashsploit.clank.server.medius.test.MediusPacket;
import net.hashsploit.clank.server.medius.test.MediusPacketHandler;
import net.hashsploit.clank.server.medius.test.NetInput;
import net.hashsploit.clank.server.medius.test.packets.MediusFindWorldByNameRequest;
import net.hashsploit.clank.server.medius.test.packets.MediusFindWorldByNameResponse;

import java.io.IOException;
import java.util.List;

public class MediusFindWorldByNameHandler extends MediusPacketHandler {

    private MediusFindWorldByNameRequest request;

    public MediusFindWorldByNameHandler() {
        super(MediusMessageType.FindWorldByName, MediusMessageType.FindWorldByNameResponse);
    }

    @Override
    public void read(NetInput netInput, MediusClient client) throws IOException {
        this.request = new MediusFindWorldByNameRequest(netInput);
    }

    @Override
    public void write(List<MediusPacket> mediusPackets, MediusClient client) throws IOException {

        MediusCallbackStatus callbackStatus = MediusCallbackStatus.SUCCESS;
        int applicationID = 10411;
        String applicationName = "Syphon Filter";
        MediusApplicationType applicationType = MediusApplicationType.LOBBY_CHAT_CHANNEL;
        int worldID = 40;
        String worldName = request.getWorldName();
        MediusWorldStatus worldStatus = MediusWorldStatus.WORLD_STAGING;
        boolean endOfList = true;

        mediusPackets.add(new MediusFindWorldByNameResponse(request.getMessageID(), callbackStatus, applicationID, applicationName, applicationType, worldID, worldName, worldStatus, endOfList));
    }
}
