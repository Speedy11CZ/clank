package net.hashsploit.clank.server.medius.test.handlers;

import net.hashsploit.clank.server.MediusClient;
import net.hashsploit.clank.server.MediusGame;
import net.hashsploit.clank.server.Player;
import net.hashsploit.clank.server.medius.MediusCallbackStatus;
import net.hashsploit.clank.server.medius.MediusLobbyServer;
import net.hashsploit.clank.server.medius.MediusMessageType;
import net.hashsploit.clank.server.medius.objects.MediusApplicationType;
import net.hashsploit.clank.server.medius.objects.MediusPlayerStatus;
import net.hashsploit.clank.server.medius.test.MediusPacket;
import net.hashsploit.clank.server.medius.test.MediusPacketHandler;
import net.hashsploit.clank.server.medius.test.NetInput;
import net.hashsploit.clank.server.medius.test.packets.MediusFindPlayerRequest;
import net.hashsploit.clank.server.medius.test.packets.MediusFindPlayerResponse;
import net.hashsploit.clank.utils.Utils;

import java.io.IOException;
import java.util.List;

public class MediusFindPlayerHandler extends MediusPacketHandler {

    private MediusFindPlayerRequest request;

    public MediusFindPlayerHandler() {
        super(MediusMessageType.FindPlayer, MediusMessageType.FindPlayerResponse);
    }

    @Override
    public void read(NetInput netInput, MediusClient client) throws IOException {
        this.request = new MediusFindPlayerRequest(netInput);
    }

    @Override
    public void write(List<MediusPacket> mediusPackets, MediusClient client) throws IOException {
        MediusLobbyServer server = (MediusLobbyServer) client.getServer();

        MediusApplicationType applicationType;
        int worldID;
        MediusCallbackStatus callbackStatus;

        Player player = server.getPlayer(request.getAccountID());

        if (player == null) {
            callbackStatus = MediusCallbackStatus.NO_RESULT;
            applicationType = MediusApplicationType.MEDIUS_APP_TYPE_GAME;
            worldID = -1;
        } else {
            callbackStatus = MediusCallbackStatus.SUCCESS;
            MediusPlayerStatus status = player.getStatus();
            if (status == MediusPlayerStatus.MEDIUS_PLAYER_IN_GAME_WORLD) {
                applicationType = MediusApplicationType.MEDIUS_APP_TYPE_GAME;

                MediusGame game = server.getGameFromPlayer(player);
                worldID = game == null ? -1 : game.getWorldId();
            }
            else if (status == MediusPlayerStatus.MEDIUS_PLAYER_IN_CHAT_WORLD) {
                applicationType = MediusApplicationType.LOBBY_CHAT_CHANNEL;
                worldID = player.getChatWorldId();
            } else {
                applicationType = MediusApplicationType.LOBBY_CHAT_CHANNEL;
                worldID = -1;
            }
        }

        mediusPackets.add(new MediusFindPlayerResponse(request.getMessageID(), callbackStatus, 0, "", applicationType, worldID, request.getAccountID(), request.getAccountName(), true)); //TODO: FIX HARDCODED
    }
}
