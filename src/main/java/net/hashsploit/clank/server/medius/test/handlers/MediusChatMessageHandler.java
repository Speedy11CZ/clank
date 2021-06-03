package net.hashsploit.clank.server.medius.test.handlers;

import net.hashsploit.clank.server.MediusClient;
import net.hashsploit.clank.server.Player;
import net.hashsploit.clank.server.medius.MediusLobbyServer;
import net.hashsploit.clank.server.medius.MediusMessageType;
import net.hashsploit.clank.server.medius.objects.MediusChatMessageType;
import net.hashsploit.clank.server.medius.test.MediusPacket;
import net.hashsploit.clank.server.medius.test.MediusPacketHandler;
import net.hashsploit.clank.server.medius.test.NetInput;
import net.hashsploit.clank.server.medius.test.packets.MediusChatFwdMessageResponse;
import net.hashsploit.clank.server.medius.test.packets.MediusChatMessageRequest;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;

public class MediusChatMessageHandler extends MediusPacketHandler {

    private MediusChatMessageRequest request;

    public MediusChatMessageHandler(MediusMessageType requestMessageType, MediusMessageType responseMessageType) {
        super(requestMessageType, responseMessageType);
    }

    @Override
    public void read(NetInput netInput, MediusClient client) throws IOException {
        this.request = new MediusChatMessageRequest(netInput);
    }

    @Override
    public void write(List<MediusPacket> mediusPackets, MediusClient client) throws IOException {
        // FIXME: sanitize input, check for color parsing, check for muted, etc.

        LOGGER.finest("[CHAT] " + client.getPlayer().getUsername() + ": " + request.getText());

        // This should be ChatColor.strip() unless the player is an operator.	String username = client.getPlayer().getUsername();
        //		String chatMsg = Utils.bytesToStringClean(requestPacket.getText());
        //
        //		// FIXME: sanitize input, check for color parsing, check for muted, etc.
        //
        //		logger.finest("[CHAT] " + username + ": " + chatMsg);
        //
        //		// This should be ChatColor.strip() unless the player is an operator.
        //		byte[] byteMsg = requestPacket.getText(); //Utils.padByteArray(ChatColor.parse(chatMsg), MediusConstants.CHATMESSAGE_MAXLEN.value);
        //
        //		ChatFwdMessageResponse responsePacket = new ChatFwdMessageResponse(requestPacket.getMessageId(), client.getPlayer().getAccountId(), Utils.buildByteArrayFromString(username, MediusConstants.USERNAME_MAXLEN.value), byteMsg, requestPacket.getMessageType());
        //		int playerWorldId = client.getPlayer().getChatWorldId();
        //		MediusLobbyServer server = (MediusLobbyServer) client.getServer();
        //		HashSet<Player> playersInWorld = server.getLobbyWorldPlayers(playerWorldId);
        //
        //		if (requestPacket.getMessageType() == MediusChatMessageType.BROADCAST) {
        //			for (Player player : playersInWorld) {
        //				if (player != client.getPlayer()) {
        //					player.getClient().sendMediusMessage(responsePacket);
        //				}
        //			}
        //		}
        //		else if (requestPacket.getMessageType() == MediusChatMessageType.WHISPER) {
        //			Player p = server.getPlayer(requestPacket.getTargetId());
        //			if (p != null) {
        //				p.getClient().sendMediusMessage(responsePacket);
        //			}

        //		}
        //Utils.padByteArray(ChatColor.parse(chatMsg), MediusConstants.CHATMESSAGE_MAXLEN.value);

        MediusChatFwdMessageResponse responsePacket = new MediusChatFwdMessageResponse(request.getMessageID(), client.getPlayer().getUsername(), request.getText(), client.getPlayer().getAccountId(), request.getMessageType());
        int playerWorldId = client.getPlayer().getChatWorldId();
        MediusLobbyServer server = (MediusLobbyServer) client.getServer();
        HashSet<Player> playersInWorld = server.getLobbyWorldPlayers(playerWorldId);

        if (request.getMessageType() == MediusChatMessageType.BROADCAST) {
            for (Player player : playersInWorld) {
                if (player != client.getPlayer()) {
                    player.getClient().sendMediusPacket(responsePacket);
                }
            }
        } else if (request.getMessageType() == MediusChatMessageType.WHISPER) {
            Player p = server.getPlayer(request.getTargetID());
            if (p != null) {
                p.getClient().sendMediusPacket(responsePacket);
            }
        }
    }
}
