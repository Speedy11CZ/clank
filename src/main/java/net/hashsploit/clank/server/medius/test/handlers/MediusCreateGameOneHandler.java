package net.hashsploit.clank.server.medius.test.handlers;

import net.hashsploit.clank.server.MediusClient;
import net.hashsploit.clank.server.medius.MediusCallbackStatus;
import net.hashsploit.clank.server.medius.MediusLobbyServer;
import net.hashsploit.clank.server.medius.MediusMessageType;
import net.hashsploit.clank.server.medius.test.MediusPacket;
import net.hashsploit.clank.server.medius.test.MediusPacketHandler;
import net.hashsploit.clank.server.medius.test.NetInput;
import net.hashsploit.clank.server.medius.test.packets.MediusCreateGameOneRequest;
import net.hashsploit.clank.server.medius.test.packets.MediusCreateGameResponse;

import java.io.IOException;
import java.util.List;

public class MediusCreateGameOneHandler extends MediusPacketHandler {

    private MediusCreateGameOneRequest request;

    public MediusCreateGameOneHandler() {
        super(MediusMessageType.CreateGame1, MediusMessageType.CreateGameResponse);
    }


    @Override
    public void read(NetInput netInput, MediusClient client) throws IOException {
        this.request = new MediusCreateGameOneRequest(netInput);
    }

    @Override
    public void write(List<MediusPacket> mediusPackets, MediusClient client) throws IOException {
        MediusLobbyServer server = (MediusLobbyServer) client.getServer();

        int newWorldId = server.createGame(request);
        MediusCallbackStatus callbackStatus = MediusCallbackStatus.FAILURE;

        if (newWorldId > 0) {
            callbackStatus = MediusCallbackStatus.SUCCESS;
        }

        mediusPackets.add(new MediusCreateGameResponse(request.getMessageID(), callbackStatus, newWorldId));

    }
}
