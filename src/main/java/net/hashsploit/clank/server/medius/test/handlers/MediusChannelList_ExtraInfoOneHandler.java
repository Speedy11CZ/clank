package net.hashsploit.clank.server.medius.test.handlers;

import net.hashsploit.clank.server.MediusClient;
import net.hashsploit.clank.server.medius.MediusCallbackStatus;
import net.hashsploit.clank.server.medius.MediusLobbyServer;
import net.hashsploit.clank.server.medius.MediusMessageType;
import net.hashsploit.clank.server.medius.objects.Channel;
import net.hashsploit.clank.server.medius.objects.MediusWorldGenericFieldLevelType;
import net.hashsploit.clank.server.medius.objects.MediusWorldSecurityLevelType;
import net.hashsploit.clank.server.medius.test.MediusPacket;
import net.hashsploit.clank.server.medius.test.MediusPacketHandler;
import net.hashsploit.clank.server.medius.test.NetInput;
import net.hashsploit.clank.server.medius.test.packets.MediusChannelList_ExtraInfoOneRequest;
import net.hashsploit.clank.server.medius.test.packets.MediusChannelList_ExtraInfoOneResponse;

import java.io.IOException;
import java.util.List;

public class MediusChannelList_ExtraInfoOneHandler extends MediusPacketHandler {

    private MediusChannelList_ExtraInfoOneRequest request;

    public MediusChannelList_ExtraInfoOneHandler() {
        super(MediusMessageType.ChannelList_ExtraInfo1, MediusMessageType.ChannelList_ExtraInfoResponse);
    }

    @Override
    public void read(NetInput netInput, MediusClient client) throws IOException {
        this.request = new MediusChannelList_ExtraInfoOneRequest(netInput);
    }

    @Override
    public void write(List<MediusPacket> mediusPackets, MediusClient client) throws IOException {
        MediusLobbyServer server = (MediusLobbyServer) client.getServer();
        List<Channel> channels = server.getChannels();

        if (channels.size() > 0) {
            for (int i = 0; i < channels.size() ; i++) {
                Channel channel = channels.get(i);

                // FIXME: HARDCODED
                mediusPackets.add(new MediusChannelList_ExtraInfoOneResponse(request.getMessageID(), MediusCallbackStatus.SUCCESS, channel.getId(), (short) server.getChannelActivePlayerCountById(channel.getId()), (short) channel.getCapacity(), MediusWorldSecurityLevelType.WORLD_SECURITY_NONE, 1, 1, 0, 0, MediusWorldGenericFieldLevelType.WORLD_GENERIC_FIELD_LEVEL_123, channel.getName(), i == channels.size() - 1));
            }
        } else {
            mediusPackets.add(new MediusChannelList_ExtraInfoOneResponse(request.getMessageID(), MediusCallbackStatus.NO_RESULT));
        }
    }
}
