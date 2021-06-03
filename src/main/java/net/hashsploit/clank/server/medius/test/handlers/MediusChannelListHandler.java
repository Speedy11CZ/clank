package net.hashsploit.clank.server.medius.test.handlers.channels;

import net.hashsploit.clank.server.MediusClient;
import net.hashsploit.clank.server.medius.MediusCallbackStatus;
import net.hashsploit.clank.server.medius.MediusLobbyServer;
import net.hashsploit.clank.server.medius.MediusMessageType;
import net.hashsploit.clank.server.medius.objects.Channel;
import net.hashsploit.clank.server.medius.test.MediusPacket;
import net.hashsploit.clank.server.medius.test.MediusPacketHandler;
import net.hashsploit.clank.server.medius.test.NetInput;
import net.hashsploit.clank.server.medius.test.packets.channels.MediusChannelListRequest;
import net.hashsploit.clank.server.medius.test.packets.channels.MediusChannelListResponse;

import java.io.IOException;
import java.util.List;

public class MediusChannelListHandler extends MediusPacketHandler {

    private MediusChannelListRequest request;

    public MediusChannelListHandler() {
        super(MediusMessageType.ChannelList, MediusMessageType.ChannelListResponse);
    }

    @Override
    public void read(NetInput netInput, MediusClient client) throws IOException {
        this.request = new MediusChannelListRequest(netInput);
    }

    @Override
    public void write(List<MediusPacket> mediusPackets, MediusClient client) throws IOException {
        MediusLobbyServer server = (MediusLobbyServer) client.getServer();

        if(server.getChannels().size() < 1) {
            mediusPackets.add(new MediusChannelListResponse(request.getMessageID(), MediusCallbackStatus.NO_RESULT));
        } else {
            for (int i = 0; i != server.getChannels().size(); i++) {
                Channel channel = server.getChannels().get(i);
                mediusPackets.add(new MediusChannelListResponse(request.getMessageID(), MediusCallbackStatus.SUCCESS, channel.getId(), channel.getName(), server.getChannelActivePlayerCountById(channel.getId()), i == server.getChannels().size() - 1));
            }
        }
    }
}
