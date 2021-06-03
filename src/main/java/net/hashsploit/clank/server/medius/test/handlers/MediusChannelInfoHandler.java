package net.hashsploit.clank.server.medius.test.handlers;

import net.hashsploit.clank.server.MediusClient;
import net.hashsploit.clank.server.medius.MediusCallbackStatus;
import net.hashsploit.clank.server.medius.MediusLobbyServer;
import net.hashsploit.clank.server.medius.MediusMessageType;
import net.hashsploit.clank.server.medius.objects.Channel;
import net.hashsploit.clank.server.medius.test.MediusPacket;
import net.hashsploit.clank.server.medius.test.MediusPacketHandler;
import net.hashsploit.clank.server.medius.test.NetInput;
import net.hashsploit.clank.server.medius.test.packets.MediusChannelInfoRequest;
import net.hashsploit.clank.server.medius.test.packets.MediusChannelInfoResponse;

import java.io.IOException;
import java.util.List;

public class MediusChannelInfoHandler extends MediusPacketHandler {

    private MediusChannelInfoRequest request;

    public MediusChannelInfoHandler() {
        super(MediusMessageType.ChannelInfo, MediusMessageType.ChannelInfoResponse);
    }

    @Override
    public void read(NetInput netInput, MediusClient client) throws IOException {
        this.request = new MediusChannelInfoRequest(netInput);
    }

    @Override
    public void write(List<MediusPacket> mediusPackets, MediusClient client) throws IOException {
        MediusLobbyServer server = (MediusLobbyServer) client.getServer();
        Channel channel = server.getChannelById(request.getWorldID());

        if (channel == null) {
            channel = server.getChannelById(1);
        }

        mediusPackets.add(new MediusChannelInfoResponse(request.getMessageID(), MediusCallbackStatus.SUCCESS, channel.getName(), server.getChannelActivePlayerCountById(request.getWorldID()), channel.getCapacity()));
    }
}
